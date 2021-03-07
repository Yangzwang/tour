package com.ccnu.tour.controller;

import com.alibaba.fastjson.JSONObject;
import com.ccnu.tour.util.CommonUtil;
import com.ccnu.tour.util.ErrorEnum;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yang
 * @CreateTime: 2021-03-07 21:14
 * @Description: 图片上传
 */
@RestController
@RequestMapping("/api/pb/upload/")
public class UploadController {

    @RequestMapping(value = "/img", method = RequestMethod.POST, consumes = {"multipart/form-data; charset=utf-8"})
    @ResponseBody
    public JSONObject imgUpdated(@RequestParam(value = "file") MultipartFile[] files, HttpServletRequest request) {
        if (files == null) {
            return CommonUtil.errorJson(ErrorEnum.E_90003);
        }
        List<String> strings = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                String path = "/usr/local/tomcat/apache-tomcat-7.0.54/webapps/";
                String savePath = "D://Xiaomi/";
                String filename = "img/nian" + System.currentTimeMillis() + ".png";
                File saveFile = new File(savePath + filename);
                //判断文件父目录是否存在
                if (!saveFile.getParentFile().exists()) {
                    saveFile.getParentFile().mkdirs();
                }
                file.transferTo(saveFile);
                strings.add(filename);

            } catch (Exception e) {
                return CommonUtil.errorJson(ErrorEnum.E_600);
            }
        }
        return CommonUtil.successJson(strings);

    }

}
