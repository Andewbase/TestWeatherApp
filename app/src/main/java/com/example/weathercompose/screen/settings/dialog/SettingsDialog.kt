package com.example.weathercompose.screen.settings.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weathercompose.R
import com.example.weathercompose.ui.theme.WeatherComposeTheme

@Composable
fun SettingsDialog(
    modifier: Modifier = Modifier
){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

       /* SettingsDialogTextField(
            modifier = modifier
        )*/

        val typeCity = listOf("Маленький", "Средний", "Большой")
        val (selectedOption, onOptionSelected) = remember {
            mutableStateOf(typeCity[0])
        }

        Text(text = stringResource(id = R.string.type_city))

        Column {
            Text(text = selectedOption)
            Column(Modifier.selectableGroup()) {
                typeCity.forEach{ text ->
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                        RadioButton(
                            selected = text == selectedOption,
                            onClick = { onOptionSelected(text) }
                        )
                        Text(text = text)
                    }
                }
            }
        }


    }

}


@Composable
fun SettingsDialogTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
){

    TextField(
        value = value,
        onValueChange = onValueChanged,
        maxLines = 1,
        placeholder = {
            Text(text = stringResource(id = R.string.add_name_city))
        }
    )
}


@Preview(showBackground = true)
@Composable
fun SettingsDialogPreview() {
    WeatherComposeTheme {
       SettingsDialog()
    }
}