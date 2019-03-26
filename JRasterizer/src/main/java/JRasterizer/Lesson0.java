package JRasterizer;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Lesson0 {
    BufferedImage bufferedImage;
    int[] data;
    int size;

    public Lesson0(int size) {
        this.size = size;
        bufferedImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        data = new int[size * size];
        Arrays.fill(data, 0xff000000);
    }

    public void test()  {
        setPixel(new int[]{1, 7}, 0xffff0000);
        setPixel(new int[]{3, 9}, 0xff00ffff);
        setPixel(new int[]{1, 1}, 0xff00ffff);
        setPixel(new int[]{9, 6}, 0xff00ffff);
        setPixel(new int[]{1, 2}, 0xffff0000);
        setPixel(new int[]{7, 7}, 0xff00ff00);
    }

    public void setPixel(int[] point, int color){
        if (0 <= point[0] && point[0] < size && 0 <= point[1] && point[1] < size) {
            data[point[0] + size * (size - point[1] - 1)] = color;
        }
    }

    public void flushImage(){
        bufferedImage.setRGB(0, 0, size, size, data, 0, size);
    }

    public BufferedImage getBufferedImage(){
        return bufferedImage;
    }
}

