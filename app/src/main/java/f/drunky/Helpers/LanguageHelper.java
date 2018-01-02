package f.drunky.Helpers;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import f.drunky.Entity.Language;
import f.drunky.R;

/**
 * Created by AZhloba on 1/2/2018.
 */

public class LanguageHelper {
    public static Language En = new Language("En", R.string.Settings_English);
    public static Language Ru = new Language("Ru", R.string.Settings_Russian);

    private static List<Language> _languages = Arrays.asList(En, Ru);
    private static List<String> _languagesCodes = Arrays.asList(En.Code, Ru.Code);


    public static Language findLanguage(String code) throws Exception {
        for (Language lang : _languages) {
            if (lang.Code.equals(code)) {
                return lang;
            }
        }

        throw new Exception("unknown language code: " + code);
    }

    public static List<String> getAvailableLanguages() {
        return _languagesCodes;
    }
}
