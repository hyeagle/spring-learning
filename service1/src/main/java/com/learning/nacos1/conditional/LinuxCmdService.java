package com.learning.nacos1.conditional;

/**
 * @author hongyu
 */
public class LinuxCmdService implements CmdService {
    @Override
    public void print() {
        System.out.println("linux...");
    }
}
