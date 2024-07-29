package com.example.weather3.entity

import com.example.weather3.R

val weatherCod: HashMap<Int, Int> = hashMapOf(
    0 to R.string.clear_sky,
    1 to R.string.mainly_clear,
    2 to R.string.partly_cloudy,
    3 to R.string.overcast,
    45 to R.string.fog,
    48 to R.string.depositing_rime_fog,
    51 to R.string.drizzle_light,
    53 to R.string.drizzle_moderate,
    55 to R.string.drizzle_dense,
    56 to R.string.freezing_drizzle_light,
    57 to R.string.freezing_drizzle_dense,
    61 to R.string.rain_slight,
    63 to R.string.rain_moderate,
    65 to R.string.rain_heavy,
    66 to R.string.freezing_rain_light,
    67 to R.string.freezing_rain_heavy,
    71 to R.string.snow_fall_slight,
    73 to R.string.snow_fall_moderate,
    75 to R.string.snow_fall_heavy,
    77 to R.string.snow_grains,
    80 to R.string.rain_showers_slight,
    81 to R.string.rain_showers_moderate,
    82 to R.string.rain_showers_violent,
    85 to R.string.snow_showers_slight,
    86 to R.string.snow_showers_heavy,
    95 to R.string.thunderstorm,
    96 to R.string.thunderstorm_with_slight_hail,
    99 to R.string.thunderstorm_with_heavy_hail,
)
val weatherCodIcon: HashMap<Int, Int> = hashMapOf(
    0 to R.drawable.frame_20,
    1 to R.drawable.frame_26,
    2 to R.drawable.frame_26,
    3 to R.drawable.frame_21,
    45 to R.drawable.frame_23,
    48 to R.drawable.frame_23,
    51 to R.drawable.frame_19,
    53 to R.drawable.frame_19,
    55 to R.drawable.frame_19,
    56 to R.drawable.frame_22,
    57 to R.drawable.frame_22,
    61 to R.drawable.frame_24,
    63 to R.drawable.frame_24,
    65 to R.drawable.frame_24,
    66 to R.drawable.frame_22,
    67 to R.drawable.frame_22,
    71 to R.drawable.frame_30,
    73 to R.drawable.frame_30,
    75 to R.drawable.frame_30,
    77 to R.drawable.frame_30,
    80 to R.drawable.frame_27,
    81 to R.drawable.frame_27,
    82 to R.drawable.frame_27,
    85 to R.drawable.frame_27,
    86 to R.drawable.frame_27,
    95 to R.drawable.frame_25,
    96 to R.drawable.frame_25,
    99 to R.drawable.frame_25,
)
