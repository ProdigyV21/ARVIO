package io.ktor.utils.io.concurrent;

import kotlin.Metadata;
import r7.a;
import u7.c;
import u7.d;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a)\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u0000H\u0007¢\u0006\u0004\b\u0004\u0010\u0005\u001a/\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0006\"\b\b\u0000\u0010\u0000*\u00020\u00032\u0006\u0010\u0001\u001a\u00028\u0000H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a3\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00028\u00000\u0006\"\b\b\u0000\u0010\u0000*\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0007¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"T", "value", "Lu7/d;", "", "shared", "(Ljava/lang/Object;)Lu7/d;", "Lu7/c;", "threadLocal", "(Ljava/lang/Object;)Lu7/c;", "Lkotlin/Function0;", "function", "sharedLazy", "(Lr7/a;)Lu7/c;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SharedKt {
    @e
    public static final <T> d<Object, T> shared(T t2) {
        throw new IllegalStateException("Obsolete in new memory model");
    }

    @e
    public static final <T> c<Object, T> sharedLazy(a<? extends T> aVar) {
        throw new IllegalStateException("Obsolete in new memory model");
    }

    @e
    public static final <T> c<Object, T> threadLocal(T t2) {
        throw new IllegalStateException("Obsolete in new memory model");
    }
}
