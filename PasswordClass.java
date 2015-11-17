
package studentportal;

import java.io.*;
import java.util.*;

public class PasswordClass {
    public PasswordClass() throws FileNotFoundException, IOException{
    Map <String,String> passfile;
        passfile = new TreeMap <String,String>();
        passfile.put("pop","123");
        passfile.put("dsa","456");
        passfile.put("math", "789");
        passfile.put("unix","100");
        passfile.put("ddco","101");
        
        passfile.put("14it101", "one");
        passfile.put("14it102", "two");
        passfile.put("14it103", "three");
        passfile.put("14it104", "four");
        passfile.put("14it105", "five");
        passfile.put("14it106", "six");
        passfile.put("14it107", "seven");
        passfile.put("14it108", "eight");
        passfile.put("14it109", "nine");
        passfile.put("14it110", "ten");
        passfile.put("14it111", "eleven");
        passfile.put("14it112", "twelve");
        passfile.put("14it113", "thirteen");
        passfile.put("14it114", "fourteen");
        passfile.put("14it115", "fifteen");
        passfile.put("14it116", "sixteen");
        passfile.put("14it117", "seventeen");
        passfile.put("14it118", "eighteen");
        passfile.put("14it119", "nineteen");
        passfile.put("14it120", "twenty");
        passfile.put("14it121", "twentyone");
        passfile.put("14it122", "twentytwo");
        passfile.put("14it123", "twentythree");
        passfile.put("14it124", "twentyfour");
        passfile.put("14it125", "twentyfive");
        passfile.put("14it126", "twentysix");
        passfile.put("14it127", "twentyseven");
        passfile.put("14it128", "twentyeight");
        passfile.put("14it129", "twentynine");
        passfile.put("14it130", "thirty");
        passfile.put("14it131", "thirtyone");
        passfile.put("14it132", "thirtytwo");
        passfile.put("14it133", "thirtythree");
        passfile.put("14it134", "thirtyfour");
        passfile.put("14it135", "thirtyfive");
        passfile.put("14it136", "thirtysix");
        passfile.put("14it137", "thirtyseven");
        passfile.put("14it138", "thirtyeight");
        passfile.put("14it139", "thirtynine");
        passfile.put("14it140", "forty");
        passfile.put("14it141", "fortyone");
        passfile.put("14it142", "fortytwo");
        passfile.put("14it143", "fortythree");
        passfile.put("14it144", "fortyfour");
        passfile.put("14it145", "fortyfive");
        passfile.put("14it146", "fortysix");
        passfile.put("14it147", "fortyseven");
        passfile.put("14it148", "fortyeight");
        passfile.put("14it149", "fortynine");
        passfile.put("14it150", "fifty");
        
        
        FileOutputStream fos =new FileOutputStream("password.txt");
        ObjectOutputStream oos =new ObjectOutputStream(fos);
        oos.writeObject(passfile);
        oos.flush();
        oos.close();
        fos.close();
}
}
