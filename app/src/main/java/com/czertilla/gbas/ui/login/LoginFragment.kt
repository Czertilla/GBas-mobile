package com.czertilla.gbas.ui.login

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.czertilla.gbas.databinding.FragmentLoginBinding

import com.czertilla.gbas.R
import com.czertilla.gbas.data.model.LoggedInUser

class LoginFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel = ViewModelProvider(
            this,
            LoginViewModelFactory(requireContext())
        )[LoginViewModel::class.java]

        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        loginViewModel.loginFormState.observe(viewLifecycleOwner) { loginFormState ->
            loginFormState ?: return@observe
            binding.btnLogin.isEnabled = loginFormState.isDataValid
            loginFormState.usernameError?.let { binding.username.error = getString(it) }
            loginFormState.passwordError?.let { binding.password.error = getString(it) }
        }

        loginViewModel.loginResult.observe(viewLifecycleOwner) { loginResult ->
            loginResult ?: return@observe
            binding.pbLoading.visibility = View.GONE
            loginResult.error?.let { showLoginFailed(it.toString()) }
            loginResult.success?.let { updateUiWithUser(it) }
        }
        loginViewModel.loginResult.observe(viewLifecycleOwner) { oauthResult ->
            oauthResult ?: return@observe
            binding.pbLoading.visibility = View.GONE
            oauthResult.error?.let { showLoginFailed(it.toString()) }
            oauthResult.success?.let { updateUiWithUser(it) }
        }
    }

    private fun setupListeners() {
        binding.username.doOnTextChanged { text, _, _, _ ->
            loginViewModel.loginDataChanged(
                text.toString(),
                binding.password.text.toString()
            )
        }

        binding.password.doOnTextChanged { text, _, _, _ ->
            loginViewModel.loginDataChanged(
                binding.username.text.toString(),
                text.toString()
            )
        }

        binding.btnLogin.setOnClickListener {
            binding.pbLoading.visibility = View.VISIBLE
            loginViewModel.login(
                binding.username.text.toString(),
                binding.password.text.toString()
            )
        }

        binding.btnGoogleSignIn.setOnClickListener {
            loginViewModel.loginWithGoogle()
        }
    }

    private fun updateUiWithUser(model: LoggedInUser) {
        val welcome = getString(R.string.welcome) + model.displayName
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@SuppressLint("SupportAnnotationUsage") @StringRes errorString: String) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}