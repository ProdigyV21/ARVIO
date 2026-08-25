package xb;

import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u00012\u00020\u0002\u0082\u0001\u0002\u0003\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Lxb/l;", "Lxb/o0;", "Ljava/nio/channels/ReadableByteChannel;", "Lxb/j;", "Lxb/i0;", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface l extends o0, ReadableByteChannel {
    long A(byte b10, long j10, long j11);

    String B(long j10);

    long D(k kVar);

    long L0();

    String Q();

    void S(j jVar, long j10);

    void X(long j10);

    m c0(long j10);

    int d0(e0 e0Var);

    j h();

    byte[] i0();

    InputStream inputStream();

    i0 peek();

    String r0(Charset charset);

    byte readByte();

    void readFully(byte[] bArr);

    int readInt();

    long readLong();

    short readShort();

    boolean request(long j10);

    boolean s0(long j10, m mVar);

    void skip(long j10);

    m x0();
}
