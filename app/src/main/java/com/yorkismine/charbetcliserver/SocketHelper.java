package com.yorkismine.charbetcliserver;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Proxy;
import java.net.Socket;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SocketHelper {
    private static Socket socket;
    public static void connect() {

        Observable.just(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> {
                    socket = new Socket("178.121.28.3", 7777);
                }, t -> t.printStackTrace());

    }

    public static void write(String msg) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write(msg);
        bw.newLine();
        bw.flush();
    }

    public static Observable<String> read() throws IOException {
        if (socket == null) Log.d("f", "fuck");
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        return Observable.just(reader.readLine())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
