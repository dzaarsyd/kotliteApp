package com.brillante.kotlite.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.brillante.kotlite.data.local.entity.*
import com.brillante.kotlite.data.remote.model.detailorder.DetailOrderResponse
import com.brillante.kotlite.data.remote.model.detailpsg.DetailPsgResponse
import com.brillante.kotlite.data.remote.model.recomendation.RecommendationRequest
import com.brillante.kotlite.preferences.SessionManager
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng

interface DataSource {

    fun getDirection(from: String, to: String, map: GoogleMap): Unit

    fun login(
        username: String,
        password: String,
        sessionManager: SessionManager,
        context: Context
    ): LiveData<Boolean>

    fun createOrder(
        latLngStart: LatLng?,
        latLngEnd: LatLng?,
        time: String,
        capacity: Int,
        carType: String,
        context: Context,
        authHeader: String,
    ): LiveData<OrderEntity>

    fun getPsgList(orderId: Int, authHeader: String): LiveData<List<PassengerListEntity>>
    fun getAccPsgList(orderId: Int, authHeader: String): LiveData<List<PassengerListEntity>>
    fun patchAccPsg(id: Int, authHeader: String): LiveData<Boolean>
    fun getOnGoingRoute(orderId: Int, authHeader: String, map: GoogleMap)
    fun getDetailDriver(orderId: Int, authHeader: String): LiveData<DetailDriverEntity>

    //status
    fun patchPsgArrived(id: Int, authHeader: String): LiveData<Boolean>
    fun patchPsgStartRide(id: Int, authHeader: String): LiveData<Boolean>
    fun patchPsgCompleteRide(id: Int, authHeader: String): LiveData<Boolean>
    fun patchPsgDone(id: Int, authHeader: String): LiveData<Boolean>
    fun patchArriving(orderId: Int, authHeader: String): LiveData<Boolean>

    //passenger
    fun recommendation(
        request: RecommendationRequest,
        authHeader: String
    ): LiveData<RecommendationEntity>

    fun createPsg(
        request: PsgDataEntity,
        authHeader: String,
        orderId: Int
    ): LiveData<CreatePsgEntity>

    fun getDetailPsg(psgId: Int, authHeader: String): LiveData<DetailPsgResponse>
}