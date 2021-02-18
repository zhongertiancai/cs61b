package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world that contains RANDOM tiles.
 */
public class RandomWorldDemo {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    /**
     * Fills the given 2D array of tiles with RANDOM tiles.
     * @param tiles
     */
    public static void fillWithRandomTiles(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    /** Picks a RANDOM tile with a 33% change of being
     *  a wall, 33% chance of being a flower, and 33%
     *  chance of being empty space.
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.SAND;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            default: return Tileset.TREE;
        }
    }
    private static class Position{
        Position(int a, int b){
            x = a;
            y = b;
        }
        private int x;
        private int y;
    }
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t){
        for(int i = 0; i < s ; i += 1){
            for(int j = s - 1 -i; j < 2*s -1  +i ;j++){
                world[p.x + j][p.y + i] = t;
                world[p.x + j][p.y + 2*s-i-1] = t;
            }
        }
    }
    public static void tesselate(TETile[][] world,int s){
        for(int i = 0 ; i < 5; i++){
            vertical(world,s,i);
        }
    }

    private static void vertical(TETile[][] world,int s,int column){
        for(int i = 0;i < 5-Math.abs(column-2);i++) {
            Position p = new Position(column * (2 * s - 1), 2*s*i+s * Math.abs(column - 2) + 10);
            addHexagon(world, p, s, randomTile());
        }
    }
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] randomTiles = new TETile[WIDTH][HEIGHT];
        fillWithRandomTiles(randomTiles);
        tesselate(randomTiles,3);
        ter.renderFrame(randomTiles);
    }


}
