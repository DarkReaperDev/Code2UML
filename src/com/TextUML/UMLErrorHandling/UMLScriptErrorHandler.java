package com.TextUML.UMLErrorHandling;

import javax.swing.*;

public class UMLScriptErrorHandler {

    public static void HandleUMLScriptError(Exception exception){
        int line = 0;
        String errorString = exception.getClass().getSimpleName() + " at line " + line + ": " + exception.getMessage();
        JOptionPane.showMessageDialog(null, errorString, exception.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
    }

}
