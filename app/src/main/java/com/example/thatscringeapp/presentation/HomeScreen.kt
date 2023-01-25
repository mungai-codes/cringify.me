package com.example.thatscringeapp.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thatscringeapp.util.UiEvent
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: MainViewModel = hiltViewModel()
) {

    val state = viewModel.uiState.collectAsState().value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
//            OutlinedTextField(
//                value = state.prompt,
//                onValueChange = viewModel::onPromptChanged,
//                shape = RoundedCornerShape(16.dp),
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    unfocusedBorderColor = Color.LightGray,
//                    focusedBorderColor = Color(0xFF25AC87)
//                ),
//                trailingIcon = {
//                    IconButton(onClick = { viewModel.getCringed() }) {
//                        Icon(
//                            imageVector = Icons.Rounded.Search,
//                            contentDescription = "Search"
//                        )
//                    }
//                }
//            )
            Button(onClick = { viewModel.cringe() }) {
                Text(text = "Get Cringed Goober!!")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = state.quote)
            if (state.isLoading) {
                CircularProgressIndicator()
            }
        }
    }

}