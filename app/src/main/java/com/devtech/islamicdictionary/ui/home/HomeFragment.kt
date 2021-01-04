package com.devtech.islamicdictionary.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.devtech.islamicdictionary.R
import com.devtech.islamicdictionary.adapter.home.HomeAdapter
import com.devtech.islamicdictionary.data.local.entity.Menu
import com.devtech.islamicdictionary.databinding.FragmentHomeBinding
import com.devtech.islamicdictionary.utils.snackbar
import com.devtech.islamicdictionary.utils.toogleActionbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).toogleActionbar(
            show = true,
            back = false,
            title = resources.getString(R.string.app_name)
        )

        adapter = HomeAdapter()
        val sGrid = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        sGrid.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        binding.rvMenu.layoutManager = sGrid
        binding.rvMenu.isNestedScrollingEnabled = false
        binding.rvMenu.adapter = adapter
        setHasOptionsMenu(true)
        fetchMenu()
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                findNavController().navigate(R.id.action_homeFragment_to_settingFragment)
            }
            R.id.help ->
                binding.root.snackbar("Sedang perbaikan")
        }
        return false
    }

    private fun fetchMenu() {
        val listMenu: ArrayList<Menu> = ArrayList()
        listMenu.add(
            Menu(
                "Fiqih", R.drawable.ic_fiqih, 0, 1, R.id.action_homeFragment_to_dictionaryFragment
            )
        )
        listMenu.add(
            Menu(
                "Qur'an Hadits", R.drawable.ic_hadist, 0, 1, R.id.action_homeFragment_to_dictionaryFragment
            )
        )
        listMenu.add(
            Menu(
                "Akidah Akhlak", R.drawable.ic_aqidah, 0, 1, R.id.action_homeFragment_to_dictionaryFragment
            )
        )
        listMenu.add(
            Menu(
                "Tarikh Islam", R.drawable.ic_tarikh, 0, 1, R.id.action_homeFragment_to_dictionaryFragment
            )
        )
        listMenu.add(
            Menu(
                "Filsafat", R.drawable.ic_filsafat, 0, 1, R.id.action_homeFragment_to_dictionaryFragment
            )
        )
        listMenu.add(
            Menu(
                "Lainnya", R.drawable.ic_lainnya, 0, 1, R.id.action_homeFragment_to_dictionaryFragment
            )
        )
        adapter.replaceAll(listMenu)
    }
}