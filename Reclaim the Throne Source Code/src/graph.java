import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;


public class graph {

    int vertices;

    HashMap<String, vertex> keys = new HashMap<String, vertex>();
    HashMap<vertex, ArrayList<edge>> adjacency = new HashMap<vertex, ArrayList<edge>>();

    graph(int size) {
        this.vertices = size;
    }

    public void dfs(vertex current) {
        current.visited = true;
        for ( edge edge : adjacency.get(current)) {
            vertex dest = keys.get(edge.destination);
            if(!dest.visited && edge.length != 0){
                dfs(dest);
            }
        }
    }



    public int FF(String startS, String finishS) {
        int MaxFlow = 0;
        boolean uh = true;
        
        while (uh) {
            bfs(startS, finishS);

            // Path bulma
            ArrayList<edge> path = new ArrayList<>();
            PriorityQueue<edge> minFinder = new PriorityQueue<edge>(new myComparator());

            vertex current = keys.get(finishS);

            while (current.parent != null) {
                vertex parent = current.parent;
                ArrayList<edge> n = adjacency.get(parent);
                for (edge edge : n) {
                    if (edge.destination.equals(current.name)) {
                        path.add(edge);
                        minFinder.add(edge);
                        break;
                    }
                }
                current = current.parent;

            }

            for (Map.Entry<String, vertex> set : keys.entrySet()) {
                set.getValue().parent = null;
            }

            //System.out.println(path);
            //System.out.println(minFinder);

            int min = minFinder.poll().length;
            MaxFlow += min;

            for (edge edge : path) {
                String startsS = edge.from;
                vertex from = keys.get(startsS);

                String endsS = edge.destination;
                vertex to = keys.get(endsS);

                ArrayList<edge> way = adjacency.get(from);
                ArrayList<edge> wayReverse = adjacency.get(to);

                for (edge edge2 : way) {

                    if (to.name.equals(edge2.destination)) {
                        edge.length -= min;
                    }
                }

                for (edge edge2 : wayReverse) {
                    if (from.name.equals(edge2.destination)) {
                        edge2.length += min;
                    }
                }

            }

            //System.out.println(adjacency);
            uh = bfs(startS, finishS);

        }
        vertex start = keys.get(startS);
        dfs(start);


        return MaxFlow;

    }

    public boolean bfs(String startS, String endS) {
        vertex start = keys.get(startS);
        HashSet<vertex> visited = new HashSet<vertex>();
        Queue<vertex> Qu = new LinkedList<>();

        Qu.add(start);
        visited.add(start);

        while (!Qu.isEmpty()) {

            vertex current = Qu.poll();
            visited.add(current);
            for (edge edge : adjacency.get(current)) {
                if (edge.length == 0) {
                    continue;
                }
                vertex dest = keys.get(edge.destination);

                if (dest.name.equals(endS)) {
                    dest.parent = current;
                    return true;
                }
                if (!visited.contains(dest) && edge.length != 0) {
                    dest.parent = current;
                    Qu.add(dest);
                    //visited.add(dest);
                }

            }

        }

        return false;
    }

}
