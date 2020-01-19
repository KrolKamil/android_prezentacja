package com.example.prezentacja

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.XmlRes
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.test2.*
import kotlinx.coroutines.*

class Test2 : Fragment() {
    private var testInProgress = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.test2, container, false)
    }

    override fun onStart() {
        startTestButton.setOnClickListener {
            if(testInProgress === false){
                sScore.text = "wynik"
                aScore.text = "wynik"
                CoroutineScope(Dispatchers.Main).launch {
                    testInProgress = true
                    startAsynchronousTest()
                    startSynchronousTest()
                    testInProgress = false
                }
            }
        }
        super.onStart()
    }

    private suspend fun startSynchronousTest(){
        val start = System.currentTimeMillis()
        stressTest(s1)
        stressTest(s2)
        stressTest(s3)
        val stop = System.currentTimeMillis()
        sScore.text = (stop - start).toString()
    }

    private suspend fun startAsynchronousTest(){
        withContext(Dispatchers.Main){
            val start = System.currentTimeMillis()
            val t1 = async { stressTest(a1) }
            val t2 = async { stressTest(a2) }
            val t3 = async { stressTest(a3) }
            awaitAll(t1, t2, t3)
            val stop = System.currentTimeMillis()
            aScore.text = (stop - start).toString()
        }
    }

    private suspend fun stressTest(textElement: TextView){
        textElement.text = ""
        delay(1000)
        textElement.text = "*"
        delay(1000)
        textElement.text = "**"
        delay(1000)
        textElement.text = "***"
    }
}