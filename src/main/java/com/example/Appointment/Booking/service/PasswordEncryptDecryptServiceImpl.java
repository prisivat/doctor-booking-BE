package com.example.Appointment.Booking.service;

import com.example.Appointment.Booking.model.SchedulerDetails;
import com.example.Appointment.Booking.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PasswordEncryptDecryptServiceImpl {

    public User passwordEncryption(User user){
        User user1 = new User();
        user1.setPassword(user.getPassword());
        user1.setEmail(user.getEmail());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.setUserName(user.getUserName());
        List<Character> ch = user1.getPassword().chars().mapToObj(i->(char)i).collect(Collectors.toList());
        AtomicReference<String> encryptPassword = new AtomicReference<>("");
        IntStream.range(0,ch.size()).forEach(i->{
            if(Character.isDigit(ch.get(i))){
                int sum = Integer.parseInt(String.valueOf(ch.get(i)))+1;
                sum = sum==10?0:sum;
                encryptPassword.getAndSet(encryptPassword.get()+sum);
            }
            else if(ch.get(i)=='Z'||ch.get(i)=='z'){
                char c = ch.get(i)=='Z'?'A':'a';
                encryptPassword.getAndSet(encryptPassword.get()+c);
            }
            else if(ch.get(i).toString().matches("(.*[@,#,$,%,&].*$)")){
                encryptPassword.getAndSet(encryptPassword.get()+ch.get(i));
            }
            else {
                int charSum = ch.get(i) + 1;
                char c = (char)charSum;
                encryptPassword.getAndSet(encryptPassword.get()+c);
            }
        });
        user1.setPassword(encryptPassword.get());
        return user1;
    }

    public String passwordDecryption(String passWord){
        List<Character> ch = passWord.chars().mapToObj(i->(char)i).collect(Collectors.toList());
        AtomicReference<String> decryptPassword = new AtomicReference<>("");
        IntStream.range(0,ch.size()).forEach(i->{
            if(Character.isDigit(ch.get(i))){
                int sum = Integer.parseInt(String.valueOf(ch.get(i)))-1;
                sum = sum==-1?9:sum;
                decryptPassword.getAndSet(decryptPassword.get()+sum);
            }
            else if(ch.get(i)=='A'||ch.get(i)=='a'){
                char c = ch.get(i)=='A'?'Z':'z';
                decryptPassword.getAndSet(decryptPassword.get()+c);
            }
            else if(ch.get(i).toString().matches("(.*[@,#,$,%,&].*$)")){
                decryptPassword.getAndSet(decryptPassword.get()+ch.get(i));
            }
            else {
                int charSum = ch.get(i) - 1;
                char c = (char)charSum;
                decryptPassword.getAndSet(decryptPassword.get()+c);
            }
        });
        return decryptPassword.get();
    }

    public String passwordSchedulerEncryption(SchedulerDetails schedulerDetails){

        List<Character> ch = schedulerDetails.getPassword().chars().mapToObj(i->(char)i).collect(Collectors.toList());
        AtomicReference<String> encryptPassword = new AtomicReference<>("");
        IntStream.range(0,ch.size()).forEach(i->{
            if(Character.isDigit(ch.get(i))){
                int sum = Integer.parseInt(String.valueOf(ch.get(i)))+1;
                sum = sum==10?0:sum;
                encryptPassword.getAndSet(encryptPassword.get()+sum);
            }
            else if(ch.get(i)=='Z'||ch.get(i)=='z'){
                char c = ch.get(i)=='Z'?'A':'a';
                encryptPassword.getAndSet(encryptPassword.get()+c);
            }
            else if(ch.get(i).toString().matches("(.*[@,#,$,%,&].*$)")){
                encryptPassword.getAndSet(encryptPassword.get()+ch.get(i));
            }
            else {
                int charSum = ch.get(i) + 1;
                char c = (char)charSum;
                encryptPassword.getAndSet(encryptPassword.get()+c);
            }
        });
        return encryptPassword.get();
    }

    public String passwordSchedulerDecryption(String passWord){
        List<Character> ch = passWord.chars().mapToObj(i->(char)i).collect(Collectors.toList());
        AtomicReference<String> decryptPassword = new AtomicReference<>("");
        IntStream.range(0,ch.size()).forEach(i->{
            if(Character.isDigit(ch.get(i))){
                int sum = Integer.parseInt(String.valueOf(ch.get(i)))-1;
                sum = sum==-1?9:sum;
                decryptPassword.getAndSet(decryptPassword.get()+sum);
            }
            else if(ch.get(i)=='A'||ch.get(i)=='a'){
                char c = ch.get(i)=='A'?'Z':'z';
                decryptPassword.getAndSet(decryptPassword.get()+c);
            }
            else if(ch.get(i).toString().matches("(.*[@,#,$,%,&].*$)")){
                decryptPassword.getAndSet(decryptPassword.get()+ch.get(i));
            }
            else {
                int charSum = ch.get(i) - 1;
                char c = (char)charSum;
                decryptPassword.getAndSet(decryptPassword.get()+c);
            }
        });
        return decryptPassword.get();
    }
}
