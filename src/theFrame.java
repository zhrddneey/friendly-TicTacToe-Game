import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class theFrame implements ActionListener{
	 JFrame frame = new JFrame("Start Menu");
	 JLabel optionLabel = new JLabel("Enter Players Name",SwingConstants.CENTER);
	 JLabel oLabel = new JLabel();
	 JLabel xLabel = new JLabel();
	 JButton submit = new JButton("Submit");
	 
	 JTextField oText = new JTextField();
	 JTextField xText = new JTextField();
	 ImageIcon oIcon = new ImageIcon("letter-o_9467531.png");
	 ImageIcon xIcon = new ImageIcon("x_13932861.png");


	theFrame(){
		
		frame.setSize(450,600);
		frame.getContentPane().setBackground(new Color(25, 25, 75));
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		//submit button
		submit.setBounds(120,230,210,40);
		submit.setFocusable(false);
		submit.setBackground(new Color(30, 168, 220)); // Light blue
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("SansSerif", Font.BOLD, 16));
        submit.addActionListener(this);

        submit.setFocusPainted(false);
		frame.add(submit);
		
		//resize the images
		oIcon = resizeImage (oIcon,30,30);
		xIcon = resizeImage (xIcon,30,30);

		
		//add the oicons and otextField to the frame
		oLabel.setIcon(oIcon);
		oLabel.setBounds(60,110,40,40);
		
		oText.setBounds(100,110,300,40);
		oText.setFont(new Font("SansSerif",Font.BOLD,15));
		oText.setBackground(new Color(0,0,51));
		oText.setForeground(Color.white);
		oText.setCaretColor(Color.white);
		frame.add(oText);
		
		//add the xicons and xtextField to the frame
		xLabel.setIcon(xIcon);
		xLabel.setBounds(60,170,40,40);
		frame.add(xLabel);
				
		xText.setBounds(100,170,300,40);
		xText.setFont(new Font("SansSerif",Font.BOLD,15));
		xText.setBackground(new Color(0,0,51));
		xText.setForeground(Color.white);
		xText.setCaretColor(Color.white);
		frame.add(xText);
		
		// top text on first page
		optionLabel.setFont(new Font("SansSerif",Font.BOLD,25));
		optionLabel.setForeground(Color.white);
		optionLabel.setBounds(100,60,240,40);
		
		frame.add(oLabel);
		frame.add(optionLabel);
		frame.setVisible(true);
		
	}

  // resize the size of the image icons
	private ImageIcon resizeImage(ImageIcon icon, int width, int height) {
		Image img = icon.getImage();
		Image resize = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
		return new ImageIcon(resize);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==submit) {
			//to check if first and second person names are chose before proceeding to next page
			if(!(oText.getText().equals("")) && !(xText.getText().equals(""))){
				String o =oText.getText();
				String x =xText.getText();
				
				//go to next page
				TicTacToeUi tictac =	new TicTacToeUi(o,x);
				
				frame.dispose();
			}
			else {
				// display an error message if the one of the name are not filled
				JOptionPane.showMessageDialog(null, "You Must Fill the Options","Options Are not Selected" , JOptionPane.WARNING_MESSAGE );
			}
		}
		
	}
    
   
}
