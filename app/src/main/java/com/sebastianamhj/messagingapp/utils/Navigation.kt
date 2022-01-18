package com.sebastianamhj.messagingapp.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sebastianamhj.messagingapp.ui.main.ChatView
import com.sebastianamhj.messagingapp.ui.main.MoreView
import com.sebastianamhj.messagingapp.ui.main.ProfileView
import com.sebastianamhj.messagingapp.ui.main.SearchView

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationItem.Chat.route) {
        composable(NavigationItem.Profile.route) {
            ProfileView()
        }
        composable(NavigationItem.Chat.route) {
            ChatView()
        }
        composable(NavigationItem.Search.route) {
            SearchView()
        }
        composable(NavigationItem.More.route) {
            MoreView()
        }
    }
}