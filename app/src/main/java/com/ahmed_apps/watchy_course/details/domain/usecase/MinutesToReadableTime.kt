package com.ahmed_apps.watchy_course.details.domain.usecase

/**
 * @author Ahmed Guedmioui
 */
class MinutesToReadableTime(
    private val minutes: Int
) {

    operator fun invoke() : String {
        return if (minutes < 60) {
            "$minutes min"
        } else {
            val hours = minutes / 60
            val remainingMinutes = minutes % 60

            String.format("%02d hr %02d min", hours, remainingMinutes)
        }
    }

}




















