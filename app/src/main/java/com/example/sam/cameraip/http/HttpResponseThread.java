package com.example.sam.cameraip.http;

import android.util.Base64;
import android.util.Log;


import com.example.sam.cameraip.camera.CameraConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Created by Thuans on 4/19/2017.
 */

public class HttpResponseThread extends Thread {
    private final String TAG = "HttpResponseThread";
    Socket socket;

    HttpResponseThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader is;
        PrintWriter os;
        String request = "";


        try {
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            request = is.readLine();
            Log.d(TAG,request+"");
            //kiem tra request url
            String response = "";
            os = new PrintWriter(socket.getOutputStream(), true);
            if (request != null ) {
                if (request.contains("/camera")) {
                    CameraConfig camera = CameraConfig.getInstance();

                    if (camera.getFaceImage() != null) {
                        byte[] picture = camera.getFaceImage();
                        String encoded = Base64.encodeToString(picture, Base64.NO_WRAP);
                        response = encoded;
                        camera.setFaceImage(null);
                    }
                    else {
                        response = "Nobody";
                    }
                } else if (request.contains("/get")) {
                    response = "";
                } else if (request.contains("/check")) {
                    response = "";
                }
            }
            os.print("HTTP/1.0 200" + "\r\n");
            os.print("Content type: text/html" + "\r\n");
            os.print("Content length: " + response.length() + "\r\n");
            os.print("\r\n");
            os.print(response + "\r\n");
            os.flush();
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return;
    }
}
