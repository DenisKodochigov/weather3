package com.example.weather3.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weather3.R

val fontFam = FontFamily(Font(R.font.mtssans_regular))
// Set of Material typography styles to start with
val Typography = Typography(

    displayLarge = TextStyle(fontFamily = fontFam,),
    displayMedium = TextStyle(fontFamily = fontFam,),
    displaySmall = TextStyle(fontFamily = fontFam,),

    headlineLarge = TextStyle(fontFamily = fontFam,),
    headlineMedium = TextStyle(fontFamily = fontFam,),
    headlineSmall = TextStyle(fontFamily = fontFam,),

    titleLarge = TextStyle(fontFamily = fontFam,),
    titleMedium = TextStyle(fontFamily = fontFam,),
    titleSmall = TextStyle(fontFamily = fontFam,),

    bodyLarge = TextStyle(fontFamily = fontFam,),
    bodyMedium = TextStyle(fontFamily = fontFam,),
    bodySmall = TextStyle(fontFamily = fontFam,),

    labelLarge = TextStyle(fontFamily = fontFam,),
    labelMedium = TextStyle(fontFamily = fontFam,),
    labelSmall = TextStyle(fontFamily = fontFam,),
)
val mts = TextStyle(fontFamily = FontFamily(Font(R.font.mtssans_regular)))
val fieldStyle = mts.copy(fontSize = 18.sp, color = onSurfaceVariantLight)
val locationStyle = mts.copy(fontSize = 22.sp, color = onPrimaryLight)
val temperatureStyle1 = mts.copy(fontSize = 57.sp, color = onPrimaryLight)
val feelsStyle = mts.copy(fontSize = 16.sp, color = onPrimaryLight)
val weatherStyle = mts.copy(fontSize = 16.sp, color = onPrimaryLight, fontWeight = FontWeight(500))
val buttonStyle = mts.copy(fontSize = 16.sp, color = onPrimaryLight, fontWeight = FontWeight(500))
val titleStyle = mts.copy(fontSize = 12.sp, color = onPrimaryLight)
val listTimeStyle = mts.copy(fontSize = 14.sp, color = onPrimaryLight)
val listTimeStyle1 = mts.copy(fontSize = 15.sp, color = onPrimaryLight)
val listTempStyle = mts.copy(fontSize = 18.sp, color = onPrimaryLight)
val dialogButtonStyle = mts.copy(fontSize = 24.sp, color = onPrimaryDark)
val dialogButtonStyleLight = mts.copy(fontSize = 20.sp, color = onPrimaryLight)
