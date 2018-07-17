package com.jeff.gois.core.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {

    /**
     * 单文件上传
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    public String handleFileUpload(@RequestParam("file")MultipartFile file){
        if(!file.isEmpty()){
            try {
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                outputStream.write(file.getBytes());
                outputStream.flush();
                outputStream.close();
            }catch (Exception e){
                e.printStackTrace();
                return"上传失败,"+e.getMessage();
            }
            return"上传成功";
        }else {
            return"上传失败，因为文件是空的.";
        }
    }

    /**
     * 多文件上传
     * @param request
     * @return
     */
    @RequestMapping(value="/batch/upload", method= RequestMethod.POST)
    public @ResponseBody String handleFileUpload(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        MultipartFile multipartFile = null;
        BufferedOutputStream stream = null;
        for (int i=0; i<files.size(); i++) {
            multipartFile = files.get(i);
            if (!multipartFile.isEmpty()){
                try{
                    byte[] bytes = multipartFile.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(new File(multipartFile.getOriginalFilename())));
                    stream.write(bytes);
                    stream.flush();
                    stream.close();
                }catch (Exception e){
                    e.printStackTrace();
                    stream =  null;
                    return"You failed to upload " + i + " => " + e.getMessage();
                }
            }else {
                return"You failed to upload " + i + " because the file was empty.";
            }
        }
        return"upload successful";
    }
}
