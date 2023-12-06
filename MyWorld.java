import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main world where the game is being played
 * 
 * @author Darren
 * @version 11/30/2023
 */
public class MyWorld extends World
{ 
    public int score = 0; //Score variable
    public int highScore = 0; //High score variable
    Label scoreLabel; //Score label object
    Label highScoreLabel; //High score label object
    int level = 1; //Speed at which apple is falling
    int maxSpikes = 1; //Limit of amount of spike objects on screen
    
    /**
     * Constructor for objects of class MyWorld.
     */
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
        
        //Label to show the high score of the session
        highScoreLabel = new Label("High score: " + highScore, 40);
        addObject(highScoreLabel, 500, 20);
        
        //Create falling apple
        createApple();
        
        //Create spike
        createSpike(); 
    }
   
    /**
     * Continually check if user types R to reset
     */
    public void act(){
        //Reset to title screen if R is pressed
        if(Greenfoot.isKeyDown("r")){
            //Keep track of highscore across resets with overloaded constructor
            TitleScreen title = new TitleScreen(highScore);
            Greenfoot.setWorld(title);
        }
    }
    
    /**
     * Method to set highScore variable to save highscore during different playthroughs of a session
     */
    public void setHighScore(int scoreValue){
        highScore = scoreValue;
        highScoreLabel.setValue("High score: " + scoreValue);
    }
    
    /**
     * Method to increase the score by 1
     */
    public void increaseScore(){
        score++;
        scoreLabel.setValue(score);
        
        //Increase level or speed of apple falling every 5 points
        if(score % 5 == 0)
        {
            level++;
            maxSpikes++;
        }
    }
    
    /**
     * Method to end the game when the apple falls too far down
     */
    public void gameOver(){
        Label gameOverLabel = new Label("Game Over (R to reset)", 60);
        addObject(gameOverLabel, 300, 200);
        //Remove all instances of Apple
        removeObjects(getObjects(Apple.class)); 
        
        //Set highscore if current score is larger than old highscore
        if(score > highScore){
            highScoreLabel.setValue("High score: " + score);
            highScore = score; //Update highscore variable
        }
    }    
    
    /**
     * Create an apple in a random x-value at the top
     */
    public void createApple(){
        Apple apple = new Apple();
        //Update apple falling speed
        apple.setSpeed(level);
        //Spawn apple at random x-value
        int x = Greenfoot.getRandomNumber(600);
        int y = 0;
        addObject(apple, x, y);
    }
    
    /**
     * Create a spike in a random y-value on the ground
     */
    public void createSpike(){
        //Remove all previous instances of spike so it doesn't stack infinitely
        removeObjects(getObjects(Spike.class)); 
        //Set the amount of spikes that can appear at once
        for(int i = 0; i<maxSpikes; i++){
            Spike spike = new Spike();
            int x = Greenfoot.getRandomNumber(600);
            int y = 310; //Elephant ground y-value
            addObject(spike, x, y);
        }
    }
}
