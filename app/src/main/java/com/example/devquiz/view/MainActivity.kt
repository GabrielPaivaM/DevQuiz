package com.example.devquiz.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import com.example.devquiz.R
import com.example.devquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private inner class ConnectivityReceiver: BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            verifyIfIsOnline()
        }
    }

    private lateinit var binding: ActivityMainBinding
    private var connectivityReceiver= ConnectivityReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(connectivityReceiver, filter)

        verifyIfIsOnline()
    }

    private fun verifyIfIsOnline() {
        if (isOnline()) {
            binding.cloudoffIcon.visibility = View.INVISIBLE
            binding.cloudoffTextview.visibility = View.INVISIBLE
        } else {
            binding.cloudoffIcon.visibility = View.VISIBLE
            binding.cloudoffTextview.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(connectivityReceiver)
    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }

    private fun setBackground() {
        val videoPath = "android.resource://" + packageName + "/" + R.raw.backgroundmainactivity4k
        val uri = Uri.parse(videoPath)

        binding.videoView.setVideoURI(uri)
        binding.videoView.start()
    }

    override fun onStart() {
        super.onStart()
        onPlayButtonClick()
    }

    private fun onPlayButtonClick() {
        binding.playButton.setOnClickListener {
            goToNextActivity()
        }
    }

    private fun goToNextActivity() {
        val intent = Intent(this, OptionsActivity::class.java)
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        backgroundPause()
    }

    private fun backgroundPause() {
        val videoPath = "android.resource://" + packageName + "/" + R.raw.backgroundmainactivity4k
        val uri = Uri.parse(videoPath)
        binding.videoView.setVideoURI(uri)

        binding.videoView.pause()
    }

    override fun onResume() {
        super.onResume()
        setBackground()
    }
}