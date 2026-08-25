package androidx.core.app;

import android.app.PendingIntent;
import android.os.Bundle;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final IconCompat f2049a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CharSequence f2050b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final PendingIntent f2051c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f2052d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Bundle f2053e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f2054f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f2055g;

    public u(int i10, PendingIntent pendingIntent, String str) {
        this(i10 != 0 ? IconCompat.e(null, "", i10) : null, str, pendingIntent, new Bundle());
    }

    public final x a() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = this.f2054f;
        if (arrayList3 != null) {
            Iterator it = arrayList3.iterator();
            if (it.hasNext()) {
                it.next().getClass();
                throw new ClassCastException();
            }
        }
        return new x(this.f2049a, this.f2050b, this.f2051c, this.f2053e, arrayList2.isEmpty() ? null : (a2[]) arrayList2.toArray(new a2[arrayList2.size()]), arrayList.isEmpty() ? null : (a2[]) arrayList.toArray(new a2[arrayList.size()]), this.f2052d, this.f2055g);
    }

    public u(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle) {
        this.f2052d = true;
        this.f2055g = true;
        this.f2049a = iconCompat;
        this.f2050b = d0.b(charSequence);
        this.f2051c = pendingIntent;
        this.f2053e = bundle;
        this.f2054f = null;
        this.f2052d = true;
        this.f2055g = true;
    }
}
