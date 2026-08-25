package v3;

import android.util.Log;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f22155a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f22156b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f22157c;

    public a(String str, String... strArr) {
        String string;
        if (strArr.length == 0) {
            string = "";
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append('[');
            for (String str2 : strArr) {
                if (sb2.length() > 1) {
                    sb2.append(",");
                }
                sb2.append(str2);
            }
            sb2.append("] ");
            string = sb2.toString();
        }
        this.f22156b = string;
        this.f22155a = str;
        int i10 = 2;
        Object[] objArr = {str, 23};
        if (!(str.length() <= 23)) {
            throw new IllegalArgumentException(String.format("tag \"%s\" is longer than the %d character maximum", objArr));
        }
        while (i10 <= 7 && !Log.isLoggable(this.f22155a, i10)) {
            i10++;
        }
        this.f22157c = i10;
    }

    public final void a(String str, Object... objArr) {
        if (this.f22157c <= 3) {
            Log.d(this.f22155a, b(str, objArr));
        }
    }

    public final String b(String str, Object... objArr) {
        if (objArr.length > 0) {
            str = String.format(Locale.US, str, objArr);
        }
        return this.f22156b.concat(str);
    }

    public final void c(String str, Object... objArr) {
        Log.w(this.f22155a, b(str, objArr));
    }
}
