/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import com.developpez.adiguba.shell.Shell;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Principale.acceuil;

/**
 *
 * @author zeus
 */
public class IpScanner {
    private String commande = "arp -a";
    private String res = "";
    public IpScanner(){
      Shell sh=new Shell();
      sh.setCharset("utf-8"); 
      try {
            this.res = sh.command(this.commande).consumeAsString();
        } catch (IllegalStateException ex) {
            Logger.getLogger(acceuil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(acceuil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getNodes(){
        Pattern pattern=Pattern.compile("(\\d{0,3}\\.\\d{0,3}\\.\\d{0,3}\\.\\d{0,3})");
        Matcher matcher=pattern.matcher(res); 
        String tmp = "";
      while( matcher.find() ){
        if( matcher.group(1).contains("255") ) continue; 
        tmp +=  matcher.group(1).concat( System.lineSeparator() ) ;
      }
    return tmp;
    }
}
