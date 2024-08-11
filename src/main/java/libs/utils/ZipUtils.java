package libs.utils;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;

import java.io.File;

public class ZipUtils {

    private ZipUtils(){}

    public static void zip(String targetPath, String path) {
        try (ZipFile zipFile = new ZipFile(new File(path))) {

            ZipParameters zipParameters = new ZipParameters();
            zipParameters.setCompressionMethod(CompressionMethod.DEFLATE);
            zipParameters.setCompressionLevel(CompressionLevel.NORMAL);

            File targetFile = new File(targetPath);

            if (targetFile.isFile())
                zipFile.addFile(targetFile, zipParameters);
            else if (targetFile.isDirectory())
                zipFile.addFolder(targetFile, zipParameters);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
