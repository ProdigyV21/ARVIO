package androidx.core.view;

import android.view.ContentInfo;
import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public abstract class r1 {
    public static String[] a(View view) {
        return view.getReceiveContentMimeTypes();
    }

    public static n b(View view, n nVar) {
        ContentInfo contentInfoD = nVar.d();
        ContentInfo contentInfoPerformReceiveContent = view.performReceiveContent(contentInfoD);
        if (contentInfoPerformReceiveContent == null) {
            return null;
        }
        return contentInfoPerformReceiveContent == contentInfoD ? nVar : new n(new h(contentInfoPerformReceiveContent));
    }

    public static void c(View view, String[] strArr, s0 s0Var) {
        if (s0Var == null) {
            view.setOnReceiveContentListener(strArr, null);
        } else {
            view.setOnReceiveContentListener(strArr, new w1(s0Var));
        }
    }
}
