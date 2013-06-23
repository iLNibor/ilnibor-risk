import java.awt.CardLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.io.FileNotFoundException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
 
/**
 * TODO:
 * 
 * @see documentation on cardLayout 
 * 	(here: http://docs.oracle.com/javase/tutorial/uiswing/layout/card.html)
 * @author Michael Zhang
 * @author Akhil Velegapudi
 * @author That Kid Named Sid
 *
 */
public class GuiFrame extends JFrame {
	private static final long serialVersionUID = 5633826349468137699L;
	protected JPanel cards; // The primary, center panel of the board. Flips through multiple pages in response to user input
	private JPanel mainPanel; // A panel containing the mainMenu of the game. Features the mainMenu and a panel containing text
	private JPanel sidePanel; // The panel on the right side of the mainmenu. Contains the group credit text.
	protected MainMenuPanel mainMenu; // The mainMenu of the game. To be placed onto the mainPanel.
	protected GameBoardPanel gameBoard; // The gameboard of the game. To be placed on the cards variable
	protected String MENUPANEL = "Menu"; // The string label for the mainmenu of the game for the cards variable
	protected String GAMEPANEL = "Game"; // The string label for the gameboard of the game for the cards variable
	private ImageIcon icon; // The icon of the game on the titlebar of the program
	private Dimension frameSize; // The size of the frame in general
	public MessageLogFrame messages;

	public GuiFrame() throws FileNotFoundException {
	
		// Creates the message log frame
		messages = new MessageLogFrame();
		
		// Setup the menuPanel of the frame (to be implemented)
		makeMenu();
		
		// Set the size of the frame
		frameSize = new Dimension(1179, 700);
		
		// Set the location of where the frame icon is located
		icon = new ImageIcon("resources/iconimage.png");
		
		// Set the title of the frame
		setTitle("iLNibor's RISK");
		
		// Set the icon of the frame to be the image contained in icon variable
		setIconImage(icon.getImage());
		
		// Sets the program to end upon user exit of the frame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Sets the size of the frame
		setSize(frameSize);
		
		// Sets the frame to non-resizeable
		setResizable(false);
		
		// Gives the frame a borderLayout from within the Java Awt API
		setLayout(new BorderLayout());
		
		// Initializes the cards variable
		cards = new JPanel(new CardLayout());
		
		// Removed Tester code (don't know if this should have be here, Akhil
		// it might be better to make a separate class, say Game.java,
		// to put a tester like this.)
				
		// Setup the mainMenu and the gameboard
		mainMenu = new MainMenuPanel();
		
		// Setup the main menu Panel size
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setMinimumSize(frameSize);
		mainPanel.setMaximumSize(frameSize);
		mainPanel.setPreferredSize(frameSize);
		mainPanel.add(mainMenu, BorderLayout.WEST);
		
		// Create a side panel, add text to it, then place it on the main menu panel
		sidePanel = new JPanel(new BorderLayout());
		JLabel title = new JLabel("<html>" +
									"<font size = \"20\" color = \"black\"><u>________</u></font><br><br><br><br>" +
									"<div style = \"color:red\">" +
									"<h1><font size = \"5\" face = \"georgia\">Team iLNibor</font></h1></div>" +
									"<p face = \"verdana\"><font size = \"5\" face = \"georgia\">" +
									"Mike Zhang" +
									"<br>Akhil Velegapudi" +
									"<br>Sid Senthilkumar</font><br>" +
									"In Loving Memory Of..." +
									"<font size = \"20\" color = \"black\" face = \"georgia\"><br><i>Robin Li</i></font>" +
									"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Our dear leader.</p>" +
									//"<p style = \"color:gray\">" +
									"<font size = \"20\" color = \"black\"><u>________</u></font>" + 
									//"<font size = \"5\">Motto:<br><i>\"For Great Justice:<br>&nbsp;Take off every Zig\"</i></font></p>" +
								  "</html>");
		sidePanel.setBackground(Color.white);
		sidePanel.add(title, BorderLayout.WEST);
		mainPanel.add(sidePanel);
		
		// Add the mainmenu to the cards panel (main, center board)
		cards.add(mainPanel, MENUPANEL);
		add(cards);
		
		// Setup listeners for the return to main menu button on the actual gameBoard
		// NOT IMPLEMENTED YET
		setupGameListener();
		
		// Sets startup location of frame
		//setLocationRelativeTo(null);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		setLocation(0, (int) (ge.getMaximumWindowBounds().getHeight() - getHeight())/2);
		
	}
	
	public void flipToGame() {
		CardLayout cl = (CardLayout)cards.getLayout();
		cl.show(cards, GAMEPANEL);
	}
	
	public void flipToMenu() {
		CardLayout c1 = (CardLayout)cards.getLayout();
		c1.show(cards, MENUPANEL);
	}
	
	public void setGameBoardPanelInformation(GameBoardPanel gbd) {
		gameBoard = gbd;
		cards.add(gameBoard, GAMEPANEL);
		cards.revalidate();
		cards.repaint();
	}
	
	
	/**
	 * Exits the application
	 */
	public void exitApp() {
		messages.dispose();
		this.dispose();
	}
	
	public void setVisible(boolean visible)
	{
		super.setVisible(visible);
		messages.setVisible(true);
	}
	
	public void setupGameListener() {
		
	}
	
	public void makeMenu() {
		
	}
	
}
