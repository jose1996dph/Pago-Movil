package com.example.pagomovil.views

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.pagomovil.R
import com.example.pagomovil.viewModels.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.Exception
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setActionBar(findViewById(R.id.tbBalance))
        configNav()/*
        try {
            setupBinding()
        } catch (e: Exception){
            Log.e("ERROR", e.message)
        }*/

        setupPermission()
    }

    fun configNav(){
        val buttonNavigationView = findViewById<BottomNavigationView>(R.id.navMenu)
        NavigationUI.setupWithNavController(buttonNavigationView, Navigation.findNavController(this, R.id.content))
    }

/*
    private fun setupBinding(){
        var mainActivityBinding: com.example.pagomovil.databinding.MainActivityBinding
                = DataBindingUtil.setContentView(this, R.layout.main_activity)

        this.viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        mainActivityBinding.viewModel = this.viewModel
    }*/

    private fun setupPermission() {
        if (ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.SEND_SMS), 1000)
        } else {
        }
    }
}