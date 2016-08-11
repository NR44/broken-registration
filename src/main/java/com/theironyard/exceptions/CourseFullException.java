package com.theironyard.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by jeff on 8/8/16.
 */
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Class is full")
public class CourseFullException extends RuntimeException{
}
