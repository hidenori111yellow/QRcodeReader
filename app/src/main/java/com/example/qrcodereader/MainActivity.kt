package com.example.qrcodereader


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.client.android.Intents
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val qrButton: Button = findViewById(R.id.qr_button)

        qrButton.setOnClickListener {
            IntentIntegrator(this).apply {
                captureActivity = (CustomScannerActivity::class.java)
                setOrientationLocked(true)
                setBeepEnabled(false)
                setTorchEnabled(false)
            }.initiateScan()
        }
    }

    // 読取後に呼ばれる
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            // 読取結果はresult.contentsで参照できる
            Log.w("result.contents", result.contents)
            Toast.makeText(this, result.contents, Toast.LENGTH_LONG).show()
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}