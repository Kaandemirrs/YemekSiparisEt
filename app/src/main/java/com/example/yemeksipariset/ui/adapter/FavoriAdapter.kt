package com.example.yemeksipariset.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yemeksipariset.data.entity.Favoriler
import com.example.yemeksipariset.databinding.FavCardTasarimBinding

class FavoriAdapter(private val mContext:Context,private var favoriListesi:List<Favoriler>,private val onFavoriSil: (Favoriler) -> Unit) :
RecyclerView.Adapter<FavoriAdapter.FavoriViewHolder>()
{
    fun guncelleListe(yeniListe: List<Favoriler>){
        favoriListesi = yeniListe
        notifyDataSetChanged()

    }

    inner class FavoriViewHolder(val tasarım : FavCardTasarimBinding) :RecyclerView.ViewHolder(tasarım.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriViewHolder {
       val binding = FavCardTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return FavoriViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriViewHolder, position: Int) {
       val favoriler = favoriListesi[position]
        val y = holder.tasarım

        y.favNesnesi = favoriler



        val resimAdı = favoriler.yemek_resim_adi.removeSuffix(".png")
        val resimId = mContext.resources.getIdentifier(resimAdı,"drawable",mContext.packageName)
        y.imageViewFavCardResim.setImageResource(resimId)

        y.imageViewSil.setOnClickListener {
            onFavoriSil(favoriler)
        }
    }

    override fun getItemCount(): Int {
        return favoriListesi.size
    }





}