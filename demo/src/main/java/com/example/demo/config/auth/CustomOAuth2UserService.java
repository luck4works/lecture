package com.example.demo.config.auth;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.demo.config.auth.dto.OAuthAttributes;
import com.example.demo.config.auth.dto.SessionUser;
import com.example.demo.domain.user.Account;
import com.example.demo.domain.user.AccountRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final AccountRepository userRepository;
    private final HttpSession httpSession;
 
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
 
        String registrationId = userRequest
                .getClientRegistration().getRegistrationId(); //1
        String userNameAttributeName = userRequest
                .getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint()
                    .getUserNameAttributeName(); //2
 
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,
                oAuth2User.getAttributes()); //3
 
        Account user = saveOrUpdate(attributes);
 
        httpSession.setAttribute("user", new SessionUser(user)); //4
 
        return new DefaultOAuth2User(
                Collections.singleton(new
                        SimpleGrantedAuthority((user.getRoleKey()))),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }
 
    private Account saveOrUpdate (OAuthAttributes attributes) {
        Account user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
