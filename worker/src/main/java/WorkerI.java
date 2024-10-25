import com.zeroc.Ice.Current;

public class WorkerI implements Montecarlo.Worker {
    @Override
    public int countPointsInCircle(int numPoints, Current current) {
        int pointsInCircle = 0;
        for (int i = 0; i < numPoints; i++) {
            double x = Math.random();
            double y = Math.random();
            if (x * x + y * y <= 1) {
                pointsInCircle++;
            }
        }
        return pointsInCircle;
    }
}
