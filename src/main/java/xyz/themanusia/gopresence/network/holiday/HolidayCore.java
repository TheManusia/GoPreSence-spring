package xyz.themanusia.gopresence.network.holiday;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import xyz.themanusia.gopresence.network.holiday.response.JsonResponse;

import java.io.IOException;

@Service
public class HolidayCore {
    private final HolidayService service;

    public HolidayCore() {
        service = RetrofitHolidayService.getService();
    }

    public JsonResponse isTodayHoliday() throws IOException {
        Call<JsonResponse> response = service.getHoliday();
        Response<JsonResponse> ress = response.execute();

        return ress.body();
    }
}
