package com.example.mysalon

import android.os.Bundle
import android.os.Handler
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
//スライド
    class MyAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val resources = listOf(
            R.drawable.salon1,R.drawable.salon2,
            R.drawable.salon3,R.drawable.salon4)

        override fun getCount(): Int {
            return  resources.size
        }

        override fun getItem(position: Int): Fragment {
            return  ImageFragment.newInstance(resources[position])
        }
    }
//end スライド
    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                textMessage.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                textMessage.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                textMessage.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//コンテキストメニュー
        registerForContextMenu(BookingButton)

        textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
//スライドショー
        pager.adapter = MyAdapter(supportFragmentManager)
        val handler = Handler()
        timer(period = 4500){
            handler.post{
                pager.currentItem = (pager.currentItem+1)%4
            }
        }
//        エンドスライド
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context, menu)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.sms ->return true
            R.id.mail ->return true
            R.id.share ->return true
            R.id.browse ->return true
        }
        return super.onContextItemSelected(item)
    }
}
