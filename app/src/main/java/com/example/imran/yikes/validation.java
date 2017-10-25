package com.example.imran.yikes;

/**
 * Created by IMRAN_SHAIK on 6/6/2017.
 */

import java.util.regex.Pattern;

public class validation {

    private final Pattern hasUppercase = Pattern.compile("[A-Z]");
    private final Pattern hasLowercase = Pattern.compile("[a-z]");
    private final Pattern hasNumber = Pattern.compile("[0-9]");
    private final Pattern hasSpecialChar = Pattern.compile("[@$#&_]");
    private final Pattern hasSpace = Pattern.compile("[ ]");

    public String userValidation(String emp_id){
        if(emp_id.equals("")) {
            return ("Username  cannot be blank");
        }



        return "true";
    }
    public String nameValidation(String emp_name){
        if(emp_name.equals("")){
            return(" name cannot be blank");
        }
        if(emp_name.length()<6){
            return("Should be atleast 6 characters");
        }
//        if(!emp_name.matches("[a-zA-Z ]+")){
//            return("Should contain letters and spaces only");
//        }
        return "true";
    }
    public String phoneValidation(String emp_phone){
        if(emp_phone.equals("")){
            return("Cannot be blank");
        }
        if(emp_phone.length()!=10){
            return("Should be of length 10.Do not use any code");
        }
        if(hasSpace.matcher(emp_phone).find()){
            return("Should not contain space");
        }
        return "true";
    }
    public String passwordValidation(String emp_password){
        if(emp_password.equals("")){
            return("Cannot be blank");
        }
        if(!hasUppercase.matcher(emp_password).find()){
            return("Should contain atleast an Uppercase Letter");
        }
        if(!hasLowercase.matcher(emp_password).find()){
            return("Should contain atleast a Lowercase Letter");
        }
        if(!hasNumber.matcher(emp_password).find()){
            return("Should contain atleast one digit");
        }
        if(!hasSpecialChar.matcher(emp_password).find()){
            return("Should contain anyone of @,$,#,&,_");
        }
        if(hasSpace.matcher(emp_password).find()){
            return("Should not contain any space");
        }
        if(emp_password.length()<8){
            return("Size should be atleast 8");
        }
        return "true";
    }
}
