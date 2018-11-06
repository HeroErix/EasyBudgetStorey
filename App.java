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
					reView rv = new reView();
					rv.setVisible(true);
					Controller c = new Controller(rv);
					c.initController();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}