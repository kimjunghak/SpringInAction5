package tacos.email

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "tacocloud.api")
class ApiProperties(
    val url: String? = null
)