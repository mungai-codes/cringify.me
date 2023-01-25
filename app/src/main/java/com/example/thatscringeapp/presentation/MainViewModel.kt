package com.example.thatscringeapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thatscringeapp.BuildConfig
import com.example.thatscringeapp.data.remote.OpenAiService
import com.example.thatscringeapp.data.remote.RequestBody
import com.example.thatscringeapp.domain.repository.CringifyRepository
import com.example.thatscringeapp.util.Resource
import com.example.thatscringeapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: CringifyRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null
    fun cringe() {
        val prompt = _uiState.value.prompt
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            repo.getCringed(prompt = prompt).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(
                                isLoading = true,
                                visible = false
                            )
                        }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                quote = result.data ?: "",
                                isLoading = false,
                                visible = true
                            )
                        }
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar("Be Ready to be Cringed")
                        )
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                visible = false
                            )
                        }
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                result.message ?: "Unknown error"
                            )
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    fun onPromptChanged(input: String) {
        _uiState.update {
            it.copy(prompt = input)
        }
    }
}