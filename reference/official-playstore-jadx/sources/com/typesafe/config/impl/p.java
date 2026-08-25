package com.typesafe.config.impl;

import java.io.File;

/* JADX INFO: loaded from: classes4.dex */
public final class p implements x1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14606a;

    public /* synthetic */ p(int i10) {
        this.f14606a = i10;
    }

    @Override // com.typesafe.config.impl.x1
    public final l6.m a(String str, l6.l lVar) {
        switch (this.f14606a) {
            case 0:
                return f1.h(str, lVar);
            default:
                File file = new File(str);
                com.google.android.gms.common.api.internal.f1 f1Var = f1.f14531d;
                return new y0(file, lVar);
        }
    }
}
