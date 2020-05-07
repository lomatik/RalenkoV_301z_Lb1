/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author lomatik
 */
@WebListener
public class ServletContextManager implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context initializated");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context destroyed");
    }
    
}
