package com.TextUML.TextEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class MenuBarDropdown extends JMenu {

    List<JMenuItem> menu_items;

    public MenuBarDropdown(String menu_name){
        super(menu_name);
        menu_items = new ArrayList<JMenuItem>();
    }

    public void AddMenuItem(String name, Runnable action){
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        });
        menu_items.add(item);
        add(item);
    }
}
