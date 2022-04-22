package binar.and.lima.challengechapterlima

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import binar.and.lima.challengechapterlima.adapter.Adapter_film
import binar.and.lima.challengechapterlima.viewmodel.FilmViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    lateinit var sphome : SharedPreferences
    lateinit var adapterfilmm : Adapter_film



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getFilmm()




        val viewww = inflater.inflate(R.layout.fragment_home,container,false)
        sphome = requireContext().getSharedPreferences("login", Context.MODE_PRIVATE)

        viewww.rv_userhome.layoutManager = LinearLayoutManager(requireContext())
        adapterfilmm = Adapter_film (){
            val bundleee = Bundle()
            bundleee.putParcelable("detailfilm", it)
            viewww.findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundleee)
        }

        viewww.rv_userhome.adapter = adapterfilmm

        val name = sphome.getString("username","")
        viewww.home_username.text = "Welcome $name"

        viewww.home_profile.setOnClickListener{
            viewww.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        return viewww
    }

    fun getFilmm(){
        val Vmdlhome = ViewModelProvider(requireActivity()).get(FilmViewModel::class.java)
        Vmdlhome.FilmObserver().observe(requireActivity()){
            if(it != null){
                adapterfilmm.setDataFilm(it)
                adapterfilmm.notifyDataSetChanged()
            }
        }

        Vmdlhome.FilmApi()

    }

}