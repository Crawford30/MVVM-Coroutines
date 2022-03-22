package com.example.mvvm_courotines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mvvm_courotines.models.User
import com.example.mvvm_courotines.repository.Repository

class MainViewModel : ViewModel() {
    //We dont need to declare view Model as singleton because they are going to be bound to activity or fragment

    private val _userId: MutableLiveData<String> = MutableLiveData()


    //Transformations::

    //SwitchMap->similar to RXJava. If the user id changes, the switchmap will trigger and returns a type of user*we are matching from string to a live data object by calling repository.get
    val user:LiveData<User> = Transformations
        .switchMap(_userId){ userId ->
            Repository.getUser(userId ) //pass the user id
        }

    fun setuserId(userId: String){
        val update =  userId

        //If the value hasnt changed, return the value
        if (_userId.value == update){
           return
        }

        //Else if it isnt, update
        _userId.value = update
    }

    fun cancelJobs() {
        Repository.cancelJobs()
    }
}