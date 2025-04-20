package com.pium;

import com.pium.riot.service.BotLauncherService;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new BotLauncherService().start();
    }
}