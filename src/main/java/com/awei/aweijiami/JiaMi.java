package com.awei.aweijiami;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JiaMi {
    private static final String key = "huwei12345678911";
    public final String filePath = "F:\\jiemi.txt";
    public final String outPutPath = "F:\\jiami.txt";
    public AES aes = SecureUtil.aes(key.getBytes(StandardCharsets.UTF_8));


    public void jiaMi() {
        File file = new File(filePath);
        File wFile = new File(outPutPath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (BufferedReader reader = new BufferedReader((new FileReader(file)));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outPutPath));
        ) {
            String readLine;
            while ((readLine = reader.readLine()) != null) {
                String encrypt = aes.encryptHex(readLine);
                writer.write(encrypt+"\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void jieMi() {
        File file = new File(filePath);
        File wFile = new File(outPutPath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (BufferedReader reader = new BufferedReader((new FileReader(file)));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outPutPath));
        ) {
            String readLine;
            while ((readLine = reader.readLine()) != null) {
                String encrypt = aes.decryptStr(readLine);
                writer.write(encrypt+"\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JiaMi jiami = new JiaMi();
        jiami.jieMi();
    }
}
