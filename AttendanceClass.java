/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentportal;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author G50
 */
public class AttendanceClass {
     public AttendanceClass(String filename) throws FileNotFoundException, IOException{
    Map <String,String> attfile;
        attfile = new TreeMap <String,String>();
        for(int i=1;i<=50;i++)
            attfile.put("14it" + (i+100), "0");
        FileOutputStream fos =new FileOutputStream(filename + "attendance.txt");
        ObjectOutputStream oos =new ObjectOutputStream(fos);
        oos.writeObject(attfile);
        oos.flush();
        oos.close();
        fos.close();
    }
}