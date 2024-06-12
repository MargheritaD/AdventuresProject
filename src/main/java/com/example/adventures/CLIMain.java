package com.example.adventures;

import com.example.adventures.cli.CLILogin;

public class CLIMain {
    public static void main(String[] args){
        CLILogin applicationController = new CLILogin();
        applicationController.start();
    }
}
