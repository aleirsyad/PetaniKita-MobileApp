package com.c23ps419.petanikita.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.c23ps419.petanikita.data.remote.network.ApiService
import com.c23ps419.petanikita.data.remote.response.LoginResponse
import com.c23ps419.petanikita.data.remote.response.RegisterResponse

class DataRepository(private val apiService: ApiService) {
    fun postLogin(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val deviceInfo = android.os.Build.MANUFACTURER + android.os.Build.PRODUCT
            val response = apiService.userLogin(email, password, deviceInfo)
            emit(Result.Success(response))
        } catch (e: Exception){
            emit(Result.Error(e.message.toString()))
        }
    }

    fun postRegister(name: String, email: String, password: String): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.userRegister(name, email, password)
            emit(Result.Success(response))
        } catch (e: Exception){
            emit(Result.Error(e.message.toString()))
        }
    }
}