package com.eneselcuk.connecttointernet.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.eneselcuk.connecttointernet.MarsApiFilter
import com.eneselcuk.connecttointernet.R
import com.eneselcuk.connecttointernet.databinding.FragmentDetailBinding
import com.eneselcuk.connecttointernet.databinding.FragmentOverviewBinding
import com.eneselcuk.connecttointernet.databinding.GridViewItemBinding
import com.eneselcuk.connecttointernet.overview.adapter.PhotoGridAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OverViewFragment : Fragment() {

    private lateinit var binding: FragmentOverviewBinding
    private val overViewModel: OverViewViewModel by activityViewModels()
    private lateinit var adapter: PhotoGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_overview,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        adapter = PhotoGridAdapter(PhotoGridAdapter.ClickListener { marsData ->
            overViewModel.displayPropertyDetails(marsData)
        })
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = overViewModel
            photosGrid.adapter = adapter
            setHasOptionsMenu(true)
        }
    }

    private fun setObserver() {
        overViewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, { marsData ->
            marsData?.let {
                val action =
                    OverViewFragmentDirections.actionOverViewFragmentToDetailFragment(marsData)
                findNavController().navigate(action)
            }
            overViewModel.displayPropertyDetailsComplete()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        overViewModel.updateFilter(
            when (item.itemId) {
                R.id.show_rent_menu -> MarsApiFilter.SHOW_RENT
                R.id.show_buy_menu -> MarsApiFilter.SHOW_BUY
                else -> MarsApiFilter.SHOW_ALL
            }
        )
        return true
    }

}