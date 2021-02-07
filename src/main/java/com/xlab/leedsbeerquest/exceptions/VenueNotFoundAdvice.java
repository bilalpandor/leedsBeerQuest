package com.xlab.leedsbeerquest.exceptions;

import com.xlab.leedsbeerquest.exceptions.VenueNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
class VenueNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(VenueNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String venueNotFoundException(VenueNotFoundException ex) {
        return ex.getMessage();
    }

}
