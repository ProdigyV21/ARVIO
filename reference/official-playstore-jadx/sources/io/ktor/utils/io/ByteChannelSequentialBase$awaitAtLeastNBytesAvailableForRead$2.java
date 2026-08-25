package io.ktor.utils.io;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForRead$2 extends r implements a<Boolean> {
    final /* synthetic */ int $count;
    final /* synthetic */ ByteChannelSequentialBase this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForRead$2(ByteChannelSequentialBase byteChannelSequentialBase, int i10) {
        super(0);
        this.this$0 = byteChannelSequentialBase;
        this.$count = i10;
    }

    @Override // r7.a
    public final Boolean invoke() {
        return Boolean.valueOf(this.this$0.get_availableForRead() < this.$count && !this.this$0.isClosedForRead());
    }
}
