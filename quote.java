package quote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import javax.swing.*;


public class quote{

    private JLabel quotes;
    private JLabel bal;
    private JLabel quotes2;
    private JLabel bal2;
    private JLabel time;
    
    public String firm="FXPO";    
    public String qts;

    public quote(){
    	JFrame frame = new JFrame("FrameDemo");
        //System.out.println(qts);
        quotes = new JLabel();
        bal = new JLabel();
        quotes2 = new JLabel();
        bal2 = new JLabel();
        time = new JLabel();
        //setBounds(100, 100, 200, 200);
        //JPanel panel = new JPanel(new FlowLayout()); 
        frame.add(time, BorderLayout.PAGE_START);
        frame.add(quotes, BorderLayout.WEST);
        frame.add(bal,BorderLayout.CENTER);
        frame.add(quotes2, BorderLayout.SOUTH);
        frame.add(bal2,BorderLayout.AFTER_LINE_ENDS);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	frame.pack(); 
    	frame.setVisible(true);
        }

    public String qts() throws IOException {

        // Make a URL to the web page
        URL url = new URL("https://finance.google.com/finance/getprices?q="+firm+".L&p=10m&f=c&i=60");
        System.out.println("https://finance.google.com/finance/getprices?q="+firm+".L&p=10m&f=c&i=60");
        // Get the input stream through URL Connection
        URLConnection con = url.openConnection();
        InputStream is =con.getInputStream();

        // Once you have the Input Stream, it's just plain old Java IO stuff.

        // For this case, since you are interested in getting plain-text web page
        // I'll use a reader and output the text content to System.out.

        // For binary content, it's better to directly read the bytes from stream and write
        // to the target file.


        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;
        String sep = null;
        // read each line and write to System.out
        while ((line = br.readLine()) != null) {
            //System.out.println(line);
        	sep = line;
        }	        	
        //return Float.parseFloat(sep);
    //System.out.println(sep);
    return sep;
    }
    

public static void main(String[] args) throws IOException, InterruptedException {
    quote app = new quote();
    while (true) {
    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new java.util.Date());
    	app.time.setText(timeStamp);
    	app.firm = "FXPO";
    	app.quotes.setText("Quote for "+ app.firm +" is "+app.qts());
    	app.bal.setText(app.firm + " balance is "+ 
    			String.valueOf((int)(Float.parseFloat(app.qts())-320.8)*9.35-36.05));
    	app.firm = "KAZ";
    	app.quotes2.setText("Quote for "+app.firm +" is "+app.qts());
    	app.bal2.setText(app.firm + " balance is "+ 
    			String.valueOf((int)(Float.parseFloat(app.qts())-795)*2.4-30.59));
    	TimeUnit.SECONDS.sleep(30);
    }
  }
}

