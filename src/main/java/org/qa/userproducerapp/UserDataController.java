package org.qa.userproducerapp;

import org.qa.userproducerapp.dal.UserRepository;
import org.qa.userproducerapp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@RestController
public class UserDataController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/user/all")
    public List<User> getAll() {
        log.info("getting all users. total {}", userRepository.count());
        List<User> target = new ArrayList<>();
        userRepository.findAll().forEach(target::add);
        return target;
    }

    @RequestMapping("/user/save")
    public User save(@RequestBody User user) {
        log.info("adding a new user: {}", user.toString());

        UserDataController control = new UserDataController();

        String imageData = control.getImage(user);

        user.setImageData(imageData);

        Long id = user.getId();
        if (userRepository.existsById(id)){
            id = id +1;
            user.setId(id);
        }
        else {
            return userRepository.save(user);
        }
        return save(user);
    }

    public String getImage(User user){

        Long seed = user.generateSeed();

        user.setSeedNumber(seed);

        BufferedReader in;
        StringBuffer response = null;

        try{
            String url = "https://avatars.dicebear.com/v2/identicon/"+user.getSeedNumber()+".svg";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            System.out.println("\nSending GET request to URL:" + url);
            System.out.println("Response Code:" + responseCode);
            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            String inputLine;
            response = new StringBuffer();

            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            in.close();

        } catch (IOException el){
            el.printStackTrace();
        }
        System.out.println(response.toString());
        return response.toString();

    }
}
