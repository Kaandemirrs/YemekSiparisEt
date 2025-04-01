package com.example.yemeksipariset.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yemeksipariset.data.entity.SepetYemek
import com.example.yemeksipariset.databinding.SepetCardTasarimBinding
import com.example.yemeksipariset.ui.adapter.SepettenSilListener
import com.google.android.material.snackbar.Snackbar

class SepetAdapter(var mContext:Context,var sepetListesi : List<SepetYemek>,private var silListener: SepettenSilListener) : RecyclerView.Adapter<SepetAdapter.SepetViewHolder>() {



    inner class SepetViewHolder(var tasarim:SepetCardTasarimBinding) :RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetViewHolder {
       val binding = SepetCardTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false,)
        return SepetViewHolder(binding)
    }


    override fun onBindViewHolder(holder: SepetViewHolder, position: Int) {
        val sepyemek = sepetListesi[position]
        val y = holder.tasarim

        y.sepetYemekNesnesi = sepyemek

        val resimAdi= sepyemek.yemek_resim_adi.removeSuffix(".png")
        val rsimId = mContext.resources.getIdentifier(resimAdi,"drawable",mContext.packageName)
        y.imageViewSepetcardResim.setImageResource(rsimId)

        y.imageViewCardSil.setOnClickListener {
            Snackbar.make(it,"${sepyemek.yemek_adi} silindi", Snackbar.LENGTH_SHORT).show()
      silListener.sepettenSil(sepyemek.sepet_yemek_id.toInt())
        }

        y.textView7.text = "${sepyemek.yemek_siparis_adet}"
    }

    override fun getItemCount(): Int {
      return  sepetListesi.size
    }



}