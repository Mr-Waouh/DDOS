/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import com.developpez.adiguba.shell.Shell;
import java.io.IOException;
import java.util.regex.*;

/**
 *
 * @author root
 */
public class TTLComputer {
     public static String TTLTimer(String ip) throws IOException {
        // TODO code application logic here
        Shell sh=new Shell();
        sh.setCharset("utf-8");
        String commande = "tracert -d " + ip;
        String res=sh.command(commande).consumeAsString();
        Pattern pattern=Pattern.compile("\\d* ms");
        Matcher matcher=pattern.matcher(res);
        int tmp = 0;
        while(matcher.find()){
                 System.out.println( matcher.group(0) );
                 tmp += Integer.parseInt( matcher.group(0).split(" ")[0] );
        }
    return Integer.toString(tmp);
    }
}
