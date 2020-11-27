package com.comit.service;

import com.comit.execption.UserNotFoundException;
import com.comit.model.ERole;
import com.comit.model.Role;
import com.comit.model.User;
import com.comit.payload.UserForm;
import com.comit.repository.RoleRepository;
import com.comit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

/**
 * Sistemde kayıtlı User sınıfının CRUD işlemlerinin gerçekleştirildiği service sınıfıdır.
 */
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * User nesnesi oluşturmak için kullanılan metod. UserForm nesnesi üzerinden User rolü default olacak şekilde
     * yeni kullanıcı oluşturur.
     *
     * @param model Yeni eklenecek olan User nesnesine ait bilgileri tutan UserForm nesnesi
     * @return Yeni eklenen user nesnesi
     */
    public User createUser(UserForm model) {
        User newUser = new User(model.getUsername(), model.getPassword(),
                model.getName(), model.getSurName());
        newUser.setRoles(new HashSet<>(Collections.singletonList(new Role(ERole.USER))));
        userRepository.save(newUser);
        return newUser;
    }

    /**
     * User nesnesi oluşturmak için kullanılan metod. User nesnesi üzerinden yeni User'ı repository'e kayıt eder.
     *
     * @param model Eklenecek olan User nesnesi
     */
    public void createUserWithSecurity(User model) {
        userRepository.save(model);
    }

    /**
     * Verilen username değeri ile bu username değerine sahip User nesnesi döner
     *
     * @param username Aranan username değeri
     * @return username değerine sahip user nesnesi
     */
    public User getUserByUserName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    /**
     * Verilen id değeri ile bu id değerine sahip User nesnesi döner
     *
     * @param id Aranan id değeri
     * @return id değerine sahip user nesnesi
     */
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Verilen username değeri ile bu username değerine sahip User nesnesi arayan metod
     *
     * @param username Aranan username değeri
     * @return ilgili User nesnesi varsa true, yoksa false döner
     */
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
