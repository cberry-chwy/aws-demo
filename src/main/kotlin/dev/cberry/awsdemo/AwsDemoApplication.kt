package dev.cberry.awsdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@SpringBootApplication
class AwsDemoApplication

fun main(args: Array<String>) {
    runApplication<AwsDemoApplication>(*args)
}

data class Message(
    val id: Long = 0,
    val content: String = ""
)

@RestController
class MessageController {
    private val counter: AtomicLong = AtomicLong()

    @PostMapping("/messages")
    fun createMessage(
        @RequestParam(value = "content", defaultValue = "")
        message: String
    ) = Message(counter.getAndIncrement(), message).also {
        State.messageMap[it.id] = it
    }

    @GetMapping("/messages")
    fun getMessage(
        @RequestParam(value = "id", defaultValue = "0")
        id: String
    ) = State.messageMap[id.toLongOrNull() ?: 0L]
}

object State {
    val messageMap = mutableMapOf<Long, Message>()
}