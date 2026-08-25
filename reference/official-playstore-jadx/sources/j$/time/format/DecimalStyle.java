package j$.time.format;

import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class DecimalStyle {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final DecimalStyle f18214d = new DecimalStyle('0', '-', '.');

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ConcurrentHashMap f18215e = new ConcurrentHashMap(16, 0.75f, 2);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final char f18216a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final char f18217b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final char f18218c;

    /* JADX WARN: Multi-variable type inference failed */
    public static DecimalStyle of(Locale locale) {
        DecimalStyle decimalStyle;
        Objects.requireNonNull(locale, "locale");
        ConcurrentHashMap concurrentHashMap = f18215e;
        DecimalStyle decimalStyle2 = (DecimalStyle) concurrentHashMap.get(locale);
        if (decimalStyle2 != null) {
            return decimalStyle2;
        }
        DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance(locale);
        char zeroDigit = decimalFormatSymbols.getZeroDigit();
        char minusSign = decimalFormatSymbols.getMinusSign();
        char decimalSeparator = decimalFormatSymbols.getDecimalSeparator();
        if (zeroDigit == '0' && minusSign == '-' && decimalSeparator == '.') {
            decimalStyle = f18214d;
        } else {
            decimalStyle = new DecimalStyle(zeroDigit, minusSign, decimalSeparator);
        }
        concurrentHashMap.putIfAbsent(locale, decimalStyle);
        return (DecimalStyle) concurrentHashMap.get(locale);
    }

    public DecimalStyle(char c10, char c11, char c12) {
        this.f18216a = c10;
        this.f18217b = c11;
        this.f18218c = c12;
    }

    public final String a(String str) {
        char c10 = this.f18216a;
        if (c10 == '0') {
            return str;
        }
        int i10 = c10 - '0';
        char[] charArray = str.toCharArray();
        for (int i11 = 0; i11 < charArray.length; i11++) {
            charArray[i11] = (char) (charArray[i11] + i10);
        }
        return new String(charArray);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DecimalStyle)) {
            return false;
        }
        DecimalStyle decimalStyle = (DecimalStyle) obj;
        return this.f18216a == decimalStyle.f18216a && this.f18217b == decimalStyle.f18217b && this.f18218c == decimalStyle.f18218c;
    }

    public final int hashCode() {
        return this.f18216a + '+' + this.f18217b + this.f18218c;
    }

    public final String toString() {
        return "DecimalStyle[" + this.f18216a + '+' + this.f18217b + this.f18218c + "]";
    }
}
