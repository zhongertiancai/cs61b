package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private int roomNum;
    private Random RANDOM;
    private String seed = "";
    private TETile[][] finalWorldFrame;
    private Room[] room;
    private int xIndex;
    private int yIndex;
    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        if (input.charAt(0) == 'n' && input.charAt(input.length()-1) == 's'){
            input = input.substring(1,input.length()-1);
        }
        seed = input;
        int sed = Integer.parseInt(seed);
        RANDOM = new Random(sed);

        finalWorldFrame = new TETile[WIDTH][HEIGHT];
        finalWorldFrame = turnNothing(finalWorldFrame);
        generateRoom();
        connectRoom();

        return finalWorldFrame;
    }
    private Position randomP(Room room){
        int dx = RANDOM.nextInt(room.wid - 1) + 1;
        int dy = RANDOM.nextInt(room.height - 1) + 1;
        Position p = new Position(dx + room.p.x, dy + room.p.y);
        return p;
    }
    private void connectRoom(){
        for (int i = 0; i < room.length - 1 ; i++) {
            generateHallway(room[i],room[i + 1]);

        }
    }
    private void drawL(Position p1, Position p2, Boolean isFloor){
        int smaller, bigger;
        smaller = p1.x;
        bigger = p2.x;
        int smy;
        for (int i = smaller; i <= bigger; i++){
            if (isFloor){
                finalWorldFrame[i][yIndex] = Tileset.FLOOR;
            } else {
                if(finalWorldFrame[i][yIndex] != Tileset.FLOOR) {
                    finalWorldFrame[i][yIndex] = Tileset.WALL;
                }
            }
        }
        if (p1.y > p2.y){
            bigger = p1.y;
            smaller = p2.y;
        } else {
            bigger = p2.y;
            smaller = p1.y;
        }
        for (int i = smaller; i <= bigger; i++){
            if (isFloor){
                finalWorldFrame[xIndex][i] = Tileset.FLOOR;
            } else {
                if(finalWorldFrame[xIndex][i] != Tileset.FLOOR) {
                    finalWorldFrame[xIndex][i] = Tileset.WALL;
                }
            }
        }
    }
    private void generateHallway(Room r1, Room r2){
        Position p1 = randomP(r1);
        Position p2 = randomP(r2);
        while(p1.x == r2.p.x || p1.y == r2.p.y){
            p1 = randomP(r1);
        }
        while (p2.y == r1.p.y || p2.x == r1.p.x){
            p2 = randomP(r2);
        }
        Position temp;
        if (p1.x > p2.x){
            temp = p1;
            p1 = p2;
            p2 = temp;
        }
        if (p1.x == p2.x){
            xIndex = p1.x;
            yIndex = p1.y;
            drawL(p1, p2, true);
            p1.move(1,0);
            p2.move(1,0);
            xIndex = p1.x;
            yIndex = p1.y;
            drawL(p1, p2, false);
            p1.move(-2 ,0);
            p2.move(-2, 0);
            xIndex = p1.x;
            yIndex = p1.y;
            drawL(p1, p2, false);
        }
        if (p1.y < p2.y){
            xIndex = p2.x;
            yIndex = p1.y;
            drawL(p1, p2, true);
            p1.move(0,1);
            p2.move(-1,0);
            xIndex = p2.x;
            yIndex = p1.y;
            drawL(p1, p2, false);
            p1.move(0 ,-2);
            p2.move(2, 0);
            xIndex = p2.x;
            yIndex = p1.y;
            drawL(p1, p2, false);
        }
        else {
            xIndex = p2.x;
            yIndex = p1.y;
            drawL(p1, p2, true);
            p1.move(0,1);
            p2.move(1,0);
            xIndex = p2.x;
            yIndex = p1.y;
            drawL(p1, p2, false);
            p1.move(0 ,-2);
            p2.move(-2, 0);
            xIndex = p2.x;
            yIndex = p1.y;
            drawL(p1, p2, false);
        }
    }

    private void generateRoom(){
        roomNum = RANDOM.nextInt(15);
        while (roomNum < 3){
            roomNum = RANDOM.nextInt(15);
        }
        int i = 0;
        room = new Room[roomNum];
        while (i < roomNum) {
            room[i] = new Room();
            room[i].height = 4 + RANDOM.nextInt(6);
            room[i].wid = 4 + RANDOM.nextInt(6);
            room[i].p.x = RANDOM.nextInt(WIDTH);
            room[i].p.y = RANDOM.nextInt(HEIGHT);
            if (checkRoom(room[i]) == true){
                paintRoom(room[i]);
                i++;
            }
        }
        sortRoom(room);
    }
    private void sortRoom(Room[] rooms){
        Room temp;
        int index;
        for (int i = 0;i < rooms.length; i++){
            index = i;
            for (int j = i; j < rooms.length; j++) {
                if (rooms[j].p.x < rooms[index].p.x){
                    index = j;
                }
            }
            temp = rooms[i];
            rooms[i] = rooms[index];
            rooms[index] = temp;
        }
    }
    private void paintRoom(Room room){
        for (int i = room.p.x ;i < room.p.x + room.wid; i++){
            finalWorldFrame[i][room.p.y] = Tileset.WALL;
            finalWorldFrame[i][room.p.y + room.height] = Tileset.WALL;
            finalWorldFrame[i + 1][room.p.y + room.height] = Tileset.WALL;
        }
        for (int i = room.p.y ;i < room.p.y + room.height; i++){
            finalWorldFrame[room.p.x][i] = Tileset.WALL;
            finalWorldFrame[room.p.x + room.wid][i] = Tileset.WALL;
        }
        for (int i = room.p.x + 1; i < room.p.x + room.wid; i++){
            for (int j = room.p.y + 1; j < room.p.y + room.height; j++){
                finalWorldFrame[i][j] = Tileset.FLOOR;
            }
        }
    }
    private boolean checkRoom(Room room){
        if(room.p.x + room.wid >= WIDTH  || room.p.y + room.height >= HEIGHT){
            return false;
        }
        for (int i = room.p.x; i < room.p.x + room.wid; i++){
            for (int j = room.p.y; j < room.p.y + room.height; j++){
                if (finalWorldFrame[i][j] == Tileset.WALL || finalWorldFrame[i][j] == Tileset.FLOOR ){
                    return false;
                }
            }
        }
        return true;
    }

    private class Room{
        public int height;
        public int wid;
        public Position p;
        public Room(){
            p = new Position();
        }
    }

    private class Position{
        public int x;
        public int y;
        Position(){
        }
        Position(int a, int b){
            x = a;
            y = b;
        }
        public void move(int dx, int dy){
            x += dx;
            y += dy;
        }
    }
    private TETile[][] turnNothing(TETile[][] world){
        for (int i = 0; i < WIDTH; i++){
            for (int j = 0; j < HEIGHT; j++){
                world[i][j] = Tileset.NOTHING;
            }
        }
        return world;
    }
}
