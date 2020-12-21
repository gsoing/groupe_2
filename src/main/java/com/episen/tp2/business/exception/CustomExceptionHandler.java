package com.episen.tp2.business.exception;

import com.episen.tp2.business.model.Error;
import com.episen.tp2.business.model.ErrorDefinition;
import com.episen.tp2.business.model.ErrorTypeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("404", ex.getMessage()));
        ErrorDefinition error = new ErrorDefinition(ErrorTypeEnum.FUNCTIONAL, errors);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Object> handleBadRequestException(Exception ex, WebRequest request) {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("400", ex.getMessage()));
        ErrorDefinition error = new ErrorDefinition(ErrorTypeEnum.TECHNICAL, errors);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LockException.class)
    public final ResponseEntity<Object> handleLockedException(Exception ex, WebRequest request) {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("423", ex.getMessage()));
        ErrorDefinition error = new ErrorDefinition(ErrorTypeEnum.FUNCTIONAL, errors);
        return new ResponseEntity(error, HttpStatus.LOCKED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public final ResponseEntity<Object> handleForbiddenException(Exception ex, WebRequest request) {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("403", ex.getMessage()));
        ErrorDefinition error = new ErrorDefinition(ErrorTypeEnum.TECHNICAL, errors);
        return new ResponseEntity(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ConflictException.class)
    public final ResponseEntity<Object> handleConflictException(Exception ex, WebRequest request) {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("409", ex.getMessage()));
        ErrorDefinition error = new ErrorDefinition(ErrorTypeEnum.FUNCTIONAL, errors);
        return new ResponseEntity(error, HttpStatus.CONFLICT);
    }





}
