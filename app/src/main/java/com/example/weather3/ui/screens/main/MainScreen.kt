package com.example.weather3.ui.screens.main

import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weather3.R
import com.example.weather3.domain.lg
import com.example.weather3.domain.rus
import com.example.weather3.entity.TypeKeyboard
import com.example.weather3.entity.WeatherDay
import com.example.weather3.entity.WeatherHour
import com.example.weather3.entity.Weathers
import com.example.weather3.entity.weatherCod
import com.example.weather3.entity.weatherCodIcon
import com.example.weather3.service_location.WarningNotInternet
import com.example.weather3.ui.TextFieldWithIcon
import com.example.weather3.ui.theme.shape1
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val viewModel: MainViewModel = hiltViewModel()
    val grantState = rememberPermissionState(ACCESS_FINE_LOCATION)
    if (grantState.status.isGranted && viewModel.availableInternet()) viewModel.getLocation()
    if (!viewModel.availableInternet())
        WarningNotInternet(textId = R.string.text_request_internet, titleId = R.string.title_request_internet)
    MainScreenCreateView(viewModel = viewModel, modifier = modifier)
}

@Composable fun MainScreenCreateView(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val uiState by viewModel.mainScreenState.collectAsState()
    ScreenLayout(uiState = uiState, modifier = modifier)
}

@Composable fun ScreenLayout(uiState: MainScreenState, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize()) {
            SearchCity(uiState)
            WeatherCap(uiState)
            Weather(uiState)
        }
        if (uiState.loadLocation.value || uiState.loadWeather.value){
            val color = if (uiState.loadLocation.value && !uiState.loadWeather.value)
                colorScheme.secondary
            else colorScheme.tertiary

            CircularProgressIndicator(
                modifier = Modifier.width(120.dp).align(alignment = Alignment.Center),
                color = color,
                trackColor = colorScheme.surfaceVariant,
            )
        }
    }
}

@Composable fun SearchCity(uiState: MainScreenState) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(95.dp)
            .fillMaxWidth()
            .background(color = colorScheme.tertiaryContainer)
    ) {
        val city = remember { mutableStateOf("") }
        TextFieldWithIcon(
            textAlign = TextAlign.Start,
            enterValue = city,
            typeKeyboard = TypeKeyboard.TEXT,
            onClickSearch = { city -> uiState.onEnterCity(city) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
        )
    }
}

@Composable fun WeatherCap(uiState: MainScreenState) {
    if (uiState.weathers != null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorScheme.tertiaryContainer)
        ) {
            Location(uiState)
            CurrentWeather(uiState = uiState)
//            SelectMode(uiState = uiState)
        }
    }
}

@Composable fun Location(uiState: MainScreenState) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 8.dp)
    ) {
        Text(text = uiState.location.value.city, style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable fun CurrentWeather(uiState: MainScreenState) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(0.dp))
        Text(
            text = (uiState.weathers?.currentTemp() + "\u00B0"),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = 12.dp),
        )
        val appTemp = (uiState.weathers?.currentApparentTemp() + "\u00B0")
        Text(
            text = stringResource(id = R.string.feels_like) + " " + appTemp,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 4.dp, top = 36.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        IconWeather(uiState.weathers?.currentWeatherCod() ?: 22 ,
            modifier = Modifier
                .size(77.dp)
                .padding(end = 12.dp))
    }
}

@Composable fun Weather(uiState: MainScreenState) {
    WeatherTwoWeek(uiState)
}

@Composable fun WeatherTwoWeek(uiState: MainScreenState){
    uiState.weathers?.let { twoWeek->
        WeatherTwoWeekContent(twoWeek)
    }
}
@Composable fun WeatherTwoWeekContent(weathers: Weathers){
    LazyColumn(
        modifier = Modifier.padding(top = 20.dp),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(0.dp),
        verticalArrangement =  Arrangement.Top
    ) {
        items(count= weathers.weatherDay.count()){ index->
            WeatherTwoWeekContentCard(weathers.weatherDay[index]
            ) { weathers.weatherDay[index].selectDay.value = !weathers.weatherDay[index].selectDay.value }
        }
    }
}
@Composable fun WeatherTwoWeekContentCard(weatherDay: WeatherDay, onSelectDay: ()-> Unit){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onSelectDay() }
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 4.dp)
            .background(color = colorScheme.primaryContainer, shape = shapes.medium)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f),
            content = {WeatherTwoWeekContentCardData(weatherDay) }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(50.dp),
            content = { WeatherTwoWeekContentCardTemperature(weatherDay)}
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.width(75.dp)
        ) {
            Image(
                painter = painterResource(id = weatherCodIcon[ weatherDay.middleCod] ?: 22),
                contentDescription = "",
                modifier = Modifier.size(53.dp),
            )
        }
    }
    if (weatherDay.selectDay.value){
        WeatherToDay(weatherDay)
    }
}
@Composable fun WeatherTwoWeekContentCardData(weatherDay: WeatherDay){
    Column(modifier = Modifier.padding(horizontal = 18.dp, vertical = 18.dp)) {
        val date: String = with(weatherDay){
            if (date == LocalDate.now()) { stringResource(id = R.string.today) }
                                else  stringResource(id = date.dayOfWeek.rus) + ", " +
                                    stringResource(id = date.month.rus) + " " + date.dayOfMonth
        }
        val textWeatherCod = weatherCod[weatherDay.middleCod]?.let { stringResource(id = it) } ?: ""
        Text(text = date, modifier = Modifier.padding(horizontal = 0.dp),
                style = MaterialTheme.typography.titleMedium,)
        Text(text = textWeatherCod,
            modifier = Modifier.padding( top = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
            softWrap = false,
            overflow = TextOverflow.Ellipsis)
    }
}
@Composable fun WeatherTwoWeekContentCardTemperature(weatherDay: WeatherDay){
    Row( modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        val vertPad = 2.dp
        Column(modifier = Modifier
            .padding(end = 8.dp)
            .width(50.dp)){
            Text(text = weatherDay.maxTemp.toString() + "\u00B0",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = vertPad))
            Text(text = weatherDay.minTemp.toString() + "\u00B0",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = vertPad))
            Text(text = weatherDay.middleRainfall.toString() + "%",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = vertPad))
        }
        VerticalDivider( thickness = 1.dp,  modifier = Modifier.height(80.dp)) //color = Color.White,
    }
}

@Composable fun IconWeather(weatherCod: Int, modifier: Modifier = Modifier){
    val idIcon: Int = weatherCodIcon[weatherCod] ?: R.drawable.frame_20
    Image(
        painter = painterResource(id = idIcon),
        contentDescription = "",
        modifier = modifier
    )
}

@Composable fun WeatherToDay(weatherDay: WeatherDay){
    val offsetVertText = 86.dp
    val offsetHotText = 6.dp
    val horizontalDp = 20.dp
    Box (
        modifier = Modifier.padding(start = horizontalDp, end = horizontalDp),)
    {
        LazyRow(
            modifier = Modifier
                .background(color = colorScheme.secondaryContainer, shape = shape1)
                .padding(horizontal = 8.dp),
            state = rememberLazyListState(),
            contentPadding = PaddingValues(0.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            items(count= weatherDay.weatherHour.count()){ hour->
                WeatherDayTemperatureContent( weatherDay.weatherHour[hour] )
            }
        }
        Text(text = stringResource(id = R.string.title_temp), style  = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(start = offsetHotText, top = offsetVertText, bottom = 8.dp))
        Text(text = stringResource(id = R.string.title_precipitation),
            style  = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(start = offsetHotText, top = offsetVertText + 44.dp, bottom = 8.dp),)
    }
}
@Composable fun WeatherDayTemperatureContent(weatherHour: WeatherHour){
    Column(  horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.width(70.dp))
    {
        val now = weatherHour.time.toString() == ( LocalTime.now().hour.toString() + ":00")
        Text(
            text = if (now) stringResource(id = R.string.now) else weatherHour.time.toString(),
            softWrap = false, style  = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        IconWeather(weatherHour.weatherCode, Modifier.size(32.dp))
        Text(
            text = (weatherHour.temperature.toInt().toString() + "\u00B0"),
            style  = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 24.dp, bottom = 0.dp),
        )
        Text(
            text = ("${weatherHour.precipitation}%"),
            style  = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 20.dp, bottom = 16.dp),
        )
    }
}
