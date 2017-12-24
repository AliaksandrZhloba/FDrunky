package f.drunky.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import f.drunky.R;

/**
 * Created by AZhloba on 12/21/2017.
 */

public class AskToFillProfileDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_ask_to_fill_profile, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(view);

        Button btnOk = view.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(l ->  {
            dismiss();
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
        });

        Button btnSkip = view.findViewById(R.id.btnSkip);
        btnSkip.setOnClickListener(l -> dismiss());

        return builder.create();
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }
}
