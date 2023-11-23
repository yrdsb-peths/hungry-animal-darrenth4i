import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The elephant is hungry and needs to keep eating apples
 * or else he will die.
 * 
 * @author Darren
 * @version November 2023
 */
public class Elephant extends Actor
{
    GreenfootSound elephantSound = new GreenfootSound("elephantcub.mp3");
    GreenfootImage[] idle = new GreenfootImage[8];
    
    //Elephant constructor
    public Elephant(){
        for(int i = 0; i < idle.length; i++){
            idle[i] = new GreenfootImage("images/elephant_idle/idle" + i  + ".png");
            idle[i].scale(100, 100);
        }
        setImage(idle[0]);
    }
    
    //Method to animate the elephant
    int imageIndex = 0;
    public void animateElephant(){
        setImage(idle[imageIndex]);
        imageIndex = (imageIndex + 1) % idle.length;
    }
    
    /**
     * Act - do whatever the Elephant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        //Move left or right based on key a or d
        if(Greenfoot.isKeyDown("a")){
            move(-2);
        }
        if(Greenfoot.isKeyDown("d")){
            move(2);
        }
        
        //Apple disappears once elephant touches it
        eat();
        
        //Animate the elephant
        animateElephant();
    }    
    
    //Method that occurs when elephant touches an apple
    public void eat(){
        
        if(isTouching(Apple.class)){
            removeTouching(Apple.class);
            MyWorld world = (MyWorld) getWorld();
            //Create new apple
            world.createApple();
            //Add 1 to score
            world.increaseScore();
            //play elephant cub sound when apple is eaten
            elephantSound.play();
        }
    }
}
