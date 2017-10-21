package br.com.rafaelfelipeac.coincoin.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import br.com.rafaelfelipeac.coincoin.R
import br.com.rafaelfelipeac.coincoin.fragments.FragmentMain
import br.com.rafaelfelipeac.coincoin.fragments.FragmentGoals
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val manager = supportFragmentManager
                manager.beginTransaction().replace(R.id.frame_principal, FragmentMain()).commit()
                supportActionBar?.title = "CoinCoin"
                true
            }
            R.id.navigation_dashboard -> {
                val manager = supportFragmentManager
                manager.beginTransaction().replace(R.id.frame_principal, FragmentGoals()).commit()
                supportActionBar?.title = "Metas"
                true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = supportFragmentManager
        manager.beginTransaction().replace(R.id.frame_principal, FragmentMain()).commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
