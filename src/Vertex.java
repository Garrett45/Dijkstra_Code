import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Vertex {

    List<Path> pathList = new ArrayList<>();
    String prevVertex;
    int dist;

    public static void setVertex(String[] in, Map<String, Vertex> vertexMap) {
        boolean objectExist = false;
        int isThirdNum = 0;

        if (vertexMap.keySet().contains(in[0])) {
            objectExist = true;
        }

        if (!vertexMap.keySet().contains(in[2])) {
            isThirdNum = 1;
        }

        if (objectExist == false) {
            Vertex name = new Vertex();
            vertexMap.put(in[0], name);
        }

        if (isThirdNum == 1) {
            Vertex name = new Vertex();
            vertexMap.put(in[2], name);
        }
    }

    public void setPath(String[] in, int n) {
        Path path = new Path(in, n);
        pathList.add(path);
    }

    public void setPrevVertex(String c) {
        prevVertex = c;
    }

    public String getPrevVertex() {
        return prevVertex;
    }

    public void setDist(int n) {
        dist = n;
    }

    public int getDist() {
        return dist;
    }

    public List getPath() {
        return pathList;
    }

    public Path getPathItem(int n) {
        return pathList.get(n);
    }

    public static String compareDist(List<String> in, Map<String, Vertex> vertexMap) {
        int smallest = 10000000;
        String smallestKey = "Garrett";

        for (int i = 0; i < in.size(); i++) {
            int checkNum = vertexMap.get(in.get(i)).getDist();

            if (checkNum < smallest) {
                smallest = checkNum;
                smallestKey = in.get(i);
            }
        }

        return smallestKey;
    }
}