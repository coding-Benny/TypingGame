import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

class TimerRunnable implements Runnable {
	private TypingGamePanel gamePanel = new TypingGamePanel();
	TypingGamePanel.JGameGroundPanel ground = gamePanel.new JGameGroundPanel();
	public static boolean isFinished = false;
	public static int remainedTime = 0;
	private JLabel timerLabel;
	
	public TimerRunnable(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}
	@Override
	public void run() {
		remainedTime=30;
		while(remainedTime>=0 && !Thread.interrupted()) {
			timerLabel.setText(Integer.toString(remainedTime));
			remainedTime--;
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				return;
			}
		}
		isFinished=true;	
	}
}

public class ScorePanel extends JPanel {
	static int correct=0;
	static int score=0;
	
	private JLabel textLabel = new JLabel("Score");
	static JLabel scoreLabel=new JLabel("0");
	private Thread th;
	private JLabel timeInfoLabel = new JLabel("Remaining Time");
	private JLabel timerLabel = new JLabel();
	
	static public void checkSuccess() { //맞췄을 때 점수 바뀌기
		if(ScorePanel.correct==1) {
			ScorePanel.score+=20;
			scoreLabel.setText(Integer.toString(ScorePanel.score));
			System.out.println("plus : " + ScorePanel.score);
		}
		else if(ScorePanel.correct ==-1) {
			if(ScorePanel.score<10)
				ScorePanel.score=0;
			else
				ScorePanel.score-=10;
			scoreLabel.setText(Integer.toString(ScorePanel.score));
			System.out.println("minus : " + ScorePanel.score);
		}
		else { //sucess 가 디폴트로 0일 때. 즉 아무일도 업을 때
			return;
		}
		ScorePanel.correct=0;
	}

	public ScorePanel() {
		setBackground(Color.PINK);	// 배경은 노란색
		setLayout(null);	// 개발자가 레이아웃 설정
		textLabel.setSize(150, 70);	// 텍스트 사이즈 50x30 설정
		textLabel.setFont(new Font("Gothic", Font.PLAIN, 30));
		textLabel.setLocation(10, 10);	// 텍스트 위치 10x10 설정
		add(textLabel);	// 텍스트를 panel에 추가
		
		scoreLabel.setSize(100, 70);
		scoreLabel.setFont(new Font("Gothic", Font.ITALIC, 30));
		scoreLabel.setLocation(100, 10);
		add(scoreLabel);
		checkSuccess();
		
		timeInfoLabel.setSize(200, 100);
		timeInfoLabel.setFont(new Font("Gothic", Font.ITALIC, 20));
		timeInfoLabel.setLocation(10, 50);
		add(timeInfoLabel);
		
		timerLabel.setSize(100, 100);
		timerLabel.setFont(new Font("Gothic", Font.ITALIC, 50));
		timerLabel.setLocation(170, 50);
		
		TimerRunnable runnable = new TimerRunnable(timerLabel);
		th = new Thread(runnable);
		add(timerLabel);
		th.start();
	}
	
	
}
