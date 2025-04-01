package com.example.yemeksipariset.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yemeksipariset.R
import com.example.yemeksipariset.databinding.FragmentSepetSayfaBinding
import com.example.yemeksipariset.ui.adapter.SepetAdapter
import com.example.yemeksipariset.ui.viewmodel.SepetSayfaViewModel
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.yemeksipariset.ui.adapter.SepettenSilListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetSayfaFragment : Fragment(),SepettenSilListener {
 private lateinit var tasarim : FragmentSepetSayfaBinding
    private val viewModel: SepetSayfaViewModel by viewModels()
 private lateinit var SepetAdapter:SepetAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

     tasarim = FragmentSepetSayfaBinding.inflate(inflater,container,false)


    viewModel.sepetiGetir("kaandev")

        viewModel.sepetListesi.observe(viewLifecycleOwner){liste ->

            SepetAdapter = SepetAdapter(requireContext(),liste,this)
         tasarim.rvSepet.adapter = SepetAdapter

            val ToplamTutar = liste.sumOf {
                val adet = it.yemek_siparis_adet.toIntOrNull() ?: 0
                adet * it.yemek_fiyat
            }
            tasarim.textViewFiyatToplam.text = "$ToplamTutar TL"
        }

        tasarim.buttonSepetOnayla.setOnClickListener {
            val gecis = SepetSayfaFragmentDirections.sepetdensiparisegcis()
            Navigation.findNavController(it).navigate(gecis)
        }



        tasarim.imageViewSepCarpi.setOnClickListener {
            val gecis = SepetSayfaFragmentDirections.actionSepetSayfaFragmentToAnaSayfaFragment()
            Navigation.findNavController(it).navigate(gecis)

        }








        return tasarim.root
    }
    override fun sepettenSil(sepetYemekId:Int){
        viewModel.sepettenSil(sepetYemekId,"kaandev")
    }

}