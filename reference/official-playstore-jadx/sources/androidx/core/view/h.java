package androidx.core.view;

import android.content.ClipData;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContentInfo;

/* JADX INFO: loaded from: classes3.dex */
public final class h implements i, k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2236a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f2237b;

    public h(ContentInfo contentInfo) {
        contentInfo.getClass();
        this.f2237b = contentInfo;
    }

    @Override // androidx.core.view.i
    public void a(Uri uri) {
        ((ContentInfo.Builder) this.f2237b).setLinkUri(uri);
    }

    @Override // androidx.core.view.i
    public n build() {
        return new n(new h(((ContentInfo.Builder) this.f2237b).build()));
    }

    @Override // androidx.core.view.k
    public int f() {
        return ((ContentInfo) this.f2237b).getSource();
    }

    @Override // androidx.core.view.k
    public ContentInfo g() {
        return (ContentInfo) this.f2237b;
    }

    @Override // androidx.core.view.k
    public ClipData getClip() {
        return ((ContentInfo) this.f2237b).getClip();
    }

    @Override // androidx.core.view.k
    public int getFlags() {
        return ((ContentInfo) this.f2237b).getFlags();
    }

    @Override // androidx.core.view.i
    public void setExtras(Bundle bundle) {
        ((ContentInfo.Builder) this.f2237b).setExtras(bundle);
    }

    @Override // androidx.core.view.i
    public void setFlags(int i10) {
        ((ContentInfo.Builder) this.f2237b).setFlags(i10);
    }

    public String toString() {
        switch (this.f2236a) {
            case 1:
                return "ContentInfoCompat{" + ((ContentInfo) this.f2237b) + "}";
            default:
                return super.toString();
        }
    }

    public h(ClipData clipData, int i10) {
        this.f2237b = g.a(clipData, i10);
    }
}
