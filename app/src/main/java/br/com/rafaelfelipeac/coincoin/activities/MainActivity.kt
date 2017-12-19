package br.com.rafaelfelipeac.coincoin.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.com.rafaelfelipeac.coincoin.R
import br.com.rafaelfelipeac.coincoin.fragments.FragmentCalculatedGoals
import br.com.rafaelfelipeac.coincoin.fragments.FragmentGoals
import br.com.rafaelfelipeac.coincoin.fragments.FragmentMain
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var price : Double = 0.0

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val manager = supportFragmentManager
                manager.beginTransaction().replace(R.id.frame_principal, FragmentMain(), "MAIN").commit()
                supportActionBar?.title = "CoinCoin"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                val manager = supportFragmentManager
                manager.beginTransaction().replace(R.id.frame_principal, FragmentGoals(), "GOALS").commit()
                supportActionBar?.title = "Metas"
                return@OnNavigationItemSelectedListener true
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

    fun replaceForCalculatedGoals() {
        val manager = supportFragmentManager
        manager.beginTransaction().replace(R.id.frame_principal, FragmentCalculatedGoals(), "CALCULATEDGOALS").commit()
        supportActionBar?.title = "Metas calculadas"
    }

    fun replaceForMain() {
        val manager = supportFragmentManager
        manager.beginTransaction().replace(R.id.frame_principal, FragmentMain(), "MAIN").commit()
        supportActionBar?.title = "CoinCoin"
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home)
            return false
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val fragmentGoals = supportFragmentManager.findFragmentByTag("GOALS")
        val fragmentCGoals = supportFragmentManager.findFragmentByTag("CALCULATEDGOALS")
        val fragmentMain = supportFragmentManager.findFragmentByTag("MAIN")

        if((fragmentGoals == null && fragmentCGoals == null && fragmentMain == null) || fragmentMain != null)
            super.onBackPressed()
        else if(fragmentGoals != null || fragmentCGoals != null)
            replaceForMain()
    }

    fun setPrice(price: Double) {
        this.price = price
    }

    fun getPrice(): Double {
        return this.price
    }
}
