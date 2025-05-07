package com.epsports.nasaapi.model

data class AstoroiedData(
    val astoroiedId: String,
    val name: String,
    val nasaJplUrl: String,
    val estimatedDiameterMinInKilometer: Double,
    val estimatedDiameterMaxInKilometer: Double,
    val relativeVelocityKilometersPerSecond: String,
    val relativeVelocityKilometersPerHour: String,
    val relativeVelocityMilesPerHour: String,
    )
