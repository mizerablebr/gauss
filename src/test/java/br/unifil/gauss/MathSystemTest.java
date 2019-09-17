package br.unifil.gauss;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                m[i] = matrix[i][0]/pivot;
            }
            // Calcula elementos
            //TODO: guardar matriz anterior antes da alteração
            for (int i = k; i < n; i++) {
                for (int j = 0; j < matrix[k].length; j++) {
                    float a = matrix[i][j] -(m[i] * matrix[i-1][j]);
                    matrix[k][j] = a;
                }
            }

        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }
}
