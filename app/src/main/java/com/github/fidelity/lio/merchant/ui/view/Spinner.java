package com.github.fidelity.lio.merchant.ui.view;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class Spinner<T extends Spinner.SpinnerItem> extends EditText implements View.OnClickListener, ActionMode.Callback {

    private Context context;
    private OnSpinnerListener<T> onItemSelectedListener;
    private Dialog dialog;
    private ArrayAdapter<T> arrayAdapter;

    public Spinner(Context context) {
        super(context);
        this.context = context;
    }

    public Spinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public Spinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Spinner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setFocusable(false);
        setClickable(true);
        buildItemsSpinner();
        setOnClickListener(this);
        setCustomSelectionActionModeCallback(this);
        setLongClickable(false);
        setTextIsSelectable(false);
    }

    @Override
    public void onClick(View v) {
        requestFocus();
        dialog.show();
    }

    private void buildItemsSpinner() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setAdapter(arrayAdapter, new OnClickListenerToDialog());
        this.dialog = builder.create();
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }

    private class OnClickListenerToDialog implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int selectedIndex) {
            setText(arrayAdapter.getItem(selectedIndex).toString());

            if (onItemSelectedListener != null) {
                onItemSelectedListener.onItemSelectedListener(arrayAdapter.getItem(selectedIndex), selectedIndex);
            }
        }
    }

    public void setOnItemSelectedListener(OnSpinnerListener<T> onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public interface OnSpinnerListener<T> {
        void onItemSelectedListener(T item, int selectedIndex);
    }

    public void setAdapter(ArrayAdapter<T> adapter) {
        arrayAdapter = adapter;
    }

    @Override
    public boolean isSuggestionsEnabled() {
        return false;
    }

    @Override
    public boolean onTextContextMenuItem(int id) {
        switch (id) {
            case android.R.id.paste:
            case android.R.id.pasteAsPlainText:
                return false;

        }
        return super.onTextContextMenuItem(id);
    }

    public interface SpinnerItem {
        @Override
        String toString();
    }


}