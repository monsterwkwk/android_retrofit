package com.example.androidretrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidretrofit.models.UserDataResponse
import com.example.androidretrofit.networks.UserNetworkRepository

class UserFormViewModel :ViewModel() {
    // get single data
    fun getUser(id: Int): MutableLiveData<UserDataResponse>{
        return UserNetworkRepository().getUser(id)
    }

    // create user
    fun createUser(userData: UserDataResponse)
            : MutableLiveData<UserDataResponse> {

        return UserNetworkRepository().createUser(userData)
    }
}