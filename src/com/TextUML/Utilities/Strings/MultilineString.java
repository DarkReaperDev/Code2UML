package com.TextUML.Utilities.Strings;

import java.awt.*;

public class MultilineString {
    private String[] strings;
    private int current = 0;

    public MultilineString(String[] strings){
        this.strings = strings;
    }

    public int GetSize(){
        return strings.length;
    }

    public String GetNext(){
        String string = strings[current];
        current ++;
        return string;
    }

    public void GoToStart(){
        current = 0;
    }

    public String[] GetAsArray(){
        return strings;
    }

    public int GetLongestStringSize(FontMetrics metrics){
        int longestWidth = 0;
        for(String s : strings){
            int width = metrics.stringWidth(s);
            if(width > longestWidth){
                longestWidth = width;
            }
        }
        return longestWidth;
    }
}
