package v6;

import android.os.Build;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes5.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f22162a = 0;

    static {
        StringBuilder sb2 = new StringBuilder();
        String str = Build.FINGERPRINT;
        if (str != null) {
            sb2.append(str);
        }
        String str2 = null;
        try {
            str2 = (String) Build.class.getField("SERIAL").get(null);
        } catch (Exception unused) {
        }
        if (str2 != null) {
            sb2.append(str2);
        }
        try {
            sb2.toString().getBytes(Charset.forName("UTF-8"));
        } catch (UnsupportedEncodingException unused2) {
            throw new RuntimeException("UTF-8 encoding not supported");
        }
    }
}
