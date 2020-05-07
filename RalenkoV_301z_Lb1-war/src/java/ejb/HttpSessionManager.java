/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author lomatik
 */
@Singleton
@LocalBean
@WebListener
public class HttpSessionManager implements HttpSessionListener {
    private static int counter = 0;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        counter++;
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        counter--;
    }

    public static int getActiveSessionsCount() {
        return counter;
    }

}

