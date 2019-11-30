import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;

class TypingGamePanel extends JPanel {
	private JGameGroundPanel groundPanel = new JGameGroundPanel();
	private JInputPanel inputPanel = new JInputPanel();
	private String newWord;
	private Words words = new Words("words.txt");

	public TypingGamePanel() {
		setLayout(new BorderLayout());
		add(groundPanel, BorderLayout.CENTER);
		add(inputPanel, BorderLayout.SOUTH);
	}

	class JGameGroundPanel extends JPanel {
		private JLabel label;

		public JGameGroundPanel() {
			setLayout(null);
			label = new JLabel("타이핑해보세요");
			label.setLocation(100, 50);
			label.setSize(100, 15);
			add(label);
		}
	}

	class JInputPanel extends JPanel {

		public JInputPanel() {
			setLayout(new FlowLayout());
			JTextField tf = new JTextField(40);
			add(tf);

			tf.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JTextField t = (JTextField) e.getSource();
					if (newWord.equals(t.getText())) {
						// System.out.println("correct");
						run();
					} else {
						// System.out.println("wrong");
					}
					t.setText("");
				}
			});
		}
	}
	public void run() {
		newWord = words.getRandomWord();
		System.out.println(newWord);
		groundPanel.label.setText(newWord);
		if (newWord == null)
			System.out.println("error");
	}
	
}

//words.txt 파일을 읽고 벡터에 저장하고 벡터로부터 랜덤하게 단어를 추출하는 클래스
class Words {
	Vector<String> wordVector = new Vector<String>();

	public Words(String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				wordVector.add(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("file not found error");
			System.exit(0);
		} catch (IOException e) {
			System.out.print("input output error");
		}
	}

	public String getRandomWord() {
		final int WORDMAX = wordVector.size(); // 총 단어의 개수
		int index = (int) (Math.random() * WORDMAX);
		return wordVector.get(index);
	}
}
