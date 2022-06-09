package Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        String map = null;
        String creature = null;
        boolean proofmap = false;
        Scanner scanner = new Scanner(System.in);

        while (proofmap==false){
            System.out.println("Введите 16 заглавных букв");
            map = scanner.nextLine();
            if (map.length()==16){
                proofmap=true;
            }
            else System.out.println("Ошибка!");
        }

            System.out.println("Введите название существа");
            creature = scanner.nextLine();

        getResult(map.toUpperCase(), creature);
    }

    public static void getResult(String p_map, String p_creature) throws FileNotFoundException {

        int k = 11, n = 0;
        String[] Finfo = new String[k];
        Scanner in = new Scanner(new File("info.txt"));
        while(in.hasNext())
        {
            Finfo[n] = in.nextLine();
            n++;
        }
        in.close();

        String line;
        for (int g = 1; g < 9; g++) {
            if (p_creature.compareTo(Finfo[g-1])==0){
                line = Finfo[g] + Finfo[g+1];
                for(int h = 0; h<=3; h++){
                    for(int l = 0; l<15; l++){
                        if(line.charAt(h)==p_map.charAt(l)){
                            p_map = p_map.replace(p_map.charAt(l), line.charAt(h+4));
                        }
                    }

                }

            }
        }

        int[][] map = Arrays.stream(p_map.split("(?<=\\G.{4})")).map(s -> (Arrays.stream(s.split("(?<=\\G.{1})")).mapToInt(Integer::parseInt).toArray())).toArray(int[][]::new);
        int rows = map.length;
        int columns = map[0].length;

        for (int i = 1; i < columns; i++) {
            map[0][i] += map[0][i - 1];
        }
        for (int i = 1; i < rows; i++) {
            map[i][0] += map[i - 1][0];
        }
        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < columns; c++) {
                int from1 = map[r - 1][c];
                int from2 = map[r][c - 1];
                map[r][c] += Math.min(from1, from2);
            }
        }
        System.out.print("Кратчайший путь стоит: ");
        System.out.print(map[rows - 1][columns - 1] - map[0][0]);
    }
}
