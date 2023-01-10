package com.example.core.data.repository

import com.example.core.data.source.remote.model.request.ComplaintRequest
import com.example.core.data.source.remote.model.response.Complaint
import com.example.core.data.source.remote.network.ApiService
import com.example.core.domain.repository.ComplaintRepository

class ComplaintRepositoryImpl(private val apiService: ApiService): ComplaintRepository {
  override suspend fun getComplaintByUid(uid: String): List<Complaint> {
    return apiService.getComplaintByUid(uid)
  }

  override suspend fun postComplaint(complaintRequest: ComplaintRequest): Complaint {
    return apiService.postComplaint(complaintRequest)
  }

  override suspend fun removeComplaintById(id: String) {
    return apiService.removeComplaintById(id)
  }

}