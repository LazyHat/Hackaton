package ru.lazyhat.hackaton.data.db

import androidx.room.TypeConverter
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class Converters {
    @TypeConverter
    fun dateTimeToLong(dt: LocalDateTime) =
        dt.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()

    @TypeConverter
    fun LongToDateTime(long: Long) =
        Instant.fromEpochMilliseconds(long).toLocalDateTime(TimeZone.currentSystemDefault())

    @TypeConverter
    fun dateToInt(date: LocalDate) =
        date.toEpochDays()

    @TypeConverter
    fun intToDate(int: Int) =
        LocalDate.fromEpochDays(int)
}