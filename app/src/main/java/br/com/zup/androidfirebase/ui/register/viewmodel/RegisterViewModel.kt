package br.com.zup.androidfirebase.ui.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.androidfirebase.domain.model.User
import br.com.zup.androidfirebase.domain.repository.AuthenticationRepository

class RegisterViewModel : ViewModel() {
    private val authenticationRepository = AuthenticationRepository()

    private var _registerState = MutableLiveData<User>()
    val registerState: LiveData<User> = _registerState

    private var _errorState = MutableLiveData<String>()
    val errorState: LiveData<String> = _errorState

    fun validateDataUser(user: User) {
        when {
            user.name.isEmpty() -> _errorState.value = "Insira seu nome"
            user.email.isEmpty() -> _errorState.value = "Insira um email"
            user.password.isEmpty() -> _errorState.value = "Insira uma senha"
            else -> registerUser(user)
        }
    }

    private fun registerUser(user: User) {
        try {
            authenticationRepository.registerUser(user.email, user.password).addOnSuccessListener {
                authenticationRepository.updateUserProfile(user.name)?.addOnSuccessListener {
                    _registerState.value = user
                }

            }.addOnFailureListener {
                _errorState.value = "Ops! Ocorreu um erro ao criar o usuário!" + it.message
            }
        } catch (ex: Exception) {
            _errorState.value = ex.message
        }
    }
}