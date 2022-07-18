package br.com.zup.androidfirebase.ui.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import br.com.zup.androidfirebase.R
import br.com.zup.androidfirebase.databinding.ActivityHomeBinding
import br.com.zup.androidfirebase.ui.home.viewmodel.HomeViewModel
import br.com.zup.androidfirebase.ui.login.view.LoginActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataUser()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item1_exit -> {
                viewModel.logoutUser()
                this.finish()
                goToLogin()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDataUser() {
        val name = viewModel.getNameUser()
        val email = viewModel.getEmailUser()
        binding.tvNameUser.text = name
        binding.tvEmailUser.text = email

    }

    private fun goToLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
    }
}