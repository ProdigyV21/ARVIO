package xb;

import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u00012\u00020\u0002\u0082\u0001\u0002\u0003\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Lxb/k;", "Lxb/m0;", "Ljava/nio/channels/WritableByteChannel;", "Lxb/j;", "Lxb/h0;", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface k extends m0, WritableByteChannel {
    k H(String str);

    k U(m mVar);

    k Y(long j10);

    @Override // xb.m0, java.io.Flushable
    void flush();

    j h();

    k p0(int i10, byte[] bArr);

    k q();

    k v0(long j10);

    k write(byte[] bArr);

    k writeByte(int i10);

    k writeInt(int i10);

    k writeShort(int i10);

    long z0(o0 o0Var);
}
