package com.example.yemeksipariset.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.yemeksipariset.R
import com.example.yemeksipariset.databinding.FragmentSiparisimBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SiparisimFragment : Fragment() {

    private lateinit var tasarim: FragmentSiparisimBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        tasarim = FragmentSiparisimBinding.inflate(inflater, container, false)

       tasarim.imageViewFavCarpi2.setOnClickListener {
           val gecis = SiparisimFragmentDirections.actionSiparisimFragmentToAnaSayfaFragment()
           Navigation.findNavController(it).navigate(gecis)
       }




        return tasarim.root


    }
}