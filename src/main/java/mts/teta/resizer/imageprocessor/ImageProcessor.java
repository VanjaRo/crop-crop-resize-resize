package mts.teta.resizer.imageprocessor;

import marvin.image.MarvinImage;

import mts.teta.resizer.ConsoleAttributes;
import net.coobird.thumbnailator.Thumbnails;
import org.marvinproject.image.blur.gaussianBlur.GaussianBlur;
import org.marvinproject.image.segmentation.crop.Crop;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class ImageProcessor {
    BufferedImage stored_img;
    String fileFormat;

    public void processImage(BufferedImage img, ConsoleAttributes attr) throws IOException, BadAttributesException{
        stored_img = img;
        if (attr.getGetResizeHeight() != null && attr.getGetResizeWidth() != null){
            stored_img = changeSize(attr.getGetResizeWidth(), attr.getGetResizeHeight());
        }
        if (attr.getQuality() != 0){
            stored_img = changeQuality(attr.getQuality());
        }
        if (attr.getCropHeight() != null && attr.getCropWidth() != null && attr.getCropX() != null && attr.getCropY() != null){
            stored_img = changeCrop(attr.getCropWidth(), attr.getCropHeight(), attr.getCropX(), attr.getCropY());
        }
        if (attr.getBlur() != 0) {
            stored_img = changeBlur(attr.getBlur());
        }
        if (attr.getFormat() != null){
            stored_img = changeFormat(attr.getFormat());
        }
        Thumbnails.of(stored_img)
                .size(stored_img.getWidth(), stored_img.getHeight())
                .toFile(attr.getOutputFile());
    }
    private BufferedImage changeBlur(int radius) throws IOException, BadAttributesException{
        if (radius < 0){
            throw new BadAttributesException("Please check params!");
        }
        MarvinImage marvinImageIn = new MarvinImage(stored_img);
        MarvinImage marvinImageOut = new MarvinImage(stored_img.getWidth(), stored_img.getHeight());
        GaussianBlur gaussianBlur = new GaussianBlur();
        gaussianBlur.load();
        gaussianBlur.setAttribute("radius", radius);
        gaussianBlur.process(marvinImageIn, marvinImageOut);
        return marvinImageOut.getBufferedImageNoAlpha();
    }
//    thubnailarot
    private BufferedImage changeQuality(int resolution) throws IOException, BadAttributesException{
        if (resolution < 0){
            throw new BadAttributesException("Please check params!");
        }
        BufferedImage res = Thumbnails.of(stored_img)
                .size(stored_img.getWidth(), stored_img.getHeight())
                .outputQuality(resolution / 100)
                .asBufferedImage();
        return res;
    }
//    thubnailarot
    private BufferedImage changeFormat(String format) throws IOException, BadAttributesException{
        if (format != "jpeg" || format != "png") {
            throw new BadAttributesException("Please check params!");
        }
        fileFormat = format;
        BufferedImage res = Thumbnails.of(stored_img)
                .size(stored_img.getWidth(), stored_img.getHeight())
                .outputFormat(format)
                .asBufferedImage();
        return res;
    }
//    thubnailarot
    private BufferedImage changeSize(int width, int height) throws IOException, BadAttributesException{
        if (width <= 0 || height <= 0){
            throw new BadAttributesException("Please check params!");
        }

        BufferedImage res = Thumbnails.of(stored_img)
                .size(width, height)
                .keepAspectRatio(false)
                .asBufferedImage();
        return res;
    }
    private BufferedImage changeCrop(int width, int height, int x, int y) throws IOException, BadAttributesException{
        if (x > stored_img.getWidth() || y > stored_img.getHeight() || x + width > stored_img.getWidth() || y + height > stored_img.getHeight()){
            throw new BadAttributesException("Please check params!");
        }
        MarvinImage marvinImageIn = new MarvinImage(stored_img);
        MarvinImage marvinImageOut = new MarvinImage(stored_img.getWidth(), stored_img.getHeight());
        Crop crop = new Crop();
        crop.load();
        crop.setAttribute("x", x);
        crop.setAttribute("y", y);
        crop.setAttribute("height", height);
        crop.setAttribute("width", width);
        crop.process(marvinImageIn, marvinImageOut);

        return marvinImageOut.getBufferedImage();
    }


}
