package com.arflix.tv.updater;

import d7.d;
import e7.a;
import f7.c;
import f7.e;
import kotlin.Metadata;
import x6.d0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@e(c = "com.arflix.tv.updater.AppUpdateRepository", f = "AppUpdateRepository.kt", l = {56}, m = "getLatestUpdate-IoAF18A", v = 2)
public final class AppUpdateRepository$getLatestUpdate$1 extends c {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AppUpdateRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppUpdateRepository$getLatestUpdate$1(AppUpdateRepository appUpdateRepository, d<? super AppUpdateRepository$getLatestUpdate$1> dVar) {
        super(dVar);
        this.this$0 = appUpdateRepository;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6613getLatestUpdateIoAF18A = this.this$0.m6613getLatestUpdateIoAF18A(this);
        return objM6613getLatestUpdateIoAF18A == a.f15033i ? objM6613getLatestUpdateIoAF18A : new d0(objM6613getLatestUpdateIoAF18A);
    }
}
