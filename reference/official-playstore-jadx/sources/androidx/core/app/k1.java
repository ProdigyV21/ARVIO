package androidx.core.app;

import android.app.Notification;

/* JADX INFO: loaded from: classes3.dex */
public final class k1 implements o1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2014a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2015b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Notification f2016c;

    public k1(String str, int i10, Notification notification) {
        this.f2014a = str;
        this.f2015b = i10;
        this.f2016c = notification;
    }

    @Override // androidx.core.app.o1
    public final void a(d.c cVar) {
        cVar.Z(this.f2014a, this.f2015b, this.f2016c);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("NotifyTask[packageName:");
        sb2.append(this.f2014a);
        sb2.append(", id:");
        return androidx.compose.material3.d.j(this.f2015b, ", tag:null]", sb2);
    }
}
