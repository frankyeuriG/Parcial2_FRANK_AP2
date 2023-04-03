package com.ucne.parcial2.ui.navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material.icons.twotone.ConfirmationNumber
import androidx.compose.material.icons.twotone.LocalActivity
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenModule(val route: String, val title: String, val icon: ImageVector) {

    object Tickets : ScreenModule("tickets", "Registro de Tickets", Icons.TwoTone.LocalActivity)

    object TicketsList :
        ScreenModule("tickets_list", "Lista de Tickets", Icons.TwoTone.ConfirmationNumber)

    object R_ticktes : ScreenModule("Registro_Tickets", "Registro de Ticktes", Icons.TwoTone.Add)
}