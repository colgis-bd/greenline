package com.colgisbd.greenline

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.activity_barcode_result.*

class BarcodeResult : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode_result)
        val barcode_value = intent.getStringExtra("result")
        val name = intent.getStringExtra("userName")
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Scan Result")
//        builder.setNeutralButton("Visit") { dialog, which ->
//        }
//        builder.setMessage(barcode_value)
//        val alert1 = builder.create()
//        alert1.show()
        barcodeValue.text= barcode_value
        textView_showID.setText(name)
    }
}
