package uz.gita.newsproject.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.newsproject.R
import uz.gita.newsproject.databinding.ScreenNetworkBinding
import uz.gita.newsproject.ui.viewModels.NetworkScreenViewModel
import uz.gita.newsproject.ui.viewModels.impl.NetworkScreenViewModelImpl
import uz.gita.newsproject.utils.scope

class NetworkScreen : Fragment(R.layout.screen_network) {
    private val viewModel: NetworkScreenViewModel by viewModels<NetworkScreenViewModelImpl>()
    private val binding by viewBinding(ScreenNetworkBinding::bind)
    private val navController by lazy(LazyThreadSafetyMode.NONE){ findNavController() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope{

        backButton.setOnClickListener {
            viewModel.backToStack()
        }

        viewModel.backButtonLiveData.observe(viewLifecycleOwner, backButtonObserver)
    }
    private val backButtonObserver = Observer<Unit>{
        navController.navigateUp()
    }
}