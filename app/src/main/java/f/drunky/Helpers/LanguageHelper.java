package f.drunky.Helpers;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import f.drunky.Entity.Language;
import f.drunky.FDrunkyApplication;
import f.drunky.R;

/**
 * Created by AZhloba on 1/2/2018.
 */

public class LanguageHelper {
    public static final Language En = new Language("En", "English");
    public static final Language Ru = new Language("Ru", "Русский");

    private static final List<Language> _languages = Arrays.asList(En, Ru);


    public static Language findLanguage(String code) throws Exception {
        for (Language lang : _languages) {
            if (lang.Code.equals(code)) {
                return lang;
            }
        }

        throw new Exception("unknown language code: " + code);
    }

    public static List<Language> getLanguages() {
        return _languages;
    }
}
