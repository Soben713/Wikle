package edu.sharif.ce.fall92.mir.pa3.wikle.utils;

public class Print {
	public static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
}
