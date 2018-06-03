package de.htwg.margogo.monstermaths;

import org.junit.Test;

import static de.htwg.margogo.monstermaths.MiscUtilities.distance;
import static org.junit.Assert.assertEquals;


public class MiscUnitTest {

    @Test
    public void distance_isCorrectA() {

        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;

        assertEquals(distance(x1,y1,x2,y2), 0, 0);
    }

    @Test
    public void distance_isCorrectB() {

        int x1 = 0;
        int y1 = 0;
        int x2 = 1;
        int y2 = 1;

        assertEquals(distance(x1,y1,x2,y2), 1.412, 0.01);
    }

    @Test
    public void distance_isCorrectC() {

        int x1 = 1;
        int y1 = 2;
        int x2 = 3;
        int y2 = 4;

        assertEquals(distance(x1,y1,x2,y2), 2.828, 0.01);
    }


    @Test
    public void distance_isCorrectD() {

        int x1 = 6;
        int y1 = 3;
        int x2 = 1;
        int y2 = 2;

        assertEquals(distance(x1,y1,x2,y2), 5.099, 0.01);
    }

    @Test
    public void distance_isCorrectE() {

        float x1 = 0.001f;
        float y1 = 0.03f;
        float x2 = -0.021f;
        float y2 = -0.22f;

        assertEquals(distance(x1,y1,x2,y2), 0.25096, 0.0001);
    }


}