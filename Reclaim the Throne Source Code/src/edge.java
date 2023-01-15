public class edge{
    String from;
    String destination;
    int length;

    public edge(String from, String destination, int length) {
        this.from = from;
        this.destination = destination;
        this.length = length;
    }

    @Override
    public String toString() {
        //return "[from=" + from + " to " + destination + ", length=" + length + "]";
        return from  + " to "+ destination + " " + length;
        //return "to " + destination + ", length=" + length + " ";
    }


    
}
