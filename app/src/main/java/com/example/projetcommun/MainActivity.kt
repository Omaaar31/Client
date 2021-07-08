package com.example.projetcommun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        thread{
            try {
                WSUtils.test()
                WSUtils.retest()

            }catch(e:Exception) {
                e.printStackTrace()
            }

        }


    }

    //créer menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0, 1, 0, "Map")
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==1) {
            val intent= Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)

    }


}