package androidx.core.view.inputmethod;

import android.view.inputmethod.EditorInfo;

/* JADX INFO: loaded from: classes3.dex */
public abstract class a {
    public static void a(EditorInfo editorInfo, CharSequence charSequence) {
        editorInfo.setInitialSurroundingSubText(charSequence, 0);
    }
}
