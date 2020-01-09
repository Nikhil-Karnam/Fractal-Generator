import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Generator {

	private int x = 200;
	private int y = 500;

	private int previous = 0;
	private int previous2 = 0;
	
	Random r = new Random();
	
	private ArrayList<String> list = new ArrayList<>();
	
	
	//draws a shape
	public void drawShape(int sides, Graphics g) 
	{
		double angle = 2*Math.PI/sides;
		
		
		for(double i = 0; i < 2*Math.PI; i+=angle) 
		{
			int xCord = (int) (400 + 350*Math.cos(i));
			int yCord = (int) (400 + 350*Math.sin(i));
						
			g.fillRect(xCord, yCord, 5, 5);			
		}
	}
	
	
	//draws a shape
	public void defineCoordinates(double sides, Graphics g) 
	{
		double angle = 2*Math.PI/sides;
		
		for(double i = 0; i < 2*Math.PI; i+=angle) 
		{
			int xCord = (int) (400 + 350*Math.cos(i));
			int yCord = (int) (400 + 350*Math.sin(i));
													
			list.add(xCord + "," + yCord);
		}
	}
	
	
	//plots a single point
	public void plotPoint(Graphics g) 
	{		
		//generates color
		g.setColor(makeColor(x, y));

		
		//calculates random value
		int a = r.nextInt(list.size());
		
		//if(previous == previous2) 
		//{
			//while(Math.abs(a-previous) == 1 || Math.abs(a-previous) == list.size()-1) 
				//a = r.nextInt(list.size());
		//}

		
		//plots point
		for(int i = 0; i < list.size(); i++) 
		{
			if(a == i) 
				setNewCoordinates(Integer.parseInt(list.get(i).split(",")[0]) , Integer.parseInt(list.get(i).split(",")[1]));
			
		}
		
		g.fillRect(x, y, 1, 1);
		
		
		previous2 = previous;
		previous = a;
	}
	
	
	
	//calculates new coordinate
	public void setNewCoordinates(int x1, int y1) 
	{
		//x = (x1 - x)/2 + (int)Math.sqrt(y) + 400;
		//y = (y1 - y)/2 + (int)Math.sqrt(x) + 400;
		
		x = (x1 - x)/2 + x;
		y = (y1 - y)/2 + y;		
	}
	
	
	public Color makeColor(double x, double y) 
	{
		int val = Math.abs((int)(x / 4))+40;
		int val2 = Math.abs((int)(y / 4))+40;
				
		while(val > 255)
			val = 500-val;
		
		while(val2 > 255)
			val2 = 500-val2;

		return new Color(val2, val, val);
	}
	
}
