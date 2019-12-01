import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;

class TypingGamePanel extends JPanel {
	private JGameGroundPanel groundPanel = new JGameGroundPanel();
	private JInputPanel inputPanel = new JInputPanel();
	
	public TypingGamePanel() {
		setLayout(new BorderLayout());
		add(groundPanel, BorderLayout.CENTER);
		add(inputPanel, BorderLayout.SOUTH);
	}

	class JGameGroundPanel extends JPanel {
		private JLabel label = new JLabel();
		private JLabel resLabel = new JLabel();
		private Words words = null;
		private String fallingWord = null;
		private FallingThread thread = null;
		private boolean gameOn = false;
		
		public JGameGroundPanel() {
			setLayout(null);
			add(label); // 화면에 표시 및 출력
			
			resLabel.setLocation(0, 0);
			resLabel.setSize(100, 30);
			add(resLabel);
			words = new Words("words.txt");
		}
		
		public boolean isGameOn() {
			return gameOn;
		}
		
		public void stopGame() {
			if(thread == null)
				return; // 스레드가 없음 
			thread.interrupt(); // 스레드 강제 종료
			thread = null;
			gameOn = false;
		}
		
		public void stopSelfAndNewGame() { // 스레드가 바닥에 닿아서 실패할 때 호출
			startGame();			
		}
		
		public void printResult(String text) {
			resLabel.setText(text);
		}
		
		public void startGame() {
			fallingWord = words.getRandomWord();
			int x = ((int)(Math.random() * this.getWidth()));
			int y = ((int)(Math.random() * (this.getHeight()/3)));
			label.setText(fallingWord);
			label.setSize(200, 30);
			label.setLocation(x, y);
			label.setForeground(Color.MAGENTA);
			label.setFont(new Font("Tahoma", Font.ITALIC, 20));
			
			thread = new FallingThread(this, label);
			thread.start();
			gameOn = true;
		}
		
		public boolean matchWord(String text) {
			if(fallingWord != null && fallingWord.equals(text))
				return true;
			else
				return false;
		}
		
		class FallingThread extends Thread {
			private JGameGroundPanel panel;
			private JLabel label; //게임 숫자를 출력하는 레이블
			private long delay = 200; // 지연 시간의 초깃값 = 200
			private boolean falling = false; // 떨이지고 있는지. 초깃값 = false
			
			public FallingThread(JGameGroundPanel panel, JLabel label) {
				this.panel = panel;
				this.label = label;
			}
			
			public boolean isFalling() {
				return falling; 
			}	
			
			@Override
			public void run() {
				falling = true;
				while(true) {
					try {
						sleep(delay);
						int y = label.getY() + 5; //5픽셀 씩 아래로 이동
						if(y >= panel.getHeight()-label.getHeight()) {
							falling = false;
							label.setText("");
							groundPanel.printResult("시간초과실패");
							groundPanel.stopSelfAndNewGame();
							break; // 스레드 종료
						}
						label.setLocation(label.getX(), y);
						TypingGamePanel.this.repaint();
					} catch (InterruptedException e) {
						falling = false;
						return; // 스레드 종료
					}
				}
			}	
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
					String answer = t.getText();
					if(answer.equals("그만"))
						System.exit(0);
					if(!groundPanel.isGameOn())
						return;
					
					boolean match = groundPanel.matchWord(answer);
					if(match == true) {
						groundPanel.printResult("성공");
						groundPanel.stopGame();
						groundPanel.startGame();
						tf.setText("");
					}
					else {
						groundPanel.printResult(tf.getText() + " 틀림");
						tf.setText("");
					}
				}	
			});
		}
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
