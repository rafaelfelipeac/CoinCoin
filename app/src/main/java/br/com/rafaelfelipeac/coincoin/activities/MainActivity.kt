package br.com.rafaelfelipeac.coincoin.activities

import android.app.FragmentManager
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.rafaelfelipeac.coincoin.R
import br.com.rafaelfelipeac.coincoin.dao.MoneyDAO
import br.com.rafaelfelipeac.coincoin.fragments.FragmentA
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_a.*

class MainActivity : AppCompatActivity() {



    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val manager = supportFragmentManager
                manager.beginTransaction().replace(R.id.frame_principal, FragmentA()).commit()

            }
            R.id.navigation_dashboard -> {

            }
//            R.id.navigation_notifications -> {
//
//            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = supportFragmentManager
        manager.beginTransaction().replace(R.id.frame_principal, FragmentA()).commit()
        //setSupportActionBar(toolbar)



        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
