package com.righit.synthesizer;

import java.util.HashMap;


public class AudioTracker {

    HashMap<Integer, SoundOutput> threadList;

    AudioTracker() {
        threadList = new HashMap<>();
    }

    void addThread(int hash, SoundOutput thread) {
        threadList.put(hash, thread);
    }

    HashMap<Integer, SoundOutput> getThreadMap() {
        return threadList;
    }

    void removeThread(int hash){
        threadList.remove(hash);
    }



}
