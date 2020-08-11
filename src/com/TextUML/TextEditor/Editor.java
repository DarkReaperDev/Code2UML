package com.TextUML.TextEditor;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.lang.management.MemoryNotificationInfo;

public class Editor {
    JFrame main_frame;
    JTextArea main_text_area;
    MenuBar menuBar;

    public Editor(){
        InitializeComponents();
    }

    public Editor(String title){
        InitializeComponents();
        SetTitle(title);
    }

    public Editor(String title, int width, int height){
        InitializeComponents();
        SetTitle(title);
        SetSize(width, height);
    }

    void InitializeComponents(){
        main_frame = new JFrame();
        main_text_area = new JTextArea();
        menuBar = new MenuBar();
    }

    public void Launch(){
        main_frame.setJMenuBar(menuBar);
        main_frame.add(main_text_area);
        main_frame.show();
    }

    public void Close(){

    }

    public void SetSize(int width, int height){
        main_frame.setSize(width, height);
    }

    public void SetTitle(String title){
        main_frame.setTitle(title);
    }
}
