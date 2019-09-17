package br.unifil.gauss;

import org.junit.Test;

import java.util.Arrays;

public class MathSystemTest {
    @Test
    public void calculate() {

        int n = 3;
        Float[][] matrix = new Float[n][];
        matrix[0] = new Float[]{3f, 2f, 4f, 1f};
        matrix[1] = new Float[]{1f, 1f, 2f, 2f};
        matrix[2] = new Float[]{4f, 3f, -2f, 3f};

        for (int k = 1; k < n; k++) {

            Float pivot = matrix[k-1][k-1];
            Float[] m = new Float[n + 1];
            // Gera multiplicadores
            for (int i = k; i < n; i++) {
                m[i] = matrix[i][k-1]/pivot;
            }
            // Calcula elementos

            // Guarda matriz anterior
            Float[][] previousMatrix = new Float[n][n+1];
            for (int i = 0; i < n; i++) {
                System.arraycopy(matrix[i], 0, previousMatrix[i], 0, previousMatrix[i].length);
            }

            for (int i = k; i < n; i++) {
                for (int j = 0; j < matrix[k].length; j++) {
                    float a = previousMatrix[i][j] -(m[i] * previousMatrix[k-1][j]);
                    matrix[i][j] = a;
                }
            }

        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                System.out.printf("%.4f ",matrix[i][j]);
            }
            System.out.println();
        }

        // Calcular o X;

        Float[] x = new Float[n];
        Arrays.fill(x, 0f);
        for (int i = n-1; i >=0; i--) {
            x[i] = matrix[i][n];
            for (int j = 0; j < x.length; j++) {
                // Pega os outros X
                if (j != i)
                    x[i] = x[i] - matrix[i][j] * x[j];
            }
            x[i] = x[i] / matrix[i][i];
        }

        for (Float aFloat : x) {
            if (aFloat < 0 && aFloat >-0.0001)
                aFloat = 0f;
            System.out.printf("%.4f ", aFloat);
        }

    }


}
