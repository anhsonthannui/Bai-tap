package com.example.baitapvenha2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.baitapvenha2.screens.ConfirmScreen
import com.example.baitapvenha2.screens.CreateNewPasswordScreen
import com.example.baitapvenha2.screens.ForgetPasswordScreen
import com.example.baitapvenha2.screens.VerificationScreen


sealed class Screen(val route: String) {
    object ForgetPassword : Screen("forget_password_screen")
    object Verification : Screen("verification_screen/{email}") {
        fun createRoute(email: String) = "verification_screen/$email"
    }
    object CreatePassword : Screen("create_password_screen/{email}") {
        fun createRoute(email: String) = "create_password_screen/$email"
    }
    object Confirm : Screen("confirm_screen/{email}/{password}") {
        fun createRoute(email: String, password: String) = "confirm_screen/$email/$password"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.ForgetPassword.route
    ) {
        composable(route = Screen.ForgetPassword.route) {
            ForgetPasswordScreen(navController = navController)
        }

        composable(
            route = Screen.Verification.route,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            VerificationScreen(navController = navController, email = email)
        }

        composable(
            route = Screen.CreatePassword.route,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            CreateNewPasswordScreen(navController = navController, email = email)
        }

        composable(
            route = Screen.Confirm.route,
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("password") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""
            ConfirmScreen(navController = navController, email = email, password = password)
        }
    }
}
