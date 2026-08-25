package io.ktor.client.request.forms;

import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.core.Input;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0002\f\rB\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n\u0082\u0001\u0002\u000e\u000f¨\u0006\u0010"}, d2 = {"Lio/ktor/client/request/forms/PreparedPart;", "", "headers", "", ContentDisposition.Parameters.Size, "", "([BLjava/lang/Long;)V", "getHeaders", "()[B", "getSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "ChannelPart", "InputPart", "Lio/ktor/client/request/forms/PreparedPart$ChannelPart;", "Lio/ktor/client/request/forms/PreparedPart$InputPart;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
abstract class PreparedPart {
    private final byte[] headers;
    private final Long size;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lio/ktor/client/request/forms/PreparedPart$ChannelPart;", "Lio/ktor/client/request/forms/PreparedPart;", "", "headers", "Lkotlin/Function0;", "Lio/ktor/utils/io/ByteReadChannel;", "provider", "", ContentDisposition.Parameters.Size, "<init>", "([BLr7/a;Ljava/lang/Long;)V", "Lr7/a;", "getProvider", "()Lr7/a;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ChannelPart extends PreparedPart {
        private final a<ByteReadChannel> provider;

        /* JADX WARN: Multi-variable type inference failed */
        public ChannelPart(byte[] bArr, a<? extends ByteReadChannel> aVar, Long l10) {
            super(bArr, l10, null);
            this.provider = aVar;
        }

        public final a<ByteReadChannel> getProvider() {
            return this.provider;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lio/ktor/client/request/forms/PreparedPart$InputPart;", "Lio/ktor/client/request/forms/PreparedPart;", "", "headers", "Lkotlin/Function0;", "Lio/ktor/utils/io/core/Input;", "provider", "", ContentDisposition.Parameters.Size, "<init>", "([BLr7/a;Ljava/lang/Long;)V", "Lr7/a;", "getProvider", "()Lr7/a;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class InputPart extends PreparedPart {
        private final a<Input> provider;

        /* JADX WARN: Multi-variable type inference failed */
        public InputPart(byte[] bArr, a<? extends Input> aVar, Long l10) {
            super(bArr, l10, null);
            this.provider = aVar;
        }

        public final a<Input> getProvider() {
            return this.provider;
        }
    }

    public /* synthetic */ PreparedPart(byte[] bArr, Long l10, h hVar) {
        this(bArr, l10);
    }

    public final byte[] getHeaders() {
        return this.headers;
    }

    public final Long getSize() {
        return this.size;
    }

    private PreparedPart(byte[] bArr, Long l10) {
        this.headers = bArr;
        this.size = l10;
    }
}
