package com.ucne.parcial2.ui.ticket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.parcial2.data.remote.dto.TicketDto
import com.ucne.parcial2.data.repository.TicketRepositoryImp
import com.ucne.parcial2.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class TicketsListState(
    val isLoading: Boolean = false,
    val tickets: List<TicketDto> = emptyList(),
    val error: String = ""
)

data class TicketsState(
    val isLoading: Boolean = false,
    val ticket: TicketDto? = null,
    val error: String = ""
)

@HiltViewModel
class TicketApiViewModel @Inject constructor(
    private val ticketRepository: TicketRepositoryImp
) : ViewModel() {
    var ticketId by mutableStateOf(1)
    var empresa by mutableStateOf("")
    var asunto by mutableStateOf("")
    var especificaciones by mutableStateOf("")
    var estatus by mutableStateOf("")
    var fecha by mutableStateOf("2023-04-01T19:23:59.770Z")
    var orden by mutableStateOf(1)
    var encargadoId by mutableStateOf(1)

    var uiState = MutableStateFlow(TicketsListState())
        private set
    var uiStateTicket = MutableStateFlow(TicketsState())
        private set

    init {
        ticketRepository.getTickets().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(tickets = result.data ?: emptyList())
                    }
                }
                is Resource.Error -> {
                    uiState.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun TicketbyId(id: Int) {
        ticketId = id
        Limpiar()
        ticketRepository.getTicketsId(ticketId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateTicket.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateTicket.update {
                        it.copy(ticket = result.data)
                    }
                    empresa = uiStateTicket.value.ticket!!.empresa
                    asunto = uiStateTicket.value.ticket!!.asunto
                    estatus = uiStateTicket.value.ticket!!.estatus
                    fecha = uiStateTicket.value.ticket!!.fecha
                    especificaciones = uiStateTicket.value.ticket!!.especificaciones
                }
                is Resource.Error -> {
                    uiStateTicket.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun putTicket() {
        viewModelScope.launch {
            ticketRepository.putTickets(
                ticketId, TicketDto(
                    ticketId = ticketId,
                    uiStateTicket.value.ticket!!.fecha,
                    empresa,
                    asunto,
                    especificaciones,
                    uiStateTicket.value.ticket!!.encargadoId,
                    estatus,
                    uiStateTicket.value.ticket!!.orden
                )
            )
        }
    }

    fun postTickets() {
        viewModelScope.launch {
            if (encargadoId == null) {
                encargadoId += 1
            }
            if (orden == null) {
                orden += 1
            }
            ticketRepository.postTickets(
                TicketDto(
                    asunto = asunto,
                    empresa = empresa,
                    encargadoId = encargadoId,
                    especificaciones = especificaciones,
                    estatus = estatus,
                    fecha = "2023-04-01T19:23:59.770Z",
                    orden = orden
                )
            )
        }
    }

    private fun Limpiar() {
        asunto = ""
        empresa = ""
        especificaciones = ""
        estatus = ""
        fecha = ""
    }
}