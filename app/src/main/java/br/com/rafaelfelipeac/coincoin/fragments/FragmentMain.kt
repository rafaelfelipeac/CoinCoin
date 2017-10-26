package br.com.rafaelfelipeac.coincoin.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.rafaelfelipeac.coincoin.R
import br.com.rafaelfelipeac.coincoin.activities.MainActivity
import br.com.rafaelfelipeac.coincoin.activities.MoneyFormActivity
import br.com.rafaelfelipeac.coincoin.dao.MoneyDAO
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*


/**
 * A simple [Fragment] subclass.
 */
class FragmentMain : Fragment() {

    var salary: Float = Float.MIN_VALUE

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_main, container, false)

        if(MoneyDAO(context).Read().size > 0)
            salary = MoneyDAO(context).Read().get(0).value

        view.button.setOnClickListener { view ->

            var valor: Double = if(!editText.text.toString().equals("")) editText.text.toString().toDouble() else 0.toDouble()

            if(valor > 0) {
                //textView2.text = ((valor / salary) * 100).toString() + "%"
                (activity as MainActivity).setPrice(valor)
                btnCalculateGoals.visibility = View.VISIBLE
                btnCalculateGoals.isClickable = true

                btnCalculateGoals.setOnClickListener { view ->
                    (activity as MainActivity).replaceForCalculatedGoals()
                }
            }
        }

        view.btnNewSalary.setOnClickListener {view ->
            val intent = Intent(context, MoneyFormActivity::class.java)
            startActivity(intent)
        }

        view.editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btnCalculateGoals.visibility = View.INVISIBLE
                textView2.text = ""
            }
        })

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }

    override fun onResume() {

        if(MoneyDAO(context).Read().size > 0)
            salary = MoneyDAO(context).Read().get(0).value

        super.onResume()
    }
}


