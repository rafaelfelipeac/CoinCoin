package br.com.rafaelfelipeac.coincoin.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import br.com.rafaelfelipeac.coincoin.R
import br.com.rafaelfelipeac.coincoin.dao.MoneyDAO
import br.com.rafaelfelipeac.coincoin.model.Money
import kotlinx.android.synthetic.main.activity_money_form.*

class MoneyFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_money_form)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        formMoney_btnSave.setOnClickListener { view ->
            val dao = MoneyDAO(this)

            var money = Money()
            money.value = formMoney_editTextNumber.text.toString().toFloat()

            val list = dao.Read()

            for(i in list)
                dao.Remove(i)

            dao.Insert(money)

            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
