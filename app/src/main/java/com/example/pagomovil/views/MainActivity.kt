package com.example.pagomovil.views

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.pagomovil.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(findViewById(R.id.toolBar))
        setupNav()
        setupPermission()
    }

    private fun setupNav(){
        val buttonNavigationView = findViewById<BottomNavigationView>(R.id.navMenu)
        NavigationUI.setupWithNavController(buttonNavigationView, Navigation.findNavController(this, R.id.content))
    }

    private fun setupPermission() {
        if (ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.SEND_SMS), 1000)
        }
    }

/*
    private lateinit var viewModel: MainViewModel


    private fun setupBinding(){
        var mainActivityBinding: com.example.pagomovil.databinding.MainActivityBinding
                = DataBindingUtil.setContentView(this, R.layout.main_activity)

        this.viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainActivityBinding.viewModel = this.viewModel
    }
*/
}