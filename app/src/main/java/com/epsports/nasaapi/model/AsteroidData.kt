package com.epsports.nasaapi.model

data class AsteroidData(
    val id: String,
    val name: String,
    val nasaJplUrl: String,
    val estimatedDiameterMinInKilometer: Double,
    val estimatedDiameterMaxInKilometer: Double,
    val relativeVelocityKilometersPerSecond: Double,
    val relativeVelocityKilometersPerHour: Double,
    val relativeVelocityMilesPerHour: Double,
    )
