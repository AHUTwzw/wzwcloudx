package com.wzw.springcloud.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author wuzhiwei
 * @create 2020-12-15 0:12
 */
@Slf4j
public class ObjectSerializerUtils {
    /**
     * 反序列化
     *
     * @param data
     * @return
     */
    public static Object deSerilizer(byte[] data) {
        if (data != null && data.length > 0) {
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(data);
                ObjectInputStream ois = new ObjectInputStream(bis);
                return ois.readObject();
            } catch (Exception e) {
                log.info("[异常信息] {}", e.getMessage());
                e.printStackTrace();
            }
            return null;
        } else {
            log.info("[反序列化] 入参为空");
            return null;
        }
    }

    /**
     * 序列化对象
     *
     * @param obj
     * @return
     */
    public static byte[] serilizer(Object obj) {
        if (obj != null) {
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(obj);
                oos.flush();
                oos.close();
                return bos.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
    }
}

