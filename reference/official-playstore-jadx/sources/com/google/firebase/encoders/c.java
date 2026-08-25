package com.google.firebase.encoders;

import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f14367a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f14368b;

    public c(String str, Map map) {
        this.f14367a = str;
        this.f14368b = map;
    }

    public static c a(String str) {
        return new c(str, Collections.EMPTY_MAP);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f14367a.equals(cVar.f14367a) && this.f14368b.equals(cVar.f14368b);
    }

    public final int hashCode() {
        return this.f14368b.hashCode() + (this.f14367a.hashCode() * 31);
    }

    public final String toString() {
        return "FieldDescriptor{name=" + this.f14367a + ", properties=" + this.f14368b.values() + "}";
    }
}
