package xonix.Commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;

abstract class Command extends AbstractAction implements ActionListener {
    @Override
    public abstract void actionPerformed(ActionEvent e);
    protected Command(){}
}
