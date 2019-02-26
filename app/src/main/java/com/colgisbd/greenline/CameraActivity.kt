package com.colgisbd.greenline

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.Detector
import android.view.SurfaceHolder
import android.util.Log
import android.view.View
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.IOException


class CameraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

    }

    public fun getPicture(view: View){
        val barcodeDetector = BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build()

        val cameraSource: CameraSource = CameraSource.Builder(this, barcodeDetector).setRequestedPreviewSize(900, 480).build()

        this.camera_view.getHolder().addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {

                try {
                    cameraSource.start(camera_view.getHolder())
                } catch (ie: IOException) {
                    Log.e("CAMERA SOURCE", ie.toString())
                }

            }
            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource.stop()
            }
        })
        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {}

            override fun receiveDetections(detections: Detector.Detections<Barcode>) {

                val barcodes = detections.detectedItems

                if (barcodes.size() != 0) {
                    txtContent.post(Runnable // Use the post method of the TextView
                    {
                        txtContent.text = barcodes.valueAt(0).displayValue
                    })
                }
            }
        })
    }
}
