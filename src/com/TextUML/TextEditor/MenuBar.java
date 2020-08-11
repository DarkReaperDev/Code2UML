package com.TextUML.TextEditor;

import javax.management.JMException;
import javax.swing.*;

public class MenuBar extends JMenuBar {

    Menu file_menu;
    MenuBarActionListener actionListener;

    public MenuBar(){
        super();
        file_menu = new Menu("file");
        actionListener = new MenuBarActionListener();

        file_menu.AddMenuItem("close", actionListener);
        add(file_menu);
    }

    void InitializeComponents(){

    }
}
