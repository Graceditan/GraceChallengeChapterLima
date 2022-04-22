package binar.and.lima.challengechapterlima

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import binar.and.lima.challengechapterlima.model.GetAllUserResponseItem
import binar.and.lima.challengechapterlima.model.RegisterResponse
import binar.and.lima.challengechapterlima.network.ApiClientFilm
import binar.and.lima.challengechapterlima.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {
    lateinit var email: String
    lateinit var spregist: SharedPreferences
    lateinit var dataUser: List<GetAllUserResponseItem>
    lateinit var viewModel: UserViewModel
    lateinit var password: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_register, container, false)
        val registt = view.findViewById<Button>(R.id.btn_register)
        spregist = requireContext().getSharedPreferences("login", Context.MODE_PRIVATE)


        btn_register.setOnClickListener {
            val username = register_username.text.toString()
            email = register_email.text.toString()
            password = register_password.text.toString()
            val konfirmpass = register_konfirmasipassword.text.toString()
            if (password == konfirmpass) {
                val edit = spregist.edit()
                edit.putString("username", username)
                edit.apply()
                regisUser(username, email, password)
                view?.findNavController()
                    ?.navigate(R.id.action_registerFragment_to_loginFragment)

            } else {

                Toast.makeText(requireContext(), "Password Tidak Sama", Toast.LENGTH_SHORT).show()

            }

        }
        return view

    }
//    fun getDataUserItem(){
//        viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
//        viewModel.getLiveUserObserver().observe(viewLifecycleOwner, Observer {
//            dataUser = it
//            check(dataUser)
//
//        })
//        viewModel.userApi()
//    }

    fun regisUser(username: String, email: String, password: String) {
        ApiClientFilm.instance.register(username, email, password)
            .enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(requireContext(), "sukses", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), response.message(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {

                }
            })
    }
}