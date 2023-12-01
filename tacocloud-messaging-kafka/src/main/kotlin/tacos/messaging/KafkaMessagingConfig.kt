package tacos.messaging

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin


@Configuration
class KafkaMessagingConfig{

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val configs: MutableMap<String, Any> = HashMap()
        configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        return KafkaAdmin(configs)
    }

    @Bean
    fun newTopic() = NewTopic("tacocloud.orders.topic", 10, 1)
}