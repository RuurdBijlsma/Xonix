package xonix.Commands;

import java.awt.event.ActionEvent;

public class QuitGame extends Command{
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
