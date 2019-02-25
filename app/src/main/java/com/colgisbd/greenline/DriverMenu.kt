package com.colgisbd.greenline

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class DriverMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_menu)
    }

    public fun barcodeClick(view: View) {
        startActivity(Intent(this@DriverMenu, BarcodeHome::class.java))
    }
}
