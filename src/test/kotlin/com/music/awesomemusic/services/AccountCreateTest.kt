package com.music.awesomemusic.services

import com.music.awesomemusic.persistence.domain.AwesomeAccount
import com.music.awesomemusic.persistence.dto.request.AccountSignUpForm
import com.music.awesomemusic.repositories.IAccountRepository
import com.music.awesomemusic.security.tokens.JwtTokenProvider
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotSame
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@SpringBootTest
@RunWith(SpringRunner::class)
class AccountCreateTest {

    @Autowired
    lateinit var accountService: AccountService

    @Autowired
    lateinit var accountRepository: IAccountRepository

    @Autowired
    private lateinit var tokenProvider: JwtTokenProvider


    @Before
    fun init() {
        accountRepository.deleteAll()

    }


    @Test
    fun contextLoads() {
    }


    @Test
    @Throws(Exception::class)
    fun shouldSignInToReturnToken() {

        val account = accountService.createAccount(AccountSignUpForm("test4", "1234", "emailTest4", false))

        val authorities = arrayListOf<String>()
        val token = tokenProvider.createToken(account.username, authorities)

        Assert.assertNotNull(token)
    }


    @Test
    fun shouldDatabaseUniqueValues() {
        val allAccounts = accountRepository.findAll()
        val account = AwesomeAccount("testUsername", "12125125",
                "test@mail.com", "some_name", false)

        accountService.saveAccount(account)

        val createdAccount = accountRepository.findById(1)
        assertEquals(0, allAccounts.count())

        assertNotNull(createdAccount)
    }

    @Test
    fun shouldCreateAccountValid() {
        val userRegistrationForm = AccountSignUpForm("testUser6", "12", "email", false)
        val user = accountService.createAccount(userRegistrationForm)

        val userFromRepo = accountRepository.findById(user.id).get()
        assertEquals(user.id, userFromRepo.id)
    }

    @Test
    fun shouldEncoderPasswordForAccount() {
        val userRegistrationForm = AccountSignUpForm("testUser6", "12", "email", false)
        val user = accountService.createAccount(userRegistrationForm)

        assertNotSame(user.password, "12")
    }
}


