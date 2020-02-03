package main;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.io.*;
import static java.util.concurrent.TimeUnit.*;

import main.SbCompetitor;

public class SbRace 
{
    static Logger logger = Logger.getLogger(SbCompetitor.class.getName());
	List<String> surrnamesList = new ArrayList<String>();
//	Set<String> uniqueSurrnames= new HashSet<String>();
	Random r = new Random();
	private final ScheduledExecutorService scheduler =
		     Executors.newScheduledThreadPool(1);
	PriorityQueue<SbCompetitor> raceBracket = new PriorityQueue<>();
	
 public void createLogger()
 {
	 try {
         LogManager.getLogManager().readConfiguration(new FileInputStream("SbProperties.properties"));
     } catch (SecurityException | IOException e1) {
         e1.printStackTrace();
     }
//     logger.setLevel(Level.ALL);
//     logger.addHandler(new ConsoleHandler());
     //adding custom handler
//     logger.addHandler(new SbMyHandler());
//     try {
         //FileHandler file name with max size and number of log files limit
//         Handler fileHandler = new FileHandler("./LAb2.log", 2000, 5);
//         fileHandler.setFormatter(new SbMyFormatter());
//         logger.addHandler(fileHandler);
         
//         for(int i=0; i<2000; i++){
//             //logging messages
//             logger.log(Level.INFO, "Msg"+i);
//         }
//         logger.log(Level.CONFIG, "Config data");
//     } catch (SecurityException | IOException e) {
//         e.printStackTrace();
//     }
 }

		
	public void generateSurrnameList() throws Exception 
	{
		
        URL website = new URL("https://drive.google.com/uc?export=download&id=1rElwhvoaez2v7zwC_8w7kBt8ma8LJKz0");
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                    connection.getInputStream()));

        String inputLine;

        while ((inputLine = in.readLine()) != null)
        {
        	if (!surrnamesList.contains(inputLine)  )
        	{
        		surrnamesList.add(inputLine);
        	}
        }
        	

        in.close();
        
//        System.out.println(uniqueSurrnames);
    }
	public void doTheRace()
	{
		
		
		final Runnable cyclistRun = new Runnable() 
		{
		       public void run() 
		       {
		    	    String name = surrnamesList.remove(r.nextInt(surrnamesList.size()-1));
		    		SbCompetitor competitor = new SbCompetitor(name);
		    		competitor.generateCompetitorTime();
		    		System.out.println("Kolarz o nazwisku: " + competitor.getCompetitorName() + ". Mia³ czas: " + competitor.getCompetitorTime());
		    		logger.log(Level.WARNING, "Kolarz o nazwisku: " + competitor.getCompetitorName() + ". Mia³ czas: " + competitor.getCompetitorTime());
		    		raceBracket.add(competitor);
		    		if(raceBracket.size() == 1)
		    		{
	    				SbCompetitor lCompetitor = null;
		    			
	    				lCompetitor = raceBracket.remove();
		    			System.out.println(lCompetitor.getCompetitorName() + " czas:" + lCompetitor.getCompetitorTime());
			    		logger.log(Level.WARNING,lCompetitor.getCompetitorName() + " czas:" + lCompetitor.getCompetitorTime());
		    			raceBracket.add(lCompetitor);

		    		}else if(raceBracket.size() == 2)
		    		{		    			
	    				SbCompetitor lCompetitor = raceBracket.remove();
	    				SbCompetitor lCompetitor2 = raceBracket.remove();

	    				System.out.println(lCompetitor.getCompetitorName() + " czas:" + lCompetitor.getCompetitorTime());
	    				System.out.println(lCompetitor2.getCompetitorName() + " czas:" + lCompetitor2.getCompetitorTime());
			    		logger.log(Level.WARNING,lCompetitor.getCompetitorName() + " czas:" + lCompetitor.getCompetitorTime());
			    		logger.log(Level.WARNING,lCompetitor2.getCompetitorName() + " czas:" + lCompetitor2.getCompetitorTime());

	    				raceBracket.add(lCompetitor);
	    				raceBracket.add(lCompetitor2);

		    		}else if(raceBracket.size() >= 3)
		    		{		    			
	    				SbCompetitor lCompetitor = raceBracket.remove();
	    				SbCompetitor lCompetitor2 = raceBracket.remove();
	    				SbCompetitor lCompetitor3 = raceBracket.remove();

	    				System.out.println(lCompetitor.getCompetitorName() + " czas:" + lCompetitor.getCompetitorTime());
	    				System.out.println(lCompetitor2.getCompetitorName() + " czas:" + lCompetitor2.getCompetitorTime());
	    				System.out.println(lCompetitor3.getCompetitorName() + " czas:" + lCompetitor3.getCompetitorTime());
	    				raceBracket.add(lCompetitor);
	    				raceBracket.add(lCompetitor2);
	    				raceBracket.add(lCompetitor3);		    			
		    		}		    		
		       }
	     };
	     
	     final ScheduledFuture<?> cyclistRunHandler =
	    	       scheduler.scheduleAtFixedRate(cyclistRun, 0, 2400, MILLISECONDS);
	     
	     scheduler.schedule(new Runnable() 
	     {
	         public void run() 
	         { cyclistRunHandler.cancel(true); 
	         }
	      }, 15 * 2400, MILLISECONDS);
	}
}
