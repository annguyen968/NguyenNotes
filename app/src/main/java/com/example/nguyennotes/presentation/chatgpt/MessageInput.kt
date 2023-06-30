package com.example.nguyennotes.presentation.chatgpt

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.nguyennotes.R
import com.example.nguyennotes.ui.theme.Primary

@Composable
fun MessageInput(modifier: Modifier = Modifier, onSubmit: (String) -> Unit) {
    var inputValue by remember { mutableStateOf("") }
    Row(
        modifier = modifier
            .padding(vertical = 73.dp, horizontal = 8.dp)
            .height(48.dp)
            .fillMaxWidth()
    ) {
        TextField(
            modifier = modifier
                .weight(1f)
                .fillMaxWidth(),
            value = inputValue,
            shape = RoundedCornerShape(25.dp),
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            onValueChange = { inputValue = it },
            keyboardActions = KeyboardActions {

            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send)
        )
        FloatingActionButton(
            modifier = modifier
                .fillMaxHeight()
                .padding(horizontal = 8.dp),
            onClick = { onSubmit(inputValue) },
            shape = CircleShape
        ) {
            Icon(Icons.Default.Send, contentDescription = "content description")

        }

    }
}