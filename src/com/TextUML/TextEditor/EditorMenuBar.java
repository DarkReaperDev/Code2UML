package com.TextUML.TextEditor;

import javax.swing.*;

 class EditorMenuBar extends JMenuBar {

    MenuBarDropdown fileMenuDropdown;

    public EditorMenuBar(EditorActionsController actionsController){
        super();

        fileMenuDropdown = new MenuBarDropdown("file");

        fileMenuDropdown.AddMenuItem("close", ()-> actionsController.CloseEditor());
        fileMenuDropdown.AddMenuItem("Run", ()-> actionsController.CreateDiagram());
        add(fileMenuDropdown);
    }

    void InitializeComponents(){

    }
}
