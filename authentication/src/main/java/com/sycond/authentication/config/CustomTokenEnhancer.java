package com.sycond.authentication.config;


import com.sycond.authentication.entity.security.User;
import com.sycond.authentication.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class CustomTokenEnhancer extends JwtAccessTokenConverter {

	private AccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		//User user = (User) authentication.getPrincipal();

		Map<String, Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());

        //info.put("user", user); si quiero agregar algo al momento de generar el token

		DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
		customAccessToken.setAdditionalInformation(info);

		return super.enhance(customAccessToken, authentication);
	}


	@Override
	public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		User user = (User) customUserDetailsService.loadUserByUsername(((User) authentication.getPrincipal()).getUsername());
		//token.getAdditionalInformation().put("user",user); add adicional values to token
		return tokenConverter.convertAccessToken(token, authentication);
	}





}