package com.example.tugas_advance.screen.movie

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.tugas_advance.data.Movies.Movie
import com.example.tugas_advance.ui.theme.poppins
import com.example.tugas_advance.viewmodel.MovieViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Movie(navController: NavController) {
    val movieViewModel : MovieViewModel = viewModel()

    Scaffold (
      modifier = Modifier
          .fillMaxSize()
          .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Daftar Film",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            MovieList(movieList = movieViewModel.movieListRespone)
            movieViewModel.getMovieList()
        }
    }
}

@Composable
fun MovieList(movieList: List<Movie>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(items = movieList){index, item ->
            MovieItem(movie = item)
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Surface(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp),
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 10.dp,
        contentColor = Color.White
    ) {
        Surface {
            Row (
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {
                Image(painter = rememberImagePainter(data = movie.imageUrl),
                    contentDescription = movie.desc,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                        .padding(10.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = movie.name,
                        fontSize = 18.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = movie.category,
                        fontSize = 12.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Medium,
                    )
                    Text(
                        text = movie.desc,
                        fontSize = 12.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Normal,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}