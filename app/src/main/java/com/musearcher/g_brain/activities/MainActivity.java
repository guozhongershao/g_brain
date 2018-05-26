package com.musearcher.g_brain.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.musearcher.g_brain.R;
import com.musearcher.g_brain.beans.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化bombSDK
        Bmob.initialize(this, "240ca09b90236d283c811992541cfa41");

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            byte[] bytes = new byte[1024];
            try {
                bytes = "我是客户端".getBytes("utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            try {
                // 发送udp数据包
                InetAddress address = InetAddress.getByName("192.168.31.186");
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, address,23456);
                DatagramSocket datagramSocket = new DatagramSocket();
                datagramSocket.send(datagramPacket);

                // 接受数据
                byte[] bytes1 = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(bytes1, bytes1.length);
                datagramSocket.receive(receivePacket);
                System.out.println("服务器：" + new String(bytes1,"utf-8"));
                datagramSocket.close();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (SocketException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (id == R.id.nav_share) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Socket socket = new Socket("192.168.31.186",11233);
                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write("我是客户端".getBytes());
                        outputStream.flush();
                        outputStream.close();
                        socket.shutdownOutput();
                        InputStream inputStream = socket.getInputStream();
                        System.out.println("获取服务端输入流");
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String inputLine = null;
                        StringBuffer buffer = new StringBuffer();
                        while ((inputLine = bufferedReader.readLine()) != null){
                            buffer.append(inputLine);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("获取服务端输入流");
                            }
                        });
                        System.out.println(buffer.toString());
                        bufferedReader.close();
                        inputStreamReader.close();
                        inputStream.close();
                        socket.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } else if (id == R.id.nav_about) {
//            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
//            startActivity(intent);
//            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            Person p2 = new Person();
            p2.setName("lucky");
            p2.setAddress("北京海淀");
            p2.save(new SaveListener<String>() {
                @Override
                public void done(String objectId,BmobException e) {
                    if(e==null){
                        Log.d("MainActivity","添加数据成功，返回objectId为："+objectId);
                    }else{
                        Log.d("MainActivity", "创建数据失败：" + e.getMessage());
                    }
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
