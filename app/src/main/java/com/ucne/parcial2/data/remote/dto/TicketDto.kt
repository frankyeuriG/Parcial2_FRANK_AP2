package com.ucne.parcial2.data.remote.dto

data class TicketDto(
    val ticketId: Int = 0,
    val asunto: String = "",
    val empresa: String = "" ,
    val encargadoId: Int = 0,
    val especificaciones: String = " ",
    val estatus: String = "",
    val fecha: String = "",
    val orden: Int = 0
)