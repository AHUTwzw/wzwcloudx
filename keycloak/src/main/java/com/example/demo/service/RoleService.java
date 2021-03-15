package com.example.demo.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuzhiwei
 * @create 2021-03-14 0:09
 */
@Service
public class RoleService {
    public List<GrantedAuthority> getGrantedAuthorities(String userId) {
        return new ArrayList<>();
    }
}
