import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Logfiler {
	
	PrintWriter writer;
	
	public Logfiler()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Calendar cal = Calendar.getInstance();
		String name = dateFormat.format(cal.getTime());
		
		
		try {
			writer = new PrintWriter(name+".txt", "UTF-8");
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void printLog(String log)
	{
		writer.println(log);
	}
	public void closeWriter()
	{
		writer.close();
	}
	public void printErr(String err)
	{/*
		try {
		    Files.write(Paths.get("errlog.txt"), err.getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {
		    e.printStackTrace();
		}*/
	}
}
