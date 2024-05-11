package com.antoniocostadossantos.git_repos_jetpackcompose.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.antoniocostadossantos.git_repos_jetpackcompose.R
import com.antoniocostadossantos.git_repos_jetpackcompose.ui.screens.home.HomeScreen
import com.antoniocostadossantos.git_repos_jetpackcompose.ui.theme.GitreposjetpackcomposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitreposjetpackcomposeTheme(
                darkTheme = true
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(text = "Home") }
                            )
                        },
                        content = { innerPadding ->
                            Navigator(HomeScreen(Modifier.padding(innerPadding)))
                        }
                    )
                }
            }
        }
//        setContent {
//
//            var isDarkTheme by remember { mutableStateOf(true) }
//
//            GitreposjetpackcomposeTheme(
//                darkTheme = isDarkTheme,
//                dynamicColor = true
//            ) {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Scaffold(
//                        topBar = {
//                            TopAppBar(
//                                title = { Text(text = "Home") },
//                                actions = {
//                                    Switch(
//                                        modifier = Modifier
//                                            .padding(horizontal = 8.dp),
//                                        checked = isDarkTheme,
//                                        onCheckedChange = {
//                                            isDarkTheme = !isDarkTheme
//                                        },
//                                        thumbContent = {
//                                            if (isDarkTheme) {
//                                                Icon(
//                                                    painterResource(R.drawable.dark_mode_24dp_fill0_wght400_grad0_opsz24),
//                                                    null
//                                                )
//                                            } else {
//                                                Icon(
//                                                    painterResource(R.drawable.light_mode_24dp_fill0_wght400_grad0_opsz24),
//                                                    null
//                                                )
//                                            }
//                                        }
//                                    )
//                                }
//                            )
//                        }
//                    ) { innerPadding ->
//                        HomeScreen(
//                            modifier = Modifier.padding(innerPadding)
//                        )
//                    }
//                }
//            }
//        }
    }
}