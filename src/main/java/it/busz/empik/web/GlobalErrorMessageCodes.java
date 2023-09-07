package it.busz.empik.web;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class GlobalErrorMessageCodes {
    public static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";
    public static final String RESOURCE_NOT_FOUND = "NOT_FOUND";
    public static final String LOGIN_CANNOT_BE_EMPTY = "LOGIN_CANNOT_BE_EMPTY";
}
