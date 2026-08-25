package androidx.core.view;

import android.content.ClipData;
import android.view.ContentInfo;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f2292a;

    public n(k kVar) {
        this.f2292a = kVar;
    }

    public final ClipData a() {
        return this.f2292a.getClip();
    }

    public final int b() {
        return this.f2292a.getFlags();
    }

    public final int c() {
        return this.f2292a.f();
    }

    public final ContentInfo d() {
        ContentInfo contentInfoG = this.f2292a.g();
        Objects.requireNonNull(contentInfoG);
        return contentInfoG;
    }

    public final String toString() {
        return this.f2292a.toString();
    }
}
