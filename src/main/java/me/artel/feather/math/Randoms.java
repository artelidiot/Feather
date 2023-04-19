package me.artel.feather.math;

import lombok.Getter;

import java.util.Random;
import java.util.UUID;

public class Randoms {
    @Getter
    public static final Random random = new Random();

    public static int coinFlip() {
        return 0; // TODO
    }

    public static int randomNumber(int minimumRange, int maximumRange) {
        return 0; // TODO
    }

    public static UUID randomUUID() {
        return UUID.randomUUID();
    }
}