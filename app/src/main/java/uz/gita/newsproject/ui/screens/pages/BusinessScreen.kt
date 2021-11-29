package uz.gita.newsproject.ui.screens.pages

import android.os.Bundle
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
import uz.gita.newsproject.databinding.PageGeneralBinding
import uz.gita.newsproject.ui.adapters.RecyclerSourceAdapter
import uz.gita.newsproject.ui.viewModels.pages.BusinessScreenViewModel
import uz.gita.newsproject.ui.viewModels.pages.impl.BusinessScreenViewModelImpl

@AndroidEntryPoint
class BusinessScreen : Fragment(R.layout.page_general) {
    private val viewModel: BusinessScreenViewModel by viewModels<BusinessScreenViewModelImpl>()
    private val binding by viewBinding(PageGeneralBinding::bind)
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    private val adapter = RecyclerSourceAdapter()

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.generalRecycler.adapter = adapter
        binding.generalRecycler.layoutManager = LinearLayoutManager(requireContext())

        adapter.setFavouriteButtonListener { source ->
            viewModel.insert(source)
        }

        adapter.setListener { url ->
            val bundle = Bundle()
            bundle.putString("base_url", url)
            navController.navigate(R.id.sourceWebScreen, bundle)
        }

        viewModel.hideProgressBarLiveData.observe(viewLifecycleOwner, hideProgressBarObserver)
        viewModel.showProgressBarLiveData.observe(viewLifecycleOwner, showProgressBarObserver)
        viewModel.sourceNewsLiveData.observe(viewLifecycleOwner, sourceNewsObserver)
        viewModel.connectionLiveData.observe(viewLifecycleOwner, connectionObserver)

    }

    private val connectionObserver = Observer<Boolean> {
        if (binding.generalRecycler.adapter?.itemCount == 0)
            binding.warningLayout.visibility = if (it) View.GONE else View.VISIBLE
    }

    private val showProgressBarObserver = Observer<Unit> {
        binding.progressBar.show()
    }
    private val hideProgressBarObserver = Observer<Unit> {
        binding.progressBar.hide()
    }

    private val sourceNewsObserver = Observer<List<SourcesItem>> {
        adapter.submitList(it)
    }

}