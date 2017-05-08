package com.crystal.flexin.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.crystal.flexin.R;
import com.crystal.flexin.activity.TextSearchActivity;

/**
 * Created by basaile92 on 25/04/2017.
 */

public class DialogTextSearchFragment extends DialogFragment {

    public static String DIALOGINTENT = "dialogintent";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View view = inflater.inflate(R.layout.dialog_text_search, null);
        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.searchLabel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = (EditText) view.findViewById(R.id.textSearchDialogTextField);
                        Intent intent = new Intent(getActivity(), TextSearchActivity.class);
                        intent.putExtra(DIALOGINTENT ,editText.getText());
                        startActivity(intent);
                        getActivity().finish();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DialogTextSearchFragment.this.getDialog().cancel();
                    }
                })
                .setTitle(R.string.searchTextDialogTitle);
        return builder.create();
    }

}
