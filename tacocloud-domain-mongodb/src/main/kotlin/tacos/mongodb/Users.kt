package tacos.mongodb

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable

@Document
class Users(
    @field:org.springframework.data.annotation.Id
    var id: Long? = null,
    private val username: String = "",
    private val password: String = "",
    var fullName: String? = null,
    var street: String? = null,
    var city: String? = null,
    var state: String? = null,
    var zip: String? = null,
    var phoneNumber: String? = null,
): Serializable, UserDetails {
    private val serialVersionUID: Long = 1L

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun getPassword(): String = password
    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true


}