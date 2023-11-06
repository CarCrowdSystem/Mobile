package com.example.driver_ccs.data.remote.profile

import android.content.Context
import com.example.driver_ccs.data.remote.BaseRepository
import com.example.driver_ccs.data.remote.RetrofitClient
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.ProfileModel
import com.example.driver_ccs.data.remote.profile.network.IProfileService
import kotlinx.serialization.descriptors.PrimitiveKind

class ProfileRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(IProfileService::class.java)

    fun getProfilePicture(name: String, listener: ApiListener<Any>) {
        executeCall(remote.getDefaultPicture(name), listener )
    }

    fun updateProfile(nome: String, telefone: String, cpf: String, email: String, id: Int, listener: ApiListener<Unit>) {
        executeCall(remote.updateProfile(id, ProfileModel(nome, telefone, cpf, email)), listener)
    }
}