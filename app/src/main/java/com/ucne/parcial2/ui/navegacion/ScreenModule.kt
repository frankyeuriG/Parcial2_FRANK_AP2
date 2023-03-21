package com.ucne.parcial2.ui.navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ConfirmationNumber
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.LocalActivity
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenModule(val route: String, val title: String, val icon: ImageVector) {

    object Start : ScreenModule("ui", "Inicio", Icons.TwoTone.Home)

    object Tickets : ScreenModule("tickets", "Registro de Tickets", Icons.TwoTone.LocalActivity)

    object TicketsList :
        ScreenModule("tickets_list", "Lista de Tickets", Icons.TwoTone.ConfirmationNumber)
}