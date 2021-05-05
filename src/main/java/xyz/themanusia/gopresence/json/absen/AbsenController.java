package xyz.themanusia.gopresence.json.absen;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.themanusia.gopresence.json.response.Response;
import xyz.themanusia.gopresence.json.user.User;
import xyz.themanusia.gopresence.json.user.UserRepository;
import xyz.themanusia.gopresence.network.holiday.HolidayCore;
import xyz.themanusia.gopresence.network.holiday.response.ItemsItem;
import xyz.themanusia.gopresence.network.holiday.response.JsonResponse;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(path = "/absen")
public class AbsenController {
    private final AbsenRepository absenRepository;
    private final UserRepository userRepository;
    private final HolidayCore holidayCore;

    @Autowired
    public AbsenController(AbsenRepository absenRepository, UserRepository userRepository) {
        this.absenRepository = absenRepository;
        this.userRepository = userRepository;
        holidayCore = new HolidayCore();
    }

    @SneakyThrows
    @GetMapping
    public @ResponseBody
    Response absen(@RequestParam(name = "username") String username) {
        Response response = new Response();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        JsonResponse res = holidayCore.isTodayHoliday();

        for (ItemsItem item : res.getItems()) {
            String s = item.getStart().getDate().replaceAll("-", "");
            if (absenRepository.isUserExist(username) == 0) {
                if (!s.equals(simpleDateFormat.format(new Date(System.currentTimeMillis())))) {
                    User u = userRepository.getUserByUsername(username);
                    Absen a = new Absen();
                    a.setUser(u);
                    a.setDate(new Date(System.currentTimeMillis()));
                    a.setTime(new Time(System.currentTimeMillis()));
                    absenRepository.save(a);
                    response.setMessage("Absen Berhasil");
                    response.setStatus(Response.OK);
                    response.setValue(absenRepository.findTopByOrderByIdDesc());
                } else {
                    response.setValue(null);
                    response.setMessage(item.getSummary());
                    response.setStatus(Response.BAD_REQUEST);
                }
                return response;
            } else {
                response.setStatus(Response.BAD_REQUEST);
                response.setMessage("Anda Sudah Absen");
                response.setValue(null);
            }
        }

        return response;
    }
}
