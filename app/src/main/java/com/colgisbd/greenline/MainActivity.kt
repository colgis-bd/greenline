package com.colgisbd.greenline

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.makeText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    public fun loginClick(view: View) {
//        makeText(this, "Login Clicked", Toast.LENGTH_LONG).show()
        startActivity(Intent(this@MainActivity, main_menu::class.java))
    }
}
