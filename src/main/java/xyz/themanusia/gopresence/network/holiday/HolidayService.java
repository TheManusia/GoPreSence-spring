package xyz.themanusia.gopresence.network.holiday;

import retrofit2.Call;
import retrofit2.http.GET;
import xyz.themanusia.gopresence.network.holiday.response.JsonResponse;

public interface HolidayService {

    @GET(".")
    Call<JsonResponse> getHoliday();
}
