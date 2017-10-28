package br.com.rafaelfelipeac.coincoin.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import br.com.rafaelfelipeac.coincoin.R
import br.com.rafaelfelipeac.coincoin.dao.GoalDAO
import br.com.rafaelfelipeac.coincoin.model.Goal
import kotlinx.android.synthetic.main.activity_goal_form.*
import kotlinx.android.synthetic.main.activity_goal_form.view.*

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

            if(goal.name.equals(""))
                Toast.makeText(this, "Nome inválido.", Toast.LENGTH_SHORT).show()
            else if(goal.value <= 0)
                Toast.makeText(this, "Valor inválido.", Toast.LENGTH_SHORT).show()
            else {
                if(goal.id.compareTo(0) == 0)
                    goalDAO.Insert(goal)
                else
                    goalDAO.Update(goal)

                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
