package com.example.accountamate.database


class MockUserDao : UserDao {
    private val users = mutableListOf<User>()

    override suspend fun getUser(username: String, password: String): User? {
        return users.find { it.username == username && it.password == password }
    }

    override suspend fun insertUser(user: User) {
        users.add(user)
    }
}
