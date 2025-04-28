package com.Travelbnb.service;

import com.Travelbnb.entity.Appuser;
import com.Travelbnb.paylod.Appuser_dto;
import com.Travelbnb.paylod.LoginDto;

import java.util.List;

public interface Service {

    Appuser_dto signupService(Appuser_dto dto1);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

//   public Boolean loginService(LoginDto loginDto);

   public String verifylogin(LoginDto loginDto);

    List<Appuser_dto> GetList(int pageNo, int pageSize,String direction, String sortby);

    Appuser_dto getById(Long id);
}
