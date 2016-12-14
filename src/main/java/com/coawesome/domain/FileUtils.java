package com.coawesome.domain;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;


@Component("fileUtils")
public class FileUtils {
    private static final String filePath = "/home/centos/Dr_R_Client/app/storedimg/";
//private static final String filePath = "/storedimg";
    public static String getRandomString(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public boolean deleteFile(String fileName){
        //check
        File file = new File(filePath+fileName);
        return (file.delete());
    }

    public ImageVO parseInsertFileInfo(MultipartFile multipartFile, DiaryVO diaryVO) throws Exception{
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;

        File file = new File(filePath);
        if(file.exists() == false){
            file.mkdirs();
        }
        ImageVO image = new ImageVO();
        if(multipartFile.isEmpty() == false){
            originalFileName = multipartFile.getOriginalFilename();
            originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            storedFileName = getRandomString() + originalFileExtension;

            file = new File(filePath + storedFileName);
            multipartFile.transferTo(file);

            image.setOriginal_file_name(originalFileName);
            image.setStored_file_name(storedFileName);
            image.setFile_size(multipartFile.getSize());
            image.setList_id(diaryVO.getList_id());
            image.setU_id(diaryVO.getU_id());
            System.out.println(image);
        }
        return image;
   }


    public ImageVO parseInsertFileInfoForUser(MultipartFile multipartFile, UserVO user) throws Exception{

        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;

        File file = new File(filePath);
        if(file.exists() == false){
            file.mkdirs();
        }

        ImageVO image = new ImageVO();
        if(multipartFile.isEmpty() == false){
            originalFileName = multipartFile.getOriginalFilename();
            originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            storedFileName = getRandomString() + originalFileExtension;

            file = new File(filePath + storedFileName);
            multipartFile.transferTo(file);

            image.setUser_pic(storedFileName);  //저장된 이미지 이름
            image.setU_id(user.getU_id());
            System.out.println(image);
        }
        return image;
    }


    public List<Map<String, Object>> parseUpdateFileInfo(Map<String, Object> map, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String, Object> listMap = null;

        String boardIdx = (String)map.get("IDX");
        String requestName = null;
        String idx = null;


        while(iterator.hasNext()){
            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            if(multipartFile.isEmpty() == false){
                originalFileName = multipartFile.getOriginalFilename();
                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                storedFileName = getRandomString() + originalFileExtension;

                multipartFile.transferTo(new File(filePath + storedFileName));

                listMap = new HashMap<String,Object>();
//                listMap.put("IS_NEW", "Y");
                listMap.put("board_id", (String)map.get("board_id"));
                listMap.put("original_file_name", originalFileName);
                listMap.put("stored_file_name", storedFileName);
                listMap.put("file_size", multipartFile.getSize());
                list.add(listMap);
            }
            else{
                requestName = multipartFile.getName();
                idx = "IDX_"+requestName.substring(requestName.indexOf("_")+1);
                if(map.containsKey(idx) == true && map.get(idx) != null){
                    listMap = new HashMap<String,Object>();
                    listMap.put("IS_NEW", "N");
                    listMap.put("FILE_IDX", map.get(idx));
                    list.add(listMap);
                }
            }
        }
        return list;
    }
}
