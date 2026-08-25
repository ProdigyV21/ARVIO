package io.ktor.util;

import io.ktor.utils.io.ByteReadChannel;
import ka.k0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u00020\u0005*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\t\u001a\u00020\u0005*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Lio/ktor/util/Identity;", "Lio/ktor/util/Encoder;", "<init>", "()V", "Lka/k0;", "Lio/ktor/utils/io/ByteReadChannel;", "source", "encode", "(Lka/k0;Lio/ktor/utils/io/ByteReadChannel;)Lio/ktor/utils/io/ByteReadChannel;", "decode", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Identity implements Encoder {
    public static final Identity INSTANCE = new Identity();

    private Identity() {
    }

    @Override // io.ktor.util.Encoder
    public ByteReadChannel decode(k0 k0Var, ByteReadChannel byteReadChannel) {
        return byteReadChannel;
    }

    @Override // io.ktor.util.Encoder
    public ByteReadChannel encode(k0 k0Var, ByteReadChannel byteReadChannel) {
        return byteReadChannel;
    }
}
