package com.colgisbd.greenline

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        val name = intent.getStringExtra("userName")
        textView_showID.setText("Username : "+name)
    }
    public fun driverClick(view: View) {
        val intent = Intent(this@MainMenu, DriverMenu::class.java)
        intent.putExtra("userName",textView_showID.text)
        startActivity(intent)
    }
}
