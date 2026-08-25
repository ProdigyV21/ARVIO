package androidx.core.provider;

import android.database.Cursor;
import android.net.Uri;

/* JADX INFO: loaded from: classes3.dex */
interface b {
    Cursor a(Uri uri, String[] strArr, String[] strArr2);

    void close();
}
