package com.ucne.parcial2.ui.navegacion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirlineSeatFlat
import androidx.compose.material.icons.filled.AirplaneTicket
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.SportsMartialArts
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerMenu(
    navController: NavController
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val ic = Icons.TwoTone.Favorite

    val items = listOf(ScreenModule.Start, ScreenModule.TicketsList)
    val selectedItem = remember { mutableStateOf(items[0]) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(item.title) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                            navController.navigate(item.route)
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Spacer(Modifier.height(12.dp))
        TopAppBar(
            modifier = Modifier
                .background(Color.Green)
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .clip(shape = RoundedCornerShape(20.dp)),
            title = {
                Box(
                    modifier = Modifier,
                    Alignment.Center
                ) {
                    Text(text = "Inicio")
                }
            },
            navigationIcon = {
                IconButton(onClick = { scope.launch { drawerState.open() } }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Icon"
                    )
                }
            },
            actions = {
                IconButton(onClick = {}) {}
                IconButton(onClick = {}) {}
            }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.AirplaneTicket,
                contentDescription = null,
                tint = Color.Green,
                modifier = Modifier
                    .size(300.dp, 300.dp)
                    .padding(4.dp),
            )
        }
    }
}