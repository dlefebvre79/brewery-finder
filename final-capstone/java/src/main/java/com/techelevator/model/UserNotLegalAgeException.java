package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST, reason = "User Not of Legal Drinking Age.")
public class UserNotLegalAgeException extends RuntimeException {

}
