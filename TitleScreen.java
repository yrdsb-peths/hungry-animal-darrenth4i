import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The title screen which introduces the player to the game
 * 
 * @author Darren
 * @version Nov 2023
 */
public class TitleScreen extends World
{
    Label titleLabel = new Label("Hungry Elephant", 60);
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 

        addObject(titleLabel, getWidth()/2, 70);
        prepare();
    }

    //Main world's act loop

    public void act(){
        //Start game once player presses space bar
        if(Greenfoot.isKeyDown("space")){
            MyWorld gameWorld = new MyWorld();
            Greenfoot.setWorld(gameWorld);
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Elephant elephant = new Elephant();
        addObject(elephant,508,76);
        elephant.setLocation(490,90);
        elephant.setLocation(488,86);
        elephant.setLocation(484,76);
        Elephant elephant2 = new Elephant();
        addObject(elephant2,579,91);
        elephant2.setLocation(92,168);
        elephant.setLocation(564,82);
        elephant2.setLocation(65,69);
        elephant.setLocation(528,67);
    }
}
