package com.smart.config;

import java.util.Collection;
import java.util.List;

import com.smart.entities.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        super();
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Assuming your user has a role attribute
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        return List.of(authority);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Assuming account is not expired. Modify as needed.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Assuming account is not locked. Modify as needed.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Assuming credentials are not expired. Modify as needed.
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled(); // Assuming you have an 'enabled' field in the User entity
    }
}


//package com.smart.config;
//
//import java.util.Collection;
//import java.util.List;
//
//import com.smart.entities.User;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class CustomUserDetails implements UserDetails{
//
//	//this is first step for authentication
//	
//	private User user;
//	
//	
//	public CustomUserDetails(User user) {
//		super();
//		this.user = user;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		SimpleGrantedAuthority simpleGrantedAuthority= new SimpleGrantedAuthority(user.getPassword());
//		return List.of(simpleGrantedAuthority);
//	}
//
//	@Override
//	public String getPassword() {
//		return user.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		 return user.getEmail();
//	}
//
//}
