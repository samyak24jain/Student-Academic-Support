package studentportal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.util.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

class Marks implements Serializable{
    String rollno;
    String insem;
    String endsem;
    ArrayList<Marks> m1 = new ArrayList<Marks>();
    
    String getinsem(){
        return this.insem;
    }
    String getendsem(){
        return this.endsem;
    }
    
    Marks(String roll,String in,String end){
       rollno=roll;
       insem=in;
       endsem=end;
       
    }
}


class FacultyMarks extends JFrame implements ActionListener,Serializable {
    JButton prevButton,nextButton,clearButton,cancelButton,backButton;
    JTextField insemfield,endsemfield;
    JLabel insem,endsem,rollno;
    Container fa;
    JPanel p1,p2,p3,p4,p5;
    int a=101,x, flag=0;
    String name;
    ArrayList<Marks> al;
   // String rollnum;
    FacultyMarks(String facultyName){
            al = new ArrayList<Marks>();
            setSize(400,300);
            setLocation(500,300);
            setTitle("Marks entry portal");
            name = facultyName;
            insem = new JLabel("Enter insem marks");
            insem.setFont(insem.getFont().deriveFont(16.0f));
            endsem = new JLabel("Enter endsem marks");
            endsem.setFont(endsem.getFont().deriveFont(16.0f));
            rollno= new JLabel("Enter marks of 14IT" + a);
            rollno.setFont(rollno.getFont().deriveFont(16.0f));
            insemfield= new JTextField(5);
            endsemfield = new JTextField(5);
            
            nextButton = new JButton(">>");
            clearButton = new JButton("Clear");
            cancelButton = new JButton("Save");
            prevButton = new JButton("<<");
            backButton = new JButton("Back");
            
            fa = getContentPane();
            fa.setLayout(new GridLayout(5,1,10,5));
            
            p1 = new JPanel();
            p1.setLayout(new FlowLayout());
            p2 = new JPanel();
            p2.setLayout(new FlowLayout());
            p3 = new JPanel();
            p3.setLayout(new FlowLayout());
            p4 = new JPanel();
            p4.setLayout(new FlowLayout());
            p5 = new JPanel();
            p5.setLayout(new FlowLayout());
            
            p1.add(rollno);
            p2.add(insem);
            p2.add(insemfield);
            p3.add(endsem);
            p3.add(endsemfield);
            p4.add(prevButton);
            p4.add(clearButton);
            p4.add(cancelButton);
            p4.add(nextButton);
            p5.add(backButton);
            
            fa.add(p1);
            fa.add(p2);
            fa.add(p3);
            fa.add(p4);
            fa.add(p5);
            
            prevButton.addActionListener(this);
            clearButton.addActionListener(this);
            cancelButton.addActionListener(this);
            nextButton.addActionListener(this);
            endsemfield.addActionListener(this);
            backButton.addActionListener(this);
            
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
      
        if(ae.getSource() instanceof JTextField) {
            if(a == 150)
                {
                    JOptionPane.showMessageDialog(null, "Thank you for entering the marks!");
                    this.setVisible(false);
                    FacultyScreen2 fs2 = new FacultyScreen2(name);
                }
                if(insemfield.getText().isEmpty()) {
                    insemfield.setText("0");
                }
                if(endsemfield.getText().isEmpty()) {
                    endsemfield.setText("0");
                }
                Marks marks = new Marks("14it" + a, insemfield.getText(), endsemfield.getText());
                
                if ( flag == 0)
                {
                    al.add(marks);
                 
                }
                if(flag > 0) {
                    al.set(a-101, marks);
                    flag--;
                }
                a++;
                rollno.setText("Enter marks of 14IT" + a);
                insemfield.setText("");
                endsemfield.setText("");
        } else {
        JButton clickedButton;
                   
      
      clickedButton = (JButton) ae.getSource();
            if( clickedButton == clearButton){
                insemfield.setText("");
                endsemfield.setText("");
            }
            if(clickedButton == backButton){
                this.setVisible(false);
                FacultyScreen2 fs2 = new FacultyScreen2(name);
            }
            if(clickedButton == nextButton){
                
                if(a == 150)
                {
                    JOptionPane.showMessageDialog(null, "Thank you for entering the marks!");
                    this.setVisible(false);
                    FacultyScreen2 fs2 = new FacultyScreen2(name);
                }
                if(insemfield.getText().isEmpty()) {
                    insemfield.setText("0");
                }
                if(endsemfield.getText().isEmpty()) {
                    endsemfield.setText("0");
                }
                Marks marks = new Marks("14it" + a, insemfield.getText(), endsemfield.getText());
                
                if ( flag == 0)
                {
                    al.add(marks);
                 
                }
                if(flag > 0) {
                    al.set(a-101, marks);
                    flag--;
                }
                a++;
                rollno.setText("Enter marks of 14IT" + a);
                insemfield.setText("");
                endsemfield.setText("");
            }
            
            if(clickedButton == cancelButton) {
          try {
              for(int i=a; i<=150; i++) {
                  Marks m = new Marks("14it" + i, "0", "0");
                  al.add(m);
              }
              FileOutputStream fos = new FileOutputStream(name + "marks.txt");
              BufferedOutputStream bos=new BufferedOutputStream(fos);
              ObjectOutputStream oos = new ObjectOutputStream(bos);
              oos.writeObject(al);
              oos.close();
              bos.close();
              fos.close();
              JOptionPane.showMessageDialog(null, "Entered marks have been saved!");
              this.setVisible(false);
              FacultyScreen2 fs2 = new FacultyScreen2(name);
             
              
          }
          catch(Exception e)
          {
              e.printStackTrace();
              //JOptionPane.showMessageDialog(null,);
          }
        }
         if(clickedButton == prevButton) {
             if(a <= 101) {
                 JOptionPane.showMessageDialog(null, "Roll number does not exist! ");
             }
             else { 
                a--;
                flag++;
                insemfield.setText("");
                endsemfield.setText("");
                rollno.setText("Enter marks of 14IT" + a);
             }

         }
        }
    }
   
    
}

class FacultyAttendance extends JFrame implements ActionListener {
    JButton prevButton,nextButton,clearButton,cancelButton,backButton;
    JTextField attendance;
    JLabel entry, roll;
    Container fa;
    JPanel p1,p2,p3,p4;
    int a,x;
    String facultyName, percent;
    Map <String, String> write;
    public FacultyAttendance(String facname) throws IOException{
            write = new TreeMap <String, String>();
            a = 101;
            setSize(400,300);
            setLocation(500,300);
            setTitle("Attendance Entry Portal");
            facultyName = facname;
            entry = new JLabel("Enter attendance percentage:");
            entry.setFont(entry.getFont().deriveFont(16.0f));
            roll = new JLabel("14IT" + a);
            roll.setFont(roll.getFont().deriveFont(16.0f));
            attendance = new JTextField();
            attendance.setColumns(5);
            
            nextButton = new JButton(">>");
            clearButton = new JButton("Clear");
            cancelButton = new JButton("Save");
            prevButton = new JButton("<<");
            backButton = new JButton("Back");
            
            fa = getContentPane();
            fa.setLayout(new GridLayout(4,1,10,5));
            
            p1 = new JPanel();
            p1.setLayout(new FlowLayout());
            p2 = new JPanel();
            p2.setLayout(new FlowLayout());
            p3 = new JPanel();
            p3.setLayout(new FlowLayout());
            p4 = new JPanel();
            p4.setLayout(new FlowLayout());
            
            p1.add(entry);
            p2.add(roll);
            p2.add(attendance);
            p3.add(prevButton);
            p3.add(clearButton);
            p3.add(cancelButton);
            p3.add(nextButton);
            p4.add(backButton);
            
            fa.add(p1);
            fa.add(p2);
            fa.add(p3);
            fa.add(p4);
            prevButton.addActionListener(this);
            clearButton.addActionListener(this);
            cancelButton.addActionListener(this);
            nextButton.addActionListener(this);
            attendance.addActionListener(this);
            backButton.addActionListener(this);
            
            
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            AttendanceClass attClass = new AttendanceClass(facname);
            
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() instanceof JTextField)
        {
                percent = attendance.getText();
                try {
                    saveToFile(a, percent);
                } catch (IOException ex) {
                    Logger.getLogger(FacultyAttendance.class.getName()).log(Level.SEVERE, null, ex);
                }
                a++;
                
                if(a<=150) {
                    roll.setText("14IT" + a);
                    attendance.setText("");
                } else {
                    JLabel tymsg = new JLabel("Thank You for entering attendance!");
                    JOptionPane.showMessageDialog(null, tymsg);
                }
        }
        else {
            JButton clickedButton;
            clickedButton = (JButton) ae.getSource();

            if(clickedButton == clearButton) {
                attendance.setText("");
            }

            if(clickedButton == cancelButton) {
                if(a < 150) {
                    for(int i = a; i<=150; i++) {
                        if(!(write.containsKey("14it" + i) && write.get("14it" + i) != "0")) {
                            try { 
                                saveToFile(i, "0");
                                a++;
                            } catch (IOException ex) {
                                Logger.getLogger(FacultyAttendance.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Contents have been saved!");
            }

            if(clickedButton == nextButton) {
                    percent = attendance.getText();
                    try {
                        saveToFile(a, percent);
                    } catch (IOException ex) {
                        Logger.getLogger(FacultyAttendance.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    a++;

                    if(a<=150) {
                        roll.setText("14IT" + a);
                        attendance.setText("");
                    } else {
                        JLabel tymsg = new JLabel("Thank You for entering attendance!");
                        JOptionPane.showMessageDialog(null, tymsg);
                    }
            }

            if(clickedButton == prevButton) {
                if(a>=102) {
                    a--;
                    roll.setText("14IT" + a);
                    attendance.setText("");
                }
            }
            
            if(clickedButton == backButton) {
                this.setVisible(false);
                FacultyScreen2 fs2 = new FacultyScreen2(facultyName);
            }
        
    }
}
    public void saveToFile(int x, String percent) throws FileNotFoundException, IOException {
            write.put("14it" + a, percent);
            FileOutputStream fos = new FileOutputStream(facultyName + "attendance.txt");
            ObjectOutputStream oos =new ObjectOutputStream(fos);
            oos.writeObject(write);
            oos.flush();
            oos.close();            
            fos.close();
        }
    
}

class FacultyScreen2 extends JFrame implements ActionListener {
    JButton academiccb,weeklycb,attendanceb,marksb;
    Container cfs2;
    JPanel p1,p2,p3,p4,p5,p6;
    String facultyName;
    JButton signout;
    public FacultyScreen2(String name){
        setSize(400,400);
        setLocation(500,200);
        setTitle("Welcome " + name);
        facultyName = name;
        //courseb = new JButton("Course Plan");
        academiccb=new JButton("Academic calendar");
        weeklycb=new JButton("Weekly calendar");
        attendanceb=new JButton("Attendance");
        marksb=new JButton("Marks");
        signout = new JButton("Log Out");
        cfs2=getContentPane();
        cfs2.setLayout(new GridLayout(5,1,3,3));
        p1=new JPanel();
        p1.setLayout(new FlowLayout());
        p2=new JPanel();
        p2.setLayout(new FlowLayout());
        p3=new JPanel();
        p3.setLayout(new FlowLayout());
        p4=new JPanel();
        p4.setLayout(new FlowLayout());
        p5=new JPanel();
        p5.setLayout(new FlowLayout());
        p6 = new JPanel();
        p6.setLayout(new FlowLayout());
        //p1.add(courseb);
        p2.add(academiccb);
        p3.add(weeklycb);
        p4.add(attendanceb);
        p5.add(marksb);
        p6.add(signout);
        //cfs2.add(p1);
        cfs2.add(p2);
        cfs2.add(p3);
        cfs2.add(p4);
        cfs2.add(p5);
        cfs2.add(p6);
        
        //courseb.addActionListener(this);
        academiccb.addActionListener(this);
        weeklycb.addActionListener(this);
        attendanceb.addActionListener(this);
        marksb.addActionListener(this);
        signout.addActionListener(this);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton clickedb;
        clickedb=(JButton) ae.getSource();
        
//        if(clickedb==courseb){
//            try {
//                
//                BufferedImage image = ImageIO.read(new File(""));
//                JLabel picLabel = new JLabel(new ImageIcon(image));
//                JOptionPane.showMessageDialog(null, picLabel, "Course Plan", JOptionPane.PLAIN_MESSAGE, null);
//            }catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "Could not open image.");
//            }
//        }   
        
        if(clickedb==academiccb){
            try {
                
                BufferedImage image = ImageIO.read(new File("AcademicCalendar.jpg"));
                JLabel picLabel = new JLabel(new ImageIcon(image));
                JOptionPane.showMessageDialog(null, picLabel, "Course Plan", JOptionPane.PLAIN_MESSAGE, null);
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not open image.");
            }
        }
        
        if(clickedb==weeklycb){
            try {
                
                BufferedImage image = ImageIO.read(new File("WeeklyPlan.jpg"));
                JLabel picLabel = new JLabel(new ImageIcon(image));
                JOptionPane.showMessageDialog(null, picLabel, "Course Plan", JOptionPane.PLAIN_MESSAGE, null);
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not open image.");
            }
        }
        
        if(clickedb==attendanceb){
            this.setVisible(false);
            try {
                FacultyAttendance facattendance=new FacultyAttendance(facultyName);
            } catch (IOException ex) {
                Logger.getLogger(FacultyScreen2.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        if(clickedb==signout) {
            this.setVisible(false);
            FacultyScreen facscreen = new FacultyScreen();
        }
        if(clickedb == marksb ){
            this.setVisible(false);
            
                FacultyMarks facmarks = new FacultyMarks(facultyName);
            
        }
    }
    
}

class FacultyScreen extends JFrame implements ActionListener {           
            
    JLabel namelabel,passwordlabel;
    JTextField name;
    JPasswordField passwordt;
    Container cfs;
    JButton okbutton,clearbutton,cancelbutton;
    public FacultyScreen(){
            setSize(300,200);
            setLocation(500,300);
            setTitle("Faculty Login");
            
            namelabel=new JLabel("User Name:");
            passwordlabel=new JLabel("Password:");
            name=new JTextField();
            name.setColumns(20);
            passwordt=new JPasswordField();
            passwordt.setColumns(20);
            passwordt.setEchoChar('*');
            okbutton=new JButton("OK");
            clearbutton=new JButton("Clear");
            cancelbutton=new JButton("Back");
            cfs=getContentPane();
            cfs.setLayout(new FlowLayout());            
            
            cfs.add(namelabel);
            cfs.add(name);
            cfs.add(passwordlabel);
            cfs.add(passwordt);
            cfs.add(okbutton);
            cfs.add(clearbutton);
            cfs.add(cancelbutton);
            
            okbutton.addActionListener(this);
            clearbutton.addActionListener(this);
            cancelbutton.addActionListener(this);
            passwordt.addActionListener(this);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
         
         Map <String,String> passfile2;
         
         if(ae.getSource() instanceof JTextField){
             String username = name.getText();
                char[] pwd2=passwordt.getPassword();
                String pwd=new String(pwd2);
                
                if ((username !=null && !username.isEmpty()) || (pwd !=null && !pwd.isEmpty()))
                {   
                    try {
                        PasswordClass nc = new PasswordClass();
                    } catch (IOException ex) {
                        Logger.getLogger(FacultyScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try { 
                        passfile2 = new TreeMap <String,String>();
                        FileInputStream fis = new FileInputStream("password.txt");
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        passfile2 = (TreeMap <String,String>)ois.readObject();
                        ois.close();
                        if(passfile2.containsKey(username)) {
                            if(pwd.equals(passfile2.get(username)))
                            {
                                //JOptionPane.showMessageDialog(null, "Successful Login!");
                                this.setVisible(false);
                                FacultyScreen2 fs2=new FacultyScreen2(username);
                            } else {
                                JOptionPane.showMessageDialog(null, "Username and Password do not match! Try again.");
                                name.setText("");
                                passwordt.setText("");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Username doesn't exist! Try again.");
                            name.setText("");
                            passwordt.setText("");
                        }
                    
                     }catch(Exception e) {
                         JOptionPane.showMessageDialog(null, "File Error!");
                         name.setText("");
                         passwordt.setText("");
                     }
                }
         }
         else{
            JButton clickedButton;
            clickedButton = (JButton) ae.getSource();
            
            if (clickedButton==okbutton)
            {
                String username = name.getText();
                char[] pwd2=passwordt.getPassword();
                String pwd=new String(pwd2);
                
                if ((username !=null && !username.isEmpty()) || (pwd !=null && !pwd.isEmpty()))
                {   
                    try {
                        PasswordClass nc = new PasswordClass();
                    } catch (IOException ex) {
                        Logger.getLogger(FacultyScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try { 
                        passfile2 = new TreeMap <String,String>();
                        FileInputStream fis = new FileInputStream("password.txt");
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        passfile2 = (TreeMap <String,String>)ois.readObject();
                        ois.close();
                        if(passfile2.containsKey(username)) {
                            if(pwd.equals(passfile2.get(username)))
                            {
                                //JOptionPane.showMessageDialog(null, "Successful Login!");
                                this.setVisible(false);
                                FacultyScreen2 fs2=new FacultyScreen2(username);
                            } else {
                                JOptionPane.showMessageDialog(null, "Username and Password do not match! Try again.");
                                name.setText("");
                                passwordt.setText("");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Username doesn't exist! Try again.");
                            name.setText("");
                            passwordt.setText("");
                        }
                    
                     }catch(Exception e) {
                         JOptionPane.showMessageDialog(null, "File Error!");
                         name.setText("");
                         passwordt.setText("");
                     }
                }
                
            }
            if(clickedButton == clearbutton) {
                name.setText("");
                passwordt.setText("");
            }
            if(clickedButton == cancelbutton){
                this.setVisible(false);
                WelcomeScreen ws = new WelcomeScreen();
            }
        }
    }
}

class StudentScreen extends JFrame implements ActionListener {
    JLabel rnlabel,pwdlabel;
    JTextField rollno;
    JPasswordField password;
    JPanel p3;
    Container css;
    JButton okbutton,clearbutton,cancelbutton;
    public StudentScreen(){
            setSize(300,200);
            setLocation(500,300);
            setTitle("Student Login");
            
            rnlabel= new JLabel("Roll Number: ");
            rollno=new JTextField();
            rollno.setColumns(20);
            pwdlabel=new JLabel("Password:  ");
            password= new JPasswordField();
            password.setColumns(20);
            password.setEchoChar('*');
            okbutton=new JButton("OK");
            clearbutton=new JButton("Clear");
            cancelbutton=new JButton("Back");
            css=getContentPane();
            css.setLayout(new FlowLayout());
            
            
            css.add(rnlabel);
            css.add(rollno);
            css.add(pwdlabel);
            css.add(password);
            css.add(okbutton);
            css.add(clearbutton);
            css.add(cancelbutton);
            
            okbutton.addActionListener(this);
            clearbutton.addActionListener(this);
            cancelbutton.addActionListener(this);
            password.addActionListener(this);
            
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
            if(ae.getSource() instanceof JTextField)
            {
                String roll = rollno.getText();
                char[] pwd2=password.getPassword();
                String pwd=new String(pwd2);
                
                if ((roll !=null && !roll.isEmpty()) || (!pwd.isEmpty() && pwd !=null))
                {   
                    try {
                        PasswordClass nc = new PasswordClass();
                    } catch (IOException ex) {
                        Logger.getLogger(FacultyScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try { 
                        Map <String,String> passfile2;
                        passfile2 = new TreeMap <String,String>();
                        
                        FileInputStream fis = new FileInputStream("password.txt");
                        
                        ObjectInputStream ois = new ObjectInputStream(fis);
                       
                        passfile2 = (TreeMap <String,String>)ois.readObject();
                        
                        ois.close();
                      
                        if(passfile2.containsKey(roll)) {
                     
                            if(pwd.equals(passfile2.get(roll)))
                            {
                                //JOptionPane.showMessageDialog(null, "Successful Login!");
                                this.setVisible(false);
                                
                                roll = rollno.getText();
                                StudentScreen2 ss2=new StudentScreen2(roll);
                                
                            } else {
                                JOptionPane.showMessageDialog(null, "Username and Password do not match! Try again.");
                                rollno.setText("");
                                password.setText("");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Username doesn't exist! Try again.");
                            rollno.setText("");
                            password.setText("");
                        }
                     }catch(Exception e) {
                         JOptionPane.showMessageDialog(null, "File Error!");
                         e.printStackTrace();
                         rollno.setText("");
                         password.setText("");
                     }
                }
            }
            else{
             
            JButton clickedButton;
            clickedButton = (JButton) ae.getSource();    
                
            if (clickedButton==okbutton)
            {
                String roll = rollno.getText();
                char[] pwd2=password.getPassword();
                String pwd=new String(pwd2);
                
                if ((roll !=null && !roll.isEmpty()) || (!pwd.isEmpty() && pwd !=null))
                {   
                    try {
                        PasswordClass nc = new PasswordClass();
                    } catch (IOException ex) {
                        Logger.getLogger(FacultyScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try { 
                        Map <String,String> passfile2;
                        passfile2 = new TreeMap <String,String>();
                        
                        FileInputStream fis = new FileInputStream("password.txt");
                        
                        ObjectInputStream ois = new ObjectInputStream(fis);
                       
                        passfile2 = (TreeMap <String,String>)ois.readObject();
                        
                        ois.close();
                      
                        if(passfile2.containsKey(roll)) {
                     
                            if(pwd.equals(passfile2.get(roll)))
                            {
                                //JOptionPane.showMessageDialog(null, "Successful Login!");
                                this.setVisible(false);
                                
                                roll = rollno.getText();
                                StudentScreen2 ss2=new StudentScreen2(roll);
                                
                            } else {
                                JOptionPane.showMessageDialog(null, "Username and Password do not match! Try again.");
                                rollno.setText("");
                                password.setText("");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Username doesn't exist! Try again.");
                            rollno.setText("");
                            password.setText("");
                        }
                     }catch(Exception e) {
                         JOptionPane.showMessageDialog(null, "File Error!");
                         e.printStackTrace();
                         rollno.setText("");
                         password.setText("");
                     }
                }
                
            }
            if(clickedButton == clearbutton) {
                rollno.setText("");
                password.setText("");
            }
            if(clickedButton == cancelbutton){
                this.setVisible(false);
                WelcomeScreen ws = new WelcomeScreen();
            }
            
        }
    }
}

class StudentScreen2 extends JFrame implements ActionListener {
    JButton academiccb,weeklycb,attendanceb,marksb,logout;
    Container cfs2;
    JPanel p2,p3,p4,p5,p6;
    String roll;
    
    public StudentScreen2(String name){
        setSize(400,400);
        setLocation(500,200);
        setTitle("Welcome " + name);
        roll = name;
        //courseb = new JButton("Course Plan");
        academiccb=new JButton("Academic calendar");
        weeklycb=new JButton("Weekly calendar");
        attendanceb=new JButton("Attendance");
        marksb=new JButton("Marks");
        logout=new JButton("Logout");
        cfs2=getContentPane();
        cfs2.setLayout(new GridLayout(5,1,3,3));
        p2=new JPanel();
        p2.setLayout(new FlowLayout());
        p3=new JPanel();
        p3.setLayout(new FlowLayout());
        p4=new JPanel();
        p4.setLayout(new FlowLayout());
        p5=new JPanel();
        p5.setLayout(new FlowLayout());
        p6= new JPanel();
        p6.setLayout(new FlowLayout());
        
        p2.add(academiccb);
        p3.add(weeklycb);
        p4.add(attendanceb);
        p5.add(marksb);
        p6.add(logout);
        
        cfs2.add(p2);
        cfs2.add(p3);
        cfs2.add(p4);
        cfs2.add(p5);
        cfs2.add(p6);
        
        academiccb.addActionListener(this);
        weeklycb.addActionListener(this);
        attendanceb.addActionListener(this);
        marksb.addActionListener(this);
        logout.addActionListener(this);
        
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton clickedb;
        clickedb=(JButton) ae.getSource();
        
        if(clickedb==academiccb){
            try {
                /*File f = new File("G:\\StudentPortal\\gimg.png");
                Desktop dt = Desktop.getDesktop();
                dt.open(f);*/
                BufferedImage image = ImageIO.read(new File("AcademicCalendar.jpg"));
                JLabel picLabel = new JLabel(new ImageIcon(image));
                JOptionPane.showMessageDialog(null, picLabel, "Course Plan", JOptionPane.PLAIN_MESSAGE, null);
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not open image.");
            }
        }
        
        if(clickedb==weeklycb){
            try {
                
                BufferedImage image = ImageIO.read(new File("WeeklyPlan.jpg"));
                JLabel picLabel = new JLabel(new ImageIcon(image));
                JOptionPane.showMessageDialog(null, picLabel, "Course Plan", JOptionPane.PLAIN_MESSAGE, null);
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not open image.");
            }
        }
        
        if(clickedb == attendanceb)
        {
            try {
                this.setVisible(false);
                StudentAttendance sa = new StudentAttendance(roll);
            } catch (IOException ex) {
                Logger.getLogger(StudentScreen2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StudentScreen2.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        if(clickedb == logout){
            
           this.setVisible(false);
           StudentScreen sc = new StudentScreen();
        }
        if(clickedb == marksb){
            try {
                this.setVisible(false);
                StudentMarks sm = new StudentMarks(roll);
            } catch (IOException ex) {
                Logger.getLogger(StudentScreen2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StudentScreen2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }
    
}


class StudentAttendance extends JFrame implements ActionListener {
    JLabel courses[];
    JLabel attendance[];
    JPanel p1,p2,p3,p4,p5,p6;
    Container csa;
    JButton back;
    String roll;
    StudentAttendance(String name) throws FileNotFoundException, IOException, ClassNotFoundException {
        this.courses = new JLabel[5];
        this.attendance = new JLabel[5];
        back = new JButton("Back");
        
        roll=name;
        setTitle("Attendance Status");
        setSize(300,300);
        setLocation(550,250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        csa=getContentPane();
        csa.setLayout(new GridLayout(6,1));
        p1=new JPanel();
        p1.setLayout(new FlowLayout());
        p2=new JPanel();
        p2.setLayout(new FlowLayout());
        p3=new JPanel();
        p3.setLayout(new FlowLayout());
        p4=new JPanel();
        p4.setLayout(new FlowLayout());
        p5=new JPanel();
        p5.setLayout(new FlowLayout());
        p6=new JPanel();
        p6.setLayout(new FlowLayout());
        
        
        courses[0] = new JLabel("Paradigms of Programming");
        courses[1] = new JLabel("Data Structures and Algorithms");
        courses[2] = new JLabel("Mathematical Foundations of IT");
        courses[3] = new JLabel("UNIX Programming and Practices");
        courses[4] = new JLabel("Digital Design and Computer Organization");
        
        FileInputStream fis = new FileInputStream("popattendance.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Map <String,String> north=new TreeMap<String,String> ();
        north=(TreeMap <String,String>)ois.readObject();
        String check=north.get(name);
        attendance[0]=new JLabel(check);
        ois.close();
        fis.close();
        
        
        FileInputStream fis2 = new FileInputStream("dsaattendance.txt");
        ObjectInputStream ois2 = new ObjectInputStream(fis2);
        Map <String,String> north2=new TreeMap<String,String> ();
        north2=(TreeMap <String,String>)ois2.readObject();
        String check2=north2.get(name);
        attendance[1]=new JLabel(check2);
        ois2.close();
        fis2.close();
       
        FileInputStream fis3 = new FileInputStream("mathattendance.txt");
        ObjectInputStream ois3 = new ObjectInputStream(fis3);
        Map <String,String> north3=new TreeMap<String,String> ();
        north3=(TreeMap <String,String>)ois3.readObject();
        String check3=north3.get(name);
        attendance[2]=new JLabel(check3);
        ois3.close();
        fis3.close();
        
        FileInputStream fis4 = new FileInputStream("unixattendance.txt");
        ObjectInputStream ois4 = new ObjectInputStream(fis4);
        Map <String,String> north4=new TreeMap<String,String> ();
        north4=(TreeMap <String,String>)ois4.readObject();
        String check4=north4.get(name);
        attendance[3]=new JLabel(check4);
        ois4.close();
        fis4.close();
        
        FileInputStream fis5 = new FileInputStream("ddcoattendance.txt");
        ObjectInputStream ois5 = new ObjectInputStream(fis5);
        Map <String,String> north5=new TreeMap<String,String> ();
        north5=(TreeMap <String,String>)ois5.readObject();
        String check5=north5.get(name);
        attendance[4]=new JLabel(check5);
        ois5.close();
        fis5.close();
        
        p1.add(courses[0]);p1.add(attendance[0]);
        p2.add(courses[1]);p2.add(attendance[1]);
        p3.add(courses[2]);p3.add(attendance[2]);
        p4.add(courses[3]);p4.add(attendance[3]);
        p5.add(courses[4]);p5.add(attendance[4]);
        p6.add(back);
        
        csa.add(p1);
        csa.add(p2);
        csa.add(p3);
        csa.add(p4);
        csa.add(p5);
        csa.add(p6);
        
        back.addActionListener(this);
        
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        this.setVisible(false);
        StudentScreen2 ss2=new StudentScreen2(roll);
        
    }
    
}

class StudentMarks extends JFrame implements ActionListener {
    JLabel courses[];
    JLabel marks[];
    JPanel p0,p1,p2,p3,p4,p5;
    Container con;
    JButton back;
    String roll,pseudoroll;
    Integer number;
    String insemstr,endsemstr;
    StudentMarks(String name) throws FileNotFoundException, IOException, ClassNotFoundException {
        
        this.courses = new JLabel[5];
        this.marks = new JLabel[5];
        back = new JButton("Back");
        
        roll=name;
        pseudoroll=roll.substring(5,7);
        number= Integer.parseInt(pseudoroll);
        setTitle("Marks Status");
        setSize(400,500);
        setLocation(550,100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        con=getContentPane();
        con.setLayout(new GridLayout(6,1));
        p0=new JPanel();
        p0.setLayout(new FlowLayout());
        p1=new JPanel();
        p1.setLayout(new FlowLayout());
        p2=new JPanel();
        p2.setLayout(new FlowLayout());
        p3=new JPanel();
        p3.setLayout(new FlowLayout());
        p4=new JPanel();
        p4.setLayout(new FlowLayout());
        p5=new JPanel();
        p5.setLayout(new FlowLayout());
        
        
        courses[0] = new JLabel("Paradigms of Programming \n");
        courses[1] = new JLabel("Data Structures and Algorithms \n");
        courses[2] = new JLabel("Mathematical Foundations of IT \n");
        courses[3] = new JLabel("UNIX Programming and Practices \n");
        courses[4] = new JLabel("Digital Design and Computer Organization \n");
        
        FileInputStream fis1 = new FileInputStream("popmarks.txt");
        BufferedInputStream bis1=new BufferedInputStream(fis1);
        ObjectInputStream ois1 = new ObjectInputStream(bis1);
        ArrayList<Marks> al1=new ArrayList<Marks>();
       
        al1= (ArrayList <Marks>) ois1.readObject();
        
        Marks m1;
        m1=al1.get(number -1);
        insemstr=m1.getinsem();
        endsemstr=m1.getendsem();
        marks[0]=new JLabel(" Insem:"+insemstr+" Endsem"+endsemstr);
        
        ois1.close();
        bis1.close();
        fis1.close();
        
        FileInputStream fis2 = new FileInputStream("dsamarks.txt");
        BufferedInputStream bis2=new BufferedInputStream(fis2);
        ObjectInputStream ois2 = new ObjectInputStream(bis2);
        ArrayList<Marks> al2=new ArrayList<Marks>();
       
        al2= (ArrayList <Marks>) ois2.readObject();
        
        Marks m2;
        m2=al2.get(number-1);
        insemstr=m2.getinsem();
        endsemstr=m2.getendsem();
        marks[1]=new JLabel(" Insem:"+insemstr+" Endsem"+endsemstr);
        
        ois2.close();
        bis2.close();
        fis2.close();
        
        FileInputStream fis3 = new FileInputStream("mathmarks.txt");
        BufferedInputStream bis3=new BufferedInputStream(fis3);
        ObjectInputStream ois3 = new ObjectInputStream(bis3);
        ArrayList<Marks> al3=new ArrayList<Marks>();
       
        al3= (ArrayList <Marks>) ois3.readObject();
        
        Marks m3;
        m3=al3.get(number-1);
        insemstr=m3.getinsem();
        endsemstr=m3.getendsem();
        marks[2]=new JLabel(" Insem:"+insemstr+" Endsem"+endsemstr);
        
        ois3.close();
        bis3.close();
        fis3.close();
        
        FileInputStream fis4 = new FileInputStream("unixmarks.txt");
        BufferedInputStream bis4=new BufferedInputStream(fis4);
        ObjectInputStream ois4 = new ObjectInputStream(bis4);
        ArrayList<Marks> al4=new ArrayList<Marks>();
       
        al4= (ArrayList <Marks>) ois4.readObject();
        
        Marks m4;
        m4=al4.get(number-1);
        insemstr=m4.getinsem();
        endsemstr=m4.getendsem();
        marks[3]=new JLabel(" Insem:"+insemstr+" Endsem"+endsemstr);
        
        ois4.close();
        bis4.close();
        fis4.close();
        
        FileInputStream fis5 = new FileInputStream("ddcomarks.txt");
        BufferedInputStream bis5=new BufferedInputStream(fis5);
        ObjectInputStream ois5 = new ObjectInputStream(bis5);
        ArrayList<Marks> al5=new ArrayList<Marks>();
       
        al5= (ArrayList <Marks>) ois5.readObject();
        
        Marks m5;
        m5=al5.get(number-1);
        insemstr=m5.getinsem();
        endsemstr=m5.getendsem();
        marks[4]=new JLabel(" Insem:"+insemstr+" Endsem"+endsemstr);
        
        ois5.close();
        bis5.close();
        fis5.close();
        
        
        
        p0.add(courses[0]);
        p0.add(marks[0]);
        p1.add(courses[1]);
        p1.add(marks[1]);
        p2.add(courses[2]);
        p2.add(marks[2]);
        p3.add(courses[3]);
        p3.add(marks[3]);
        p4.add(courses[4]);
        p4.add(marks[4]);
        p5.add(back);
        
        con.add(p0);
        con.add(p1);
        con.add(p2);
        con.add(p3);
        con.add(p4);
        con.add(p5);
        back.addActionListener(this);
        
        this.setVisible(true); 
        
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
     
        JButton clickedButton;
        clickedButton= (JButton)ae.getSource();
        if(clickedButton == back){
            this.setVisible(false);
            StudentScreen2 sc2 = new StudentScreen2(roll);
        }
      
    }
}

class WelcomeScreen extends JFrame implements ActionListener {
    JLabel welcome1,imagelabel1;
    JRadioButton student,faculty;
    ImageIcon image1;
    JPanel p1,p2,p3;
    ButtonGroup group;
    public WelcomeScreen(){
            this.
            setSize(650,500);                               //setting the frame
            setLocation(350,120); 
            setTitle("Student Academic Support");
            Container contentPane = getContentPane();
            contentPane.setBackground(Color.white);
            contentPane.setLayout(new FlowLayout());
            welcome1=new JLabel("STUDENT ACADEMIC SUPPORT");        //declaring the fields
            welcome1.setFont(welcome1.getFont().deriveFont(32.0f));
            image1=new ImageIcon("NitkLogo.jpg");
            imagelabel1=new JLabel(image1);
            student=new JRadioButton("STUDENT");
            faculty=new JRadioButton("FACULTY");
            student.setFont(student.getFont().deriveFont(16.0f));
            student.setBackground(Color.white);
            faculty.setBackground(Color.white);
            faculty.setFont(faculty.getFont().deriveFont(16.0f));
            group = new ButtonGroup();
            group.add(student);
            group.add(faculty);
            
            p1=new JPanel();  
            p2=new JPanel();
            p3=new JPanel();
            
            
            p1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
            p1.setPreferredSize(new Dimension(650, 100));
            p1.add(welcome1);
            p1.setBackground(Color.white);
            
            p2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            p2.setPreferredSize(new Dimension(650, 250));
            p2.add(imagelabel1);
            p2.setBackground(Color.white);
            
            p3.setLayout(new FlowLayout());
            p3.setPreferredSize(new Dimension(650, 100));
            p3.add(student);
            p3.add(faculty);
            p3.setBackground(Color.white);
            
            contentPane.add(p1);
            contentPane.add(p2);
            contentPane.add(p3);
             
            student.addActionListener(this);                                    //actionlisteners
            faculty.addActionListener(this);
            
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

     }

    @Override
    public void actionPerformed(ActionEvent ae) {                               //implementing the methods
         JRadioButton clickedButton;
            clickedButton = (JRadioButton) ae.getSource();
            
            if(clickedButton==student)
            {
               StudentScreen studentScreen=new StudentScreen();
               this.setVisible(false);
            }
            else
            {
                FacultyScreen facultyScreen=new FacultyScreen();
                this.setVisible(false);
            }
            
        
    }
    
}

public class StudentPortal { 
    public static void main(String[] args) throws IOException {
        
       WelcomeScreen welcomescreen=new WelcomeScreen();
       
    }
    
}

