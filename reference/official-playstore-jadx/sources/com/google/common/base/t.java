package com.google.common.base;

/* JADX INFO: loaded from: classes4.dex */
public enum t extends x {
    public t() {
        super("ALWAYS_TRUE", 0);
    }

    @Override // com.google.common.base.r
    public final boolean apply(Object obj) {
        return true;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "Predicates.alwaysTrue()";
    }
}
