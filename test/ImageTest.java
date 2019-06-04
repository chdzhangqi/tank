package test;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * ImageTest.class.getClassLoader.getResourceAsStream()---->获取src classpath目录下的文件。
 * import org.junit.Test;
 * import static org.junit.Assert.*;
 * 定义方法使用junit单元测试.
 */
public class ImageTest {
    @Test
    public void loadImage() {
        try {
            BufferedImage image = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/1.gif"));
            assertNotNull(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
