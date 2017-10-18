package br.com.rafaelfelipeac.keepcoin.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.rafaelfelipeac.keepcoin.model.Goal

/**
 * Created by Rafael Felipe on 16/10/2017.
 */

class GoalDAO (context: Context?) : SQLiteOpenHelper(context, "Goal", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        val sql = "CREATE TABLE Goal (Id INTEGER PRIMARY KEY, Name TEXT, Value DECIMAL(19,4));"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, ov: Int, nv: Int) {

    }

    fun Insert(goal: Goal) {
        val db = writableDatabase
        val data = getContentValuesGoal(goal)
        db.insert("Goal", null, data)
    }

    fun Read() : List<Goal> {
        var goals  = ArrayList<Goal>()
        val sql = "SELECT * FROM Goal"
        val db = readableDatabase
        val cursor = db.rawQuery(sql, null)

        while(cursor.moveToNext()) {
            val goal = Goal()

            goal.id = cursor.getLong(cursor.getColumnIndex("Id"))
            goal.name = cursor.getString(cursor.getColumnIndex("Name"))
            goal.value = cursor.getFloat(cursor.getColumnIndex("Value"))

            goals.add(goal)
        }

        cursor.close()

        return goals
    }

    fun Remove(goal: Goal) {
        val db = writableDatabase

        if(goal.id.compareTo(0) != 0) {
            val sql = "SELECT * FROM Goal"
            val dbR = readableDatabase
            val c = dbR.rawQuery(sql, null)

            while(c.moveToNext())
                goal.id = c.getLong(c.getColumnIndex("Id"))
        }

        val params = arrayOf(goal.id.toString())

        db.delete("Goal", "id = ?", params)
    }

    fun Update(goal: Goal) {
        val db = writableDatabase
        val data = getContentValuesGoal(goal)
        val params = arrayOf(goal.id.toString())
        db.update("Goal", data, "id = ?", params)

    }


    private fun getContentValuesGoal(goal: Goal): ContentValues {
        val data = ContentValues()
        data.put("name", goal.name)
        data.put("value", goal.value)

        return data
    }


}