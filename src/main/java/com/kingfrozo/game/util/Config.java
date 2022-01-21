package com.kingfrozo.game.util;

public class Config {

    // Define arena size here:
    // Must be rectangular and of odd length and width

    public static int arenaX = 339; // width
    public static int arenaY = 142; // height
    public static int arenaZ = -246; // length
    public static int arenaWidth = 33;
    public static int arenaLength = 25;

    public static int x_offset = ((int) ((Config.arenaWidth - 1) / 2));
    public static int z_offset = ((int) ((Config.arenaLength - 1) / 2));

    public static int x_top = arenaX + x_offset;
    public static int x_bottom = arenaX - x_offset;
    public static int z_top = arenaZ + z_offset;
    public static int z_bottom = arenaZ - z_offset;

    public static double respawnX = 339.5;
    public static double respawnY = 187.0;
    public static double respawnZ = -245.5;
    public static String respawnDir = "west";

}
