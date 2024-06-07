package com.example.tugas_advance.screen.Room

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tugas_advance.data.Room.entity.UserEntity
import com.example.tugas_advance.ui.theme.poppins

@Composable
fun UserList(
    modifier: Modifier,
    userList : List<UserEntity>
) {
    LazyColumn(modifier = Modifier) {
        items(items = userList){ user ->
            UserCard(modifier = modifier.padding(all = 8.dp),
                anggota = user
            )
        }
    }
}

@Composable
fun UserCard(
    modifier: Modifier,
    elevation: CardElevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    anggota : UserEntity
) {
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 10.dp,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .height(85.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = anggota.nama,
                fontSize = 20.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Medium
            )
            Text(
                modifier = Modifier.offset(y = (-5).dp),
                text = anggota.role,
                fontSize = 15.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Normal
            )
        }
    }
}