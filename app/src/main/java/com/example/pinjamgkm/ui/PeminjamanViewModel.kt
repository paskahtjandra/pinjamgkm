package com.example.pinjamgkm.ui

import android.adservices.adid.AdId
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pinjamgkm.api.Repository
import com.example.pinjamgkm.api.RetrofitInstance
import com.example.pinjamgkm.model.Peminjaman
import com.example.pinjamgkm.model.StatusRequest
import kotlinx.coroutines.launch
import java.util.concurrent.TimeoutException
import com.example.pinjamgkm.api.Result
import com.example.pinjamgkm.model.Ruangan

class PeminjamanViewModel(
    private val snackbarHostState: SnackbarHostState
) : ViewModel() {
    private val generalRepository = Repository()
    val peminjaman = MutableLiveData<List<Peminjaman>>()
    val peminjamanFiltered = MutableLiveData<List<Peminjaman>>()
    val ruangan = MutableLiveData<List<Ruangan>>()

    fun fetchPeminjaman() {
        viewModelScope.launch {
            snackbarHostState.showSnackbar("Loading")
            val response = generalRepository.getPeminjamans()
            if (response.isSuccessful) {
                val responsePeminjaman = response.body()?.peminjamans
                peminjaman.postValue(responsePeminjaman?.map { it })
                peminjamanFiltered.postValue(responsePeminjaman?.map { it })
                snackbarHostState.showSnackbar("Sukses Mengambil Data")
            }
        }
    }

    fun fetchRuangan() {
        viewModelScope.launch {
            snackbarHostState.showSnackbar("Menghubungi Kunci Ruangan")
            val response = generalRepository.getRuangans()
            if (response.isSuccessful) {
                val responseRuangan = response.body()?.ruangans
                ruangan.postValue(responseRuangan?.map { it })
                snackbarHostState.showSnackbar("Sukses Terhubung")
            }
        }
    }

    fun updateStatusPeminjaman(statusRequest: StatusRequest, peminjamanId: Int, onFinish: () -> Unit){
        viewModelScope.launch {
            snackbarHostState.showSnackbar("Sedang Mengupdate Status")
            val response = generalRepository.updateStatus(statusRequest,peminjamanId)
            if (response.isSuccessful) {
                onFinish()
                snackbarHostState.showSnackbar("Sukses Mengupdate Data")
            }
        }
    }

    fun lockRoom(ruanganId: Int, onFinish: () -> Unit){
        viewModelScope.launch {
            snackbarHostState.showSnackbar("Sedang Mengunci Ruangan")

            val result = RetrofitInstance.safeApiCall {
                generalRepository.lockRoom(ruanganId)
            }

            when (result) {
                is Result.Success -> {
                    onFinish()
                    snackbarHostState.showSnackbar("Sukses Mengunci Ruangan")
                }

                is Result.Error -> {
                    val errorMessage = result.message
                    val exception = result.exception
                    if (exception is TimeoutException) {
                        // Handle timeout-specific behavior
                        snackbarHostState.showSnackbar("Request timeout: $errorMessage")
                    } else {
                        // Handle other errors
                        snackbarHostState.showSnackbar("Gagal Mengunci Ruangan")
                    }
                    onFinish()
                }

                else -> {}
            }
        }
    }

    fun unlockRoom(ruanganId: Int, onFinish: () -> Unit){
        viewModelScope.launch {
            snackbarHostState.showSnackbar("Sedang Membuka Ruangan")
            val result = RetrofitInstance.safeApiCall {
                generalRepository.unlockRoom(ruanganId)
            }

            when (result) {
                is Result.Success -> {
                    onFinish()
                    snackbarHostState.showSnackbar("Sukses Membuka Ruangan")
                }

                is Result.Error -> {
                    val errorMessage = result.message
                    val exception = result.exception
                    if (exception is TimeoutException) {
                        // Handle timeout-specific behavior
                        snackbarHostState.showSnackbar("Request timeout: $errorMessage")
                    } else {
                        // Handle other errors
                        snackbarHostState.showSnackbar("Gagal Membuka Ruangan")
                    }
                    onFinish()
                }

                else -> {}
            }
        }
    }
}

class PeminjamanViewModelFactory(
    private val snackbarHostState: SnackbarHostState
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PeminjamanViewModel::class.java)) {
            return PeminjamanViewModel( snackbarHostState) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}