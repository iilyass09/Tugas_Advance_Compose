package com.example.tugas_advance.screen.Room

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugas_advance.data.Room.entity.UserEntity
import com.example.tugas_advance.data.Room.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    var userUiState by mutableStateOf(UserEntity(nama = "", role = ""))
        private set

    val userList get() = userRepository.getUserList()

    fun updateUserUiState(userEntity: UserEntity){
        userUiState = userEntity
    }

    fun insertUser(){
        viewModelScope.launch {
            userRepository.insertUser(userUiState)
        }
    }
}