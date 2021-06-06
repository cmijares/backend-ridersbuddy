package com.sysarch.finals.springridersbuddy.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transaction")
public class Transactions {
@Id
private String id;
private String customerid;
private String firstname;
private String lastname;
private String borrowerqty;
private String startTime;
private String endTime;
private double price;
private String dateborrowed;
private String duration;
private double totalPay;
private String status;
private String personnel;
private String biketype;

public Transactions(String customerid, String firstname, String lastname, String borrowerqty, String startTime,
		String endTime, double price, String dateborrowed, String duration, double totalPay, String status,
		String personnel,String biketype) {

	this.customerid = customerid;
	this.firstname = firstname;
	this.lastname = lastname;
	this.borrowerqty = borrowerqty;
	this.startTime = startTime;
	this.endTime = endTime;
	this.price = price;
	this.dateborrowed = dateborrowed;
	this.duration = duration;
	this.totalPay = totalPay;
	this.status = status;
	this.personnel = personnel;
	this.biketype = biketype;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getCustomerid() {
	return customerid;
}

public void setCustomerid(String customerid) {
	this.customerid = customerid;
}

public String getFirstname() {
	return firstname;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}

public String getLastname() {
	return lastname;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}

public String getBorrowerqty() {
	return borrowerqty;
}

public void setBorrowerqty(String borrowerqty) {
	this.borrowerqty = borrowerqty;
}

public String getStartTime() {
	return startTime;
}

public void setStartTime(String startTime) {
	this.startTime = startTime;
}

public String getEndTime() {
	return endTime;
}

public void setEndTime(String endTime) {
	this.endTime = endTime;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public String getDateborrowed() {
	return dateborrowed;
}

public void setDateborrowed(String dateborrowed) {
	this.dateborrowed = dateborrowed;
}

public String getDuration() {
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    
    try {
       String durations;
    
       Date date1 = timeFormat.parse(this.startTime);
       Date date2 = timeFormat.parse(this.endTime);
       
       long sum = date2.getTime() - date1.getTime();
       long newSum = sum/1000;
       
       long seconds = newSum; 
       
       long p1 = seconds % 60;
       long hours = seconds / 60;
       long minutes = hours % 60;
       
       hours = hours / 60;
       
       durations = hours + " hrs " + minutes + " mins";
       
       //store to database
       System.out.println("durations: " +durations);
       this.duration = durations;
       String time1;
       String time2; 
       
       char first;
       String first1;  
       String second; 
       
       int sum_min = 0;
       
       String hr;          
       int sum_hr = 0;

       int result = 0;
       int min_to_hour;
       
       double rateperhour = this.price;
       
       double new_hr;
       double newrateperhour;
       
       int str_length;
                  
          time1 = durations;
          time2 = time1.replaceAll("[^0-9]", "");    
          
          str_length = time2.length();
          
          System.out.println("number of string: " +str_length);     
          
          if(str_length == 3) {
             first = time2.charAt(0); 
             second = time2.substring(time2.length()-2);  
                  
             sum_min += Integer.parseInt(second);  
             hr = Character.toString(first);
             sum_hr += Integer.parseInt(hr); 
            
             System.out.println("remove string: " +time2);      
             System.out.println("hour: " +first);                       
             System.out.println("minutes: " +second);

          } else if(str_length == 4) {
                       
             first1 = (time2.length()>=2) ? time2.substring(0,2) : "00"; 
                                                
             second = time2.substring(time2.length()-2);  
                  
             sum_min += Integer.parseInt(second);  
             sum_hr += Integer.parseInt(first1); 
            
             System.out.println("remove string: " +time2);      
             System.out.println("hour: " +first1);                       
             System.out.println("minutes: " +second);
          }
          
          else {
             first = time2.charAt(0);
             second = time2.substring(time2.length()-1);  
                  
             sum_min += Integer.parseInt(second);  
             hr = Character.toString(first);
             sum_hr += Integer.parseInt(hr); 
            
             System.out.println("remove string: " +time2);      
             System.out.println("hour: " +first);                       
             System.out.println("minutes: " +second);
          }
             
            System.out.println("sum of hour: " +sum_hr);     
            
            result = sum_min%60;
                       
            min_to_hour = sum_min-result;
            
            int div_hr = min_to_hour/60;
            
            /*
             excess charge P50
            */                
            
           System.out.println("remainder minutes: " +result); 
            
            new_hr = div_hr + sum_hr;
            
            System.out.println("total hours: : " +new_hr); 
    
            newrateperhour = (int) (new_hr *rateperhour);
            
            //store to database
            System.out.println("total pay: " +newrateperhour);    
                       
    } catch(Exception ex) {
       ex.printStackTrace();
    }           
    return this.duration;
}

public void setDuration(String duration) {
	this.duration = duration;
}

public double getTotalPay() {
	  SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
      
      try {
         String durations;
      
         Date date1 = timeFormat.parse(this.startTime);
         Date date2 = timeFormat.parse(this.endTime);
         
         long sum = date2.getTime() - date1.getTime();
         long newSum = sum/1000;
         
         long seconds = newSum; 
         
         long p1 = seconds % 60;
         long hours = seconds / 60;
         long minutes = hours % 60;
         
         hours = hours / 60;
         
         durations = hours + " hrs " + minutes + " mins";
         
         //store to database
         System.out.println("durations: " +durations);
         this.duration = durations;
         String time1;
         String time2; 
         
         char first;
         String first1;  
         String second; 
         
         int sum_min = 0;
         
         String hr;          
         int sum_hr = 0;

         int result = 0;
         int min_to_hour;
         
         double rateperhour = this.price;
         
         double new_hr;
         double newrateperhour;
         
         int str_length;
                    
            time1 = durations;
            time2 = time1.replaceAll("[^0-9]", "");    
            
            str_length = time2.length();
            
            System.out.println("number of string: " +str_length);     
            
            if(str_length == 3) {
               first = time2.charAt(0); 
               second = time2.substring(time2.length()-2);  
                    
               sum_min += Integer.parseInt(second);  
               hr = Character.toString(first);
               sum_hr += Integer.parseInt(hr); 
              
               System.out.println("remove string: " +time2);      
               System.out.println("hour: " +first);                       
               System.out.println("minutes: " +second);

            } else if(str_length == 4) {
                         
               first1 = (time2.length()>=2) ? time2.substring(0,2) : "00"; 
                                                  
               second = time2.substring(time2.length()-2);  
                    
               sum_min += Integer.parseInt(second);  
               sum_hr += Integer.parseInt(first1); 
              
               System.out.println("remove string: " +time2);      
               System.out.println("hour: " +first1);                       
               System.out.println("minutes: " +second);
            }
            
            else {
               first = time2.charAt(0);
               second = time2.substring(time2.length()-1);  
                    
               sum_min += Integer.parseInt(second);  
               hr = Character.toString(first);
               sum_hr += Integer.parseInt(hr); 
              
               System.out.println("remove string: " +time2);      
               System.out.println("hour: " +first);                       
               System.out.println("minutes: " +second);
            }
               
              System.out.println("sum of hour: " +sum_hr);     
              
              result = sum_min%60;
                         
              min_to_hour = sum_min-result;
              
              int div_hr = min_to_hour/60;
              
              /*
               excess charge P50
              */                
              
             System.out.println("remainder minutes: " +result); 
              
              new_hr = div_hr + sum_hr;
              
              System.out.println("total hours: : " +new_hr); 
      
              newrateperhour = new_hr *rateperhour;
              
              //store to database
              System.out.println("total pay: " +newrateperhour);    
                         this.totalPay = newrateperhour;
      } catch(Exception ex) {
         ex.printStackTrace();
      }           

	return this.totalPay;
}

public void setTotalPay(double totalPay) {
	this.totalPay = totalPay;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getPersonnel() {
	return personnel;
}

public void setPersonnel(String personnel) {
	this.personnel = personnel;
}

public String getBiketype() {
	return biketype;
}

public void setBiketype(String biketype) {
	this.biketype = biketype;
}





}
