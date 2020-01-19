package com.example.prezentacja

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.io.Console
import java.lang.Exception
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        setUpActionBar()
    }

    private fun setUpActionBar(){
        startDefaultActivity()
        activeTest1Button.setOnClickListener {
            changeActivity(Test1())
        }
        activeTest2Button.setOnClickListener {
            changeActivity(Test2())
        }
    }

    private fun startDefaultActivity(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, Test1())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun changeActivity(newFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, newFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}

    //        button.setOnClickListener {
//            text.text = "Test Started"
//            CoroutineScope(Dispatchers.Main).launch {
//                Log.e("Thread: ", Thread.currentThread().name)
//                delay(10000)
//                text.text = "Test Ended"
//
//            }
//        }
//
//
//        var testValue = 0
//        button2.setOnClickListener {
//                text2.text = testValue.toString()
//            CoroutineScope(Dispatchers.Main).launch {
//                testValue++
//                text2.text = testValue.toString()
//                Log.e("Thread: ", Thread.currentThread().name)
//            }
//
//            MainScope().launch {  }
//        }

//    suspend fun startSuperTest2(){
//
//    }
//
//    suspend fun startSuperTest(){
//        val StartTime = System.currentTimeMillis()
//        withContext(Dispatchers.IO){
//            val d1 = async { getSomeValue(1) }
//            val d2 = async { getSomeValue(2) }
//            val d3 = async { getSomeValue(3) }
//            val sum = d1.await() + d2.await() + d3.await()
//            Log.e("SolutioN: ", sum.toString())
//        }
//        val EndTime = System.currentTimeMillis()
//        Log.e("Job Done In: ", (EndTime - StartTime).toString())
//
//        val job = GlobalScope.launch {  }
//
//    }
//
//    suspend fun getSomeValue(i: Int): Int {
//        delay(4000)
//        return 10 * i
//    }
//
//
//    private fun setNewText(input: String){
//        try {
//            val newText = text.text.toString() + "\n$input"
//            text.text = newText
//        } catch (error: Exception){
//            Log.e("ERRO", error.toString())
//        }
//
//    }
//    private suspend fun setTextOnMainThread(input: String) {
//        CoroutineScope(Dispatchers.Main).launch {
//            setNewText(input)
//        }
//    }
//
//    suspend fun fakeApi(){
//        delay(1000)
//    }
//
//    fun ndTest() = GlobalScope.launch {
//        delay(5000)
//        text.text = "WORKS"
//    }
//
//    fun coreTest() = runBlocking {
//
//            delay(5000)
//        text.text = "WORKS"
//
//    }
//
//    suspend fun asyncTest() {
//        withContext(Dispatchers.Default){
//            val response = async { doSomethingUsefulOne() }
//            response.await()
//        }
//    }
//
//    suspend fun doSomethingUsefulOne() {
//        delay(5000) // pretend we are doing something useful here
//        text.text = "XD"
//    }
