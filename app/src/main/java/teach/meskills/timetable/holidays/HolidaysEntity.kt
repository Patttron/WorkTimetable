package teach.meskills.timetable.holidays

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class HolidaysEntity(
    @PrimaryKey
    @ColumnInfo(name = "metaCode")
    val metaCode: Int,
    @ColumnInfo(name = "holidaysName")
    val holidaysName: String,
    @ColumnInfo(name = "descriptions")
    val descriptions: String,
    @ColumnInfo(name = "holidayDateDay")
    val holidayDateDay: Int,
    @ColumnInfo(name = "holidayDateMonth")
    val holidayDateMonth: Int,
    @ColumnInfo(name = "holidayDateYear")
    val holidayDateYear: Int
) : Parcelable
