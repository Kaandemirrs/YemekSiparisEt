package com.example.yemeksipariset.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.navigation.NavArgs
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.yemeksipariset.R
import com.example.yemeksipariset.data.entity.Yemekler
import com.example.yemeksipariset.databinding.FragmentDetaySayfaBinding
import com.example.yemeksipariset.ui.viewmodel.AnaSayfaViewModel
import com.example.yemeksipariset.ui.viewmodel.SepetSayfaViewModel
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetaySayfaFragment : Fragment() {
    private lateinit var yemek : Yemekler
    private val viewModel: SepetSayfaViewModel by viewModels()

private lateinit var tasarim : FragmentDetaySayfaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

 tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_detay_sayfa,container,false)


val bundle: DetaySayfaFragmentArgs by navArgs()
        yemek = bundle.tumyemek
        tasarim.detayYemek = yemek

        val resimAdi = yemek.yemek_resim_adi.removeSuffix(".png")
        val resimId = resources.getIdentifier(resimAdi,"drawable",requireContext().packageName)
         tasarim.imageViewDetResim.setImageResource(resimId)


        tasarim.buttonDetSepetteEkle.setOnClickListener {

            Snackbar.make(it,"${yemek.yemek_adi} sepete eklendi",Snackbar.LENGTH_SHORT).show()


            viewModel.sepetteEkleAkilli(yemek, yeniAdet = 1,"kaandev")

        }


//detay fragmentden veriyi almadık



        return tasarim.root
    }

}