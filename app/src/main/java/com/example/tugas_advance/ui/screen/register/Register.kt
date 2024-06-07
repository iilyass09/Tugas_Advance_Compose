package com.example.tugas_advance.screen.register

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tugas_advance.R
import com.example.tugas_advance.viewmodel.FirebaseViewModel
import com.example.tugas_advance.navigation.Screen
import com.example.tugas_advance.screen.login.NameTextField
import com.example.tugas_advance.screen.login.PasswordTextField
import com.example.tugas_advance.ui.theme.Biru
import com.example.tugas_advance.ui.theme.poppins
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Register(
    navController: NavController,
    viewModel: FirebaseViewModel = hiltViewModel()
) {
    var textUsername by remember { mutableStateOf("") }
    var textEmail by remember { mutableStateOf("") }
    var textPassword by remember { mutableStateOf("") }
    var textConfirmPassword by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val state by viewModel.state.collectAsState(initial = null)

    val icon = if (passwordVisibility)
        painterResource(id = R.drawable.password_visibility)
    else
        painterResource(id = R.drawable.password_visibility_off)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(28.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Daftar",
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
                contentDescription = null,
                modifier = Modifier
                    .size(175.dp)
                    .align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.height(30.dp))

            // TEXTFIELD USERNAME
            NameTextField(
                value = textUsername,
                onValueChange = { textUsername = it },
                label = "Buat nama pengguna",
                imageVector = Icons.Outlined.Person,
                contentDescription = "Icon Person"
            )
            Spacer(modifier = Modifier.height(10.dp))

            // TEXTFIELD EMAIL
            NameTextField(
                value = textEmail,
                onValueChange = { textEmail = it },
                label = "Buat alamat email",
                imageVector = Icons.Outlined.Email,
                contentDescription = "Icon Email"
            )
            Spacer(modifier = Modifier.height(10.dp))

            // TEXTFIELD PASSWORD
            PasswordTextField(
                text = textPassword,
                onValueChange = { textPassword = it },
                label = "Buat kata sandi"
            )
            Spacer(modifier = Modifier.height(10.dp))

            // TEXTFIELD KONFIRMASI PASSWORD
            PasswordTextField(
                text = textConfirmPassword,
                onValueChange = { textConfirmPassword = it },
                label = "Konfirmasi kata sandi"
            )
            Spacer(modifier = Modifier.height(30.dp))

            // BUTTON REGISTER
            Button(
                onClick = {
                    coroutineScope.launch {
                        when {
                            textUsername.isBlank() || textEmail.isBlank() || textPassword.isBlank() || textConfirmPassword.isBlank() -> {
                                Toast.makeText(context, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                            }
                            textPassword.length < 7 -> {
                                Toast.makeText(context, "Password minimal 7 huruf", Toast.LENGTH_SHORT).show()
                            }
                            textPassword != textConfirmPassword -> {
                                Toast.makeText(context, "Password dan Konfirmasi Password tidak cocok", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                viewModel.registerUser(textEmail, textPassword) { isSuccess ->
                                    if (isSuccess) {
                                        navController.navigate(Screen.Login.route)
                                        Toast.makeText(context, "Akun Berhasil Dibuat", Toast.LENGTH_SHORT).show()
                                    } else {
                                        Toast.makeText(context, "Gagal Membuat Akun", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                    }
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(55.dp),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(55.dp)
                        .background(
                            brush = Brush.horizontalGradient(listOf(Biru, Biru))
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Daftar",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppins,
                        color = Color.White,
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            // TEKS LOGIN
            ClickableLoginTextComponent(
                onTextSelected = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }
    }
}

@Composable
fun ClickableLoginTextComponent(onTextSelected: (String) -> Unit) {
    val initialText = "Sudah Punya Akun?  "
    val loginText = "Masuk"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Biru)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 20.dp),
        style = androidx.compose.ui.text.TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            fontFamily = poppins
        ),
        text = annotatedString, onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")
                    if (span.item == loginText) {
                        onTextSelected(span.item)
                    }
                }

        }
    )
}
