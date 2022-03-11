package com.example.bottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.bottomnavigation.Fragment.Fragment1
import com.example.bottomnavigation.Fragment.Fragment2
import com.example.bottomnavigation.Fragment.Fragment3
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction().add(R.id.frame_lay, Fragment1()).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.home -> {
                supportFragmentManager.beginTransaction().replace(R.id.frame_lay, Fragment1()).commitAllowingStateLoss()
                return true
            }
            R.id.memo -> {
                supportFragmentManager.beginTransaction().replace(R.id.frame_lay, Fragment2()).commitAllowingStateLoss()
                return true
            }
            R.id.profile -> {
                supportFragmentManager.beginTransaction().replace(R.id.frame_lay, Fragment3()).commitAllowingStateLoss()
                return true
            }
        }
        return false
    }
}
