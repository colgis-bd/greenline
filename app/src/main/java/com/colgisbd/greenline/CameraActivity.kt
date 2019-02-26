package com.colgisbd.greenline

import android.Manifest
import android.Manifest.permission.CAMERA
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Camera
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.Detector
import android.view.SurfaceHolder
import android.util.Log
import android.view.View
import android.widget.Switch
import android.widget.Toast
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.google.zxing.Result
import kotlinx.android.synthetic.main.activity_camera.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import java.io.IOException


class CameraActivity : AppCompatActivity() , ZXingScannerView.ResultHandler{

    private val REQUEST_CAMERA: Int = 1
    private var scannerView: ZXingScannerView? = null

    override fun handleResult(result: Result?) {
        val myResult = result!!.getText()
        Log.d("QRCodeScanner", "----------------------------------------")
        Log.d("QRCodeScanner", result.getText())
        Log.d("QRCodeScanner", result.barcodeFormat.toString())
        Log.d("QRCodeScanner", "----------------------------------------")

//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Scan Result")
//        builder.setPositiveButton(
//            "OK"
//        ) { _, which -> scannerView!!.resumeCameraPreview(this@CameraActivity) }
//        builder.setNeutralButton("Visit") { dialog, which ->
//            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(myResult))
//            startActivity(browserIntent)
//        }
//        builder.setMessage(result.text)
//        val alert1 = builder.create()
//        alert1.show()
        val name = intent.getStringExtra("userName")
        val intent2 = Intent(this@CameraActivity, BarcodeResult::class.java)
        intent2.putExtra("result",result.text)
        intent2.putExtra("userName",name)
        startActivity(intent2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scannerView =  ZXingScannerView(this)
        setContentView(scannerView)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission() ) {
                Toast.makeText(this@CameraActivity, "Permission is Granted", Toast.LENGTH_SHORT).show()
            } else {
                requestPermission()
            }
        }

    }

    public override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if (scannerView == null) {
                    scannerView = ZXingScannerView(this)
                    setContentView(scannerView)
                }
                scannerView!!.setResultHandler(this)
                scannerView!!.startCamera()
            } else {
                requestPermission()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scannerView!!.stopCamera()
    }
    
    private fun checkPermission(): Boolean {
        return (ContextCompat.checkSelfPermission(this@CameraActivity, CAMERA) == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(CAMERA), REQUEST_CAMERA)
    }
    
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CAMERA -> if (grantResults.size > 0) {

                val cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                if (cameraAccepted) {
                    Toast.makeText(
                        applicationContext,
                        "Permission Granted, Now you can access camera",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Permission Denied, You cannot access and camera",
                        Toast.LENGTH_LONG
                    ).show()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(CAMERA)) {
                            showMessageOKCancel("You need to allow access to both the permissions",
                                DialogInterface.OnClickListener { dialog, which ->
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(
                                            arrayOf(CAMERA),
                                            REQUEST_CAMERA
                                        )
                                    }
                                })
                            return
                        }
                    }
                }
            }
        }
    }

    private fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        android.support.v7.app.AlertDialog.Builder(this@CameraActivity)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

}
