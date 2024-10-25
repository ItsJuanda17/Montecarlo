import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

public class Worker {
    public static void main(String[] args) {
        try (Communicator communicator = Util.initialize(args)) {
            // Crear un adaptador de objeto en el puerto correspondiente al trabajador
            int workerId = Integer.parseInt(args[0]);
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("WorkerAdapter", "default -p " + (10001 + workerId));

            WorkerI worker = new WorkerI();
            adapter.add(worker, Util.stringToIdentity("Worker" + workerId));

            adapter.activate();
            System.out.println("Trabajador " + workerId + " listo en el puerto " + (10001 + workerId));
            communicator.waitForShutdown();
        }
    }
}
