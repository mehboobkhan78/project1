package com.Travelbnb.service;


import com.Travelbnb.entity.Appuser;
import com.Travelbnb.exception.ExceptionNotFound;
import com.Travelbnb.paylod.Appuser_dto;
import com.Travelbnb.paylod.LoginDto;
import com.Travelbnb.repository.AppuserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service

public class serviceimpls implements Service {
    private AppuserRepository appuserRepository;
    private jwtservice jwts;
    public serviceimpls(AppuserRepository appuserRepository,jwtservice jwts) {
        this.appuserRepository = appuserRepository;
        this.jwts = jwts;
    }


    @Override
    public Appuser_dto signupService(Appuser_dto dto1) {
        Appuser user = DtoToEntity(dto1);
        Appuser saved = appuserRepository.save(user);
        Appuser_dto dto = EntityToDto(saved);
        return dto;
    }
    public Appuser DtoToEntity(Appuser_dto dto){
        Appuser app = new Appuser();
        app.setName(dto.getName());
        app.setUsername(dto.getUsername());
        app.setEmail(dto.getEmail());
        app.setRole(dto.getRole());
        app.setPassword(BCrypt.hashpw(dto.getPassword(),BCrypt.gensalt(5)));
        return app;

    }
    public Appuser_dto EntityToDto(Appuser user){
        Appuser_dto dto = new Appuser_dto();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setId(user.getId());
        dto.setRole(user.getRole());
        return dto;
    }

    public boolean existsByEmail(String email) {
        return appuserRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return appuserRepository.existsByUsername(username);
    }

    @Override
    public String  verifylogin(LoginDto dto){
        Optional<Appuser> data = appuserRepository.findByUsername(dto.getUsername());
        if(data.isPresent()){
            Appuser entity = data.get();
             if(BCrypt.checkpw(dto.getPassword(),entity.getPassword())){
//                 String token = jwts.GenrateToken(entity);
                 return "token";
             }
        }
        return null;

    }

    @Override
    public List<Appuser_dto> GetList(int pageNo, int pageSize, String direction,String sortby) {
         Pageable pageable= null;
         if(direction.equalsIgnoreCase("asc")){
             pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortby));
         }
         else if(direction.equalsIgnoreCase("desc")){
             pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortby));
         }
        Page<Appuser> data = appuserRepository.findAll(pageable);
        List<Appuser> List = data.getContent();
        return List.stream().map(p->EntityToDto(p)).collect(Collectors.toList());
    }

    @Override
    public Appuser_dto getById(Long id) {
       return appuserRepository.findById(id).filter(p -> p.getPassword()!=null).
               map(p ->EntityToDto(p)).orElseThrow(()->new ExceptionNotFound("No such user : " +id));
    }
//    OR
//        Optional<Appuser> Byid= appuserRepository.findById(id);
//        if(Byid.isPresent()){
//            Appuser user = Byid.get();
//            if(user.getPassword()!=null){
//                return EntityToDto(user);
//            }
//        }
//            throw new ExceptionNotFound("No data found for thid Id " +id);
//
//    }





}