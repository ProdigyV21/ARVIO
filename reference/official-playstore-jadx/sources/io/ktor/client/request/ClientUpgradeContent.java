package io.ktor.client.request;

import d7.d;
import e7.a;
import io.ktor.http.Headers;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.InternalAPI;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteReadChannelKt;
import io.ktor.utils.io.ByteWriteChannel;
import kotlin.Metadata;
import x6.i0;
import x6.s;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@InternalAPI
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH&¢\u0006\u0004\b\u000b\u0010\fR\u001b\u0010\u0012\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lio/ktor/client/request/ClientUpgradeContent;", "Lio/ktor/http/content/OutgoingContent$NoContent;", "<init>", "()V", "Lio/ktor/utils/io/ByteWriteChannel;", "output", "Lx6/t0;", "pipeTo", "(Lio/ktor/utils/io/ByteWriteChannel;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/http/Headers;", "headers", "verify", "(Lio/ktor/http/Headers;)V", "Lio/ktor/utils/io/ByteChannel;", "content$delegate", "Lx6/s;", "getContent", "()Lio/ktor/utils/io/ByteChannel;", "content", "getOutput", "()Lio/ktor/utils/io/ByteWriteChannel;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class ClientUpgradeContent extends OutgoingContent.NoContent {

    /* JADX INFO: renamed from: content$delegate, reason: from kotlin metadata */
    private final s content = new i0(ClientUpgradeContent$content$2.INSTANCE);

    private final ByteChannel getContent() {
        return (ByteChannel) this.content.getValue();
    }

    public final ByteWriteChannel getOutput() {
        return getContent();
    }

    public final Object pipeTo(ByteWriteChannel byteWriteChannel, d<? super t0> dVar) {
        Object objCopyAndClose$default = ByteReadChannelKt.copyAndClose$default(getContent(), byteWriteChannel, 0L, dVar, 2, null);
        return objCopyAndClose$default == a.f15033i ? objCopyAndClose$default : t0.f22605a;
    }

    public abstract void verify(Headers headers);
}
