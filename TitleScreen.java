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
        Elephant elephant2 = new Elephant();
        addObject(elephant2,579,91);
        elephant2.setLocation(65,69);
        elephant.setLocation(528,67);
        Label label = new Label("Press 'a' and 'd' to move!", 50);
        addObject(label,286,220);
        Label label2 = new Label("Press spacebar to start the game!", 30);
        addObject(label2,286,354);
        label.setLocation(300,330);
        label2.setLocation(300,376);
        
    }
}