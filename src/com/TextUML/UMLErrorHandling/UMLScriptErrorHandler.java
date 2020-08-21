package com.TextUML.UMLErrorHandling;

import javax.swing.*;

public class UMLScriptErrorHandler {

    public static void HandleUMLScriptError(UMLScriptError exception){
        int line = 0;
        String errorString = exception.getClass().getSimpleName() + " at line " + exception.GetErrorLine() + ": " + exception.getMessage();
        JOptionPane.showMessageDialog(null, errorString, exception.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
    }
}