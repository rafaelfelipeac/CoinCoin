package br.com.rafaelfelipeac.coincoin.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import br.com.rafaelfelipeac.coincoin.R
import br.com.rafaelfelipeac.coincoin.dao.MoneyDAO
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var salary: Float = Float.MIN_VALUE

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

            }
            R.id.navigation_dashboard -> {

            }
            R.id.navigation_notifications -> {

            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)

        if(MoneyDAO(this).Read().size > 0)
            salary = MoneyDAO(this).Read().get(0).value

        button.setOnClickListener { view ->
            val valor: Float = editText.text.toString().toFloat()

            textView2.text = ((valor / salary) * 100).toString() + "%"
        }

        btnNewSalary.setOnClickListener {view ->
            val intent = Intent(this, MoneyFormActivity::class.java)
            startActivity(intent)
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onResume() {

        if(MoneyDAO(this).Read().size > 0)
            salary = MoneyDAO(this).Read().get(0).value

        super.onResume()
    }
}
