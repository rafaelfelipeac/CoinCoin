package br.com.rafaelfelipeac.keepcoin.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.rafaelfelipeac.keepcoin.R
import br.com.rafaelfelipeac.keepcoin.dao.MoneyDAO
import br.com.rafaelfelipeac.keepcoin.model.Money
import kotlinx.android.synthetic.main.activity_money_form.*
import kotlinx.android.synthetic.main.content_main.*

class MoneyFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_money_form)

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
}
