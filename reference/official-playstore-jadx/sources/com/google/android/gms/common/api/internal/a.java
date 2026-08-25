package com.google.android.gms.common.api.internal;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f12689a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final com.google.android.gms.common.api.i f12690b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final com.google.android.gms.common.api.d f12691c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f12692d;

    public a(com.google.android.gms.common.api.i iVar, com.google.android.gms.common.api.d dVar, String str) {
        this.f12690b = iVar;
        this.f12691c = dVar;
        this.f12692d = str;
        this.f12689a = Arrays.hashCode(new Object[]{iVar, dVar, str});
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return com.google.android.gms.common.internal.t.l(this.f12690b, aVar.f12690b) && com.google.android.gms.common.internal.t.l(this.f12691c, aVar.f12691c) && com.google.android.gms.common.internal.t.l(this.f12692d, aVar.f12692d);
    }

    public final int hashCode() {
        return this.f12689a;
    }
}
