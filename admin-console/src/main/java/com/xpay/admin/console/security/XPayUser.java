package com.xpay.admin.console.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Map;

public class XPayUser extends User {

    private Map<String, Object> merchantBasic;

    /**
     * Calls the more complex constructor with all boolean arguments set to {@code true}.
     *
     * @param username
     * @param password
     * @param authorities
     */
    public XPayUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public Map<String, Object> getMerchantBasic() {
        return merchantBasic;
    }

    public void setMerchantBasic(Map<String, Object> merchantBasic) {
        this.merchantBasic = merchantBasic;
    }

    public int getMerchantId() {
        return Integer.parseInt(merchantBasic.get("merchantId").toString());
    }

}
