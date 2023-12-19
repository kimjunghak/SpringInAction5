package tacos.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.stereotype.Service
import org.springframework.web.servlet.handler.HandlerMappingIntrospector
import reactor.core.publisher.Mono
import tacos.data.UserReactiveRepository
import tacos.data.UserRepository

@Configuration
//@EnableWebSecurity
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder? = PasswordEncoderFactories.createDelegatingPasswordEncoder()

//    @Bean
//    fun securityFilterChain(http: HttpSecurity, inspector: HandlerMappingIntrospector): SecurityFilterChain {
//        val matcherBuilder = MvcRequestMatcher.Builder(inspector)
//
//        return http
//            .authorizeHttpRequests {
//                it
//                    .requestMatchers(HttpMethod.OPTIONS).permitAll()
//                    .requestMatchers(
//                        matcherBuilder.pattern("/design"),
//                        matcherBuilder.pattern("/orders/**")
//                    ).permitAll()
////                ).hasRole("USER")
//                    .requestMatchers(HttpMethod.PUT, "/ingredients").permitAll()
//                    .requestMatchers(
//                        matcherBuilder.pattern("/"),
//                        matcherBuilder.pattern("/**"),
//                        AntPathRequestMatcher("/h2-console/**")
//                    ).permitAll()
//            }
//            .httpBasic {
//                it.realmName("Taco Cloud")
//            }
//            .formLogin {
//                it.loginPage("/login")
//                    .defaultSuccessUrl("/design", true)
//            }
//            .logout {
//                it.logoutSuccessUrl("/")
//            }
//            .csrf { it.ignoringRequestMatchers(AntPathRequestMatcher("/h2-console/**"))
//                .ignoringRequestMatchers("/ingredients/**", "/design", "/orders/**")
//            }
//            .headers { it.frameOptions { option -> option.sameOrigin() } }
//            .build()
//    }

    /*
    Webflux 버전
    Ant 방식인 **를 사용하지 않아도 된다
     */
    @Bean
    fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .authorizeExchange {
                ex -> ex.pathMatchers("/design", "orders").hasAuthority("USER")
                .anyExchange().permitAll()
            }
            .build()
    }

    @Bean
    fun userDetailsService(userRepository: UserReactiveRepository): ReactiveUserDetailsService {
        return ReactiveUserDetailsService {
            username -> userRepository.findByUsername(username)
            .map { it }
        }
    }

}