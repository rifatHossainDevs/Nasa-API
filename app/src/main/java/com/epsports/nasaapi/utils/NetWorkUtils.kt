package com.epsports.nasaapi.utils

import com.epsports.nasaapi.model.AsteroidData
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


fun asteroidJsonObject(jsonObject: JSONObject): ArrayList<AsteroidData>{
    val getNextSevenDays = getNext7Days()
    val listOfAsteroid = ArrayList<AsteroidData>()
    val nearEarthObject = jsonObject.getJSONObject("near_earth_objects")

    for (formatedDate in getNextSevenDays){
        if (nearEarthObject.has(formatedDate)){
            val listOfObjectByDate = nearEarthObject.getJSONArray(formatedDate).length()

            var i = 0

            while (i < listOfObjectByDate){
                val objectByDate = nearEarthObject.getJSONArray(formatedDate).getJSONObject(i)
                val id = objectByDate.getString("id")
                val name = objectByDate.getString("name")
                val nasaJplUrl = objectByDate.getString("nasa_jpl_url")

                val estimatedDiameter = objectByDate.getJSONObject("estimated_diameter")
                val estimatedDiameterKiloMeter = estimatedDiameter.getJSONObject("kilometers")

                val estimatedDiameterMinInKilometer = estimatedDiameterKiloMeter.getDouble("estimated_diameter_min")
                val estimatedDiameterMaxInKilometer = estimatedDiameterKiloMeter.getDouble("estimated_diameter_max")

                val closeApproachData = objectByDate.getJSONArray("close_approach_data").getJSONObject(0)
                val relativeVelocity = closeApproachData.getJSONObject("relative_velocity")

                val relativeVelocityKilometersPerSecond = relativeVelocity.getDouble("kilometers_per_second")
                val relativeVelocityKilometersPerHour = relativeVelocity.getDouble("kilometers_per_hour")
                val relativeVelocityMilesPerHour = relativeVelocity.getDouble("miles_per_hour")

                listOfAsteroid.add(
                    AsteroidData(
                        id = id,
                        name = name,
                        nasaJplUrl = nasaJplUrl,
                        estimatedDiameterMaxInKilometer = estimatedDiameterMaxInKilometer,
                        estimatedDiameterMinInKilometer = estimatedDiameterMinInKilometer,
                        relativeVelocityMilesPerHour = relativeVelocityMilesPerHour,
                        relativeVelocityKilometersPerHour = relativeVelocityKilometersPerHour,
                        relativeVelocityKilometersPerSecond = relativeVelocityKilometersPerSecond
                    )
                )

                i++
            }
        }
    }




    return listOfAsteroid
}

fun getNext7Days(): ArrayList<String> {
    val listOfDays = ArrayList<String>()

    val calendar = Calendar.getInstance()

    for (i in 1..7) {
        val currentTime = calendar.time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        listOfDays.add(dateFormat.format(currentTime))
        calendar.add(Calendar.DAY_OF_YEAR, 1)
    }

    return listOfDays
}