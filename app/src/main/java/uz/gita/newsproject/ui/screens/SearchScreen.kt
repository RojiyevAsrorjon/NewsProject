package uz.gita.newsproject.ui.screens

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsproject.R
import uz.gita.newsproject.data.models.responses.ArticlesItem
import uz.gita.newsproject.databinding.PageSearchBinding
import uz.gita.newsproject.ui.adapters.RecyclerSearchAdapter
import uz.gita.newsproject.ui.viewModels.SearchScreenViewModel
import uz.gita.newsproject.ui.viewModels.impl.SearchScreenViewModelImpl
import uz.gita.newsproject.utils.scope

@AndroidEntryPoint
class SearchScreen : Fragment(R.layout.page_search) {
    private val viewModel: SearchScreenViewModel by viewModels<SearchScreenViewModelImpl>()
    private val binding by viewBinding(PageSearchBinding::bind)
    private val adapter = RecyclerSearchAdapter()
    private val handler = Handler(Looper.getMainLooper())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.showProgressBarLiveData.observe(viewLifecycleOwner, showProgressBarObserver)
        viewModel.hideProgressBarLiveData.observe(viewLifecycleOwner, hideProgressBarObserver)
        viewModel.newsLiveData.observe(viewLifecycleOwner, searchNewsObserver)
        viewModel.openNetworkScreenLiveData.observe(viewLifecycleOwner, openNetworkObserver)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                handler.removeCallbacksAndMessages(null)
                query?.let {
                    viewModel.loadData(query.trim())
                    hideKeyBoard(requireContext(), searchView)
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        })
    }

    private val openNetworkObserver = Observer<Boolean> {
        binding.warningLayout.visibility = if (it) View.GONE else View.VISIBLE
    }

    private val searchNewsObserver = Observer<List<ArticlesItem>> {
        adapter.submitList(it)
    }

    private val showProgressBarObserver = Observer<Unit> {
        binding.progressBar.show()
    }
    private val hideProgressBarObserver = Observer<Unit> {
        binding.progressBar.hide()
    }
    private fun hideKeyBoard(context: Context, view: View) {
        val manager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}