package vn.com.fsoft.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import vn.com.fsoft.common.Constants;
import vn.com.fsoft.model.FileConverted;
import vn.com.fsoft.service.FileManagementService;
import java.util.Arrays;
import java.io.FilenameFilter;

@Service
public class FileManagementServiceImpl implements FileManagementService {

    @Value("${storage.uploadPath}")
    private String uploadPath;

    @Override
    public List<FileConverted> getFileConverted(String type) {
        List<FileConverted> result = new ArrayList<>();
        FileConverted fileConverted;
        String tomcatBase = System.getProperty("catalina.base");
        String webApp = tomcatBase + uploadPath + type;
        System.out.println("1--------------" +webApp);

        File folder = new File( webApp);
        String[] directories = folder.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
              return new File(current, name).isDirectory();
            }
          });
        System.out.println("----------------------------------------------------------");
        System.out.println(Arrays.toString(directories));
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                fileConverted = new FileConverted();
                fileConverted.setFileId(String.valueOf("FILE" + (i + 1)));
                fileConverted.setFileName(listOfFiles[i].getName());
                fileConverted.setFilePath("/"+type+"/"+listOfFiles[i].getName());
                result.add(fileConverted);
            }
        }

        return result;
    }

    @Override
    public void deleteFile(String filePath) {
        String tomcatBase = System.getProperty("catalina.base");
        String webApp = org.springframework.util.StringUtils.cleanPath(tomcatBase + uploadPath + filePath);
        System.out.println("webApp: " + webApp);
        File file = new File(webApp);
        if (file.exists()) {
            System.out.println("DELETE FILE: " + file.delete());
        } else {
            System.out.println("NOT EXIST FILE");
        }
    }
}
