package dentist.businessobjects;

import java.util.*;

public class AppointmentList {
    
    ArrayList<Appointment> alist = new ArrayList();
    
    public void addAppt(Appointment appt){
        alist.add(appt);
    }
    
    public Appointment getAppt(int i){
        return alist.get(i);
    }
    
    public int getSize(){
        return alist.size();
    }

    public void display(){
        Appointment a;
        for (int i = 0 ; i < alist.size() ; i++){
            a = alist.get(i);
            a.display();
            System.out.println("---------------------------");
        }
    }
    
}