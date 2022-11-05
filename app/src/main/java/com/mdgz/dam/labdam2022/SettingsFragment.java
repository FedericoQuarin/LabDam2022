package com.mdgz.dam.labdam2022;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import com.google.android.material.transition.MaterialFade;
import com.google.android.material.transition.MaterialFadeThrough;

public class SettingsFragment extends PreferenceFragmentCompat {

    private SwitchPreferenceCompat temaOscuroPreference;
    private ListPreference listaModoOscuro;
    private EditTextPreference emailPreference;
    private EditTextPreference cuitPreference;
    private ListPreference listaPreference;
    private ListPreference listaMoneda;
    private Preference logs;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        // Configuraciones de la preferencia Tema oscuro
        listaModoOscuro = findPreference("tema");
        if (listaModoOscuro != null) {
            listaModoOscuro.setOnPreferenceChangeListener((preference, newValue) -> {
                // Si se selecciona el mismo valor que no se modifique nada
                if(listaModoOscuro.getValue().equals(newValue.toString())) return true;

                if(newValue.toString().equals("desactivado")) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                else if(newValue.toString().equals("activado")) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                }
                return true;
            });
        }

        // Configuraciones de las Notificaciones
        /* No se hace nada aca, solo se guarda si esta on o off, cuando se necesite realizar
           una notificacion se debe preguntar en que estado está
         */

        // Configuraciones de la preferencia Email
        emailPreference = findPreference("email");
        if (emailPreference != null) {
            // Setear que se muestre "No configurado"
            emailPreference.setSummaryProvider(new Preference.SummaryProvider<EditTextPreference>() {
                @Override
                public CharSequence provideSummary(EditTextPreference preference) {
                    String text = preference.getText();
                    if (TextUtils.isEmpty(text)){
                        return "No configurado";
                    }
                    return text;
                }
            });

            emailPreference.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                        @Override
                        public void onBindEditText(@NonNull EditText editText) {
                            editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                        }
                    });
        }

        // Configuraciones de la preferencia CUIT
        cuitPreference = findPreference("cuit");
        if (cuitPreference != null) {
            // Setear que se muestre "No configurado"
            cuitPreference.setSummaryProvider(new Preference.SummaryProvider<EditTextPreference>() {
                @Override
                public CharSequence provideSummary(EditTextPreference preference) {
                    String text = preference.getText();
                    if (TextUtils.isEmpty(text)){
                        return "No configurado";
                    }
                    return text;
                }
            });

            cuitPreference.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
            });
        }

        // Configuraciones de la preferencia Metodo de pago preferido
        listaPreference = findPreference("pagoPreferido");
        listaMoneda = findPreference("monedaPreferida");
        if (listaPreference != null) {
            if (listaMoneda != null) {
                if (!listaPreference.getValue().equals("efectivo")) {
                    listaMoneda.setValue("pesos");
                    listaMoneda.setEnabled(false);
                }
                else {
                    listaMoneda.setEnabled(true);
                }

                listaPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {

                        // Habilitar o desabilitar la moneda preferida dependiendo del metodo preferido
                        if (!newValue.toString().equals("efectivo")) {
                            listaMoneda.setValue("pesos");
                            listaMoneda.setEnabled(false);
                        } else {
                            listaMoneda.setEnabled(true);
                        }

                        return true;
                    }
                });
            }

        }

        logs = findPreference("logs");
        if (logs != null) {

            logs.setOnPreferenceClickListener(preference -> {

                NavHostFragment.findNavController(this).navigate(R.id.action_settingsFragment_to_logBusquedaFragment);

                return true;
            });
        }
    }
}