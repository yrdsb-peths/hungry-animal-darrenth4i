import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    
    public int score = 0; //Score variable
    Label scoreLabel; //Score label object
    int level = 1; //Speed at which apple is falling
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false);
        
        //Create elephant
        Elephant elephant = new Elephant();
        addObject(elephant, 300, 300);
        
        //Label to show the score
        scoreLabel = new Label(0, 80);
        addObject(scoreLabel, 20, 30);
        
        //Create falling apple
        createApple();
    }
    
    //Method to increase the score by 1
    public void increaseScore(){
        score++;
        scoreLabel.setValue(score);
        
        //Increase level or speed of apple falling every 5 points
        if(score % 5 == 0)
        {
            level += 1;
        }
    }
    
    //End the game when the apple falls too far down
    public void gameOver(){
        Label gameOverLabel = new Label("Game Over", 100);
        addObject(gameOverLabel, 300, 200);
    }    
    
    //Create an apple in a random x-value at the top
    public void createApple(){
        Apple apple = new Apple();
        //Update apple falling speed
        apple.setSpeed(level);
        //Spawn apple at random x-value
        int x = Greenfoot.getRandomNumber(600);
        int y = 0;
        addObject(apple, x, y);
    }
}
