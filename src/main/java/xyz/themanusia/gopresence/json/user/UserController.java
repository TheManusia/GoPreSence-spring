package xyz.themanusia.gopresence.json.user;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.themanusia.gopresence.json.response.Response;
import xyz.themanusia.gopresence.tools.Tools;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

@RequestMapping(path = "/user")
@Controller
public class UserController {
    private final UserRepository userRepository;

    @Value(value = "${image.path:src/main/upload/static/img}")
    private String imagePath;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(path = "/login")
    public @ResponseBody
    Response login(@RequestParam(name = "username") String username,
                   @RequestParam(name = "password") String password) {
        if (userRepository.existsByUsernameAndPassword(username, Tools.stringToMd5(password))) {
            return new Response(Response.OK, "Login successfully", userRepository.getUserByUsername(username));
        }
        return new Response(Response.NOT_FOUND, "User not found", null);
    }

    @RequestMapping(path = "/edit")
    public @ResponseBody
    Response edit(@RequestParam(name = "username") String username,
                  @RequestParam(name = "password") String password,
                  @RequestParam(name = "nama", required = false) String name,
                  @RequestParam(name = "newpassword", required = false) String newPass,
                  @RequestParam(name = "gambar", required = false) String gambar) {
        if (userRepository.existsByUsernameAndPassword(username, Tools.stringToMd5(password))) {
            User u = userRepository.getUserByUsername(username);
            if (name != null && !name.isEmpty())
                u.setName(name);
            if (newPass != null && !newPass.isEmpty())
                u.setPassword(Tools.stringToMd5(newPass));
            if (gambar != null && !gambar.isEmpty())
                u.setPicture(saveFile(gambar, username));
            userRepository.save(u);
            return new Response(Response.OK, "Successfully edit", userRepository.getUserByUsername(username));
        }
        return new Response(Response.BAD_REQUEST, "Wrong Username Or Password", null);
    }

    @SneakyThrows
    private String saveFile(String base64Str, String username) {
        Random random = new Random();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd");
        StringBuilder fileName = new StringBuilder();
        fileName.append(random.nextInt(1000));
        fileName.append("-");
        fileName.append(simpleDateFormat.format(new Date(System.currentTimeMillis())));
        fileName.append("-");
        fileName.append(username);
        fileName.append(".png");
        File file = new File(imagePath, fileName.toString());
        byte[] fileBytes = Base64.getDecoder().decode(base64Str);
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(fileBytes);
        return fileName.toString();
    }

}
