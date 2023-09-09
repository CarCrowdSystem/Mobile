package com.example.driver_ccs.extensions

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.driver_ccs.utils.FragmentViewBindingDelegate

inline fun <reified T : ViewBinding> Fragment.viewBinding() =
    FragmentViewBindingDelegate(T::class.java)