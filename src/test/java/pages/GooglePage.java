package pages;

import Base.BasePage;
import Base.Browsers;

public class GooglePage extends BasePage {
    public GooglePage() {
        super(Browsers.FIREFOX);
    }

    public void navigateToGoogle(){
        this.openURL("https://google.com");
    }
}
