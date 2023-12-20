package Lab6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class FloydMethodParallel {

    private final long[][] C;
    private final long[][] P;
    private final ExecutorService executor;

    public FloydMethodParallel(long[][] C, long[][] P, int nThreads) {
        this.C = C;
        this.P = P;
        this.executor = Executors.newFixedThreadPool(nThreads);
    }

    public long[][] getC() {
        return C;
    }

    public long[][] getP() {
        return P;
    }

    public void findShortestPaths() {
        int n = C.length;

        for (int k = 0; k < n; k++) {
            final int finalK = k;
            executor.submit(() -> {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (C[i][finalK] + C[finalK][j] < C[i][j]) {
                            C[i][j] = C[i][finalK] + C[finalK][j];
                            P[i][j] = finalK + 1;
                        }
                    }
                }
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
