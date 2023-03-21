package com.ucne.parcial2.data.repository

import com.ucne.parcial2.data.local.dao.TicketDao
import com.ucne.parcial2.data.local.entity.TicketEntity
import com.ucne.parcial2.data.local.entity.toTicketDto
import com.ucne.parcial2.data.remote.TicketsApi
import com.ucne.parcial2.data.remote.dto.TicketDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Ticket1Repository @Inject constructor(
    private val ticketsApi: TicketsApi,
    private val ticketDao: TicketDao
) {
    suspend fun insert(ticket: TicketEntity) {

        ticketDao.insert(ticket) //insertar en la base de datos

//        ticketsApi.postTickets(ticket.toTicketDto())

        ticketsApi.putTickets(ticket.ticketId!!, ticket.toTicketDto())
    }

    suspend fun delete(ticket: TicketEntity){
        ticketDao.delete(ticket)
        ticketsApi.deleteTickets(ticket.ticketId!!, ticket.toTicketDto())
    }

    suspend fun update(ticket: TicketEntity){
        ticketDao.update(ticket)
    }

    suspend fun find(ticketId:Int) = ticketDao.find(ticketId)

    suspend fun putTickets(id: Int, ticketDto: TicketDto) = ticketsApi.putTickets(id,ticketDto)

    suspend fun postTickets(ticketDto: TicketDto) = ticketsApi.postTickets(ticketDto)

    fun getList(): Flow<List<TicketEntity>> = ticketDao.getList()

}