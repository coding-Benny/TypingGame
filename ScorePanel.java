import java.awt.Color;

import javax.swing.*;

class ScorePanel extends JPanel {
	private JLabel text = new JLabel("점수");
	private JLabel score = new JLabel("0");
	public ScorePanel() {
		setBackground(Color.YELLOW);	// 배경은 노란색
		setLayout(null);	// 개발자가 레이아웃 설정
		text.setSize(50, 30);	// 텍스트 사이즈 50x30 설정
		text.setLocation(10, 10);	// 텍스트 위치 10x10 설정
		add(text);	// 텍스트를 panel에 추가
		
		score.setSize(100, 30);
		score.setLocation(70, 10);
		add(score);
	}
}
