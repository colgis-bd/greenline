package com.colgisbd.greenline

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class BarcodeHome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode_home)
    }

    public fun barcodeReader(view: View) {
        startActivity(Intent(this@BarcodeHome, BarcodeReader::class.java))
    }

    public fun cameraReader(view: View) {
        startActivity(Intent(this@BarcodeHome, CameraActivity::class.java))
    }
}
