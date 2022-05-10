package com.example.intentorigin

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.intentorigin.databinding.ActivityMainBinding
import com.example.intentorigin.databinding.ActivitySecondBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toSecondActivity.setOnClickListener {
            val i = Intent(this, SecondActivity::class.java)
            val textToSecondActivity = binding.textForSecondActivityEdit.text.toString()
            i.putExtra(SecondActivity.MESSAGE, textToSecondActivity)
            startActivity(i)
        }

        binding.callUrl.setOnClickListener {
            val viewUrl = Intent(Intent.ACTION_VIEW).apply {
                val textUrl = binding.urlEdit.text.toString()
                setData(Uri.parse(textUrl))
            }
            try {
                startActivity(viewUrl)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(applicationContext, "Web application not available", Toast.LENGTH_SHORT).show()
            }
        }
    }
}