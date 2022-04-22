package binar.and.lima.challengechapterlima.viewmodel

import androidx.lifecycle.MutableLiveData
import binar.and.lima.challengechapterlima.model.GetAllUserResponseItem
import binar.and.lima.challengechapterlima.network.ApiClientFilm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    lateinit var livedatauser : MutableLiveData<List<GetAllUserResponseItem>>

    init {
        livedatauser = MutableLiveData()
    }
    fun UserObserver() : MutableLiveData<List<GetAllUserResponseItem>>{
        return livedatauser
    }
    fun userApi(){
        ApiClientFilm.instance.getAllUser()
            .enqueue(object : Callback<List<GetAllUserResponseItem>>{
                override fun onResponse(
                    call: Call<List<GetAllUserResponseItem>>,
                    response: Response<List<GetAllUserResponseItem>>
                ) {
                    if (response.isSuccessful){
                        livedatauser.postValue(response.body())
                    }else{
                        livedatauser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<GetAllUserResponseItem>>, t: Throwable) {
                    livedatauser.postValue(null)
                }


            })
    }
    fun updateUser(id : Int, username : String, completeName : String, dateofbirth : String, adress: String){
        ApiClientFilm.instance.updateUser(id,username,completeName,dateofbirth, adress)
            .enqueue(object : Callback<List<GetAllUserResponseItem>>{
                override fun onResponse(
                    call: Call<List<GetAllUserResponseItem>>,
                    response: Response<List<GetAllUserResponseItem>>
                ) {
                    livedatauser.postValue(response.body())
                }

                override fun onFailure(call: Call<List<GetAllUserResponseItem>>, t: Throwable) {
                    livedatauser.postValue(null)
                }


            })

    }
}