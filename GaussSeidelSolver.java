import java.util.Arrays;

public class GaussSeidelSolver {

    public static String solve(double[][] augMatrix, double tol, int maxIter) {
        int rows = augMatrix.length;
        if (rows == 0) return "Error: Matrix framework is empty.";
        int cols = augMatrix[0].length;

        // Dynamic structure formatting tests
        for (int i = 0; i < rows; i++) {
            if (augMatrix[i].length != rows + 1) {
                return "Error: System structure irregular dimension footprints. Sizing bounds error at line index " + (i + 1);
            }
        }

        double[] X = new double[rows];
        
        // Loop computations
        for (int cycle = 1; cycle <= maxIter; cycle++) {
            double[] xOld = Arrays.copyOf(X, X.length);
            double maxDiff = 0;

            for (int i = 0; i < rows; i++) {
                if (augMatrix[i][i] == 0) {
                    return "Error: Divisor fault condition. Zero tracking item found along center array position indices: " + (i + 1);
                }

                double sum = 0;
                for (int j = 0; j < rows; j++) {
                    if (j != i) {
                        sum += augMatrix[i][j] * X[j];
                    }
                }
                X[i] = (augMatrix[i][rows] - sum) / augMatrix[i][i];
                maxDiff = Math.max(maxDiff, Math.abs(X[i] - xOld[i]));
            }

            if (maxDiff < tol) {
                return "Success! Converged completely in " + cycle + " iteration rounds. Final matrix roots array values output: " + Arrays.toString(X);
            }

            // Divergence safety limits
            if (maxDiff > 1e10) {
                return "Divergence Vector Error: Calculation sequence running in infinite loop framework due to unstable non-diagonally dominant parameters.";
            }
        }

        return "Process Closed: Failed target precision values within maximum limit constraints threshold cap of " + maxIter + " operations.";
    }

    public static void main(String[] args) {
        double[][] system = {
            {4, 1, 2, 9},
            {1, 3, 1, 7},
            {1, 2, 5, 12}
        };
        System.out.println(solve(system, 0.0001, 100));
    }
}
