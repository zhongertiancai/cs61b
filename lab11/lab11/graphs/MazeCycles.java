package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    public int[] prev;
    private int s;
    private int t;
    public MazeCycles(Maze m) {
        super(m);
        maze = m;;
        prev = new int[m.V()];
        s = maze.xyTo1D(0, 0);
    }

    @Override
    public void solve() {
        dfs(0);
    }
    private void dfs(int vertex) {
        for (int t : maze.adj(vertex)) {
            if (t == prev[vertex]) {
                marked[t] = true;
                continue;
            }
            if (marked[t] == false) {
                marked[t] = true;
                prev[t] = vertex;
                announce();
                dfs(t);
            }
            if (marked[t] == true) {
                prev[t] = vertex;
                t = vertex;
                 do{
                    edgeTo[t] = prev[t];
                    t = prev[t];
                }while (vertex != t);
                 announce();
                 break;
            }
        }
    }
    // Helper methods go here
}

