package com.TextUML.TextEditor;

import javax.swing.*;

 class EditorMenuBar extends JMenuBar {

    MenuBarDropdown fileMenuDropdown;
    MenuBarActionListener actionListener;

    public EditorMenuBar(){
        super();
        fileMenuDropdown = new MenuBarDropdown("file");
        actionListener = new MenuBarActionListener();

        fileMenuDropdown.AddMenuItem("close", actionListener);
        add(fileMenuDropdown);
    }

    void InitializeComponents(){

    }
}
