package com.shop.service.configurations.exceptions.handlers;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shop.service.configurations.exceptions.CustomResponseException;
import com.shop.service.configurations.exceptions.responses.CustomResponseBody;
import com.shop.service.controllers.CargoController;
import com.shop.service.controllers.ClientController;
import com.shop.service.controllers.DrinkController;
import com.shop.service.controllers.OrderController;
import com.shop.service.controllers.OrderDrinkController;
import com.shop.service.controllers.OrderDrinkReceiptController;
import com.shop.service.controllers.ReceiptController;

@RestControllerAdvice(assignableTypes = { CargoController.class, ClientController.class,
        DrinkController.class, OrderController.class, OrderDrinkController.class, OrderDrinkReceiptController.class,
        ReceiptController.class })
public class GlobalExceptionHandler {

    private final Logger logger = LogManager.getLogger();

    @ExceptionHandler(CustomResponseException.class)
    public ResponseEntity<?> handleCustomResponseException(HttpServletRequest request,
            Exception ex) {
        logger.error("handleCustomResponseException {}\n", request.getRequestURI(),
                ex);

        int status = 400;
        String message = ex.getMessage();

        return ResponseEntity.status(400)
                .body(new CustomResponseBody(status, message)); // 400 Bad Request

    }

}
