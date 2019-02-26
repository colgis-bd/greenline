package com.colgisbd.greenline

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    public fun loginClick(view: View) {
        var name = editText_ID.text.toString()
        val intent = Intent(this@MainActivity, MainMenu::class.java)
        intent.putExtra("userName",name)
        startActivity(intent)
    }
}
