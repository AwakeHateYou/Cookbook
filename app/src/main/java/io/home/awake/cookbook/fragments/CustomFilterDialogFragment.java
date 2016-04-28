package io.home.awake.cookbook.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import io.home.awake.cookbook.R;
import io.home.awake.cookbook.activity.CookbookActivity;


/**
 * Кастомный диалог фильтра ингредиентов.
 */
public class CustomFilterDialogFragment extends DialogFragment implements
        DialogInterface.OnClickListener {
    /**
     *
     */
    private View form = null;
    private EditText filterBox;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        form = getActivity().getLayoutInflater()
                .inflate(R.layout.fragment_filter_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Фильтр по ингредиентам");
        builder.setView(form);
        builder.setPositiveButton(android.R.string.ok, this);
        builder.setNegativeButton(android.R.string.cancel, null);
        return builder.create();
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {
        EditText filterBox =(EditText)form.findViewById(R.id.filterText);
        String value = filterBox.getText().toString();
        CookbookActivity callingActivity = (CookbookActivity) getActivity();
        callingActivity.onUserSelectValue(value);
        dialog.dismiss();

    }
    @Override
    public void onDismiss(DialogInterface unused) {
        super.onDismiss(unused);
    }
    @Override
    public void onCancel(DialogInterface unused) {
        super.onCancel(unused);
    }
}