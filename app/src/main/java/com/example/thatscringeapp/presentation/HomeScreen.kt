package com.example.thatscringeapp.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    val focusManager = LocalFocusManager.current

    var isInFocus by remember {
        mutableStateOf(false)
    }

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
            OutlinedTextField(
                value = state.prompt,
                onValueChange = viewModel::onPromptChanged,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color(0xFF25AC87)
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus(true)
                    }
                ),
                modifier = Modifier
                    .onFocusChanged { focusState ->
                        isInFocus = focusState.isFocused
                    }

            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                focusManager.clearFocus(true)
                viewModel.cringe()
            }) {
                Text(
                    text = "Get Cringed Goober!!",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = state.quote, fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = Color(0xFFB93E17)
            )
            if (state.isLoading) {
                CircularProgressIndicator()
            }
        }
    }

}