import java.awt.EventQueue;
import UI.View;
import UI.reView;

public class App {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//View v = new View();
					reView rv = new reView();
					rv.setVisible(true);
					Controller c = new Controller(rv);
					c.initController();
					//v.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}