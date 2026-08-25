package com.google.android.gms.common.internal;

import android.content.ComponentName;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class u0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f12920a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f12921b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ComponentName f12922c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f12923d;

    public u0(ComponentName componentName) {
        this.f12920a = null;
        this.f12921b = null;
        t.i(componentName);
        this.f12922c = componentName;
        this.f12923d = false;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof u0)) {
            return false;
        }
        u0 u0Var = (u0) obj;
        return t.l(this.f12920a, u0Var.f12920a) && t.l(this.f12921b, u0Var.f12921b) && t.l(this.f12922c, u0Var.f12922c) && this.f12923d == u0Var.f12923d;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f12920a, this.f12921b, this.f12922c, 4225, Boolean.valueOf(this.f12923d)});
    }

    public final String toString() {
        String str = this.f12920a;
        if (str != null) {
            return str;
        }
        ComponentName componentName = this.f12922c;
        t.i(componentName);
        return componentName.flattenToString();
    }

    public u0(String str, String str2, boolean z) {
        t.f(str);
        this.f12920a = str;
        t.f(str2);
        this.f12921b = str2;
        this.f12922c = null;
        this.f12923d = z;
    }
}
