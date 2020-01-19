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
        super.onStart()
    }

    private suspend fun startTimer () {
        while (true){
            timerValue++
            delay(1000)
            timer.text = timerValue.toString()
        }
    }
}