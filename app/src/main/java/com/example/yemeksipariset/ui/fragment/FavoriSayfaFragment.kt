package com.example.yemeksipariset.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.yemeksipariset.R
import com.example.yemeksipariset.data.entity.Favoriler
import com.example.yemeksipariset.databinding.FragmentFavoriSayfaBinding
import com.example.yemeksipariset.ui.adapter.FavoriAdapter
import com.example.yemeksipariset.ui.viewmodel.FavoriSayfaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriSayfaFragment : Fragment() {
  private lateinit var tasarim : FragmentFavoriSayfaBinding
  private val viewModel: FavoriSayfaViewModel by viewModels()
  private lateinit var adapter:FavoriAdapter
  private lateinit var favorolistsi:Favoriler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
 tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_favori_sayfa,container,false)


        tasarim.imageViewFavCarpi.setOnClickListener {
            val gecis = FavoriSayfaFragmentDirections.actionFavoriSayfaFragmentToAnaSayfaFragment()
            Navigation.findNavController(it).navigate(gecis)
        }


        return tasarim.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.favoriYukle()

        viewModel.favoriListesi.observe(viewLifecycleOwner){ liste ->
            adapter = FavoriAdapter(requireContext(),liste){silinecekyemek ->
                viewModel.favoriSil(silinecekyemek)

            }
            tasarim.rvFav.adapter = adapter

        }
        super.onViewCreated(view, savedInstanceState)
    }





}