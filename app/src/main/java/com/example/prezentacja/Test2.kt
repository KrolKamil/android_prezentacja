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
                    resetTestProgress()
                    testInProgress = true
                    val aTest = async {startAsynchronousTest()}
                    val sTest = async {startSynchronousTest()}
                    awaitAll(aTest, sTest)
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
        delay(1000)
        textElement.text = "*"
        delay(1000)
        textElement.text = "**"
        delay(1000)
        textElement.text = "***"
    }

    private fun resetTestProgress(){
        s1.text = ""
        s2.text = ""
        s3.text = ""
        a1.text = ""
        a2.text = ""
        a3.text = ""
    }
}