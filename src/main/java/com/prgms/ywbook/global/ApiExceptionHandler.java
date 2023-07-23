package com.prgms.ywbook.global;

import com.prgms.ywbook.book.exception.InvalidLengthException;
import com.prgms.ywbook.global.exception.NotFoundEntityException;
import com.prgms.ywbook.global.exception.NotUpdateException;
import com.prgms.ywbook.member.exception.InvalidNumberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(NotFoundEntityException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getErrorResponse(400, e));
    }

    @ExceptionHandler(InvalidLengthException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInputException(InvalidLengthException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getErrorResponse(400, e));
    }

    @ExceptionHandler(NotUpdateException.class)
    public ResponseEntity<ErrorResponse> handleNotUpdateException(NotUpdateException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getErrorResponse(500, e));
    }

    @ExceptionHandler(InvalidNumberException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDiscountException(InvalidNumberException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getErrorResponse(400, e));
    }

    private static ErrorResponse getErrorResponse(int status, Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(status, e.getMessage());
        return errorResponse;
    }
}
