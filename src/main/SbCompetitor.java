package main;

import java.util.Random;

public class SbCompetitor implements Comparable<SbCompetitor>{
	private String competitorName = null;
	private int competitorTime = 0;
	Random r = new Random();
	
	public SbCompetitor(String name)
	{
		competitorName = name;
		competitorTime = 0;
	}
	
	public int getCompetitorTime() 
	{
		return competitorTime;
	}

	public String getCompetitorName()
	{
		return competitorName;
	}	
	
	public void generateCompetitorTime()
	{
		competitorTime = (int) Math.round(r.nextGaussian()*30 + 300);
		if(competitorTime < 250)
		{
			competitorTime = 250;
		}else if(competitorTime > 370)
		{
			competitorTime = 370;
		}
	}

	@Override
	public int compareTo(SbCompetitor competitor) {
		// TODO Auto-generated method stub
		if(this.getCompetitorTime() > competitor.getCompetitorTime())
		{
			return 1;
		}else if (this.getCompetitorTime() < competitor.getCompetitorTime())
		{
			return -1;
		}else
		{
			return 0;
		}
	}	
}
