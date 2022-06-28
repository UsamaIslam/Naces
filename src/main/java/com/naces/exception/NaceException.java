package com.naces.exception;

import com.naces.dto.NaceStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class NaceException {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationError onConstraintValidationException(ConstraintViolationException e) {
        ValidationError validationError = new ValidationError();
        e.getConstraintViolations().forEach(error -> validationError.getErrorResponses()
            .add(new ErrorResponse(error.getPropertyPath().toString(), error.getMessage(),null)));
        return validationError;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationError onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationError validationError = new ValidationError();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> validationError.getErrorResponses()
            .add(new ErrorResponse(fieldError.getField(), fieldError.getDefaultMessage(), null)));
        return validationError;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ErrorResponse entityNotFoundException(EntityNotFoundException e) {
        return new ErrorResponse(null, NaceStatus.valueOf(HttpStatus.NOT_FOUND.name()).code, HttpStatus.NOT_FOUND);
    }

}
