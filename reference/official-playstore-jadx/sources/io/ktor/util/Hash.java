package io.ktor.util;

import kotlin.Metadata;
import kotlin.collections.r;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006\"\u00020\u0001¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lio/ktor/util/Hash;", "", "()V", "combine", "", "objects", "", "([Ljava/lang/Object;)I", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Hash {
    public static final Hash INSTANCE = new Hash();

    private Hash() {
    }

    public final int combine(Object... objects) {
        return r.o0(objects).hashCode();
    }
}
