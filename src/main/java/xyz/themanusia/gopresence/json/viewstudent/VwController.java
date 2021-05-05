package xyz.themanusia.gopresence.json.viewstudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.themanusia.gopresence.json.response.Response;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(path = "/uservw")
@Controller
public class VwController {
    private final VwStudentRepository vwStudentRepository;

    @Autowired
    public VwController(VwStudentRepository vwStudentRepository) {
        this.vwStudentRepository = vwStudentRepository;
    }

    @GetMapping
    public @ResponseBody
    Response getView(@RequestParam(name = "id", required = false) String username) {
        if (username != null) {
            VwStudent vwStudent = vwStudentRepository.getViewStudentById(username);
            if (vwStudent == null)
                return new Response(Response.NOT_FOUND, "User not found", null);
            return new Response(Response.OK, "Showing data", vwStudent);
        }
        List<VwStudent> s = new ArrayList<>(vwStudentRepository.getViewStudent());

        if (s.isEmpty())
            return new Response(Response.OK, "Data is Empty", null);

        s.forEach(vwStudent -> vwStudent.setGambar("img/" + vwStudent.getGambar() + ".png"));
        return new Response(Response.OK, "Showing data", s);
    }
}