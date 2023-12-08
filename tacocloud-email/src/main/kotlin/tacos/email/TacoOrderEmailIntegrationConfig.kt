package tacos.email

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.Pollers
import org.springframework.integration.mail.dsl.Mail

@Configuration
class TacoOrderEmailIntegrationConfig {

    @Bean
    fun tacoOrderEmailFlow(
        emailProperties: EmailProperties,
        emailToOrderTransformer: EmailToOrderTransformer,
        orderSubmitHandler: OrderSubmitMessageHandler
    ): IntegrationFlow {
        return IntegrationFlow
            .from(Mail.imapInboundAdapter(emailProperties.imapUrl))
            { e -> e.poller(Pollers.fixedDelay(emailProperties.pollRate)) }
            .transform(emailToOrderTransformer)
            .handle(orderSubmitHandler)
            .get()
    }
}