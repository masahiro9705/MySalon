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
import android.content.Intent
import android.net.Uri


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
//コンテキストメニュー長押し
        registerForContextMenu(BookingButton)
//        エンドコンテキスト

// ナビメニュー
        textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
//        エンドナビ

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
//    コンテキストメニュー
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context, menu)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.sms -> {
                val number ="999-9999-9999"
                val uri = Uri.parse("sms:$number")
                var intent = Intent(Intent.ACTION_VIEW)
                intent.data = uri
                startActivity(intent)
                return true
            }
            R.id.mail -> {
                val email = "nobody@example.com"
                val subject ="予約お問い合わせ"
                val text = "以下のとおり予約希望します。"
                val uri = Uri.parse("mailto:")
                val intent =Intent(Intent.ACTION_SENDTO)
                intent.data = uri
                intent.putExtra(Intent.EXTRA_EMAIL,arrayOf(email))
                        .putExtra(Intent.EXTRA_SUBJECT,subject)
                        .putExtra(Intent.EXTRA_TEXT, text)
                if (intent.resolveActivity(packageManager) != null ){
                    startActivity(intent)
                }
                return true
            }
            R.id.share -> {
                val uri = Uri.parse("tel:99999999999")
                val intent = Intent(Intent.ACTION_DIAL, uri)
                startActivity(intent)
                return true
            }
            R.id.browse -> {
                val url: String = "https://instagram.com/_ats_wtnb?igshid=1sdh8qyme6wdt"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                if (intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
//    エンドコンテキストメニュー
}
