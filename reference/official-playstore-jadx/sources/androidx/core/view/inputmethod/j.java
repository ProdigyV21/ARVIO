package androidx.core.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build;

/* JADX INFO: loaded from: classes3.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f2266a;

    public j(Uri uri, ClipDescription clipDescription, Uri uri2) {
        if (Build.VERSION.SDK_INT >= 25) {
            this.f2266a = new g(uri, clipDescription, uri2);
        } else {
            this.f2266a = new h(uri, clipDescription, uri2);
        }
    }

    public j(g gVar) {
        this.f2266a = gVar;
    }
}
