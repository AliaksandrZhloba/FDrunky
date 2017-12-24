package f.drunky.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import f.drunky.FDrunkyApplication;
import f.drunky.Helpers.SettingsHelper;
import f.drunky.Navigation.ChainFragment;
import f.drunky.R;


public class SettingsFragment extends ChainFragment {
    private int _languageId;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        String language = SettingsHelper.getLanguage();

        String[] languageNames = new String[] { getString(R.string.Settings_English), getString(R.string.Settings_Russian) };
        List<String> languages =  Arrays.asList("en", "ru");
        _languageId = languages.indexOf(language);

        ArrayAdapter<String> languagesAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, languageNames);
        languagesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spLanguage = getActivity().findViewById(R.id.spLanguages);
        spLanguage.setAdapter(languagesAdapter);
        spLanguage.setSelection(_languageId);

        spLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (_languageId != i) {
                    _languageId = i;

                    String locale = languages.get(i);
                    FDrunkyApplication.INSTANCE.LanguageController.setLanguage(locale);
                    SettingsHelper.setLanguage(locale);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
