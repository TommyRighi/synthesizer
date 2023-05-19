package com.righit.synthesizer;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;


public class AudioTracker {

    ConcurrentHashMap<Integer, SoundOutput> threadList;

    AudioTracker() {
        threadList = new ConcurrentHashMap<>();
    }

    void addThread(int hash, SoundOutput thread) {
        threadList.put(hash, thread);
    }

    ConcurrentHashMap<Integer, SoundOutput> getThreadMap() {
        return threadList;
    }

    void removeThread(int hash){
        threadList.remove(hash);
    }



}
