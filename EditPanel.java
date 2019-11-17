import java.awt.*;
import javax.swing.*;

class EditPanel extends JPanel {
	public EditPanel() {
		setBackground(Color.YELLOW);
		setLayout(new FlowLayout());
		add(new JTextField(20));
		add(new JButton("add"));
		add(new JButton("save"));
	}
}