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
    //Array to "animate" elephant's idle animation
    GreenfootImage[] idleRight = new GreenfootImage[8];
    GreenfootImage[] idleLeft = new GreenfootImage[8];
    
    //Direction the elephant is facing
    String facing = "right";
    SimpleTimer animationTimer = new SimpleTimer();
    
    /**
     * Elephant constructor
     */
    public Elephant(){
        //Construct an array of 8 pictures of elephant
        for(int i = 0; i < idleRight.length; i++){
            idleRight[i] = new GreenfootImage("images/elephant_idle/idle" + i  + ".png");
        }
        
        //Same as above but for when elephant faces left
        for(int i = 0; i < idleLeft.length; i++){
            idleLeft[i] = new GreenfootImage("images/elephant_idle/idle" + i  + ".png");
            idleLeft[i].mirrorHorizontally();
        }
        
        animationTimer.mark();
        
        //initial elephant image
        setImage(idleRight[0]);
    }
    
    /**
     * Method to animate the elephant using arrays
     */
    int imageIndex = 0;
    public void animateElephant(){
        if(animationTimer.millisElapsed() < 50){
            return;
        }
        animationTimer.mark();
        
        if(facing.equals("right")){
            setImage(idleRight[imageIndex]);
            imageIndex = (imageIndex + 1) % idleRight.length;
        }
        else{
            setImage(idleLeft[imageIndex]);
            imageIndex = (imageIndex + 1) % idleLeft.length;
        }
    }
    
    int cycles;
    boolean letGo;
    /**
     * Act - do whatever the Elephant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //Move left or right based on key a or d
        if(Greenfoot.isKeyDown("a")){
            move(-2);
            facing = "left";
        }
        if(Greenfoot.isKeyDown("d")){
            move(2);
            facing = "right";
        }
        if(Greenfoot.isKeyDown("w") && cycles < 50 && !letGo){
            setLocation(getX(), getY() - 5);
            cycles += 5;
            if(cycles >= 50){
                letGo = true;
            }
        }
        if(getY() < 300 && cycles != 0 && (letGo || !Greenfoot.isKeyDown("w"))){
            setLocation(getX(), getY() + 5);
            cycles -= 5;
            if(cycles <= 0){
                letGo = false;
            }
        }
        
        //If the elephant goes past the world boundaries it will be
        //teleported to the other side of the world 
        //e.g. Hold right and it will eventually teleport elephant to left side 
        if(getX() < -50){ //-50 instead of 0 because 0,0 of elephant is at the left
            setLocation(600, getY());
        }
        else if(getX() > 600){
            setLocation(-25, getY());
        }
        
        //Apple disappears once elephant touches it
        eat();
        
        //Animate the elephant
        animateElephant();
    }    
    
    /**
     * Method that occurs when elephant touches an apple
     */
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
