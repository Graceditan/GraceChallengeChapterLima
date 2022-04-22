package binar.and.lima.challengechapterlima.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import binar.and.lima.challengechapterlima.model.GetAllFilmResponseItem
import binar.and.lima.challengechapterlima.network.ApiClientFilm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmViewModel: ViewModel() {
    lateinit var livedatafilm : MutableLiveData<List<GetAllFilmResponseItem>>

    init {
        livedatafilm = MutableLiveData()
    }

    fun FilmObserver() : MutableLiveData<List<GetAllFilmResponseItem>>{
        return livedatafilm
    }
    fun FilmApi(){
        ApiClientFilm.instance.getAllFilm()
            .enqueue(object :Callback<List<GetAllFilmResponseItem>>{
                override fun onResponse(
                    call: Call<List<GetAllFilmResponseItem>>,
                    response: Response<List<GetAllFilmResponseItem>>
                ) {
                    if (response.isSuccessful){
                        livedatafilm.postValue(response.body())
                    }else{
                        livedatafilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<GetAllFilmResponseItem>>, t: Throwable) {
                    livedatafilm.postValue(null)
                }


            })
    }
}