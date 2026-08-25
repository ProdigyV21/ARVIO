package io.ktor.util.internal;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0006\u001a\u0004\u0018\u00010\u00012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H&¢\u0006\u0004\b\u0006\u0010\u0007J%\u0010\n\u001a\u00020\t2\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0001H&¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/util/internal/AtomicDesc;", "", "<init>", "()V", "Lio/ktor/util/internal/AtomicOp;", "op", "prepare", "(Lio/ktor/util/internal/AtomicOp;)Ljava/lang/Object;", "failure", "Lx6/t0;", "complete", "(Lio/ktor/util/internal/AtomicOp;Ljava/lang/Object;)V", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class AtomicDesc {
    public abstract void complete(AtomicOp<?> op, Object failure);

    public abstract Object prepare(AtomicOp<?> op);
}
