package com.example.pinjamgkm.api

import com.example.pinjamgkm.model.BackendResponsePeminjaman
import com.example.pinjamgkm.model.BackendResponseRuangan
import com.example.pinjamgkm.model.DefaultSuccessPeminjamanResponse
import com.example.pinjamgkm.model.Peminjaman
import com.example.pinjamgkm.model.StatusRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("api/v1/allpeminjaman")
    suspend fun getPeminjamanList(): Response<BackendResponsePeminjaman>

    @GET("api/v1/peminjaman/ruangan")
    suspend fun getRuanganList(): Response<BackendResponseRuangan>

    @POST("api/v1/peminjaman/update/{peminjamanId}")
    suspend fun updateStatus(@Body requestBody: StatusRequest, @Path("peminjamanId") peminjamanId: Int): Response<DefaultSuccessPeminjamanResponse>

    @POST("api/v1/peminjaman/lockroom/{idRuangan}")
    suspend fun lockRoom(@Path("idRuangan") idRuangan: Int): Response<DefaultSuccessPeminjamanResponse>

    @POST("api/v1/peminjaman/unlockroom/{idRuangan}")
    suspend fun unlockRoom(@Path("idRuangan") idRuangan: Int): Response<DefaultSuccessPeminjamanResponse>

}