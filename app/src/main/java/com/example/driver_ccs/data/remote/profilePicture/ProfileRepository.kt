package com.example.driver_ccs.data.remote.profilePicture

import android.content.Context
import com.example.driver_ccs.data.remote.BaseRepository
import com.example.driver_ccs.data.remote.RetrofitClient
import com.example.driver_ccs.data.remote.listener.ApiListener
import com.example.driver_ccs.data.remote.model.CarResponseModel
import com.example.driver_ccs.data.remote.profilePicture.network.IProfileService

class ProfileRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(IProfileService::class.java)

    fun getProfilePicture(name: String, listener: ApiListener<Any>) {
        executeCall(remote.getDefaultPicture(name), listener )
    }
}