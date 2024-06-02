package com.example.accountamate
import androidx.lifecycle.ViewModel
import com.example.accountamate.database.MockUserDao
import com.example.accountamate.database.User
import com.example.accountamate.database.UserDao
import kotlinx.coroutines.flow.MutableStateFlow

class UserViewModel(private val userDao: UserDao = MockUserDao()) : ViewModel() {

    val isLoggedIn = MutableStateFlow(false)

    suspend fun login(username: String, password: String): Boolean {
        val user = userDao.getUser(username, password)
        if (user != null) {
            isLoggedIn.value = true
            return true
        } else {
            return false
        }
    }

    suspend fun register(username: String, password: String) {

        val newUser = User(username = username, password = password)
        userDao.insertUser(newUser)
    }
}


