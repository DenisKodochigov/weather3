package com.example.weather3.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.weather3.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)


//val inter = TextStyle(fontFamily = FontFamily(Font(googleFont = GoogleFont("Inter"), fontProvider = provider,)))
//val fieldStyle = inter.copy(fontSize = 18.sp, color = onSurfaceVariantLight)
//val locationStyle = inter.copy(fontSize = 22.sp, color = onPrimaryLight)
//val temperatureStyle1 = inter.copy(fontSize = 57.sp, color = onPrimaryLight)
//val feelsStyle = inter.copy(fontSize = 16.sp, color = onPrimaryLight)
//val weatherStyle = inter.copy(fontSize = 16.sp, color = onPrimaryLight, fontWeight = FontWeight(500))
//val buttonStyle = inter.copy(fontSize = 16.sp, color = onPrimaryLight, fontWeight = FontWeight(500))
//val titleStyle = inter.copy(fontSize = 12.sp, color = onPrimaryLight)
//val listTimeStyle = inter.copy(fontSize = 14.sp, color = onPrimaryLight)
//val listTimeStyle1 = inter.copy(fontSize = 15.sp, color = onPrimaryLight)
//val listTempStyle = inter.copy(fontSize = 18.sp, color = onPrimaryLight)
//val dialogButtonStyle = inter.copy(fontSize = 24.sp, color = onPrimaryDark)
//val dialogButtonStyleLight = inter.copy(fontSize = 20.sp, color = onPrimaryLight)



val bodyFontFamily = FontFamily(
    Font(googleFont = GoogleFont("Inter"), fontProvider = provider,))

val displayFontFamily = FontFamily(
    Font(googleFont = GoogleFont("Inter"), fontProvider = provider,))

// Default Material 3 typography values
val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
)

// Set of Material typography styles to start with
val Typography1 = Typography(

    displayLarge = TextStyle(fontFamily = bodyFontFamily,),
    displayMedium = TextStyle(fontFamily = bodyFontFamily,),
    displaySmall = TextStyle(fontFamily = bodyFontFamily,),

    headlineLarge = TextStyle(fontFamily = bodyFontFamily,),
    headlineMedium = TextStyle(fontFamily = bodyFontFamily,),
    headlineSmall = TextStyle(fontFamily = bodyFontFamily,),

    titleLarge = TextStyle(fontFamily = bodyFontFamily,),
    titleMedium = TextStyle(fontFamily = bodyFontFamily,),
    titleSmall = TextStyle(fontFamily = bodyFontFamily,),

    bodyLarge = TextStyle(fontFamily = bodyFontFamily,),
    bodyMedium = TextStyle(fontFamily = bodyFontFamily,),
    bodySmall = TextStyle(fontFamily = bodyFontFamily,),

    labelLarge = TextStyle(fontFamily = bodyFontFamily,),
    labelMedium = TextStyle(fontFamily = bodyFontFamily,),
    labelSmall = TextStyle(fontFamily = bodyFontFamily,),
)