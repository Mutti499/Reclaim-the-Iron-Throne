import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map.Entry;


public class project5 {
    public static void main(String args[]) throws IOException {

        FileReader in = new FileReader(args[0]);

        BufferedReader br = new BufferedReader(in);

        FileOutputStream foutput = new FileOutputStream(args[1]);
        System.setOut(new PrintStream(foutput));

        ArrayList<String> inputLines = new ArrayList<String>();

        String givenInputLine;
        while ((givenInputLine = br.readLine()) != null) {
            inputLines.add(givenInputLine);
        }
        in.close();

        int lineNum1 = Integer.parseInt(inputLines.get(0));
        String[] line = inputLines.get(1).split(" ");
        int lineNum = lineNum1 + line.length;
        lineNum += 2;// to ad KL and our arbitrary vertex

        graph Graph = new graph(lineNum);

        // ONE WAY GRAPH Construction
        for (int i = 0; i < lineNum - 2; i++) {
            line = inputLines.get(2 + i).split(" ");
            ArrayList<edge> neighbours = new ArrayList<edge>();
            String name = line[0];
            vertex newW = new vertex(name);

            for (int x = 0; x < (line.length - 1) / 2; x++) {

                int dist = Integer.parseInt(line[2 + 2 * x]);
                edge way = new edge(name, line[(1 + 2 * x)], dist);
                neighbours.add(way);
            }

            Graph.keys.put(name, newW);
            Graph.adjacency.put(newW, neighbours);

        }

        // adding start point
        String name = "start";
        vertex newW = new vertex(name);
        ArrayList<edge> neighbours = new ArrayList<edge>();
        line = inputLines.get(1).split(" ");

        for (int x = 0; x < (line.length); x++) {

            int dist = Integer.parseInt(line[x]);
            String[] line2 = inputLines.get(2 + x).split(" ");

            edge way = new edge(name, line2[0], dist);
            neighbours.add(way);

        }

        Graph.keys.put(name, newW);
        Graph.adjacency.put(newW, neighbours);

        // adding end point
        name = "KL";
        newW = new vertex(name);
        neighbours = new ArrayList<edge>();
        Graph.keys.put(name, newW);
        Graph.adjacency.put(newW, neighbours);

        for (int i = 0; i < lineNum - 2; i++) {
            line = inputLines.get(2 + i).split(" ");

            String toAdd = line[0];
            for (int x = 0; x < (line.length - 1) / 2; x++) {
                String slot = line[(1 + 2 * x)];
                vertex slotW = Graph.keys.get(slot);
                ArrayList<edge> n = Graph.adjacency.get(slotW);

                boolean add = true;
                for (int a = 0; a < n.size(); a++) {

                    if (n.get(a).destination.equals(toAdd)) {
                        add = false;
                        break;
                    }

                }
                if (add) {
                    edge way = new edge(slot, toAdd, 0);

                    Graph.adjacency.get(slotW).add(way);
                }

            }

        }

        line = inputLines.get(1).split(" ");

        for (int x = 0; x < (line.length); x++) {

            int dist = 0;
            String[] line2 = inputLines.get(2 + x).split(" ");
            vertex addTO = Graph.keys.get(line2[0]);

            edge way = new edge(line2[0], "start", dist);
            Graph.adjacency.get(addTO).add(way);
        }


        System.out.println(Graph.FF("start", "KL"));


        for (Entry<vertex, ArrayList<edge>> set : Graph.adjacency.entrySet()) {
            for( edge edge : set.getValue()){
                vertex dest = Graph.keys.get(edge.destination);
                vertex from = Graph.keys.get(edge.from);

                if(from.visited && !dest.visited){
                    if(from.name.equals("start")){
                        System.out.println(edge.destination);
                    }
                    else{
                        System.out.println(edge.from + " " + edge.destination);
                    }
                }
            }

        }


       
    }
}