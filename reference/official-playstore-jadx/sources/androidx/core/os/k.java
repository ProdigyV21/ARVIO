package androidx.core.os;

import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Locale[] f2125a = {new Locale("en", "XA"), new Locale("ar", "XB")};

    public static Locale a(String str) {
        return Locale.forLanguageTag(str);
    }

    public static boolean b(Locale locale, Locale locale2) {
        if (locale.equals(locale2)) {
            return true;
        }
        if (locale.getLanguage().equals(locale2.getLanguage())) {
            Locale[] localeArr = f2125a;
            int length = localeArr.length;
            int i10 = 0;
            while (true) {
                if (i10 >= length) {
                    int length2 = localeArr.length;
                    int i11 = 0;
                    while (true) {
                        if (i11 >= length2) {
                            String strA = androidx.core.text.e.a(locale);
                            if (!strA.isEmpty()) {
                                return strA.equals(androidx.core.text.e.a(locale2));
                            }
                            String country = locale.getCountry();
                            if (country.isEmpty() || country.equals(locale2.getCountry())) {
                                return true;
                            }
                        } else {
                            if (localeArr[i11].equals(locale2)) {
                                break;
                            }
                            i11++;
                        }
                    }
                } else {
                    if (localeArr[i10].equals(locale)) {
                        break;
                    }
                    i10++;
                }
            }
        }
        return false;
    }
}
