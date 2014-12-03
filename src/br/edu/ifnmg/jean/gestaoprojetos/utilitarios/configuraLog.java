/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.jean.gestaoprojetos.utilitarios;

import java.io.IOException;
import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 *
 * @author PC
 */
public class configuraLog extends BasicConfigurator{
    
    /**
     *
     * @throws IOException
     */
    public Logger configura(Logger logger) throws IOException{
        Appender fileAppender = new FileAppender(new PatternLayout("%-2d{dd/MM/yy HH:mm} [%t] %5p %c:%L – %m%n"), "LogSGP.log");
        logger.addAppender(fileAppender);
        return logger;
    }
    
}
