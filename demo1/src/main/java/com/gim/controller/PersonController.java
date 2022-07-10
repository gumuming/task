package com.gim.controller;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

/**
 * @Author: xiang_chat
 * @Date: 2019/5/21 10:27
 */
public class PersonController {
    public static void main(String[] args) throws IOException {
        String absolutePath = new File("").getAbsolutePath();
        System.out.println(absolutePath);

        Thumbnails.of(new File(absolutePath+"\\img\\test.jpg"))
                .scale(0.3d)
                .toFile(absolutePath + "\\img\\climb-up.size.400X300.jpeg");

    }
}
