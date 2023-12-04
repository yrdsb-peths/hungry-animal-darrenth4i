import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spike here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spike extends Actor
{
    //Array to "animate" spike's animation 
    GreenfootImage[] idle = new GreenfootImage[4];
    SimpleTimer animationTimer = new SimpleTimer();
 
    public Spike(){
        //Construct an array of 4 images of spike
        for(int i = 0; i < idle.length; i++){
            idle[i] = new GreenfootImage("images/spike_idle/spike" + i  + ".png");
        }
        
        //initial spike image
        setImage(idle[0]);
    }
    
    /**
     * Method to animate spike going up
     */
    int imageIndex = 0;
    public void animateSpike(){
        if(animationTimer.millisElapsed() > 500){
            animationTimer.mark();
            MyWorld world = (MyWorld) getWorld();
            
            if(imageIndex == 3){
                world.removeObject(this);
            }
            setImage(idle[imageIndex]);
            imageIndex = (imageIndex + 1);
        }
    }
    
    /**
     * Act - do whatever the Spike wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void act() 
    {
        // Add your action code here.
        animateSpike();
    }  
}
