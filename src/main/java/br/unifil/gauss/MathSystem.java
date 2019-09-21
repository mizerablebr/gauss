package br.unifil.gauss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathSystem {
    private int n;
    private Float[][] matrix;
    private String matrixString;
    private List<Float[][]> steps;
    private Float[] x;

    public MathSystem(String typedEquation, int n) {
        setN(n);

    }

    public MathSystem() {
    }

    public Float[] calculateX() {
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
            addStep(previousMatrix);
            // Zera elementos abaixo do pivô
            for (int i = k; i < n; i++) {
                for (int j = 0; j < matrix[k].length; j++) {
                    float a = previousMatrix[i][j] -(m[i] * previousMatrix[k-1][j]);
                    matrix[i][j] = a;
                }
            }

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

        return this.x = x;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
        // Pega lista de números separados por linha
        String[] lines = matrixString.split("\n");
        updateMatrix(lines);
    }

    public Float[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Float[][] matrix) {
        this.matrix = matrix;
    }

    public List<Float[][]> getSteps() {
        return steps;
    }

    public void setSteps(List<Float[][]> steps) {
        this.steps = steps;
    }

    private void addStep(Float[][] step) {
        if (getSteps() == null)
            setSteps(new ArrayList<>());

        getSteps().add(step);
    }

    public String getMatrixString() {
        return matrixString;
    }

    public void setMatrixString(String matrixString) {
        this.matrixString = matrixString;
        // Pega lista de números separados por linha
        String[] lines = this.matrixString.split("\n");
        updateMatrix(lines);
    }

    public void setX(Float[] x) {
        this.x = x;
    }

    public Float[] getX() {
        return x;
    }

    @Override
    public String toString() {
        return "MathSystem{" +
                "n=" + n +
                "\n , matrix=" + printFloatArrayBidimencional(matrix) +
                "\n, steps=" + printListOfFloatArrayBidimencional(steps) +
                '}';
    }

    private static String printFloatArrayBidimencional(Float[][] f) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[i].length; j++) {
                sb.append(String.format("%.4f ",f[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static String printListOfFloatArrayBidimencional(List<Float[][]> lf) {
        StringBuilder sb = new StringBuilder();
        lf.forEach(f -> {
            sb.append(printFloatArrayBidimencional(f));
            sb.append("\n");
        });
        return  sb.toString();
    }

    private void updateMatrix(String[] lines) {
        Pattern multiplier = Pattern.compile("[+\\-]*\\d(?=[x|X])");
        Pattern equality = Pattern.compile("(?<=\\=)\\d");

        matrix = new Float[n][];
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
        calculateX();
    }
}
