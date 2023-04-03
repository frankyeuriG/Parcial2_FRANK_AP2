package com.ucne.parcial2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ucne.parcial2.ui.navegacion.ScreenModule
import com.ucne.parcial2.ui.theme.Parcial2Theme
import com.ucne.parcial2.ui.ticket.R_TicketsScreen
import com.ucne.parcial2.ui.ticket.TicketScreen
import com.ucne.parcial2.ui.ticket.TicketsListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Parcial2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenModule.TicketsList.route
                    ) {
                        composable(route = ScreenModule.TicketsList.route) {
                            TicketsListScreen(navController = navController) { id ->
                                navController.navigate(ScreenModule.Tickets.route + "/${id}")
                            }
                        }
                        composable(ScreenModule.R_ticktes.route){
                            R_TicketsScreen(navController = navController)
                        }
                        composable(
                            route = ScreenModule.Tickets.route + "/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { capturar ->
                            val ticketId = capturar.arguments?.getInt("id") ?: 0

                            TicketScreen(ticketId = ticketId, navController = navController) {
                                navController.navigate(ScreenModule.TicketsList.route)
                            }
                        }
                    }
                }
            }
        }
    }
}

