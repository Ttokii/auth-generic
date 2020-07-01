package com.tokii.common.authgeneric.config;

import com.tokii.common.authgeneric.component.GenericPasswordEncoder;
import com.tokii.common.authgeneric.component.GenericUserDetailsComponent;
import com.tokii.common.authgeneric.exception.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GenericAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired GenericUserDetailsComponent genericUserDetailsComponent;
    @Autowired GenericPasswordEncoder genericPasswordEncoder;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        if (usernamePasswordAuthenticationToken.getCredentials() == null) {
            throw new BadCredentialsException(this.messages.getMessage(ErrorInfo.INVALID_ACCOUNT_NAME.summary, ErrorInfo.INVALID_ACCOUNT_NAME.desc));
        } else {
            String presentedPassword = usernamePasswordAuthenticationToken.getCredentials().toString();
            if (!this.genericPasswordEncoder.matches(presentedPassword, userDetails.getPassword())) {
                throw new BadCredentialsException(this.messages.getMessage(ErrorInfo.INVALID_PASSWORD.summary, ErrorInfo.INVALID_PASSWORD.desc));
            }
        }
    }

    @Override
    protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        return genericUserDetailsComponent.loadUserByUsername(userName);
    }


}
