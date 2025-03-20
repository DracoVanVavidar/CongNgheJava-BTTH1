import java.util.Random;

public class ApproxCircleArea {
    public static double approxCircleArea(double r, int numSamples) {
        Random rand = new Random();
        int insideCircle = 0;
        for (int i = 0; i < numSamples; i++) {
            double x = -r + 2 * r * rand.nextDouble();
            double y = -r + 2 * r * rand.nextDouble();
            if (x * x + y * y <= r * r) {
                insideCircle++;
            }
        }
        return (4.0 * insideCircle / numSamples) * (r * r);
    }

    public static void main(String[] args) {
        double r = 5.0;
        double estimatedArea = approxCircleArea(r, 100000);
        System.out.println("Xấp xỉ diện tích hình tròn bán kính " + r + ": " + estimatedArea);
    }
}
