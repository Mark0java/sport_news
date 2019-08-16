package com.turtles.sport_news;

import com.turtles.sport_news.entity.Privilege;
import com.turtles.sport_news.entity.Role;
import com.turtles.sport_news.entity.User;
import com.turtles.sport_news.repository.PrivilegeRepository;
import com.turtles.sport_news.repository.RoleRepository;
import com.turtles.sport_news.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {



    boolean alreadySetup = false;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
                Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        if(userRepository.getOneByEmail("admin@test.com") == null){
            Role adminRole = roleRepository.findByName("ROLE_ADMIN");
            User user = new User();
            user.setName("Admin");
            user.setSurname("Admin");
            user.setPassword(passwordEncoder.encode("test"));
            user.setEmail("admin@test.com");
            user.setRoles(Arrays.asList(adminRole));
            userRepository.save(user);
        }

        alreadySetup = true;
    }

    @Transactional
    public Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    public Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges((List<Privilege>) privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
