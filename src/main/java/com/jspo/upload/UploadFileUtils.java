package com.jspo.upload;

import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

public class UploadFileUtils {
    public static String fileUpload(String uploadPath, String fileName,
                                    byte[] fileData, String ymdPath) throws Exception {

        UUID uid = UUID.randomUUID();

        String newFileName = uid + "_" + fileName;
        String imgPath = uploadPath + ymdPath;
        System.out.println("newFileName = "+ newFileName);
        System.out.println("imgPath = " + imgPath);

        File target = new File(imgPath, newFileName);
        System.out.println("target = "+target);
        System.out.println("fileData = " + fileData);
        FileCopyUtils.copy(fileData, target);

        return newFileName;
    }

    public static String calcPath(String uploadPath) {
        Calendar cal = Calendar.getInstance();
        String yearPath = File.separator+ cal.get(Calendar.YEAR);
        String monthPath = yearPath + File.separator+ new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
        String datePath = monthPath + File.separator+ new DecimalFormat("00").format(cal.get(Calendar.DATE));

        makeDir(uploadPath, yearPath, monthPath, datePath);
        System.out.println(uploadPath);
        System.out.println(yearPath);
        System.out.println(monthPath);
        System.out.println(datePath);

        return datePath;
    }

    private static void makeDir(String uploadPath, String... paths) {

        if (new File(paths[paths.length - 1]).exists()) { return; }

        for (String path : paths) {
            File dirPath = new File(uploadPath + path);

            if (!dirPath.exists()) {
                dirPath.mkdir();
            }
        }
    }
}
