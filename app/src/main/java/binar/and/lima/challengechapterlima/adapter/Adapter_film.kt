package binar.and.lima.challengechapterlima.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import binar.and.lima.challengechapterlima.R
import binar.and.lima.challengechapterlima.model.GetAllFilmResponseItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_film.view.*

class Adapter_film (private var onclick : (GetAllFilmResponseItem)->Unit ): RecyclerView.Adapter<Adapter_film.ViewHolder>() {

    private var datafilm : List<GetAllFilmResponseItem>? = null

    fun setDataFilm(film : List<GetAllFilmResponseItem>){
        this.datafilm = film
    }

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter_film.ViewHolder {
        val itemview = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film, parent, false)
        return  ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: Adapter_film.ViewHolder, position: Int) {
        holder.itemView.tv_judulfilm.text = datafilm!![position].title
        holder.itemView.tv_tglfilm.text = datafilm!![position].releaseDate
        holder.itemView.tv_sutradarafilm.text = datafilm!![position].director
        Glide.with(holder.itemView.context).load(datafilm!![position].image).into(holder.itemView.img_film)

        holder.itemView.cardFilm.setOnClickListener {
            onclick(datafilm!![position])
        }
    }

    override fun getItemCount(): Int {
        if (datafilm == null){
            return 0
        }else{
            return  datafilm!!.size
        }
    }

}