package io.ktor.client.request;

import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteChannelKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/utils/io/ByteChannel;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ClientUpgradeContent$content$2 extends r implements a<ByteChannel> {
    public static final ClientUpgradeContent$content$2 INSTANCE = new ClientUpgradeContent$content$2();

    public ClientUpgradeContent$content$2() {
        super(0);
    }

    @Override // r7.a
    public final ByteChannel invoke() {
        return ByteChannelKt.ByteChannel$default(false, 1, null);
    }
}
