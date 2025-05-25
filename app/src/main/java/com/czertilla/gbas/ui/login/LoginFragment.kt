package com.czertilla.gbas.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.czertilla.gbas.databinding.FragmentLoginBinding

import com.czertilla.gbas.R
import com.czertilla.gbas.data.local.session.SessionManager
import com.czertilla.gbas.data.model.LoggedInUser
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch
import com.czertilla.gbas.MainActivity

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val loginViewModel: LoginViewModel by viewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sessionManager: SessionManager

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
            lifecycleScope.launch {
                this@LoginFragment.context?.let { it1 -> loginViewModel.loginWithGoogle(it1) }
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUser) {
        val welcome = getString(R.string.welcome) + model.displayName
        sessionManager.userId = model.userId

        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()

        startMainActivity()
    }

    private fun startMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        requireActivity().finish()
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