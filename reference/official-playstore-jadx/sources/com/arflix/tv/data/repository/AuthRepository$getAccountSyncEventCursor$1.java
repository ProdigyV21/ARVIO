package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.AuthRepository", f = "AuthRepository.kt", l = {1780, 1787}, m = "getAccountSyncEventCursor-IoAF18A", v = 2)
public final class AuthRepository$getAccountSyncEventCursor$1 extends f7.c {
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AuthRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AuthRepository$getAccountSyncEventCursor$1(AuthRepository authRepository, d7.d<? super AuthRepository$getAccountSyncEventCursor$1> dVar) {
        super(dVar);
        this.this$0 = authRepository;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6096getAccountSyncEventCursorIoAF18A = this.this$0.m6096getAccountSyncEventCursorIoAF18A(this);
        return objM6096getAccountSyncEventCursorIoAF18A == e7.a.f15033i ? objM6096getAccountSyncEventCursorIoAF18A : new x6.d0(objM6096getAccountSyncEventCursorIoAF18A);
    }
}
