

import javax.swing.JFrame;

public class ChaosTheory extends JFrame {
	public ChaosTheory() {
		setSize(1000,1000);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ChaosTheoryPanel ctp = new ChaosTheoryPanel();
		add(ctp);
		new Thread(ctp).start();
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new ChaosTheory();
	}
}
