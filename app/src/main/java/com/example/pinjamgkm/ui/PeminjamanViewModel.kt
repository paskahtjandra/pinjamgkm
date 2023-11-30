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
import com.example.pinjamgkm.model.Peminjaman
import com.example.pinjamgkm.model.StatusRequest
import kotlinx.coroutines.launch

class PeminjamanViewModel(
    private val snackbarHostState: SnackbarHostState
) : ViewModel() {
    private val generalRepository = Repository()
    val peminjaman = MutableLiveData<List<Peminjaman>>()
    val peminjamanFiltered = MutableLiveData<List<Peminjaman>>()


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

    fun updateStatusPeminjaman(statusRequest: StatusRequest, peminjamanId: Int){
        viewModelScope.launch {
            snackbarHostState.showSnackbar("Loading")
            val response = generalRepository.updateStatus(statusRequest,peminjamanId)
            if (response.isSuccessful) {
                snackbarHostState.showSnackbar("Sukses Mengupdate Data")
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