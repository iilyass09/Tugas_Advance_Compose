package com.example.tugas_advance.screen.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.tugas_advance.R
import com.example.tugas_advance.data.SharedPreferences.SharedPreferencesManager
import com.example.tugas_advance.data.Datastore.UserDataStore
import com.example.tugas_advance.viewmodel.FirebaseViewModel
import com.example.tugas_advance.navigation.Screen
import com.example.tugas_advance.ui.theme.Biru
import com.example.tugas_advance.ui.theme.poppins
import kotlinx.coroutines.launch

@Composable
fun Login(
    navController: NavController,
    viewModel: FirebaseViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val userDataStore = UserDataStore(context)
    var textEmail by remember { mutableStateOf("") }
    var textPassword by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    var isLoggingIn by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Masuk",
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(25.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            fontFamily = poppins
        )
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .fillMaxWidth()
                .size(175.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))

        // EMAIL TEXT FIELD
        NameTextField(
            value = textEmail,
            onValueChange = { textEmail = it },
            label = "Email",
            imageVector = Icons.Outlined.Person,
            contentDescription = "Icon Email"
        )
        Spacer(modifier = Modifier.height(10.dp))

        // PASSWORD TEXT FIELD
        PasswordTextField(
            text = textPassword,
            onValueChange = { textPassword = it },
            label = "Password"
        )
        Spacer(modifier = Modifier.height(30.dp))

        // LOGIN BUTTON
        Button(
            onClick = {
                if (textEmail.isNotBlank() && textPassword.isNotBlank() && !isLoggingIn) {
                    isLoggingIn = true
                    viewModel.loginUser(textEmail, textPassword) { isSuccess ->
                        if (isSuccess) {
                            sharedPreferencesManager.name = textEmail
                            sharedPreferencesManager.password = textPassword
                            coroutineScope.launch {
                                userDataStore.saveStatus(true)
                            }
                            navController.navigate(Screen.Maps.route) {
                                popUpTo(Screen.Login.route) {
                                    inclusive = true
                                }
                            }
                        } else {
                            Toast.makeText(context, "Email atau Password salah", Toast.LENGTH_SHORT).show()
                            isLoggingIn = false
                        }
                    }
                } else {
                    Toast.makeText(context, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(55.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Biru),
            shape = MaterialTheme.shapes.small
        ) {
            if (!isLoggingIn) {
                Text(
                    text = "Masuk",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontFamily = poppins
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (isLoggingIn) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie))
            LottieAnimation(
                composition = composition,
                modifier = Modifier.size(300.dp),
                iterations = LottieConstants.IterateForever
            )
        }

        Spacer(modifier = Modifier.height(5.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Belum punya akun?",
                fontSize = 15.sp,
                fontFamily = poppins
            )
            TextButton(onClick = {
                navController.navigate(Screen.Register.route)
            }) {
                Text(
                    text = "Daftar",
                    fontSize = 15.sp,
                    fontFamily = poppins,
                    color = Biru
                )
            }
        }
    }
}


