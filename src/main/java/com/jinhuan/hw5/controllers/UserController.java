package com.jinhuan.hw5.controllers;


import com.jinhuan.hw5.exceptions.BadRequestException;
import com.jinhuan.hw5.models.Calculate;
import com.jinhuan.hw5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.regex.Pattern;

@RestController
public class UserController {
//    HashMap<String, Calculate> hm = new HashMap<>();
    @Autowired
    private UserService userService;
//    @PostConstruct
//    public void init() {
//        Calculate add = new Calculate();
//        add.setOperation("+");
//        add.setX("0");
//        add.setY("0");
//        Calculate sub = new Calculate();
//        sub.setOperation("-");
//        sub.setX("1");
//        sub.setY("0");
//        Calculate div = new Calculate();
//        div.setOperation("÷");
//        div.setX("4");
//        div.setY("1");
//        Calculate mul = new Calculate();
//        mul.setOperation("x");
//        mul.setX("2");
//        mul.setY("3");
//        Calculate pow = new Calculate();
//        pow.setOperation("^");
//        pow.setX("8");
//        pow.setY("2");
//
//        hm.put("add", add);
//        hm.put("sub", sub);
//        hm.put("div", div);
//        hm.put("mul", mul);
//        hm.put("pow", pow);
//        System.out.println(hm);
//
//    }

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
    public Calculate calculateSaveDefault(@PathVariable String operation, String x, String y, @RequestHeader("hash-alg") String hash_alg) throws BadRequestException {
        if (x != "" && !(isInteger(x))) {
            throw new BadRequestException();
        }
        if (y != "" && !isInteger(y)) {
            throw new BadRequestException();
        }
//        Calculate c = hm.get(operation);
//        if (x != "") {
//            c.setX(x);
//        }
//        if (y != "") {
//            c.setY(y);
//        }

        return userService.initialPostCalculate(x,y,operation,hash_alg);
    }


    @RequestMapping(value = "/api/v1/{operation}", method = RequestMethod.GET)
    public Calculate calculateDefault(@PathVariable String operation, @RequestParam String x, @RequestParam String y, @RequestHeader("hash-alg") String hash_alg) throws BadRequestException {
        if (x != "" && !(isInteger(x))) {
            throw new BadRequestException();
        }
        if (y != "" && !isInteger(y)) {
            throw new BadRequestException();
        }

//        Calculate c = Calculate.create(hm.get(operation));
//        System.out.println(c);
//        if (x != "") {
//            c.setX(x);
//        }
//        if (y != "") {
//            c.setY(y);
//        }
        return userService.initialGetCalculate(x,y,operation,hash_alg);


    }





}

