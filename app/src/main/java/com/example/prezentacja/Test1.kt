package com.example.prezentacja

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.test1.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Test1 : Fragment() {

    var timerValue = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.test1, container, false)
    }

    override fun onStart() {
        CoroutineScope(Dispatchers.Main).launch{
            startTimer()
        }
        threadTestButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                startHardJobThread()
            }
        }
        coroutineTestButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                startHardJobCoroutine()
            }
        }
        super.onStart()
    }

    private suspend fun startHardJobThread(){
        threadText.text = "Ciezko pracuje - thread"
        delay(100)
        Thread.sleep(5000)
        threadText.text = "Praca zakonczona"
    }

    private suspend fun startHardJobCoroutine(){
        coroutineText.text = "Ciezko pracuje - coroutine"
        delay(5000)
        coroutineText.text = "Praca zakonczona"
    }

    private suspend fun startTimer () {
        while (true){
            try{
                timerValue++
                delay(1000)
                timer.text = timerValue.toString()
            } catch (error: Exception){

            }
        }
    }
}