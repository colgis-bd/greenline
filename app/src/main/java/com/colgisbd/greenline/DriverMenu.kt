package com.colgisbd.greenline

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_driver_menu.*

class DriverMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_menu)
        val name = intent.getStringExtra("userName")
        textView_showID.setText(name)
    }

    public fun barcodeClick(view: View) {
        val intent = Intent(this@DriverMenu, BarcodeHome::class.java)
        intent.putExtra("userName",textView_showID.text)
        startActivity(intent)
    }
}
