module Montecarlo {
    interface Master {
        float estimatePi(int N, int numWorkers);
    }

    interface Worker {
        int countPointsInCircle(int numPoints);
    }

    interface Client {
        void receivePiEstimate(float pi);
    }
}
