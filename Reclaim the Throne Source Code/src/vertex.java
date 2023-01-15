
public class vertex{
    String name;
    vertex parent = null;
    boolean visited = false;

    public vertex(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
        //return name + " : " + distanceStart;
       // return "vertex name=" + name ;
    }

}
