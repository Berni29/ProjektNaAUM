import javax.swing.*;
import java.awt.event.*;

public class Configuration extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField food;
    private JTextField strongPreds;
    private JTextField weakPreds;
    private JTextField travelers;
    private WorldMap wp;
    private JFrame frame;

    public Configuration(JFrame frame, WorldMap wp) {
        this.wp = wp;
        this.frame = frame;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        int foodVal = 0;
        int strongPredators = 0;
        int weakPredators = 0;
        int travelersNum = 0;
        try {
            foodVal = Integer.parseInt(food.getText());
            strongPredators = Integer.parseInt(strongPreds.getText());
            weakPredators = Integer.parseInt(weakPreds.getText());
            travelersNum = Integer.parseInt(travelers.getText());
            wp.setConfig(foodVal,strongPredators,weakPredators,travelersNum);
            this.setVisible(false);
            frame.setVisible(true);
            wp.repaint();
        }catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,"You must enter all parameters!","Error",0);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
