package com.example.intentorigin

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.intentorigin.databinding.ActivityMainBinding
import com.example.intentorigin.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    companion object {
        const val MESSAGE = "message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textFromMainActivity = intent?.extras?.getString(MESSAGE).toString()
        binding.fromMainActivity.text=textFromMainActivity
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.share_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.share -> {
                share()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun share() {
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            setType("text/plain")
            putExtra(Intent.EXTRA_TEXT, binding.fromMainActivity.text)
            putExtra(Intent.EXTRA_SUBJECT, "Email subject")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("alain.perraul@wanadoo.fr"))
        }
        try {
            startActivity(sendIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(applicationContext, "share application not available", Toast.LENGTH_SHORT).show()
        }
    }
}