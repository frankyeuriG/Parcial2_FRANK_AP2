package com.ucne.parcial2.ui.ticket

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ucne.parcial2.ui.navegacion.ScreenModule
import java.util.*

@Composable
fun R_TicketsScreen(
    navController: NavController,
    viewModel: TicketApiViewModel = hiltViewModel()
) {
    Column(Modifier.fillMaxWidth()) {
        TicketBody(viewModel = viewModel, navController = navController)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TicketBody(
    viewModel: TicketApiViewModel,
    navController: NavController
) {
    var expanded by remember { mutableStateOf(false) }
    val anio: Int
    val mes: Int
    val dia: Int

    val mCalendar = Calendar.getInstance()
    anio = mCalendar.get(Calendar.YEAR)
    mes = mCalendar.get(Calendar.MONTH)
    dia = mCalendar.get(Calendar.DAY_OF_MONTH)

    val mDatePickerDialog = DatePickerDialog(
        LocalContext.current, { _: DatePicker, anio: Int, mes: Int, dia: Int ->
            viewModel.fecha = "$dia/${mes + 1}/$anio"
        }, anio, mes, dia
    )

    Column(
        Modifier
            .fillMaxWidth()
    ) {

        Scaffold(
            modifier = Modifier.fillMaxWidth(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Registro de Tickets", textAlign = TextAlign.Center,
                            fontSize = 27.sp, style = MaterialTheme.typography.headlineLarge
                        )
                    }
                )
            }
        ) {
            Spacer(modifier = Modifier.padding(30.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it)
                    .wrapContentSize(Alignment.Center)
            ) {

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = viewModel.empresa,
                    singleLine = true,
                    maxLines = 1,
                    label = { Text(text = "Empresa") },
                    onValueChange = { viewModel.empresa = it })

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = viewModel.asunto,
                    singleLine = true,
                    maxLines = 1,
                    label = { Text(text = "Asunto") },
                    onValueChange = { viewModel.asunto = it })


                OutlinedTextField(

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true }
                        .padding(8.dp),
                    value = viewModel.estatus,
                    singleLine = true,
                    maxLines = 1,
                    label = { Text(text = "Estatus") },
                    onValueChange = { viewModel.estatus = it })


                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = viewModel.especificaciones,
                    singleLine = true,
                    maxLines = 1,
                    label = { Text(text = "Especificaciones") },
                    onValueChange = { viewModel.especificaciones = it })

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.BottomCenter)
                ) {
                    ExtendedFloatingActionButton(
                        modifier = Modifier
                            .size(150.dp, 130.dp)
                            .align(Alignment.CenterHorizontally)
                            .wrapContentSize(Alignment.Center),
                        text = {
                            Text(
                                "Guardar",
                                style = MaterialTheme.typography.titleLarge
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Save,
                                contentDescription = "Save",
                                tint = Color.Green,
                            )
                        },
                        onClick = {
                            viewModel.postTickets()
                            navController.navigate(ScreenModule.TicketsList.route)
                        }
                    )
                }
            }
        }
    }
}

