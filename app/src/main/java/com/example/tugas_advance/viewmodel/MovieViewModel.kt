package com.example.tugas_advance.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugas_advance.data.Movies.Movie
import com.example.tugas_advance.network.ApiService
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    var movieListRespone : List<Movie> by mutableStateOf(listOf())
    var errorMessage : String by mutableStateOf("")

    fun getMovieList(){
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val movieList = apiService.getMovies()
                movieListRespone = movieList
            }
            catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }

}