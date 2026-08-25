package com.google.android.gms.internal.auth;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public final class s0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f13109a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k1 f13110b;

    public s0(Context context, k1 k1Var) {
        this.f13109a = context;
        this.f13110b = k1Var;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof s0) {
            s0 s0Var = (s0) obj;
            if (this.f13109a.equals(s0Var.f13109a) && this.f13110b.equals(s0Var.f13110b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.f13109a.hashCode() ^ 1000003) * 1000003) ^ this.f13110b.hashCode();
    }

    public final String toString() {
        return androidx.fragment.app.a2.m("FlagsContext{context=", this.f13109a.toString(), ", hermeticFileOverrides=", this.f13110b.toString(), "}");
    }
}
