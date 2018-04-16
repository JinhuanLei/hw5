package com.jinhuan.hw5.repositories;

import com.jinhuan.hw5.models.Calculate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
@Repository
public class UserRepository {
    HashMap<String, Calculate> hm = new HashMap<>();
    public HashMap initDefault(){
        Calculate add = new Calculate();
        add.setOperation("+");
        add.setX("0");
        add.setY("0");
        Calculate sub = new Calculate();
        sub.setOperation("-");
        sub.setX("1");
        sub.setY("0");
        Calculate div = new Calculate();
        div.setOperation("÷");
        div.setX("4");
        div.setY("1");
        Calculate mul = new Calculate();
        mul.setOperation("x");
        mul.setX("2");
        mul.setY("3");
        Calculate pow = new Calculate();
        pow.setOperation("^");
        pow.setX("8");
        pow.setY("2");

        hm.put("add", add);
        hm.put("sub", sub);
        hm.put("div", div);
        hm.put("mul", mul);
        hm.put("pow", pow);
        System.out.println(hm);
        return hm;
    }
}
