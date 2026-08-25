package androidx.core.view;

import android.content.ClipData;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContentInfo;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public final class j implements i, k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2267a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ClipData f2268b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2269c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2270d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Uri f2271e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Bundle f2272f;

    public /* synthetic */ j() {
    }

    @Override // androidx.core.view.i
    public void a(Uri uri) {
        this.f2271e = uri;
    }

    @Override // androidx.core.view.i
    public n build() {
        return new n(new j(this));
    }

    @Override // androidx.core.view.k
    public int f() {
        return this.f2269c;
    }

    @Override // androidx.core.view.k
    public ContentInfo g() {
        return null;
    }

    @Override // androidx.core.view.k
    public ClipData getClip() {
        return this.f2268b;
    }

    @Override // androidx.core.view.k
    public int getFlags() {
        return this.f2270d;
    }

    @Override // androidx.core.view.i
    public void setExtras(Bundle bundle) {
        this.f2272f = bundle;
    }

    @Override // androidx.core.view.i
    public void setFlags(int i10) {
        this.f2270d = i10;
    }

    public String toString() {
        String str;
        switch (this.f2267a) {
            case 1:
                Uri uri = this.f2271e;
                StringBuilder sb2 = new StringBuilder("ContentInfoCompat{clip=");
                sb2.append(this.f2268b.getDescription());
                sb2.append(", source=");
                int i10 = this.f2269c;
                sb2.append(i10 != 0 ? i10 != 1 ? i10 != 2 ? i10 != 3 ? i10 != 4 ? i10 != 5 ? String.valueOf(i10) : "SOURCE_PROCESS_TEXT" : "SOURCE_AUTOFILL" : "SOURCE_DRAG_AND_DROP" : "SOURCE_INPUT_METHOD" : "SOURCE_CLIPBOARD" : "SOURCE_APP");
                sb2.append(", flags=");
                int i11 = this.f2270d;
                sb2.append((i11 & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(i11));
                if (uri == null) {
                    str = "";
                } else {
                    str = ", hasLinkUri(" + uri.toString().length() + ")";
                }
                sb2.append(str);
                return a0.c.p(sb2, this.f2272f != null ? ", hasExtras" : "", "}");
            default:
                return super.toString();
        }
    }

    public j(j jVar) {
        ClipData clipData = jVar.f2268b;
        clipData.getClass();
        this.f2268b = clipData;
        int i10 = jVar.f2269c;
        if (i10 < 0) {
            Locale locale = Locale.US;
            throw new IllegalArgumentException("source is out of range of [0, 5] (too low)");
        }
        if (i10 > 5) {
            Locale locale2 = Locale.US;
            throw new IllegalArgumentException("source is out of range of [0, 5] (too high)");
        }
        this.f2269c = i10;
        int i11 = jVar.f2270d;
        if ((i11 & 1) == i11) {
            this.f2270d = i11;
            this.f2271e = jVar.f2271e;
            this.f2272f = jVar.f2272f;
        } else {
            throw new IllegalArgumentException("Requested flags 0x" + Integer.toHexString(i11) + ", but only 0x" + Integer.toHexString(1) + " are allowed");
        }
    }
}
