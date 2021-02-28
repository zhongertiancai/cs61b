package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private int[] priority;
    private boolean targetFound = false;
    private Maze maze;
    private int sourceX;
    private int sourceY;
    private int targetX;
    private int targetY;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        priority = new int[m.V()];
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.targetX = targetX;
        this.targetY = targetY;
        initial();
        edgeTo[s] = s;
        distTo[s] = 0;
        marked[s] = true;
        announce();
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs(maze.toX(v) - targetX) + Math.abs(maze.toY(v) - targetY);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        int min = -1;
        for (int i = 0; i < maze.V(); i++) {
            if (!marked[i]) {
                if (min == -1) {
                    min = i;
                } else {
                    if (priority[min] > priority[i]) {
                        min = i;
                    }
                }
            }
        }
        return min;

        /* You do not have to use this method. */
    }
    private void initial() {
        for (int i = 0; i < maze.V(); i++) {
            distTo[i] = 100000;
            priority[i] = 1000000;
            marked[i] = false;
        }
    }
    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        int vertex = s;
        while (!marked[t]) {
            for (int v : maze.adj(vertex)) {
                if (edgeTo[vertex] != v) {
                    edgeTo[v] = vertex;
                    distTo[v] = distTo[vertex] + 1;
                    priority[v] = distTo[v] + h(v);
                }
            }
            vertex = findMinimumUnmarked();
            if (vertex == -1) {
                break;
            }
            marked[vertex] = true;
            announce();
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

