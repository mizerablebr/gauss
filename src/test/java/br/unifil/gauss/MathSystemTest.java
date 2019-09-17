package br.unifil.gauss;

import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathSystemTest {
    @Test
    public void calculate() {

        int n = 3;
        Float[][] matrix = new Float[n][];
        matrix[0] = new Float[]{3f, 2f, 4f, 1f};
        matrix[1] = new Float[]{1f, 1f, 2f, 2f};
        matrix[2] = new Float[]{4f, 3f, -2f, 3f};

        for (int k = 1; k < n; k++) {
            // Determina o Pivô
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
            // Zera elementos abaixo do pivô
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
        // Cria array preenchida com zeros
        Float[] x = new Float[n];
        Arrays.fill(x, 0f);
        // A partir do último X, segue calculando até o primeiro: x3 -> x2 -> x1 ...
        for (int i = n-1; i >=0; i--) {
            x[i] = matrix[i][n];
            for (int j = 0; j < x.length; j++) {
                // Pega os outros X, diferente do X atual.
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

    @Test
    public void readEquation() {
        int n = 3;
        String typedEquation = "3x^3+2x^2+4x=1\n1x^3+1x^2+2x=2\n4x^3+3x^2-2x=3";
        // Pega lista de números separados por linha
        String[] lines = typedEquation.split("\n");
        // Converte cada linha num array de float
        Pattern multiplier = Pattern.compile("[+\\-]*\\d(?=[x|X])");
        Pattern equality = Pattern.compile("(?<=\\=)\\d");

        Float[][] matrix = new Float[n][];
        for (int i = 0; i < n; i++) {
            Float[] line = new Float[n+1];
            int index = 0;
            Matcher matcher = multiplier.matcher(lines[i]);
            while (matcher.find())
               line[index++] = Float.valueOf(matcher.group());

            matcher = equality.matcher(lines[i]);
            matcher.find();
            line[n] = Float.valueOf(matcher.group());
            matrix[i] = line;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                System.out.printf("%.4f ",matrix[i][j]);
            }
            System.out.println();
        }
    }

    @Test
    public void mathSystemTest() {
        MathSystem ms = new MathSystem("3x^3+2x^2+4x=1\n1x^3+1x^2+2x=2\n4x^3+3x^2-2x=3", 3);
        ms.getX();
        System.out.println(ms);
    }
}
