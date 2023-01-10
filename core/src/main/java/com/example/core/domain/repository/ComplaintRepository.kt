package com.example.core.domain.repository

import com.example.core.data.source.remote.model.request.ComplaintRequest
import com.example.core.data.source.remote.model.response.Complaint

interface ComplaintRepository {
  suspend fun getComplaintByUid(uid: String): List<Complaint>
  suspend fun postComplaint(complaintRequest: ComplaintRequest): Complaint
  suspend fun removeComplaintById(id: String)
}