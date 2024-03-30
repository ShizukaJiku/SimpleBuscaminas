package com.shizuka;

import java.util.Random;

public class Buscaminas {
    private final int size;
    private final int minesCount;
    private final int[][] table;

    public Buscaminas(int size, int minesCount) {
        this.size = size;
        this.minesCount = minesCount;
        this.table = new int[size][size];
        generateTable();
    }

    private void generateTable() {
        generateMines();
        generateGuides();
    }

    private void generateMines() {
        Random random = new Random();
        int minesPlaced = 0;

        while (minesPlaced < minesCount) {
            int x = random.nextInt(size);
            int y = random.nextInt(size);

            if (table[x][y] == 0) {
                table[x][y] = -1;
                minesPlaced++;
            }
        }
    }

    private void generateGuides(){
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if(table[x][y] == -1) addGuide(x, y);
            }
        }
    }

    private void addGuide(int x, int y) {
        int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

        for (int i = 0; i < 8; i++) {
            tryAddGuide(x + dx[i], y + dy[i]);
        }
    }

    private void tryAddGuide(int x, int y) {
        if (!isValidCoordinate(x, y)) return;
        if (table[x][y] == -1) return;
        table[x][y]++;
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                var value = table[x][y] == -1 ? 'M' : String.valueOf(table[x][y]);
                stringBuilder.append(value);

                if(y != size - 1) stringBuilder.append(' ');
            }
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }
}
