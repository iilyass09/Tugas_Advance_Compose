package com.example.tugas_advance.navigation

sealed class Screen(val route : String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Maps : Screen("maps")
    object Alarm : Screen("alarm")
    object Room : Screen("room")
    object Movie : Screen("movie")
}