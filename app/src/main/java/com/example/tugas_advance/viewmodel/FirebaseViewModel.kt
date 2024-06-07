package com.example.tugas_advance.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FirebaseViewModel : ViewModel() {

    private val _state = MutableStateFlow<FirebaseAuthState?>(null)
    val state: StateFlow<FirebaseAuthState?> = _state

    private val firebaseAuth = FirebaseAuth.getInstance()

    fun registerUser(email: String, password: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    callback(task.isSuccessful)
                }
        }
    }

    fun loginUser(email: String, password: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    callback(task.isSuccessful)
                }
        }
    }
}

data class FirebaseAuthState(
    val user: FirebaseUser? = null,
    val error: String? = null
)
