package xyz.themanusia.gopresence.laporan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.themanusia.gopresence.response.Response;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(path = "/laporan")
@Controller
public class LaporanController {
    private final LaporanRepository laporanRepository;

    @Autowired
    public LaporanController(LaporanRepository laporanRepository) {
        this.laporanRepository = laporanRepository;
    }

    @GetMapping
    public @ResponseBody
    Response getLaporanById(@RequestParam(name = "username") String username) {
        List<Laporan> laporan = new ArrayList<>(laporanRepository.getLaporansByUser_UsernameOrderByDateDesc(username));
        if (laporan.isEmpty())
            return new Response(Response.NOT_FOUND, "Data is empty", null);
        return new Response(Response.OK, "Showing data", laporan);
    }
}
