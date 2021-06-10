package com.example.dagger.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.example.dagger.R
import com.example.dagger.ui.main.MainActivity
import com.example.dagger.viewmodels.ViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import java.lang.NumberFormatException
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private lateinit var viewModel: AuthViewModel

    private lateinit var userId: EditText
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
        setLogo()

        userId = findViewById(R.id.user_id_input)
        progressBar = findViewById(R.id.progress_bar)

        findViewById<Button>(R.id.login_button).setOnClickListener {
            attemptLogin()
        }

        subscribeObservers()
    }

    private fun setLogo() {
        requestManager.load(logo).into(findViewById(R.id.login_logo))
    }

    private fun showProgressBar(isOnShow: Boolean) {
        progressBar.visibility = if (isOnShow) View.VISIBLE else View.INVISIBLE
    }

    private fun attemptLogin() {
        val s = userId.text.toString()
        if (s.isEmpty()) {
            Snackbar.make(userId, getString(R.string.field_is_empty), Snackbar.LENGTH_LONG).show()
        } else {
            try {
                viewModel.authenticateById(s.toInt())
            } catch (e: NumberFormatException) {
                Snackbar.make(userId, getString(R.string.input_number), Snackbar.LENGTH_LONG).show()
            }

        }
    }

    private fun onLoginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun subscribeObservers() {
        viewModel.observeAuthState().observe(this) {
            if (it != null) {
                when(it.status) {
                    AuthResource.AuthStatus.LOADING -> { showProgressBar(true) }
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        showProgressBar(false)
                        onLoginSuccess()
                        Log.i("zxcv", "AUTH SUCCESS")
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        Toast.makeText(this, "ERROR: ${it.message}", Toast.LENGTH_LONG).show()
                        showProgressBar(false)
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        showProgressBar(false)
                    }
                }
            }
        }
    }
}