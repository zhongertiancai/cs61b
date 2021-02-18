package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {


        int seed = 12222222;
        MemoryGame game = new MemoryGame(40, 40, seed);

        game.startGame();

    }

    public MemoryGame(int width, int height, long seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        rand = new Random(seed);
        //TODO: Initialize random number generator

    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        String s = "";
        int k;
        for(int i = 0;i < n; i++){
            k = rand.nextInt(26);
            s = s + CHARACTERS[k];
        }
        return s;
    }

    public void drawFrame(String s) {
        StdDraw.clear(Color.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(width/2,height/2,s );
        if(!gameOver){
            Font smallerfont = new Font("Monaco",Font.BOLD,20);
            StdDraw.setFont(smallerfont);
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.text(5,height-1,"ROUND: "+round);
            StdDraw.text(width-7,height-1,ENCOURAGEMENT[round % ENCOURAGEMENT.length]);
            StdDraw.line(0, height - 2, width, height - 2);
            if(playerTurn){
                StdDraw.text(width/2,height-1,"Write!");
            } else{
                StdDraw.text(width/2,height-1,"Watch!");
            }
        }
        StdDraw.show();

        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
    }

    public void flashSequence(String letters) {
        for (int i = 0; i < letters.length(); i++){
            drawFrame(letters.substring(i,i + 1));
            StdDraw.pause(750);
            drawFrame(" ");
            StdDraw.pause(750);
        }
        //TODO: Display each character in letters, making sure to blank the screen between letters
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        String s = "";
        drawFrame(s);
        while(s.length() < n){
            if(!StdDraw.hasNextKeyTyped()){
                continue;
            }else{
                s = s + StdDraw.nextKeyTyped();
                drawFrame(s);
            }
        }
        StdDraw.pause(500);
        return s;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        //TODO: Establish Game loop
        gameOver = false;
        round = 1;
        while(!gameOver) {
            playerTurn = false;
            drawFrame("ROUND " + round);
            StdDraw.pause(500);
            String s = generateRandomString(round);
            flashSequence(s);
            playerTurn = true;
            String s1 = solicitNCharsInput(round);
            if(s.compareTo(s1) == 0){
                round ++;
            }else{
                gameOver = true;
            }
        }
        drawFrame("Game Over! You made it to ROUND "+round);
        StdDraw.pause(10000);
    }

}
