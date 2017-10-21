package br.com.rafaelfelipeac.coincoin.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.rafaelfelipeac.coincoin.R
import br.com.rafaelfelipeac.coincoin.dao.GoalDAO
import br.com.rafaelfelipeac.coincoin.model.Goal
import kotlinx.android.synthetic.main.activity_goal_form.*

class GoalFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_form)

        val goalDAO = GoalDAO(this)

        formGoal_btnSave.setOnClickListener { view ->
            val goal = Goal()
            goal.name = formGoal_editTextName.text.toString()
            goal.value = formGoal_editTextValue.text.toString().toFloat()

            val list = goalDAO.Read()

            goalDAO.Insert(goal)

            finish()
        }

    }
}