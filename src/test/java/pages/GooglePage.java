package pages;

import Base.BasePage;
import Base.Browsers;

public class GooglePage extends BasePage {

    private final String searchButton = "//input[contains(@value, 'Buscar con Google')]";
    private final String searchField = "//input[@title='Buscar']";

    public GooglePage() {
        super(Browsers.FIREFOX);
    }

    public void navigateToGoogle(){
        this.openURL("https://google.com");
    }

    public void enterSearchCriteria(String criteria){
        this.write(searchField, criteria);
    }

    public void clickGoogleSearch(){
        this.clickElement(this.searchButton);
    }
}
