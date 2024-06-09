package com.example.adventures;

import com.example.adventures.cli.CLILoginGraphicController;

public class CLIMain {
    public static void main(String[] args){
        CLILoginGraphicController applicationController = new CLILoginGraphicController();
        applicationController.start();
    }
}
