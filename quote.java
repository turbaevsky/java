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
    JLabel allBal;
    
    public String firm="FXPO";    
    public String qts;

    public quote(){
    	JFrame frame = new JFrame("FrameDemo");
    	frame.setSize(400,400);
    	frame.setLayout(new GridLayout(6, 1));
        //System.out.println(qts);
        quotes = new JLabel("",JLabel.CENTER);
        bal = new JLabel("",JLabel.CENTER);
        quotes2 = new JLabel("",JLabel.CENTER);
        bal2 = new JLabel("",JLabel.CENTER);
        time = new JLabel("",JLabel.CENTER);
        allBal = new JLabel("",JLabel.CENTER);
        //setBounds(100, 100, 200, 200);
        //JPanel panel = new JPanel(new FlowLayout()); 
        frame.add(time);
        frame.add(quotes);
        frame.add(bal);
        frame.add(quotes2);
        frame.add(bal2);
        frame.add(allBal);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	//frame.pack(); 
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
    	String val1 = app.qts();
    	app.quotes.setText("Quote for "+ app.firm +" is "+ val1);
    	double balF = (Float.parseFloat(val1)-320.8)*9.35-36.05;
    	app.bal.setText(app.firm + " balance is "+ String.valueOf((int)(balF)));
    	TimeUnit.SECONDS.sleep(5);
    	app.firm = "KAZ";
    	String val2 = app.qts();
    	app.quotes2.setText("Quote for "+app.firm +" is "+ val2);
    	double balS = (Float.parseFloat(val2)-795)*2.4-30.59;
    	app.bal2.setText(app.firm + " balance is "+ String.valueOf((int)(balS)));
    	app.allBal.setText(" All balance is "+ String.valueOf((int)(balF+balS)));
    	TimeUnit.SECONDS.sleep(55);
    }
  }
}

