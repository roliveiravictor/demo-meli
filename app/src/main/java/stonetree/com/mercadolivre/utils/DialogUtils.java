package stonetree.com.mercadolivre.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import stonetree.com.mercadolivre.R;
import stonetree.com.mercadolivre.constants.Constants;
import stonetree.com.mercadolivre.session.Session;

public class DialogUtils {

    public static void showMessage(final Activity activity, final String title, final String positiveMessage, DialogInterface.OnClickListener positiveListener, int gravityId) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);

        final View dialogCheckout = loadViewComponents(activity);
        alertDialogBuilder.setView(dialogCheckout);

        if (!Collections.isNullOrEmpty(title))
            alertDialogBuilder.setTitle(title);

        alertDialogBuilder.setPositiveButton(positiveMessage, positiveListener);

        final AlertDialog dialog = alertDialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        setButtonsGravity(dialog, AlertDialog.BUTTON_POSITIVE, gravityId);
    }

    private static View loadViewComponents(Activity activity) {
        final LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogCheckout = inflater.inflate(R.layout.dialog_checkout, null);

        final TextView amount = dialogCheckout.findViewById(R.id.amountToPay);
        final TextView quota = dialogCheckout.findViewById(R.id.quota);
        final ImageView cardThumbnail = dialogCheckout.findViewById(R.id.cardThumbnail);
        final ImageView issuerThumbnail = dialogCheckout.findViewById(R.id.issuerThumbnail);

        final Session session = Session.getInstance();

        String money = Constants.MONEY_LABEL + String.valueOf(session.getAmountToPay());
        amount.setText(money);

        quota.setText(session.getQuota());
        issuerThumbnail.setImageBitmap(session.getIssuerThumbnail());
        cardThumbnail.setImageBitmap(session.getCreditThumbnail());

        return dialogCheckout;
    }

    private static void setButtonsGravity(AlertDialog dialog, int buttonId, int gravityAlignment) {
        final Button button = dialog.getButton(buttonId);
        final LinearLayout.LayoutParams buttonLinearLayout = (LinearLayout.LayoutParams) button.getLayoutParams();
        buttonLinearLayout.gravity = gravityAlignment;
        button.setLayoutParams(buttonLinearLayout);
    }

}
