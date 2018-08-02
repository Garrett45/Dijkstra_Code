public class Path {
    String startCity;
    int dist;
    String endCity;

    Path(String[] in, int n) {
        dist = Integer.parseInt(in[1]);

        if (n == 0) {
            startCity = in[0];
            endCity = in[2];
        }

        if (n == 1) {
            startCity = in[2];
            endCity = in[0];
        }

    }

    public String getEndCity() {
        return endCity;
    }

    public int getDist() {
        return dist;
    }
}
