    package com.example.tugas_advance

    import androidx.compose.animation.AnimatedVisibility
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.offset
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.material3.BottomAppBar
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.Icon
    import androidx.compose.material3.IconButton
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Text
    import androidx.compose.material3.TopAppBar
    import androidx.compose.material3.TopAppBarDefaults
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.res.stringResource
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.navigation.NavController
    import androidx.navigation.NavGraph.Companion.findStartDestination
    import androidx.navigation.NavHostController
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.currentBackStackEntryAsState
    import androidx.navigation.compose.rememberNavController
    import com.example.tugas_advance.screen.Room.Room
    import com.example.tugas_advance.screen.alarm.AlarmScreen
    import com.example.tugas_advance.navigation.NavigationItem
    import com.example.tugas_advance.navigation.Screen
    import com.example.tugas_advance.screen.maps.Maps
    import com.example.tugas_advance.screen.login.Login
    import com.example.tugas_advance.screen.movie.Movie
    import com.example.tugas_advance.screen.register.Register
    import com.example.tugas_advance.screen.splash.SplashScreen
    import com.example.tugas_advance.ui.theme.poppins
    import com.example.tugas_advance.utils.HideTopBar
    import com.example.tugas_advance.utils.ShowBottomBar

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TugasAdvance(
        modifier: Modifier = Modifier,
        navController: NavHostController = rememberNavController(),
    ) {
        Scaffold(
            topBar = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                AnimatedVisibility(visible = currentRoute?.HideTopBar() == true) {
                    TopAppBar(
                        colors = TopAppBarDefaults.largeTopAppBarColors(Color.White),
                        title = {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {

                                }
                            }
                        }
                    )
                }
            },
            bottomBar = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                AnimatedVisibility(visible = currentRoute.ShowBottomBar()) {
                    BottomBar(navController)
                }
            },
            modifier = modifier
        ) { contentPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Splash.route,
                modifier = Modifier.padding(contentPadding)
            ) {
                composable(Screen.Splash.route){ SplashScreen(navController) }
                composable(Screen.Room.route){ Room(navController) }
                composable(Screen.Login.route){ Login(navController) }
                composable(Screen.Register.route){ Register(navController) }
                composable(Screen.Maps.route){ Maps(navController) }
                composable(Screen.Alarm.route){ AlarmScreen(navController) }
                composable(Screen.Movie.route){ Movie(navController) } }
        }
    }

    @Composable
    fun BottomBar(
        navController: NavController,
        modifier: Modifier = Modifier
    ) {
        BottomAppBar(
            modifier = modifier,
            containerColor = Color.White,
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            val navigationItems = listOf(
                NavigationItem(
                    title = stringResource(id = R.string.menu_maps),
                    iconClick = R.drawable.maps,
                    iconUnclick = R.drawable.maps,
                    screen = Screen.Maps
                ),
                NavigationItem(
                    title = stringResource(id = R.string.menu_alarm),
                    iconClick = R.drawable.alarm,
                    iconUnclick = R.drawable.alarm,
                    screen = Screen.Alarm
                ),
                NavigationItem(
                    title = stringResource(id = R.string.menu_movie),
                    iconClick = R.drawable.movie,
                    iconUnclick = R.drawable.movie,
                    screen = Screen.Movie
                ),
                NavigationItem(
                    title = stringResource(id = R.string.menu_anggota),
                    iconClick = R.drawable.anggota,
                    iconUnclick = R.drawable.anggota,
                    screen = Screen.Room
                ),
            )

            navigationItems.forEach { item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate(item.screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        },
                    ) {
                        val icon = if (currentRoute == item.screen.route) item.iconClick else item.iconUnclick
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = item.title,
                            tint = if (currentRoute == item.screen.route) Color.Black else Color.LightGray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(
                        modifier = Modifier.offset(y = (-5).dp),
                        text = item.title,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Medium,
                        fontSize = 11.sp,
                        color = if (currentRoute == item.screen.route) Color.Black else Color.LightGray
                    )
                }
            }
        }
    }