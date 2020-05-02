package com.music.awesomemusic.repositories

import com.music.awesomemusic.domain.Role
import com.music.awesomemusic.domain.AwesomeUser
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface IRoleRepository : CrudRepository<Role, Int> {
    fun findByRoleName(roleName: String) : Optional<Role>
}