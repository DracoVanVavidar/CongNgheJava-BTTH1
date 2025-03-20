import java.util.Random;

public class ApproxPi {
    public static double approxPi(int numSamples) {
        Random rand = new Random();
        int insideCircle = 0;
        for (int i = 0; i < numSamples; i++) {
            double x = rand.nextDouble() * 2 - 1;
            double y = rand.nextDouble() * 2 - 1;
            if (x * x + y * y <= 1) {
                insideCircle++;
            }
        }
        return 4.0 * insideCircle / numSamples;
    }

    public static void main(String[] args) {
        double estimatedPi = approxPi(100000);
        System.out.println("Xấp xỉ giá trị của pi: " + estimatedPi);
    }
}
