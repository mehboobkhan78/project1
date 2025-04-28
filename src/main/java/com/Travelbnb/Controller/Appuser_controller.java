package com.Travelbnb.Controller;

import com.Travelbnb.entity.Appuser;
import com.Travelbnb.paylod.Appuser_dto;
import com.Travelbnb.paylod.JwtToken_dto;
import com.Travelbnb.paylod.LoginDto;
import com.Travelbnb.service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api/v1/signup")
public class Appuser_controller {
    private Service su;

    public Appuser_controller(Service su) {
        this.su = su;
    }
//    http://localhost:8080/Api/v1/signup/user

    @PostMapping("/adduser")
    public ResponseEntity<?> signup(@RequestBody Appuser_dto dto1) {
        if (su.existsByEmail(dto1.getEmail())) {

            return new ResponseEntity<>("Email is already in use.", HttpStatus.BAD_REQUEST);
        }
        if (su.existsByUsername(dto1.getUsername())) {
            return new ResponseEntity<>("username is already in use.", HttpStatus.BAD_REQUEST);
        }
        Appuser_dto dto = su.signupService(dto1);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
//    http://localhost:8080/Api/v1/signup/Login

    @PostMapping("/Login")
    public ResponseEntity<?> Verifylogin(@RequestBody LoginDto loginDto){
       String token= su.verifylogin(loginDto);
       if(token!=null){
           JwtToken_dto jwtToken = new JwtToken_dto();
           jwtToken.setType("JWT token");
           jwtToken.setToken(token);
           return new ResponseEntity<>(jwtToken,HttpStatus.OK);
       }
        return new ResponseEntity<>("In valid username/password",HttpStatus.OK);

    }
//    getall?pageNo=1&pageSize=2&sortby=id&direction=desc

    @GetMapping("/getall")
    public ResponseEntity<List<Appuser_dto>> getAll
            (@RequestParam(name="pageNo",defaultValue = "5", required = false) int pageNo,
             @RequestParam(name="pageSize", defaultValue = "10", required = false) int pageSize,
             @RequestParam(name="sortby", defaultValue = "id", required = false)String sortby,
             @RequestParam(name="Direction", defaultValue = "asc", required = false)String direction){
        List<Appuser_dto> list = su.GetList(pageNo, pageSize, direction, sortby);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/GetbyId")
    public ResponseEntity<Appuser_dto> getById(@RequestParam Long id){
        Appuser_dto user = su.getById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

//    get list of all users ,done
//    add exception class, done
//    pagination, done
//    Security
// token
// search, review or comment
//    add to cart or fav
//    add post or image




}
