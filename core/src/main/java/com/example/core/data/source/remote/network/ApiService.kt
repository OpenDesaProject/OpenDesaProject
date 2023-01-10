package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.model.request.ComplaintRequest
import com.example.core.data.source.remote.model.response.Complaint
import retrofit2.http.*

interface ApiService {

    @POST("complaints")
    suspend fun postComplaint(
        @Body complaintRequest: ComplaintRequest
    ): Complaint

    @GET("complaints/user/{uid}")
    suspend fun getComplaintByUid(
        @Path("uid") id: String
    ): List<Complaint>

    @DELETE("complaints/{id}")
    suspend fun removeComplaintById(
        @Path("id") id: String
    ): Unit

}