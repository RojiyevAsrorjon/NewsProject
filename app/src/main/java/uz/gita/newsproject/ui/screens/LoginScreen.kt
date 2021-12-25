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
import uz.gita.newsproject.data.models.LoginDataClass
import uz.gita.newsproject.databinding.ScreenLoginBinding
import uz.gita.newsproject.ui.viewModels.LoginScreenViewModel
import uz.gita.newsproject.ui.viewModels.impl.LoginScreenViewModelImpl
import uz.gita.newsproject.utils.scope

@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_login) {
    private val viewModel: LoginScreenViewModel by viewModels<LoginScreenViewModelImpl>()
    private val binding by viewBinding(ScreenLoginBinding::bind)
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    private val enable = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        combine(
            loginEdit.textChanges()
                .map { it.isNotEmpty() },
            passwordEdit.textChanges()
                .map { it.length >= 6 },
            transform = { login, password -> login && password }
        ).onEach { loginButton.isEnabled = it }
            .launchIn(lifecycleScope)

        loginButton.setOnClickListener { viewModel.login(LoginDataClass(loginEdit.text.toString(), passwordEdit.text.toString())) }
        registerText.setOnClickListener { viewModel.openRegisterScreen() }

        observers()
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun observers() {
        viewModel.errorMassageLiveData.observe(viewLifecycleOwner, errorMessageObserver)
        viewModel.openMainScreenLiveData.observe(this, openMainScreenObserver)
        viewModel.openRegisterScreenLiveData.observe(this, openRegisterScreenObserver)
    }

    private val openRegisterScreenObserver = Observer<Unit> {
        navController.navigate(R.id.registerScreen)
    }

    private val openMainScreenObserver = Observer<Unit> {
        navController.navigate(R.id.mainScreen)
    }

    private val errorMessageObserver = Observer<String> {
        Snackbar.make(binding.layout, it, Snackbar.LENGTH_SHORT).show()
    }
}