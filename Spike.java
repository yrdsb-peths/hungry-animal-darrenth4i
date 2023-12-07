import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A spike trap that spawns on the ground where the elephant walks
 * Touching the spike trap during its active state (imageIndex = 3)
 * will result in a game over
 * 
 * @author Darren T.
 * @version 12/06/2023
 */
public class Spike extends Actor
{
    //Array to "animate" spike's animation 
    GreenfootImage[] idle = new GreenfootImage[4];
    SimpleTimer animationTimer = new SimpleTimer();
    
    //Duration each image of the spike lasts for, active state (collision on) lasts 2.5 seconds
    int[] duration = {500, 500, 500, 2500};
 
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
        //Each image of the spike animation lasts for as long as the int value in array duration
        if(animationTimer.millisElapsed() > duration[imageIndex]){
            animationTimer.mark();
            MyWorld world = (MyWorld) getWorld();
            imageIndex = (imageIndex + 1);
            
            //imageIndex needs to be 4 or else the game will never show the final spike image
            if(imageIndex == 4){
                world.removeObject(this);
                imageIndex = 3; //Set it back to 3 so Arrays.indexOutOfBoundsException doesn't occur
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
