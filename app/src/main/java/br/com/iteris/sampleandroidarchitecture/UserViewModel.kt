package br.com.iteris.sampleandroidarchitecture

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var users: MutableLiveData<ArrayList<User>>

    fun getUsers(): MutableLiveData<ArrayList<User>> {
        if (!::users.isInitialized) {
            users = MutableLiveData()
            users.value = ArrayList()
        }
        return users
    }

    fun create(name: String) {
        val user = User(name)
        users.value!!.add(user)
    }
}