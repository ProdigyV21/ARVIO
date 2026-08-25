package androidx.core.app;

import android.app.PendingIntent;
import android.os.Bundle;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Bundle f2067a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public IconCompat f2068b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a2[] f2069c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f2070d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f2071e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f2072f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final CharSequence f2073g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final PendingIntent f2074h;

    public x(int i10, CharSequence charSequence, PendingIntent pendingIntent) {
        this(i10 != 0 ? IconCompat.e(null, "", i10) : null, charSequence, pendingIntent);
    }

    public final IconCompat a() {
        int i10;
        if (this.f2068b == null && (i10 = this.f2072f) != 0) {
            this.f2068b = IconCompat.e(null, "", i10);
        }
        return this.f2068b;
    }

    public x(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent) {
        this(iconCompat, charSequence, pendingIntent, new Bundle(), null, null, true, true);
    }

    public x(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, a2[] a2VarArr, a2[] a2VarArr2, boolean z, boolean z5) {
        this.f2071e = true;
        this.f2068b = iconCompat;
        if (iconCompat != null) {
            int i10 = iconCompat.f2090a;
            if ((i10 == -1 ? j0.c.c(iconCompat.f2091b) : i10) == 2) {
                this.f2072f = iconCompat.f();
            }
        }
        this.f2073g = d0.b(charSequence);
        this.f2074h = pendingIntent;
        this.f2067a = bundle == null ? new Bundle() : bundle;
        this.f2069c = a2VarArr;
        this.f2070d = z;
        this.f2071e = z5;
    }
}
