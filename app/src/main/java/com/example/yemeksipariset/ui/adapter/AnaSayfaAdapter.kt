package com.example.yemeksipariset.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yemeksipariset.R
import com.example.yemeksipariset.data.entity.Favoriler
import com.example.yemeksipariset.data.entity.Yemekler
import com.example.yemeksipariset.databinding.AnaCardTasarimBinding
import com.example.yemeksipariset.ui.fragment.AnaSayfaFragmentDirections
import com.example.yemeksipariset.ui.viewmodel.FavoriSayfaViewModel
import com.google.android.material.snackbar.Snackbar

class AnaSayfaAdapter(
    private val mContext: Context, var yemekListesi : List<Yemekler>,private val onFavorilerEkle: (Yemekler) -> Unit

) : RecyclerView.Adapter<AnaSayfaAdapter.AnaCardTasarimTutucu>() {



    inner class AnaCardTasarimTutucu(val tasarim: AnaCardTasarimBinding) :
        RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnaCardTasarimTutucu {
        val binding = DataBindingUtil.inflate<AnaCardTasarimBinding>(
            LayoutInflater.from(mContext),
            R.layout.ana_card_tasarim,
            parent,
            false
        )
        return AnaCardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: AnaCardTasarimTutucu, position: Int) {
        val yemek = yemekListesi[position]
        val y = holder.tasarim

        y.yemeklerNesnesi = yemek

        val resimADI = yemek.yemek_resim_adi.removeSuffix(".png")
        val resimID = mContext.resources.getIdentifier(resimADI, "drawable", mContext.packageName)
        y.imageViewAnaCardResim.setImageResource(resimID)

        y.imageViewAnaCardEkle.setOnClickListener {
            val gecis = AnaSayfaFragmentDirections.anadandetayagecis(yemek)
            Navigation.findNavController(it).navigate(gecis)
        }

        y.imageViewAnaCardFavButt.setOnClickListener {
          onFavorilerEkle(yemek)
        }


    }

    override fun getItemCount() : Int{

      return yemekListesi.size
    }

}

