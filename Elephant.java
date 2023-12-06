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
    
    //Cooldown timer for jumping so elephant can't spam jump
    SimpleTimer jumpTimer = new SimpleTimer(); //TODO DOESNT WORK
    
    int cycles; //Variable to keep track of how far away vertically from (x, 300) the elephant is
    boolean letGo; //Variable to track when player lets go of "w"/jump
    boolean jumpCD; //Variable that allows player to jump after a set cooldown is over
    
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
    
    /**
     * Act - do whatever the Elephant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //Move left or right based on key a or d
        if(Greenfoot.isKeyDown("a")){
            move(-5);
            facing = "left";
        }
        if(Greenfoot.isKeyDown("d")){
            move(5);
            facing = "right";
        }
        
        //Elephant jumping method
        jump();

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
        
        //Game over if elephant touches spike
        hitSpike();
        
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
    
    /**
     * Method that occurs when elephant touches a spike
     */
    public void hitSpike(){
        Spike spike = (Spike)getOneIntersectingObject(Spike.class);
        if(isTouching(Spike.class) && spike.imageIndex == 3){ 
            removeTouching(Spike.class);
            MyWorld world = (MyWorld) getWorld();
            //Run gameover if elephant touches spike
            world.gameOver();
        }
    }
    
    /**
     * Method that allows the elephant to single jump
     */
    public void jump(){
        //Lets user jump 
        if(Greenfoot.isKeyDown("w") && !letGo && !jumpCD){
            setLocation(getX(), getY() - 10);
            cycles += 10;
            //Condition is checking if user has hit the max jump height
            if(cycles >= 120){ 
                letGo = true;
            }

        }
        
        //Condition checks if user is has let go of jump midair
        if(!Greenfoot.isKeyDown("w") && cycles > 0){
            letGo = true;
        }
        
        //Condition to make player fall back down to original height
        if(getY() < 300 && cycles != 0 && (letGo || !Greenfoot.isKeyDown("w"))){
            setLocation(getX(), getY() + 5);
            cycles -= 5;
            //User can jump again if they are back to original y-value
            if(cycles <= 0){
                letGo = false;
                jumpCD = true; //begin jump cooldown
                jumpTimer.mark();
            }
        }
        
        //Allow user to jump again after a cooldown of 200 ms
        if(jumpTimer.millisElapsed() > 200){
            jumpCD = false;
        }
    }
}
