package com.example.yemeksipariset.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.yemeksipariset.R
import com.example.yemeksipariset.data.entity.Favoriler
import com.example.yemeksipariset.data.entity.Yemekler
import com.example.yemeksipariset.databinding.FragmentAnaSayfaBinding
import com.example.yemeksipariset.ui.adapter.AnaSayfaAdapter
import com.example.yemeksipariset.ui.viewmodel.AnaSayfaViewModel
import com.example.yemeksipariset.ui.viewmodel.FavoriSayfaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnaSayfaFragment : Fragment() {

    private lateinit var binding : FragmentAnaSayfaBinding
    private lateinit var AnaSayfaAdapter : AnaSayfaAdapter
    private lateinit var yemek : Yemekler
    private lateinit var favoriler : Favoriler
    private val viewModel: AnaSayfaViewModel by viewModels()
    private val FavViewModel: FavoriSayfaViewModel by viewModels()
    private var tumyemeklerListesi: List<Yemekler> = emptyList()
    private fun onFavClicked(yemek:Yemekler){
        val favori = Favoriler(yemek_id = yemek.yemek_id, yemek_adi = yemek.yemek_adi, yemek_resim_adi = yemek.yemek_resim_adi, yemek_fiyat = yemek.yemek_fiyat)
        FavViewModel.favoriEkle(favori)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_ana_sayfa,container,false)


        viewModel.TumyemeklerListesi.observe(viewLifecycleOwner) {
            tumyemeklerListesi = it
            AnaSayfaAdapter = AnaSayfaAdapter(requireContext(), it,::onFavClicked)
            binding.anaSayfaAdapter = AnaSayfaAdapter

        }

        binding.imageViewAnaSepet.setOnClickListener {
            val gecis = AnaSayfaFragmentDirections.anadansepetegecis()
            Navigation.findNavController(it).navigate(gecis)

        }

        binding.imageViewAnaSiparis.setOnClickListener {
            val gecis = AnaSayfaFragmentDirections.anadansiparislerimegecis()
            Navigation.findNavController(it).navigate(gecis)
        }

        binding.imageViewAnaFav.setOnClickListener {
            val gecis = AnaSayfaFragmentDirections.anadanfavagecis()
            Navigation.findNavController(it).navigate(gecis)

        }

        binding.searchViewAna.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filtreleYemekler(newText.orEmpty())
                return true
            }
        })





        return binding.root
    }

    private fun filtreleYemekler(arananKelime:String){
        val filtrelemeListe = viewModel.TumyemeklerListesi.value?.filter {
            it.yemek_adi.contains(arananKelime, ignoreCase = true)
        } ?: emptyList()

        AnaSayfaAdapter.yemekListesi = filtrelemeListe
        AnaSayfaAdapter.notifyDataSetChanged()

    }


}









