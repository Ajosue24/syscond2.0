package com.sycond.authentication.controller;

import com.sycond.authentication.entity.security.User;
import com.sycond.authentication.response.JsendResponse;
import com.sycond.authentication.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping(
        value = {"/oauth"},
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Validated
public class TokenController {

    @Autowired
    private DefaultTokenServices tokenServices;

    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    CheckTokenEndpoint checkTokenEndpoint;

    @RequestMapping(method = RequestMethod.DELETE, path = "/logout")
    @ResponseStatus(HttpStatus.OK)
    public void revokeToken(Authentication authentication) {
        final String userToken = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
        tokenServices.revokeToken(userToken);
    }


    @RequestMapping(method = RequestMethod.POST, path = "/login")
    @ResponseStatus(HttpStatus.OK)
    public JsendResponse<?> login(Principal principal, @RequestParam
            Map<String, String> parameters){
        onlyOneUserPerSession(parameters.get("username"));
        try {
            final ResponseEntity<OAuth2AccessToken> token = tokenEndpoint.postAccessToken(principal,parameters);
            User user = (User) customUserDetailsService.loadUserByUsername(principal.getName());
            //token.getBody().getAdditionalInformation().put("user",user);
   /*         if(parameters.containsKey("fcm")||parameters.containsKey("FCM")){
                customUserDetailsService.saveToken(user,parameters.get("fcm")!=null?parameters.get("fcm"):parameters.get("FCM"));
            }*/
            return new JsendResponse<>().sendSuccess(token.getBody(),"");
        }catch (Exception e){
            return null;
        }
    }

    /**
     * Solo un usuario por session
     */
    private void onlyOneUserPerSession(String user){

    }


    @RequestMapping(method = RequestMethod.GET, path = "/validate_token")
    @ResponseStatus(HttpStatus.OK)
    public JsendResponse<?> validateToken(@RequestParam("token") String value){
        Map<String, ?> token = checkTokenEndpoint.checkToken(value);
        try {
            return new JsendResponse<>().sendSuccess(token,"");
        }catch (Exception e){
            return new JsendResponse<>().sendFailureBySystem();
        }

    }





}