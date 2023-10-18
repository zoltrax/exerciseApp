package com.example.myapplication333


import android.os.Bundle
import android.widget.Toast
import androidx.compose.runtime.remember

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable


import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Row


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState

import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue


import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.example.myapplication333.db.LoadDB
import com.example.myapplication333.db.LoadItem
import com.example.myapplication333.ui.theme.MyApplication333Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MyApplication333.setContext(this)


//        val db = Room.databaseBuilder(
//            MyApplication333.getContext(),
//            LoadDB::class.java, "load_barbells_db"
//        ).build()



        setContent {
            MyApplication333Theme {

                //loads =

                AlertDialogSample()


            }
        }
    }
}

lateinit var loads: SnapshotStateList<LoadItem>
//val context = MyApplication333.getContext()


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplication333Theme {
        AlertDialogSample()
    }
}

//val _loads = mutableStateListOf<LoadItem>()


//data class Load(val name: String, val credits: Int)
//data class Barbells(val name: String, val credits: Int)


//val barbells = listOf(
//    Barbells("Gryf 1", 125),
//    Barbells("Gryf 2", 125),
//    Barbells("Gryf 3", 125),
//    Barbells("Gryf 4", 125),
//    Barbells("Gryf 5", 125),
//    Barbells("Gryf 6", 125),
//
//    )

@OptIn(ExperimentalFoundationApi::class, DelicateCoroutinesApi::class)
@Composable
fun LoadRow(loadItem: LoadItem) {
//    var offsetX by remember { mutableStateOf(0f) }
    //   val openDialog3 = remember { mutableStateOf(false) }
    val openDialog3 = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(all = 6.dp)
            .combinedClickable(


                onLongClick = {

                    openDialog3.value = true
                    Toast
                        .makeText(MyApplication333.getContext(), "long", Toast.LENGTH_SHORT)
                        .show()


                },

                onClick = { })


    ) {
        Column(
            modifier = Modifier
                .padding(all = 2.dp)
                .fillMaxWidth(1f)
        ) {
            Text(
                loadItem.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.padding(2.dp)
            )
            Text(loadItem.weight, color = Color.Gray, modifier = Modifier.padding(2.dp))
        }
    }

    if (openDialog3.value) {

        AlertDialog(
            onDismissRequest = {
                openDialog3.value = false
            },
            title = {
                Text(text = "usun")
            },
            text = {
                Column {


                }
            },

            confirmButton = {
                Button(

                    onClick = {
                        openDialog3.value = false

                        Toast.makeText(MyApplication333.getContext(), loadItem.name, Toast.LENGTH_SHORT).show()


                        val db = Room.databaseBuilder(
                            MyApplication333.getContext(),
                            LoadDB::class.java, "load_barbells_db"
                        ).build()

                        val loadDBDao = db.loadDao()

                        fun main2() = runBlocking { // this: CoroutineScope
                            launch {
                                loadDBDao.delete(loadItem)

                            }
                        }
                        main2()


                        GlobalScope.launch {
                            val loadDBDao1 = db.loadDao().getAll()
                            fun main3() = runBlocking { // this: CoroutineScope

                                launch {

                                    loads.swapList(loadDBDao1)

                                }
                            }

                            main3()
                        }


                    }) {
                    Text("usun")
                }
            },
            dismissButton = {
                Button(

                    onClick = {
                        openDialog3.value = false
                    }) {
                    Text("anuluj")
                }
            }
        )
    }

}

//@Composable
//fun LoadRow2(content: @Composable RowScope.() -> Unit) {
//
//    Row {
//        content()
//    }
//
//}


fun <T> SnapshotStateList<T>.swapList(newList: List<T>) {
    clear()
    addAll(newList)
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun AlertDialogSample() {


    val db = Room.databaseBuilder(
        MyApplication333.getContext(),
        LoadDB::class.java, "load_barbells_db"
    ).build()


    val scope = rememberCoroutineScope()

    var text by remember { mutableStateOf("0") }

    loads = remember { mutableStateListOf() }

    var name by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        scope.launch {
            withContext(Dispatchers.IO) {
                val loadDBDao = db.loadDao().getAll()
               // val list: List<LoadItem> = loadDBDao
                loads.swapList(loadDBDao)


            }

        }
    }


//    val context = MyApplication333.getContext()
    MaterialTheme {
        Column {


            Row {
                val openDialog1 = remember { mutableStateOf(false) }
                val openDialog2 = remember { mutableStateOf(false) }



                Button(onClick = {
                    openDialog1.value = true
                }, modifier = Modifier.padding(8.dp)) {
                    Text("dodaj obciążenie")


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
                            Column {


                                TextField(
                                    value = name,
                                    onValueChange = {
                                        name = it
                                    },
                                    label = { Text("nazwa:") }
                                )



                                TextField(
                                    value = weight,
                                    onValueChange = {
                                        weight = it
                                    },
                                    label = { Text("kg:") }
                                )

                            }
                        },
                        confirmButton = {
                            Button(

                                onClick = {
                                    openDialog1.value = false


//                                    val db = Room.databaseBuilder(
//                                        context,
//                                        LoadDB::class.java, "load_barbells_db"
//                                    ).build()

                                    val item = LoadItem(null, name, weight)

                                    val loadDBDao = db.loadDao()

                                    fun main() = runBlocking { // this: CoroutineScope
                                        launch {
                                            loadDBDao.insert(item)
                                            name = ""
                                            weight = ""

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

                                            }
                                        }

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

//                                        val db = Room.databaseBuilder(
//                                            context,
//                                            LoadDB::class.java, "load_barbells_db"
//                                        ).build()


                                        CoroutineScope(Dispatchers.IO).launch {
                                            //val loadDBDao = db.loadDao().getAll()
                                            //val list: List<LoadItem> = loadDBDao
                                            //   Log.v("testo", "m " + (list.get(0).name))
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
                            Column {


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


            Row(Modifier.fillMaxHeight(0.9f)) {
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
                        .fillMaxWidth(2f)
                        .weight(2f)
                ) {


                    Row(
                        Modifier
                            .fillMaxHeight(0.5f)
                            .fillMaxWidth(2f)
                            .weight(2f)
                            .background(Color.Green)

                    ) {
                        Text("GRYF 1")

                    }


                    Row(
                        Modifier
                            .fillMaxHeight(1f)
                            .fillMaxWidth(2f)
                            .weight(2f)
                            .background(Color.Magenta)

                    ) {
                        Text("GRYF 2")

                    }

                }

//                Column(
//                    Modifier
//                        .weight(2f)
//                        .height(200.dp)
//                        .background(Color.Magenta)
//                        .verticalScroll(rememberScrollState())
//                ) {
//
//                }


            }


            Row(
                modifier = Modifier.fillMaxWidth()
                //  verticalArrangement = Arrangement.Bottom


            ) {
                Button(
                    onClick = {},
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text(text = "G1")
                }
                Button(
                    onClick = {},
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text(text = "G2")
                }
                Button(
                    onClick = {},
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text(text = "G3")
                }
                Button(
                    onClick = {},
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text(text = "G4")
                }
                Button(
                    onClick = {},
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text(text = "G5")
                }
            }


        }


    }
}



