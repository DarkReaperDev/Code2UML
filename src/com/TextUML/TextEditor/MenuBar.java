package com.TextUML.TextEditor;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    MenuDropdown file_menu;
    MenuBarActionListener actionListener;

    public MenuBar(){
        super();
        file_menu = new MenuDropdown("file");
        actionListener = new MenuBarActionListener();

        file_menu.AddMenuItem("close", actionListener);
        add(file_menu);
    }

    void InitializeComponents(){

    }
}
