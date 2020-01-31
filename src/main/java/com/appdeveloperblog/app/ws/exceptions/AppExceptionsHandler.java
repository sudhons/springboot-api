package com.appdeveloperblog.app.ws.exceptions;

// import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

// import com.appdeveloperblog.app.ws.ui.model.response.ErrorMessage;
import com.appdeveloperblog.app.ws.ui.model.response.OperationFailedModel;
import com.appdeveloperblog.app.ws.ui.model.response.OperationStatusModel;
// import com.appdeveloperblog.app.ws.ui.model.response.ValidationErrorMessage;

import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// @ControllerAdvice
// public class AppExceptionsHandler {

// @ExceptionHandler(value = { UserServiceException.class })
// public ResponseEntity<Object> handleUserServiceExpection(UserServiceException
// ex) {
// ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());

// return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
// }

// @ExceptionHandler(MethodArgumentNotValidException.class)
// // @ResponseStatus(HttpStatus.BAD_REQUEST)
// // @ResponseBody
// // public ValidationErrorMessage
// // handleValidationExpections(MethodArgumentNotValidException ex) {
// public ResponseEntity<Object>
// handleValidationExpections(MethodArgumentNotValidException ex) {
// Map<String, String> errors = new HashMap<>();

// // ex.getBindingResult().getAllErrors().forEach(error -> {
// // String fieldName = ((FieldError) error).getField();
// // String errorMsg = error.getDefaultMessage();
// // errors.put(fieldName, errorMsg);
// // });

// ex.getBindingResult().getFieldErrors().forEach(error -> {
// String fieldName = error.getField();
// String errorMsg = error.getDefaultMessage();
// errors.put(fieldName, errorMsg);
// });

// ValidationErrorMessage errorMessagesObj = new ValidationErrorMessage(new
// Date(), errors);

// // return ResponseEntity.badRequest().body(errorMessagesObj);
// return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessagesObj);
// // return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
// // return errorMessagesObj;
// }

// @ExceptionHandler(value = { Exception.class })
// public ResponseEntity<Object> handleOtherExpections(Exception ex) {
// ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());

// return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
// }
// }

@RestControllerAdvice
public class AppExceptionsHandler {

  @ExceptionHandler(UserServiceException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public OperationStatusModel<String> handleUserServiceExpection(UserServiceException ex) {

    return new OperationFailedModel<>(ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public OperationStatusModel<Map<String, String>> handleValidationExpections(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(error -> {
      String fieldName = error.getField();
      String fieldErrorMsg = error.getDefaultMessage();

      errors.put(fieldName, fieldErrorMsg);
    });

    return new OperationFailedModel<>(errors);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public OperationStatusModel<String> handleParamsException(ConstraintViolationException ex) {
    ConstraintViolation<?> error = (ConstraintViolation<?>) ex.getConstraintViolations().toArray()[0];

    return new OperationFailedModel<>(error.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public OperationStatusModel<String> handleOtherExpections(Exception ex) {

    return new OperationFailedModel<>(ex.getMessage());
  }
}
