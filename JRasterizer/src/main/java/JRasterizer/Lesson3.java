package JRasterizer;

import java.util.Arrays;
import java.util.Comparator;

public class Lesson3 extends Lesson2 {

    public Lesson3(int size) {
        super(size);
    }

    public void drawTriangle(int[] p1, int[] p2, int[] p3, int color){
        int[][] points = new int[3][3];
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        Arrays.sort(points, Comparator.comparing(it -> it[1]));

        double kb = (double) (points[2][0] - points[0][0])/(points[2][1] - points[0][1]);
        double kbz = (double) (points[2][2] - points[0][2])/(points[2][1] - points[0][1]);

        double ka = (double) (points[2][0] - points[1][0])/(points[2][1] - points[1][1]);
        double kaz = (double) (points[2][2] - points[1][2])/(points[2][1] - points[1][1]);

        double kc = (double) (points[1][0] - points[0][0])/(points[1][1] - points[0][1]);
        double kcz = (double) (points[1][2] - points[0][2])/(points[1][1] - points[0][1]);

        for (int i = 0; i < points[1][1] - points[0][1]; i++){
            drawLine(new int[]{points[0][0] + (int) (kb * i), points[0][1] + i, points[0][2] + (int) (kbz * i)},
                    new int[]{points[0][0] + (int) (kc * i), points[0][1] + i, points[0][2] + (int) (kcz * i)}, color);
        }
        for (int i = 0; i <= points[2][1] - points[1][1]; i++){
            drawLine(new int[]{points[2][0] - (int) (kb * i), points[2][1] - i, points[2][2] - (int) (kbz * i) },
                    new int[]{points[2][0] - (int) (ka * i), points[2][1] - i, points[2][2] - (int) (kaz * i)}, color);
        }
        drawLine(p1, p2, color);
        drawLine(p1, p3, color);
        drawLine(p3, p2, color);
    }

    public void drawLine (int[] p1, int[] p2, int color) {
        if (p1[1] == p2[1]){
            var pleft = p1[0] < p2[0] ? p1 : p2;
            var pright = p1[0] < p2[0] ? p2 : p1;
            double tz = (double) (pright[2] - pleft[2])/(pright[0] - pleft[0]);
            for (int i=pleft[0]; i<pright[0]; i++){
                setPixel(new int[]{i, p1[1], pleft[2] + (int) (tz * i)}, color);
            }
        } else if(Math.abs(p2[0] - p1[0]) > Math.abs(p2[1] - p1[1])){
            float t = ((float) p2[1] - p1[1])/(p2[0] - p1[0]);
            float tz = ((float) p2[2] - p1[2])/(p2[0] - p1[0]);
            var startx = p1[0]<p2[0] ? p1 : p2;
            int starty = t < 0 ? Math.max(p1[1], p2[1]) : Math.min(p1[1], p2[1]);
            for (int i=0; i<= Math.abs(p1[0] - p2[0]); i++){
                setPixel(new int[]{i + startx[0], (int) (i*t) + starty, startx[2] + (int) (tz * i)}, color);
            }
        } else {
            float t = ((float) p2[0] - p1[0])/(p2[1] - p1[1]);
            float tz = ((float) p2[2] - p1[2])/(p2[1] - p1[1]);
            var starty = p1[1] < p2[1] ? p1 : p2;
            int startx = t < 0 ? Math.max(p1[0], p2[0]) : Math.min(p1[0], p2[0]);
            for (int i=0; i<= Math.abs(p1[1] - p2[1]); i++){
                setPixel(new int[]{(int) (i*t) + startx, i + starty[1], starty[2] + (int) (tz * i)}, color);
            }
        }
    }
}
