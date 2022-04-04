package teach.meskills.timetable.holidays

import android.util.Log
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

import java.lang.Exception

class OkhttpContentRepository: ContentRepository {
    private val gson = Gson()
    private val okHttpClient = OkHttpClient.Builder().build()

   override suspend fun downloadHolidays(): List<HolidaysEntity> {
      return  try {
            val urlAddress = "https://calendarific.com/api/v2/holidays?&" +
                    "api_key=$API_KEY&country=by&year=2022"
            val response =
                okHttpClient
                    .newCall(
                        Request.Builder()
                            .url(urlAddress)
                            .post(RequestBody.create(null, ByteArray(0)))
                            .build()
                    ).execute()
            val jsonString = response.body?.string().orEmpty()
            Log.d("respon", jsonString)
            val json = gson.fromJson(jsonString, HolidayResponseDTO::class.java)
            Log.d("respon", response.code.toString())
            val holidayMap = json.response.holidays.map {
                HolidaysEntity(
                    metaCode = it.code,
                    holidaysName = it.name,
                    descriptions = it.description,
                    dateIso = it.date.iso
                )
            }.orEmpty()
          Log.d("holidayMap", holidayMap.toString())
            holidayMap
        }
        catch (e:Exception){
            e.printStackTrace()
            return emptyList()
        }
    }

    companion object {
        const val API_KEY = "f0aaea10bdb084d8bde54336569043896b5dac67"
    }
}