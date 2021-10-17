package com.otienosamwel.mlimagescan.domain.model

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity
import org.springframework.data.annotation.Id

@Entity
data class Photo(
    @Id val id: String?,
    val url: String?,
    val label: String?
)
