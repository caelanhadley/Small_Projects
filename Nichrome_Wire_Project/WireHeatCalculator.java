package Nichrome_Wire_Project;

import java.util.Scanner;

public class WireHeatCalculator {
    static final double T_ZERO = 0.0;
    static final double TWENTYEIGHT_RPF = 4.21; // Ristance per Foot of Nicrome Wire @ 68F

    double dt = 0.005; // Seconds
    double wireTemp = 0.0; // C

    public static void main(String args[]) {

    }

    public <T> void print(T printIn) {
        System.out.print(printIn);
    }

    public <T> void println(T printIn) {
        System.out.println(printIn);
    }
}
