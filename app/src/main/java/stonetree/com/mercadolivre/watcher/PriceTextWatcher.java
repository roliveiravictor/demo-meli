package stonetree.com.mercadolivre.watcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;

import stonetree.com.mercadolivre.constants.Constants;
import stonetree.com.mercadolivre.utils.Collections;
import stonetree.com.mercadolivre.utils.MoneyUtils;

public class PriceTextWatcher implements TextWatcher {

    private final WeakReference<EditText> editTextWeakReference;

    private boolean isDeleteOperation = false;

    public PriceTextWatcher(EditText editText) {
        editTextWeakReference = new WeakReference<>(editText);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        EditText priceInput = editTextWeakReference.get();
        if (priceInput == null) return;

        Editable editablePriceInput = priceInput.getText();
        if (editablePriceInput == null) return;

        priceInput.setSelection(editablePriceInput.length());
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int delete, int add) {
        if (delete > add)
            isDeleteOperation = true;
        else
            isDeleteOperation = false;
    }

    @Override
    public void afterTextChanged(Editable editable) {
        final EditText priceInput = editTextWeakReference.get();
        if (priceInput == null) return;

        priceInput.removeTextChangedListener(this);

        final String price = editable.toString();

        String unmaskedPrice = MoneyUtils.cleanMask(price);
        if (isDeleteOperation)
            unmaskedPrice = unmaskedPrice.substring(0, unmaskedPrice.length() - 1);

        if (Collections.isNullOrEmpty(unmaskedPrice))
            unmaskedPrice = Constants.ZERO_STRING;

        int amount = Integer.valueOf(unmaskedPrice);

        final DecimalFormat decimalFormat = new DecimalFormat("##,###.00");

        String formattedDecimal = decimalFormat.format(amount);
        if (Constants.CENTS.equals(formattedDecimal))
            formattedDecimal = Constants.ZERO_STRING + Constants.CENTS;

        final String formattedPrice = Constants.MONEY_LABEL + formattedDecimal;

        priceInput.setText(formattedPrice);
        priceInput.setSelection(formattedPrice.length());
        priceInput.addTextChangedListener(this);
    }
}