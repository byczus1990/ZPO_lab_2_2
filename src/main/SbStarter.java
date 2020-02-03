package main;

import main.SbRace;

public class SbStarter {

	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub\
		
		SbRace r = new SbRace();
		r.createLogger();
		r.generateSurrnameList();
		r.doTheRace();
	    
	}

}
