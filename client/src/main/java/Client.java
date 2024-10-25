import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;

public class Client {
    public static void main(String[] args) {
        try (Communicator communicator = Util.initialize(args)) {
            // Crear el proxy para el objeto Maestro
            ObjectPrx base = communicator.stringToProxy("Master:default -h localhost -p 10000");
            Montecarlo.MasterPrx master = Montecarlo.MasterPrx.checkedCast(base);

            if (master == null) {
                throw new Error("Invalid proxy for Master");
            }

            // Enviar la solicitud al Maestro con N puntos y numWorkers
            int N = 100000;
            int numWorkers = 4;

            // Solicitar la estimación de Pi y obtener el resultado
            float piEstimate = master.estimatePi(N, numWorkers);
            System.out.println("Estimación de Pi: " + piEstimate);
        }
    }
}
