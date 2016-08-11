package com.theironyard.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by jeff on 8/8/16.
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Not the professor of the class")
public class NotClassOwnerException extends RuntimeException {
}
