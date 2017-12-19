package br.com.rafaelfelipeac.coincoin.fragments

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import br.com.rafaelfelipeac.coincoin.R
import br.com.rafaelfelipeac.coincoin.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*


class FragmentMain : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_main, container, false)
        view.button.setOnClickListener { view ->

            var value: Double = if(editText.text.toString() != "") editText.text.toString().toDouble() else 0.toDouble()

            closeKeyboard(activity, editText.windowToken)

            if(value > 0) {
                (activity as MainActivity).setPrice(value)
                (activity as MainActivity).replaceForCalculatedGoals()
            }
            else {
                Snackbar.make(view, "Valor inválido.", Snackbar.LENGTH_SHORT).show()
            }
        }

        view.editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) { }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textView2.text = ""
            }
        })

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        super.onCreate(savedInstanceState)
    }

    private fun closeKeyboard(c: Context, windowToken: IBinder) {
        val mgr = c.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mgr.hideSoftInputFromWindow(windowToken, 0)
    }
}


