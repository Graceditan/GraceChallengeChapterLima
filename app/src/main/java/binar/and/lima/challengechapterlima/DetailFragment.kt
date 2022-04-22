package binar.and.lima.challengechapterlima

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import binar.and.lima.challengechapterlima.model.GetAllFilmResponseItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*


class DetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vieww = inflater.inflate(R.layout.fragment_detail, container, false)
        val getfilm = arguments?.getParcelable<GetAllFilmResponseItem>("detailfilm")

        vieww.detail_title.text = getfilm?.title
        vieww.detail_director.text = getfilm?.director
        vieww.detail_tanggal.text = getfilm?.createdAt
        vieww.detail_keterangan.text = getfilm?.synopsis

        Glide.with(requireContext()).load(getfilm?.image).into(vieww.detail_image)

        return vieww
    }
}
