package com.sebastianamhj.messagingapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sebastianamhj.messagingapp.ui.theme.AppTheme
import com.sebastianamhj.messagingapp.utils.Navigation
import com.sebastianamhj.messagingapp.utils.NavigationItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppTheme() {
                // A surface container using the 'background' color from the theme
                Scaffold(bottomBar = { BottomNavigationBar(navController = navController)}) {
                    Navigation(navController)
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Profile,
        NavigationItem.Chat,
        NavigationItem.Search,
        NavigationItem.More
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = item.title)},
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White,
                alwaysShowLabel = false,
                selected = false,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}