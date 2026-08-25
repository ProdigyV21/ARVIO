package io.ktor.utils.io.core;

import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\n"}, d2 = {"Lio/ktor/utils/io/core/ByteOrder;", "", "nioOrder", "Ljava/nio/ByteOrder;", "(Ljava/lang/String;ILjava/nio/ByteOrder;)V", "getNioOrder", "()Ljava/nio/ByteOrder;", "BIG_ENDIAN", "LITTLE_ENDIAN", "Companion", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public enum ByteOrder {
    BIG_ENDIAN(java.nio.ByteOrder.BIG_ENDIAN),
    LITTLE_ENDIAN(java.nio.ByteOrder.LITTLE_ENDIAN);

    private final java.nio.ByteOrder nioOrder;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: native, reason: not valid java name */
    private static final ByteOrder f78native = ByteOrderJVMKt.orderOf(java.nio.ByteOrder.nativeOrder());

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lio/ktor/utils/io/core/ByteOrder$Companion;", "", "()V", "native", "Lio/ktor/utils/io/core/ByteOrder;", "nativeOrder", "of", "nioOrder", "Ljava/nio/ByteOrder;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final ByteOrder nativeOrder() {
            return ByteOrder.f78native;
        }

        public final ByteOrder of(java.nio.ByteOrder nioOrder) {
            return ByteOrderJVMKt.orderOf(nioOrder);
        }

        private Companion() {
        }
    }

    ByteOrder(java.nio.ByteOrder byteOrder) {
        this.nioOrder = byteOrder;
    }

    public final java.nio.ByteOrder getNioOrder() {
        return this.nioOrder;
    }
}
