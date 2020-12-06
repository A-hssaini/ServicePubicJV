import Presentation.Login.*;

public class Main {

	public static void main(String[] args) {
		Login auth = new Login("Admin", "Mot de passe");
		auth.setVisible(true);
	}
}
