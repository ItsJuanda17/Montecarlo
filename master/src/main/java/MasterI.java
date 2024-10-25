import Montecarlo.WorkerPrx;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;

public class MasterI implements Montecarlo.Master {
    private Communicator communicator;

    public MasterI(Communicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public float estimatePi(int numPoints, int numWorkers, Current current) {
        System.out.println("Estimando el valor de π con " + numPoints + " puntos y " + numWorkers + " trabajadores...");
        int totalPointsInCircle = 0;
        int pointsPerWorker = numPoints / numWorkers;

        // Distribuir el trabajo entre los trabajadores
        for (int i = 0; i < numWorkers; i++) {
            WorkerPrx worker = WorkerPrx.checkedCast(
                communicator.stringToProxy("Worker" + i + ":default -h localhost -p " + (10001 + i))
            );

            if (worker != null) {
                System.out.println("Solicitando al trabajador " + i + " calcular " + pointsPerWorker + " puntos...");
                totalPointsInCircle += worker.countPointsInCircle(pointsPerWorker);
            } else {
                System.out.println("El trabajador " + i + " no está disponible.");
            }
        }

        // Estimar el valor de Pi y devolverlo al cliente
        return 4.0f * totalPointsInCircle / numPoints;
    }
}
