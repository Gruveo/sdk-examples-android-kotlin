package com.gruveo.sdk.kotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gruveo.sdk.Gruveo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_video_button.setOnClickListener {
            initCall(true)
        }

        main_voice_button.setOnClickListener {
            initCall(false)
        }
    }

    private fun initCall(videoCall: Boolean) {
        val code = main_edittext.text.toString()
        val result = Gruveo.Builder(this).callCode(code).videoCall(videoCall).clientId("demo").eventsListener(eventsListener).build()
        when (result) {
            Gruveo.GRV_RES_MISSING_CALL_CODE -> {
            }
            Gruveo.GRV_RES_INVALID_CALL_CODE -> {
            }
            Gruveo.GRV_RES_MISSING_CREDENTIALS -> {
            }
            Gruveo.GRV_RES_INVALID_CREDENTIALS -> {
            }
            Gruveo.GRV_RES_OFFLINE -> {
            }
            else -> {
            }
        }
    }

    private val eventsListener = object : Gruveo.EventsListener {
        override fun callInit(videoCall: Boolean, code: String) {

        }

        override fun callEstablished(code: String) {

        }

        override fun callEnd(data: Intent, isInForeground: Boolean) {

        }
    }
}
