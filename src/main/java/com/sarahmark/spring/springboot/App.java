package com.sarahmark.spring.springboot;

import com.sarahmark.spring.springboot.configuration.MyConfig;
import com.sarahmark.spring.springboot.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);
        System.out.println(communication.allUsers());
        communication.addUser(new User(3L, "James", "Brown", (byte) 35));
        communication.saveUser(new User(3L, "Thomas", "Shelby", (byte) 35));
        communication.deleteUser(3L);
    }
}
