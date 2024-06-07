package com.example.tugas_advance.screen.Room

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tugas_advance.ui.theme.poppins

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Room(
    navController: NavController,
) {
    val viewModel: RoomViewModel = hiltViewModel()
    val uiState = viewModel.userUiState
    val userList by viewModel.userList.collectAsState(initial = emptyList())

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Grup Jendral Soedirman",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.nama,
                onValueChange = { viewModel.updateUserUiState(uiState.copy(nama = it)) },
                label = { Text(text = "Nama") }
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.role,
                onValueChange = { viewModel.updateUserUiState(uiState.copy(role = it)) },
                label = { Text(text = "Role") }
            )
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = viewModel::insertUser,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = MaterialTheme.shapes.small,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF495D91)
                ),
            ) {
                Text(
                    text = "Simpan",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Anggota Grup",
                fontFamily = poppins
            )
            UserList(modifier = Modifier, userList = userList)
        }
    }
}