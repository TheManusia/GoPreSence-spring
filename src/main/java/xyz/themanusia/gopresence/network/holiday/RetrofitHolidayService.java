package xyz.themanusia.gopresence.network.holiday;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.themanusia.gopresence.GopresenceApplication;

public class RetrofitHolidayService {
    private static final String URL = "https://www.googleapis.com/calendar/v3/calendars/id.indonesian%23holiday%40group.v.calendar.google.com/events/";

    public static HolidayService getService() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request.Builder request = chain.request().newBuilder();
                    HttpUrl original = chain.request().url();
                    HttpUrl url = original.newBuilder().addQueryParameter("key", GopresenceApplication.GOOGLE_CALENDAR_KEY).build();
                    request.url(url);
                    return chain.proceed(request.build());
                })
                .build();
        Retrofit retrofit = new Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit.create(HolidayService.class);
    }
}
