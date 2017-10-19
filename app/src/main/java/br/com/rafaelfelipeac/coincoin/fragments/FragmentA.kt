package br.com.rafaelfelipeac.coincoin.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.rafaelfelipeac.coincoin.R
import br.com.rafaelfelipeac.coincoin.activities.MoneyFormActivity
import br.com.rafaelfelipeac.coincoin.dao.MoneyDAO
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.android.synthetic.main.fragment_a.view.*


/**
 * A simple [Fragment] subclass.
 */
class FragmentA : Fragment() {

    var salary: Float = Float.MIN_VALUE

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_a, container, false)

        if(MoneyDAO(context).Read().size > 0)
            salary = MoneyDAO(context).Read().get(0).value

        view.button.setOnClickListener { view ->
            val valor: Float = editText.text.toString().toFloat()

            textView2.text = ((valor / salary) * 100).toString() + "%"
        }

        view.btnNewSalary.setOnClickListener {view ->
            val intent = Intent(context, MoneyFormActivity::class.java)
            startActivity(intent)
        }


        // Inflate the layout for this fragment
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


