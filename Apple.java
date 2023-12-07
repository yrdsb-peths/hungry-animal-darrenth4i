import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The apple that the elephant will eat. This class is responsible for updating it's position
 * while falling and changing the speed at which the apple falls. 
 * 
 * @author Darren  T.
 * @version 12/06/2023
 */
public class Apple extends Actor
{
    /**
     * Act - do whatever the Apple wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private int speed = 1;
    
    public void act() 
    {
        // Add your action code here.
        //Apple continously falls down
        setLocation(getX(), getY() + speed);
        
        //Remove apple and draw game over when apple gets to bottom
        MyWorld world = (MyWorld) getWorld();
        if(getY() >= world.getHeight()){
            world.gameOver();
            //Remove apple object when game is over
            world.removeObject(this);
        }
    }   
    
    /**
     * Setter method to change the speed at which apple falls down
     */
    public void setSpeed(int spd){
        speed = spd;
    }
}
