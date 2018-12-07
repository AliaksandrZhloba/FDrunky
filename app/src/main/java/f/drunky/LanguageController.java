package f.drunky;

import f.drunky.Helpers.SettingsHelper;

/**
 * Created by AZhloba on 12/22/2017.
 */

public abstract class LanguageController {
    private String _language = null;

    public String getLanguage() { return _language; }
    public void setLanguage(String language) { _language = language; }


    public LanguageController() {
        _language = SettingsHelper.getLanguage();
    }

    public abstract void switchLanguage(String language);
}
