package c3;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public final class c extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f7389a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m3.a f7390b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final m3.a f7391c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f7392d;

    public c(Context context, m3.a aVar, m3.a aVar2, String str) {
        if (context == null) {
            throw new NullPointerException("Null applicationContext");
        }
        this.f7389a = context;
        if (aVar == null) {
            throw new NullPointerException("Null wallClock");
        }
        this.f7390b = aVar;
        if (aVar2 == null) {
            throw new NullPointerException("Null monotonicClock");
        }
        this.f7391c = aVar2;
        if (str == null) {
            throw new NullPointerException("Null backendName");
        }
        this.f7392d = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof f) {
            c cVar = (c) ((f) obj);
            if (this.f7389a.equals(cVar.f7389a) && this.f7390b.equals(cVar.f7390b) && this.f7391c.equals(cVar.f7391c) && this.f7392d.equals(cVar.f7392d)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((((this.f7389a.hashCode() ^ 1000003) * 1000003) ^ this.f7390b.hashCode()) * 1000003) ^ this.f7391c.hashCode()) * 1000003) ^ this.f7392d.hashCode();
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("CreationContext{applicationContext=");
        sb2.append(this.f7389a);
        sb2.append(", wallClock=");
        sb2.append(this.f7390b);
        sb2.append(", monotonicClock=");
        sb2.append(this.f7391c);
        sb2.append(", backendName=");
        return a0.c.p(sb2, this.f7392d, "}");
    }
}
