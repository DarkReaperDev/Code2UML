package com.TextUML.TextEditor;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class MenuDropdown extends JMenu {

    List<JMenuItem> menu_items;

    public MenuDropdown(String menu_name){
        super(menu_name);
        menu_items = new ArrayList<JMenuItem>();
    }

    public void AddMenuItem(String name, ActionListener listener){
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(listener);
        menu_items.add(item);
        add(item);
    }
}
