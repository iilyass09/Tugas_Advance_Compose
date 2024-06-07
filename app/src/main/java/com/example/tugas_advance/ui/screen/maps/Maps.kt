package com.example.tugas_advance.screen.maps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tugas_advance.R
import com.example.tugas_advance.data.Datastore.UserDataStore
import com.example.tugas_advance.data.SharedPreferences.SharedPreferencesManager
import com.example.tugas_advance.navigation.Screen
import com.example.tugas_advance.ui.theme.poppins
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Maps(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }
    val name = sharedPreferencesManager.name ?: ""
    val userDataStore = UserDataStore(context)
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            TopAppBar(
                title = {
                    Text(
                        text = name,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Normal,
                        fontFamily = poppins
                    )
                },
                navigationIcon = {
                    Box {
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(7.dp)
                                .size(30.dp)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            sharedPreferencesManager.clear()
                            coroutineScope.launch {
                                userDataStore.clearStatus()
                            }
                            navController.navigate(Screen.Login.route) {
                                popUpTo(Screen.Maps.route) {
                                    inclusive = true
                                }
                            }
                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.keluar),
                            contentDescription = "keluar",
                            modifier = Modifier
                                .padding(end = 15.dp)
                                .size(23.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFF495D91)
                ),
                modifier = Modifier.shadow(4.dp)
            )
            MapScreen()
        }
    }
}


@Composable
fun MapScreen() {
    val atasehir = LatLng(-6.915712233400342, 107.6191202302451)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(atasehir, 15f)
    }

    val uiSettings by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
    }
    val properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings
    ) {
        Marker(
            state = MarkerState(position = atasehir),
            title = "Marker 1"
        )
    }
}


