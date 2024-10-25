import com.zeroc.Ice.Current;

public class ClientI implements Montecarlo.Client {
    @Override
    public void receivePiEstimate(float pi, Current current) {
        System.out.println("Estimación de Pi: " + pi);
    }
}
