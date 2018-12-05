package f.drunky.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import f.drunky.Entity.Gender;
import f.drunky.Entity.Language;
import f.drunky.Navigation.ChainFragment;
import f.drunky.R;
import f.drunky.mvp.models.UserProfile;
import f.drunky.mvp.models.UserSettings;
import f.drunky.mvp.presenters.SettingsPresenter;
import f.drunky.mvp.views.SettingsView;


public class SettingsFragment extends ChainFragment implements SettingsView {

    @InjectPresenter
    SettingsPresenter presenter;

    private Spinner _spLanguages;
    private Spinner _spGenders;

    private List<Language> _languages;
    private List<Gender> _genders;
    private int _languageNum;
    private int _genderNum;
    private TextInputEditText _txtAge;
    private TextInputEditText _txtWeight;
    private Button _btnSave;


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
        super.onViewCreated(view, savedInstanceState);

        _spLanguages = getView().findViewById(R.id.spLanguages);
        _spGenders = getView().findViewById(R.id.spGenders);

        _txtAge = getView().findViewById(R.id.txtAge);
        _txtAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (count == 0) {
                    presenter.ageChanged(null);
                }
                else {
                    String text = charSequence.toString();
                    try {
                        int age = Integer.parseInt(text);
                        presenter.ageChanged(age);
                    } catch (Exception e) {
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        _txtWeight = getView().findViewById(R.id.txtWeight);
        _txtWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (count == 0) {
                    presenter.weightChanged(null);
                }
                else {
                    String text = charSequence.toString();
                    try {
                        float weight = Float.parseFloat(text);
                        presenter.weightChanged(weight);
                    } catch (Exception e) { }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        _btnSave = getView().findViewById(R.id.btnSave);
        _btnSave.setOnClickListener(v -> presenter.save());
    }

    @Override
    public void setLanguages(List<Language> languages) {
        _languages = languages;

        final LayoutInflater mInflater = LayoutInflater.from(getActivity());
        final int mResource = R.layout.spinner_language_item;
        ArrayAdapter<Language> languagesAdapter = new ArrayAdapter<Language>(getActivity(), mResource, languages) {
            @Override
            public View getDropDownView(int position, @Nullable View convertView,
                                        @NonNull ViewGroup parent) {
                return createItemView(position, convertView, parent);
            }

            @Override
            public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return createItemView(position, convertView, parent);
            }

            private View createItemView(int position, View convertView, ViewGroup parent){
                final View view = mInflater.inflate(mResource, parent, false);

                TextView txtLanguageSpinnerItem = view.findViewById(R.id.txtLanguageSpinnerItem);
                Language languageData = languages.get(position);

                txtLanguageSpinnerItem.setText(languageData.UiString);

                return view;
            }
        };

        _spLanguages.setAdapter(languagesAdapter);
        _spLanguages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (_languageNum != i) {
                    _languageNum = i;
                    Language language = _languages.get(i);
                    presenter.languageChanged(language);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void setGenders(List<Gender> genders) {
        _genders = genders;

        final LayoutInflater mInflater = LayoutInflater.from(getActivity());
        final int mResource = R.layout.spinner_gender_item;
        ArrayAdapter<Gender> gendersAdapter = new ArrayAdapter<Gender>(getActivity(), mResource, genders) {
            @Override
            public View getDropDownView(int position, @Nullable View convertView,
                                        @NonNull ViewGroup parent) {
                if (position == 0) {
                    return initialSelection(true);
                }
                return createItemView(position, convertView, parent);
            }

            @Override
            public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return createItemView(position, convertView, parent);
            }

            private View createItemView(int position, View convertView, ViewGroup parent){
                final View view = mInflater.inflate(mResource, parent, false);

                TextView txtGenderSpinnerItem = view.findViewById(R.id.txtGenderSpinnerItem);
                Gender genderData = genders.get(position);

                txtGenderSpinnerItem.setText(getText(genderData.UiStringId));
                if (position == 0) {
                    txtGenderSpinnerItem.setTextColor(getActivity().getColor(R.color.auxiliaryColor));
                }

                return view;
            }

            private View initialSelection(boolean dropdown) {
                TextView view = new TextView(getContext());

                if (dropdown) {
                    view.setHeight(0);
                }

                return view;
            }
        };

        _spGenders.setAdapter(gendersAdapter);
        _spGenders.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (_genderNum != i) {
                    _genderNum = i;
                    Gender gender = _genders.get(i);
                    presenter.genderChanged(gender);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    public void setUserProfile(UserProfile userProfile) {
        _genderNum = -1;
        for (int i = 0; i < _genders.size(); i++) {
            if (_genders.get(i).Code.equals(userProfile.Gender)) {
                if (_genderNum != i) {
                    _genderNum = i;
                    _spGenders.setSelection(_genderNum);
                }
                break;
            }
        }

        if (userProfile.Age != null) {
            _txtAge.setText(String.valueOf(userProfile.Age));
        }
        if (userProfile.Weight != null) {
            _txtWeight.setText(String.valueOf(userProfile.Weight));
        }
    }

    @Override
    public void setUserSettings(UserSettings userSettings) {
        _languageNum = -1;
        for (int i = 0; i < _languages.size(); i++) {
            if (_languages.get(i).Code.equals(userSettings.Language)) {
                if (_languageNum != i) {
                    _languageNum = i;
                    _spLanguages.setSelection(_languageNum);
                }
                break;
            }
        }
    }
}
