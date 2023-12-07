import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The title screen which introduces the player to the game
 * 
 * @author Darren
 * @version 12/06/2023
 */
public class TitleScreen extends World
{
    Label titleLabel = new Label("Hungry Elephant", 60);
    int pastHS; //Variable to keep track of a highscore when R is pressed to reset game
    /**
     * Default constructor for objects of class TitleScreen.
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 

        addObject(titleLabel, getWidth()/2, 70);
        prepare();
    }
    
    /**
     * Overloaded constructor for displaying highscore during a new
     * playthrough of the same session
     */
    public TitleScreen(int HS){
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        pastHS = HS;
        addObject(titleLabel, getWidth()/2, 70);
        prepare();
    }

    /**
     * Main world's act loop
     */
    public void act(){
        //Start game once player presses space bar
        if(Greenfoot.isKeyDown("space")){
            MyWorld gameWorld = new MyWorld();
            //Set highscore to the past highscore of the same session, 0 by default
            gameWorld.setHighScore(pastHS);
            Greenfoot.setWorld(gameWorld);
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Elephant elephant = new Elephant(false);
        addObject(elephant,508,76);
        Elephant elephant2 = new Elephant(false);
        addObject(elephant2,579,91);
        elephant2.setLocation(65,69);
        elephant.setLocation(528,67);
        Label label = new Label("Press 'a' and 'd' to move!", 50);
        addObject(label,286,220);
        Label label3 = new Label("Press 'w' to jump!", 50);
        addObject(label3,300,270);
        Label label2 = new Label("Press spacebar to start the game!", 30);
        addObject(label2,286,354);
        label.setLocation(300,330);
        label2.setLocation(300,376);
        
    }
}
