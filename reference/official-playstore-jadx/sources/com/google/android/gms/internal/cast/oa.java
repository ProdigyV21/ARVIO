package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public abstract class oa implements hb {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final pa f13529i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public pa f13530l;

    public oa(pa paVar) {
        this.f13529i = paVar;
        if (paVar.l()) {
            throw new IllegalArgumentException("Default instance must be immutable.");
        }
        this.f13530l = (pa) paVar.k(4, null);
    }

    public final void c() {
        if (this.f13530l.l()) {
            return;
        }
        pa paVar = (pa) this.f13529i.k(4, null);
        ob.f13531c.a(paVar.getClass()).c(paVar, this.f13530l);
        this.f13530l = paVar;
    }

    public final Object clone() {
        oa oaVar = (oa) this.f13529i.k(5, null);
        oaVar.f13530l = d();
        return oaVar;
    }

    public final pa d() {
        if (!this.f13530l.l()) {
            return this.f13530l;
        }
        pa paVar = this.f13530l;
        paVar.getClass();
        ob.f13531c.a(paVar.getClass()).g(paVar);
        paVar.m();
        return this.f13530l;
    }

    public final pa e() {
        pa paVarD = d();
        paVarD.getClass();
        boolean z = true;
        byte bByteValue = ((Byte) paVarD.k(1, null)).byteValue();
        if (bByteValue != 1) {
            if (bByteValue == 0) {
                z = false;
            } else {
                boolean zF = ob.f13531c.a(paVarD.getClass()).f(paVarD);
                paVarD.k(2, true == zF ? paVarD : null);
                z = zF;
            }
        }
        if (z) {
            return paVarD;
        }
        throw new zzaac("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }
}
