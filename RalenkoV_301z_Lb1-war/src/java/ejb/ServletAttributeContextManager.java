/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author lomatik
 */
@WebListener
public class ServletAttributeContextManager implements ServletContextAttributeListener{

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println("Attribute successfully added");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println("Attribute successfully removed");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        System.out.println("Attribute successfully replaced");
    }
    
}
