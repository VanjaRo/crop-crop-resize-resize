package mts.teta.resizer;

import java.io.File;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "convert")
public class ConsoleAttributes {
    @Parameters(index = "0", paramLabel = "input-file")
    File inputFile;
//    width height
    @Option(names = "--resize", arity = "2", description = "resize the image")
    Integer[] resizeValues;

    @Option(names = "--quality", arity = "1", description = "JPEG/PNG compression level")
    int quality;

    @Option(names = "--blur", arity = "1", description = "reduce image noise detail levels")
    int blur;
//    width height x y
    @Option(names = "--crop", arity = "4", description = "cut out one rectangular area of the image")
    Integer[] cropValues;

    @Option(names = "--format", arity = "1", description = "the image format type")
    String format;

    @Parameters(index = "1", paramLabel = "output-file")
    File outputFile;

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public int getBlur() {
        return blur;
    }

    public Integer getGetResizeHeight() {
        if (resizeValues != null) {
            return resizeValues[1];
        }
        return null;
    }
    public Integer getGetResizeWidth() {
        if (resizeValues != null) {
            return resizeValues[0];
        }
        return null;
    }

    public int getQuality() {
        return quality;
    }

    public String getFormat() {
        return format;
    }

    public Integer getCropWidth() {
        if (cropValues != null) {
            return cropValues[0];
        }
        return null;
    }

    public Integer getCropHeight() {
        if (cropValues != null) {
            return cropValues[1];
        }
        return null;
    }
    public Integer getCropX() {
        if (cropValues != null) {
            return cropValues[2];
        }
        return null;
    }
    public Integer getCropY() {
        if (cropValues != null) {
            return cropValues[3];
        }
        return null;
    }
    public void setCropWidth(Integer width) {
        if (cropValues == null) {
            cropValues = new Integer[4];
        }
        cropValues[0] = width;
    }
    public void setCropHeight(Integer height) {
        if (cropValues == null) {
            cropValues = new Integer[4];
        }
        cropValues[1] = height;
    }
    public void setCropX(Integer x) {
        if (cropValues == null) {
            cropValues = new Integer[4];
        }
        cropValues[2] = x;
    }
    public void setCropY(Integer y) {
        if (cropValues == null) {
            cropValues = new Integer[4];
        }
        cropValues[3] = y;
    }
    public void setFileFormat(String format){
        this.format = format;
    }
    public void setBlur(int radius){
        blur = radius;
    }
    public void setInputFile(File file) {
        inputFile = file;
    }

    public void setOutputFile(File file) {
        outputFile = file;
    }

    public void setResizeWidth(Integer width) {
        if (resizeValues == null){
            resizeValues = new Integer[2];
        }
        resizeValues[0] = width;
    }

    public void setResizeHeight(Integer height) {
        if (resizeValues == null){
            resizeValues = new Integer[2];
        }
        resizeValues[1] = height;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }
}
