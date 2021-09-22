package pages;

import Base.BasePage;
import Base.Browsers;

public class GooglePage extends BasePage {
    public GooglePage() {
        super(Browsers.SAFARI);
    }

    public void navigateToGoogle(){
        System.getProperties().list(System.out);
        this.openURL("https://google.com");
    }
}
