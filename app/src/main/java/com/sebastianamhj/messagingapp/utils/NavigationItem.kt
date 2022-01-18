package com.sebastianamhj.messagingapp.utils

import com.sebastianamhj.messagingapp.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Profile : NavigationItem("profile", R.drawable.profile_unchecked, "Profile")
    object Chat : NavigationItem("chat", R.drawable.chat_unchecked, "Chat")
    object Search : NavigationItem("search", R.drawable.search_checked, "Search")
    object More : NavigationItem("more", R.drawable.more_checked, "More")
}