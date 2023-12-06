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
    
    int[] duration = {500, 500, 500, 3000};
 
    public Spike(){
        //Construct an array of 4 images of spike
        for(int i = 0; i < idle.length; i++){
            idle[i] = new GreenfootImage("images/spike_idle/spike" + i  + ".png");
            idle[i].scale(idle[i].getWidth() / 2, idle[i].getHeight() / 2);
        }
        
        //initial spike image
        setImage(idle[0]);
    }
    
    /**
     * Method to animate spike going up
     */
    int imageIndex = 0;
    public void animateSpike(){
        if(animationTimer.millisElapsed() > duration[imageIndex]){
            animationTimer.mark();
            MyWorld world = (MyWorld) getWorld();
            imageIndex = (imageIndex + 1);
 
            if(imageIndex == 4){
                world.removeObject(this);
                imageIndex = 3; //Set it back to 3 so indexOutOfBoundsException doesn't occur
                world.createSpike();
            }
            setImage(idle[imageIndex]);
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
