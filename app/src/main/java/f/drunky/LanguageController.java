package f.drunky;

/**
 * Created by AZhloba on 12/22/2017.
 */

public abstract class LanguageController {
    private String _currentLanguage = "en";

    public String getCurrentLanguage() { return _currentLanguage; }
    public void setCurrentLanguage(String language) { _currentLanguage = language; }

    public abstract void setLanguage(String language);
}
