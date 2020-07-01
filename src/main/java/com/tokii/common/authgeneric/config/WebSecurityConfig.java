package com.tokii.common.authgeneric.config;

import com.tokii.common.authgeneric.constants.AuthConstants;
import com.tokii.common.authgeneric.exception.AuthException;
import com.tokii.common.authgeneric.exception.ErrorInfo;
import com.tokii.common.authgeneric.filter.UserPwdAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${auth.generic.type}") String authType;
    @Value("${auth.generic.static-resource}") String staticResource;
    @Value("${auth.generic.login-page}") String loginPage;
    @Value("${auth.generic.login-processing-url}") String loginProcessingUrl;
    @Value("${auth.generic.success-forward-url}") String successForwardUrl;

    @Autowired AuthSuccessHandler AuthSuccessHandler;
    @Autowired AuthFailureHandler authFailureHandler;
    @Autowired UserAccessDeniedHandler userAccessDeniedHandler;
    @Autowired GenericAuthenticationProvider genericAuthenticationProvider;
    @Autowired NotAuthEntryPoint notAuthEntryPoint;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public UserPwdAuthenticationFilter userPwdAuthenticationFilter() throws Exception {
        UserPwdAuthenticationFilter userPwdAuthenticationFilter = new UserPwdAuthenticationFilter();
        userPwdAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        userPwdAuthenticationFilter.setFilterProcessesUrl(loginProcessingUrl);
        userPwdAuthenticationFilter.setAuthenticationSuccessHandler(AuthSuccessHandler);
        userPwdAuthenticationFilter.setAuthenticationFailureHandler(authFailureHandler);
        return userPwdAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if(StringUtils.equalsIgnoreCase(authType, AuthConstants.AUTH_TYPE_FORM)) {
            this.formAuth(http);
        } else if(StringUtils.equalsIgnoreCase(authType, AuthConstants.AUTH_TYPE_AJAX)) {
            this.ajaxAuth(http);
        } else if(StringUtils.equalsIgnoreCase(authType, AuthConstants.AUTH_TYPE_OAUTH)) {
            this.oAuth2(http);
        } else {
            throw new AuthException(ErrorInfo.INVALID_AUTH_TYPE);
        }

    }

    /**
     * 表单认证
     */
    private void formAuth(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(staticResource).permitAll()
                .antMatchers("/api/user").hasRole("USER")
                .antMatchers("/api/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().authenticationProvider(genericAuthenticationProvider)
                .formLogin().loginPage(loginPage).loginProcessingUrl(loginProcessingUrl).permitAll()
                .and().exceptionHandling().accessDeniedHandler(userAccessDeniedHandler).authenticationEntryPoint(notAuthEntryPoint)
                .and().sessionManagement().maximumSessions(1);
        http.addFilterAt(userPwdAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();
    }

    /**
     * ajax认证
     */
    private void ajaxAuth(HttpSecurity http) throws Exception {
        // TODO
    }

    /**
     * oauth认证
     */
    private void oAuth2(HttpSecurity http) throws Exception {
        // TODO
    }
}
