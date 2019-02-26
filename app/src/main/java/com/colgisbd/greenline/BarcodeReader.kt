package com.colgisbd.greenline

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.graphics.BitmapFactory
import kotlinx.android.synthetic.main.activity_barcode_reader.*
import android.widget.TextView
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector

class BarcodeReader : AppCompatActivity() {
    private var scanResults: TextView? = null
    private var detector: BarcodeDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode_reader)
    }

    public fun loadImage(view: View) {
        val myBitmap = BitmapFactory.decodeResource(
            applicationContext.resources,
            R.drawable.image
        )
        imgview.setImageBitmap(myBitmap)

        detector = BarcodeDetector.Builder(applicationContext).setBarcodeFormats(Barcode.ALL_FORMATS).build()

        if (!detector!!.isOperational)
        {
            scanResults!!.text = "Could not set up the detector!"
            return
        }
        val frame = Frame.Builder().setBitmap(myBitmap).build()
        val barcodes = detector!!.detect(frame)
        val thisCode = barcodes.valueAt(0)
        txtView.text = thisCode.rawValue
    }
}
