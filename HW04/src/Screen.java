/* Garrett N. Shuman
HW04
10/1/20
Used lecture video code where applicable

*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Screen extends JFrame implements ActionListener {
    User owner;
    JTextField input;
    JPanel panel;
    JLabel label;
    JButton button;
    JTextArea chat;
    public Screen (User owner) {
        this.owner = owner;
        this.input = new JTextField();
        this.input.setPreferredSize(new Dimension(80,20));
        this.label = new JLabel(this.owner.name);
        this.button = new JButton("Send");
        this.button.addActionListener(this);
        this.chat = new JTextArea();

        this.chat.setPreferredSize(new Dimension(300,300));
        this.chat.setEditable(false); // user cant edit received messages

        this.panel = new JPanel();
        this.panel.add(this.label);
        this.panel.add(this.input);

        this.panel.add(this.button);
        this.panel.add(this.chat);



        this.add(this.panel);
        this.setSize(400, 400);
        this.setVisible(true);
    }
    public void actionPerformed (ActionEvent e){
        this.owner.broadcast(this.input.getText());
        chat.setText(chat.getText() + " \n"  + owner.name + ": " + this.input.getText()); // get previous text and append new text for sender
        this.input.setText("");


    }
}
