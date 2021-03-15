//package com.example.demo.config;
//
//import com.example.demo.service.RoleService;
//import com.example.demo.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.keycloak.KeycloakPrincipal;
//import org.keycloak.KeycloakSecurityContext;
//import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
//import org.keycloak.representations.AccessToken;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.List;
//
///**
// * @author wuzhiwei
// * @create 2021-03-14 0:04
// */
//@Slf4j
//@Component
//public class SecurityAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private RoleService roleService;
//
//    private GrantedAuthoritiesMapper grantedAuthoritiesMapper;
//
//    public void setGrantedAuthoritiesMapper(GrantedAuthoritiesMapper grantedAuthoritiesMapper) {
//        this.grantedAuthoritiesMapper = grantedAuthoritiesMapper;
//    }
//
//    /**
//     * 验证Authentication，建立系统使用者信息principal(token)
//     */
//    @Override
//    public Authentication authenticate(Authentication authentication) throws RuntimeException {
//        //从token中获取用户信息
//        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) authentication;
//        AccessToken accessToken = getAccessToken((token));
//        String userId = accessToken.getSubject();
//        //查询用户是否存在，若不存在则存入数据库
//        userService.checkUser(accessToken, userId);
//        //根据userId查询本系统用户权限，放入token中
//        List<GrantedAuthority> grantedAuthorities = roleService.getGrantedAuthorities(userId);
//        KeycloakAuthenticationToken authenticationToken = new KeycloakAuthenticationToken(token.getAccount(), token.isInteractive(), mapAuthorities(grantedAuthorities));
//        return authenticationToken;
//    }
//
//    private AccessToken getAccessToken(KeycloakAuthenticationToken principal) {
//        KeycloakAuthenticationToken token = principal;
//        KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal)token.getPrincipal();
//        KeycloakSecurityContext context = keycloakPrincipal.getKeycloakSecurityContext();
//        return context.getToken();
//    }
//
//    private Collection<? extends GrantedAuthority> mapAuthorities(
//            Collection<? extends GrantedAuthority> authorities) {
//        return grantedAuthoritiesMapper != null
//                ? grantedAuthoritiesMapper.mapAuthorities(authorities)
//                : authorities;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return KeycloakAuthenticationToken.class.isAssignableFrom(aClass);
//    }
//
//}
//
