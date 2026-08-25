package com.google.common.base;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
public abstract class x implements r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final t f13913i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ x[] f13914l;

    static {
        t tVar = new t();
        f13913i = tVar;
        f13914l = new x[]{tVar, new x() { // from class: com.google.common.base.u
            @Override // com.google.common.base.r
            public final boolean apply(Object obj) {
                return false;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "Predicates.alwaysFalse()";
            }
        }, new x() { // from class: com.google.common.base.v
            @Override // com.google.common.base.r
            public final boolean apply(Object obj) {
                return obj == null;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "Predicates.isNull()";
            }
        }, new x() { // from class: com.google.common.base.w
            @Override // com.google.common.base.r
            public final boolean apply(Object obj) {
                return obj != null;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "Predicates.notNull()";
            }
        }};
    }

    public static x valueOf(String str) {
        return (x) Enum.valueOf(x.class, str);
    }

    public static x[] values() {
        return (x[]) f13914l.clone();
    }
}
