package com.tineo.wallet_backend.config;

public class Constant {

    public static final String API_VERSION = "/api/v1";

    // General messages
    public static final String ERROR_PARAMETER_TYPE_MISMATCH = "The parameter '%s' must be of type '%s'";
    public static final String ERROR_DETAILS = "[%s] %s";

    // User messages
    public static final String API_VERSION_USERS = API_VERSION + "/users";
    public static final String API_VERSION_USERS_ID = "/{id}";
    public static final String API_VERSION_USERS_PAGINATE = "/paginate";

    public static final String USER_FOUND = "Users found";
    public static final String USER_NOT_FOUND = "No users found";

    public static final String USER_BY_ID_FOUND = "User by id found";
    public static final String USER_NOT_FOUND_BY_ID = "User not found with id: ";
    public static final String USER_NOT_FOUND_BY_USERNAME = "User not found with username: ";

    public static final String USER_BY_ID_DELETED = "User by id deleted";

    public static final String USER_USERNAME_EXISTS = "User by username already exists";

    public static final String USER_CREATED = "User created";
    public static final String USER_UPDATED = "User updated";

    public static final String PAGE_SIZE_FOUND = "Page and size found";
    public static final String PAGE_SIZE_ERROR = "Page and size must be greater than 0";

    // Error jwt messages
    public static final String ERROR_JWT_EXPIRED_TOKEN = "Token has expired - ";
    public static final String ERROR_JWT_INVALID_TOKEN = "Token is invalid - ";
    public static final String ERROR_JWT_ILLEGAL_TOKEN = "Token is malformed - ";

    // Auth messages
    public static final String API_VESION_AUTH = API_VERSION + "/auth";
    public static final String API_VERSION_AUTH_LOGIN = "/login";
    public static final String API_VERSION_AUTH_REGISTER = "/register";

    public static final String TOKEN_CREATED_LOGIN = "Token created - Login";
    public static final String TOKEN_CREATED_REGISTER = "Token created - Register";

    // H2 route
    public static final String H2_ENDPOINT = "/h2-console";

}
