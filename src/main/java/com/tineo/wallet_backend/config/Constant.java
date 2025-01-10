package com.tineo.wallet_backend.config;

public class Constant {

    // API Version
    public static final String API_VERSION = "/api/v1";

    // GlobalResponse - error messages
    public static final String GLOBAL_RESPONSE_ERROR_PARAMETER_TYPE = "The parameter '%s' must be of type '%s'";
    public static final String GLOBAL_RESPONSE_ERROR_DETAILS = "[%s] %s";

    // User - endpoints
    public static final String API_ENDPOINT_USERS = API_VERSION + "/users";
    public static final String ENDPOINT_USERS_ID = "/{id}";
    public static final String ENDPOINT_USERS_PAGINATE = "/paginate";

    // User - messages GET
    public static final String USER_FOUND = "Users found";
    public static final String USER_FOUND_BY_ID = "User found by ID";

    public static final String USER_NOT_FOUND = "No users found";
    public static final String USER_NOT_FOUND_BY_ID = "User not found -> ID: ";
    public static final String USER_NOT_FOUND_BY_USERNAME = "User not found -> Username: ";

    public static final String PAGE_SIZE_FOUND = "Page size found";
    public static final String PAGE_SIZE_ERROR = "Page and size must be greater than 0";

    // User - messages POST & PUT
    public static final String USER_CREATED = "User successfully created";
    public static final String USER_UPDATED = "User successfully updated";
    public static final String USER_USERNAME_EXISTS = "User with the specified username already exists";

    // User - messages DELETE
    public static final String USER_DELETED_BY_ID = "User deleted by ID";

    // Auth - jwt error messages
    public static final String ERROR_JWT_EXPIRED_TOKEN = "Token has expired - ";
    public static final String ERROR_JWT_INVALID_TOKEN = "Token is invalid - ";
    public static final String ERROR_JWT_ILLEGAL_TOKEN = "Token is malformed - ";

    // Auth - endpoints
    public static final String API_ENDPOINT_AUTH = API_VERSION + "/auth";
    public static final String ENDPOINT_AUTH_LOGIN = "/login";
    public static final String ENDPOINT_AUTH_REGISTER = "/register";

    // Auth - messages POST
    public static final String TOKEN_CREATED_LOGIN = "Token successfully created - Login";
    public static final String TOKEN_CREATED_REGISTER = "Token successfully created - Register";

    // H2 - endpoint
    public static final String ENDPOINT_H2 = "/h2-console";

    // Swagger - endpoint
    public static final String ENDPOINT_SWAGGER = "/swagger-ui";
    public static final String ENDPOINT_SWAGGER_RESOURCES = "/v3/api-docs";
}
