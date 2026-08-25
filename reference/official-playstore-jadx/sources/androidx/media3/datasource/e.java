package androidx.media3.datasource;

import android.text.TextUtils;
import androidx.media3.common.MimeTypes;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class e {
    public static /* synthetic */ boolean a(String str) {
        if (str == null) {
            return false;
        }
        String strS = t7.a.S(str);
        return (TextUtils.isEmpty(strS) || (strS.contains("text") && !strS.contains(MimeTypes.TEXT_VTT)) || strS.contains("html") || strS.contains("xml")) ? false : true;
    }
}
