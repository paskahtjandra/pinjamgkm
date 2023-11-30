package com.example.pinjamgkm.api

import com.example.pinjamgkm.api.RetrofitInstance.apiService
import com.example.pinjamgkm.model.StatusRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

class Repository() {
    suspend fun getPeminjamans() = apiService.getPeminjamanList()
    suspend fun updateStatus(requestBody: StatusRequest, peminjamanId: Int) = apiService.updateStatus(requestBody, peminjamanId)
    suspend fun lockRoom(idRuangan: Int) = apiService.lockRoom(idRuangan)
    suspend fun unlockRoom(idRuangan: Int) = apiService.unlockRoom(idRuangan)
}