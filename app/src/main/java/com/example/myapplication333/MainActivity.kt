package com.example.myapplication333


import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.remember

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.background


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState

import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Yellow

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.myapplication333.db.LoadDB
import com.example.myapplication333.db.LoadItem
import com.example.myapplication333.ui.theme.MyApplication333Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
   // private lateinit var loads: List<LoadItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MyApplication333.setContext(this)

        val db = Room.databaseBuilder(
            context,
            LoadDB::class.java, "load_barbells_db"
        ).build()



        setContent {
            MyApplication333Theme {

                //loads =

                AlertDialogSample()


            }
        }
    }
}

val context = MyApplication333.getContext()


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplication333Theme {
        AlertDialogSample()
    }
}

//val _loads = mutableStateListOf<LoadItem>()


data class Load(val name: String, val credits: Int)
data class Barbells(val name: String, val credits: Int)

//var loads = listOf(
//    Load("Cięzarek 1", 125),
//    Load("Cięzarek 2", 75),
//    Load("Cięzarek 3", 15),
//    Load("Cięzarek 4", 87),
//    Load("Cięzarek 5", 22),
//    Load("Cięzarek 6", 96),
//    Load("Cięzarek 7", 125),
//    Load("Cięzarek 8", 75),
//    Load("Cięzarek 9", 15),
//    Load("Cięzarek 10", 87),
//    Load("Cięzarek 11", 22),
//    Load("Cięzarek 12", 96),
//    Load("Zacisk 9", 15),
//    Load("Zacisk 10", 87),
//    Load("Zacisk 11", 22),
//    Load("Zacisk 12", 96),
//)


val barbells = listOf(
    Barbells("Gryf 1", 125),
    Barbells("Gryf 2", 125),
    Barbells("Gryf 3", 125),
    Barbells("Gryf 4", 125),
    Barbells("Gryf 5", 125),
    Barbells("Gryf 6", 125),

    )

@Composable
fun LoadRow(loadItem: LoadItem) {
    Card(
        modifier = Modifier
            .padding(all = 10.dp)
    ) {
        Column(modifier = Modifier.padding(all = 10.dp)) {
            Text(
                loadItem.name,
                fontSize = 25.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.padding(10.dp)
            )
            Text(loadItem.weight.toString(), color = Color.Gray, modifier = Modifier.padding(10.dp))
        }
    }
}

@Composable
fun LoadRow2(barbells: Barbells) {
    Card(
        modifier = Modifier
            .padding(all = 10.dp)
    ) {
        Column(modifier = Modifier.padding(all = 10.dp)) {
            Text(
                barbells.name,
                fontSize = 25.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                barbells.credits.toString(),
                color = Color.Gray,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

fun <T> SnapshotStateList<T>.swapList(newList: List<T>){
    clear()
    addAll(newList)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogSample() {


    val db = Room.databaseBuilder(
        context,
        LoadDB::class.java, "load_barbells_db"
    ).build()


    val scope = rememberCoroutineScope()

    var text by remember { mutableStateOf("0") }

    var loads = remember { mutableStateListOf<LoadItem>() }

                        LaunchedEffect(Unit) {
                        scope.launch {
                            withContext(Dispatchers.IO) {
                                val loadDBDao = db.loadDao().getAll()
                                val list: List<LoadItem> = loadDBDao
//                                Log.v("testo", "m " + (list.get(0).name))
                                loads.swapList(loadDBDao)
                                //  Toast.makeText(context,"test",Toast.LENGTH_LONG).show()
                                //testt

                            }

                        }}





    val context = MyApplication333.getContext()
    MaterialTheme {
        Column() {


            Row {
                val openDialog1 = remember { mutableStateOf(false) }
                val openDialog2 = remember { mutableStateOf(false) }

                Button(onClick = {
                    openDialog1.value = true
                }, modifier = Modifier.padding(8.dp)) {
                    Text("dodaj obciążenie")

//                    val db = Room.databaseBuilder(
//                        context ,
//                        LoadDB::class.java, "load_barbells_db"
//                    ).build()
//
//                    val item = LoadItem(null,"Grześ",1.3f)
//
//                    item.name = "greg"
//
//                    val loadDBDao = db.loadDao()
//
//                    val scope = rememberCoroutineScope()
//
//                    LaunchedEffect(Unit) {
//                        scope.launch {
//                            withContext(Dispatchers.IO) {
//                                loadDBDao.insert(item)
//
//                            }
//                        }
//                    }


                }

                Spacer(Modifier.weight(1f))

                Button(onClick = {
                    openDialog2.value = true
                }, modifier = Modifier.padding(8.dp)) {
                    Text("dodaj gryf")
                }

                if (openDialog1.value) {

                    AlertDialog(
                        onDismissRequest = {
                            openDialog1.value = false
                        },
                        title = {
                            Text(text = "dodaj obciążenie")
                        },
                        text = {
                            Column() {


                                TextField(
                                    value = text,
                                    onValueChange = {
                                        text = it
                                    },
                                    label = { Text("nazwa:") }
                                )

                                TextField(
                                    value = text,
                                    onValueChange = {
                                        text = it
                                    },
                                    label = { Text("kg:") }
                                )

                            }
                        },
                        confirmButton = {
                            Button(

                                onClick = {
                                    openDialog1.value = false


                                    val db = Room.databaseBuilder(
                                        context,
                                        LoadDB::class.java, "load_barbells_db"
                                    ).build()

                                    val item = LoadItem(null, "Grześ", 1.3f)

                                    val loadDBDao = db.loadDao()

                                    fun main() = runBlocking { // this: CoroutineScope
                                        launch {
                                            loadDBDao.insert(item)

                                         //   var loding:LoadItem= LoadItem(444,"dd",1.0f)


                                         //   _loads.add(loding)
                                        }
                                    }

                                    main()


                                //    LaunchedEffect(Unit) {
                              //  AlertDialogSample()
                                //    }


                                    GlobalScope.launch {
                                    val loadDBDao1 = db.loadDao().getAll()
                                    fun main3() = runBlocking { // this: CoroutineScope

                                        launch {

                                            loads.swapList(loadDBDao1)

                                        }}

                                    main3()
                                    }








//refresh

                                }) {
                                Text("dodaj")
                            }
                        },
                        dismissButton = {
                            Button(

                                onClick = {
                                    openDialog1.value = false


                                    fun main2() = runBlocking { // this: CoroutineScope

                                        val db = Room.databaseBuilder(
                                            context,
                                            LoadDB::class.java, "load_barbells_db"
                                        ).build()


                                        CoroutineScope(Dispatchers.IO).launch {
                                            val loadDBDao = db.loadDao().getAll()
                                            val list: List<LoadItem> = loadDBDao
                                            Log.v("testo", "m " + (list.get(0).name))
                                            //  Toast.makeText(context,"test",Toast.LENGTH_LONG).show()
                                        }


                                        launch {


                                        }
                                    }

                                    main2()


                                }) {
                                Text("anuluj")
                            }
                        }
                    )
                }




                if (openDialog2.value) {

                    AlertDialog(
                        onDismissRequest = {
                            openDialog2.value = false
                        },
                        title = {
                            Text(text = "dodaj gryf")
                        },
                        text = {
                            Column() {


                                TextField(
                                    value = text,
                                    onValueChange = {
                                        text = it
                                    },
                                    label = { Text("nazwa:") }
                                )

                                TextField(
                                    value = text,
                                    onValueChange = {
                                        text = it
                                    },
                                    label = { Text("kg:") }
                                )

                            }
                        },

                        confirmButton = {
                            Button(

                                onClick = {
                                    openDialog2.value = false
                                }) {
                                Text("dodaj")
                            }
                        },
                        dismissButton = {
                            Button(

                                onClick = {
                                    openDialog2.value = false
                                }) {
                                Text("anuluj")
                            }
                        }
                    )
                }


            }


            Row() {
                Column(
                    Modifier
                        .weight(1f)
                        .background(Blue)
                        .verticalScroll(rememberScrollState())
                ) {
                    loads.forEach { load ->
                        LoadRow(load)
                    }
                }
                Column(
                    Modifier
                        .weight(2f)
                        .background(Yellow)
                        .verticalScroll(rememberScrollState())
                ) {
                    barbells.forEach { barbells ->
                        LoadRow2(barbells)
                    }
                }
            }
        }


    }
}



