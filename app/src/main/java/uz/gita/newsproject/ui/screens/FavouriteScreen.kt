package uz.gita.newsproject.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsproject.R
import uz.gita.newsproject.data.models.responses.SourcesItem
import uz.gita.newsproject.databinding.PageFavouriteBinding
import uz.gita.newsproject.ui.adapters.RecyclerSourceSaveAdapter
import uz.gita.newsproject.ui.viewModels.FavouriteScreenViewModel
import uz.gita.newsproject.ui.viewModels.impl.FavouriteScreenViewModelImpl
import uz.gita.newsproject.utils.scope

@AndroidEntryPoint
class FavouriteScreen : Fragment(R.layout.page_favourite) {
    private val binding by viewBinding(PageFavouriteBinding::bind)
    private val viewModel : FavouriteScreenViewModel by viewModels<FavouriteScreenViewModelImpl>()
    private val adapter = RecyclerSourceSaveAdapter()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope{
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        adapter.setListener { url->
            val bundle = Bundle()
            bundle.putString("base_url", url)
            navController.navigate(R.id.sourceWebScreen, bundle)
        }

        adapter.setFavouriteButtonListener { source ->
            viewModel.delete(source)
        }

        viewModel.newsListLiveData.observe(viewLifecycleOwner, listObserver)
    }

    private val listObserver = Observer<List<SourcesItem>>{
        adapter.submitList(it)
        Log.d("TTT", "List size : ${it.size}")
    }
}