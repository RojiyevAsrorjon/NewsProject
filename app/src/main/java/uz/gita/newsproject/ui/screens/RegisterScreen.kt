package uz.gita.newsproject.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.widget.textChanges
import uz.gita.newsproject.R
import uz.gita.newsproject.data.models.RegisterDataClass
import uz.gita.newsproject.databinding.ScreenRegisterBinding
import uz.gita.newsproject.ui.viewModels.RegisterScreenViewModel
import uz.gita.newsproject.ui.viewModels.impl.RegisterScreenViewModelImpl

@AndroidEntryPoint
class RegisterScreen : Fragment(R.layout.screen_register) {
    private val binding by viewBinding(ScreenRegisterBinding::bind)
    private val viewModel: RegisterScreenViewModel by viewModels<RegisterScreenViewModelImpl>()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        combine(
            binding.usernameEdit.textChanges()
                .map { it.isNotEmpty() },
            binding.loginEdit.textChanges()
                .map { it.isNotEmpty() },
            binding.passwordEdit.textChanges()
                .map { it.length>=6 },
            binding.conPasswordEdit.textChanges()
                .map { it.length>=6 },
            transform = { username, login, password, conPassword -> login && password && username && conPassword }
        ).onEach { binding.registerButton.isEnabled = it }
            .launchIn(lifecycleScope)

        binding.alreadyButton.setOnClickListener { navController.navigateUp() }
        viewModel.openMainScreenLiveData.observe(this, openMainScreenObserver)
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner, errorMessageObserver)

        binding.registerButton.setOnClickListener {
            if (binding.passwordEdit.text.toString() == binding.conPasswordEdit.text.toString()) {
                val registerData = RegisterDataClass(binding.usernameEdit.text.toString(), binding.loginEdit.text.toString(), binding.passwordEdit.text.toString())
                viewModel.register(registerData)
            } else {
                viewModel.showErrorMessage("Parol tasdiqlanmadi")
                return@setOnClickListener
            }
        }
    }

    private val openMainScreenObserver = Observer<Unit> {
        navController.navigate(R.id.action_registerScreen_to_mainScreen)
    }


    private val errorMessageObserver = Observer<String> {
        Snackbar.make(binding.layout, it, Snackbar.LENGTH_SHORT).show()
    }

}