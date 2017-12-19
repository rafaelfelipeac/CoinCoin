package br.com.rafaelfelipeac.coincoin.activities

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import br.com.rafaelfelipeac.coincoin.R
import br.com.rafaelfelipeac.coincoin.dao.GoalDAO
import br.com.rafaelfelipeac.coincoin.model.Goal
import kotlinx.android.synthetic.main.activity_goal_form.*

class GoalFormActivity : AppCompatActivity() {
    lateinit var goal: Goal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_form)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val goalDAO = GoalDAO(this)

        goal = if((intent.getSerializableExtra("goal") as Goal?) != null) (intent.getSerializableExtra("goal") as Goal) else Goal()

        if(goal != null) {
            formGoal_editTextName.setText(goal.name)
            formGoal_editTextValue.setText(goal.value.toString())
        }

        formGoal_btnSave.setOnClickListener { view ->
            goal.name = formGoal_editTextName.text.toString()
            goal.value = formGoal_editTextValue.text.toString().toFloat()

            closeKeyboard(this, formGoal_editTextName.windowToken)
            closeKeyboard(this, formGoal_editTextValue.windowToken)


            when {
                goal.name == "" -> Snackbar.make(view, "Nome inválido.", Snackbar.LENGTH_SHORT).show()
                goal.value <= 0 -> Snackbar.make(view, "Valor inválido.", Snackbar.LENGTH_SHORT).show()
                else -> {
                    if(goal.id.compareTo(0) == 0)
                        goalDAO.Insert(goal)
                    else
                        goalDAO.Update(goal)
                    finish()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

    private fun closeKeyboard(c: Context, windowToken: IBinder) {
        val mgr = c.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mgr.hideSoftInputFromWindow(windowToken, 0)
    }
}
