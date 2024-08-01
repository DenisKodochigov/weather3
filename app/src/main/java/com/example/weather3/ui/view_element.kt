package com.example.weather3.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weather3.entity.TypeKeyboard

@Composable
fun TextFieldWithIcon(
    modifier: Modifier = Modifier,
    textAlign: TextAlign,
    enterValue: MutableState<String>,
    typeKeyboard: TypeKeyboard,
    onClickSearch: (String)->Unit
)
{
    val keyboardController = LocalSoftwareKeyboardController.current
    var focusItem by remember { mutableStateOf(false) }
    enterValue.value = if (!focusItem) enterValue.value else ""


    BasicTextField(
        value = enterValue.value,
        onValueChange = {
            focusItem = false
            enterValue.value = it },
        singleLine = true,
        maxLines = 1,
        modifier = modifier
            .onFocusChanged { focusItem = it.isFocused }
            .background(shape = MaterialTheme.shapes.large, color = colorScheme.surfaceVariant)
            .border(
                width = 0.dp,
                color = colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.large
            ),
        textStyle = MaterialTheme.typography.bodyMedium.copy(textAlign = textAlign),
        decorationBox = {
            Row( verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)){
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier.size(27.dp).clickable { onClickSearch( enterValue.value ) })
                Spacer(modifier = Modifier.width(18.dp))
                it() }
        },
        keyboardOptions = keyBoardOpt(typeKeyboard),
        keyboardActions = KeyboardActions(onDone = {
            onClickSearch( enterValue.value )
            keyboardController?.hide() })
    )
}

@Composable fun keyBoardOpt(typeKeyboard: TypeKeyboard): KeyboardOptions {
    return when (typeKeyboard) {
        TypeKeyboard.TEXT -> {
            KeyboardOptions(keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences).copy(imeAction = ImeAction.Done) }
        TypeKeyboard.DIGIT -> {
            KeyboardOptions(keyboardType = KeyboardType.Decimal).copy(imeAction = ImeAction.Done) }
        else -> { KeyboardOptions.Default.copy(imeAction = ImeAction.Done) }
    }
}