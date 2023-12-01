package com.example.driver_ccs.extensions

import android.view.View
import android.widget.Button
import androidx.annotation.IntDef
import androidx.core.view.isVisible

@IntDef(View.GONE, View.INVISIBLE)
annotation class HideTypeDef

fun View.show() = run { this.visibility = View.VISIBLE }
fun View.hide(@HideTypeDef hideType: Int = View.GONE) = run { this.visibility = hideType }
fun View.toggle(value: Boolean = !this.isVisible, @HideTypeDef hideType: Int = View.GONE) =
    run { if (value) this.show() else this.hide(hideType) }
fun View.showOrHide(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}