package com.pium;

import com.pium.riot.botservice.BotLauncherService;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new BotLauncherService().start();
    }
}