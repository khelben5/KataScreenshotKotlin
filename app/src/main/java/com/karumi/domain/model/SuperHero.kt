package com.karumi.domain.model

data class SuperHero(
    val name: String,
    val photo: String?,
    val isAvenger: Boolean,
    val team: Team? = null,
    val isAvailable: Boolean = true,
    val description: String
)
