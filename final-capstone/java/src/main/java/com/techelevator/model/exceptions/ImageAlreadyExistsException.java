package com.techelevator.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus( value = HttpStatus.BAD_REQUEST, reason = "Image Already Exists.")
public class ImageAlreadyExistsException extends RuntimeException
{
	
	
}
