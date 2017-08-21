package com.example.ht.kotlinproject.notify.util;

/**
 * Created By HanTe
 */

public class NotifyUtil {

//    private NotificationCompat.Builder mNotificationBuilder;
//    private NotificationManagerCompat mNotificationManager;
// 静态内部类实现单利模式
    public static final NotifyUtil getInstance(){
        return SingleOnHolder.INSTANCE;
    }
    public static class SingleOnHolder{
        public static final NotifyUtil INSTANCE = new NotifyUtil();
    }
}
