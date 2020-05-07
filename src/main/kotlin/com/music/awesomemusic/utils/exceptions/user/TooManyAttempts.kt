package com.music.awesomemusic.utils.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
class TooManyAttempts(message: String) : Exception(message)