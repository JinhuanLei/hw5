package com.jinhuan.hw5.services;

import com.jinhuan.hw5.models.Calculate;
import com.jinhuan.hw5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@Service
    public class UserService {
       @Autowired
        private UserRepository userRepository;
    HashMap<String, Calculate> hm;
       @PostConstruct
    public void init(){
           hm = userRepository.initDefault();
    }
    public Calculate initialPostCalculate(Calculate cal){
        String operation=cal.getOperation();
        String x=cal.getX();
        String y=cal.getY();
        String hash_alg=cal.getHash_alg();
            Calculate c = hm.get(operation);
            if (x != "") {
                c.setX(x);
            }
            if (y != "") {
                c.setY(y);
            }
            hm.replace(operation,c);
            return calculate(c,operation,hash_alg);
        }
    public Calculate initialGetCalculate(Calculate cal){
 String operation=cal.getOperation();
 String x=cal.getX();
 String y=cal.getY();
 String hash_alg=cal.getHash_alg();
        Calculate c = Calculate.create(hm.get(operation));
        if (x != "") {
            c.setX(x);
        }
        if (y != "") {
            c.setY(y);
        }

        return calculate(c,operation,hash_alg);
    }

        public Calculate calculate(Calculate c,String operation,String hash_alg) {
            BigInteger X = new BigInteger(c.getX());
            BigInteger Y = new BigInteger(c.getY());
            switch (operation) {
                case "add":
                    c.setResult(X.add(Y) + "");
                    //c.getX()+" "+c.getOperation()+" "+c.getY()+" = "+
                    break;
                case "sub":
                    c.setResult(X.subtract(Y) + "");
                    break;
                case "div":
                    c.setResult(X.divide(Y)+"");
//                System.out.println((double) Integer.parseInt(c.getX()) / Integer.parseInt(c.getY()) + "");
                    break;
                case "pow":
                    int temp = Integer.parseInt(c.getY());
                    BigInteger result = X.pow(temp);
                    c.setResult(result + "");
                    break;
                case "mul":
                    c.setResult(X.multiply(Y) + "");
                    break;
            }
            c.setHash_alg(hash_alg);
            String encrypt=c.getX()+" "+c.getOperation()+" "+c.getY()+" = "+c.getResult();
            System.out.println(encrypt);
            switch (hash_alg){
                case "md5":
                    c.setHash(Encrypy_md5(encrypt));
                    break;
                case "sha1":
                    c.setHash(Encrypy_sha1(encrypt));
                    break;
                case "sha256":
                    c.setHash(Encrypy_sha256(encrypt));
                    break;
                case "sha512":
                    c.setHash(Encrypy_sha512(encrypt));
                    break;
            }
            return c;
        }
    public String Encrypy_md5(String str){
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f'};
        try {
            byte[] strTemp = str.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte tmp[] = mdTemp.digest();
            char strs[] = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                strs[k++] = hexDigits[byte0 >>> 4 & 0xf];
                strs[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(strs).toUpperCase();
        } catch (Exception e) {
            return null;
        }
    }

    public String Encrypy_sha1(String str){
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String Encrypy_sha256(String str){
        return SHA(str, "SHA-256");
    }

    public String Encrypy_sha512(String str){
        return SHA(str, "SHA-512");
    }

    private String SHA(final String strText, final String strType)
    {
        String strResult = null;
        if (strText != null && strText.length() > 0)
        {
            try
            {
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                messageDigest.update(strText.getBytes());
                byte byteBuffer[] = messageDigest.digest();
                StringBuffer strHexString = new StringBuffer();
                for (int i = 0; i < byteBuffer.length; i++)
                {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1)
                    {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                strResult = strHexString.toString();
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
        }

        return strResult;
    }
}
