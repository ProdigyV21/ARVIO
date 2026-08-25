package io.ktor.utils.io.pool;

import kotlin.Metadata;
import r7.l;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aA\u0010\u0006\u001a\u00028\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u00028\u00000\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001aA\u0010\b\u001a\u00028\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u00028\u00000\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0086\bø\u0001\u0000¢\u0006\u0004\b\b\u0010\u0007\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"", "T", "R", "Lio/ktor/utils/io/pool/ObjectPool;", "Lkotlin/Function1;", "block", "useBorrowed", "(Lio/ktor/utils/io/pool/ObjectPool;Lr7/l;)Ljava/lang/Object;", "useInstance", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PoolKt {
    @e
    public static final <T, R> R useBorrowed(ObjectPool<T> objectPool, l<? super T, ? extends R> lVar) {
        T tBorrow = objectPool.borrow();
        try {
            return (R) lVar.invoke(tBorrow);
        } finally {
            objectPool.recycle(tBorrow);
        }
    }

    public static final <T, R> R useInstance(ObjectPool<T> objectPool, l<? super T, ? extends R> lVar) {
        T tBorrow = objectPool.borrow();
        try {
            return (R) lVar.invoke(tBorrow);
        } finally {
            objectPool.recycle(tBorrow);
        }
    }
}
