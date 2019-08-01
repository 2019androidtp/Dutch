package button;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.net.URI;
public class Class4 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void InternetScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Class4 window = new Class4();
					window.frame.setVisible(true);
					
					Desktop d = Desktop.getDesktop();
					d.browse(new URI("http://naver.com"));
					
				} catch (Exception e) {
					e.printStackTrace();
					
					// if it is not working
					Class3 nw3 = new Class3();
					nw3.ToastScreen();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Class4() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
