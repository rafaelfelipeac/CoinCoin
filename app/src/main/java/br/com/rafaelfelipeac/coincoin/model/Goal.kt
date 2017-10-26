package br.com.rafaelfelipeac.coincoin.model

/**
 * Created by Rafael Felipe on 28/09/2017.
 */

class Goal() {
    var id: Long = 0
    var name: String = ""
    var value: Float = 0.0f

    fun calculation(value2: Double?) : Double {
        return ((value2!! / value) * 100)
    }
}