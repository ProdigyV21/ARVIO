package io.ktor.utils.io.jvm.javaio;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import java.io.InputStream;
import java.io.OutputStream;
import ka.v1;
import kotlin.Metadata;
import vc.b;
import x6.i0;
import x6.s;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a\u001d\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001d\u0010\b\u001a\u00020\u0007*\u00020\u00062\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\b\u0010\t\"#\u0010\u0010\u001a\n \u000b*\u0004\u0018\u00010\n0\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013\"\u0014\u0010\u0014\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0013¨\u0006\u0015"}, d2 = {"Lio/ktor/utils/io/ByteReadChannel;", "Lka/v1;", "parent", "Ljava/io/InputStream;", "toInputStream", "(Lio/ktor/utils/io/ByteReadChannel;Lka/v1;)Ljava/io/InputStream;", "Lio/ktor/utils/io/ByteWriteChannel;", "Ljava/io/OutputStream;", "toOutputStream", "(Lio/ktor/utils/io/ByteWriteChannel;Lka/v1;)Ljava/io/OutputStream;", "Lvc/b;", "kotlin.jvm.PlatformType", "ADAPTER_LOGGER$delegate", "Lx6/s;", "getADAPTER_LOGGER", "()Lvc/b;", "ADAPTER_LOGGER", "", "CloseToken", "Ljava/lang/Object;", "FlushToken", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BlockingKt {
    private static final s ADAPTER_LOGGER$delegate = new i0(BlockingKt$ADAPTER_LOGGER$2.INSTANCE);
    private static final Object CloseToken = new Object();
    private static final Object FlushToken = new Object();

    /* JADX INFO: Access modifiers changed from: private */
    public static final b getADAPTER_LOGGER() {
        return (b) ADAPTER_LOGGER$delegate.getValue();
    }

    public static final InputStream toInputStream(ByteReadChannel byteReadChannel, v1 v1Var) {
        return new InputAdapter(v1Var, byteReadChannel);
    }

    public static /* synthetic */ InputStream toInputStream$default(ByteReadChannel byteReadChannel, v1 v1Var, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            v1Var = null;
        }
        return toInputStream(byteReadChannel, v1Var);
    }

    public static final OutputStream toOutputStream(ByteWriteChannel byteWriteChannel, v1 v1Var) {
        return new OutputAdapter(v1Var, byteWriteChannel);
    }

    public static /* synthetic */ OutputStream toOutputStream$default(ByteWriteChannel byteWriteChannel, v1 v1Var, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            v1Var = null;
        }
        return toOutputStream(byteWriteChannel, v1Var);
    }
}
