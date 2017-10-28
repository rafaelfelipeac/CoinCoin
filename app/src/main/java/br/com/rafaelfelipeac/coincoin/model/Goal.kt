package br.com.rafaelfelipeac.coincoin.model

import java.io.Serializable

/**
 * Created by Rafael Felipe on 28/09/2017.
 */

class Goal(): Serializable{
    var id: Long = 0
    var name: String = ""
    var value: Float = 0.0f

    fun calculation(value2: Double?) : Double {
        return ((value2!! / value) * 100)
    }
}