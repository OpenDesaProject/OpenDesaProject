package com.example.core.data.interactors

import com.example.core.data.source.Resource
import com.example.core.data.source.remote.model.request.ComplaintRequest
import com.example.core.data.source.remote.model.response.Complaint
import com.example.core.data.source.remote.network.SafeApiCall
import com.example.core.domain.base.execute
import com.example.core.domain.repository.ComplaintRepository
import com.example.core.domain.usecase.ComplaintUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class ComplaintInteractors(
  private val complaintRepository: ComplaintRepository
): ComplaintUseCase, SafeApiCall {
  override suspend fun getComplaintByUid(uid: String): Flow<Resource<List<Complaint>>> {
    return execute(Dispatchers.IO) {
      safeApiCall { complaintRepository.getComplaintByUid(uid) }
    }
  }

  override suspend fun postComplaint(complaintRequest: ComplaintRequest): Flow<Resource<Complaint>> {
    return execute(Dispatchers.IO) {
      safeApiCall { complaintRepository.postComplaint(complaintRequest) }
    }
  }

  override suspend fun removeComplaintById(id: String): Flow<Resource<Unit>> {
    return execute(Dispatchers.IO) {
      safeApiCall { complaintRepository.removeComplaintById(id) }
    }
  }
}