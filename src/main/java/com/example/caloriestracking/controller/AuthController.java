package com.example.caloriestracking.controller;

import com.example.caloriestracking.entity.Food;
import com.example.caloriestracking.entity.User;
import com.example.caloriestracking.entity.dto.ResponseLogin;
import com.example.caloriestracking.entity.dto.ResponseObject;
import com.example.caloriestracking.repo.UserRepo;
import com.example.caloriestracking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "auth")
public class AuthController {
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("google/login")
    public ResponseEntity<ResponseObject> authGG(OAuth2AuthenticationToken authentication, HttpServletRequest request, HttpServletResponse responseServlet){
        try{
            OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                    authentication.getAuthorizedClientRegistrationId(),
                    authentication.getName());

            String userInfoEndpointUri = client.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri();

            if (!StringUtils.isEmpty(userInfoEndpointUri)) {
                RestTemplate restTemplate = new RestTemplate();

                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue());

                HttpEntity entity = new HttpEntity("", headers);

                //từ restTemplate call theo cái userInfoEndpointUri + headers có token từ login success để lấy response trả về (info chi tiết của user đó) ánh xạ vào Map để có thể lấy info theo key ra
                ResponseEntity<Map> response = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);

                Map userAttributes = response.getBody();

                String name = (String) userAttributes.get("name");
                String email = (String) userAttributes.get("email");
                String avatar = (String) userAttributes.get("picture");

                ResponseLogin responseLogin = new ResponseLogin();
                responseLogin.setName(name);
                responseLogin.setEmail(email);
                responseLogin.setAvatar(avatar);

                //xét xem có email này dưới DB ko (xem đã có acc chưa vì mỗi user 1 email)
                User user = userService.findByEmail(email);
                if(user == null){
                    //chưa --> return name + email (để lưu ở share preference ghi nhớ session thôi) --> chuyển tới page register info (height, weitght, tall, ...) --> page chose target --> create acc -> home
                    ResponseObject responseObject = new ResponseObject("success", "login success (no account)", responseLogin); //(no account) để nhận diện
                    return ResponseEntity.status(HttpStatus.OK).body(responseObject);
                }else{
                    //rồi --> return  name + email (để lưu ở share preference ghi nhớ session thôi) --> home
                    ResponseObject responseObject = new ResponseObject("success", "login success", responseLogin);
                    return ResponseEntity.status(HttpStatus.OK).body(responseObject);
                }

            } else{
                throw new Exception("some error when login");
            }
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    @PostMapping("login")
    public ResponseEntity<ResponseObject> auth(@RequestParam String email,
                                                @RequestParam String pass){
        try{
            User user = userService.checkLogin(email, pass);
            if(user == null){
                ResponseObject responseObject = new ResponseObject("fail", "login fail - wrong email or password.", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }else{
                ResponseObject responseObject = new ResponseObject("success", "login success", user);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    //ta có thể dùng link của springboot cung cấp để logout (localhost:8080/logout - nó có sẵn giao diện logout)
    @GetMapping("logout")
    public ResponseEntity<ResponseObject> logout(HttpServletRequest request, HttpServletResponse response) {
        try{
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("GoogleSession")) {
                        cookie.setValue("");
                        cookie.setPath("/");
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                        break;
                    }
                }
            }
            ResponseObject responseObject = new ResponseObject("success", "logout success", null);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    @PostMapping("forgotpass")
    public ResponseEntity<ResponseObject> sendEmail(@RequestParam String to,
                            @RequestParam String linkResetPwd) {
        try{

            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("khaphpdz@gmail.com");
            message.setTo(to);
            message.setSubject("FORGOT PASSWORD !");    //Forgot password
            message.setText("Your link to reset password account: " + linkResetPwd);

            mailSender.send(message);

            ResponseObject responseObject = new ResponseObject("success", "send mail success", null);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    @GetMapping("reset")
    public ResponseEntity<ResponseObject> resetPass(@RequestParam String email) {
        try{
            User user = userService.findByEmail(email);
            if(user != null){
                String random = String.valueOf(UUID.randomUUID());
                user.setPassword(random);

                userRepo.save(user);

                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("khaphpdz@gmail.com");
                message.setTo(email);
                message.setSubject("Reset Password !");
                message.setText("Your new password account: " + random);
                mailSender.send(message);

                ResponseObject responseObject = new ResponseObject("success", "send mail success", "Check your mail again");
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }else{
                ResponseObject responseObject = new ResponseObject("fail", "send mail fail - not found acc by email", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    @PostMapping("feedback")
    public ResponseEntity<ResponseObject> sendEmailFeedBackFromUser(@Email @RequestParam String from,
                                                    @RequestParam String feedback) {
        try{

            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("khaphpdz@gmail.com");
            message.setTo("khaphpdz@gmail.com");
            message.setSubject("FEED BACK from user [" + from.toUpperCase()+"]");    //Forgot password
            message.setText(feedback);

            mailSender.send(message);

            ResponseObject responseObject = new ResponseObject("success", "send mail success", null);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }
}
