
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Random;

public class GUI 
{
/*	JFrame frame;
	JPanel panel;
	JTextField tfield;
	JButton sendButton;
	JTextArea tarea;
	*/
	String input = null;
	public int speed = 1;

	public GUI()
	{
/*		frame = new JFrame("Eliezer");
		frame.setSize(800, 600);
		
		panel = new JPanel();

		
		tarea = new JTextArea(30,70);
		tarea.setEditable(false);
		tarea.setSize(700,300);
		tarea.setLineWrap(true);
		tarea.setWrapStyleWord(true);
		JScrollPane scrollpane = new JScrollPane(tarea);
		
		
		tfield = new JTextField(70);
		tfield.setForeground(Color.BLACK);
		tfield.setBackground(Color.WHITE);
		tfield.requestFocus();
		
		sendButton = new JButton("send");
		
		// TODO add enter button support
		
	
		panel.add(scrollpane);
		panel.add(tfield);
		panel.add(sendButton);
		frame.add(panel);
		frame.setVisible(true);
		
		sendButton.addActionListener(this);
		tfield.addKeyListener(this);*/
	}
	
	public void typeOutput(String output, boolean real){

		Random rand = new Random();
		int sleep = (rand.nextInt(100)+1)%5;  // chose time at random
		if (!real) sleep = 0; //but not in testing mode

			for (int i = 0; i< output.length(); i++){
				//tarea.append(output.substring(i,i+1));
				System.out.print(output.substring(i,i+1));
				
				int delay = 0;
				if (speed == 1) delay = 50;
				if (speed == 0) delay = 100;
				if (speed == 2) delay = 25;
				//delay = 0;
				if(real){
						try {
							Thread.sleep(delay);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
			} 
			System.out.println();
			//tarea.append("\n");
			//tarea.setCaretPosition(tarea.getDocument().getLength());
			
			
			try {
				Thread.sleep(500 + sleep * 50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	public void putOutput(String output)
	{
		System.out.println(output);
//		tarea.append(output+"\n");
//		tarea.setCaretPosition(tarea.getDocument().getLength());
	}
	
	public String getInput(){
		return getInput(0);
	}
	
	public String getInput(int wait)
	{
		if (wait == 0){//wait forever
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			try {
				String s = br.readLine();
				return s;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		else {
			wait = wait*20;
		}

		if (speed == 0){
			wait = wait + 5;
		}
		if (speed == 2){
			wait = wait - 2;
		}
		speed = 2;


	    while(true){
	    	byte[] input = new byte[1024];
	        int readCount = 0;
			try {
				readCount = readInputStreamWithTimeout(System.in, input, 5000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        
			
	        if (readCount > 0){
	        	byte[] readData = new byte[readCount];
	        	for (int i = 0; i < readCount; i++){
	        		readData[i] = input[i];
	        	}
	        	return(new String( readData, Charset.forName("UTF-8") ));
	        }
	        else return "";
        }
		
	}
/*
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == this.sendButton)
		{
			input = tfield.getText();
			tfield.setText("");
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			input = tfield.getText();
			tfield.setText("");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
*/
	public void goodbye() {


		System.exit(0);
		
		
	}
	
	public static int readInputStreamWithTimeout(InputStream is, byte[] b, int timeoutMillis)
		     throws IOException  {
		     int bufferOffset = 0;
		     long maxTimeMillis = System.currentTimeMillis() + timeoutMillis;
		     while (System.currentTimeMillis() < maxTimeMillis && bufferOffset < b.length) {
		         int readLength = java.lang.Math.min(is.available(),b.length-bufferOffset);
		         int readResult = is.read(b, bufferOffset, readLength);
		         if (readResult == -1) break;
		         bufferOffset += readResult;
		         if(bufferOffset > 0){
		        	 break;
		         }
		     }
		     return bufferOffset;
		 }

}
