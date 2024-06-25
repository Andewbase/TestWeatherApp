package com.example.weathercompose.screen.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.weathercompose.domain.City
import com.example.weathercompose.ui.theme.WeatherComposeTheme

@Composable
fun SettingsScreen(
    allCity: List<City>,
    navController: NavController,
    modifier: Modifier = Modifier
){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(top = 40.dp)
                .verticalScroll(rememberScrollState())
        ) {

            if (allCity.isEmpty()){
                Text(text = "Список Пуст")
            }else{
                LazyColumnExample(
                    allCity = allCity,
                    onClick = { /*TODO*/ }
                )
            }

            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Добавить город")
            }

        }

    }




}

@Composable
private fun LazyColumnExample(
    allCity: List<City>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){

    LazyColumn(
        modifier = modifier.fillMaxHeight(0.9f)
    ) {
        items(
            items = allCity,
            key = {city -> city.id}
        ){ city ->
            Item(
                city = city,
                onClick = { onClick() }
            )
        }
    }

}

@Composable
private fun Item(
    city: City,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        border = BorderStroke(width = 1.dp, color = Color.Green),
        colors = CardDefaults.cardColors(containerColor = Color.Blue),
        shape = RoundedCornerShape(size = 15.dp),
        modifier = modifier
            .fillMaxSize(0.8f)
            .padding(10.dp)
            .clickable {
                onClick()
            }
    ) {

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            Row(
                modifier = modifier
                    .padding(start = 10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = city.name,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic
                )
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = city.type,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    WeatherComposeTheme {
        SettingsScreen(
            allCity = emptyList(),
            navController = rememberNavController()
        )
    }
}