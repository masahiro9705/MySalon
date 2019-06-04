package com.example.mysalon

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main_next.*

class Main_next : AppCompatActivity() {

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                textMessage.setText(R.string.title_home)
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_menu -> {
                textMessage.setText(R.string.title_menu)
                val intent = Intent(this,MenuActivitity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_access -> {
                textMessage.setText(R.string.title_access)
                val intent = Intent(this,AccessAcvivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_next)

        val id = intent.getIntExtra("MY_IMAGE",0)
        when(id) {
            R.id.AboutButton ->NextImage.setImageResource(R.drawable.top1)
            R.id.ShopButton ->NextImage.setImageResource(R.drawable.top2)
            R.id.StaffButton ->NextImage.setImageResource(R.drawable.next2)
            R.id.StyleButton ->NextImage.setImageResource(R.drawable.salon2)

        }
        when(id){
            R.id.AboutButton -> h1text.setText(R.string.about_h1)
            R.id.StaffButton -> h1text.setText(R.string.staff_h1)
            R.id.StyleButton -> h1text.setText(R.string.style_h1)
            R.id.ShopButton ->  h1text.setText(R.string.shop_h1)
        }
        when(id){
            R.id.AboutButton -> h2text.setText(R.string.about_h2)
            R.id.StaffButton -> h2text.setText(R.string.staff_h2)
            R.id.StyleButton -> h2text.setText(R.string.style_h2)
            R.id.ShopButton ->  h2text.setText(R.string.shop_h2)
        }
        when(id){
            R.id.AboutButton -> h3text.setText(R.string.about_h3)
            R.id.StaffButton -> h3text.setText(R.string.staff_h3)
            R.id.StyleButton -> h3text.setText(R.string.style_h3)
            R.id.ShopButton ->  h3text.setText(R.string.shop_h3)
        }

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
