package com.example.qrcodereader

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureManager
import kotlinx.android.synthetic.main.activity_custom_scanner.*
import kotlinx.android.synthetic.main.custom_qr_code_scanner.*

class CustomScannerActivity : AppCompatActivity() {
    private lateinit var capture: CaptureManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_scanner)

        capture = CaptureManager(this, barcodeView).apply {
            initializeFromIntent(intent, savedInstanceState)
        }
        capture.decode()

        flashSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                barcodeView.setTorchOn()
            } else {
                barcodeView.setTorchOff()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        capture.onResume()
    }

    override fun onPause() {
        super.onPause()
        capture.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture.onDestroy()
    }
}