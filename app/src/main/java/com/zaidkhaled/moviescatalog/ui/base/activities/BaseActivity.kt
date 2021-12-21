package com.zaidkhaled.moviescatalog.ui.base.activities

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}