package org.example.techmateaccessories.service;

import jakarta.servlet.ServletContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class UploadService {
    private final ServletContext servletContext;
    public UploadService(ServletContext servletContext){
       this.servletContext = servletContext ;
    }
     public String handleUploadFile(MultipartFile file , String pathInAssets){
         if(file.isEmpty()){
              return "" ;
         }
         String rs = "" ;
         try{
             byte[] bytes = file.getBytes();
             String rootPath = this.servletContext.getRealPath("/assets/images");

             File dir = new File(rootPath + File.separator +  pathInAssets );
             if (!dir.exists())
                 dir.mkdirs();

             // Tạo tên file duy nhất
             String savedFileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();

             // Tạo file trên server
             File serverFile = new File(dir.getAbsolutePath() + File.separator + savedFileName);

             // Ghi dữ liệu ảnh vào file
             BufferedOutputStream stream = new BufferedOutputStream(
                     new FileOutputStream(serverFile));
             stream.write(bytes);
             stream.close();
             rs = savedFileName  ;

         }
         catch(IOException e ){
             e.printStackTrace();
         }
         return rs ;
     }
}
