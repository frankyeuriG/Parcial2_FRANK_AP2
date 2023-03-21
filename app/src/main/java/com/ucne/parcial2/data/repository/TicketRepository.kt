package com.ucne.parcial2.data.repository

import com.ucne.parcial2.data.remote.dto.TicketDto
import com.ucne.parcial2.util.Resource
import kotlinx.coroutines.flow.Flow

interface TicketRepository {

    fun getTickets(): Flow<Resource<List<TicketDto>>>

    fun getTicketsId(id: Int): Flow<Resource<TicketDto>>

    suspend fun putTickets(id: Int, ticketDto: TicketDto)

    suspend fun deleteTickets(id: Int, ticketDto: TicketDto)

    suspend fun postTickets(ticketDto: TicketDto)
}