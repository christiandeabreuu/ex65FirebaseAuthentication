package br.com.zup.androidfirebase.ui.register.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import br.com.zup.androidfirebase.databinding.ActivityRegisterBinding
import br.com.zup.androidfirebase.domain.model.User
import br.com.zup.androidfirebase.ui.home.view.HomeActivity
import br.com.zup.androidfirebase.ui.register.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnRegistration.setOnClickListener {
            val user = getDataUser()
            viewModel.validateDataUser(user)
        }
        initObserver()
    }

    private fun getDataUser(): User {
        return User(
            name = binding.etUsername.text.toString(),
            email = binding.etUseremail.text.toString(),
            password = binding.etPassword.text.toString()
        )
    }

    private fun initObserver() {
        viewModel.registerState.observe(this) {
            goToHomePage(it)
        }
        viewModel.errorState.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun goToHomePage(user: User) {
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra("user key", user)
        }
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}