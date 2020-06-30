package br.com.celfons.domains

import org.springframework.cache.annotation.Cacheable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Cacheable
@Table(name = "message")
data class Message(
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    val id: Long? = null,
    @Column(name="value")
    val value: String? = null
)
