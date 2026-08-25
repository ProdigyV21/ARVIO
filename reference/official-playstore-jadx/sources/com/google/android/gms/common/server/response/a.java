package com.google.android.gms.common.server.response;

import android.os.Parcel;

/* JADX INFO: loaded from: classes4.dex */
public final class a extends t3.a {
    public static final g CREATOR = new g();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f12947i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f12948l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f12949m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f12950n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final boolean f12951o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f12952p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f12953q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Class f12954r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final String f12955s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public j f12956t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final b f12957u;

    public a(int i10, int i11, boolean z, int i12, boolean z5, String str, int i13, String str2, z3.b bVar) {
        this.f12947i = i10;
        this.f12948l = i11;
        this.f12949m = z;
        this.f12950n = i12;
        this.f12951o = z5;
        this.f12952p = str;
        this.f12953q = i13;
        if (str2 == null) {
            this.f12954r = null;
            this.f12955s = null;
        } else {
            this.f12954r = e.class;
            this.f12955s = str2;
        }
        if (bVar == null) {
            this.f12957u = null;
            return;
        }
        z3.a aVar = bVar.f23172l;
        if (aVar == null) {
            throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
        }
        this.f12957u = aVar;
    }

    public static a d(int i10, String str) {
        return new a(7, true, 7, true, str, i10, null);
    }

    public final String toString() {
        io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(this);
        cVar.w(Integer.valueOf(this.f12947i), "versionCode");
        cVar.w(Integer.valueOf(this.f12948l), "typeIn");
        cVar.w(Boolean.valueOf(this.f12949m), "typeInArray");
        cVar.w(Integer.valueOf(this.f12950n), "typeOut");
        cVar.w(Boolean.valueOf(this.f12951o), "typeOutArray");
        cVar.w(this.f12952p, "outputFieldName");
        cVar.w(Integer.valueOf(this.f12953q), "safeParcelFieldId");
        String str = this.f12955s;
        if (str == null) {
            str = null;
        }
        cVar.w(str, "concreteTypeName");
        Class cls = this.f12954r;
        if (cls != null) {
            cVar.w(cls.getCanonicalName(), "concreteType.class");
        }
        b bVar = this.f12957u;
        if (bVar != null) {
            cVar.w(bVar.getClass().getCanonicalName(), "converterName");
        }
        return cVar.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f12947i);
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(this.f12948l);
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(this.f12949m ? 1 : 0);
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(this.f12950n);
        a.a.l0(parcel, 5, 4);
        parcel.writeInt(this.f12951o ? 1 : 0);
        a.a.g0(parcel, 6, this.f12952p, false);
        a.a.l0(parcel, 7, 4);
        parcel.writeInt(this.f12953q);
        z3.b bVar = null;
        String str = this.f12955s;
        if (str == null) {
            str = null;
        }
        a.a.g0(parcel, 8, str, false);
        b bVar2 = this.f12957u;
        if (bVar2 != null) {
            if (!(bVar2 instanceof z3.a)) {
                throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
            }
            bVar = new z3.b((z3.a) bVar2);
        }
        a.a.f0(parcel, 9, bVar, i10, false);
        a.a.n0(iM0, parcel);
    }

    public a(int i10, boolean z, int i11, boolean z5, String str, int i12, Class cls) {
        this.f12947i = 1;
        this.f12948l = i10;
        this.f12949m = z;
        this.f12950n = i11;
        this.f12951o = z5;
        this.f12952p = str;
        this.f12953q = i12;
        this.f12954r = cls;
        if (cls == null) {
            this.f12955s = null;
        } else {
            this.f12955s = cls.getCanonicalName();
        }
        this.f12957u = null;
    }
}
