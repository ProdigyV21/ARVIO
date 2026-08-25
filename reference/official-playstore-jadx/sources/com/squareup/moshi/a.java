package com.squareup.moshi;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes4.dex */
public final class a implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14397a;

    public /* synthetic */ a(int i10) {
        this.f14397a = i10;
    }

    public static void b(Type type, Class cls) {
        Class<?> clsD = e0.d(type);
        if (cls.isAssignableFrom(clsD)) {
            throw new IllegalArgumentException("No JsonAdapter for " + type + ", you should probably use " + cls.getSimpleName() + " instead of " + clsD.getSimpleName() + " (Moshi only supports the collection interfaces by default) or else register a custom JsonAdapter.");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0330  */
    /* JADX WARN: Type inference failed for: r0v91, types: [java.lang.Object, x6.s] */
    @Override // com.squareup.moshi.k
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.squareup.moshi.l a(java.lang.reflect.Type r26, java.util.Set r27, com.squareup.moshi.z r28) {
        /*
            Method dump skipped, instruction units count: 2216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.a.a(java.lang.reflect.Type, java.util.Set, com.squareup.moshi.z):com.squareup.moshi.l");
    }
}
