package com.springboot.racemanage.util;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * 上传文件的工具类
 */
public class UploadFile {


    /**
     * 上传文件并得到文件路径的工具方法
     *
     * @param file 要上传的文件
     * @param path 需要上传的路径
     * @return 返回上传完成后的文件所在路径（包括文件名）
     * @throws IOException
     */
    public static String upload(MultipartFile file, String path) throws IOException {

        //得到文件名并拼接UUID ， 防止文件重名
        StringBuffer fileName = new StringBuffer(UUID.randomUUID().toString());

        if (file.isEmpty()) {
            return "0";
        }
        fileName.append("_" + file.getOriginalFilename());

        StringBuffer filePath = new StringBuffer(path);
        if (!new File(filePath.toString()).exists()) {
            new File(filePath.toString()).mkdirs();
        }
        filePath.append(fileName.toString());
        File f = new File(filePath.toString());
        if (!f.exists()) {
            f.createNewFile();

        }
        System.out.println(f.getAbsolutePath());
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
        out.write(file.getBytes());
        out.flush();
        out.close();

        StringBuffer filePath2 = filePath;
        System.out.println(filePath2);

        return filePath2.toString();
    }
    public static File uploadFile(MultipartFile doc, String fileName, String dir) throws IOException {
        File file = new File(dir+fileName);
        if (!new File(file.getParent()).exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        doc.transferTo(file);
        return file;
    }

}
