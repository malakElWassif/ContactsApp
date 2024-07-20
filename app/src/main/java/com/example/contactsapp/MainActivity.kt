package com.example.contactsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactsapp.data.constants.DataSource
import com.example.contactsapp.model.Contact
import com.example.contactsapp.ui.theme.ContactsAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactsAppTheme {
             integrate()

            }
        }
    }
}


@Composable
fun createContact(contact: Contact, modifier: Modifier = Modifier) {

    val context = LocalContext.current

    Column(

        modifier = modifier

            .clickable {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:"+contact.number)
                }
                context.startActivity(intent)
            }) {
        Image(
            painter = painterResource(id = contact.image),
            contentDescription = "",
            modifier.align(Alignment.CenterHorizontally)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .background(color = Color.LightGray)
                .width(170.dp)


        ) {
            Text(
                text = stringResource(id = contact.name),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = Color.Black
            )
            SelectionContainer {


                Text(
                    text = contact.number,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray
                )
            }
        }

    }
}

@Composable
fun contactList(contacts: List<Contact>, modifier: Modifier = Modifier) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3), modifier.height(700.dp),

        ) {
        items(contacts) {
            createContact(contact = it)
        }
    }



}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun integrate(modifier: Modifier=Modifier){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(
                        "Contacts App",
                        maxLines = 1,
                        textAlign = TextAlign.Left
                    )
                },

                actions = {
                    IconButton(onClick = { val intent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:"+20111111111111)
                    }
                        context.startActivity(intent) }) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "home"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) {
           innerPadding -> contactList(
        contacts = DataSource().getContactData(),
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    )

    }
}




@Preview(showBackground = true)
@Composable
fun createContactPreview() {
    integrate()

}