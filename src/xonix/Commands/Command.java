package xonix.Commands;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class Command extends AbstractAction implements ActionListener {
    @Override
    public abstract void actionPerformed(ActionEvent e);
    protected Command(){}
}
