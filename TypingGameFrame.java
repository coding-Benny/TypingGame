import java.awt.*;
import javax.swing.*;

class TypingGameFrame extends JFrame {
	private TypingGamePanel gamePanel = new TypingGamePanel();
	private	ScorePanel scorePanel = new ScorePanel();
	private EditPanel editPanel = new EditPanel();
	
	public TypingGameFrame() {
		setTitle("타이핑 게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.setLayout(new BorderLayout());
		container.add(makeToolBar(), BorderLayout.NORTH);
		setJMenuBar(makeMenu());
		setSize(800,600);
		setResizable(false);
		splitPane();
		setVisible(true);
	}
	
	public JToolBar makeToolBar() {
		JToolBar toolBar = new JToolBar();
		toolBar.add(new JButton("Play"));
		toolBar.add(new JButton("Pause"));
		toolBar.add(new JButton("Resume"));
		
		return toolBar;
	}
	
	public JMenuBar makeMenu() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu accountMenu = new JMenu("Account");
		accountMenu.add("Sign in");
		accountMenu.add("Sign out");
		accountMenu.addSeparator();
		accountMenu.add("Remove");
		menuBar.add(accountMenu);
		
		JMenu gameMenu = new JMenu("Game");
		gameMenu.add("New Game");
		gameMenu.add("Continue");
		gameMenu.add("Save");
		gameMenu.addSeparator();
		gameMenu.add("Records");
		gameMenu.add("Rank");
		gameMenu.add("Exit");
		menuBar.add(gameMenu);
		
		JMenu settingMenu = new JMenu("Settings");
		settingMenu.add("Color");
		settingMenu.add("Font");
		settingMenu.addSeparator();
		settingMenu.add("Background Music");
		settingMenu.add("Sound Effect");
		settingMenu.addSeparator();
		settingMenu.add("Language");
		menuBar.add(settingMenu);
		
		JMenu infoMenu = new JMenu("Info");
		infoMenu.add("How to play");
		infoMenu.add("About");
		menuBar.add(infoMenu);
		
		return menuBar;
	}
	
	private void splitPane() {
		JSplitPane hPane = new JSplitPane();
		getContentPane().add(hPane, BorderLayout.CENTER);
		
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		hPane.setDividerLocation(500);
		hPane.setEnabled(false);
		hPane.setLeftComponent(gamePanel);
		
		JSplitPane pPane = new JSplitPane();
		hPane.setRightComponent(pPane);
		pPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pPane.setDividerLocation(200);
		pPane.setTopComponent(scorePanel);
		pPane.setBottomComponent(editPanel);
	}
}
