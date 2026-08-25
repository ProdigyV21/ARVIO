package com.squareup.moshi;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes4.dex */
public final class x extends l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Type f14490a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f14491b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f14492c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public l f14493d;

    public x(Type type, String str, Object obj) {
        this.f14490a = type;
        this.f14491b = str;
        this.f14492c = obj;
    }

    @Override // com.squareup.moshi.l
    public final Object a(p pVar) {
        l lVar = this.f14493d;
        if (lVar != null) {
            return lVar.a(pVar);
        }
        throw new IllegalStateException("JsonAdapter isn't ready");
    }

    @Override // com.squareup.moshi.l
    public final void e(q qVar, Object obj) {
        l lVar = this.f14493d;
        if (lVar == null) {
            throw new IllegalStateException("JsonAdapter isn't ready");
        }
        lVar.e(qVar, obj);
    }

    public final String toString() {
        l lVar = this.f14493d;
        return lVar != null ? lVar.toString() : super.toString();
    }
}
