package com.ucne.parcial2.data.remote.dto

data class TicketDto(
    val ticketId: Int = 0,
    val fecha: String = "",
    val empresa: String = "" ,
    val asunto: String = "",
    val especificaciones: String = " ",
    val encargadoId: Int = 0,
    val estatus: String = "",
    val orden: Int = 0
)