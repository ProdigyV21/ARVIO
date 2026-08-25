package android.graphics;

import kotlin.Metadata;
import kotlin.jvm.internal.h;
import v1.i;
import xb.j;
import xb.m;
import xb.o0;
import xb.v;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0000\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000e\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0013\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0013\u0010\u000fR\u0014\u0010\u0014\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Lcoil/decode/FrameDelayRewritingSource;", "Lxb/v;", "Lxb/o0;", "delegate", "<init>", "(Lxb/o0;)V", "Lxb/m;", "bytes", "", "indexOf", "(Lxb/m;)J", "Lxb/j;", "sink", "byteCount", "write", "(Lxb/j;J)J", "", "request", "(J)Z", "read", "buffer", "Lxb/j;", "Companion", "coil-gif_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class FrameDelayRewritingSource extends v {
    private static final int DEFAULT_FRAME_DELAY = 10;
    private static final int FRAME_DELAY_START_MARKER_SIZE_BYTES = 4;
    private static final int MINIMUM_FRAME_DELAY = 2;
    private final j buffer;
    private static final Companion Companion = new Companion(null);
    private static final m FRAME_DELAY_START_MARKER = i.f("0021F904");

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000b\u0010\u0006¨\u0006\f"}, d2 = {"Lcoil/decode/FrameDelayRewritingSource$Companion;", "", "<init>", "()V", "", "DEFAULT_FRAME_DELAY", "I", "Lxb/m;", "FRAME_DELAY_START_MARKER", "Lxb/m;", "FRAME_DELAY_START_MARKER_SIZE_BYTES", "MINIMUM_FRAME_DELAY", "coil-gif_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        private Companion() {
        }
    }

    public FrameDelayRewritingSource(o0 o0Var) {
        super(o0Var);
        this.buffer = new j();
    }

    private final long indexOf(m bytes) {
        long jA = -1;
        while (true) {
            jA = this.buffer.A(bytes.m(0), 1 + jA, Long.MAX_VALUE);
            if (jA == -1 || (request(bytes.f()) && this.buffer.s0(jA, bytes))) {
                break;
            }
        }
        return jA;
    }

    private final boolean request(long byteCount) {
        j jVar = this.buffer;
        long j10 = jVar.f22759l;
        if (j10 >= byteCount) {
            return true;
        }
        long j11 = byteCount - j10;
        return super.read(jVar, j11) == j11;
    }

    private final long write(j sink, long byteCount) {
        long j10 = this.buffer.read(sink, byteCount);
        if (j10 < 0) {
            return 0L;
        }
        return j10;
    }

    @Override // xb.v, xb.o0
    public long read(j sink, long byteCount) {
        request(byteCount);
        if (this.buffer.f22759l != 0) {
            long jWrite = 0;
            while (true) {
                long jIndexOf = indexOf(FRAME_DELAY_START_MARKER);
                if (jIndexOf == -1) {
                    break;
                }
                jWrite += write(sink, jIndexOf + ((long) 4));
                if (request(5L) && this.buffer.x(4L) == 0 && (((this.buffer.x(2L) & 255) << 8) | (this.buffer.x(1L) & 255)) < 2) {
                    sink.M0(this.buffer.x(0L));
                    sink.M0(10);
                    sink.M0(0);
                    this.buffer.skip(3L);
                }
            }
            if (jWrite < byteCount) {
                jWrite += write(sink, byteCount - jWrite);
            }
            if (jWrite != 0) {
                return jWrite;
            }
        } else if (byteCount == 0) {
            return 0L;
        }
        return -1L;
    }
}
