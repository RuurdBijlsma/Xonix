package xonix.Commands;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import xonix.Application;
import xonix.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AboutGame extends Command {
    GameView view;

    @Override
    public void actionPerformed(ActionEvent e) {
        view = Application.controller.view;
        JDialog dialog = new JDialog(view, "About Xonix", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(new Dimension(150, 150));
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);

        JLabel about = new JLabel("Over dit spel");
        Font font = new Font("Consolas", Font.BOLD, 14);
        about.setForeground(Color.black);
        about.setFont(font);
        about.validate();
        dialog.validate();
        about.setLocation(10, 10);
        about.setSize(about.getPreferredSize());

        dialog.setVisible(true);
        about.setVisible(true);
        dialog.add(about);
        dialog.repaint();
        about.repaint();
    }
}
