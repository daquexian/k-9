package com.fsck.k9.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * An {@link android.widget.EditText} extension with methods that convert line endings from
 * {@code \r\n} to {@code \n} and back again when setting and getting text.
 *
 */
public class EolConvertingEditText extends EditText {

    public EolConvertingEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Return the text the EolConvertingEditText is displaying.
     *
     * @return A string with any line endings converted to {@code \r\n}.
     */
    public String getCharacters() {
        return getText().toString().replace("\n", "\r\n");
    }

    public SpannableStringBuilder getRichChars() {
        Editable editable = getText();
        Pattern newlinePattern = Pattern.compile("\\n");
        Matcher newlineMatcher = newlinePattern.matcher(editable);

        while (newlineMatcher.find()) {
            editable = editable.replace(newlineMatcher.start(), newlineMatcher.end(), "\r\n");
        }

        return new SpannableStringBuilder(editable);
    }

    /**
     * Sets the string value of the EolConvertingEditText. Any line endings
     * in the string will be converted to {@code \n}.
     *
     * @param text
     */
    public void  setCharacters(CharSequence text) {
        setText(text.toString().replace("\r\n", "\n"));
    }

}
