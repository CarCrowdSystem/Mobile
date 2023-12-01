package com.example.driver_ccs.data.remote.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ParkingLotLatLongResponseModel(
    @SerializedName("place_id")
    var place_id : Int,
    @SerializedName("lat")
    var lat : String,
    @SerializedName("lon")
    var long : String,
) : Serializable