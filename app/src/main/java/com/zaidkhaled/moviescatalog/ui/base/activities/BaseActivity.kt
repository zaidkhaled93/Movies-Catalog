package com.zaidkhaled.moviescatalog.ui.base.activities

import androidx.appcompat.app.AppCompatActivity

//Base class for shared properties and functions between all activities..
abstract class BaseActivity : AppCompatActivity() {
    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}