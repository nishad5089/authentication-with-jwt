package com.commonauthmodule.commonauthmodule.service;

import com.commonauthmodule.commonauthmodule.entity.MyUserDetails;
import com.commonauthmodule.commonauthmodule.entity.User;
import com.commonauthmodule.commonauthmodule.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;
    public UserDetailsServiceImpl() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUserName(username);
        user.orElseThrow(()-> new UsernameNotFoundException("Not Found: "+ username));
        return user.map(MyUserDetails::new).get();
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<SimpleGrantedAuthority> roles=null;
//        if(username.equals("admin"))
//        {
//            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
//            return new User("admin", "$2y$12$I0Di/vfUL6nqwVbrvItFVOXA1L9OW9kLwe.1qDPhFzIJBpWl76PAe",
//                    roles);
//        }
//        else if(username.equals("user"))
//        {
//            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
//            return new User("user", "$2y$12$VfZTUu/Yl5v7dAmfuxWU8uRfBKExHBWT1Iqi.s33727NoxHrbZ/h2",
//                    roles);
//        }
//        throw new UsernameNotFoundException("User not found with username: " + username);
//    }
//
//    List<SimpleGrantedAuthority> roles=null;
//		if(username.equals("admin"))
//    {
//        roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        return new User("admin", "$2y$12$I0Di/vfUL6nqwVbrvItFVOXA1L9OW9kLwe.1qDPhFzIJBpWl76PAe",
//                roles);
//    }
//		else if(username.equals("user"))
//    {
//        roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
//        return new User("user", "$2y$12$VfZTUu/Yl5v7dAmfuxWU8uRfBKExHBWT1Iqi.s33727NoxHrbZ/h2",
//                roles);
//    }
//		throw new UsernameNotFoundException("User not found with username: " + username);
}
