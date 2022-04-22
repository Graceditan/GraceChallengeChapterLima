package binar.and.lima.challengechapterlima

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import binar.and.lima.challengechapterlima.dataclass.User
import binar.and.lima.challengechapterlima.model.GetAllUserResponseItem
import binar.and.lima.challengechapterlima.model.LoginResponse
import binar.and.lima.challengechapterlima.network.ApiClientFilm
import binar.and.lima.challengechapterlima.viewmodel.FilmViewModel
import binar.and.lima.challengechapterlima.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {
    lateinit var splogin : SharedPreferences
    lateinit var datauser : List<GetAllUserResponseItem>
    lateinit var emaillogin : String
    lateinit var password : String
    lateinit var Vmlogin : UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val vieww = inflater.inflate(R.layout.fragment_login, container,false)
        splogin = requireContext().getSharedPreferences("login", Context.MODE_PRIVATE)

        DataUserItem()

      // val regist = vieww.findViewById<TextView>(R.id.login_register)
      val login = vieww.findViewById<Button>(R.id.btnLogin)


      //  vieww.login_register.setOnClickListener{
        //vieww.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        //}

      //  login_register.setOnClickListener{
       //     vieww.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)

       // }

       // login_register.setOnClickListener{
        //    Navigation.findNavController(vieww!!).navigate(R.id.action_loginFragment_to_registerFragment)}

        login.setOnClickListener{
            if (login_email.text.isNotEmpty() && login_password.text.isNotEmpty()){
                emaillogin = login_email.text.toString()
                password = login_password.text.toString()

                periksa(datauser)
            }else{
                Toast.makeText(requireContext(), "Email atau Password Belum diisi",Toast.LENGTH_LONG).show()
            }
        }



        return vieww

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)
      login_register.setOnClickListener{
           Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment)
       }
    }

    fun DataUserItem(){
        Vmlogin = ViewModelProvider(this).get(UserViewModel::class.java)
        Vmlogin.UserObserver().observe(viewLifecycleOwner, Observer {
            datauser = it
        } )
        Vmlogin.userApi()

      //  val Vmdlhome = ViewModelProvider(requireActivity()).get(FilmViewModel::class.java)
       // Vmdlhome.FilmObserver().observe(requireActivity()){
       //     if(it != null){
        //        adapterfilmm.setDataFilm(it)
         //       adapterfilmm.notifyDataSetChanged()
         //   }
       // }

      //  Vmdlhome.FilmApi()

    }

    fun periksa(datauser : List<GetAllUserResponseItem>){
       login(emaillogin,password)
        for (i in datauser.indices){
            if (emaillogin == datauser[i].email && password == datauser[i].password){
                val loginnn = "true"

                val sppp = splogin.edit()
                sppp.putString("id", datauser[i].id)
                sppp.putString("email", datauser[i].email)
                sppp.putString("username", datauser[i].username)
                sppp.putString("namalengkap", datauser[i].completeName)
                sppp.putString("birth", datauser[i].dateofbirth)
                sppp.putString("address", datauser[i].dateofbirth)
                sppp.putString("login", loginnn)
                sppp.apply()
                view?.findNavController()
                ?.navigate(R.id.action_loginFragment_to_homeFragment)
            }

        }
    }

    fun login (email : String, password : String){
        ApiClientFilm.instance.login(email,password).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){
                    Toast.makeText(requireContext(), "Berhasil", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(requireContext(), "Email atau Paaaword Salah", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

            }


        } )
    }

}



