import java.awt.*;
import javax.swing.*;

class TypingGamePanel extends JPanel {
	public TypingGamePanel() {
		setLayout(new BorderLayout());
		add(new JGameGroundPanel(), BorderLayout.CENTER);
		add(new JInputPanel(), BorderLayout.SOUTH);
	}
	
	class JGameGroundPanel extends JPanel {
		public JGameGroundPanel() {
			setLayout(null);
			JLabel label = new JLabel("타이핑해보세요");
			label.setLocation(100, 50);
			label.setSize(100, 15);
			add(label);
		}
	}
	class JInputPanel extends JPanel {
		public JInputPanel() {
			setLayout(new FlowLayout());
			add(new JTextField(40));
		}
	}
}
