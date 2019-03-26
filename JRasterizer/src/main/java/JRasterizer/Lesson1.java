package JRasterizer;

public class Lesson1 extends Lesson0 {

    public Lesson1(int size) {
        super(size);
    }

    public void drawLine (int[] p1, int[] p2, int color) {
        if (p1[1] == p2[1]){
            for (int i=Math.min(p1[0], p2[0]); i<=Math.max(p1[0], p2[0]); i++){
                setPixel(new int[]{i, p1[1]}, color);
            }
        } else if(Math.abs(p2[0] - p1[0]) > Math.abs(p2[1] - p1[1])){
            float t = ((float) p2[1] - p1[1])/(p2[0] - p1[0]);
            int startx = Math.min(p1[0], p2[0]);
            int starty = t < 0 ? Math.max(p1[1], p2[1]) : Math.min(p1[1], p2[1]);
            for (int i=0; i<= Math.abs(p1[0] - p2[0]); i++){
                setPixel(new int[]{i + startx, (int) (i*t) + starty}, color);
            }
        } else {
            float t = ((float) p2[0] - p1[0])/(p2[1] - p1[1]);
            int starty = Math.min(p1[1], p2[1]);
            int startx = t < 0 ? Math.max(p1[0], p2[0]) : Math.min(p1[0], p2[0]);
            for (int i=0; i<= Math.abs(p1[1] - p2[1]); i++){
                setPixel(new int[]{(int) (i*t) + startx, i + starty}, color);
            }
        }
    }

    public void test(){
        drawLine(new int[]{11, 50}, new int[]{90, 70}, 0xffffffff);

        drawLine(new int[]{100,100}, new int[]{190,100}, 0xffff00ff);
        drawLine(new int[]{100,100}, new int[]{190,190}, 0xffff00ff);
        drawLine(new int[]{100,100}, new int[]{190,10}, 0xffff00ff);
        drawLine(new int[]{100,100}, new int[]{100,190}, 0xffff00ff);
        drawLine(new int[]{100,100}, new int[]{100,10}, 0xffff00ff);

        drawLine(new int[]{100,100}, new int[]{10,10}, 0xffff00ff);
        drawLine(new int[]{100,100}, new int[]{10,100}, 0xffff00ff);
        drawLine(new int[]{100,100}, new int[]{10,190}, 0xffff00ff);
    }
}
