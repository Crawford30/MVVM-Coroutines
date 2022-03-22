package com.example.mvvm_courotines.repository

import androidx.lifecycle.LiveData
import com.example.mvvm_courotines.api.MyRetrofitBuilder
import com.example.mvvm_courotines.models.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {
    var job: CompletableJob? = null

    fun getUser(userId: String): LiveData<User>{
        job = Job()

        return object :LiveData<User>(){
            //When this life object becomes, I wanna get this value
            override fun onActive() {
                super.onActive()
                //check if job is not null
                job?.let { theJob ->
                    //IO if we work on background thread
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.getUser(userId)

                        //NB: we cant set live data value on the background thread, we use post value
                        //value = user //This will crush

                        withContext(Main){ //Switch to main thread
                            value = user
                            theJob.complete()
                        }
                    } //creates new routine that references the job
                }


            }
        }

    }

    fun cancelJobs(){
        //When the activity is destroyed, shall cancel the job
        //If  the job is not null
        job?.cancel()
    }
}