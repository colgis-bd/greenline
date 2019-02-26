package com.colgisbd.greenline

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_barcode_home.*

class BarcodeHome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode_home)
        val name = intent.getStringExtra("userName")
        textView_showID.setText(name)
    }

    fun cameraReader(view: View) {
        val intent = Intent(this@BarcodeHome, CameraActivity::class.java)
        intent.putExtra("userName",textView_showID.text)
        startActivity(intent)
    }
}
