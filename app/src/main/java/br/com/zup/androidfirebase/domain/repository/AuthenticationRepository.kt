package br.com.zup.androidfirebase.domain.repository

import br.com.zup.androidfirebase.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthenticationRepository {
    private val auth = Firebase.auth

    fun registerUser(email: String, password:String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    fun updateUserProfile(name: String): Task<Void>? {
        val profile = UserProfileChangeRequest.Builder().setDisplayName(name).build()
        return auth.currentUser?.updateProfile(profile)
    }

    fun loginUser(email: String, pass: String) = auth.signInWithEmailAndPassword(email, pass)

    fun getNameUser(): String = auth.currentUser?.displayName.toString()

    fun getEmailUser(): String = auth.currentUser?.email.toString()

    fun logoutUser() {
        auth.signOut()
    }
}