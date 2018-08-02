import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args)throws Exception {
        Scanner user_input = new Scanner(System.in);
        System.out.print("Enter your cities: ");
        String user;
        user = user_input.nextLine();
        String[] userSplit = user.split(" ");

        String userStart = userSplit[0];
        String userEnd = userSplit[1];

        Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();

        File file = new File("src\\cities.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            String[] lineSplit = line.split(" ");
            Vertex.setVertex(lineSplit, vertexMap);
            String[] keys = vertexMap.keySet().toArray(new String[vertexMap.keySet().size()]);

            for (int i = 0; i < keys.length; i++) {
                int isEnd = 0;

                vertexMap.get(keys[i]).setDist(100000);

                if (keys[i].equals(lineSplit[0])) {
                    vertexMap.get(keys[i]).setPath(lineSplit, isEnd);
                }

                if (keys[i].equals(lineSplit[2])) {
                    isEnd = 1;
                    vertexMap.get(keys[i]).setPath(lineSplit, isEnd);
                }
            }
        }

        br.close();

        List<String> unvisited = new ArrayList<>(vertexMap.keySet());

        vertexMap.get(userStart).setDist(0);

        while (unvisited.size() != 0) {
            String visiting = Vertex.compareDist(unvisited, vertexMap);

            for (int i = 0; i < vertexMap.get(visiting).getPath().size(); i++) {
                int testDist = vertexMap.get(visiting).getDist() + vertexMap.get(visiting).getPathItem(i).getDist();
                String endCity = vertexMap.get(visiting).getPathItem(i).getEndCity();

                if (testDist < vertexMap.get(endCity).getDist()) {
                    vertexMap.get(endCity).setDist(testDist);
                    vertexMap.get(endCity).setPrevVertex(visiting);
                }

            }

            unvisited.remove(visiting);
        }

        Stack shortestPath = new Stack();
        String checkString = "Garrett";
        int popAmount = 0;
        shortestPath.push(userEnd);
        String previousBlock = "Garrett";



        while (!checkString.equals(userStart)) {

            if (popAmount == 0) {
                shortestPath.push(vertexMap.get(userEnd).getPrevVertex());
                previousBlock = vertexMap.get(userEnd).getPrevVertex();
                checkString = previousBlock;
            }

            if (popAmount >= 1) {
                shortestPath.push(vertexMap.get(previousBlock).getPrevVertex());
                previousBlock = vertexMap.get(previousBlock).getPrevVertex();
                checkString = previousBlock;
            }

            popAmount += 1;
        }

        System.out.println("The shortest distance between the two cities is: " + vertexMap.get(userEnd).getDist());

        System.out.print("The route is: ");

        for (int i= 0; i <= popAmount; i++) {
            System.out.print(shortestPath.pop());

            if (i != popAmount) {
                System.out.print(" -> ");
            }
        }
    }
}
