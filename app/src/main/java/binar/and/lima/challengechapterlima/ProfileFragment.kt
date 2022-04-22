package binar.and.lima.challengechapterlima

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import binar.and.lima.challengechapterlima.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.custom_dialog.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    lateinit var spprofile: SharedPreferences
    lateinit var VMprofile: UserViewModel








    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val vieww = inflater.inflate(R.layout.fragment_profile, container, false)
        spprofile = requireContext().getSharedPreferences("login", Context.MODE_PRIVATE)

        vieww.profile_username.setText(spprofile.getString("username", "").toString())
        vieww.profile_namalengkap.setText(spprofile.getString("namalengkap", "").toString())
        vieww.profile_tanggallahir.setText(spprofile.getString("birth", "").toString())
        vieww.profile_alamat.setText(spprofile.getString("address", "").toString())

        vieww.btn_profile_update.setOnClickListener{

            val id = spprofile.getString("id","").toString()
            val username = vieww.profile_username.text.toString()
            val nl = vieww.profile_namalengkap.text.toString()
            val db = vieww.profile_tanggallahir.text.toString()
            val ad = vieww.profile_alamat.text.toString()
            val spp = spprofile.edit()
            spp.putString("username", username)
            spp.putString("namalengkap", nl)
            spp.putString("birth", db)
            spp.putString("address", ad)
            spp.apply()

            updateUser(id!!.toInt(), username,nl, db, ad)
            vieww.findNavController().navigate(R.id.action_profileFragment_to_homeFragment)



        }

        vieww.btn_profile_logout.setOnClickListener{
            val dialogg = LayoutInflater.from(requireContext()).inflate(R.layout.custom_dialog,null)
            val adl = AlertDialog.Builder(requireContext())
                .setView(dialogg)
                .create()

            dialogg.btn_customdialog_tidak.setOnClickListener{
                adl.dismiss()
            }

            dialogg.btn_customdialog_ya.setOnClickListener{
                val lg = spprofile.edit()

                for (key in spprofile.all.keys) {
                    if (key.startsWith("loginn")) {
                        lg.remove(key)
                    }
                }
                lg.commit()

                adl.dismiss()
                vieww.findNavController().navigate(R.id.action_profileFragment_to_loginFragment)

            }
            adl.show()



        }
        return  vieww

    }
    @SuppressLint("CommitPrefEdits")

    fun updateUser(id: Int, username : String, completename :String, dateofbirth : String, addres: String){
        VMprofile = ViewModelProvider(this).get(UserViewModel::class.java)
        VMprofile.UserObserver().observe(requireActivity(), Observer{
            if (it != null){
                Toast.makeText(requireContext(), "Gagal", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(requireContext(), "Berhasil", Toast.LENGTH_LONG).show()
            }
        })

        VMprofile.updateUser(id, username, completename, dateofbirth,addres )

    }








    //@SuppressLint("CommitPrefEdit")
}