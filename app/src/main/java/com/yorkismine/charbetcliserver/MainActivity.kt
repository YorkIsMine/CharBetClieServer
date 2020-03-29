package com.yorkismine.charbetcliserver

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.net.Socket

class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SocketHelper.connect()
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()

        recycler.layoutManager = LinearLayoutManager(this)
        val adapter = MessageAdapter()
        recycler.adapter = adapter

        SocketHelper.read().map {
                Message(it)
            }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ m -> adapter.setData(m) }, { t -> t.printStackTrace() })


        send_btn.setOnClickListener {
            val text: String? = message_field_et.text.toString()
            if (text.isNullOrEmpty()) return@setOnClickListener

            val message = Message(text)
            SocketHelper.write(text)

            adapter.setData(message)
        }
    }
}
