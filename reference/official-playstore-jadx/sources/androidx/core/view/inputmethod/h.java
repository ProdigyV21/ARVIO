package androidx.core.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;

/* JADX INFO: loaded from: classes3.dex */
public final class h implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Uri f2263a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ClipDescription f2264b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Uri f2265c;

    public h(Uri uri, ClipDescription clipDescription, Uri uri2) {
        this.f2263a = uri;
        this.f2264b = clipDescription;
        this.f2265c = uri2;
    }

    @Override // androidx.core.view.inputmethod.i
    public final ClipDescription a() {
        return this.f2264b;
    }

    @Override // androidx.core.view.inputmethod.i
    public final Object b() {
        return null;
    }

    @Override // androidx.core.view.inputmethod.i
    public final Uri c() {
        return this.f2263a;
    }

    @Override // androidx.core.view.inputmethod.i
    public final void d() {
    }

    @Override // androidx.core.view.inputmethod.i
    public final Uri e() {
        return this.f2265c;
    }
}
