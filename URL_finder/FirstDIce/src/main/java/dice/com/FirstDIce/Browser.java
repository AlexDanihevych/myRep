package dice.com.FirstDIce;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Browser {

	public static void openUrl(String url) throws IOException, URISyntaxException {
		
		Desktop desk = Desktop.getDesktop();
		desk.browse(URI.create(url));
	}

}
