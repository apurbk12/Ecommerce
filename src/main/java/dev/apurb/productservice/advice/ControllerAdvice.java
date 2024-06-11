package dev.apurb.productservice.advice;

import dev.apurb.productservice.DTOs.ErrorDTO;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDTO> handleNullPointerException(Exception ex){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage("Something went wrong!");
        ResponseEntity<ErrorDTO> responseEntity = new ResponseEntity(errorDTO, HttpStatusCode.valueOf(404));
        return responseEntity;
    }
}
