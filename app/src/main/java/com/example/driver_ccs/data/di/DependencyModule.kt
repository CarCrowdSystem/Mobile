package com.example.driver_ccs.data.di

import com.example.driver_ccs.ui.cadastro.CadastroViewModel
import com.example.driver_ccs.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DependencyModule {
    val moduleApp = module {
        viewModel { LoginViewModel(get()) }
        viewModel { CadastroViewModel() }
    }
}