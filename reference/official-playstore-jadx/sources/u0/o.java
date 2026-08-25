package u0;

import android.icu.text.DecimalFormatSymbols;
import android.text.PrecomputedText;
import android.widget.TextView;

/* JADX INFO: loaded from: classes3.dex */
public abstract class o {
    public static CharSequence a(PrecomputedText precomputedText) {
        return precomputedText;
    }

    public static String[] b(DecimalFormatSymbols decimalFormatSymbols) {
        return decimalFormatSymbols.getDigitStrings();
    }

    public static PrecomputedText.Params c(TextView textView) {
        return textView.getTextMetricsParams();
    }

    public static void d(TextView textView, int i10) {
        textView.setFirstBaselineToTopHeight(i10);
    }
}
