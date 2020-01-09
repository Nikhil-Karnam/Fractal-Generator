import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 800, HEIGHT = 800;
	private boolean running = false;
	private Thread thread;
	
	Generator generator = new Generator();
	
	private boolean paintBG = false;
	private int speed = 1000;
	
	
	public Main() 
	{
		new Window(WIDTH, HEIGHT, "Cool Patterns", this);
	}
	
	public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
	
	public void run() 
	{
		this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1)
            {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                //System.out.println("FPS: "+ frames);
                frames = 0;
            }
        }
        stop();
	}
	
	public void tick() 
	{
		
	}
	
	public void render() 
	{
		BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
		
        
		//actual code starts here

        if(!paintBG) 
        {
        	g.setColor(Color.WHITE);
    		g.fillRect(0, 0, WIDTH, HEIGHT);
    		generator.defineCoordinates(5, g);
    		paintBG = true;
        }
        
		
		//generator.drawShape(4, g);
		
		for(int i = 0; i < speed; i++) 
			generator.plotPoint(g);
		
		
		//code ends here
        
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) 
	{
		new Main();
	}
}
