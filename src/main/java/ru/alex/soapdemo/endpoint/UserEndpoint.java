package ru.alex.soapdemo.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.alex.soapdemo.DTO.*;
import ru.alex.soapdemo.service.UserService;
import java.util.List;

/**
 * Эндпоинт класс для обработки запросов
 */
@Endpoint
public class UserEndpoint {

    UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    /**
     * Обработчик запроса пользователя
     * @param request объект запроса содержащий данные пользователя
     * @return объект содержащий данные пользователя для представления
     */
    @PayloadRoot(namespace = "http://alex.ru/soapdemo/DTO",
        localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUserRequest(@RequestPayload GetUserRequest request){
        GetUserResponse response = new GetUserResponse();
        response.setUser(userService.findUserByUsername(request.getUsername()));
        return response;
    }

    /**
     * Обработчик запроса на всех пользователей
     * @param request объект запроса содержащий данные пользователей
     * @return объект содержащий данные пользователей для представления
     */
    @PayloadRoot(namespace = "http://alex.ru/soapdemo/DTO",
            localPart = "getAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsersRequest(@RequestPayload GetAllUsersRequest request){
        GetAllUsersResponse response = new GetAllUsersResponse();
        response.setUserList(userService.findAllDTOUsers());
        return response;
    }
    /**
     * Обработчик запроса на удаление пользователя
     * @param request объект запроса содержащий данные пользователя
     * @return объект содержащий данные о статусе операции
     */
    @PayloadRoot(namespace = "http://alex.ru/soapdemo/DTO",
            localPart = "deleteUserRequest")
    @ResponsePayload
    public DeleteUserResponse deleteUserRequest(@RequestPayload DeleteUserRequest request){
        DeleteUserResponse response = new DeleteUserResponse();
        response.setSuccess(userService.deleteUserByUsername(request.getUsername()));
        return response;
    }

    /**
     * Обработчик запроса на создание пользователя
     * @param request объект запроса содержащий данные пользователя
     * @return объект содержащий данные о статусе операции
     */
    @PayloadRoot(namespace = "http://alex.ru/soapdemo/DTO",
            localPart = "createUserRequest")
    @ResponsePayload
    public CreateUserResponse createUserRequest(@RequestPayload CreateUserRequest request){

        CreateUserResponse response = new CreateUserResponse();
        List<String> errors = userService.createUser(request);
        if(!errors.isEmpty()){
            response.setSuccess(false);
            response.setErrors(errors);
            return response;
        }
        response.setSuccess(true);
        return response;
    }
    /**
     * Обработчик запроса на изменение пользователя
     * @param request объект запроса содержащий данные пользователя
     * @return объект содержащий данные о статусе операции
     */
    @PayloadRoot(namespace = "http://alex.ru/soapdemo/DTO",
            localPart = "updateUserRequest")
    @ResponsePayload
    public UpdateUserResponse updateUserRequest(@RequestPayload UpdateUserRequest request){

        UpdateUserResponse response = new UpdateUserResponse();
        List<String> errors = userService.updateUser(request);

        if(!errors.isEmpty()){
            response.setSuccess(false);
            response.setErrors(errors);
            return response;
        }
        response.setSuccess(true);
        return response;
    }


}
