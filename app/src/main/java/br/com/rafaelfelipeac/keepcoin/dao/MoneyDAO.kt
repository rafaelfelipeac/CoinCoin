package br.com.rafaelfelipeac.keepcoin.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.rafaelfelipeac.keepcoin.model.Goal
import br.com.rafaelfelipeac.keepcoin.model.Money

/**
 * Created by Rafael Felipe on 16/10/2017.
 */


class MoneyDAO (context: Context?) : SQLiteOpenHelper(context, "Money", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        val sql = "CREATE TABLE Money (Id INTEGER PRIMARY KEY, Value DECIMAL(19,4));"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, ov: Int, nv: Int) {

    }

    fun Insert(money: Money) {
        val db = writableDatabase
        val data = getContentValuesGoal(money)
        db.insert("Money", null, data)
    }

    fun Read() : List<Money> {
        var moneyL  = ArrayList<Money>()
        val sql = "SELECT * FROM Money"
        val db = readableDatabase
        val cursor = db.rawQuery(sql, null)

        while(cursor.moveToNext()) {
            val money = Money()

            money.id = cursor.getLong(cursor.getColumnIndex("Id"))
            money.value = cursor.getFloat(cursor.getColumnIndex("Value"))

            moneyL.add(money)
        }

        cursor.close()

        return moneyL
    }

    fun Remove(money: Money) {
        val db = writableDatabase

        if(money.id.compareTo(0) != 0) {
            val sql = "SELECT * FROM Money"
            val dbR = readableDatabase
            val c = dbR.rawQuery(sql, null)

            while(c.moveToNext())
                money.id = c.getLong(c.getColumnIndex("Id"))
        }

        val params = arrayOf(money.id.toString())

        db.delete("Money", "id = ?", params)
    }

    fun Update(money: Money) {
        val db = writableDatabase
        val data = getContentValuesGoal(money)
        val params = arrayOf(money.id.toString())
        db.update("Money", data, "id = ?", params)

    }


    private fun getContentValuesGoal(money: Money): ContentValues {
        val data = ContentValues()
        data.put("value", money.value)

        return data
    }


}