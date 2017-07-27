package com.gruveo.sdk.kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gruveo.sdk.Gruveo
import com.gruveo.sdk.model.CallErrorType
import com.gruveo.sdk.model.CallErrorType.*
import com.gruveo.sdk.model.GrvConstants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val REQUEST_CALL = 1

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
        val result = Gruveo.Builder(this).callCode(code).videoCall(videoCall).clientId("demo").requestCode(REQUEST_CALL).eventsListener(eventsListener).build()
        when (result) {
            Gruveo.GRV_RES_MISSING_CALL_CODE -> { }
            Gruveo.GRV_RES_INVALID_CALL_CODE -> { }
            Gruveo.GRV_RES_MISSING_CREDENTIALS -> { }
            Gruveo.GRV_RES_INVALID_CREDENTIALS -> { }
            Gruveo.GRV_RES_OFFLINE -> { }
            else -> { }
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CALL && resultCode == Activity.RESULT_OK && data != null) {
            val error = data.getSerializableExtra(GrvConstants.GRV_RES_CALL_ERROR)
            val callCode = data.getStringExtra(GrvConstants.GRV_RES_CALL_CODE)
            val duration = data.getIntExtra(GrvConstants.GRV_RES_CALL_DURATION, 0)
            val messagesExchanged = data.getIntExtra(GrvConstants.GRV_RES_MESSAGES_EXCHANGED, 0)

            when (error as CallErrorType) {
                BUSY -> { }
                DIRECT_BUSY -> { }
                DIRECT_UNREACHABLE -> { }
                DIRECT_NONEXIST -> { }
                DIRECT_CALLING_SELF -> { }
                FREE_MULTIPARTY_ENDED -> { }
                MULTIPARTY_NOT_SUPPORTED -> { }
                FREE_DEMO_ENDED -> { }
                ROOM_LIMIT_REACHED -> { }
                NO_CONNECTION -> { }
                NONE -> { }
            }
        }
    }
}