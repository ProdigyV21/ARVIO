package androidx.core.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.view.inputmethod.InputContentInfo;

/* JADX INFO: loaded from: classes3.dex */
public final class g implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InputContentInfo f2262a;

    public g(Object obj) {
        this.f2262a = (InputContentInfo) obj;
    }

    @Override // androidx.core.view.inputmethod.i
    public final ClipDescription a() {
        return this.f2262a.getDescription();
    }

    @Override // androidx.core.view.inputmethod.i
    public final Object b() {
        return this.f2262a;
    }

    @Override // androidx.core.view.inputmethod.i
    public final Uri c() {
        return this.f2262a.getContentUri();
    }

    @Override // androidx.core.view.inputmethod.i
    public final void d() {
        this.f2262a.requestPermission();
    }

    @Override // androidx.core.view.inputmethod.i
    public final Uri e() {
        return this.f2262a.getLinkUri();
    }

    public g(Uri uri, ClipDescription clipDescription, Uri uri2) {
        this.f2262a = new InputContentInfo(uri, clipDescription, uri2);
    }
}
