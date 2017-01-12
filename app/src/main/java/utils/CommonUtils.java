package utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.yohan.dummyapp.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 14-12-16.
 */

public class CommonUtils
{

    private static Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    private static Pattern VALID_NAME_REGEX = Pattern.compile("/^[a-z ,.'-]+$/i\n");
    private int value;

    public static void showErrorDialog(Context mCtx, String msg)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        builder.setMessage(msg);
        builder.setPositiveButton(mCtx.getString(R.string.ok), new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void showProgressDialog(Context mCtx, String msg)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        builder.setMessage(msg);
        builder.setCancelable(false);

        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void showInputDialog(Context mCtx, String msg, final UtilsResponseInterface responseInterface)
    {
        int value;
        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        builder.setMessage(msg);
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        final EditText et_number = (EditText) inflater.inflate(R.layout.edittext_entry, null, false);
        et_number.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        builder.setView(et_number);
        builder.setPositiveButton(mCtx.getString(R.string.ok), new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
                responseInterface.onResponse(et_number.getText().toString());
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.show();

    }

    public static boolean isStringEmpty(String value)
    {

        if( value==null || value.equals("") )
            return true;
        else
            return false;
    }

    public static boolean isValidEmail(String email)
    {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

        return matcher.matches();
    }

    public static boolean isValidPassword(String password)
    {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);

        return matcher.matches();
    }

    public static String trimExtraSpace(String text)
    {
        text.replaceFirst("\\s++$", "");
        text.replace("\\s++", " ");

        return text;
    }

    public static boolean isValidName(String name)
    {
        Matcher matcher = VALID_NAME_REGEX.matcher(name);

        return matcher.matches();
    }

}
