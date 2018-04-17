package com.jinhuan.hw5.controllers;


import com.jinhuan.hw5.exceptions.BadRequestException;
import com.jinhuan.hw5.exceptions.DivideZeroException;
import com.jinhuan.hw5.models.Calculate;
import com.jinhuan.hw5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.regex.Pattern;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/calculator", method = RequestMethod.GET)
    public ModelAndView index() {
        System.out.println("start mvc");
        return new ModelAndView("index.html");

    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
        return pattern.matcher(str).matches();
    }
    @RequestMapping(value = "/api/v1/{operation}", method = RequestMethod.POST)
    public Calculate calculateSaveDefault(@PathVariable String operation, @RequestParam String x, @RequestParam String y, @RequestHeader("hash-alg") String hash_alg) throws BadRequestException, DivideZeroException {
        if (x != "" && !(isInteger(x))) {
            throw new BadRequestException();
        }
        if (y != "" && !isInteger(y)) {
            throw new BadRequestException();
        }
        if(operation.equals("div")&&y.equals("0")){
            throw new DivideZeroException();
        }
        Calculate cal= new Calculate(new Calculate.Builder().x(x).y(y).operation(operation).hash_alg(hash_alg));
        return userService.initialPostCalculate(cal);
    }


    @RequestMapping(value = "/api/v1/{operation}", method = RequestMethod.GET)
    public Calculate calculateDefault(@PathVariable String operation, @RequestParam String x, @RequestParam String y, @RequestHeader("hash-alg") String hash_alg) throws BadRequestException, DivideZeroException {
        if (x != "" && !(isInteger(x))) {
            throw new BadRequestException();
        }
        if (y != "" && !isInteger(y)) {
            throw new BadRequestException();
        }
        if(operation.equals("div")&&y.equals("0")){
            throw new DivideZeroException();
        }
        Calculate cal= new Calculate(new Calculate.Builder().x(x).y(y).operation(operation).hash_alg(hash_alg));

        return userService.initialGetCalculate(cal);


    }





}

