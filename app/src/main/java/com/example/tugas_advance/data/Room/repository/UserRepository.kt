package com.example.tugas_advance.data.Room.repository

import com.example.tugas_advance.data.Room.dao.UserDao
import com.example.tugas_advance.data.Room.entity.UserEntity
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(userEntity: UserEntity) = userDao.insert(userEntity)
    suspend fun updateUser(userEntity: UserEntity) = userDao.update(userEntity)
    suspend fun deleteUser(userEntity: UserEntity) = userDao.delete(userEntity)

    fun getUserList() : Flow<List<UserEntity>> = userDao.getAllAnggotas()
}