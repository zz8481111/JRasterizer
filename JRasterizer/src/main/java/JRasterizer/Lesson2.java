package JRasterizer;

import java.util.Arrays;
import java.util.Comparator;

public class Lesson2 extends Lesson1 {

    int[] zbuffer = new int[size * size];

    public Lesson2(int size) {
        super(size);
    }

    public void drawTriangle(int[] p1, int[] p2, int[] p3, int color){
        int[][] points = new int[3][3];
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        Arrays.sort(points, Comparator.comparing(it -> it[1]));


        double kb = (double) (points[2][0] - points[0][0])/(points[2][1] - points[0][1]);
        double ka = (double) (points[2][0] - points[1][0])/(points[2][1] - points[1][1]);
        double kc = (double) (points[1][0] - points[0][0])/(points[1][1] - points[0][1]);

        for (int i = 0; i < points[1][1] - points[0][1]; i++){
            drawLine(new int[]{points[0][0] + (int) (kb * i), points[0][1] + i, points[0][2]},
                    new int[]{points[0][0] + (int) (kc * i), points[0][1] + i, points[0][2]}, color);
        }
        for (int i = 0; i <= points[2][1] - points[1][1]; i++){
            drawLine(new int[]{points[2][0] - (int) (kb * i), points[2][1] - i, points[0][2]},
                    new int[]{points[2][0] - (int) (ka * i), points[2][1] - i, points[0][2]}, color);
        }
        drawLine(p1, p2, color);
        drawLine(p1, p3, color);
        drawLine(p3, p2, color);
    }

    public void test(){
        drawTriangle(new int[]{20, 20, 100},
                     new int[]{50, 10, 100},
                     new int[]{80, 40, 30}, 0xff00ffff);
    }

    public void setPixel(int[] point, int color){
        if (0 <= point[0] && point[0] < size && 0 <= point[1] && point[1] < size && point[2] <= zbuffer[point[0] + size * (size - point[1] - 1)]) {
            data[point[0] + size * (size - point[1] - 1)] = color;
            zbuffer[point[0] + size * (size - point[1] - 1)] = point[2];
        }
    }

    public void drawLine (int[] p1, int[] p2, int color) {
        if (p1[1] == p2[1]){
            for (int i=Math.min(p1[0], p2[0]); i<=Math.max(p1[0], p2[0]); i++){
                setPixel(new int[]{i, p1[1], p1[2]}, color);
            }
        } else if(Math.abs(p2[0] - p1[0]) > Math.abs(p2[1] - p1[1])){
            float t = ((float) p2[1] - p1[1])/(p2[0] - p1[0]);
            int startx = Math.min(p1[0], p2[0]);
            int starty = t < 0 ? Math.max(p1[1], p2[1]) : Math.min(p1[1], p2[1]);
            for (int i=0; i<= Math.abs(p1[0] - p2[0]); i++){
                setPixel(new int[]{i + startx, (int) (i*t) + starty, p1[2]}, color);
            }
        } else {
            float t = ((float) p2[0] - p1[0])/(p2[1] - p1[1]);
            int starty = Math.min(p1[1], p2[1]);
            int startx = t < 0 ? Math.max(p1[0], p2[0]) : Math.min(p1[0], p2[0]);
            for (int i=0; i<= Math.abs(p1[1] - p2[1]); i++){
                setPixel(new int[]{(int) (i*t) + startx, i + starty, p1[2]}, color);
            }
        }
    }
}
