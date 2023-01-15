import java.util.Comparator;


public class myComparator implements Comparator<edge> {

    
    public int compare(edge s1, edge s2) {
        if (s1.length < s2.length)
            return -1;
        else if (s1.length > s2.length)
            return 1;
        return 0;
    }

}