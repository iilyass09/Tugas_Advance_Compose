package com.example.tugas_advance.utils

import com.example.tugas_advance.navigation.Screen

fun String?.ShowBottomBar(): Boolean {
    return this in setOf(
        Screen.Maps.route,
        Screen.Alarm.route,
        Screen.Movie.route,
        Screen.Room.route
    )
}

fun String? .HideTopBar(): Boolean{
    return this !in setOf(
        Screen.Login.route,
        Screen.Register.route,
        Screen.Maps.route,
        Screen.Alarm.route,
        Screen.Movie.route,
        Screen.Room.route
    )
}