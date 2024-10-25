import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

public class Master {
    public static void main(String[] args) {
        try (Communicator communicator = Util.initialize(args)) {
            // Crear un adaptador en el puerto 10000
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("MasterAdapter", "default -h localhost -p 10000");

            // Crear instancia del maestro sin necesidad de un clientProxy
            MasterI taskCoordinator = new MasterI(communicator);

            // Registrar el objeto maestro en el adaptador
            adapter.add(taskCoordinator, Util.stringToIdentity("Master"));

            // Activar el adaptador
            adapter.activate();
            System.out.println("El servidor maestro está listo en el puerto 10000...");
            communicator.waitForShutdown();
        }
    }
}
