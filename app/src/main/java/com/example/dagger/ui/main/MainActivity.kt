package com.example.dagger.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import com.example.dagger.BaseActivity
import com.example.dagger.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}