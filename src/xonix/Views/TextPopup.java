package xonix.Views;

import xonix.Application;

import javax.swing.*;
import java.awt.*;

/**
 * Created on 11/29/2016.
 * Credit aan Jorn
 */
public class TextPopup {

    /**
     * Create a popup on the screen.
     *
     * @param text The text to show on the popup.
     */
    public TextPopup(String title, String text) {
        JDialog dialog = new JDialog(Application.controller.view);
        dialog.setLayout(new BorderLayout());
        dialog.setTitle(title);
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setBackground(new Color(230, 230, 230));
        textArea.setMargin(new Insets(10, 10, 10, 10));
        dialog.add(textArea);
        dialog.setModal(true);
        dialog.pack();
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}