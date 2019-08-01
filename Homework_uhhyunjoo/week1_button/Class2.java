package button;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Desktop;

public class Class2 {

	private JFrame frmClass;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Class2 window = new Class2();
					window.frmClass.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Class2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClass = new JFrame();
		frmClass.setTitle("Class2");
		frmClass.setBounds(100, 100, 450, 150);
		frmClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClass.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Toast");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//double click the button after that there is available
				//save class2 and class1 then just run
				Class3 nw3 = new Class3();
				nw3.ToastScreen();
			}
		});
		btnNewButton.setBounds(70, 41, 105, 27);
		frmClass.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("internet");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//double click the button after that there is available
				//save class2 and class1 then just run
				
				Class4 nw4 = new Class4();
				nw4.InternetScreen();
			}
		});
		btnNewButton_1.setBounds(262, 41, 105, 27);
		frmClass.getContentPane().add(btnNewButton_1);
	}
}
