package com.example.richard.lock_1;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.view.View;
import android.widget.*;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {

    private DevicePolicyManager policyManager;
    private ComponentName componentName;
    public static final int MY_REQUEST_CODE = 10086;
    //private TextView tv_time;
    //private static final int msgKey1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        policyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName("com.example.richard.lock_1","com.example.richard.lock_1.AdminReceiver");

        //判断是否有锁屏权限，若有则立即锁屏并结束自己，若没有则获取权限
        if (policyManager.isAdminActive(componentName)) {
            policyManager.lockNow(); //lock
            finish();
        } else {
            activeManage();
//            finish();
        }

        setContentView(R.layout.layout_simple);

//        tv_time = (TextView) findViewById(R.id.now_time);
//        new TimeThread().start();
    }


    // this function is not needed now
//    public class TimeThread extends  Thread{
//        @Override
//        public void run() {
//            super.run();
//            do{
//                try {
//                    Message msg = new Message();
//                    msg.what = msgKey1;
//                    mHandler.sendMessage(msg);
//                    Thread.sleep(1000);
//
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }while (true);
//        }
//    }
//    private Handler mHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){
//                case msgKey1:
//                    long time = System.currentTimeMillis();
//                    Date date = new Date(time);
//                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
//                    tv_time.setText(format.format(date));
//                    break;
//                default:
//                    break;
//            }
//        }
//    };

    //get access to lockscreen
    private void activeManage() {
        //启动设备管理 - 在AndroidManifest.xml中设定相应过滤器
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        //权限列表
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
        //描述
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "激活后才能使用锁屏功能哦");

        startActivityForResult(intent, MY_REQUEST_CODE);
    }

//    public void lock(View view) {
//        policyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
//        componentName = new ComponentName("com.example.richard.lock_1","com.example.richard.lock_1.AdminReceiver");
//
//        //判断是否有锁屏权限，若有则立即锁屏并结束自己，若没有则获取权限
//        if (policyManager.isAdminActive(componentName)) {
//            policyManager.lockNow(); //lock
//            finish();
//        } else {
//            activeManage();
////            finish();
//        }
//    }



}
