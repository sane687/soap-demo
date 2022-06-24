package ru.alex.soapdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alex.soapdemo.DTO.*;
import ru.alex.soapdemo.model.Role;
import ru.alex.soapdemo.model.User;
import ru.alex.soapdemo.repository.RoleRepository;
import ru.alex.soapdemo.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Класс реализующий логику обработки пользователей
 */
@Service
@Transactional
public class UserService{

    UserRepository userRepository;
    RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public UserDTO findUserByUsername(String name){
        UserDTO userDTO = new UserDTO();
        User user = userRepository.findByUsername(name);
        userDTO.setLogin(user.getLogin());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(new HashSet<>(user.getRoles()));

        return userDTO;
    }

    public List<UserDTO> findAllDTOUsers(){
        List<User> userList = findAllUsers();
        List<UserDTO> userDTOList = new ArrayList<>();

        for(User user : userList){
            UserDTO userDTO = new UserDTO(user.getLogin(), user.getUsername(), user.getPassword());
            userDTOList.add(userDTO);
        }

        return userDTOList;
    }

    public Set<RoleDTO> findAllDTORoles() {

        Set<Role> roleList = new HashSet<>(roleRepository.findAll());
        Set<RoleDTO> roleDTOList = new HashSet<>();

        for(Role role : roleList){
            RoleDTO roleDTO = new RoleDTO(role.getName());
            roleDTOList.add(roleDTO);
        }
        return roleDTOList;
    }

    public boolean deleteUserByUsername(String username){
        if(userRepository.findByUsername(username)==null){
            return false;
        }
        userRepository.deleteUserByUsername(username);
        return true;
    }

    public List<String> createUser(BaseRequest request){

        List<String> errors = isValid(request);

        if(!errors.isEmpty()){
            return errors;
        }

        User user = new User();
        user.setLogin(request.getLogin());

        setUserFields(request, user);

        if(userRepository.findUserByLogin(request.getLogin())!=null){
            errors.add("Пользователь с логином \"" + request.getLogin() + "\" существует");
            return errors;
        }
        userRepository.save(user);
        return errors;
    }

    /**
     * Метод проверяющий корректность данных пользователя
     * @param request запрос содержащий данные пользователя
     * @return возвращает список ошибок валидации
     */
    public List<String> isValid(BaseRequest request) {
        List<String> validationErrors = new ArrayList<>();

        if (!request.getUsername().matches("[А-Яа-я]+")
                || request.getUsername().length() > 15
                || request.getUsername().length() < 3) {
            validationErrors.add("Имя пользователя должен содержать только русские буквы и быть длинной от 3 до 15 символов");
        }

        if (!request.getLogin().matches("[A-Za-z]+")
                || request.getLogin().length() > 15
                || request.getLogin().length() < 3) {
            validationErrors.add("Логин должен содержать только английские буквы и быть длинной от 3 до 15 символов");
        }

        if (!request.getPassword().matches("(?=.*[A-Z])(?=.*[\\d])\\w+")
                || request.getPassword().length() > 15
                || request.getPassword().length() < 3) {
            validationErrors.add("Пароль должен содержать одну заглавную букву и символ, и быть длинной от 3 до 15 символов");
        }

        if (request.getRoles().isEmpty()) {
            validationErrors.add("Роль не может быть пустой");
        }

        Set<RoleDTO> roleDTOList = findAllDTORoles();

        for (RoleDTO roleDTO : request.getRoles()) {

            long count = roleDTOList.stream()
                    .filter(role -> role.getName().equals(roleDTO.getName())).count();
            if (count == 0) {
                validationErrors.add("Роли \"" + roleDTO.getName() + "\" не существует");
            }
        }
        return validationErrors;
        }


    public List<String> updateUser(BaseRequest request){
        List<String> errors = isValid(request);
        User user = userRepository.findUserByLogin(request.getLogin());
        if(user==null){
            errors.add("Пользователь с таким логином не найден");
            return errors;
        }

        setUserFields(request, user);
        userRepository.save(user);
        return errors;
    }

    /**
     * Инициализирует поля пользователя общие для методов добавления и редактирование
     * @param request
     * @param user
     */
    private void setUserFields(BaseRequest request, User user) {
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        List<Role> userRoles = new ArrayList<>();
        for(RoleDTO roleDTO : request.getRoles()){
            userRoles.add(roleRepository.findRoleByName(roleDTO.getName()));
        }
        user.setRoles(userRoles);
    }

}
