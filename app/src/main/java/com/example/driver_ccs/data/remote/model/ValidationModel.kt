package com.example.driver_ccs.data.remote.model

class ValidationModel(message: String = "") {

    private var status: Boolean = true
    private var message: String = ""

    init {
        if(message.isNotEmpty()) {
            this.message = message
            status = false
        }
    }

    fun showStatus() = status
    fun showMessage() = message
}