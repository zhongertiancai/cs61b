package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean haveFound = false;
    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        Queue<Integer> q = new LinkedList<>();
        int vertex = s;
        while (!haveFound) {
            for (int vn : maze.adj(vertex)) {
                if (marked[vn] == false) {
                    marked[vn] = true;
                    distTo[vn] = distTo[vertex] + 1;
                    edgeTo[vn] = vertex;
                    q.add(vn);
                    announce();
                    if (vn == t) {
                        haveFound = true;
                    }
                }

            }
            if (q.isEmpty()) {
                break;
            }
            vertex = q.remove();
        }
    }


    @Override
    public void solve() {
         bfs();
    }
}

