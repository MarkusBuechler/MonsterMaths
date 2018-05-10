package de.htwg.margogo.monstermaths;

public class MiscUtilities {

    /**
     * Returns the difference between two points
     * @param xd_1 x value of first point
     * @param yd_1 y value of first point
     * @param xd_2 x value of second point
     * @param yd_2 y value of second point
     * @return
     */
    public static double distance(float xd_1, float yd_1, float xd_2, float yd_2) {

        xd_1 -= xd_2;
        yd_1 -= yd_2;

        return Math.sqrt(xd_1 * xd_1 + yd_1 * yd_1);
    }
}
