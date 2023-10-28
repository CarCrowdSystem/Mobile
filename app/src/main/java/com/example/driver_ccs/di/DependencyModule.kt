package com.example.driver_ccs.di

import android.content.Context
import android.content.SharedPreferences
import com.example.driver_ccs.ui.cadastro.CadastroViewModel
import com.example.driver_ccs.ui.login.LoginViewModel
import com.example.driver_ccs.ui.user_profile.UserProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DependencyModule {
    val moduleApp = module {
        viewModel { LoginViewModel(get()) }
        viewModel { CadastroViewModel(get()) }
        viewModel { UserProfileViewModel()}
    }
}