package com.apress.springrecipes.board.security;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;


public class IpAddressVoter implements AccessDecisionVoter<Object> {

    private static final String IP_PREFIX = "IP_";
    private static final String IP_LOCAL_HOST = "IP_LOCAL_HOST";

    public boolean supports(ConfigAttribute attribute) {
        return (attribute.getAttribute() != null) && attribute.getAttribute().startsWith(IP_PREFIX);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> configList) {
        if (!(authentication.getDetails() instanceof WebAuthenticationDetails)) {
            return ACCESS_DENIED;
        }

        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String address = details.getRemoteAddress();

        int result = ACCESS_ABSTAIN;

        for (ConfigAttribute config : configList) {
            result = ACCESS_DENIED;

            if (Objects.equals(IP_LOCAL_HOST, config.getAttribute())) {
                if (address.equals("127.0.0.1") || address.equals("0:0:0:0:0:0:0:1")) {
                    return ACCESS_GRANTED;
                }
            }
        }

        return result;
    }
}
