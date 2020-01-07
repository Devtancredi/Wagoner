package com.example.wagoner;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.widget.Toast;

import java.sql.Blob;
import java.util.Arrays;


public class Wheel {

    private HighlightBox[] highlightBoxes = new HighlightBox[10];
    private Bitmap image;
    private String id;


    //Test wheel
    public Wheel() {
        image = BitmapFactory.decodeFile("@drawable/test.png");
        Arrays.fill(highlightBoxes, null); //init highlight box array to all null
        int idNum = (int)(Math.random()*((99999-1)+1))+ 1;
        id = Integer.toString(idNum);
    }

    //Test wheel 2
    public Wheel(Bitmap image) {
        this.id = "TEST";
        Arrays.fill(highlightBoxes, null);
        this.image = image;
    }

    //Wheel constructor used in app
    public Wheel(Bitmap image, String id){
        this.image = image;
        this.id = id;
        Arrays.fill(highlightBoxes, null); //init highlight box array to all null
    }

    private void checkHighlightBoxScores() {
        for (int x = 0; x < highlightBoxes.length; x++) {
            if (highlightBoxes[x].getScore() <= -5) {
                deleteHighlightBox(x);
            }
        }
    }

    public void addHighlightBox() {
        boolean vacancyLeft = false;
        int vacancyPos = -1;
        for (int x = 0; x < highlightBoxes.length; x++) {
            if (highlightBoxes[x] == null && !vacancyLeft) {
                vacancyLeft = true;
                vacancyPos = x;
            }
        }

        if (vacancyLeft) {
            highlightBoxes[vacancyPos] = new HighlightBox(/*xPos,yPos,xWitch,yHeight*/);
        }
    }

    private void deleteHighlightBox(int boxPos) {
            highlightBoxes[boxPos] = null;
    }

    public Bitmap getBitPicture() {
        return image;
    }
}
