package tacos.email

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "tacocloud.email")
class EmailProperties(
    val username: String? = null,
    val password: String? = null,
    val host: String? = null,
    val mailbox: String? = null,
    val pollRate: Long = 30000,
) {
    val imapUrl: String
        get() = String.format("imaps://%s:%s@%s/%s", username, password, host, mailbox)
}