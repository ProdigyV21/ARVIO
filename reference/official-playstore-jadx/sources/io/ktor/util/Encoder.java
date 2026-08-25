package io.ktor.util;

import io.ktor.utils.io.ByteReadChannel;
import ka.k0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u001b\u0010\u0005\u001a\u00020\u0003*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H&¢\u0006\u0004\b\u0005\u0010\u0006J\u001b\u0010\u0007\u001a\u00020\u0003*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H&¢\u0006\u0004\b\u0007\u0010\u0006¨\u0006\b"}, d2 = {"Lio/ktor/util/Encoder;", "", "Lka/k0;", "Lio/ktor/utils/io/ByteReadChannel;", "source", "encode", "(Lka/k0;Lio/ktor/utils/io/ByteReadChannel;)Lio/ktor/utils/io/ByteReadChannel;", "decode", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Encoder {
    ByteReadChannel decode(k0 k0Var, ByteReadChannel byteReadChannel);

    ByteReadChannel encode(k0 k0Var, ByteReadChannel byteReadChannel);
}
