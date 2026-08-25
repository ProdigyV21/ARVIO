package io.ktor.utils.io;

import androidx.media3.exoplayer.upstream.CmcdData;
import d7.d;
import e7.a;
import f7.c;
import f7.e;
import f7.j;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.charsets.TooLongLineException;
import io.ktor.utils.io.charsets.UTFKt;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.BufferPrimitivesJvmKt;
import io.ktor.utils.io.core.BufferUtilsJvmKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.OutputArraysJVMKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import io.ktor.utils.io.internal.CancellableReusableContinuation;
import io.ktor.utils.io.internal.ClosedElement;
import io.ktor.utils.io.internal.FailedLookAhead;
import io.ktor.utils.io.internal.JoiningState;
import io.ktor.utils.io.internal.ObjectPoolKt;
import io.ktor.utils.io.internal.ReadSessionImpl;
import io.ktor.utils.io.internal.ReadWriteBufferState;
import io.ktor.utils.io.internal.RingBufferCapacity;
import io.ktor.utils.io.internal.TerminatedLookAhead;
import io.ktor.utils.io.internal.WriteSessionImpl;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import ka.k;
import ka.v1;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.i0;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.r;
import kotlinx.coroutines.channels.ClosedReceiveChannelException;
import r7.l;
import r7.p;
import x6.c0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Ð\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0013\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0004\n\u0002\b2\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0010\u0018\u0000 ø\u00022\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0002ø\u0002B)\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fB\u0011\b\u0016\u0012\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u000e\u0010\u0012J\u000f\u0010\u0016\u001a\u00020\u0013H\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u001a\u001a\u0004\u0018\u00010\u0017H\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001bH\u0017¢\u0006\u0004\b\u001e\u0010\u001fJ\u0019\u0010\"\u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010 H\u0016¢\u0006\u0004\b\"\u0010#J\u0019\u0010$\u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010 H\u0016¢\u0006\u0004\b$\u0010#J\u000f\u0010%\u001a\u00020\u001dH\u0016¢\u0006\u0004\b%\u0010&J\u001f\u0010+\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\u00102\u0006\u0010(\u001a\u00020\fH\u0000¢\u0006\u0004\b)\u0010*J\u0011\u0010.\u001a\u0004\u0018\u00010\u0010H\u0000¢\u0006\u0004\b,\u0010-J\u000f\u00100\u001a\u00020\u001dH\u0000¢\u0006\u0004\b/\u0010&J\u000f\u00103\u001a\u00020\u0007H\u0000¢\u0006\u0004\b1\u00102J+\u00108\u001a\u00020\u001d2\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\fH\u0086@ø\u0001\u0000¢\u0006\u0004\b8\u00109J\u001b\u00108\u001a\u00020\f2\u0006\u00105\u001a\u00020\u0010H\u0086@ø\u0001\u0000¢\u0006\u0004\b8\u0010:J#\u00108\u001a\u00020\u001d2\u0006\u00105\u001a\u00020;2\u0006\u0010<\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0004\b8\u0010=J+\u0010A\u001a\u00020\f2\u0006\u0010>\u001a\u00020\f2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u001d0?H\u0016¢\u0006\u0004\bA\u0010BJ+\u0010A\u001a\u00020\f2\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0004\bA\u00109J\u001b\u0010A\u001a\u00020\f2\u0006\u00105\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0004\bA\u0010:J\u001b\u0010A\u001a\u00020\f2\u0006\u00105\u001a\u00020;H\u0096@ø\u0001\u0000¢\u0006\u0004\bA\u0010CJ\u001b\u0010F\u001a\u00020E2\u0006\u0010D\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0004\bF\u0010GJ\u0013\u0010H\u001a\u00020\u0007H\u0086@ø\u0001\u0000¢\u0006\u0004\bH\u0010IJ\u0013\u0010K\u001a\u00020JH\u0086@ø\u0001\u0000¢\u0006\u0004\bK\u0010IJ\u0013\u0010M\u001a\u00020LH\u0086@ø\u0001\u0000¢\u0006\u0004\bM\u0010IJ\u0013\u0010N\u001a\u00020\fH\u0086@ø\u0001\u0000¢\u0006\u0004\bN\u0010IJ\u0013\u0010P\u001a\u00020OH\u0086@ø\u0001\u0000¢\u0006\u0004\bP\u0010IJ\u0013\u0010R\u001a\u00020QH\u0086@ø\u0001\u0000¢\u0006\u0004\bR\u0010IJ\u0013\u0010T\u001a\u00020SH\u0086@ø\u0001\u0000¢\u0006\u0004\bT\u0010IJ'\u0010Z\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\u00102\u0006\u0010V\u001a\u00020U2\u0006\u0010W\u001a\u00020\fH\u0000¢\u0006\u0004\bX\u0010YJ\u000f\u0010]\u001a\u00020\u0000H\u0000¢\u0006\u0004\b[\u0010\\J\u001b\u0010_\u001a\u00020\u001d2\u0006\u0010^\u001a\u00020JH\u0096@ø\u0001\u0000¢\u0006\u0004\b_\u0010`J\u001b\u0010b\u001a\u00020\u001d2\u0006\u0010a\u001a\u00020LH\u0096@ø\u0001\u0000¢\u0006\u0004\bb\u0010cJ\u001b\u0010e\u001a\u00020\u001d2\u0006\u0010d\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0004\be\u0010GJ\u001b\u0010g\u001a\u00020\u001d2\u0006\u0010f\u001a\u00020OH\u0096@ø\u0001\u0000¢\u0006\u0004\bg\u0010hJ\u001b\u0010j\u001a\u00020\u001d2\u0006\u0010i\u001a\u00020SH\u0096@ø\u0001\u0000¢\u0006\u0004\bj\u0010kJ\u001b\u0010m\u001a\u00020\u001d2\u0006\u0010l\u001a\u00020QH\u0096@ø\u0001\u0000¢\u0006\u0004\bm\u0010nJ\u0013\u0010o\u001a\u00020\u001dH\u0096@ø\u0001\u0000¢\u0006\u0004\bo\u0010IJ\u001b\u0010q\u001a\u00020\f2\u0006\u0010p\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0004\bq\u0010:J\u001b\u0010q\u001a\u00020\f2\u0006\u0010p\u001a\u00020;H\u0096@ø\u0001\u0000¢\u0006\u0004\bq\u0010CJ\u001b\u0010r\u001a\u00020\u001d2\u0006\u0010p\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0004\br\u0010:J\u001b\u0010r\u001a\u00020\u001d2\u0006\u0010p\u001a\u00020sH\u0096@ø\u0001\u0000¢\u0006\u0004\br\u0010tJ1\u0010r\u001a\u00020\u001d2\u0006\u0010v\u001a\u00020u2\u0006\u0010w\u001a\u00020\f2\u0006\u0010x\u001a\u00020\fH\u0096@ø\u0001\u0001ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\by\u0010zJ#\u0010~\u001a\u00020\u001d2\u0006\u0010p\u001a\u00020\u00002\u0006\u0010{\u001a\u00020\u0007H\u0080@ø\u0001\u0000¢\u0006\u0004\b|\u0010}J1\u0010\u0083\u0001\u001a\u00020O2\u0006\u0010p\u001a\u00020\u00002\u0006\u0010\u007f\u001a\u00020O2\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u0017H\u0080@ø\u0001\u0000¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J+\u0010r\u001a\u00020\u001d2\u0006\u0010p\u001a\u0002042\u0006\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0004\br\u00109J+\u0010q\u001a\u00020\f2\u0006\u0010p\u001a\u0002042\u0006\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0004\bq\u00109J+\u0010q\u001a\u00020\f2\u0006\u0010>\u001a\u00020\f2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u001d0?H\u0016¢\u0006\u0004\bq\u0010BJ2\u0010\u0084\u0001\u001a\u00020\u001d2\u0006\u0010>\u001a\u00020\f2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u001d0?H\u0096@ø\u0001\u0000¢\u0006\u0006\b\u0084\u0001\u0010\u0085\u0001J*\u0010\u0086\u0001\u001a\u00020\u001d2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00070?H\u0096@ø\u0001\u0000¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J\u0013\u0010\u0089\u0001\u001a\u00030\u0088\u0001H\u0016¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001J\u0011\u0010\u008b\u0001\u001a\u00020\u001dH\u0016¢\u0006\u0005\b\u008b\u0001\u0010&J\u0015\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008c\u0001H\u0016¢\u0006\u0006\b\u008d\u0001\u0010\u008e\u0001J\u001b\u0010\u0090\u0001\u001a\u00020\u001d2\u0007\u0010\u008f\u0001\u001a\u00020\fH\u0016¢\u0006\u0006\b\u0090\u0001\u0010\u0091\u0001J.\u0010\u0095\u0001\u001a\u00020\u001d2\u001a\u0010\u0094\u0001\u001a\u0015\u0012\u0005\u0012\u00030\u0092\u0001\u0012\u0004\u0012\u00020\u001d0?¢\u0006\u0003\b\u0093\u0001H\u0017¢\u0006\u0006\b\u0095\u0001\u0010\u0096\u0001JE\u0010\u009a\u0001\u001a\u00020\u001d2-\u0010\u0094\u0001\u001a(\b\u0001\u0012\u0005\u0012\u00030\u0088\u0001\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\u001d0\u0098\u0001\u0012\u0007\u0012\u0005\u0018\u00010\u0099\u00010\u0097\u0001¢\u0006\u0003\b\u0093\u0001H\u0097@ø\u0001\u0000¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001J3\u0010\u009c\u0001\u001a\u00020\u001d2\u0006\u0010>\u001a\u00020\f2\u0013\u0010\u0094\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u001d0?H\u0096@ø\u0001\u0000¢\u0006\u0006\b\u009c\u0001\u0010\u0085\u0001J\u001e\u0010\u009e\u0001\u001a\u00020O2\u0007\u0010\u009d\u0001\u001a\u00020OH\u0096@ø\u0001\u0000¢\u0006\u0005\b\u009e\u0001\u0010hJ\u001f\u0010 \u0001\u001a\u00020\u001d2\u0007\u0010\u009f\u0001\u001a\u00020EH\u0096@ø\u0001\u0000¢\u0006\u0006\b \u0001\u0010¡\u0001J5\u0010¥\u0001\u001a\u00028\u0000\"\u0005\b\u0000\u0010¢\u00012\u001a\u0010¤\u0001\u001a\u0015\u0012\u0005\u0012\u00030£\u0001\u0012\u0004\u0012\u00028\u00000?¢\u0006\u0003\b\u0093\u0001H\u0017¢\u0006\u0006\b¥\u0001\u0010¦\u0001JK\u0010§\u0001\u001a\u00028\u0000\"\u0005\b\u0000\u0010¢\u00012,\u0010¤\u0001\u001a'\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\u000b\u0012\t\u0012\u0004\u0012\u00028\u00000\u0098\u0001\u0012\u0007\u0012\u0005\u0018\u00010\u0099\u00010\u0097\u0001¢\u0006\u0003\b\u0093\u0001H\u0097@ø\u0001\u0000¢\u0006\u0006\b§\u0001\u0010\u009b\u0001JE\u0010¨\u0001\u001a\u00020\u001d2-\u0010¤\u0001\u001a(\b\u0001\u0012\u0005\u0012\u00030\u008c\u0001\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\u001d0\u0098\u0001\u0012\u0007\u0012\u0005\u0018\u00010\u0099\u00010\u0097\u0001¢\u0006\u0003\b\u0093\u0001H\u0097@ø\u0001\u0000¢\u0006\u0006\b¨\u0001\u0010\u009b\u0001J\u001a\u0010©\u0001\u001a\u00020\u001d2\u0006\u0010<\u001a\u00020\fH\u0016¢\u0006\u0006\b©\u0001\u0010\u0091\u0001J\u001d\u0010ª\u0001\u001a\u00020\u00072\u0006\u0010<\u001a\u00020\fH\u0086@ø\u0001\u0000¢\u0006\u0005\bª\u0001\u0010GJ&\u0010\u00ad\u0001\u001a\u0004\u0018\u00010\u00102\u0007\u0010«\u0001\u001a\u00020\f2\u0007\u0010¬\u0001\u001a\u00020\fH\u0016¢\u0006\u0006\b\u00ad\u0001\u0010®\u0001J8\u0010³\u0001\u001a\u00020\u0007\"\u000f\b\u0000\u0010±\u0001*\b0¯\u0001j\u0003`°\u00012\u0007\u0010²\u0001\u001a\u00028\u00002\u0006\u0010\u007f\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0006\b³\u0001\u0010´\u0001J \u0010¶\u0001\u001a\u0005\u0018\u00010µ\u00012\u0006\u0010\u007f\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0005\b¶\u0001\u0010GJ\u001d\u0010·\u0001\u001a\u00020E2\u0006\u0010\u007f\u001a\u00020OH\u0096@ø\u0001\u0000¢\u0006\u0005\b·\u0001\u0010hJ\u0015\u0010¸\u0001\u001a\u00020\u001dH\u0096@ø\u0001\u0000¢\u0006\u0005\b¸\u0001\u0010IJ\u001d\u0010º\u0001\u001a\u00020\u001d2\u0006\u0010D\u001a\u00020\fH\u0080@ø\u0001\u0000¢\u0006\u0005\b¹\u0001\u0010GJG\u0010¿\u0001\u001a\u00020O2\u0007\u0010»\u0001\u001a\u00020u2\u0007\u0010¼\u0001\u001a\u00020O2\u0006\u00106\u001a\u00020O2\u0006\u0010>\u001a\u00020O2\u0007\u0010\u009d\u0001\u001a\u00020OH\u0096@ø\u0001\u0001ø\u0001\u0000ø\u0001\u0000¢\u0006\u0006\b½\u0001\u0010¾\u0001J\u0013\u0010À\u0001\u001a\u00030µ\u0001H\u0016¢\u0006\u0006\bÀ\u0001\u0010Á\u0001J\u001b\u0010Ã\u0001\u001a\u00020\u001d2\u0007\u0010Â\u0001\u001a\u00020\fH\u0002¢\u0006\u0006\bÃ\u0001\u0010\u0091\u0001J(\u0010Æ\u0001\u001a\u00020\u001d*\u00020\u00102\u0007\u0010Ä\u0001\u001a\u00020\f2\u0007\u0010Å\u0001\u001a\u00020\fH\u0002¢\u0006\u0006\bÆ\u0001\u0010Ç\u0001J\u0013\u0010È\u0001\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0005\bÈ\u0001\u0010-J\u0011\u0010É\u0001\u001a\u00020\u001dH\u0002¢\u0006\u0005\bÉ\u0001\u0010&J#\u0010Ë\u0001\u001a\u00020\u00172\u0007\u0010Ê\u0001\u001a\u00020\u00002\u0006\u0010{\u001a\u00020\u0007H\u0002¢\u0006\u0006\bË\u0001\u0010Ì\u0001J\u001b\u0010Í\u0001\u001a\u00020\u00072\u0007\u0010\u0080\u0001\u001a\u00020\u0017H\u0002¢\u0006\u0006\bÍ\u0001\u0010Î\u0001J\u001b\u0010Ð\u0001\u001a\u00020\u00072\u0007\u0010Ï\u0001\u001a\u00020\u0007H\u0002¢\u0006\u0006\bÐ\u0001\u0010Ñ\u0001J\u001f\u0010Ó\u0001\u001a\u00020\f*\u00020\u00102\u0007\u0010Ò\u0001\u001a\u00020\fH\u0002¢\u0006\u0006\bÓ\u0001\u0010Ô\u0001J:\u0010Ö\u0001\u001a\u00020\u001d2%\u0010@\u001a!\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020U\u0012\u0004\u0012\u00020\u001d0Õ\u0001¢\u0006\u0003\b\u0093\u0001H\u0082\b¢\u0006\u0006\bÖ\u0001\u0010×\u0001J4\u0010Ø\u0001\u001a\u00020\u00072\u001f\u0010@\u001a\u001b\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020U\u0012\u0004\u0012\u00020\u00070\u0097\u0001¢\u0006\u0003\b\u0093\u0001H\u0082\b¢\u0006\u0006\bØ\u0001\u0010Ù\u0001J\u001a\u0010Ú\u0001\u001a\u00020\f2\u0006\u00105\u001a\u00020\u0010H\u0002¢\u0006\u0006\bÚ\u0001\u0010Û\u0001J0\u0010Ú\u0001\u001a\u00020\f2\u0006\u00105\u001a\u00020s2\t\b\u0002\u0010©\u0001\u001a\u00020\f2\t\b\u0002\u0010\u009d\u0001\u001a\u00020\fH\u0002¢\u0006\u0006\bÚ\u0001\u0010Ü\u0001J*\u0010Ú\u0001\u001a\u00020\f2\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\fH\u0002¢\u0006\u0006\bÚ\u0001\u0010Ý\u0001J'\u0010ß\u0001\u001a\u00020\f2\u0006\u00105\u001a\u00020\u00102\u0007\u0010Þ\u0001\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0006\bß\u0001\u0010à\u0001J%\u0010ß\u0001\u001a\u00020\u001d2\u0006\u00105\u001a\u00020;2\u0006\u0010<\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0005\bß\u0001\u0010=J-\u0010ß\u0001\u001a\u00020\u001d2\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0005\bß\u0001\u00109J-\u0010á\u0001\u001a\u00020\f2\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0005\bá\u0001\u00109J\u001d\u0010á\u0001\u001a\u00020\f2\u0006\u00105\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0005\bá\u0001\u0010:J\u001d\u0010á\u0001\u001a\u00020\f2\u0006\u00105\u001a\u00020;H\u0082@ø\u0001\u0000¢\u0006\u0005\bá\u0001\u0010CJ0\u0010ä\u0001\u001a\u00020E2\u0006\u0010D\u001a\u00020\f2\b\u0010ã\u0001\u001a\u00030â\u00012\u0006\u0010'\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0006\bä\u0001\u0010å\u0001JE\u0010é\u0001\u001a\u00028\u0000\"\n\b\u0000\u0010ç\u0001*\u00030æ\u00012\u0006\u0010D\u001a\u00020\f2\u0019\u0010è\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00028\u00000?¢\u0006\u0003\b\u0093\u0001H\u0082Hø\u0001\u0000¢\u0006\u0006\bé\u0001\u0010\u0085\u0001J\u001d\u0010ê\u0001\u001a\u00020\u001d*\u00020\u00102\u0006\u0010<\u001a\u00020\fH\u0002¢\u0006\u0005\bê\u0001\u0010*J\u0015\u0010ë\u0001\u001a\u00020\u001d*\u00020\u0010H\u0002¢\u0006\u0005\bë\u0001\u0010\u0012J%\u0010ì\u0001\u001a\u00020\u001d*\u00020\u00102\u0006\u0010V\u001a\u00020U2\u0006\u0010W\u001a\u00020\fH\u0002¢\u0006\u0005\bì\u0001\u0010YJ%\u0010í\u0001\u001a\u00020\u001d*\u00020\u00102\u0006\u0010V\u001a\u00020U2\u0006\u0010W\u001a\u00020\fH\u0002¢\u0006\u0005\bí\u0001\u0010YJ&\u0010ð\u0001\u001a\u0004\u0018\u00010\u00002\u0007\u0010î\u0001\u001a\u00020\u00002\u0007\u0010ï\u0001\u001a\u00020\u0017H\u0002¢\u0006\u0006\bð\u0001\u0010ñ\u0001J9\u0010ò\u0001\u001a\u00020\u001d2\u0007\u0010\u0080\u0001\u001a\u00020\u00172\u0018\u0010@\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001d0?¢\u0006\u0003\b\u0093\u0001H\u0082Hø\u0001\u0000¢\u0006\u0006\bò\u0001\u0010ó\u0001JT\u0010ö\u0001\u001a\u00020\u001d2\u0006\u0010D\u001a\u00020\f2\u0019\u0010ô\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001d0?¢\u0006\u0003\b\u0093\u00012\u0019\u0010õ\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u001d0?¢\u0006\u0003\b\u0093\u0001H\u0082Hø\u0001\u0000¢\u0006\u0006\bö\u0001\u0010÷\u0001JB\u0010ù\u0001\u001a\u00020\u0007*\u00020\u00102\u0006\u0010D\u001a\u00020\f2\u0006\u0010V\u001a\u00020U2\u0019\u0010ø\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u001d0?¢\u0006\u0003\b\u0093\u0001H\u0082\b¢\u0006\u0006\bù\u0001\u0010ú\u0001JF\u0010û\u0001\u001a\u00020\u001d2\u0006\u0010D\u001a\u00020\f2\u0006\u0010'\u001a\u00020\u00102\u0006\u0010V\u001a\u00020U2\u0019\u0010ø\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u001d0?¢\u0006\u0003\b\u0093\u0001H\u0082\b¢\u0006\u0006\bû\u0001\u0010ü\u0001J`\u0010ý\u0001\u001a\u00020\u001d*\u00020\u00102\u0006\u0010D\u001a\u00020\f2\u0006\u0010V\u001a\u00020U2\u0019\u0010ô\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001d0?¢\u0006\u0003\b\u0093\u00012\u0019\u0010õ\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u001d0?¢\u0006\u0003\b\u0093\u0001H\u0082Hø\u0001\u0000¢\u0006\u0006\bý\u0001\u0010þ\u0001J1\u0010ÿ\u0001\u001a\u00020\u001d2\u0019\u0010ô\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001d0?¢\u0006\u0003\b\u0093\u0001H\u0082Hø\u0001\u0000¢\u0006\u0006\bÿ\u0001\u0010\u0087\u0001J\u001d\u0010\u0080\u0002\u001a\u00020\f2\u0006\u0010p\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0005\b\u0080\u0002\u0010:J\u001d\u0010\u0080\u0002\u001a\u00020\f2\u0006\u0010p\u001a\u00020;H\u0082@ø\u0001\u0000¢\u0006\u0005\b\u0080\u0002\u0010CJ\u001d\u0010\u0081\u0002\u001a\u00020\u001d2\u0006\u0010p\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0005\b\u0081\u0002\u0010:J\u001d\u0010\u0081\u0002\u001a\u00020\u001d2\u0006\u0010p\u001a\u00020sH\u0082@ø\u0001\u0000¢\u0006\u0005\b\u0081\u0002\u0010tJ\u0015\u0010\u0082\u0002\u001a\u00020\u001dH\u0082@ø\u0001\u0000¢\u0006\u0005\b\u0082\u0002\u0010IJ/\u0010\u0083\u0002\u001a\u00020\u001d2\u0006\u0010p\u001a\u00020\u00002\u0006\u0010{\u001a\u00020\u00072\u0007\u0010\u0080\u0001\u001a\u00020\u0017H\u0082@ø\u0001\u0000¢\u0006\u0006\b\u0083\u0002\u0010\u0084\u0002J\u001b\u0010\u0085\u0002\u001a\u00020\u001d2\u0007\u0010\u0080\u0001\u001a\u00020\u0017H\u0002¢\u0006\u0006\b\u0085\u0002\u0010\u0086\u0002J\u001a\u0010\u0087\u0002\u001a\u00020\f2\u0006\u0010p\u001a\u00020\u0010H\u0002¢\u0006\u0006\b\u0087\u0002\u0010Û\u0001J\u001a\u0010\u0087\u0002\u001a\u00020\f2\u0006\u0010p\u001a\u00020sH\u0002¢\u0006\u0006\b\u0087\u0002\u0010\u0088\u0002J*\u0010\u0087\u0002\u001a\u00020\f2\u0006\u0010p\u001a\u0002042\u0006\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\fH\u0002¢\u0006\u0006\b\u0087\u0002\u0010Ý\u0001J-\u0010\u0081\u0002\u001a\u00020\u001d2\u0006\u0010p\u001a\u0002042\u0006\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0005\b\u0081\u0002\u00109J-\u0010\u0089\u0002\u001a\u00020\f2\u0006\u0010p\u001a\u0002042\u0006\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0005\b\u0089\u0002\u00109J2\u0010\u008a\u0002\u001a\u00020\u001d2\u0006\u0010>\u001a\u00020\f2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u001d0?H\u0082@ø\u0001\u0000¢\u0006\u0006\b\u008a\u0002\u0010\u0085\u0001J&\u0010\u008b\u0002\u001a\u00020\u00072\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00070?H\u0002¢\u0006\u0006\b\u008b\u0002\u0010\u008c\u0002J*\u0010\u008d\u0002\u001a\u00020\u001d2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00070?H\u0082@ø\u0001\u0000¢\u0006\u0006\b\u008d\u0002\u0010\u0087\u0001J6\u0010\u008e\u0002\u001a\u00020\u00072\u0006\u00105\u001a\u00020\u00102\u0006\u0010V\u001a\u00020U2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00070?H\u0002¢\u0006\u0006\b\u008e\u0002\u0010\u008f\u0002J(\u0010\u0091\u0002\u001a\u00020O2\u0007\u0010\u0090\u0002\u001a\u00020O2\u0007\u0010\u009d\u0001\u001a\u00020OH\u0082@ø\u0001\u0000¢\u0006\u0006\b\u0091\u0002\u0010\u0092\u0002J2\u0010\u0093\u0002\u001a\u00020\u001d2\u0006\u0010>\u001a\u00020\f2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u001d0?H\u0082@ø\u0001\u0000¢\u0006\u0006\b\u0093\u0002\u0010\u0085\u0001J\u001f\u0010\u0094\u0002\u001a\u00020\u001d2\u0007\u0010\u009f\u0001\u001a\u00020EH\u0082@ø\u0001\u0000¢\u0006\u0006\b\u0094\u0002\u0010¡\u0001J\u001b\u0010\u0095\u0002\u001a\u00020\f2\u0007\u0010\u009f\u0001\u001a\u00020EH\u0002¢\u0006\u0006\b\u0095\u0002\u0010\u0096\u0002J\u001d\u0010\u0097\u0002\u001a\u00020\u00072\u0006\u0010<\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0005\b\u0097\u0002\u0010GJ[\u0010\u009b\u0002\u001a\u00020\u00072\u0007\u0010\u0098\u0002\u001a\u00020\u00072=\u0010¤\u0001\u001a8\u0012\u0015\u0012\u00130\u0010¢\u0006\u000e\b\u0099\u0002\u0012\t\b\u009a\u0002\u0012\u0004\b\b('\u0012\u0016\u0012\u00140\u0007¢\u0006\u000f\b\u0099\u0002\u0012\n\b\u009a\u0002\u0012\u0005\b\b(\u0098\u0002\u0012\u0004\u0012\u00020\u00070\u0097\u0001H\u0082\b¢\u0006\u0006\b\u009b\u0002\u0010\u009c\u0002J\"\u0010\u009d\u0002\u001a\u00020\f2\u0006\u0010'\u001a\u00020\u00102\u0006\u0010V\u001a\u00020UH\u0002¢\u0006\u0006\b\u009d\u0002\u0010\u009e\u0002J-\u0010\u009f\u0002\u001a\u00020\u00072\r\u0010²\u0001\u001a\b0¯\u0001j\u0003`°\u00012\u0006\u0010\u007f\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0006\b\u009f\u0002\u0010´\u0001J-\u0010 \u0002\u001a\u00020\u00072\r\u0010²\u0001\u001a\b0¯\u0001j\u0003`°\u00012\u0006\u0010\u007f\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0006\b \u0002\u0010´\u0001J\u001a\u0010¡\u0002\u001a\u00020E2\u0006\u0010\u007f\u001a\u00020OH\u0002¢\u0006\u0006\b¡\u0002\u0010¢\u0002J\u001d\u0010£\u0002\u001a\u00020E2\u0006\u0010\u007f\u001a\u00020OH\u0082@ø\u0001\u0000¢\u0006\u0005\b£\u0002\u0010hJ\u0011\u0010¤\u0002\u001a\u00020\u001dH\u0002¢\u0006\u0005\b¤\u0002\u0010&J#\u0010¤\u0002\u001a\u00020\u001d2\u000e\u0010¦\u0002\u001a\t\u0012\u0004\u0012\u00020 0¥\u0002H\u0082\b¢\u0006\u0006\b¤\u0002\u0010§\u0002J\u0011\u0010¨\u0002\u001a\u00020\u001dH\u0002¢\u0006\u0005\b¨\u0002\u0010&J\u001c\u0010©\u0002\u001a\u00020\u001d2\b\u0010!\u001a\u0004\u0018\u00010 H\u0002¢\u0006\u0006\b©\u0002\u0010ª\u0002J\u001d\u0010«\u0002\u001a\u00020\u00072\u0006\u0010D\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0005\b«\u0002\u0010GJ\u001d\u0010¬\u0002\u001a\u00020\u00072\u0006\u0010D\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0005\b¬\u0002\u0010GJ\u001b\u0010\u00ad\u0002\u001a\u00020\u00072\u0006\u0010D\u001a\u00020\fH\u0082\b¢\u0006\u0006\b\u00ad\u0002\u0010®\u0002J*\u0010°\u0002\u001a\u00030\u0099\u00012\u0006\u0010D\u001a\u00020\f2\u000e\u0010¯\u0002\u001a\t\u0012\u0004\u0012\u00020\u00070\u0098\u0001H\u0002¢\u0006\u0005\b°\u0002\u0010GJ\u001d\u0010±\u0002\u001a\u00020\u00072\u0006\u0010D\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0005\b±\u0002\u0010GJ\u0011\u0010²\u0002\u001a\u00020\u0007H\u0002¢\u0006\u0005\b²\u0002\u00102J\u001a\u0010³\u0002\u001a\u00020\u00072\u0006\u0010D\u001a\u00020\fH\u0002¢\u0006\u0006\b³\u0002\u0010®\u0002J\u001d\u0010\u0089\u0002\u001a\u00020\u001d2\u0006\u0010D\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0005\b\u0089\u0002\u0010GJ*\u0010¶\u0002\u001a\u00020\u001d2\u0006\u0010D\u001a\u00020\f2\u000e\u0010µ\u0002\u001a\t\u0012\u0004\u0012\u00020\u001d0´\u0002H\u0002¢\u0006\u0006\b¶\u0002\u0010·\u0002J\u0012\u0010¸\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\b¸\u0002\u0010¹\u0002J\u001a\u0010º\u0002\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\nH\u0002¢\u0006\u0006\bº\u0002\u0010»\u0002R\u001c\u0010\b\u001a\u00020\u00078\u0016X\u0096\u0004¢\u0006\u000e\n\u0005\b\b\u0010¼\u0002\u001a\u0005\b½\u0002\u00102R\u001b\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u000b\u0010¾\u0002R\u001d\u0010\r\u001a\u00020\f8\u0000X\u0080\u0004¢\u0006\u000f\n\u0005\b\r\u0010¿\u0002\u001a\u0006\bÀ\u0002\u0010Á\u0002R\u001b\u0010ï\u0001\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bï\u0001\u0010Â\u0002R\u0019\u0010Ã\u0002\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÃ\u0002\u0010¿\u0002R\u0019\u0010Ä\u0002\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÄ\u0002\u0010¿\u0002R\u001b\u0010Å\u0002\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÅ\u0002\u0010Æ\u0002R2\u0010È\u0002\u001a\u00020O2\u0007\u0010Ç\u0002\u001a\u00020O8\u0016@PX\u0096\u000e¢\u0006\u0018\n\u0006\bÈ\u0002\u0010É\u0002\u001a\u0006\bÊ\u0002\u0010Ë\u0002\"\u0006\bÌ\u0002\u0010Í\u0002R2\u0010Î\u0002\u001a\u00020O2\u0007\u0010Ç\u0002\u001a\u00020O8\u0016@PX\u0096\u000e¢\u0006\u0018\n\u0006\bÎ\u0002\u0010É\u0002\u001a\u0006\bÏ\u0002\u0010Ë\u0002\"\u0006\bÐ\u0002\u0010Í\u0002R\u001f\u0010\u0095\u0001\u001a\u00030Ñ\u00028\u0002X\u0082\u0004¢\u0006\u000f\n\u0006\b\u0095\u0001\u0010Ò\u0002\u0012\u0005\bÓ\u0002\u0010&R\u0018\u0010Õ\u0002\u001a\u00030Ô\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÕ\u0002\u0010Ö\u0002R\u001e\u0010Ø\u0002\u001a\t\u0012\u0004\u0012\u00020\u00070×\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bØ\u0002\u0010Ù\u0002R\u001e\u0010Ú\u0002\u001a\t\u0012\u0004\u0012\u00020\u001d0×\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÚ\u0002\u0010Ù\u0002R\u0019\u0010Û\u0002\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÛ\u0002\u0010¿\u0002R+\u0010Ü\u0002\u001a\u0016\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\u001d0\u0098\u0001\u0012\u0005\u0012\u00030\u0099\u00010?8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÜ\u0002\u0010Ý\u0002R\u0017\u0010ß\u0002\u001a\u00020\f8VX\u0096\u0004¢\u0006\b\u001a\u0006\bÞ\u0002\u0010Á\u0002R\u0017\u0010á\u0002\u001a\u00020\f8VX\u0096\u0004¢\u0006\b\u001a\u0006\bà\u0002\u0010Á\u0002R\u0016\u0010â\u0002\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bâ\u0002\u00102R\u0016\u0010ã\u0002\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bã\u0002\u00102R\u0019\u0010æ\u0002\u001a\u0004\u0018\u00010 8VX\u0096\u0004¢\u0006\b\u001a\u0006\bä\u0002\u0010å\u0002R\u0016\u0010è\u0002\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0007\u001a\u0005\bç\u0002\u0010\u0015R0\u0010ï\u0002\u001a\u0005\u0018\u00010é\u00022\n\u0010ê\u0002\u001a\u0005\u0018\u00010é\u00028B@BX\u0082\u000e¢\u0006\u0010\u001a\u0006\bë\u0002\u0010ì\u0002\"\u0006\bí\u0002\u0010î\u0002R<\u0010ô\u0002\u001a\u000b\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0098\u00012\u0010\u0010ê\u0002\u001a\u000b\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0098\u00018B@BX\u0082\u000e¢\u0006\u0010\u001a\u0006\bð\u0002\u0010ñ\u0002\"\u0006\bò\u0002\u0010ó\u0002R<\u0010÷\u0002\u001a\u000b\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u0098\u00012\u0010\u0010ê\u0002\u001a\u000b\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u0098\u00018B@BX\u0082\u000e¢\u0006\u0010\u001a\u0006\bõ\u0002\u0010ñ\u0002\"\u0006\bö\u0002\u0010ó\u0002\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006ù\u0002"}, d2 = {"Lio/ktor/utils/io/ByteBufferChannel;", "Lio/ktor/utils/io/ByteChannel;", "Lio/ktor/utils/io/ByteReadChannel;", "Lio/ktor/utils/io/ByteWriteChannel;", "Lio/ktor/utils/io/LookAheadSuspendSession;", "Lio/ktor/utils/io/HasReadSession;", "Lio/ktor/utils/io/HasWriteSession;", "", "autoFlush", "Lio/ktor/utils/io/pool/ObjectPool;", "Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;", "pool", "", "reservedSize", "<init>", "(ZLio/ktor/utils/io/pool/ObjectPool;I)V", "Ljava/nio/ByteBuffer;", "content", "(Ljava/nio/ByteBuffer;)V", "Lio/ktor/utils/io/internal/ReadWriteBufferState;", "currentState$ktor_io", "()Lio/ktor/utils/io/internal/ReadWriteBufferState;", "currentState", "Lio/ktor/utils/io/internal/JoiningState;", "getJoining$ktor_io", "()Lio/ktor/utils/io/internal/JoiningState;", "getJoining", "Lka/v1;", "job", "Lx6/t0;", "attachJob", "(Lka/v1;)V", "", "cause", "close", "(Ljava/lang/Throwable;)Z", "cancel", "flush", "()V", "buffer", "lockedSpace", "prepareWriteBuffer$ktor_io", "(Ljava/nio/ByteBuffer;I)V", "prepareWriteBuffer", "setupStateForWrite$ktor_io", "()Ljava/nio/ByteBuffer;", "setupStateForWrite", "restoreStateAfterWrite$ktor_io", "restoreStateAfterWrite", "tryTerminate$ktor_io", "()Z", "tryTerminate", "", "dst", "offset", "length", "readFully", "([BIILd7/d;)Ljava/lang/Object;", "(Ljava/nio/ByteBuffer;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "n", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;ILd7/d;)Ljava/lang/Object;", "min", "Lkotlin/Function1;", "block", "readAvailable", "(ILr7/l;)I", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;Ld7/d;)Ljava/lang/Object;", ContentDisposition.Parameters.Size, "Lio/ktor/utils/io/core/ByteReadPacket;", "readPacket", "(ILd7/d;)Ljava/lang/Object;", "readBoolean", "(Ld7/d;)Ljava/lang/Object;", "", "readByte", "", "readShort", "readInt", "", "readLong", "", "readFloat", "", "readDouble", "Lio/ktor/utils/io/internal/RingBufferCapacity;", "capacity", "count", "bytesWrittenFromSession$ktor_io", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/internal/RingBufferCapacity;I)V", "bytesWrittenFromSession", "resolveChannelInstance$ktor_io", "()Lio/ktor/utils/io/ByteBufferChannel;", "resolveChannelInstance", "b", "writeByte", "(BLd7/d;)Ljava/lang/Object;", CmcdData.STREAMING_FORMAT_SS, "writeShort", "(SLd7/d;)Ljava/lang/Object;", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "writeInt", CmcdData.STREAM_TYPE_LIVE, "writeLong", "(JLd7/d;)Ljava/lang/Object;", "d", "writeDouble", "(DLd7/d;)Ljava/lang/Object;", "f", "writeFloat", "(FLd7/d;)Ljava/lang/Object;", "awaitFreeSpace", "src", "writeAvailable", "writeFully", "Lio/ktor/utils/io/core/Buffer;", "(Lio/ktor/utils/io/core/Buffer;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/bits/Memory;", "memory", "startIndex", "endIndex", "writeFully-JT6ljtQ", "(Ljava/nio/ByteBuffer;IILd7/d;)Ljava/lang/Object;", "delegateClose", "joinFrom$ktor_io", "(Lio/ktor/utils/io/ByteBufferChannel;ZLd7/d;)Ljava/lang/Object;", "joinFrom", "limit", "joined", "copyDirect$ktor_io", "(Lio/ktor/utils/io/ByteBufferChannel;JLio/ktor/utils/io/internal/JoiningState;Ld7/d;)Ljava/lang/Object;", "copyDirect", "write", "(ILr7/l;Ld7/d;)Ljava/lang/Object;", "writeWhile", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/SuspendableReadSession;", "startReadSession", "()Lio/ktor/utils/io/SuspendableReadSession;", "endReadSession", "Lio/ktor/utils/io/WriterSuspendSession;", "beginWriteSession", "()Lio/ktor/utils/io/WriterSuspendSession;", "written", "endWriteSession", "(I)V", "Lio/ktor/utils/io/ReadSession;", "Lx6/n;", "consumer", "readSession", "(Lr7/l;)V", "Lkotlin/Function2;", "Ld7/d;", "", "readSuspendableSession", "(Lr7/p;Ld7/d;)Ljava/lang/Object;", "read", "max", "discard", "packet", "writePacket", "(Lio/ktor/utils/io/core/ByteReadPacket;Ld7/d;)Ljava/lang/Object;", "R", "Lio/ktor/utils/io/LookAheadSession;", "visitor", "lookAhead", "(Lr7/l;)Ljava/lang/Object;", "lookAheadSuspend", "writeSuspendSession", "consumed", "awaitAtLeast", "skip", "atLeast", "request", "(II)Ljava/nio/ByteBuffer;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "A", "out", "readUTF8LineTo", "(Ljava/lang/Appendable;ILd7/d;)Ljava/lang/Object;", "", "readUTF8Line", "readRemaining", "awaitContent", "tryWriteSuspend$ktor_io", "tryWriteSuspend", "destination", "destinationOffset", "peekTo-lBXzO7A", "(Ljava/nio/ByteBuffer;JJJJLd7/d;)Ljava/lang/Object;", "peekTo", "toString", "()Ljava/lang/String;", "minWriteSize", "flushImpl", "position", "available", "prepareBuffer", "(Ljava/nio/ByteBuffer;II)V", "setupStateForRead", "restoreStateAfterRead", "delegate", "setupDelegateTo", "(Lio/ktor/utils/io/ByteBufferChannel;Z)Lio/ktor/utils/io/internal/JoiningState;", "tryCompleteJoining", "(Lio/ktor/utils/io/internal/JoiningState;)Z", "forceTermination", "tryReleaseBuffer", "(Z)Z", "idx", "carryIndex", "(Ljava/nio/ByteBuffer;I)I", "Lkotlin/Function3;", "writing", "(Lr7/q;)V", "reading", "(Lr7/p;)Z", "readAsMuchAsPossible", "(Ljava/nio/ByteBuffer;)I", "(Lio/ktor/utils/io/core/Buffer;II)I", "([BII)I", "rc0", "readFullySuspend", "(Ljava/nio/ByteBuffer;ILd7/d;)Ljava/lang/Object;", "readAvailableSuspend", "Lio/ktor/utils/io/core/BytePacketBuilder;", "builder", "readPacketSuspend", "(ILio/ktor/utils/io/core/BytePacketBuilder;Ljava/nio/ByteBuffer;Ld7/d;)Ljava/lang/Object;", "", "T", "getter", "readPrimitive", "rollBytes", "carry", "bytesWritten", "bytesRead", "current", "joining", "resolveDelegation", "(Lio/ktor/utils/io/ByteBufferChannel;Lio/ktor/utils/io/internal/JoiningState;)Lio/ktor/utils/io/ByteBufferChannel;", "delegateSuspend", "(Lio/ktor/utils/io/internal/JoiningState;Lr7/l;Ld7/d;)Ljava/lang/Object;", "channelWriter", "bufferWriter", "writePrimitive", "(ILr7/l;Lr7/l;Ld7/d;)Ljava/lang/Object;", "writer", "tryWritePrimitive", "(Ljava/nio/ByteBuffer;ILio/ktor/utils/io/internal/RingBufferCapacity;Lr7/l;)Z", "doWritePrimitive", "(ILjava/nio/ByteBuffer;Lio/ktor/utils/io/internal/RingBufferCapacity;Lr7/l;)V", "writeSuspendPrimitive", "(Ljava/nio/ByteBuffer;ILio/ktor/utils/io/internal/RingBufferCapacity;Lr7/l;Lr7/l;Ld7/d;)Ljava/lang/Object;", "delegatePrimitive", "writeAvailableSuspend", "writeFullySuspend", "awaitClose", "joinFromSuspend", "(Lio/ktor/utils/io/ByteBufferChannel;ZLio/ktor/utils/io/internal/JoiningState;Ld7/d;)Ljava/lang/Object;", "ensureClosedJoined", "(Lio/ktor/utils/io/internal/JoiningState;)V", "writeAsMuchAsPossible", "(Lio/ktor/utils/io/core/Buffer;)I", "writeSuspend", "awaitFreeSpaceOrDelegate", "writeWhileNoSuspend", "(Lr7/l;)Z", "writeWhileSuspend", "writeWhileLoop", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/internal/RingBufferCapacity;Lr7/l;)Z", "discarded0", "discardSuspend", "(JJLd7/d;)Ljava/lang/Object;", "readBlockSuspend", "writePacketSuspend", "tryWritePacketPart", "(Lio/ktor/utils/io/core/ByteReadPacket;)I", "awaitAtLeastSuspend", "last", "Lx6/y;", ContentDisposition.Parameters.Name, "consumeEachBufferRangeFast", "(ZLr7/p;)Z", "afterBufferVisited", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/internal/RingBufferCapacity;)I", "readUTF8LineToAscii", "readUTF8LineToUtf8Suspend", "remainingPacket", "(J)Lio/ktor/utils/io/core/ByteReadPacket;", "readRemainingSuspend", "resumeReadOp", "Lkotlin/Function0;", "exception", "(Lr7/a;)V", "resumeWriteOp", "resumeClosed", "(Ljava/lang/Throwable;)V", "readSuspend", "readSuspendLoop", "readSuspendPredicate", "(I)Z", "continuation", "suspensionForSize", "readSuspendImpl", "shouldResumeReadOp", "writeSuspendPredicate", "Lka/k;", "c", "writeSuspendBlock", "(ILka/k;)V", "newBuffer", "()Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;", "releaseBuffer", "(Lio/ktor/utils/io/internal/ReadWriteBufferState$Initial;)V", "Z", "getAutoFlush", "Lio/ktor/utils/io/pool/ObjectPool;", "I", "getReservedSize$ktor_io", "()I", "Lio/ktor/utils/io/internal/JoiningState;", "readPosition", "writePosition", "attachedJob", "Lka/v1;", "<set-?>", "totalBytesRead", "J", "getTotalBytesRead", "()J", "setTotalBytesRead$ktor_io", "(J)V", "totalBytesWritten", "getTotalBytesWritten", "setTotalBytesWritten$ktor_io", "Lio/ktor/utils/io/internal/ReadSessionImpl;", "Lio/ktor/utils/io/internal/ReadSessionImpl;", "getReadSession$annotations", "Lio/ktor/utils/io/internal/WriteSessionImpl;", "writeSession", "Lio/ktor/utils/io/internal/WriteSessionImpl;", "Lio/ktor/utils/io/internal/CancellableReusableContinuation;", "readSuspendContinuationCache", "Lio/ktor/utils/io/internal/CancellableReusableContinuation;", "writeSuspendContinuationCache", "writeSuspensionSize", "writeSuspension", "Lr7/l;", "getAvailableForRead", "availableForRead", "getAvailableForWrite", "availableForWrite", "isClosedForRead", "isClosedForWrite", "getClosedCause", "()Ljava/lang/Throwable;", "closedCause", "getState", "state", "Lio/ktor/utils/io/internal/ClosedElement;", "value", "getClosed", "()Lio/ktor/utils/io/internal/ClosedElement;", "setClosed", "(Lio/ktor/utils/io/internal/ClosedElement;)V", "closed", "getReadOp", "()Ld7/d;", "setReadOp", "(Ld7/d;)V", "readOp", "getWriteOp", "setWriteOp", "writeOp", "Companion", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class ByteBufferChannel implements ByteChannel, ByteReadChannel, ByteWriteChannel, LookAheadSuspendSession, HasReadSession, HasWriteSession {
    private static final int ReservedLongIndex = -8;
    private volatile /* synthetic */ Object _closed;
    private volatile /* synthetic */ Object _readOp;
    private volatile /* synthetic */ Object _state;
    volatile /* synthetic */ Object _writeOp;
    private volatile v1 attachedJob;
    private final boolean autoFlush;
    private volatile JoiningState joining;
    private final ObjectPool<ReadWriteBufferState.Initial> pool;
    private int readPosition;
    private final ReadSessionImpl readSession;
    private final CancellableReusableContinuation<Boolean> readSuspendContinuationCache;
    private final int reservedSize;
    private volatile long totalBytesRead;
    private volatile long totalBytesWritten;
    private int writePosition;
    private final WriteSessionImpl writeSession;
    private final CancellableReusableContinuation<t0> writeSuspendContinuationCache;
    private final l<d<? super t0>, Object> writeSuspension;
    private volatile int writeSuspensionSize;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(ByteBufferChannel.class, Object.class, "_state");
    private static final /* synthetic */ AtomicReferenceFieldUpdater _closed$FU = AtomicReferenceFieldUpdater.newUpdater(ByteBufferChannel.class, Object.class, "_closed");
    private static final /* synthetic */ AtomicReferenceFieldUpdater _readOp$FU = AtomicReferenceFieldUpdater.newUpdater(ByteBufferChannel.class, Object.class, "_readOp");
    static final /* synthetic */ AtomicReferenceFieldUpdater _writeOp$FU = AtomicReferenceFieldUpdater.newUpdater(ByteBufferChannel.class, Object.class, "_writeOp");

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$attachJob$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "cause", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Throwable, t0> {
        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public final void invoke(Throwable th) {
            ByteBufferChannel.this.attachedJob = null;
            if (th == null) {
                return;
            }
            ByteBufferChannel.this.cancel(ExceptionUtilsKt.unwrapCancellationException(th));
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$awaitAtLeastSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {1871}, m = "awaitAtLeastSuspend")
    public static final class C19531 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19531(d<? super C19531> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.awaitAtLeastSuspend(0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$awaitFreeSpaceOrDelegate$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {1512, 1513}, m = "awaitFreeSpaceOrDelegate")
    public static final class C19541 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C19541(d<? super C19541> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.awaitFreeSpaceOrDelegate(0, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$discardSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {1702}, m = "discardSuspend")
    public static final class C19551 extends c {
        long J$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C19551(d<? super C19551> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.discardSuspend(0L, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$joinFromSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {1163, 1171}, m = "joinFromSuspend")
    public static final class C19561 extends c {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C19561(d<? super C19561> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.joinFromSuspend(null, false, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$lookAheadSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {1790, 1792, 1797, 1802, 1804, 1808}, m = "lookAheadSuspend$suspendImpl")
    public static final class C19571<R> extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C19571(d<? super C19571> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.lookAheadSuspend$suspendImpl(ByteBufferChannel.this, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {729, 733}, m = "readAvailableSuspend")
    public static final class C19581 extends c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C19581(d<? super C19581> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readAvailableSuspend(null, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {737, 741}, m = "readAvailableSuspend")
    public static final class AnonymousClass2 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass2(d<? super AnonymousClass2> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readAvailableSuspend((ByteBuffer) null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {745, 749}, m = "readAvailableSuspend")
    public static final class AnonymousClass3 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass3(d<? super AnonymousClass3> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readAvailableSuspend((ChunkBuffer) null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readBlockSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {1710, 1718}, m = "readBlockSuspend")
    public static final class C19591 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C19591(d<? super C19591> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readBlockSuspend(0, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readBoolean$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {818}, m = "readBoolean")
    public static final class C19601 extends c {
        int label;
        /* synthetic */ Object result;

        public C19601(d<? super C19601> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readBoolean(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readByte$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {2437}, m = "readByte")
    public static final class C19611 extends c {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19611(d<? super C19611> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readByte(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readDouble$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {2437}, m = "readDouble")
    public static final class C19621 extends c {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19621(d<? super C19621> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readDouble(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readFloat$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {2437}, m = "readFloat")
    public static final class C19631 extends c {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19631(d<? super C19631> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readFloat(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readFullySuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {585}, m = "readFullySuspend")
    public static final class C19641 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C19641(d<? super C19641> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readFullySuspend((ByteBuffer) null, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readFullySuspend$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {608}, m = "readFullySuspend")
    public static final class C19652 extends c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C19652(d<? super C19652> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readFullySuspend((ChunkBuffer) null, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readFullySuspend$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {622}, m = "readFullySuspend")
    public static final class C19663 extends c {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C19663(d<? super C19663> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readFullySuspend(null, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readInt$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {2437}, m = "readInt")
    public static final class C19671 extends c {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19671(d<? super C19671> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readInt(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readLong$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {2437}, m = "readLong")
    public static final class C19681 extends c {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19681(d<? super C19681> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readLong(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readPacketSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {800}, m = "readPacketSuspend")
    public static final class C19691 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C19691(d<? super C19691> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readPacketSuspend(0, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readRemainingSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {2093}, m = "readRemainingSuspend")
    public static final class C19701 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C19701(d<? super C19701> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readRemainingSuspend(0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/LookAheadSession;", "Lx6/t0;", "invoke", "(Lio/ktor/utils/io/LookAheadSession;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19711 extends r implements l<LookAheadSession, t0> {
        final /* synthetic */ l<ReadSession, t0> $consumer;
        final /* synthetic */ ByteBufferChannel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19711(l<? super ReadSession, t0> lVar, ByteBufferChannel byteBufferChannel) {
            super(1);
            this.$consumer = lVar;
            this.this$0 = byteBufferChannel;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((LookAheadSession) obj);
            return t0.f22605a;
        }

        public final void invoke(LookAheadSession lookAheadSession) {
            try {
                this.$consumer.invoke(this.this$0.readSession);
            } finally {
                this.this$0.readSession.completed();
            }
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readShort$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {2437}, m = "readShort")
    public static final class C19721 extends c {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19721(d<? super C19721> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readShort(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readSuspendImpl$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {2236}, m = "readSuspendImpl")
    public static final class C19731 extends c {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19731(d<? super C19731> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readSuspendImpl(0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readSuspendLoop$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {2189}, m = "readSuspendLoop")
    public static final class C19741 extends c {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19741(d<? super C19741> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readSuspendLoop(0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readSuspendableSession$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/LookAheadSuspendSession;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/LookAheadSuspendSession;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.utils.io.ByteBufferChannel$readSuspendableSession$2", f = "ByteBufferChannel.kt", l = {1630}, m = "invokeSuspend")
    public static final class C19752 extends j implements p<LookAheadSuspendSession, d<? super t0>, Object> {
        final /* synthetic */ p<SuspendableReadSession, d<? super t0>, Object> $consumer;
        int label;
        final /* synthetic */ ByteBufferChannel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19752(p<? super SuspendableReadSession, ? super d<? super t0>, ? extends Object> pVar, ByteBufferChannel byteBufferChannel, d<? super C19752> dVar) {
            super(2, dVar);
            this.$consumer = pVar;
            this.this$0 = byteBufferChannel;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new C19752(this.$consumer, this.this$0, dVar);
        }

        @Override // r7.p
        public final Object invoke(LookAheadSuspendSession lookAheadSuspendSession, d<? super t0> dVar) {
            return ((C19752) create(lookAheadSuspendSession, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    p<SuspendableReadSession, d<? super t0>, Object> pVar = this.$consumer;
                    ReadSessionImpl readSessionImpl = this.this$0.readSession;
                    this.label = 1;
                    Object objInvoke = pVar.invoke(readSessionImpl, this);
                    a aVar = a.f15033i;
                    if (objInvoke == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                this.this$0.readSession.completed();
                return t0.f22605a;
            } catch (Throwable th) {
                this.this$0.readSession.completed();
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readUTF8Line$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {2055}, m = "readUTF8Line$suspendImpl")
    public static final class C19761 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19761(d<? super C19761> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.readUTF8Line$suspendImpl(ByteBufferChannel.this, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {1960, 2036}, m = "readUTF8LineToUtf8Suspend")
    public static final class C19771 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        /* synthetic */ Object result;

        public C19771(d<? super C19771> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.readUTF8LineToUtf8Suspend(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Ljava/nio/ByteBuffer;", "buffer", "Lx6/t0;", "invoke", "(Ljava/nio/ByteBuffer;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19782 extends r implements l<ByteBuffer, t0> {
        final /* synthetic */ f0 $caret;
        final /* synthetic */ i0 $consumed;
        final /* synthetic */ int $limit;
        final /* synthetic */ f0 $newLine;
        final /* synthetic */ Appendable $out;
        final /* synthetic */ char[] $output;
        final /* synthetic */ i0 $required;
        final /* synthetic */ k0 $transferBuffer;
        final /* synthetic */ i0 $transferredRemaining;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C19782(k0 k0Var, int i10, char[] cArr, i0 i0Var, i0 i0Var2, f0 f0Var, f0 f0Var2, Appendable appendable, i0 i0Var3) {
            super(1);
            this.$transferBuffer = k0Var;
            this.$limit = i10;
            this.$output = cArr;
            this.$consumed = i0Var;
            this.$required = i0Var2;
            this.$newLine = f0Var;
            this.$caret = f0Var2;
            this.$out = appendable;
            this.$transferredRemaining = i0Var3;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) throws IOException {
            invoke((ByteBuffer) obj);
            return t0.f22605a;
        }

        public final void invoke(ByteBuffer byteBuffer) throws IOException {
            int iPosition = byteBuffer.position();
            ByteBuffer byteBuffer2 = (ByteBuffer) this.$transferBuffer.f19746i;
            if (byteBuffer2 != null) {
                int iLimit = byteBuffer.limit();
                byteBuffer.limit(Math.min(byteBuffer.limit(), byteBuffer2.remaining() + byteBuffer.position()));
                byteBuffer2.put(byteBuffer);
                byteBuffer2.flip();
                byteBuffer.limit(iLimit);
            } else {
                byteBuffer2 = byteBuffer;
            }
            int i10 = this.$limit;
            long jDecodeUTF8Line = UTFKt.decodeUTF8Line(byteBuffer2, this.$output, 0, i10 == Integer.MAX_VALUE ? this.$output.length : Math.min(this.$output.length, i10 - this.$consumed.f19744i));
            k0 k0Var = this.$transferBuffer;
            ByteBuffer byteBuffer3 = (ByteBuffer) k0Var.f19746i;
            if (byteBuffer3 != null) {
                i0 i0Var = this.$transferredRemaining;
                byteBuffer.position((byteBuffer3.position() + iPosition) - i0Var.f19744i);
                ObjectPoolKt.getBufferPool().recycle(byteBuffer3);
                k0Var.f19746i = null;
                i0Var.f19744i = 0;
            }
            int i11 = (int) (jDecodeUTF8Line >> 32);
            int i12 = (int) (jDecodeUTF8Line & 4294967295L);
            this.$required.f19744i = Math.max(1, i12);
            if (i12 == -1) {
                this.$newLine.f19738i = true;
            }
            if (i12 != -1 && byteBuffer.hasRemaining() && byteBuffer.get(byteBuffer.position()) == 13) {
                byteBuffer.position(byteBuffer.position() + 1);
                this.$caret.f19738i = true;
            }
            if (i12 != -1 && byteBuffer.hasRemaining() && byteBuffer.get(byteBuffer.position()) == 10) {
                byteBuffer.position(byteBuffer.position() + 1);
                this.$newLine.f19738i = true;
            }
            Appendable appendable = this.$out;
            if (appendable instanceof StringBuilder) {
                ((StringBuilder) appendable).append(this.$output, 0, i11);
            } else {
                this.$out.append(CharBuffer.wrap(this.$output, 0, i11), 0, i11);
            }
            this.$consumed.f19744i += i11;
            if (i11 == 0 && byteBuffer.remaining() < i12) {
                k0 k0Var2 = this.$transferBuffer;
                ByteBuffer byteBufferBorrow = ObjectPoolKt.getBufferPool().borrow();
                this.$transferredRemaining.f19744i = byteBuffer.remaining();
                byteBufferBorrow.put(byteBuffer);
                k0Var2.f19746i = byteBufferBorrow;
            }
            int i13 = this.$limit;
            if (i13 != Integer.MAX_VALUE && this.$consumed.f19744i >= i13 && !this.$newLine.f19738i) {
                throw new TooLongLineException("Line is longer than limit");
            }
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$readUTF8LineToUtf8Suspend$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Ljava/nio/ByteBuffer;", "it", "Lx6/t0;", "invoke", "(Ljava/nio/ByteBuffer;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19793 extends r implements l<ByteBuffer, t0> {
        final /* synthetic */ f0 $newLine;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C19793(f0 f0Var) {
            super(1);
            this.$newLine = f0Var;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ByteBuffer) obj);
            return t0.f22605a;
        }

        public final void invoke(ByteBuffer byteBuffer) {
            if (byteBuffer.get(byteBuffer.position()) == 10) {
                byteBuffer.position(byteBuffer.position() + 1);
                this.$newLine.f19738i = true;
            }
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$write$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {1507}, m = "write$suspendImpl")
    public static final class C19801 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C19801(d<? super C19801> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.write$suspendImpl(ByteBufferChannel.this, 0, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {1072, 1074, 1076}, m = "writeAvailableSuspend")
    public static final class C19811 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C19811(d<? super C19811> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.writeAvailableSuspend((ByteBuffer) null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {1080, 1082, 1084}, m = "writeAvailableSuspend")
    public static final class C19823 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C19823(d<? super C19823> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.writeAvailableSuspend((ChunkBuffer) null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$writeByte$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {936, 936, 936, 2426, 2481, 936, 936, 2508}, m = "writeByte$suspendImpl")
    public static final class C19831 extends c {
        byte B$0;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C19831(d<? super C19831> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.writeByte$suspendImpl(ByteBufferChannel.this, (byte) 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {1113, 1115}, m = "writeFullySuspend")
    public static final class C19841 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C19841(d<? super C19841> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.writeFullySuspend((ByteBuffer) null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {1123, 1125}, m = "writeFullySuspend")
    public static final class C19853 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C19853(d<? super C19853> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.writeFullySuspend((Buffer) null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$5, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {1422}, m = "writeFullySuspend")
    public static final class AnonymousClass5 extends c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass5(d<? super AnonymousClass5> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.writeFullySuspend(null, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$writeInt$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {944, 944, 944, 2426, 2481, 944, 944, 2508}, m = "writeInt$suspendImpl")
    public static final class C19861 extends c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C19861(d<? super C19861> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.writeInt$suspendImpl(ByteBufferChannel.this, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$writeLong$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {948, 948, 948, 2426, 2481, 948, 948, 2508}, m = "writeLong$suspendImpl")
    public static final class C19871 extends c {
        int I$0;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C19871(d<? super C19871> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.writeLong$suspendImpl(ByteBufferChannel.this, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$writePacketSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {1742, 1744}, m = "writePacketSuspend")
    public static final class C19881 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C19881(d<? super C19881> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.writePacketSuspend(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$writeShort$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {940, 940, 940, 2426, 2481, 940, 940, 2508}, m = "writeShort$suspendImpl")
    public static final class C19891 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        short S$0;
        int label;
        /* synthetic */ Object result;

        public C19891(d<? super C19891> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.writeShort$suspendImpl(ByteBufferChannel.this, (short) 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$writeSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {1439, 1441}, m = "writeSuspend")
    public static final class C19901 extends c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C19901(d<? super C19901> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.writeSuspend(null, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$writeSuspend$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {2412}, m = "writeSuspend")
    public static final class C19913 extends c {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19913(d<? super C19913> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.writeSuspend(0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$writeSuspendSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {1835}, m = "writeSuspendSession$suspendImpl")
    public static final class C19921 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19921(d<? super C19921> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.writeSuspendSession$suspendImpl(ByteBufferChannel.this, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteBufferChannel$writeWhileSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {1537, 1549}, m = "writeWhileSuspend")
    public static final class C19931 extends c {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        /* synthetic */ Object result;

        public C19931(d<? super C19931> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteBufferChannel.this.writeWhileSuspend(null, this);
        }
    }

    public ByteBufferChannel(boolean z, ObjectPool<ReadWriteBufferState.Initial> objectPool, int i10) {
        this.autoFlush = z;
        this.pool = objectPool;
        this.reservedSize = i10;
        this._state = ReadWriteBufferState.IdleEmpty.INSTANCE;
        this._closed = null;
        this._readOp = null;
        this._writeOp = null;
        this.readSession = new ReadSessionImpl(this);
        this.writeSession = new WriteSessionImpl(this);
        this.readSuspendContinuationCache = new CancellableReusableContinuation<>();
        this.writeSuspendContinuationCache = new CancellableReusableContinuation<>();
        this.writeSuspension = new ByteBufferChannel$writeSuspension$1(this);
    }

    private final int afterBufferVisited(ByteBuffer buffer, RingBufferCapacity capacity) {
        int iPosition = buffer.position() - this.readPosition;
        if (iPosition <= 0) {
            return iPosition;
        }
        if (!capacity.tryReadExact(iPosition)) {
            throw new IllegalStateException("Consumed more bytes than available");
        }
        bytesRead(buffer, capacity, iPosition);
        prepareBuffer(buffer, this.readPosition, capacity._availableForRead$internal);
        return iPosition;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object awaitAtLeastSuspend(int r5, d7.d<? super java.lang.Boolean> r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteBufferChannel.C19531
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteBufferChannel$awaitAtLeastSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19531) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$awaitAtLeastSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$awaitAtLeastSuspend$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r5 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r5 = (io.ktor.utils.io.ByteBufferChannel) r5
            k2.c.G(r6)
            goto L42
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r6)
            r0.L$0 = r4
            r0.label = r2
            java.lang.Object r6 = r4.readSuspend(r5, r0)
            e7.a r5 = e7.a.f15033i
            if (r6 != r5) goto L41
            return r5
        L41:
            r5 = r4
        L42:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L57
            io.ktor.utils.io.internal.ReadWriteBufferState r0 = r5.getState()
            boolean r0 = r0.getIdle()
            if (r0 == 0) goto L57
            r5.setupStateForRead()
        L57:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.awaitAtLeastSuspend(int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object awaitClose(d<? super t0> dVar) {
        t0 t0Var = t0.f22605a;
        if (getClosed() != null) {
            return t0Var;
        }
        JoiningState joiningState = this.joining;
        if (joiningState != null) {
            Object objAwaitClose = joiningState.awaitClose(dVar);
            return objAwaitClose == a.f15033i ? objAwaitClose : t0Var;
        }
        if (getClosed() != null) {
            return t0Var;
        }
        throw new IllegalStateException("Only works for joined.");
    }

    public static /* synthetic */ Object awaitContent$suspendImpl(ByteBufferChannel byteBufferChannel, d<? super t0> dVar) throws Throwable {
        Object suspend = byteBufferChannel.readSuspend(1, dVar);
        return suspend == a.f15033i ? suspend : t0.f22605a;
    }

    public static /* synthetic */ Object awaitFreeSpace$suspendImpl(ByteBufferChannel byteBufferChannel, d<? super t0> dVar) throws Throwable {
        Object objWriteSuspend = byteBufferChannel.writeSuspend(1, dVar);
        return objWriteSuspend == a.f15033i ? objWriteSuspend : t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object awaitFreeSpaceOrDelegate(int r7, r7.l<? super java.nio.ByteBuffer, x6.t0> r8, d7.d<? super x6.t0> r9) {
        /*
            r6 = this;
            x6.t0 r0 = x6.t0.f22605a
            boolean r1 = r9 instanceof io.ktor.utils.io.ByteBufferChannel.C19541
            if (r1 == 0) goto L15
            r1 = r9
            io.ktor.utils.io.ByteBufferChannel$awaitFreeSpaceOrDelegate$1 r1 = (io.ktor.utils.io.ByteBufferChannel.C19541) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.label = r2
            goto L1a
        L15:
            io.ktor.utils.io.ByteBufferChannel$awaitFreeSpaceOrDelegate$1 r1 = new io.ktor.utils.io.ByteBufferChannel$awaitFreeSpaceOrDelegate$1
            r1.<init>(r9)
        L1a:
            java.lang.Object r9 = r1.result
            e7.a r2 = e7.a.f15033i
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L42
            if (r3 == r5) goto L34
            if (r3 != r4) goto L2c
            k2.c.G(r9)
            goto L6d
        L2c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L34:
            int r7 = r1.I$0
            java.lang.Object r8 = r1.L$1
            r7.l r8 = (r7.l) r8
            java.lang.Object r3 = r1.L$0
            io.ktor.utils.io.ByteBufferChannel r3 = (io.ktor.utils.io.ByteBufferChannel) r3
            k2.c.G(r9)
            goto L55
        L42:
            k2.c.G(r9)
            r1.L$0 = r6
            r1.L$1 = r8
            r1.I$0 = r7
            r1.label = r5
            java.lang.Object r9 = r6.writeSuspend(r7, r1)
            if (r9 != r2) goto L54
            goto L6c
        L54:
            r3 = r6
        L55:
            io.ktor.utils.io.internal.JoiningState r9 = r3.joining
            if (r9 == 0) goto L6d
            io.ktor.utils.io.ByteBufferChannel r9 = r3.resolveDelegation(r3, r9)
            if (r9 == 0) goto L6d
            r3 = 0
            r1.L$0 = r3
            r1.L$1 = r3
            r1.label = r4
            java.lang.Object r7 = r9.write(r7, r8, r1)
            if (r7 != r2) goto L6d
        L6c:
            return r2
        L6d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.awaitFreeSpaceOrDelegate(int, r7.l, d7.d):java.lang.Object");
    }

    private final void bytesRead(ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity, int i10) {
        if (i10 < 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        this.readPosition = carryIndex(byteBuffer, this.readPosition + i10);
        ringBufferCapacity.completeRead(i10);
        setTotalBytesRead$ktor_io(get_totalBytesRead() + ((long) i10));
        resumeWriteOp();
    }

    private final void bytesWritten(ByteBuffer byteBuffer, RingBufferCapacity ringBufferCapacity, int i10) {
        if (i10 < 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        this.writePosition = carryIndex(byteBuffer, this.writePosition + i10);
        ringBufferCapacity.completeWrite(i10);
        setTotalBytesWritten$ktor_io(get_totalBytesWritten() + ((long) i10));
    }

    private final void carry(ByteBuffer byteBuffer) {
        int iCapacity = byteBuffer.capacity() - this.reservedSize;
        int iPosition = byteBuffer.position();
        for (int i10 = iCapacity; i10 < iPosition; i10++) {
            byteBuffer.put(i10 - iCapacity, byteBuffer.get(i10));
        }
    }

    private final int carryIndex(ByteBuffer byteBuffer, int i10) {
        return i10 >= byteBuffer.capacity() - this.reservedSize ? i10 - (byteBuffer.capacity() - this.reservedSize) : i10;
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0061, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean consumeEachBufferRangeFast(boolean r6, r7.p<? super java.nio.ByteBuffer, ? super java.lang.Boolean, java.lang.Boolean> r7) throws java.lang.Throwable {
        /*
            r5 = this;
            java.nio.ByteBuffer r0 = r5.setupStateForRead()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto La
        L8:
            r6 = r2
            goto L2a
        La:
            io.ktor.utils.io.internal.ReadWriteBufferState r3 = r5.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r3 = r3.capacity
            int r4 = r3._availableForRead$internal     // Catch: java.lang.Throwable -> L59
            if (r4 != 0) goto L1b
            r5.restoreStateAfterRead()
            r5.tryTerminate$ktor_io()
            goto L8
        L1b:
            boolean r2 = r0.hasRemaining()     // Catch: java.lang.Throwable -> L59
            if (r2 != 0) goto L3d
            if (r6 == 0) goto L24
            goto L3d
        L24:
            r5.restoreStateAfterRead()
            r5.tryTerminate$ktor_io()
        L2a:
            if (r6 != 0) goto L3c
            io.ktor.utils.io.internal.ClosedElement r0 = r5.getClosed()
            if (r0 == 0) goto L3c
            java.nio.ByteBuffer r6 = io.ktor.utils.io.internal.ReadWriteBufferStateKt.getEmptyByteBuffer()
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            r7.invoke(r6, r0)
            return r1
        L3c:
            return r6
        L3d:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r6)     // Catch: java.lang.Throwable -> L59
            java.lang.Object r2 = r7.invoke(r0, r2)     // Catch: java.lang.Throwable -> L59
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch: java.lang.Throwable -> L59
            boolean r2 = r2.booleanValue()     // Catch: java.lang.Throwable -> L59
            r5.afterBufferVisited(r0, r3)     // Catch: java.lang.Throwable -> L59
            if (r2 == 0) goto L5b
            if (r6 == 0) goto L1b
            boolean r2 = r0.hasRemaining()     // Catch: java.lang.Throwable -> L59
            if (r2 != 0) goto L1b
            goto L5b
        L59:
            r6 = move-exception
            goto L62
        L5b:
            r5.restoreStateAfterRead()
            r5.tryTerminate$ktor_io()
            return r1
        L62:
            r5.restoreStateAfterRead()
            r5.tryTerminate$ktor_io()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.consumeEachBufferRangeFast(boolean, r7.p):boolean");
    }

    private final Object delegatePrimitive(l<? super ByteBufferChannel, t0> lVar, d<? super t0> dVar) throws Throwable {
        t0 t0Var = t0.f22605a;
        JoiningState joiningState = this.joining;
        if (getState() == ReadWriteBufferState.Terminated.INSTANCE) {
            lVar.invoke(joiningState.getDelegatedTo());
            return t0Var;
        }
        while (getState() != ReadWriteBufferState.Terminated.INSTANCE) {
            writeSuspend(1, dVar);
        }
        lVar.invoke(joiningState.getDelegatedTo());
        return t0Var;
    }

    private final Object delegateSuspend(JoiningState joiningState, l<? super ByteBufferChannel, t0> lVar, d<? super t0> dVar) throws Throwable {
        while (getState() != ReadWriteBufferState.Terminated.INSTANCE) {
            writeSuspend(1, dVar);
        }
        lVar.invoke(joiningState.getDelegatedTo());
        return t0.f22605a;
    }

    public static Object discard$suspendImpl(ByteBufferChannel byteBufferChannel, long j10, d<? super Long> dVar) throws Throwable {
        long j11 = 0;
        if (j10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.q(j10, "max shouldn't be negative: ").toString());
        }
        ByteBuffer byteBuffer = byteBufferChannel.setupStateForRead();
        if (byteBuffer != null) {
            RingBufferCapacity ringBufferCapacity = byteBufferChannel.getState().capacity;
            try {
                if (ringBufferCapacity._availableForRead$internal != 0) {
                    int iTryReadAtMost = ringBufferCapacity.tryReadAtMost((int) Math.min(2147483647L, j10));
                    byteBufferChannel.bytesRead(byteBuffer, ringBufferCapacity, iTryReadAtMost);
                    j11 = iTryReadAtMost;
                }
            } finally {
                byteBufferChannel.restoreStateAfterRead();
                byteBufferChannel.tryTerminate$ktor_io();
            }
        }
        long j12 = j11;
        return (j12 == j10 || byteBufferChannel.isClosedForRead()) ? new Long(j12) : byteBufferChannel.discardSuspend(j12, j10, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[PHI: r10 r12 r13
      PHI (r10v2 long) = (r10v1 long), (r10v5 long) binds: [B:14:0x0039, B:30:0x007e] A[DONT_GENERATE, DONT_INLINE]
      PHI (r12v2 kotlin.jvm.internal.j0) = (r12v1 kotlin.jvm.internal.j0), (r12v5 kotlin.jvm.internal.j0) binds: [B:14:0x0039, B:30:0x007e] A[DONT_GENERATE, DONT_INLINE]
      PHI (r13v1 io.ktor.utils.io.ByteBufferChannel) = (r13v0 io.ktor.utils.io.ByteBufferChannel), (r13v2 io.ktor.utils.io.ByteBufferChannel) binds: [B:14:0x0039, B:30:0x007e] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0075 -> B:29:0x0078). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object discardSuspend(long r10, long r12, d7.d<? super java.lang.Long> r14) throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r14 instanceof io.ktor.utils.io.ByteBufferChannel.C19551
            if (r0 == 0) goto L13
            r0 = r14
            io.ktor.utils.io.ByteBufferChannel$discardSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19551) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$discardSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$discardSuspend$1
            r0.<init>(r14)
        L18:
            java.lang.Object r14 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            long r10 = r0.J$0
            java.lang.Object r12 = r0.L$1
            kotlin.jvm.internal.j0 r12 = (kotlin.jvm.internal.j0) r12
            java.lang.Object r13 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r13 = (io.ktor.utils.io.ByteBufferChannel) r13
            k2.c.G(r14)
            goto L78
        L31:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L39:
            k2.c.G(r14)
            kotlin.jvm.internal.j0 r14 = new kotlin.jvm.internal.j0
            r14.<init>()
            r14.f19745i = r10
            r10 = r12
            r12 = r14
            r13 = r9
        L46:
            long r4 = r12.f19745i
            int r14 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r14 >= 0) goto La9
            java.nio.ByteBuffer r14 = r13.setupStateForRead()
            if (r14 != 0) goto L53
            goto L63
        L53:
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r13.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r2 = r2.capacity
            int r4 = r2._availableForRead$internal     // Catch: java.lang.Throwable -> La1
            if (r4 != 0) goto L81
            r13.restoreStateAfterRead()
            r13.tryTerminate$ktor_io()
        L63:
            boolean r14 = r13.isClosedForRead()
            if (r14 != 0) goto La9
            r0.L$0 = r13
            r0.L$1 = r12
            r0.J$0 = r10
            r0.label = r3
            java.lang.Object r14 = r13.readSuspend(r3, r0)
            if (r14 != r1) goto L78
            return r1
        L78:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 != 0) goto L46
            goto La9
        L81:
            long r4 = r12.f19745i     // Catch: java.lang.Throwable -> La1
            long r4 = r10 - r4
            r6 = 2147483647(0x7fffffff, double:1.060997895E-314)
            long r4 = java.lang.Math.min(r6, r4)     // Catch: java.lang.Throwable -> La1
            int r4 = (int) r4     // Catch: java.lang.Throwable -> La1
            int r4 = r2.tryReadAtMost(r4)     // Catch: java.lang.Throwable -> La1
            r13.bytesRead(r14, r2, r4)     // Catch: java.lang.Throwable -> La1
            long r5 = r12.f19745i     // Catch: java.lang.Throwable -> La1
            long r7 = (long) r4     // Catch: java.lang.Throwable -> La1
            long r5 = r5 + r7
            r12.f19745i = r5     // Catch: java.lang.Throwable -> La1
            r13.restoreStateAfterRead()
            r13.tryTerminate$ktor_io()
            goto L46
        La1:
            r10 = move-exception
            r13.restoreStateAfterRead()
            r13.tryTerminate$ktor_io()
            throw r10
        La9:
            long r10 = r12.f19745i
            java.lang.Long r12 = new java.lang.Long
            r12.<init>(r10)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.discardSuspend(long, long, d7.d):java.lang.Object");
    }

    private final void doWritePrimitive(int size, ByteBuffer buffer, RingBufferCapacity capacity, l<? super ByteBuffer, t0> writer) {
        if (buffer.remaining() < size) {
            buffer.limit(buffer.capacity());
            writer.invoke(buffer);
            carry(buffer);
        } else {
            writer.invoke(buffer);
        }
        bytesWritten(buffer, capacity, size);
        if (capacity.isFull() || getAutoFlush()) {
            flush();
        }
        restoreStateAfterWrite$ktor_io();
        tryTerminate$ktor_io();
    }

    private final void ensureClosedJoined(JoiningState joined) {
        ClosedElement closed = getClosed();
        if (closed == null) {
            return;
        }
        this.joining = null;
        if (!joined.getDelegateClose()) {
            joined.getDelegatedTo().flush();
            joined.complete();
            return;
        }
        ReadWriteBufferState state = joined.getDelegatedTo().getState();
        boolean z = (state instanceof ReadWriteBufferState.Writing) || (state instanceof ReadWriteBufferState.ReadingWriting);
        if (closed.getCause() == null && z) {
            joined.getDelegatedTo().flush();
        } else {
            joined.getDelegatedTo().close(closed.getCause());
        }
        joined.complete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void flushImpl(int minWriteSize) {
        ReadWriteBufferState state;
        ReadWriteBufferState.Terminated terminated;
        ByteBufferChannel delegatedTo;
        JoiningState joiningState = this.joining;
        if (joiningState != null && (delegatedTo = joiningState.getDelegatedTo()) != null) {
            delegatedTo.flush();
        }
        do {
            state = getState();
            terminated = ReadWriteBufferState.Terminated.INSTANCE;
            if (state == terminated) {
                return;
            } else {
                state.capacity.flush();
            }
        } while (state != getState());
        int i10 = state.capacity._availableForWrite$internal;
        if (state.capacity._availableForRead$internal >= 1) {
            resumeReadOp();
        }
        JoiningState joiningState2 = this.joining;
        if (i10 >= minWriteSize) {
            if (joiningState2 == null || getState() == terminated) {
                resumeWriteOp();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClosedElement getClosed() {
        return (ClosedElement) this._closed;
    }

    private final d<Boolean> getReadOp() {
        return (d) this._readOp;
    }

    private static /* synthetic */ void getReadSession$annotations() {
    }

    private final ReadWriteBufferState getState() {
        return (ReadWriteBufferState) this._state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final d<t0> getWriteOp() {
        return (d) this._writeOp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object joinFromSuspend(io.ktor.utils.io.ByteBufferChannel r11, boolean r12, io.ktor.utils.io.internal.JoiningState r13, d7.d<? super x6.t0> r14) {
        /*
            r10 = this;
            boolean r0 = r14 instanceof io.ktor.utils.io.ByteBufferChannel.C19561
            if (r0 == 0) goto L14
            r0 = r14
            io.ktor.utils.io.ByteBufferChannel$joinFromSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19561) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            io.ktor.utils.io.ByteBufferChannel$joinFromSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$joinFromSuspend$1
            r0.<init>(r14)
            goto L12
        L1a:
            java.lang.Object r14 = r6.result
            int r0 = r6.label
            x6.t0 r7 = x6.t0.f22605a
            r8 = 2
            r1 = 1
            e7.a r9 = e7.a.f15033i
            if (r0 == 0) goto L44
            if (r0 == r1) goto L36
            if (r0 != r8) goto L2e
            k2.c.G(r14)
            goto L7d
        L2e:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L36:
            boolean r12 = r6.Z$0
            java.lang.Object r11 = r6.L$1
            io.ktor.utils.io.ByteBufferChannel r11 = (io.ktor.utils.io.ByteBufferChannel) r11
            java.lang.Object r13 = r6.L$0
            io.ktor.utils.io.ByteBufferChannel r13 = (io.ktor.utils.io.ByteBufferChannel) r13
            k2.c.G(r14)
            goto L60
        L44:
            k2.c.G(r14)
            r6.L$0 = r10
            r6.L$1 = r11
            r6.Z$0 = r12
            r6.label = r1
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r1 = r10
            r2 = r11
            r5 = r13
            java.lang.Object r11 = r1.copyDirect$ktor_io(r2, r3, r5, r6)
            if (r11 != r9) goto L5e
            goto L7c
        L5e:
            r13 = r10
            r11 = r2
        L60:
            if (r12 == 0) goto L6c
            boolean r12 = r11.isClosedForRead()
            if (r12 == 0) goto L6c
            io.ktor.utils.io.ByteWriteChannelKt.close(r13)
            return r7
        L6c:
            r13.flush()
            r12 = 0
            r6.L$0 = r12
            r6.L$1 = r12
            r6.label = r8
            java.lang.Object r11 = r11.awaitClose(r6)
            if (r11 != r9) goto L7d
        L7c:
            return r9
        L7d:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.joinFromSuspend(io.ktor.utils.io.ByteBufferChannel, boolean, io.ktor.utils.io.internal.JoiningState, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0171  */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <R> java.lang.Object lookAheadSuspend$suspendImpl(io.ktor.utils.io.ByteBufferChannel r7, r7.p<? super io.ktor.utils.io.LookAheadSuspendSession, ? super d7.d<? super R>, ? extends java.lang.Object> r8, d7.d<? super R> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 408
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.lookAheadSuspend$suspendImpl(io.ktor.utils.io.ByteBufferChannel, r7.p, d7.d):java.lang.Object");
    }

    private final ReadWriteBufferState.Initial newBuffer() {
        ReadWriteBufferState.Initial initialBorrow = this.pool.borrow();
        initialBorrow.capacity.resetForWrite();
        return initialBorrow;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX INFO: renamed from: peekTo-lBXzO7A$suspendImpl, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object m6733peekTolBXzO7A$suspendImpl(io.ktor.utils.io.ByteBufferChannel r13, java.nio.ByteBuffer r14, long r15, long r17, long r19, long r21, d7.d<? super java.lang.Long> r23) {
        /*
            r0 = r23
            boolean r1 = r0 instanceof io.ktor.utils.io.ByteBufferChannel$peekTo$1
            if (r1 == 0) goto L15
            r1 = r0
            io.ktor.utils.io.ByteBufferChannel$peekTo$1 r1 = (io.ktor.utils.io.ByteBufferChannel$peekTo$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.label = r2
            goto L1a
        L15:
            io.ktor.utils.io.ByteBufferChannel$peekTo$1 r1 = new io.ktor.utils.io.ByteBufferChannel$peekTo$1
            r1.<init>(r13, r0)
        L1a:
            java.lang.Object r0 = r1.result
            int r2 = r1.label
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            java.lang.Object r13 = r1.L$0
            kotlin.jvm.internal.i0 r13 = (kotlin.jvm.internal.i0) r13
            k2.c.G(r0)     // Catch: java.io.EOFException -> L5e
            goto L5e
        L2b:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L33:
            k2.c.G(r0)
            kotlin.jvm.internal.i0 r12 = new kotlin.jvm.internal.i0
            r12.<init>()
            long r4 = r19 + r17
            r6 = 4088(0xff8, double:2.0197E-320)
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 <= 0) goto L44
            r4 = r6
        L44:
            int r0 = (int) r4
            io.ktor.utils.io.ByteBufferChannel$peekTo$2 r4 = new io.ktor.utils.io.ByteBufferChannel$peekTo$2     // Catch: java.io.EOFException -> L5d
            r9 = r14
            r10 = r15
            r5 = r17
            r7 = r21
            r4.<init>(r5, r7, r9, r10, r12)     // Catch: java.io.EOFException -> L5d
            r1.L$0 = r12     // Catch: java.io.EOFException -> L5d
            r1.label = r3     // Catch: java.io.EOFException -> L5d
            java.lang.Object r13 = r13.read(r0, r4, r1)     // Catch: java.io.EOFException -> L5d
            e7.a r14 = e7.a.f15033i
            if (r13 != r14) goto L5d
            return r14
        L5d:
            r13 = r12
        L5e:
            int r13 = r13.f19744i
            long r13 = (long) r13
            java.lang.Long r0 = new java.lang.Long
            r0.<init>(r13)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.m6733peekTolBXzO7A$suspendImpl(io.ktor.utils.io.ByteBufferChannel, java.nio.ByteBuffer, long, long, long, long, d7.d):java.lang.Object");
    }

    private final void prepareBuffer(ByteBuffer byteBuffer, int i10, int i11) {
        if (i10 < 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (i11 < 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        int iCapacity = byteBuffer.capacity() - this.reservedSize;
        int i12 = i11 + i10;
        if (i12 <= iCapacity) {
            iCapacity = i12;
        }
        byteBuffer.limit(iCapacity);
        byteBuffer.position(i10);
    }

    public static /* synthetic */ Object read$suspendImpl(ByteBufferChannel byteBufferChannel, int i10, l<? super ByteBuffer, t0> lVar, d<? super t0> dVar) throws Throwable {
        int i11;
        t0 t0Var = t0.f22605a;
        if (i10 < 0) {
            throw new IllegalArgumentException("min should be positive or zero");
        }
        ByteBuffer byteBuffer = byteBufferChannel.setupStateForRead();
        boolean z = false;
        if (byteBuffer != null) {
            RingBufferCapacity ringBufferCapacity = byteBufferChannel.getState().capacity;
            try {
                if (ringBufferCapacity._availableForRead$internal != 0 && (i11 = ringBufferCapacity._availableForRead$internal) > 0 && i11 >= i10) {
                    int iPosition = byteBuffer.position();
                    int iLimit = byteBuffer.limit();
                    lVar.invoke(byteBuffer);
                    if (iLimit != byteBuffer.limit()) {
                        throw new IllegalStateException("Buffer limit modified.");
                    }
                    int iPosition2 = byteBuffer.position() - iPosition;
                    if (iPosition2 < 0) {
                        throw new IllegalStateException("Position has been moved backward: pushback is not supported.");
                    }
                    if (!ringBufferCapacity.tryReadExact(iPosition2)) {
                        throw new IllegalStateException("Check failed.");
                    }
                    byteBufferChannel.bytesRead(byteBuffer, ringBufferCapacity, iPosition2);
                    z = true;
                }
            } finally {
                byteBufferChannel.restoreStateAfterRead();
                byteBufferChannel.tryTerminate$ktor_io();
            }
        }
        if (!z) {
            if (byteBufferChannel.isClosedForRead() && i10 > 0) {
                throw new EOFException(androidx.compose.foundation.c.o(i10, "Got EOF but at least ", " bytes were expected"));
            }
            Object blockSuspend = byteBufferChannel.readBlockSuspend(i10, lVar, dVar);
            if (blockSuspend == a.f15033i) {
                return blockSuspend;
            }
        }
        return t0Var;
    }

    private final int readAsMuchAsPossible(ByteBuffer dst) throws Throwable {
        ByteBuffer byteBuffer = setupStateForRead();
        int i10 = 0;
        if (byteBuffer == null) {
            return 0;
        }
        RingBufferCapacity ringBufferCapacity = getState().capacity;
        try {
            if (ringBufferCapacity._availableForRead$internal != 0) {
                int iCapacity = byteBuffer.capacity() - this.reservedSize;
                while (true) {
                    int iRemaining = dst.remaining();
                    if (iRemaining == 0) {
                        break;
                    }
                    int i11 = this.readPosition;
                    int iTryReadAtMost = ringBufferCapacity.tryReadAtMost(Math.min(iCapacity - i11, iRemaining));
                    if (iTryReadAtMost == 0) {
                        break;
                    }
                    byteBuffer.limit(i11 + iTryReadAtMost);
                    byteBuffer.position(i11);
                    dst.put(byteBuffer);
                    bytesRead(byteBuffer, ringBufferCapacity, iTryReadAtMost);
                    i10 += iTryReadAtMost;
                }
            }
            return i10;
        } finally {
            restoreStateAfterRead();
            tryTerminate$ktor_io();
        }
    }

    public static /* synthetic */ int readAsMuchAsPossible$default(ByteBufferChannel byteBufferChannel, Buffer buffer, int i10, int i11, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readAsMuchAsPossible");
        }
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = buffer.getLimit() - buffer.getWritePosition();
        }
        return byteBufferChannel.readAsMuchAsPossible(buffer, i10, i11);
    }

    public static Object readAvailable$suspendImpl(ByteBufferChannel byteBufferChannel, byte[] bArr, int i10, int i11, d<? super Integer> dVar) throws Throwable {
        int asMuchAsPossible = byteBufferChannel.readAsMuchAsPossible(bArr, i10, i11);
        if (asMuchAsPossible == 0 && byteBufferChannel.getClosed() != null) {
            asMuchAsPossible = byteBufferChannel.getState().capacity.flush() ? byteBufferChannel.readAsMuchAsPossible(bArr, i10, i11) : -1;
        } else if (asMuchAsPossible <= 0 && i11 != 0) {
            return byteBufferChannel.readAvailableSuspend(bArr, i10, i11, dVar);
        }
        return new Integer(asMuchAsPossible);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readAvailableSuspend(byte[] r6, int r7, int r8, d7.d<? super java.lang.Integer> r9) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteBufferChannel.C19581
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19581) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L42
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r9)
            return r9
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            int r8 = r0.I$1
            int r7 = r0.I$0
            java.lang.Object r6 = r0.L$1
            byte[] r6 = (byte[]) r6
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r1 = (io.ktor.utils.io.ByteBufferChannel) r1
            k2.c.G(r9)
            goto L57
        L42:
            k2.c.G(r9)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r0.label = r3
            java.lang.Object r9 = r5.readSuspend(r3, r0)
            if (r9 != r4) goto L56
            goto L73
        L56:
            r1 = r5
        L57:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 != 0) goto L66
            java.lang.Integer r6 = new java.lang.Integer
            r7 = -1
            r6.<init>(r7)
            return r6
        L66:
            r9 = 0
            r0.L$0 = r9
            r0.L$1 = r9
            r0.label = r2
            java.lang.Object r6 = r1.readAvailable(r6, r7, r8, r0)
            if (r6 != r4) goto L74
        L73:
            return r4
        L74:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readAvailableSuspend(byte[], int, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readBlockSuspend(int r7, r7.l<? super java.nio.ByteBuffer, x6.t0> r8, d7.d<? super x6.t0> r9) throws java.lang.Throwable {
        /*
            r6 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteBufferChannel.C19591
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.utils.io.ByteBufferChannel$readBlockSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19591) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readBlockSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readBlockSuspend$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            x6.t0 r2 = x6.t0.f22605a
            r3 = 2
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L42
            if (r1 == r4) goto L34
            if (r1 != r3) goto L2c
            k2.c.G(r9)
            goto L81
        L2c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L34:
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$1
            r7.l r8 = (r7.l) r8
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r1 = (io.ktor.utils.io.ByteBufferChannel) r1
            k2.c.G(r9)
            goto L5a
        L42:
            k2.c.G(r9)
            if (r7 >= r4) goto L49
            r9 = r4
            goto L4a
        L49:
            r9 = r7
        L4a:
            r0.L$0 = r6
            r0.L$1 = r8
            r0.I$0 = r7
            r0.label = r4
            java.lang.Object r9 = r6.readSuspend(r9, r0)
            if (r9 != r5) goto L59
            goto L80
        L59:
            r1 = r6
        L5a:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 != 0) goto L73
            if (r7 > 0) goto L65
            return r2
        L65:
            java.io.EOFException r8 = new java.io.EOFException
            java.lang.String r9 = "Got EOF but at least "
            java.lang.String r0 = " bytes were expected"
            java.lang.String r7 = androidx.compose.foundation.c.o(r7, r9, r0)
            r8.<init>(r7)
            throw r8
        L73:
            r9 = 0
            r0.L$0 = r9
            r0.L$1 = r9
            r0.label = r3
            java.lang.Object r7 = r1.read(r7, r8, r0)
            if (r7 != r5) goto L81
        L80:
            return r5
        L81:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readBlockSuspend(int, r7.l, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object readFully$suspendImpl(ByteBufferChannel byteBufferChannel, ChunkBuffer chunkBuffer, int i10, d<? super t0> dVar) {
        Object fullySuspend;
        int asMuchAsPossible$default = readAsMuchAsPossible$default(byteBufferChannel, chunkBuffer, 0, i10, 2, null);
        t0 t0Var = t0.f22605a;
        return (asMuchAsPossible$default != i10 && (fullySuspend = byteBufferChannel.readFullySuspend(chunkBuffer, i10 - asMuchAsPossible$default, dVar)) == a.f15033i) ? fullySuspend : t0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0052 -> B:21:0x0055). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readFullySuspend(java.nio.ByteBuffer r6, int r7, d7.d<? super java.lang.Integer> r8) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteBufferChannel.C19641
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.utils.io.ByteBufferChannel$readFullySuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19641) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readFullySuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readFullySuspend$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L37
            if (r1 != r2) goto L2f
            int r6 = r0.I$0
            java.lang.Object r7 = r0.L$1
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r1 = (io.ktor.utils.io.ByteBufferChannel) r1
            k2.c.G(r8)
            goto L55
        L2f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L37:
            k2.c.G(r8)
            r1 = r5
        L3b:
            boolean r8 = r6.hasRemaining()
            if (r8 == 0) goto L83
            r0.L$0 = r1
            r0.L$1 = r6
            r0.I$0 = r7
            r0.label = r2
            java.lang.Object r8 = r1.readSuspend(r2, r0)
            e7.a r3 = e7.a.f15033i
            if (r8 != r3) goto L52
            return r3
        L52:
            r4 = r7
            r7 = r6
            r6 = r4
        L55:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L66
            int r8 = r1.readAsMuchAsPossible(r7)
            int r6 = r6 + r8
            r4 = r7
            r7 = r6
            r6 = r4
            goto L3b
        L66:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r6 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "Unexpected EOF: expected "
            r8.<init>(r0)
            int r7 = r7.remaining()
            r8.append(r7)
            java.lang.String r7 = " more bytes"
            r8.append(r7)
            java.lang.String r7 = r8.toString()
            r6.<init>(r7)
            throw r6
        L83:
            java.lang.Integer r6 = new java.lang.Integer
            r6.<init>(r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readFullySuspend(java.nio.ByteBuffer, int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object readPacket$suspendImpl(ByteBufferChannel byteBufferChannel, int i10, d<? super ByteReadPacket> dVar) throws Throwable {
        Throwable cause;
        ClosedElement closed = byteBufferChannel.getClosed();
        if (closed != null && (cause = closed.getCause()) != null) {
            ByteBufferChannelKt.rethrowClosed(cause);
            throw new KotlinNothingValueException();
        }
        if (i10 == 0) {
            return ByteReadPacket.INSTANCE.getEmpty();
        }
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
        ByteBuffer byteBufferBorrow = ObjectPoolKt.getBufferPool().borrow();
        while (i10 > 0) {
            try {
                byteBufferBorrow.clear();
                if (byteBufferBorrow.remaining() > i10) {
                    byteBufferBorrow.limit(i10);
                }
                int asMuchAsPossible = byteBufferChannel.readAsMuchAsPossible(byteBufferBorrow);
                if (asMuchAsPossible == 0) {
                    break;
                }
                byteBufferBorrow.flip();
                OutputArraysJVMKt.writeFully(bytePacketBuilder, byteBufferBorrow);
                i10 -= asMuchAsPossible;
            } catch (Throwable th) {
                ObjectPoolKt.getBufferPool().recycle(byteBufferBorrow);
                bytePacketBuilder.release();
                throw th;
            }
        }
        if (i10 != 0) {
            return byteBufferChannel.readPacketSuspend(i10, bytePacketBuilder, byteBufferBorrow, dVar);
        }
        ObjectPoolKt.getBufferPool().recycle(byteBufferBorrow);
        return bytePacketBuilder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0046 A[Catch: all -> 0x0053, TRY_ENTER, TryCatch #0 {all -> 0x0053, blocks: (B:29:0x006b, B:20:0x0046, B:22:0x004f, B:25:0x0058, B:30:0x0079), top: B:38:0x006b }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0079 A[Catch: all -> 0x0053, TRY_LEAVE, TryCatch #0 {all -> 0x0053, blocks: (B:29:0x006b, B:20:0x0046, B:22:0x004f, B:25:0x0058, B:30:0x0079), top: B:38:0x006b }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0068 -> B:38:0x006b). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readPacketSuspend(int r6, io.ktor.utils.io.core.BytePacketBuilder r7, java.nio.ByteBuffer r8, d7.d<? super io.ktor.utils.io.core.ByteReadPacket> r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteBufferChannel.C19691
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.utils.io.ByteBufferChannel$readPacketSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19691) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readPacketSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readPacketSuspend$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L40
            if (r1 != r2) goto L38
            int r6 = r0.I$0
            java.lang.Object r7 = r0.L$2
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r8 = r0.L$1
            io.ktor.utils.io.core.BytePacketBuilder r8 = (io.ktor.utils.io.core.BytePacketBuilder) r8
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r1 = (io.ktor.utils.io.ByteBufferChannel) r1
            k2.c.G(r9)     // Catch: java.lang.Throwable -> L36
            r4 = r8
            r8 = r7
            r7 = r4
            goto L6b
        L36:
            r6 = move-exception
            goto L85
        L38:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L40:
            k2.c.G(r9)
            r1 = r5
        L44:
            if (r6 <= 0) goto L79
            r8.clear()     // Catch: java.lang.Throwable -> L53
            int r9 = r8.remaining()     // Catch: java.lang.Throwable -> L53
            if (r9 <= r6) goto L58
            r8.limit(r6)     // Catch: java.lang.Throwable -> L53
            goto L58
        L53:
            r6 = move-exception
            r4 = r8
            r8 = r7
            r7 = r4
            goto L85
        L58:
            r0.L$0 = r1     // Catch: java.lang.Throwable -> L53
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L53
            r0.L$2 = r8     // Catch: java.lang.Throwable -> L53
            r0.I$0 = r6     // Catch: java.lang.Throwable -> L53
            r0.label = r2     // Catch: java.lang.Throwable -> L53
            java.lang.Object r9 = r1.readFully(r8, r0)     // Catch: java.lang.Throwable -> L53
            e7.a r3 = e7.a.f15033i
            if (r9 != r3) goto L6b
            return r3
        L6b:
            java.lang.Number r9 = (java.lang.Number) r9     // Catch: java.lang.Throwable -> L53
            int r9 = r9.intValue()     // Catch: java.lang.Throwable -> L53
            r8.flip()     // Catch: java.lang.Throwable -> L53
            io.ktor.utils.io.core.OutputArraysJVMKt.writeFully(r7, r8)     // Catch: java.lang.Throwable -> L53
            int r6 = r6 - r9
            goto L44
        L79:
            io.ktor.utils.io.core.ByteReadPacket r6 = r7.build()     // Catch: java.lang.Throwable -> L53
            io.ktor.utils.io.pool.ObjectPool r7 = io.ktor.utils.io.internal.ObjectPoolKt.getBufferPool()
            r7.recycle(r8)
            return r6
        L85:
            r8.release()     // Catch: java.lang.Throwable -> L89
            throw r6     // Catch: java.lang.Throwable -> L89
        L89:
            r6 = move-exception
            io.ktor.utils.io.pool.ObjectPool r8 = io.ktor.utils.io.internal.ObjectPoolKt.getBufferPool()
            r8.recycle(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readPacketSuspend(int, io.ktor.utils.io.core.BytePacketBuilder, java.nio.ByteBuffer, d7.d):java.lang.Object");
    }

    private final <T extends Number> Object readPrimitive(int i10, l<? super ByteBuffer, ? extends T> lVar, d<? super T> dVar) throws Throwable {
        Object obj;
        do {
            ByteBuffer byteBuffer = setupStateForRead();
            boolean zBooleanValue = false;
            if (byteBuffer != null) {
                RingBufferCapacity ringBufferCapacity = getState().capacity;
                try {
                    if (ringBufferCapacity._availableForRead$internal == 0) {
                        restoreStateAfterRead();
                        tryTerminate$ktor_io();
                        obj = null;
                    } else {
                        if (ringBufferCapacity.tryReadExact(i10)) {
                            if (byteBuffer.remaining() < i10) {
                                rollBytes(byteBuffer, i10);
                            }
                            Object objInvoke = lVar.invoke(byteBuffer);
                            bytesRead(byteBuffer, ringBufferCapacity, i10);
                            zBooleanValue = true;
                            obj = objInvoke;
                        } else {
                            obj = null;
                        }
                        zBooleanValue = Boolean.valueOf(zBooleanValue).booleanValue();
                        restoreStateAfterRead();
                        tryTerminate$ktor_io();
                    }
                } catch (Throwable th) {
                    restoreStateAfterRead();
                    tryTerminate$ktor_io();
                    throw th;
                }
            } else {
                obj = null;
            }
            if (zBooleanValue) {
                if (obj != null) {
                    return (Number) obj;
                }
                kotlin.jvm.internal.p.i("result");
                throw null;
            }
        } while (((Boolean) readSuspend(i10, dVar)).booleanValue());
        throw new ClosedReceiveChannelException(androidx.compose.foundation.c.o(i10, "EOF while ", " bytes expected"));
    }

    public static /* synthetic */ Object readRemaining$suspendImpl(ByteBufferChannel byteBufferChannel, long j10, d<? super ByteReadPacket> dVar) throws Throwable {
        if (!byteBufferChannel.isClosedForWrite()) {
            return byteBufferChannel.readRemainingSuspend(j10, dVar);
        }
        Throwable closedCause = byteBufferChannel.getClosedCause();
        if (closedCause == null) {
            return byteBufferChannel.remainingPacket(j10);
        }
        ByteBufferChannelKt.rethrowClosed(closedCause);
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006e A[Catch: all -> 0x0039, TryCatch #1 {all -> 0x0039, blocks: (B:12:0x0035, B:33:0x00a2, B:38:0x00b0, B:21:0x005e, B:23:0x006e, B:24:0x0072, B:26:0x0086, B:28:0x008c), top: B:54:0x0035, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0086 A[Catch: all -> 0x0039, TryCatch #1 {all -> 0x0039, blocks: (B:12:0x0035, B:33:0x00a2, B:38:0x00b0, B:21:0x005e, B:23:0x006e, B:24:0x0072, B:26:0x0086, B:28:0x008c), top: B:54:0x0035, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b0 A[Catch: all -> 0x0039, TRY_LEAVE, TryCatch #1 {all -> 0x0039, blocks: (B:12:0x0035, B:33:0x00a2, B:38:0x00b0, B:21:0x005e, B:23:0x006e, B:24:0x0072, B:26:0x0086, B:28:0x008c), top: B:54:0x0035, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b5 A[Catch: all -> 0x00c3, TRY_ENTER, TryCatch #0 {all -> 0x00c3, blocks: (B:40:0x00b5, B:42:0x00be, B:46:0x00c7, B:47:0x00c8, B:48:0x00cb, B:12:0x0035, B:33:0x00a2, B:38:0x00b0, B:21:0x005e, B:23:0x006e, B:24:0x0072, B:26:0x0086, B:28:0x008c), top: B:53:0x001d, inners: #1 }] */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v3, types: [io.ktor.utils.io.core.Output] */
    /* JADX WARN: Type inference failed for: r3v10, types: [io.ktor.utils.io.core.BytePacketBuilder] */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v7, types: [io.ktor.utils.io.core.BytePacketBuilder] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x00a1 -> B:33:0x00a2). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x00ad -> B:37:0x00ae). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readRemainingSuspend(long r11, d7.d<? super io.ktor.utils.io.core.ByteReadPacket> r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readRemainingSuspend(long, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object readSuspend(int i10, d<? super Boolean> dVar) throws Throwable {
        if (getState().capacity._availableForRead$internal >= i10) {
            return Boolean.TRUE;
        }
        ClosedElement closed = getClosed();
        if (closed == null) {
            return i10 == 1 ? readSuspendImpl(1, dVar) : readSuspendLoop(i10, dVar);
        }
        Throwable cause = closed.getCause();
        if (cause != null) {
            ByteBufferChannelKt.rethrowClosed(cause);
            throw new KotlinNothingValueException();
        }
        RingBufferCapacity ringBufferCapacity = getState().capacity;
        boolean z = ringBufferCapacity.flush() && ringBufferCapacity._availableForRead$internal >= i10;
        if (getReadOp() == null) {
            return Boolean.valueOf(z);
        }
        throw new IllegalStateException("Read operation is already in progress");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readSuspendImpl(int r5, d7.d<? super java.lang.Boolean> r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteBufferChannel.C19731
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteBufferChannel$readSuspendImpl$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19731) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readSuspendImpl$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readSuspendImpl$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r5 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r5 = (io.ktor.utils.io.ByteBufferChannel) r5
            k2.c.G(r6)     // Catch: java.lang.Throwable -> L2b
            return r6
        L2b:
            r6 = move-exception
            goto L6d
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            k2.c.G(r6)
            io.ktor.utils.io.internal.ReadWriteBufferState r6 = r4.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r2 = r6.capacity
            int r2 = r2._availableForRead$internal
            if (r2 >= r5) goto L72
            io.ktor.utils.io.internal.JoiningState r2 = r4.joining
            if (r2 == 0) goto L54
            d7.d r2 = r4.getWriteOp()
            if (r2 == 0) goto L54
            io.ktor.utils.io.internal.ReadWriteBufferState$IdleEmpty r2 = io.ktor.utils.io.internal.ReadWriteBufferState.IdleEmpty.INSTANCE
            if (r6 == r2) goto L72
            boolean r6 = r6 instanceof io.ktor.utils.io.internal.ReadWriteBufferState.IdleNonEmpty
            if (r6 != 0) goto L72
        L54:
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L6b
            r0.I$0 = r5     // Catch: java.lang.Throwable -> L6b
            r0.label = r3     // Catch: java.lang.Throwable -> L6b
            io.ktor.utils.io.internal.CancellableReusableContinuation<java.lang.Boolean> r6 = r4.readSuspendContinuationCache     // Catch: java.lang.Throwable -> L6b
            r4.suspensionForSize(r5, r6)     // Catch: java.lang.Throwable -> L6b
            d7.d r5 = t7.a.A(r0)     // Catch: java.lang.Throwable -> L6b
            java.lang.Object r5 = r6.completeSuspendBlock(r5)     // Catch: java.lang.Throwable -> L6b
            if (r5 != r1) goto L6a
            return r1
        L6a:
            return r5
        L6b:
            r6 = move-exception
            r5 = r4
        L6d:
            r0 = 0
            r5.setReadOp(r0)
            throw r6
        L72:
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readSuspendImpl(int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x008e -> B:40:0x0091). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readSuspendLoop(int r5, d7.d<? super java.lang.Boolean> r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteBufferChannel.C19741
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteBufferChannel$readSuspendLoop$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19741) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readSuspendLoop$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readSuspendLoop$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            int r5 = r0.I$0
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            k2.c.G(r6)
            goto L91
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            k2.c.G(r6)
            r2 = r4
        L39:
            io.ktor.utils.io.internal.ReadWriteBufferState r6 = r2.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r6 = r6.capacity
            int r6 = r6._availableForRead$internal
            if (r6 < r5) goto L46
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            return r5
        L46:
            io.ktor.utils.io.internal.ClosedElement r6 = r2.getClosed()
            if (r6 == 0) goto L84
            java.lang.Throwable r0 = r6.getCause()
            if (r0 != 0) goto L77
            io.ktor.utils.io.internal.ReadWriteBufferState r6 = r2.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r6 = r6.capacity
            boolean r0 = r6.flush()
            if (r0 == 0) goto L63
            int r6 = r6._availableForRead$internal
            if (r6 < r5) goto L63
            goto L64
        L63:
            r3 = 0
        L64:
            d7.d r5 = r2.getReadOp()
            if (r5 != 0) goto L6f
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r3)
            return r5
        L6f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "Read operation is already in progress"
            r5.<init>(r6)
            throw r5
        L77:
            java.lang.Throwable r5 = r6.getCause()
            io.ktor.utils.io.ByteBufferChannelKt.access$rethrowClosed(r5)
            kotlin.KotlinNothingValueException r5 = new kotlin.KotlinNothingValueException
            r5.<init>()
            throw r5
        L84:
            r0.L$0 = r2
            r0.I$0 = r5
            r0.label = r3
            java.lang.Object r6 = r2.readSuspendImpl(r5, r0)
            if (r6 != r1) goto L91
            return r1
        L91:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 != 0) goto L39
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readSuspendLoop(int, d7.d):java.lang.Object");
    }

    private final boolean readSuspendPredicate(int size) {
        ReadWriteBufferState state = getState();
        if (state.capacity._availableForRead$internal >= size) {
            return false;
        }
        if (this.joining == null || getWriteOp() == null) {
            return true;
        }
        return (state == ReadWriteBufferState.IdleEmpty.INSTANCE || (state instanceof ReadWriteBufferState.IdleNonEmpty)) ? false : true;
    }

    @x6.e
    public static /* synthetic */ Object readSuspendableSession$suspendImpl(ByteBufferChannel byteBufferChannel, p<? super SuspendableReadSession, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) {
        Object objLookAheadSuspend = byteBufferChannel.lookAheadSuspend(new C19752(pVar, byteBufferChannel, null), dVar);
        return objLookAheadSuspend == a.f15033i ? objLookAheadSuspend : t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readUTF8Line$suspendImpl(io.ktor.utils.io.ByteBufferChannel r5, int r6, d7.d<? super java.lang.String> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteBufferChannel.C19761
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.ByteBufferChannel$readUTF8Line$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19761) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readUTF8Line$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readUTF8Line$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r5 = r0.L$0
            java.lang.StringBuilder r5 = (java.lang.StringBuilder) r5
            k2.c.G(r7)
            goto L49
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r7)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r0.L$0 = r7
            r0.label = r2
            java.lang.Object r5 = r5.readUTF8LineTo(r7, r6, r0)
            e7.a r6 = e7.a.f15033i
            if (r5 != r6) goto L46
            return r6
        L46:
            r4 = r7
            r7 = r5
            r5 = r4
        L49:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r6 = r7.booleanValue()
            if (r6 != 0) goto L53
            r5 = 0
            return r5
        L53:
            java.lang.String r5 = r5.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readUTF8Line$suspendImpl(io.ktor.utils.io.ByteBufferChannel, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object readUTF8LineToAscii(Appendable appendable, int i10, d<? super Boolean> dVar) throws Throwable {
        if (getState() != ReadWriteBufferState.Terminated.INSTANCE) {
            return readUTF8LineToUtf8Suspend(appendable, i10, dVar);
        }
        Throwable closedCause = getClosedCause();
        if (closedCause == null) {
            return Boolean.FALSE;
        }
        throw closedCause;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(9:(1:86)|35|36|78|37|38|88|39|(9:42|20|43|23|(7:25|34|49|(0)|52|(1:54)|63)(0)|64|(0)(0)|74|75)) */
    /* JADX WARN: Can't wrap try/catch for region: R(9:86|35|36|78|37|38|88|39|(9:42|20|43|23|(7:25|34|49|(0)|52|(1:54)|63)(0)|64|(0)(0)|74|75)) */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0118, code lost:
    
        r19 = r7;
        r20 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x011c, code lost:
    
        r7 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x011f, code lost:
    
        r11 = r16;
        r3 = r17;
        r6 = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0167, code lost:
    
        if (r1.read(1, r4, r0) == r9) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x007c, code lost:
    
        r7 = r3;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x010f -> B:20:0x007c). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readUTF8LineToUtf8Suspend(java.lang.Appendable r22, int r23, d7.d<? super java.lang.Boolean> r24) {
        /*
            Method dump skipped, instruction units count: 394
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readUTF8LineToUtf8Suspend(java.lang.Appendable, int, d7.d):java.lang.Object");
    }

    private final boolean reading(p<? super ByteBuffer, ? super RingBufferCapacity, Boolean> block) throws Throwable {
        ByteBuffer byteBuffer = setupStateForRead();
        if (byteBuffer == null) {
            return false;
        }
        RingBufferCapacity ringBufferCapacity = getState().capacity;
        try {
            if (ringBufferCapacity._availableForRead$internal == 0) {
                return false;
            }
            return ((Boolean) block.invoke(byteBuffer, ringBufferCapacity)).booleanValue();
        } finally {
            restoreStateAfterRead();
            tryTerminate$ktor_io();
        }
    }

    private final void releaseBuffer(ReadWriteBufferState.Initial buffer) {
        this.pool.recycle(buffer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ByteReadPacket remainingPacket(long limit) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
        try {
            ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(bytePacketBuilder, 1, null);
            while (true) {
                try {
                    if (chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition() > limit) {
                        chunkBufferPrepareWriteHead.resetForWrite((int) limit);
                    }
                    limit -= (long) readAsMuchAsPossible$default(this, chunkBufferPrepareWriteHead, 0, 0, 6, null);
                    if (limit <= 0 || isClosedForRead()) {
                        break;
                    }
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(bytePacketBuilder, 1, chunkBufferPrepareWriteHead);
                } catch (Throwable th) {
                    bytePacketBuilder.afterHeadWrite();
                    throw th;
                }
            }
            bytePacketBuilder.afterHeadWrite();
            return bytePacketBuilder.build();
        } catch (Throwable th2) {
            bytePacketBuilder.release();
            throw th2;
        }
    }

    private final ByteBufferChannel resolveDelegation(ByteBufferChannel current, JoiningState joining) {
        while (current.getState() == ReadWriteBufferState.Terminated.INSTANCE) {
            current = joining.getDelegatedTo();
            joining = current.joining;
            if (joining == null) {
                return current;
            }
        }
        return null;
    }

    private final void restoreStateAfterRead() {
        ReadWriteBufferState readWriteBufferStateStopReading$ktor_io;
        ReadWriteBufferState readWriteBufferState = null;
        loop0: while (true) {
            Object obj = this._state;
            ReadWriteBufferState readWriteBufferState2 = (ReadWriteBufferState) obj;
            ReadWriteBufferState.IdleNonEmpty idleNonEmpty = (ReadWriteBufferState.IdleNonEmpty) readWriteBufferState;
            if (idleNonEmpty != null) {
                idleNonEmpty.capacity.resetForWrite();
                resumeWriteOp();
                readWriteBufferState = null;
            }
            readWriteBufferStateStopReading$ktor_io = readWriteBufferState2.stopReading$ktor_io();
            if ((readWriteBufferStateStopReading$ktor_io instanceof ReadWriteBufferState.IdleNonEmpty) && getState() == readWriteBufferState2 && readWriteBufferStateStopReading$ktor_io.capacity.tryLockForRelease()) {
                readWriteBufferStateStopReading$ktor_io = ReadWriteBufferState.IdleEmpty.INSTANCE;
                readWriteBufferState = readWriteBufferStateStopReading$ktor_io;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, readWriteBufferStateStopReading$ktor_io)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    break;
                }
            }
        }
        ReadWriteBufferState.IdleEmpty idleEmpty = ReadWriteBufferState.IdleEmpty.INSTANCE;
        if (readWriteBufferStateStopReading$ktor_io == idleEmpty) {
            ReadWriteBufferState.IdleNonEmpty idleNonEmpty2 = (ReadWriteBufferState.IdleNonEmpty) readWriteBufferState;
            if (idleNonEmpty2 != null) {
                releaseBuffer(idleNonEmpty2.getInitial());
            }
            resumeWriteOp();
            return;
        }
        if ((readWriteBufferStateStopReading$ktor_io instanceof ReadWriteBufferState.IdleNonEmpty) && readWriteBufferStateStopReading$ktor_io.capacity.isEmpty() && readWriteBufferStateStopReading$ktor_io.capacity.tryLockForRelease()) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = _state$FU;
            while (!atomicReferenceFieldUpdater2.compareAndSet(this, readWriteBufferStateStopReading$ktor_io, idleEmpty)) {
                if (atomicReferenceFieldUpdater2.get(this) != readWriteBufferStateStopReading$ktor_io) {
                    return;
                }
            }
            readWriteBufferStateStopReading$ktor_io.capacity.resetForWrite();
            releaseBuffer(((ReadWriteBufferState.IdleNonEmpty) readWriteBufferStateStopReading$ktor_io).getInitial());
            resumeWriteOp();
        }
    }

    private final void resumeClosed(Throwable cause) {
        d dVar = (d) _readOp$FU.getAndSet(this, null);
        if (dVar != null) {
            if (cause != null) {
                dVar.resumeWith(new c0(cause));
            } else {
                dVar.resumeWith(Boolean.valueOf(getState().capacity._availableForRead$internal > 0));
            }
        }
        d dVar2 = (d) _writeOp$FU.getAndSet(this, null);
        if (dVar2 != null) {
            if (cause == null) {
                cause = new ClosedWriteChannelException(ByteBufferChannelKt.DEFAULT_CLOSE_MESSAGE);
            }
            dVar2.resumeWith(new c0(cause));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resumeReadOp() {
        d dVar = (d) _readOp$FU.getAndSet(this, null);
        if (dVar != null) {
            ClosedElement closed = getClosed();
            Throwable cause = closed != null ? closed.getCause() : null;
            if (cause != null) {
                dVar.resumeWith(new c0(cause));
            } else {
                dVar.resumeWith(Boolean.TRUE);
            }
        }
    }

    private final void resumeWriteOp() {
        while (true) {
            d<t0> writeOp = getWriteOp();
            if (writeOp == null) {
                return;
            }
            ClosedElement closed = getClosed();
            if (closed == null && this.joining != null) {
                ReadWriteBufferState state = getState();
                if (!(state instanceof ReadWriteBufferState.Writing) && !(state instanceof ReadWriteBufferState.ReadingWriting) && state != ReadWriteBufferState.Terminated.INSTANCE) {
                    return;
                }
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _writeOp$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, writeOp, null)) {
                if (atomicReferenceFieldUpdater.get(this) != writeOp) {
                    break;
                }
            }
            if (closed == null) {
                writeOp.resumeWith(t0.f22605a);
                return;
            } else {
                writeOp.resumeWith(new c0(closed.getSendException()));
                return;
            }
        }
    }

    private final void rollBytes(ByteBuffer byteBuffer, int i10) {
        int iRemaining = byteBuffer.remaining();
        byteBuffer.limit(byteBuffer.position() + i10);
        int i11 = i10 - iRemaining;
        for (int i12 = 0; i12 < i11; i12++) {
            byteBuffer.put(byteBuffer.capacity() + ReservedLongIndex + i12, byteBuffer.get(i12));
        }
    }

    private final void setClosed(ClosedElement closedElement) {
        this._closed = closedElement;
    }

    private final void setReadOp(d<? super Boolean> dVar) {
        this._readOp = dVar;
    }

    private final void setWriteOp(d<? super t0> dVar) {
        this._writeOp = dVar;
    }

    private final JoiningState setupDelegateTo(ByteBufferChannel delegate, boolean delegateClose) {
        if (this == delegate) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        JoiningState joiningState = new JoiningState(delegate, delegateClose);
        this.joining = joiningState;
        ClosedElement closed = getClosed();
        if (closed == null) {
            flush();
            return joiningState;
        }
        if (closed.getCause() != null) {
            delegate.close(closed.getCause());
            return joiningState;
        }
        if (delegateClose && getState() == ReadWriteBufferState.Terminated.INSTANCE) {
            ByteWriteChannelKt.close(delegate);
            return joiningState;
        }
        delegate.flush();
        return joiningState;
    }

    private final ByteBuffer setupStateForRead() throws Throwable {
        boolean z;
        Throwable cause;
        ReadWriteBufferState readWriteBufferStateStartReading$ktor_io;
        Throwable cause2;
        do {
            Object obj = this._state;
            ReadWriteBufferState readWriteBufferState = (ReadWriteBufferState) obj;
            z = true;
            if (kotlin.jvm.internal.p.a(readWriteBufferState, ReadWriteBufferState.Terminated.INSTANCE) ? true : kotlin.jvm.internal.p.a(readWriteBufferState, ReadWriteBufferState.IdleEmpty.INSTANCE)) {
                ClosedElement closed = getClosed();
                if (closed == null || (cause = closed.getCause()) == null) {
                    return null;
                }
                ByteBufferChannelKt.rethrowClosed(cause);
                throw new KotlinNothingValueException();
            }
            ClosedElement closed2 = getClosed();
            if (closed2 != null && (cause2 = closed2.getCause()) != null) {
                ByteBufferChannelKt.rethrowClosed(cause2);
                throw new KotlinNothingValueException();
            }
            if (readWriteBufferState.capacity._availableForRead$internal != 0) {
                readWriteBufferStateStartReading$ktor_io = readWriteBufferState.startReading$ktor_io();
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
                while (true) {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, obj, readWriteBufferStateStartReading$ktor_io)) {
                        break;
                    }
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                        z = false;
                        break;
                    }
                }
            } else {
                return null;
            }
        } while (!z);
        ByteBuffer readBuffer = readWriteBufferStateStartReading$ktor_io.getReadBuffer();
        prepareBuffer(readBuffer, this.readPosition, readWriteBufferStateStartReading$ktor_io.capacity._availableForRead$internal);
        return readBuffer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldResumeReadOp() {
        if (this.joining != null) {
            return getState() == ReadWriteBufferState.IdleEmpty.INSTANCE || (getState() instanceof ReadWriteBufferState.IdleNonEmpty);
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x00d1, code lost:
    
        r7.resumeWith(java.lang.Boolean.TRUE);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.Object suspensionForSize(int r6, d7.d<? super java.lang.Boolean> r7) {
        /*
            Method dump skipped, instruction units count: 215
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.suspensionForSize(int, d7.d):java.lang.Object");
    }

    private final boolean tryCompleteJoining(JoiningState joined) {
        if (!tryReleaseBuffer(true)) {
            return false;
        }
        ensureClosedJoined(joined);
        d dVar = (d) _readOp$FU.getAndSet(this, null);
        if (dVar != null) {
            dVar.resumeWith(new c0(new IllegalStateException("Joining is in progress")));
        }
        resumeWriteOp();
        return true;
    }

    private final boolean tryReleaseBuffer(boolean forceTermination) {
        ReadWriteBufferState.Initial initial = null;
        while (true) {
            Object obj = this._state;
            ReadWriteBufferState readWriteBufferState = (ReadWriteBufferState) obj;
            ClosedElement closed = getClosed();
            if (initial != null) {
                if ((closed != null ? closed.getCause() : null) == null) {
                    initial.capacity.resetForWrite();
                }
                resumeWriteOp();
                initial = null;
            }
            ReadWriteBufferState.Terminated terminated = ReadWriteBufferState.Terminated.INSTANCE;
            if (readWriteBufferState == terminated) {
                return true;
            }
            if (readWriteBufferState != ReadWriteBufferState.IdleEmpty.INSTANCE) {
                if (closed != null && (readWriteBufferState instanceof ReadWriteBufferState.IdleNonEmpty) && (readWriteBufferState.capacity.tryLockForRelease() || closed.getCause() != null)) {
                    if (closed.getCause() != null) {
                        readWriteBufferState.capacity.forceLockForRelease();
                    }
                    initial = ((ReadWriteBufferState.IdleNonEmpty) readWriteBufferState).getInitial();
                } else {
                    if (!forceTermination || !(readWriteBufferState instanceof ReadWriteBufferState.IdleNonEmpty) || !readWriteBufferState.capacity.tryLockForRelease()) {
                        return false;
                    }
                    initial = ((ReadWriteBufferState.IdleNonEmpty) readWriteBufferState).getInitial();
                }
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, terminated)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    break;
                }
            }
            if (initial != null && getState() == ReadWriteBufferState.Terminated.INSTANCE) {
                releaseBuffer(initial);
            }
            return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0054 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int tryWritePacketPart(io.ktor.utils.io.core.ByteReadPacket r10) throws java.lang.Throwable {
        /*
            r9 = this;
            io.ktor.utils.io.internal.JoiningState r0 = r9.joining
            if (r0 == 0) goto La
            io.ktor.utils.io.ByteBufferChannel r0 = r9.resolveDelegation(r9, r0)
            if (r0 != 0) goto Lb
        La:
            r0 = r9
        Lb:
            java.nio.ByteBuffer r1 = r0.setupStateForWrite$ktor_io()
            if (r1 != 0) goto L13
            r10 = 0
            return r10
        L13:
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r0.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r2 = r2.capacity
            long r3 = r0.get_totalBytesWritten()
            io.ktor.utils.io.internal.ClosedElement r5 = r0.getClosed()     // Catch: java.lang.Throwable -> L46
            if (r5 != 0) goto L6d
            long r5 = r10.getRemaining()     // Catch: java.lang.Throwable -> L46
            int r7 = r1.remaining()     // Catch: java.lang.Throwable -> L46
            long r7 = (long) r7     // Catch: java.lang.Throwable -> L46
            long r5 = java.lang.Math.min(r5, r7)     // Catch: java.lang.Throwable -> L46
            int r5 = (int) r5     // Catch: java.lang.Throwable -> L46
            int r5 = r2.tryWriteAtMost(r5)     // Catch: java.lang.Throwable -> L46
            if (r5 <= 0) goto L48
            int r6 = r1.position()     // Catch: java.lang.Throwable -> L46
            int r6 = r6 + r5
            r1.limit(r6)     // Catch: java.lang.Throwable -> L46
            io.ktor.utils.io.core.ByteBuffersKt.readFully(r10, r1)     // Catch: java.lang.Throwable -> L46
            r0.bytesWritten(r1, r2, r5)     // Catch: java.lang.Throwable -> L46
            goto L48
        L46:
            r10 = move-exception
            goto L7a
        L48:
            boolean r10 = r2.isFull()
            if (r10 != 0) goto L54
            boolean r10 = r0.getAutoFlush()
            if (r10 == 0) goto L57
        L54:
            r0.flush()
        L57:
            if (r0 == r9) goto L66
            long r1 = r9.get_totalBytesWritten()
            long r6 = r0.get_totalBytesWritten()
            long r6 = r6 - r3
            long r6 = r6 + r1
            r9.setTotalBytesWritten$ktor_io(r6)
        L66:
            r0.restoreStateAfterWrite$ktor_io()
            r0.tryTerminate$ktor_io()
            return r5
        L6d:
            java.lang.Throwable r10 = r5.getSendException()     // Catch: java.lang.Throwable -> L46
            io.ktor.utils.io.ByteBufferChannelKt.access$rethrowClosed(r10)     // Catch: java.lang.Throwable -> L46
            kotlin.KotlinNothingValueException r10 = new kotlin.KotlinNothingValueException     // Catch: java.lang.Throwable -> L46
            r10.<init>()     // Catch: java.lang.Throwable -> L46
            throw r10     // Catch: java.lang.Throwable -> L46
        L7a:
            boolean r1 = r2.isFull()
            if (r1 != 0) goto L86
            boolean r1 = r0.getAutoFlush()
            if (r1 == 0) goto L89
        L86:
            r0.flush()
        L89:
            if (r0 == r9) goto L98
            long r1 = r9.get_totalBytesWritten()
            long r5 = r0.get_totalBytesWritten()
            long r5 = r5 - r3
            long r5 = r5 + r1
            r9.setTotalBytesWritten$ktor_io(r5)
        L98:
            r0.restoreStateAfterWrite$ktor_io()
            r0.tryTerminate$ktor_io()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.tryWritePacketPart(io.ktor.utils.io.core.ByteReadPacket):int");
    }

    private final boolean tryWritePrimitive(ByteBuffer byteBuffer, int i10, RingBufferCapacity ringBufferCapacity, l<? super ByteBuffer, t0> lVar) {
        if (!ringBufferCapacity.tryWriteExact(i10)) {
            return false;
        }
        prepareWriteBuffer$ktor_io(byteBuffer, i10);
        if (byteBuffer.remaining() < i10) {
            byteBuffer.limit(byteBuffer.capacity());
            lVar.invoke(byteBuffer);
            carry(byteBuffer);
        } else {
            lVar.invoke(byteBuffer);
        }
        bytesWritten(byteBuffer, ringBufferCapacity, i10);
        if (ringBufferCapacity.isFull() || getAutoFlush()) {
            flush();
        }
        restoreStateAfterWrite$ktor_io();
        tryTerminate$ktor_io();
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object write$suspendImpl(io.ktor.utils.io.ByteBufferChannel r5, int r6, r7.l<? super java.nio.ByteBuffer, x6.t0> r7, d7.d<? super x6.t0> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteBufferChannel.C19801
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.utils.io.ByteBufferChannel$write$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19801) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$write$1 r0 = new io.ktor.utils.io.ByteBufferChannel$write$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3b
            if (r1 != r2) goto L33
            int r5 = r0.I$0
            java.lang.Object r6 = r0.L$1
            r7.l r6 = (r7.l) r6
            java.lang.Object r7 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r7 = (io.ktor.utils.io.ByteBufferChannel) r7
            k2.c.G(r8)
            r4 = r6
            r6 = r5
            r5 = r7
            r7 = r4
            goto L44
        L33:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3b:
            k2.c.G(r8)
            if (r6 <= 0) goto L70
            r8 = 4088(0xff8, float:5.729E-42)
            if (r6 > r8) goto L5e
        L44:
            int r8 = r5.writeAvailable(r6, r7)
            if (r8 < 0) goto L4d
            x6.t0 r5 = x6.t0.f22605a
            return r5
        L4d:
            r0.L$0 = r5
            r0.L$1 = r7
            r0.I$0 = r6
            r0.label = r2
            java.lang.Object r8 = r5.awaitFreeSpaceOrDelegate(r6, r7, r0)
            e7.a r1 = e7.a.f15033i
            if (r8 != r1) goto L44
            return r1
        L5e:
            java.lang.String r5 = "Min("
            java.lang.String r7 = ") should'nt be greater than (4088)"
            java.lang.String r5 = androidx.compose.foundation.c.o(r6, r5, r7)
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r5 = r5.toString()
            r6.<init>(r5)
            throw r6
        L70:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "min should be positive"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.write$suspendImpl(io.ktor.utils.io.ByteBufferChannel, int, r7.l, d7.d):java.lang.Object");
    }

    private final int writeAsMuchAsPossible(ByteBuffer src) throws Throwable {
        ByteBufferChannel byteBufferChannelResolveDelegation;
        int iTryWriteAtMost;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (byteBufferChannelResolveDelegation = resolveDelegation(this, joiningState)) == null) {
            byteBufferChannelResolveDelegation = this;
        }
        ByteBuffer byteBuffer = byteBufferChannelResolveDelegation.setupStateForWrite$ktor_io();
        int i10 = 0;
        if (byteBuffer == null) {
            return 0;
        }
        RingBufferCapacity ringBufferCapacity = byteBufferChannelResolveDelegation.getState().capacity;
        long totalBytesWritten = byteBufferChannelResolveDelegation.get_totalBytesWritten();
        try {
            ClosedElement closed = byteBufferChannelResolveDelegation.getClosed();
            if (closed != null) {
                ByteBufferChannelKt.rethrowClosed(closed.getSendException());
                throw new KotlinNothingValueException();
            }
            int iLimit = src.limit();
            while (true) {
                int iPosition = iLimit - src.position();
                if (iPosition == 0 || (iTryWriteAtMost = ringBufferCapacity.tryWriteAtMost(Math.min(iPosition, byteBuffer.remaining()))) == 0) {
                    break;
                }
                if (iTryWriteAtMost <= 0) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                src.limit(src.position() + iTryWriteAtMost);
                byteBuffer.put(src);
                i10 += iTryWriteAtMost;
                byteBufferChannelResolveDelegation.prepareBuffer(byteBuffer, byteBufferChannelResolveDelegation.carryIndex(byteBuffer, byteBufferChannelResolveDelegation.writePosition + i10), ringBufferCapacity._availableForWrite$internal);
            }
            src.limit(iLimit);
            byteBufferChannelResolveDelegation.bytesWritten(byteBuffer, ringBufferCapacity, i10);
            if (ringBufferCapacity.isFull() || byteBufferChannelResolveDelegation.getAutoFlush()) {
                byteBufferChannelResolveDelegation.flush();
            }
            if (byteBufferChannelResolveDelegation != this) {
                setTotalBytesWritten$ktor_io((byteBufferChannelResolveDelegation.get_totalBytesWritten() - totalBytesWritten) + get_totalBytesWritten());
            }
            byteBufferChannelResolveDelegation.restoreStateAfterWrite$ktor_io();
            byteBufferChannelResolveDelegation.tryTerminate$ktor_io();
            return i10;
        } catch (Throwable th) {
            if (ringBufferCapacity.isFull() || byteBufferChannelResolveDelegation.getAutoFlush()) {
                byteBufferChannelResolveDelegation.flush();
            }
            if (byteBufferChannelResolveDelegation != this) {
                setTotalBytesWritten$ktor_io((byteBufferChannelResolveDelegation.get_totalBytesWritten() - totalBytesWritten) + get_totalBytesWritten());
            }
            byteBufferChannelResolveDelegation.restoreStateAfterWrite$ktor_io();
            byteBufferChannelResolveDelegation.tryTerminate$ktor_io();
            throw th;
        }
    }

    public static Object writeAvailable$suspendImpl(ByteBufferChannel byteBufferChannel, ByteBuffer byteBuffer, d<? super Integer> dVar) throws Throwable {
        ByteBufferChannel byteBufferChannelResolveDelegation;
        ByteBufferChannel byteBufferChannelResolveDelegation2;
        JoiningState joiningState = byteBufferChannel.joining;
        if (joiningState != null && (byteBufferChannelResolveDelegation2 = byteBufferChannel.resolveDelegation(byteBufferChannel, joiningState)) != null) {
            return byteBufferChannelResolveDelegation2.writeAvailable(byteBuffer, dVar);
        }
        int iWriteAsMuchAsPossible = byteBufferChannel.writeAsMuchAsPossible(byteBuffer);
        if (iWriteAsMuchAsPossible > 0) {
            return new Integer(iWriteAsMuchAsPossible);
        }
        JoiningState joiningState2 = byteBufferChannel.joining;
        return (joiningState2 == null || (byteBufferChannelResolveDelegation = byteBufferChannel.resolveDelegation(byteBufferChannel, joiningState2)) == null) ? byteBufferChannel.writeAvailableSuspend(byteBuffer, dVar) : byteBufferChannelResolveDelegation.writeAvailableSuspend(byteBuffer, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeAvailableSuspend(java.nio.ByteBuffer r7, d7.d<? super java.lang.Integer> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteBufferChannel.C19811
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19811) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L45
            if (r2 == r5) goto L39
            if (r2 == r4) goto L35
            if (r2 != r3) goto L2d
            k2.c.G(r8)
            return r8
        L2d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L35:
            k2.c.G(r8)
            return r8
        L39:
            java.lang.Object r7 = r0.L$1
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            k2.c.G(r8)
            goto L56
        L45:
            k2.c.G(r8)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r5
            java.lang.Object r8 = r6.writeSuspend(r5, r0)
            if (r8 != r1) goto L55
            goto L7b
        L55:
            r2 = r6
        L56:
            io.ktor.utils.io.internal.JoiningState r8 = r2.joining
            r5 = 0
            if (r8 == 0) goto L6f
            io.ktor.utils.io.ByteBufferChannel r8 = r2.resolveDelegation(r2, r8)
            if (r8 == 0) goto L6f
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r4
            java.lang.Object r7 = r8.writeAvailableSuspend(r7, r0)
            if (r7 != r1) goto L6e
            goto L7b
        L6e:
            return r7
        L6f:
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r7 = r2.writeAvailable(r7, r0)
            if (r7 != r1) goto L7c
        L7b:
            return r1
        L7c:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeAvailableSuspend(java.nio.ByteBuffer, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x007f, code lost:
    
        if (r10.writeByte(r9, r0) == r1) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x009f, code lost:
    
        if (r10.getDelegatedTo().writeByte(r9, r0) == r1) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00bc, code lost:
    
        if (r2.getDelegatedTo().writeByte(r9, r0) == r1) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0118, code lost:
    
        if (r9.getDelegatedTo().writeByte(r10, r0) == r1) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0138, code lost:
    
        if (r2.getDelegatedTo().writeByte(r8, r0) == r1) goto L64;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x00ac A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f5 A[PHI: r2 r6 r8 r9 r10
      0x00f5: PHI (r2v10 java.nio.ByteBuffer) = (r2v9 java.nio.ByteBuffer), (r2v14 java.nio.ByteBuffer) binds: [B:47:0x00f2, B:15:0x004d] A[DONT_GENERATE, DONT_INLINE]
      0x00f5: PHI (r6v2 io.ktor.utils.io.internal.RingBufferCapacity) = (r6v1 io.ktor.utils.io.internal.RingBufferCapacity), (r6v4 io.ktor.utils.io.internal.RingBufferCapacity) binds: [B:47:0x00f2, B:15:0x004d] A[DONT_GENERATE, DONT_INLINE]
      0x00f5: PHI (r8v13 io.ktor.utils.io.ByteBufferChannel) = (r8v11 io.ktor.utils.io.ByteBufferChannel), (r8v19 io.ktor.utils.io.ByteBufferChannel) binds: [B:47:0x00f2, B:15:0x004d] A[DONT_GENERATE, DONT_INLINE]
      0x00f5: PHI (r9v14 int) = (r9v12 int), (r9v21 int) binds: [B:47:0x00f2, B:15:0x004d] A[DONT_GENERATE, DONT_INLINE]
      0x00f5: PHI (r10v13 byte) = (r10v12 byte), (r10v17 byte) binds: [B:47:0x00f2, B:15:0x004d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0126 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x00f2 -> B:49:0x00f5). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeByte$suspendImpl(io.ktor.utils.io.ByteBufferChannel r8, byte r9, d7.d<? super x6.t0> r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 482
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeByte$suspendImpl(io.ktor.utils.io.ByteBufferChannel, byte, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object writeDouble$suspendImpl(ByteBufferChannel byteBufferChannel, double d4, d<? super t0> dVar) {
        Object objWriteLong = byteBufferChannel.writeLong(Double.doubleToRawLongBits(d4), dVar);
        return objWriteLong == a.f15033i ? objWriteLong : t0.f22605a;
    }

    public static /* synthetic */ Object writeFloat$suspendImpl(ByteBufferChannel byteBufferChannel, float f10, d<? super t0> dVar) {
        Object objWriteInt = byteBufferChannel.writeInt(Float.floatToRawIntBits(f10), dVar);
        return objWriteInt == a.f15033i ? objWriteInt : t0.f22605a;
    }

    public static /* synthetic */ Object writeFully$suspendImpl(ByteBufferChannel byteBufferChannel, ByteBuffer byteBuffer, d<? super t0> dVar) throws Throwable {
        Object objWriteFullySuspend;
        ByteBufferChannel byteBufferChannelResolveDelegation;
        a aVar = a.f15033i;
        t0 t0Var = t0.f22605a;
        JoiningState joiningState = byteBufferChannel.joining;
        if (joiningState == null || (byteBufferChannelResolveDelegation = byteBufferChannel.resolveDelegation(byteBufferChannel, joiningState)) == null) {
            byteBufferChannel.writeAsMuchAsPossible(byteBuffer);
            return (byteBuffer.hasRemaining() && (objWriteFullySuspend = byteBufferChannel.writeFullySuspend(byteBuffer, dVar)) == aVar) ? objWriteFullySuspend : t0Var;
        }
        Object objWriteFully = byteBufferChannelResolveDelegation.writeFully(byteBuffer, dVar);
        return objWriteFully == aVar ? objWriteFully : t0Var;
    }

    /* JADX INFO: renamed from: writeFully-JT6ljtQ$suspendImpl, reason: not valid java name */
    public static /* synthetic */ Object m6734writeFullyJT6ljtQ$suspendImpl(ByteBufferChannel byteBufferChannel, ByteBuffer byteBuffer, int i10, int i11, d<? super t0> dVar) {
        Object objWriteFully = byteBufferChannel.writeFully(Memory.m6756slice87lwejk(byteBuffer, i10, i11 - i10), dVar);
        return objWriteFully == a.f15033i ? objWriteFully : t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0074 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0054 -> B:22:0x0057). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeFullySuspend(java.nio.ByteBuffer r7, d7.d<? super x6.t0> r8) throws java.lang.Throwable {
        /*
            r6 = this;
            x6.t0 r0 = x6.t0.f22605a
            boolean r1 = r8 instanceof io.ktor.utils.io.ByteBufferChannel.C19841
            if (r1 == 0) goto L15
            r1 = r8
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$1 r1 = (io.ktor.utils.io.ByteBufferChannel.C19841) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.label = r2
            goto L1a
        L15:
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$1 r1 = new io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$1
            r1.<init>(r8)
        L1a:
            java.lang.Object r8 = r1.result
            e7.a r2 = e7.a.f15033i
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L40
            if (r3 == r5) goto L34
            if (r3 != r4) goto L2c
            k2.c.G(r8)
            goto L6f
        L2c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L34:
            java.lang.Object r7 = r1.L$1
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r3 = r1.L$0
            io.ktor.utils.io.ByteBufferChannel r3 = (io.ktor.utils.io.ByteBufferChannel) r3
            k2.c.G(r8)
            goto L57
        L40:
            k2.c.G(r8)
            r3 = r6
        L44:
            boolean r8 = r7.hasRemaining()
            if (r8 == 0) goto L74
            r1.L$0 = r3
            r1.L$1 = r7
            r1.label = r5
            java.lang.Object r8 = r3.tryWriteSuspend$ktor_io(r5, r1)
            if (r8 != r2) goto L57
            goto L6e
        L57:
            io.ktor.utils.io.internal.JoiningState r8 = r3.joining
            if (r8 == 0) goto L70
            io.ktor.utils.io.ByteBufferChannel r8 = r3.resolveDelegation(r3, r8)
            if (r8 == 0) goto L70
            r3 = 0
            r1.L$0 = r3
            r1.L$1 = r3
            r1.label = r4
            java.lang.Object r7 = r8.writeFully(r7, r1)
            if (r7 != r2) goto L6f
        L6e:
            return r2
        L6f:
            return r0
        L70:
            r3.writeAsMuchAsPossible(r7)
            goto L44
        L74:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeFullySuspend(java.nio.ByteBuffer, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x007f, code lost:
    
        if (r10.writeInt(r9, r0) == r1) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x009e, code lost:
    
        if (r8.writeInt(r9, r0) == r1) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00ba, code lost:
    
        if (r8.writeInt(r9, r0) == r1) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0112, code lost:
    
        if (r8.writeInt(r9, r0) == r1) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0132, code lost:
    
        if (r9.writeInt(r8, r0) == r1) goto L64;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x00ab A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f0 A[PHI: r2 r3 r6 r8 r9
      0x00f0: PHI (r2v10 java.nio.ByteBuffer) = (r2v9 java.nio.ByteBuffer), (r2v15 java.nio.ByteBuffer) binds: [B:47:0x00ed, B:15:0x004e] A[DONT_GENERATE, DONT_INLINE]
      0x00f0: PHI (r3v4 int) = (r3v2 int), (r3v8 int) binds: [B:47:0x00ed, B:15:0x004e] A[DONT_GENERATE, DONT_INLINE]
      0x00f0: PHI (r6v5 io.ktor.utils.io.internal.RingBufferCapacity) = (r6v4 io.ktor.utils.io.internal.RingBufferCapacity), (r6v7 io.ktor.utils.io.internal.RingBufferCapacity) binds: [B:47:0x00ed, B:15:0x004e] A[DONT_GENERATE, DONT_INLINE]
      0x00f0: PHI (r8v12 io.ktor.utils.io.ByteBufferChannel) = (r8v10 io.ktor.utils.io.ByteBufferChannel), (r8v18 io.ktor.utils.io.ByteBufferChannel) binds: [B:47:0x00ed, B:15:0x004e] A[DONT_GENERATE, DONT_INLINE]
      0x00f0: PHI (r9v9 int) = (r9v7 int), (r9v13 int) binds: [B:47:0x00ed, B:15:0x004e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0121 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x00ed -> B:49:0x00f0). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeInt$suspendImpl(io.ktor.utils.io.ByteBufferChannel r8, int r9, d7.d<? super x6.t0> r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 472
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeInt$suspendImpl(io.ktor.utils.io.ByteBufferChannel, int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0080, code lost:
    
        if (r13.writeLong(r11, r0) == r1) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x009f, code lost:
    
        if (r10.writeLong(r11, r0) == r1) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00bb, code lost:
    
        if (r10.writeLong(r11, r0) == r1) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0116, code lost:
    
        if (r10.writeLong(r12, r0) == r1) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0136, code lost:
    
        if (r12.writeLong(r10, r0) == r1) goto L64;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x00ac A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f4 A[PHI: r2 r7 r10 r11 r12
      0x00f4: PHI (r2v10 java.nio.ByteBuffer) = (r2v9 java.nio.ByteBuffer), (r2v14 java.nio.ByteBuffer) binds: [B:47:0x00f1, B:15:0x004f] A[DONT_GENERATE, DONT_INLINE]
      0x00f4: PHI (r7v2 io.ktor.utils.io.internal.RingBufferCapacity) = (r7v1 io.ktor.utils.io.internal.RingBufferCapacity), (r7v4 io.ktor.utils.io.internal.RingBufferCapacity) binds: [B:47:0x00f1, B:15:0x004f] A[DONT_GENERATE, DONT_INLINE]
      0x00f4: PHI (r10v12 io.ktor.utils.io.ByteBufferChannel) = (r10v10 io.ktor.utils.io.ByteBufferChannel), (r10v18 io.ktor.utils.io.ByteBufferChannel) binds: [B:47:0x00f1, B:15:0x004f] A[DONT_GENERATE, DONT_INLINE]
      0x00f4: PHI (r11v8 int) = (r11v6 int), (r11v14 int) binds: [B:47:0x00f1, B:15:0x004f] A[DONT_GENERATE, DONT_INLINE]
      0x00f4: PHI (r12v4 long) = (r12v3 long), (r12v6 long) binds: [B:47:0x00f1, B:15:0x004f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0125 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x00f1 -> B:49:0x00f4). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeLong$suspendImpl(io.ktor.utils.io.ByteBufferChannel r10, long r11, d7.d<? super x6.t0> r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 474
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeLong$suspendImpl(io.ktor.utils.io.ByteBufferChannel, long, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object writePacket$suspendImpl(ByteBufferChannel byteBufferChannel, ByteReadPacket byteReadPacket, d<? super t0> dVar) {
        ByteBufferChannel byteBufferChannelResolveDelegation;
        ByteBufferChannel byteBufferChannelResolveDelegation2;
        a aVar = a.f15033i;
        t0 t0Var = t0.f22605a;
        JoiningState joiningState = byteBufferChannel.joining;
        if (joiningState != null && (byteBufferChannelResolveDelegation2 = byteBufferChannel.resolveDelegation(byteBufferChannel, joiningState)) != null) {
            Object objWritePacket = byteBufferChannelResolveDelegation2.writePacket(byteReadPacket, dVar);
            return objWritePacket == aVar ? objWritePacket : t0Var;
        }
        while (!byteReadPacket.getEndOfInput() && byteBufferChannel.tryWritePacketPart(byteReadPacket) != 0) {
            try {
            } catch (Throwable th) {
                byteReadPacket.release();
                throw th;
            }
        }
        if (byteReadPacket.getRemaining() > 0) {
            JoiningState joiningState2 = byteBufferChannel.joining;
            if (joiningState2 != null && (byteBufferChannelResolveDelegation = byteBufferChannel.resolveDelegation(byteBufferChannel, joiningState2)) != null) {
                Object objWritePacket2 = byteBufferChannelResolveDelegation.writePacket(byteReadPacket, dVar);
                return objWritePacket2 == aVar ? objWritePacket2 : t0Var;
            }
            Object objWritePacketSuspend = byteBufferChannel.writePacketSuspend(byteReadPacket, dVar);
            if (objWritePacketSuspend == aVar) {
                return objWritePacketSuspend;
            }
        }
        return t0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0072, code lost:
    
        if (r8.writePacket(r7, r1) == r2) goto L34;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0050 A[Catch: all -> 0x0030, TryCatch #0 {all -> 0x0030, blocks: (B:13:0x002c, B:20:0x0042, B:28:0x005d, B:30:0x0061, B:32:0x0067, B:37:0x0079, B:23:0x004a, B:25:0x0050), top: B:43:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x007d A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x005a -> B:28:0x005d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writePacketSuspend(io.ktor.utils.io.core.ByteReadPacket r7, d7.d<? super x6.t0> r8) {
        /*
            r6 = this;
            x6.t0 r0 = x6.t0.f22605a
            boolean r1 = r8 instanceof io.ktor.utils.io.ByteBufferChannel.C19881
            if (r1 == 0) goto L15
            r1 = r8
            io.ktor.utils.io.ByteBufferChannel$writePacketSuspend$1 r1 = (io.ktor.utils.io.ByteBufferChannel.C19881) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.label = r2
            goto L1a
        L15:
            io.ktor.utils.io.ByteBufferChannel$writePacketSuspend$1 r1 = new io.ktor.utils.io.ByteBufferChannel$writePacketSuspend$1
            r1.<init>(r8)
        L1a:
            java.lang.Object r8 = r1.result
            e7.a r2 = e7.a.f15033i
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L46
            if (r3 == r5) goto L3a
            if (r3 != r4) goto L32
            java.lang.Object r7 = r1.L$0
            io.ktor.utils.io.core.ByteReadPacket r7 = (io.ktor.utils.io.core.ByteReadPacket) r7
            k2.c.G(r8)     // Catch: java.lang.Throwable -> L30
            goto L75
        L30:
            r8 = move-exception
            goto L81
        L32:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3a:
            java.lang.Object r7 = r1.L$1
            io.ktor.utils.io.core.ByteReadPacket r7 = (io.ktor.utils.io.core.ByteReadPacket) r7
            java.lang.Object r3 = r1.L$0
            io.ktor.utils.io.ByteBufferChannel r3 = (io.ktor.utils.io.ByteBufferChannel) r3
            k2.c.G(r8)     // Catch: java.lang.Throwable -> L30
            goto L5d
        L46:
            k2.c.G(r8)
            r3 = r6
        L4a:
            boolean r8 = r7.getEndOfInput()     // Catch: java.lang.Throwable -> L30
            if (r8 != 0) goto L7d
            r1.L$0 = r3     // Catch: java.lang.Throwable -> L30
            r1.L$1 = r7     // Catch: java.lang.Throwable -> L30
            r1.label = r5     // Catch: java.lang.Throwable -> L30
            java.lang.Object r8 = r3.writeSuspend(r5, r1)     // Catch: java.lang.Throwable -> L30
            if (r8 != r2) goto L5d
            goto L74
        L5d:
            io.ktor.utils.io.internal.JoiningState r8 = r3.joining     // Catch: java.lang.Throwable -> L30
            if (r8 == 0) goto L79
            io.ktor.utils.io.ByteBufferChannel r8 = r3.resolveDelegation(r3, r8)     // Catch: java.lang.Throwable -> L30
            if (r8 == 0) goto L79
            r1.L$0 = r7     // Catch: java.lang.Throwable -> L30
            r3 = 0
            r1.L$1 = r3     // Catch: java.lang.Throwable -> L30
            r1.label = r4     // Catch: java.lang.Throwable -> L30
            java.lang.Object r8 = r8.writePacket(r7, r1)     // Catch: java.lang.Throwable -> L30
            if (r8 != r2) goto L75
        L74:
            return r2
        L75:
            r7.release()
            return r0
        L79:
            r3.tryWritePacketPart(r7)     // Catch: java.lang.Throwable -> L30
            goto L4a
        L7d:
            r7.release()
            return r0
        L81:
            r7.release()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writePacketSuspend(io.ktor.utils.io.core.ByteReadPacket, d7.d):java.lang.Object");
    }

    private final Object writePrimitive(int i10, l<? super ByteBufferChannel, t0> lVar, l<? super ByteBuffer, t0> lVar2, d<? super t0> dVar) throws Throwable {
        ByteBufferChannel byteBufferChannelResolveDelegation;
        t0 t0Var = t0.f22605a;
        JoiningState joiningState = this.joining;
        if (joiningState != null && (byteBufferChannelResolveDelegation = resolveDelegation(this, joiningState)) != null) {
            lVar.invoke(byteBufferChannelResolveDelegation);
            return t0Var;
        }
        ByteBuffer byteBuffer = setupStateForWrite$ktor_io();
        if (byteBuffer == null) {
            JoiningState joiningState2 = this.joining;
            if (getState() == ReadWriteBufferState.Terminated.INSTANCE) {
                lVar.invoke(joiningState2.getDelegatedTo());
            } else {
                while (getState() != ReadWriteBufferState.Terminated.INSTANCE) {
                    writeSuspend(1, dVar);
                }
                lVar.invoke(joiningState2.getDelegatedTo());
            }
            return t0Var;
        }
        RingBufferCapacity ringBufferCapacity = getState().capacity;
        if (ringBufferCapacity.tryWriteExact(i10)) {
            prepareWriteBuffer$ktor_io(byteBuffer, i10);
            if (byteBuffer.remaining() < i10) {
                byteBuffer.limit(byteBuffer.capacity());
                lVar2.invoke(byteBuffer);
                carry(byteBuffer);
            } else {
                lVar2.invoke(byteBuffer);
            }
            bytesWritten(byteBuffer, ringBufferCapacity, i10);
            if (ringBufferCapacity.isFull() || getAutoFlush()) {
                flush();
            }
            return t0Var;
        }
        while (true) {
            try {
                writeSuspend(i10, dVar);
                if (this.joining != null) {
                    restoreStateAfterWrite$ktor_io();
                    JoiningState joiningState3 = this.joining;
                    if (getState() == ReadWriteBufferState.Terminated.INSTANCE) {
                        lVar.invoke(joiningState3.getDelegatedTo());
                    } else {
                        while (getState() != ReadWriteBufferState.Terminated.INSTANCE) {
                            writeSuspend(1, dVar);
                        }
                        lVar.invoke(joiningState3.getDelegatedTo());
                    }
                } else if (ringBufferCapacity.tryWriteExact(i10)) {
                    prepareWriteBuffer$ktor_io(byteBuffer, i10);
                    if (byteBuffer.remaining() < i10) {
                        byteBuffer.limit(byteBuffer.capacity());
                        lVar2.invoke(byteBuffer);
                        carry(byteBuffer);
                    } else {
                        lVar2.invoke(byteBuffer);
                    }
                    bytesWritten(byteBuffer, ringBufferCapacity, i10);
                    if (ringBufferCapacity.isFull() || getAutoFlush()) {
                        flush();
                    }
                }
            } finally {
                restoreStateAfterWrite$ktor_io();
                tryTerminate$ktor_io();
            }
        }
        return t0Var;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x007f, code lost:
    
        if (r10.writeShort(r9, r0) == r1) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x009f, code lost:
    
        if (r10.getDelegatedTo().writeShort(r9, r0) == r1) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00bc, code lost:
    
        if (r2.getDelegatedTo().writeShort(r9, r0) == r1) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0115, code lost:
    
        if (r10.getDelegatedTo().writeShort(r9, r0) == r1) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0136, code lost:
    
        if (r2.getDelegatedTo().writeShort(r8, r0) == r1) goto L64;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x00ac A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f2 A[PHI: r2 r6 r8 r9 r10
      0x00f2: PHI (r2v7 int) = (r2v6 int), (r2v13 int) binds: [B:47:0x00ef, B:15:0x004d] A[DONT_GENERATE, DONT_INLINE]
      0x00f2: PHI (r6v4 io.ktor.utils.io.internal.RingBufferCapacity) = (r6v3 io.ktor.utils.io.internal.RingBufferCapacity), (r6v6 io.ktor.utils.io.internal.RingBufferCapacity) binds: [B:47:0x00ef, B:15:0x004d] A[DONT_GENERATE, DONT_INLINE]
      0x00f2: PHI (r8v13 io.ktor.utils.io.ByteBufferChannel) = (r8v11 io.ktor.utils.io.ByteBufferChannel), (r8v19 io.ktor.utils.io.ByteBufferChannel) binds: [B:47:0x00ef, B:15:0x004d] A[DONT_GENERATE, DONT_INLINE]
      0x00f2: PHI (r9v13 short) = (r9v11 short), (r9v20 short) binds: [B:47:0x00ef, B:15:0x004d] A[DONT_GENERATE, DONT_INLINE]
      0x00f2: PHI (r10v11 java.nio.ByteBuffer) = (r10v10 java.nio.ByteBuffer), (r10v14 java.nio.ByteBuffer) binds: [B:47:0x00ef, B:15:0x004d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0124 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x00ef -> B:49:0x00f2). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeShort$suspendImpl(io.ktor.utils.io.ByteBufferChannel r8, short r9, d7.d<? super x6.t0> r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 480
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeShort$suspendImpl(io.ktor.utils.io.ByteBufferChannel, short, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005a A[PHI: r2 r7 r8 r9
      0x005a: PHI (r2v3 io.ktor.utils.io.ByteBufferChannel) = (r2v2 io.ktor.utils.io.ByteBufferChannel), (r2v6 io.ktor.utils.io.ByteBufferChannel) binds: [B:19:0x0057, B:16:0x0032] A[DONT_GENERATE, DONT_INLINE]
      0x005a: PHI (r7v2 byte[]) = (r7v1 byte[]), (r7v6 byte[]) binds: [B:19:0x0057, B:16:0x0032] A[DONT_GENERATE, DONT_INLINE]
      0x005a: PHI (r8v2 int) = (r8v1 int), (r8v3 int) binds: [B:19:0x0057, B:16:0x0032] A[DONT_GENERATE, DONT_INLINE]
      0x005a: PHI (r9v2 int) = (r9v1 int), (r9v5 int) binds: [B:19:0x0057, B:16:0x0032] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0057 -> B:21:0x005a). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeSuspend(byte[] r7, int r8, int r9, d7.d<? super java.lang.Integer> r10) throws java.lang.Throwable {
        /*
            r6 = this;
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteBufferChannel.C19901
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.utils.io.ByteBufferChannel$writeSuspend$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19901) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$writeSuspend$1 r0 = new io.ktor.utils.io.ByteBufferChannel$writeSuspend$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L45
            if (r2 == r4) goto L32
            if (r2 != r3) goto L2a
            k2.c.G(r10)
            return r10
        L2a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L32:
            int r7 = r0.I$1
            int r8 = r0.I$0
            java.lang.Object r9 = r0.L$1
            byte[] r9 = (byte[]) r9
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            k2.c.G(r10)
            r5 = r9
            r9 = r7
            r7 = r5
            goto L5a
        L45:
            k2.c.G(r10)
            r2 = r6
        L49:
            r0.L$0 = r2
            r0.L$1 = r7
            r0.I$0 = r8
            r0.I$1 = r9
            r0.label = r4
            java.lang.Object r10 = r2.tryWriteSuspend$ktor_io(r4, r0)
            if (r10 != r1) goto L5a
            goto L71
        L5a:
            io.ktor.utils.io.internal.JoiningState r10 = r2.joining
            if (r10 == 0) goto L73
            io.ktor.utils.io.ByteBufferChannel r10 = r2.resolveDelegation(r2, r10)
            if (r10 == 0) goto L73
            r2 = 0
            r0.L$0 = r2
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r7 = r10.writeSuspend(r7, r8, r9, r0)
            if (r7 != r1) goto L72
        L71:
            return r1
        L72:
            return r7
        L73:
            int r10 = r2.writeAsMuchAsPossible(r7, r8, r9)
            if (r10 <= 0) goto L49
            java.lang.Integer r7 = new java.lang.Integer
            r7.<init>(r10)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeSuspend(byte[], int, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void writeSuspendBlock(int size, k<? super t0> c10) throws Throwable {
        Throwable sendException;
        loop0: while (true) {
            ClosedElement closed = getClosed();
            if (closed != null && (sendException = closed.getSendException()) != null) {
                ByteBufferChannelKt.rethrowClosed(sendException);
                throw new KotlinNothingValueException();
            }
            if (!writeSuspendPredicate(size)) {
                c10.resumeWith(t0.f22605a);
                break;
            }
            while (getWriteOp() == null) {
                if (!writeSuspendPredicate(size)) {
                    break;
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _writeOp$FU;
                while (!atomicReferenceFieldUpdater.compareAndSet(this, null, c10)) {
                    if (atomicReferenceFieldUpdater.get(this) != null) {
                        break;
                    }
                }
                if (writeSuspendPredicate(size)) {
                    break;
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = _writeOp$FU;
                while (!atomicReferenceFieldUpdater2.compareAndSet(this, c10, null)) {
                    if (atomicReferenceFieldUpdater2.get(this) != c10) {
                        break loop0;
                    }
                }
            }
            throw new IllegalStateException("Operation is already in progress");
        }
        flushImpl(size);
        if (shouldResumeReadOp()) {
            resumeReadOp();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean writeSuspendPredicate(int size) {
        JoiningState joiningState = this.joining;
        ReadWriteBufferState state = getState();
        if (getClosed() != null) {
            return false;
        }
        return joiningState == null ? state.capacity._availableForWrite$internal < size && state != ReadWriteBufferState.IdleEmpty.INSTANCE : (state == ReadWriteBufferState.Terminated.INSTANCE || (state instanceof ReadWriteBufferState.Writing) || (state instanceof ReadWriteBufferState.ReadingWriting)) ? false : true;
    }

    private final Object writeSuspendPrimitive(ByteBuffer byteBuffer, int i10, RingBufferCapacity ringBufferCapacity, l<? super ByteBufferChannel, t0> lVar, l<? super ByteBuffer, t0> lVar2, d<? super t0> dVar) throws Throwable {
        t0 t0Var = t0.f22605a;
        do {
            try {
                writeSuspend(i10, dVar);
                if (this.joining != null) {
                    restoreStateAfterWrite$ktor_io();
                    JoiningState joiningState = this.joining;
                    if (getState() == ReadWriteBufferState.Terminated.INSTANCE) {
                        lVar.invoke(joiningState.getDelegatedTo());
                    } else {
                        while (getState() != ReadWriteBufferState.Terminated.INSTANCE) {
                            writeSuspend(1, dVar);
                        }
                        lVar.invoke(joiningState.getDelegatedTo());
                    }
                    return t0Var;
                }
            } finally {
                restoreStateAfterWrite$ktor_io();
                tryTerminate$ktor_io();
            }
        } while (!ringBufferCapacity.tryWriteExact(i10));
        prepareWriteBuffer$ktor_io(byteBuffer, i10);
        if (byteBuffer.remaining() < i10) {
            byteBuffer.limit(byteBuffer.capacity());
            lVar2.invoke(byteBuffer);
            carry(byteBuffer);
        } else {
            lVar2.invoke(byteBuffer);
        }
        bytesWritten(byteBuffer, ringBufferCapacity, i10);
        if (ringBufferCapacity.isFull() || getAutoFlush()) {
            flush();
        }
        return t0Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.lang.Object, x6.t0] */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeSuspendSession$suspendImpl(io.ktor.utils.io.ByteBufferChannel r4, r7.p<? super io.ktor.utils.io.WriterSuspendSession, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r5, d7.d<? super x6.t0> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteBufferChannel.C19921
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteBufferChannel$writeSuspendSession$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19921) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$writeSuspendSession$1 r0 = new io.ktor.utils.io.ByteBufferChannel$writeSuspendSession$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.internal.WriteSessionImpl r4 = (io.ktor.utils.io.internal.WriteSessionImpl) r4
            k2.c.G(r6)     // Catch: java.lang.Throwable -> L29
            goto L48
        L29:
            r5 = move-exception
            goto L4e
        L2b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L33:
            k2.c.G(r6)
            io.ktor.utils.io.internal.WriteSessionImpl r4 = r4.writeSession
            r4.begin()
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L29
            r0.label = r2     // Catch: java.lang.Throwable -> L29
            java.lang.Object r5 = r5.invoke(r4, r0)     // Catch: java.lang.Throwable -> L29
            e7.a r6 = e7.a.f15033i
            if (r5 != r6) goto L48
            return r6
        L48:
            r4.complete()
            x6.t0 r4 = x6.t0.f22605a
            return r4
        L4e:
            r4.complete()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeSuspendSession$suspendImpl(io.ktor.utils.io.ByteBufferChannel, r7.p, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object writeWhile$suspendImpl(ByteBufferChannel byteBufferChannel, l<? super ByteBuffer, Boolean> lVar, d<? super t0> dVar) throws Throwable {
        boolean zWriteWhileNoSuspend = byteBufferChannel.writeWhileNoSuspend(lVar);
        t0 t0Var = t0.f22605a;
        if (!zWriteWhileNoSuspend) {
            return t0Var;
        }
        ClosedElement closed = byteBufferChannel.getClosed();
        if (closed == null) {
            Object objWriteWhileSuspend = byteBufferChannel.writeWhileSuspend(lVar, dVar);
            return objWriteWhileSuspend == a.f15033i ? objWriteWhileSuspend : t0Var;
        }
        ByteBufferChannelKt.rethrowClosed(closed.getSendException());
        throw new KotlinNothingValueException();
    }

    private final boolean writeWhileLoop(ByteBuffer dst, RingBufferCapacity capacity, l<? super ByteBuffer, Boolean> block) {
        int iCapacity = dst.capacity() - this.reservedSize;
        boolean z = true;
        while (z) {
            int iTryWriteAtLeast = capacity.tryWriteAtLeast(1);
            if (iTryWriteAtLeast == 0) {
                break;
            }
            int i10 = this.writePosition;
            int i11 = i10 + iTryWriteAtLeast;
            if (i11 > iCapacity) {
                i11 = iCapacity;
            }
            dst.limit(i11);
            dst.position(i10);
            try {
                boolean zBooleanValue = ((Boolean) block.invoke(dst)).booleanValue();
                if (dst.limit() != i11) {
                    throw new IllegalStateException("Buffer limit modified.");
                }
                int iPosition = dst.position() - i10;
                if (iPosition < 0) {
                    throw new IllegalStateException("Position has been moved backward: pushback is not supported.");
                }
                bytesWritten(dst, capacity, iPosition);
                if (iPosition < iTryWriteAtLeast) {
                    capacity.completeRead(iTryWriteAtLeast - iPosition);
                }
                z = zBooleanValue;
            } catch (Throwable th) {
                capacity.completeRead(iTryWriteAtLeast);
                throw th;
            }
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0033 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean writeWhileNoSuspend(r7.l<? super java.nio.ByteBuffer, java.lang.Boolean> r8) throws java.lang.Throwable {
        /*
            r7 = this;
            io.ktor.utils.io.internal.JoiningState r0 = r7.joining
            if (r0 == 0) goto La
            io.ktor.utils.io.ByteBufferChannel r0 = r7.resolveDelegation(r7, r0)
            if (r0 != 0) goto Lb
        La:
            r0 = r7
        Lb:
            java.nio.ByteBuffer r1 = r0.setupStateForWrite$ktor_io()
            if (r1 != 0) goto L13
            r8 = 1
            return r8
        L13:
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r0.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r2 = r2.capacity
            long r3 = r0.get_totalBytesWritten()
            io.ktor.utils.io.internal.ClosedElement r5 = r0.getClosed()     // Catch: java.lang.Throwable -> L4c
            if (r5 != 0) goto L4e
            boolean r8 = r0.writeWhileLoop(r1, r2, r8)     // Catch: java.lang.Throwable -> L4c
            boolean r1 = r2.isFull()
            if (r1 != 0) goto L33
            boolean r1 = r0.getAutoFlush()
            if (r1 == 0) goto L36
        L33:
            r0.flush()
        L36:
            if (r0 == r7) goto L45
            long r1 = r7.get_totalBytesWritten()
            long r5 = r0.get_totalBytesWritten()
            long r5 = r5 - r3
            long r5 = r5 + r1
            r7.setTotalBytesWritten$ktor_io(r5)
        L45:
            r0.restoreStateAfterWrite$ktor_io()
            r0.tryTerminate$ktor_io()
            return r8
        L4c:
            r8 = move-exception
            goto L5b
        L4e:
            java.lang.Throwable r8 = r5.getSendException()     // Catch: java.lang.Throwable -> L4c
            io.ktor.utils.io.ByteBufferChannelKt.access$rethrowClosed(r8)     // Catch: java.lang.Throwable -> L4c
            kotlin.KotlinNothingValueException r8 = new kotlin.KotlinNothingValueException     // Catch: java.lang.Throwable -> L4c
            r8.<init>()     // Catch: java.lang.Throwable -> L4c
            throw r8     // Catch: java.lang.Throwable -> L4c
        L5b:
            boolean r1 = r2.isFull()
            if (r1 != 0) goto L67
            boolean r1 = r0.getAutoFlush()
            if (r1 == 0) goto L6a
        L67:
            r0.flush()
        L6a:
            if (r0 == r7) goto L79
            long r1 = r7.get_totalBytesWritten()
            long r5 = r0.get_totalBytesWritten()
            long r5 = r5 - r3
            long r5 = r5 + r1
            r7.setTotalBytesWritten$ktor_io(r5)
        L79:
            r0.restoreStateAfterWrite$ktor_io()
            r0.tryTerminate$ktor_io()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeWhileNoSuspend(r7.l):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00d9, code lost:
    
        if (r5.getClosed() != null) goto L45;
     */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00cb A[Catch: all -> 0x0065, TryCatch #0 {all -> 0x0065, blocks: (B:16:0x005f, B:38:0x00c7, B:40:0x00cb, B:42:0x00d1, B:43:0x00d5, B:34:0x00a5), top: B:79:0x005f }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0103 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00c5 -> B:38:0x00c7). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeWhileSuspend(r7.l<? super java.nio.ByteBuffer, java.lang.Boolean> r18, d7.d<? super x6.t0> r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 365
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeWhileSuspend(r7.l, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0031 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void writing(r7.q<? super io.ktor.utils.io.ByteBufferChannel, ? super java.nio.ByteBuffer, ? super io.ktor.utils.io.internal.RingBufferCapacity, x6.t0> r8) throws java.lang.Throwable {
        /*
            r7 = this;
            io.ktor.utils.io.internal.JoiningState r0 = r7.joining
            if (r0 == 0) goto La
            io.ktor.utils.io.ByteBufferChannel r0 = r7.resolveDelegation(r7, r0)
            if (r0 != 0) goto Lb
        La:
            r0 = r7
        Lb:
            java.nio.ByteBuffer r1 = r0.setupStateForWrite$ktor_io()
            if (r1 != 0) goto L12
            return
        L12:
            io.ktor.utils.io.internal.ReadWriteBufferState r2 = r0.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r2 = r2.capacity
            long r3 = r0.get_totalBytesWritten()
            io.ktor.utils.io.internal.ClosedElement r5 = r0.getClosed()     // Catch: java.lang.Throwable -> L4a
            if (r5 != 0) goto L4c
            r8.invoke(r0, r1, r2)     // Catch: java.lang.Throwable -> L4a
            boolean r8 = r2.isFull()
            if (r8 != 0) goto L31
            boolean r8 = r0.getAutoFlush()
            if (r8 == 0) goto L34
        L31:
            r0.flush()
        L34:
            if (r0 == r7) goto L43
            long r1 = r7.get_totalBytesWritten()
            long r5 = r0.get_totalBytesWritten()
            long r5 = r5 - r3
            long r5 = r5 + r1
            r7.setTotalBytesWritten$ktor_io(r5)
        L43:
            r0.restoreStateAfterWrite$ktor_io()
            r0.tryTerminate$ktor_io()
            return
        L4a:
            r8 = move-exception
            goto L59
        L4c:
            java.lang.Throwable r8 = r5.getSendException()     // Catch: java.lang.Throwable -> L4a
            io.ktor.utils.io.ByteBufferChannelKt.access$rethrowClosed(r8)     // Catch: java.lang.Throwable -> L4a
            kotlin.KotlinNothingValueException r8 = new kotlin.KotlinNothingValueException     // Catch: java.lang.Throwable -> L4a
            r8.<init>()     // Catch: java.lang.Throwable -> L4a
            throw r8     // Catch: java.lang.Throwable -> L4a
        L59:
            boolean r1 = r2.isFull()
            if (r1 != 0) goto L65
            boolean r1 = r0.getAutoFlush()
            if (r1 == 0) goto L68
        L65:
            r0.flush()
        L68:
            if (r0 == r7) goto L77
            long r1 = r7.get_totalBytesWritten()
            long r5 = r0.get_totalBytesWritten()
            long r5 = r5 - r3
            long r5 = r5 + r1
            r7.setTotalBytesWritten$ktor_io(r5)
        L77:
            r0.restoreStateAfterWrite$ktor_io()
            r0.tryTerminate$ktor_io()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writing(r7.q):void");
    }

    @Override // io.ktor.utils.io.ByteChannel
    @x6.e
    public void attachJob(v1 job) {
        v1 v1Var = this.attachedJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.attachedJob = job;
        job.invokeOnCompletion((2 & 1) == 0, (2 & 2) != 0, new AnonymousClass1());
    }

    @Override // io.ktor.utils.io.LookAheadSuspendSession
    public final Object awaitAtLeast(int i10, d<? super Boolean> dVar) throws Throwable {
        if (i10 < 0) {
            throw new IllegalArgumentException(a0.c.i(i10, "atLeast parameter shouldn't be negative: ").toString());
        }
        if (i10 > 4088) {
            throw new IllegalArgumentException(a0.c.i(i10, "atLeast parameter shouldn't be larger than max buffer size of 4088: ").toString());
        }
        if (getState().capacity._availableForRead$internal < i10) {
            return (getState().getIdle() || (getState() instanceof ReadWriteBufferState.Writing)) ? awaitAtLeastSuspend(i10, dVar) : i10 == 1 ? readSuspendImpl(1, dVar) : readSuspend(i10, dVar);
        }
        if (getState().getIdle() || (getState() instanceof ReadWriteBufferState.Writing)) {
            setupStateForRead();
        }
        return Boolean.TRUE;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object awaitContent(d<? super t0> dVar) {
        return awaitContent$suspendImpl(this, dVar);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object awaitFreeSpace(d<? super t0> dVar) {
        return awaitFreeSpace$suspendImpl(this, dVar);
    }

    @Override // io.ktor.utils.io.HasWriteSession
    public WriterSuspendSession beginWriteSession() {
        WriteSessionImpl writeSessionImpl = this.writeSession;
        writeSessionImpl.begin();
        return writeSessionImpl;
    }

    public final void bytesWrittenFromSession$ktor_io(ByteBuffer buffer, RingBufferCapacity capacity, int count) {
        bytesWritten(buffer, capacity, count);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public boolean cancel(Throwable cause) {
        if (cause == null) {
            cause = new CancellationException("Channel has been cancelled");
        }
        return close(cause);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public boolean close(Throwable cause) {
        JoiningState joiningState;
        if (getClosed() != null) {
            return false;
        }
        ClosedElement emptyCause = cause == null ? ClosedElement.INSTANCE.getEmptyCause() : new ClosedElement(cause);
        getState().capacity.flush();
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _closed$FU;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, null, emptyCause)) {
            if (atomicReferenceFieldUpdater.get(this) != null) {
                return false;
            }
        }
        getState().capacity.flush();
        if (getState().capacity.isEmpty() || cause != null) {
            tryTerminate$ktor_io();
        }
        resumeClosed(cause);
        if (getState() == ReadWriteBufferState.Terminated.INSTANCE && (joiningState = this.joining) != null) {
            ensureClosedJoined(joiningState);
        }
        if (cause == null) {
            this.writeSuspendContinuationCache.close(new ClosedWriteChannelException(ByteBufferChannelKt.DEFAULT_CLOSE_MESSAGE));
            this.readSuspendContinuationCache.close(Boolean.valueOf(getState().capacity.flush()));
            return true;
        }
        v1 v1Var = this.attachedJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.readSuspendContinuationCache.close(cause);
        this.writeSuspendContinuationCache.close(cause);
        return true;
    }

    @Override // io.ktor.utils.io.LookAheadSession
    /* JADX INFO: renamed from: consumed */
    public void mo7011consumed(int n6) {
        if (n6 < 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        ReadWriteBufferState state = getState();
        if (!state.capacity.tryReadExact(n6)) {
            throw new IllegalStateException(androidx.compose.foundation.c.o(n6, "Unable to consume ", " bytes: not enough available bytes"));
        }
        if (n6 > 0) {
            bytesRead(state.getReadBuffer(), state.capacity, n6);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:230:0x0151, code lost:
    
        r4 = r27;
        r7 = r30;
        r14 = r15;
        r12 = r16;
        r27 = r20;
        r11 = r22;
        r16 = r0;
        r15 = r1;
        r0 = r2;
        r2 = r3;
        r3 = r8;
        r8 = r19;
     */
    /* JADX WARN: Path cross not found for [B:137:0x02e8, B:142:0x02f3], limit reached: 229 */
    /* JADX WARN: Path cross not found for [B:142:0x02f3, B:137:0x02e8], limit reached: 229 */
    /* JADX WARN: Path cross not found for [B:148:0x0314, B:154:0x0333], limit reached: 229 */
    /* JADX WARN: Path cross not found for [B:154:0x0333, B:148:0x0314], limit reached: 229 */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x02e8 A[Catch: all -> 0x02ef, TryCatch #7 {all -> 0x02ef, blocks: (B:135:0x02e2, B:137:0x02e8, B:144:0x02f8, B:145:0x0307, B:142:0x02f3), top: B:217:0x02e2 }] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x02f8 A[Catch: all -> 0x02ef, TryCatch #7 {all -> 0x02ef, blocks: (B:135:0x02e2, B:137:0x02e8, B:144:0x02f8, B:145:0x0307, B:142:0x02f3), top: B:217:0x02e2 }] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0314 A[Catch: all -> 0x0054, TRY_ENTER, TryCatch #9 {all -> 0x0054, blocks: (B:14:0x0045, B:49:0x0118, B:51:0x011e, B:53:0x0122, B:56:0x0129, B:148:0x0314, B:151:0x031c, B:153:0x0328, B:154:0x0333, B:156:0x0339, B:158:0x0342, B:162:0x036b, B:165:0x0375, B:176:0x0391, B:178:0x0395, B:169:0x037e, B:59:0x0135, B:186:0x03d1, B:188:0x03d7, B:192:0x03e2, B:193:0x03ef, B:194:0x03f5, B:190:0x03dd, B:196:0x03f8, B:197:0x03fb, B:21:0x0073), top: B:221:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0339 A[Catch: all -> 0x0054, TryCatch #9 {all -> 0x0054, blocks: (B:14:0x0045, B:49:0x0118, B:51:0x011e, B:53:0x0122, B:56:0x0129, B:148:0x0314, B:151:0x031c, B:153:0x0328, B:154:0x0333, B:156:0x0339, B:158:0x0342, B:162:0x036b, B:165:0x0375, B:176:0x0391, B:178:0x0395, B:169:0x037e, B:59:0x0135, B:186:0x03d1, B:188:0x03d7, B:192:0x03e2, B:193:0x03ef, B:194:0x03f5, B:190:0x03dd, B:196:0x03f8, B:197:0x03fb, B:21:0x0073), top: B:221:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x037c  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0395 A[Catch: all -> 0x0054, TRY_LEAVE, TryCatch #9 {all -> 0x0054, blocks: (B:14:0x0045, B:49:0x0118, B:51:0x011e, B:53:0x0122, B:56:0x0129, B:148:0x0314, B:151:0x031c, B:153:0x0328, B:154:0x0333, B:156:0x0339, B:158:0x0342, B:162:0x036b, B:165:0x0375, B:176:0x0391, B:178:0x0395, B:169:0x037e, B:59:0x0135, B:186:0x03d1, B:188:0x03d7, B:192:0x03e2, B:193:0x03ef, B:194:0x03f5, B:190:0x03dd, B:196:0x03f8, B:197:0x03fb, B:21:0x0073), top: B:221:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x03d7 A[Catch: all -> 0x0054, TryCatch #9 {all -> 0x0054, blocks: (B:14:0x0045, B:49:0x0118, B:51:0x011e, B:53:0x0122, B:56:0x0129, B:148:0x0314, B:151:0x031c, B:153:0x0328, B:154:0x0333, B:156:0x0339, B:158:0x0342, B:162:0x036b, B:165:0x0375, B:176:0x0391, B:178:0x0395, B:169:0x037e, B:59:0x0135, B:186:0x03d1, B:188:0x03d7, B:192:0x03e2, B:193:0x03ef, B:194:0x03f5, B:190:0x03dd, B:196:0x03f8, B:197:0x03fb, B:21:0x0073), top: B:221:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:190:0x03dd A[Catch: all -> 0x0054, TryCatch #9 {all -> 0x0054, blocks: (B:14:0x0045, B:49:0x0118, B:51:0x011e, B:53:0x0122, B:56:0x0129, B:148:0x0314, B:151:0x031c, B:153:0x0328, B:154:0x0333, B:156:0x0339, B:158:0x0342, B:162:0x036b, B:165:0x0375, B:176:0x0391, B:178:0x0395, B:169:0x037e, B:59:0x0135, B:186:0x03d1, B:188:0x03d7, B:192:0x03e2, B:193:0x03ef, B:194:0x03f5, B:190:0x03dd, B:196:0x03f8, B:197:0x03fb, B:21:0x0073), top: B:221:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:192:0x03e2 A[Catch: all -> 0x0054, TryCatch #9 {all -> 0x0054, blocks: (B:14:0x0045, B:49:0x0118, B:51:0x011e, B:53:0x0122, B:56:0x0129, B:148:0x0314, B:151:0x031c, B:153:0x0328, B:154:0x0333, B:156:0x0339, B:158:0x0342, B:162:0x036b, B:165:0x0375, B:176:0x0391, B:178:0x0395, B:169:0x037e, B:59:0x0135, B:186:0x03d1, B:188:0x03d7, B:192:0x03e2, B:193:0x03ef, B:194:0x03f5, B:190:0x03dd, B:196:0x03f8, B:197:0x03fb, B:21:0x0073), top: B:221:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:196:0x03f8 A[Catch: all -> 0x0054, TryCatch #9 {all -> 0x0054, blocks: (B:14:0x0045, B:49:0x0118, B:51:0x011e, B:53:0x0122, B:56:0x0129, B:148:0x0314, B:151:0x031c, B:153:0x0328, B:154:0x0333, B:156:0x0339, B:158:0x0342, B:162:0x036b, B:165:0x0375, B:176:0x0391, B:178:0x0395, B:169:0x037e, B:59:0x0135, B:186:0x03d1, B:188:0x03d7, B:192:0x03e2, B:193:0x03ef, B:194:0x03f5, B:190:0x03dd, B:196:0x03f8, B:197:0x03fb, B:21:0x0073), top: B:221:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0272 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x011e A[Catch: all -> 0x0054, TryCatch #9 {all -> 0x0054, blocks: (B:14:0x0045, B:49:0x0118, B:51:0x011e, B:53:0x0122, B:56:0x0129, B:148:0x0314, B:151:0x031c, B:153:0x0328, B:154:0x0333, B:156:0x0339, B:158:0x0342, B:162:0x036b, B:165:0x0375, B:176:0x0391, B:178:0x0395, B:169:0x037e, B:59:0x0135, B:186:0x03d1, B:188:0x03d7, B:192:0x03e2, B:193:0x03ef, B:194:0x03f5, B:190:0x03dd, B:196:0x03f8, B:197:0x03fb, B:21:0x0073), top: B:221:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0157 A[Catch: all -> 0x01c7, TryCatch #4 {all -> 0x01c7, blocks: (B:63:0x0151, B:65:0x0157, B:67:0x015b), top: B:211:0x0151 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01a1 A[Catch: all -> 0x01b0, TRY_LEAVE, TryCatch #8 {all -> 0x01b0, blocks: (B:74:0x019d, B:76:0x01a1), top: B:219:0x019d }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0203 A[Catch: all -> 0x02c0, TRY_LEAVE, TryCatch #0 {all -> 0x02c0, blocks: (B:87:0x01e6, B:92:0x0203), top: B:203:0x01e6 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:153:0x0328 -> B:49:0x0118). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:177:0x0393 -> B:181:0x03b9). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:179:0x03b6 -> B:181:0x03b9). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object copyDirect$ktor_io(io.ktor.utils.io.ByteBufferChannel r27, long r28, io.ktor.utils.io.internal.JoiningState r30, d7.d<? super java.lang.Long> r31) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1034
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.copyDirect$ktor_io(io.ktor.utils.io.ByteBufferChannel, long, io.ktor.utils.io.internal.JoiningState, d7.d):java.lang.Object");
    }

    public final ReadWriteBufferState currentState$ktor_io() {
        return getState();
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object discard(long j10, d<? super Long> dVar) {
        return discard$suspendImpl(this, j10, dVar);
    }

    @Override // io.ktor.utils.io.HasReadSession
    public void endReadSession() {
        this.readSession.completed();
        ReadWriteBufferState state = getState();
        if ((state instanceof ReadWriteBufferState.Reading) || (state instanceof ReadWriteBufferState.ReadingWriting)) {
            restoreStateAfterRead();
            tryTerminate$ktor_io();
        }
    }

    @Override // io.ktor.utils.io.HasWriteSession
    public void endWriteSession(int written) {
        this.writeSession.written(written);
        this.writeSession.complete();
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public void flush() {
        flushImpl(1);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public boolean getAutoFlush() {
        return this.autoFlush;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    /* JADX INFO: renamed from: getAvailableForRead */
    public int get_availableForRead() {
        return getState().capacity._availableForRead$internal;
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public int getAvailableForWrite() {
        return getState().capacity._availableForWrite$internal;
    }

    @Override // io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel
    public Throwable getClosedCause() {
        ClosedElement closed = getClosed();
        if (closed != null) {
            return closed.getCause();
        }
        return null;
    }

    /* JADX INFO: renamed from: getJoining$ktor_io, reason: from getter */
    public final JoiningState getJoining() {
        return this.joining;
    }

    /* JADX INFO: renamed from: getReservedSize$ktor_io, reason: from getter */
    public final int getReservedSize() {
        return this.reservedSize;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    /* JADX INFO: renamed from: getTotalBytesRead, reason: from getter */
    public long get_totalBytesRead() {
        return this.totalBytesRead;
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    /* JADX INFO: renamed from: getTotalBytesWritten, reason: from getter */
    public long get_totalBytesWritten() {
        return this.totalBytesWritten;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public boolean isClosedForRead() {
        return getState() == ReadWriteBufferState.Terminated.INSTANCE && getClosed() != null;
    }

    @Override // io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel
    public boolean isClosedForWrite() {
        return getClosed() != null;
    }

    public final Object joinFrom$ktor_io(ByteBufferChannel byteBufferChannel, boolean z, d<? super t0> dVar) throws Throwable {
        ClosedElement closed = byteBufferChannel.getClosed();
        t0 t0Var = t0.f22605a;
        if (closed != null && byteBufferChannel.getState() == ReadWriteBufferState.Terminated.INSTANCE) {
            if (z) {
                close(byteBufferChannel.getClosed().getCause());
            }
            return t0Var;
        }
        ClosedElement closed2 = getClosed();
        if (closed2 != null) {
            if (byteBufferChannel.getClosed() != null) {
                return t0Var;
            }
            ByteBufferChannelKt.rethrowClosed(closed2.getSendException());
            throw new KotlinNothingValueException();
        }
        JoiningState joiningState = byteBufferChannel.setupDelegateTo(this, z);
        boolean zTryCompleteJoining = byteBufferChannel.tryCompleteJoining(joiningState);
        a aVar = a.f15033i;
        if (zTryCompleteJoining) {
            Object objAwaitClose = byteBufferChannel.awaitClose(dVar);
            return objAwaitClose == aVar ? objAwaitClose : t0Var;
        }
        Object objJoinFromSuspend = joinFromSuspend(byteBufferChannel, z, joiningState, dVar);
        return objJoinFromSuspend == aVar ? objJoinFromSuspend : t0Var;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    @x6.e
    public <R> R lookAhead(l<? super LookAheadSession, ? extends R> visitor) {
        Throwable closedCause = getClosedCause();
        if (closedCause != null) {
            return (R) visitor.invoke(new FailedLookAhead(closedCause));
        }
        if (getState() == ReadWriteBufferState.Terminated.INSTANCE) {
            return (R) visitor.invoke(TerminatedLookAhead.INSTANCE);
        }
        boolean z = false;
        R r4 = null;
        if (setupStateForRead() != null) {
            try {
                if (getState().capacity._availableForRead$internal != 0) {
                    r4 = (R) visitor.invoke(this);
                    restoreStateAfterRead();
                    tryTerminate$ktor_io();
                    z = true;
                }
            } finally {
                restoreStateAfterRead();
                tryTerminate$ktor_io();
            }
        }
        if (z) {
            return r4;
        }
        Throwable closedCause2 = getClosedCause();
        return closedCause2 != null ? (R) visitor.invoke(new FailedLookAhead(closedCause2)) : (R) visitor.invoke(TerminatedLookAhead.INSTANCE);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    @x6.e
    public <R> Object lookAheadSuspend(p<? super LookAheadSuspendSession, ? super d<? super R>, ? extends Object> pVar, d<? super R> dVar) {
        return lookAheadSuspend$suspendImpl(this, pVar, dVar);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    /* JADX INFO: renamed from: peekTo-lBXzO7A, reason: not valid java name */
    public Object mo6735peekTolBXzO7A(ByteBuffer byteBuffer, long j10, long j11, long j12, long j13, d<? super Long> dVar) {
        return m6733peekTolBXzO7A$suspendImpl(this, byteBuffer, j10, j11, j12, j13, dVar);
    }

    public final void prepareWriteBuffer$ktor_io(ByteBuffer buffer, int lockedSpace) {
        prepareBuffer(buffer, this.writePosition, lockedSpace);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object read(int i10, l<? super ByteBuffer, t0> lVar, d<? super t0> dVar) {
        return read$suspendImpl(this, i10, lVar, dVar);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readAvailable(ChunkBuffer chunkBuffer, d<? super Integer> dVar) {
        return readAvailable$suspendImpl(this, chunkBuffer, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.ktor.utils.io.ByteReadChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readBoolean(d7.d<? super java.lang.Boolean> r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof io.ktor.utils.io.ByteBufferChannel.C19601
            if (r0 == 0) goto L13
            r0 = r5
            io.ktor.utils.io.ByteBufferChannel$readBoolean$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19601) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readBoolean$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readBoolean$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L3b
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            r0.label = r2
            java.lang.Object r5 = r4.readByte(r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L3b
            return r0
        L3b:
            java.lang.Number r5 = (java.lang.Number) r5
            byte r5 = r5.byteValue()
            if (r5 == 0) goto L44
            goto L45
        L44:
            r2 = 0
        L45:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readBoolean(d7.d):java.lang.Object");
    }

    /* JADX WARN: Path cross not found for [B:17:0x0043, B:18:0x0045], limit reached: 50 */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x0094 -> B:42:0x0097). Please report as a decompilation issue!!! */
    @Override // io.ktor.utils.io.ByteReadChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readByte(d7.d<? super java.lang.Byte> r10) throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteBufferChannel.C19611
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.utils.io.ByteBufferChannel$readByte$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19611) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readByte$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readByte$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r4 = (io.ktor.utils.io.ByteBufferChannel) r4
            k2.c.G(r10)
            goto L97
        L2e:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L36:
            k2.c.G(r10)
            r4 = r9
            r2 = r3
        L3b:
            java.nio.ByteBuffer r10 = r4.setupStateForRead()
            r5 = 0
            r6 = 0
            if (r10 != 0) goto L45
        L43:
            r10 = r5
            goto L7d
        L45:
            io.ktor.utils.io.internal.ReadWriteBufferState r7 = r4.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r7 = r7.capacity
            int r8 = r7._availableForRead$internal     // Catch: java.lang.Throwable -> L68
            if (r8 != 0) goto L56
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            goto L43
        L56:
            boolean r8 = r7.tryReadExact(r2)     // Catch: java.lang.Throwable -> L68
            if (r8 != 0) goto L5e
            r10 = r5
            goto L77
        L5e:
            int r6 = r10.remaining()     // Catch: java.lang.Throwable -> L68
            if (r6 >= r2) goto L6a
            r4.rollBytes(r10, r2)     // Catch: java.lang.Throwable -> L68
            goto L6a
        L68:
            r10 = move-exception
            goto Lae
        L6a:
            byte r6 = r10.get()     // Catch: java.lang.Throwable -> L68
            java.lang.Byte r6 = java.lang.Byte.valueOf(r6)     // Catch: java.lang.Throwable -> L68
            r4.bytesRead(r10, r7, r2)     // Catch: java.lang.Throwable -> L68
            r10 = r6
            r6 = r3
        L77:
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
        L7d:
            if (r6 == 0) goto L8a
            if (r10 == 0) goto L84
            java.lang.Number r10 = (java.lang.Number) r10
            return r10
        L84:
            java.lang.String r10 = "result"
            kotlin.jvm.internal.p.i(r10)
            throw r5
        L8a:
            r0.L$0 = r4
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r10 = r4.readSuspend(r2, r0)
            if (r10 != r1) goto L97
            return r1
        L97:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto La0
            goto L3b
        La0:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r10 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.String r0 = "EOF while "
            java.lang.String r1 = " bytes expected"
            java.lang.String r0 = androidx.compose.foundation.c.o(r2, r0, r1)
            r10.<init>(r0)
            throw r10
        Lae:
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readByte(d7.d):java.lang.Object");
    }

    /* JADX WARN: Path cross not found for [B:17:0x0045, B:18:0x0047], limit reached: 50 */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00a4 -> B:42:0x00a7). Please report as a decompilation issue!!! */
    @Override // io.ktor.utils.io.ByteReadChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readDouble(d7.d<? super java.lang.Double> r11) throws java.lang.Throwable {
        /*
            r10 = this;
            boolean r0 = r11 instanceof io.ktor.utils.io.ByteBufferChannel.C19621
            if (r0 == 0) goto L13
            r0 = r11
            io.ktor.utils.io.ByteBufferChannel$readDouble$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19621) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readDouble$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readDouble$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r4 = (io.ktor.utils.io.ByteBufferChannel) r4
            k2.c.G(r11)
            goto La7
        L2e:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L36:
            k2.c.G(r11)
            r11 = 8
            r4 = r10
            r2 = r11
        L3d:
            java.nio.ByteBuffer r11 = r4.setupStateForRead()
            r5 = 0
            r6 = 0
            if (r11 != 0) goto L47
        L45:
            r11 = r5
            goto L80
        L47:
            io.ktor.utils.io.internal.ReadWriteBufferState r7 = r4.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r7 = r7.capacity
            int r8 = r7._availableForRead$internal     // Catch: java.lang.Throwable -> L6a
            if (r8 != 0) goto L58
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            goto L45
        L58:
            boolean r8 = r7.tryReadExact(r2)     // Catch: java.lang.Throwable -> L6a
            if (r8 != 0) goto L60
            r11 = r5
            goto L7a
        L60:
            int r6 = r11.remaining()     // Catch: java.lang.Throwable -> L6a
            if (r6 >= r2) goto L6c
            r4.rollBytes(r11, r2)     // Catch: java.lang.Throwable -> L6a
            goto L6c
        L6a:
            r11 = move-exception
            goto Lbe
        L6c:
            long r8 = r11.getLong()     // Catch: java.lang.Throwable -> L6a
            java.lang.Long r6 = new java.lang.Long     // Catch: java.lang.Throwable -> L6a
            r6.<init>(r8)     // Catch: java.lang.Throwable -> L6a
            r4.bytesRead(r11, r7, r2)     // Catch: java.lang.Throwable -> L6a
            r11 = r6
            r6 = r3
        L7a:
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
        L80:
            if (r6 == 0) goto L9a
            if (r11 == 0) goto L94
            java.lang.Number r11 = (java.lang.Number) r11
            long r0 = r11.longValue()
            double r0 = java.lang.Double.longBitsToDouble(r0)
            java.lang.Double r11 = new java.lang.Double
            r11.<init>(r0)
            return r11
        L94:
            java.lang.String r11 = "result"
            kotlin.jvm.internal.p.i(r11)
            throw r5
        L9a:
            r0.L$0 = r4
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r11 = r4.readSuspend(r2, r0)
            if (r11 != r1) goto La7
            return r1
        La7:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto Lb0
            goto L3d
        Lb0:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r11 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.String r0 = "EOF while "
            java.lang.String r1 = " bytes expected"
            java.lang.String r0 = androidx.compose.foundation.c.o(r2, r0, r1)
            r11.<init>(r0)
            throw r11
        Lbe:
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readDouble(d7.d):java.lang.Object");
    }

    /* JADX WARN: Path cross not found for [B:17:0x0044, B:18:0x0046], limit reached: 50 */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00a2 -> B:42:0x00a5). Please report as a decompilation issue!!! */
    @Override // io.ktor.utils.io.ByteReadChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readFloat(d7.d<? super java.lang.Float> r10) throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteBufferChannel.C19631
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.utils.io.ByteBufferChannel$readFloat$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19631) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readFloat$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readFloat$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r4 = (io.ktor.utils.io.ByteBufferChannel) r4
            k2.c.G(r10)
            goto La5
        L2e:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L36:
            k2.c.G(r10)
            r10 = 4
            r4 = r9
            r2 = r10
        L3c:
            java.nio.ByteBuffer r10 = r4.setupStateForRead()
            r5 = 0
            r6 = 0
            if (r10 != 0) goto L46
        L44:
            r8 = r5
            goto L7e
        L46:
            io.ktor.utils.io.internal.ReadWriteBufferState r7 = r4.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r7 = r7.capacity
            int r8 = r7._availableForRead$internal     // Catch: java.lang.Throwable -> L69
            if (r8 != 0) goto L57
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            goto L44
        L57:
            boolean r8 = r7.tryReadExact(r2)     // Catch: java.lang.Throwable -> L69
            if (r8 != 0) goto L5f
            r8 = r5
            goto L78
        L5f:
            int r6 = r10.remaining()     // Catch: java.lang.Throwable -> L69
            if (r6 >= r2) goto L6b
            r4.rollBytes(r10, r2)     // Catch: java.lang.Throwable -> L69
            goto L6b
        L69:
            r10 = move-exception
            goto Lbc
        L6b:
            int r6 = r10.getInt()     // Catch: java.lang.Throwable -> L69
            java.lang.Integer r8 = new java.lang.Integer     // Catch: java.lang.Throwable -> L69
            r8.<init>(r6)     // Catch: java.lang.Throwable -> L69
            r4.bytesRead(r10, r7, r2)     // Catch: java.lang.Throwable -> L69
            r6 = r3
        L78:
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
        L7e:
            if (r6 == 0) goto L98
            if (r8 == 0) goto L92
            java.lang.Number r8 = (java.lang.Number) r8
            int r10 = r8.intValue()
            float r10 = java.lang.Float.intBitsToFloat(r10)
            java.lang.Float r0 = new java.lang.Float
            r0.<init>(r10)
            return r0
        L92:
            java.lang.String r10 = "result"
            kotlin.jvm.internal.p.i(r10)
            throw r5
        L98:
            r0.L$0 = r4
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r10 = r4.readSuspend(r2, r0)
            if (r10 != r1) goto La5
            return r1
        La5:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto Lae
            goto L3c
        Lae:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r10 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.String r0 = "EOF while "
            java.lang.String r1 = " bytes expected"
            java.lang.String r0 = androidx.compose.foundation.c.o(r2, r0, r1)
            r10.<init>(r0)
            throw r10
        Lbc:
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readFloat(d7.d):java.lang.Object");
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readFully(ChunkBuffer chunkBuffer, int i10, d<? super t0> dVar) {
        return readFully$suspendImpl(this, chunkBuffer, i10, dVar);
    }

    /* JADX WARN: Path cross not found for [B:17:0x0044, B:18:0x0046], limit reached: 50 */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x0095 -> B:42:0x0098). Please report as a decompilation issue!!! */
    @Override // io.ktor.utils.io.ByteReadChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readInt(d7.d<? super java.lang.Integer> r10) throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteBufferChannel.C19671
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.utils.io.ByteBufferChannel$readInt$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19671) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readInt$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readInt$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r4 = (io.ktor.utils.io.ByteBufferChannel) r4
            k2.c.G(r10)
            goto L98
        L2e:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L36:
            k2.c.G(r10)
            r10 = 4
            r4 = r9
            r2 = r10
        L3c:
            java.nio.ByteBuffer r10 = r4.setupStateForRead()
            r5 = 0
            r6 = 0
            if (r10 != 0) goto L46
        L44:
            r8 = r5
            goto L7e
        L46:
            io.ktor.utils.io.internal.ReadWriteBufferState r7 = r4.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r7 = r7.capacity
            int r8 = r7._availableForRead$internal     // Catch: java.lang.Throwable -> L69
            if (r8 != 0) goto L57
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            goto L44
        L57:
            boolean r8 = r7.tryReadExact(r2)     // Catch: java.lang.Throwable -> L69
            if (r8 != 0) goto L5f
            r8 = r5
            goto L78
        L5f:
            int r6 = r10.remaining()     // Catch: java.lang.Throwable -> L69
            if (r6 >= r2) goto L6b
            r4.rollBytes(r10, r2)     // Catch: java.lang.Throwable -> L69
            goto L6b
        L69:
            r10 = move-exception
            goto Laf
        L6b:
            int r6 = r10.getInt()     // Catch: java.lang.Throwable -> L69
            java.lang.Integer r8 = new java.lang.Integer     // Catch: java.lang.Throwable -> L69
            r8.<init>(r6)     // Catch: java.lang.Throwable -> L69
            r4.bytesRead(r10, r7, r2)     // Catch: java.lang.Throwable -> L69
            r6 = r3
        L78:
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
        L7e:
            if (r6 == 0) goto L8b
            if (r8 == 0) goto L85
            java.lang.Number r8 = (java.lang.Number) r8
            return r8
        L85:
            java.lang.String r10 = "result"
            kotlin.jvm.internal.p.i(r10)
            throw r5
        L8b:
            r0.L$0 = r4
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r10 = r4.readSuspend(r2, r0)
            if (r10 != r1) goto L98
            return r1
        L98:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto La1
            goto L3c
        La1:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r10 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.String r0 = "EOF while "
            java.lang.String r1 = " bytes expected"
            java.lang.String r0 = androidx.compose.foundation.c.o(r2, r0, r1)
            r10.<init>(r0)
            throw r10
        Laf:
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readInt(d7.d):java.lang.Object");
    }

    /* JADX WARN: Path cross not found for [B:17:0x0045, B:18:0x0047], limit reached: 50 */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x0097 -> B:42:0x009a). Please report as a decompilation issue!!! */
    @Override // io.ktor.utils.io.ByteReadChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readLong(d7.d<? super java.lang.Long> r11) throws java.lang.Throwable {
        /*
            r10 = this;
            boolean r0 = r11 instanceof io.ktor.utils.io.ByteBufferChannel.C19681
            if (r0 == 0) goto L13
            r0 = r11
            io.ktor.utils.io.ByteBufferChannel$readLong$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19681) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readLong$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readLong$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r4 = (io.ktor.utils.io.ByteBufferChannel) r4
            k2.c.G(r11)
            goto L9a
        L2e:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L36:
            k2.c.G(r11)
            r11 = 8
            r4 = r10
            r2 = r11
        L3d:
            java.nio.ByteBuffer r11 = r4.setupStateForRead()
            r5 = 0
            r6 = 0
            if (r11 != 0) goto L47
        L45:
            r11 = r5
            goto L80
        L47:
            io.ktor.utils.io.internal.ReadWriteBufferState r7 = r4.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r7 = r7.capacity
            int r8 = r7._availableForRead$internal     // Catch: java.lang.Throwable -> L6a
            if (r8 != 0) goto L58
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            goto L45
        L58:
            boolean r8 = r7.tryReadExact(r2)     // Catch: java.lang.Throwable -> L6a
            if (r8 != 0) goto L60
            r11 = r5
            goto L7a
        L60:
            int r6 = r11.remaining()     // Catch: java.lang.Throwable -> L6a
            if (r6 >= r2) goto L6c
            r4.rollBytes(r11, r2)     // Catch: java.lang.Throwable -> L6a
            goto L6c
        L6a:
            r11 = move-exception
            goto Lb1
        L6c:
            long r8 = r11.getLong()     // Catch: java.lang.Throwable -> L6a
            java.lang.Long r6 = new java.lang.Long     // Catch: java.lang.Throwable -> L6a
            r6.<init>(r8)     // Catch: java.lang.Throwable -> L6a
            r4.bytesRead(r11, r7, r2)     // Catch: java.lang.Throwable -> L6a
            r11 = r6
            r6 = r3
        L7a:
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
        L80:
            if (r6 == 0) goto L8d
            if (r11 == 0) goto L87
            java.lang.Number r11 = (java.lang.Number) r11
            return r11
        L87:
            java.lang.String r11 = "result"
            kotlin.jvm.internal.p.i(r11)
            throw r5
        L8d:
            r0.L$0 = r4
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r11 = r4.readSuspend(r2, r0)
            if (r11 != r1) goto L9a
            return r1
        L9a:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto La3
            goto L3d
        La3:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r11 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.String r0 = "EOF while "
            java.lang.String r1 = " bytes expected"
            java.lang.String r0 = androidx.compose.foundation.c.o(r2, r0, r1)
            r11.<init>(r0)
            throw r11
        Lb1:
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readLong(d7.d):java.lang.Object");
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readPacket(int i10, d<? super ByteReadPacket> dVar) {
        return readPacket$suspendImpl(this, i10, dVar);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readRemaining(long j10, d<? super ByteReadPacket> dVar) {
        return readRemaining$suspendImpl(this, j10, dVar);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    @x6.e
    public void readSession(l<? super ReadSession, t0> consumer) {
        lookAhead(new C19711(consumer, this));
    }

    /* JADX WARN: Path cross not found for [B:17:0x0044, B:18:0x0046], limit reached: 50 */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x0095 -> B:42:0x0098). Please report as a decompilation issue!!! */
    @Override // io.ktor.utils.io.ByteReadChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readShort(d7.d<? super java.lang.Short> r10) throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteBufferChannel.C19721
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.utils.io.ByteBufferChannel$readShort$1 r0 = (io.ktor.utils.io.ByteBufferChannel.C19721) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readShort$1 r0 = new io.ktor.utils.io.ByteBufferChannel$readShort$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r4 = (io.ktor.utils.io.ByteBufferChannel) r4
            k2.c.G(r10)
            goto L98
        L2e:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L36:
            k2.c.G(r10)
            r10 = 2
            r4 = r9
            r2 = r10
        L3c:
            java.nio.ByteBuffer r10 = r4.setupStateForRead()
            r5 = 0
            r6 = 0
            if (r10 != 0) goto L46
        L44:
            r8 = r5
            goto L7e
        L46:
            io.ktor.utils.io.internal.ReadWriteBufferState r7 = r4.getState()
            io.ktor.utils.io.internal.RingBufferCapacity r7 = r7.capacity
            int r8 = r7._availableForRead$internal     // Catch: java.lang.Throwable -> L69
            if (r8 != 0) goto L57
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            goto L44
        L57:
            boolean r8 = r7.tryReadExact(r2)     // Catch: java.lang.Throwable -> L69
            if (r8 != 0) goto L5f
            r8 = r5
            goto L78
        L5f:
            int r6 = r10.remaining()     // Catch: java.lang.Throwable -> L69
            if (r6 >= r2) goto L6b
            r4.rollBytes(r10, r2)     // Catch: java.lang.Throwable -> L69
            goto L6b
        L69:
            r10 = move-exception
            goto Laf
        L6b:
            short r6 = r10.getShort()     // Catch: java.lang.Throwable -> L69
            java.lang.Short r8 = new java.lang.Short     // Catch: java.lang.Throwable -> L69
            r8.<init>(r6)     // Catch: java.lang.Throwable -> L69
            r4.bytesRead(r10, r7, r2)     // Catch: java.lang.Throwable -> L69
            r6 = r3
        L78:
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
        L7e:
            if (r6 == 0) goto L8b
            if (r8 == 0) goto L85
            java.lang.Number r8 = (java.lang.Number) r8
            return r8
        L85:
            java.lang.String r10 = "result"
            kotlin.jvm.internal.p.i(r10)
            throw r5
        L8b:
            r0.L$0 = r4
            r0.I$0 = r2
            r0.label = r3
            java.lang.Object r10 = r4.readSuspend(r2, r0)
            if (r10 != r1) goto L98
            return r1
        L98:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto La1
            goto L3c
        La1:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r10 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.String r0 = "EOF while "
            java.lang.String r1 = " bytes expected"
            java.lang.String r0 = androidx.compose.foundation.c.o(r2, r0, r1)
            r10.<init>(r0)
            throw r10
        Laf:
            r4.restoreStateAfterRead()
            r4.tryTerminate$ktor_io()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readShort(d7.d):java.lang.Object");
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    @x6.e
    public Object readSuspendableSession(p<? super SuspendableReadSession, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) {
        return readSuspendableSession$suspendImpl(this, pVar, dVar);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readUTF8Line(int i10, d<? super String> dVar) {
        return readUTF8Line$suspendImpl(this, i10, dVar);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public <A extends Appendable> Object readUTF8LineTo(A a10, int i10, d<? super Boolean> dVar) {
        return readUTF8LineToAscii(a10, i10, dVar);
    }

    @Override // io.ktor.utils.io.LookAheadSession
    public ByteBuffer request(int skip, int atLeast) {
        ReadWriteBufferState state = getState();
        int i10 = state.capacity._availableForRead$internal;
        int i11 = this.readPosition;
        if (i10 < atLeast + skip) {
            return null;
        }
        if (state.getIdle() || !((state instanceof ReadWriteBufferState.Reading) || (state instanceof ReadWriteBufferState.ReadingWriting))) {
            if (setupStateForRead() == null) {
                return null;
            }
            return request(skip, atLeast);
        }
        ByteBuffer readBuffer = state.getReadBuffer();
        prepareBuffer(readBuffer, carryIndex(readBuffer, i11 + skip), i10 - skip);
        if (readBuffer.remaining() >= atLeast) {
            return readBuffer;
        }
        return null;
    }

    public final ByteBufferChannel resolveChannelInstance$ktor_io() {
        ByteBufferChannel byteBufferChannelResolveDelegation;
        JoiningState joiningState = this.joining;
        return (joiningState == null || (byteBufferChannelResolveDelegation = resolveDelegation(this, joiningState)) == null) ? this : byteBufferChannelResolveDelegation;
    }

    public final void restoreStateAfterWrite$ktor_io() {
        ReadWriteBufferState readWriteBufferStateStopWriting$ktor_io;
        ReadWriteBufferState.IdleNonEmpty idleNonEmpty;
        ReadWriteBufferState readWriteBufferState = null;
        loop0: while (true) {
            Object obj = this._state;
            readWriteBufferStateStopWriting$ktor_io = ((ReadWriteBufferState) obj).stopWriting$ktor_io();
            if ((readWriteBufferStateStopWriting$ktor_io instanceof ReadWriteBufferState.IdleNonEmpty) && readWriteBufferStateStopWriting$ktor_io.capacity.isEmpty()) {
                readWriteBufferStateStopWriting$ktor_io = ReadWriteBufferState.IdleEmpty.INSTANCE;
                readWriteBufferState = readWriteBufferStateStopWriting$ktor_io;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, readWriteBufferStateStopWriting$ktor_io)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    break;
                }
            }
        }
        if (readWriteBufferStateStopWriting$ktor_io != ReadWriteBufferState.IdleEmpty.INSTANCE || (idleNonEmpty = (ReadWriteBufferState.IdleNonEmpty) readWriteBufferState) == null) {
            return;
        }
        releaseBuffer(idleNonEmpty.getInitial());
    }

    public void setTotalBytesRead$ktor_io(long j10) {
        this.totalBytesRead = j10;
    }

    public void setTotalBytesWritten$ktor_io(long j10) {
        this.totalBytesWritten = j10;
    }

    public final ByteBuffer setupStateForWrite$ktor_io() throws Throwable {
        ReadWriteBufferState readWriteBufferStateStartWriting$ktor_io;
        ReadWriteBufferState.Initial initial;
        d<t0> writeOp = getWriteOp();
        if (writeOp != null) {
            throw new IllegalStateException("Write operation is already in progress: " + writeOp);
        }
        ReadWriteBufferState.Initial initialNewBuffer = null;
        while (true) {
            Object obj = this._state;
            ReadWriteBufferState readWriteBufferState = (ReadWriteBufferState) obj;
            if (this.joining != null) {
                if (initialNewBuffer != null) {
                    releaseBuffer(initialNewBuffer);
                }
                return null;
            }
            if (getClosed() != null) {
                if (initialNewBuffer != null) {
                    releaseBuffer(initialNewBuffer);
                }
                ByteBufferChannelKt.rethrowClosed(getClosed().getSendException());
                throw new KotlinNothingValueException();
            }
            if (readWriteBufferState == ReadWriteBufferState.IdleEmpty.INSTANCE) {
                if (initialNewBuffer == null) {
                    initialNewBuffer = newBuffer();
                }
                readWriteBufferStateStartWriting$ktor_io = initialNewBuffer.startWriting$ktor_io();
            } else {
                if (readWriteBufferState == ReadWriteBufferState.Terminated.INSTANCE) {
                    if (initialNewBuffer != null) {
                        releaseBuffer(initialNewBuffer);
                    }
                    if (this.joining != null) {
                        return null;
                    }
                    ByteBufferChannelKt.rethrowClosed(getClosed().getSendException());
                    throw new KotlinNothingValueException();
                }
                readWriteBufferStateStartWriting$ktor_io = readWriteBufferState.startWriting$ktor_io();
            }
            ReadWriteBufferState readWriteBufferState2 = readWriteBufferStateStartWriting$ktor_io;
            initial = initialNewBuffer;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, readWriteBufferState2)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    break;
                }
            }
            if (getClosed() != null) {
                restoreStateAfterWrite$ktor_io();
                tryTerminate$ktor_io();
                ByteBufferChannelKt.rethrowClosed(getClosed().getSendException());
                throw new KotlinNothingValueException();
            }
            ByteBuffer writeBuffer = readWriteBufferState2.getWriteBuffer();
            if (initial != null) {
                if (readWriteBufferState == null) {
                    kotlin.jvm.internal.p.i("old");
                    throw null;
                }
                if (readWriteBufferState != ReadWriteBufferState.IdleEmpty.INSTANCE) {
                    releaseBuffer(initial);
                }
            }
            prepareBuffer(writeBuffer, this.writePosition, readWriteBufferState2.capacity._availableForWrite$internal);
            return writeBuffer;
            initialNewBuffer = initial;
        }
    }

    @Override // io.ktor.utils.io.HasReadSession
    public SuspendableReadSession startReadSession() {
        return this.readSession;
    }

    public String toString() {
        return "ByteBufferChannel(" + hashCode() + ", " + getState() + ')';
    }

    public final boolean tryTerminate$ktor_io() {
        if (getClosed() == null || !tryReleaseBuffer(false)) {
            return false;
        }
        JoiningState joiningState = this.joining;
        if (joiningState != null) {
            ensureClosedJoined(joiningState);
        }
        resumeReadOp();
        resumeWriteOp();
        return true;
    }

    public final Object tryWriteSuspend$ktor_io(int i10, d<? super t0> dVar) throws Throwable {
        Throwable sendException;
        a aVar = a.f15033i;
        t0 t0Var = t0.f22605a;
        if (!writeSuspendPredicate(i10)) {
            ClosedElement closed = getClosed();
            if (closed == null || (sendException = closed.getSendException()) == null) {
                return t0Var;
            }
            ByteBufferChannelKt.rethrowClosed(sendException);
            throw new KotlinNothingValueException();
        }
        this.writeSuspensionSize = i10;
        if (this.attachedJob != null) {
            Object objInvoke = this.writeSuspension.invoke(dVar);
            return objInvoke == aVar ? objInvoke : t0Var;
        }
        CancellableReusableContinuation<t0> cancellableReusableContinuation = this.writeSuspendContinuationCache;
        this.writeSuspension.invoke(cancellableReusableContinuation);
        Object objCompleteSuspendBlock = cancellableReusableContinuation.completeSuspendBlock(t7.a.A(dVar));
        return objCompleteSuspendBlock == aVar ? objCompleteSuspendBlock : t0Var;
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object write(int i10, l<? super ByteBuffer, t0> lVar, d<? super t0> dVar) {
        return write$suspendImpl(this, i10, lVar, dVar);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeAvailable(ChunkBuffer chunkBuffer, d<? super Integer> dVar) {
        return writeAvailable$suspendImpl(this, chunkBuffer, dVar);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeByte(byte b10, d<? super t0> dVar) {
        return writeByte$suspendImpl(this, b10, dVar);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeDouble(double d4, d<? super t0> dVar) {
        return writeDouble$suspendImpl(this, d4, dVar);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeFloat(float f10, d<? super t0> dVar) {
        return writeFloat$suspendImpl(this, f10, dVar);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeFully(Buffer buffer, d<? super t0> dVar) {
        return writeFully$suspendImpl(this, buffer, dVar);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    /* JADX INFO: renamed from: writeFully-JT6ljtQ, reason: not valid java name */
    public Object mo6736writeFullyJT6ljtQ(ByteBuffer byteBuffer, int i10, int i11, d<? super t0> dVar) {
        return m6734writeFullyJT6ljtQ$suspendImpl(this, byteBuffer, i10, i11, dVar);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeInt(int i10, d<? super t0> dVar) {
        return writeInt$suspendImpl(this, i10, dVar);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeLong(long j10, d<? super t0> dVar) {
        return writeLong$suspendImpl(this, j10, dVar);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writePacket(ByteReadPacket byteReadPacket, d<? super t0> dVar) {
        return writePacket$suspendImpl(this, byteReadPacket, dVar);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeShort(short s10, d<? super t0> dVar) {
        return writeShort$suspendImpl(this, s10, dVar);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    @x6.e
    public Object writeSuspendSession(p<? super WriterSuspendSession, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) {
        return writeSuspendSession$suspendImpl(this, pVar, dVar);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeWhile(l<? super ByteBuffer, Boolean> lVar, d<? super t0> dVar) {
        return writeWhile$suspendImpl(this, lVar, dVar);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readAvailable(ByteBuffer byteBuffer, d<? super Integer> dVar) {
        return readAvailable$suspendImpl(this, byteBuffer, dVar);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public final Object readFully(byte[] bArr, int i10, int i11, d<? super t0> dVar) throws Throwable {
        Object fullySuspend;
        int asMuchAsPossible = readAsMuchAsPossible(bArr, i10, i11);
        return (asMuchAsPossible >= i11 || (fullySuspend = readFullySuspend(bArr, i10 + asMuchAsPossible, i11 - asMuchAsPossible, dVar)) != a.f15033i) ? t0.f22605a : fullySuspend;
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeAvailable(ByteBuffer byteBuffer, d<? super Integer> dVar) {
        return writeAvailable$suspendImpl(this, byteBuffer, dVar);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeFully(ByteBuffer byteBuffer, d<? super t0> dVar) {
        return writeFully$suspendImpl(this, byteBuffer, dVar);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readAvailable(byte[] bArr, int i10, int i11, d<? super Integer> dVar) {
        return readAvailable$suspendImpl(this, bArr, i10, i11, dVar);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeAvailable(byte[] bArr, int i10, int i11, d<? super Integer> dVar) {
        return writeAvailable$suspendImpl(this, bArr, i10, i11, dVar);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeFully(byte[] bArr, int i10, int i11, d<? super t0> dVar) {
        return writeFully$suspendImpl(this, bArr, i10, i11, dVar);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public int readAvailable(int min, l<? super ByteBuffer, t0> block) throws Throwable {
        int i10;
        if (min <= 0) {
            throw new IllegalArgumentException("min should be positive");
        }
        if (min <= 4088) {
            ByteBuffer byteBuffer = setupStateForRead();
            boolean z = false;
            if (byteBuffer == null) {
                i10 = 0;
            } else {
                RingBufferCapacity ringBufferCapacity = getState().capacity;
                try {
                    if (ringBufferCapacity._availableForRead$internal == 0) {
                        restoreStateAfterRead();
                        tryTerminate$ktor_io();
                        i10 = 0;
                    } else {
                        int iTryReadAtLeast = ringBufferCapacity.tryReadAtLeast(min);
                        if (iTryReadAtLeast <= 0 || iTryReadAtLeast < min) {
                            i10 = 0;
                        } else {
                            int iPosition = byteBuffer.position();
                            int iLimit = byteBuffer.limit();
                            block.invoke(byteBuffer);
                            if (iLimit == byteBuffer.limit()) {
                                int iPosition2 = byteBuffer.position() - iPosition;
                                if (iPosition2 >= 0) {
                                    bytesRead(byteBuffer, ringBufferCapacity, iPosition2);
                                    if (iPosition2 < iTryReadAtLeast) {
                                        ringBufferCapacity.completeWrite(iTryReadAtLeast - iPosition2);
                                        ringBufferCapacity.flush();
                                    }
                                    z = true;
                                    i10 = iPosition2;
                                } else {
                                    throw new IllegalStateException("Position shouldn't been moved backwards.");
                                }
                            } else {
                                throw new IllegalStateException("Buffer limit shouldn't be modified.");
                            }
                        }
                    }
                } finally {
                    restoreStateAfterRead();
                    tryTerminate$ktor_io();
                }
            }
            if (z) {
                return i10;
            }
            return -1;
        }
        throw new IllegalArgumentException(androidx.compose.foundation.c.o(min, "Min(", ") shouldn't be greater than 4088").toString());
    }

    /* JADX WARN: Finally extract failed */
    @Override // io.ktor.utils.io.ByteWriteChannel
    public int writeAvailable(int min, l<? super ByteBuffer, t0> block) throws Throwable {
        ByteBufferChannel byteBufferChannelResolveDelegation;
        int i10;
        int i11;
        if (min <= 0) {
            throw new IllegalArgumentException("min should be positive");
        }
        if (min <= 4088) {
            JoiningState joiningState = this.joining;
            if (joiningState == null || (byteBufferChannelResolveDelegation = resolveDelegation(this, joiningState)) == null) {
                byteBufferChannelResolveDelegation = this;
            }
            ByteBuffer byteBuffer = byteBufferChannelResolveDelegation.setupStateForWrite$ktor_io();
            int iPosition = 0;
            if (byteBuffer == null) {
                i11 = 0;
            } else {
                RingBufferCapacity ringBufferCapacity = byteBufferChannelResolveDelegation.getState().capacity;
                long totalBytesWritten = byteBufferChannelResolveDelegation.get_totalBytesWritten();
                try {
                    ClosedElement closed = byteBufferChannelResolveDelegation.getClosed();
                    if (closed != null) {
                        ByteBufferChannelKt.rethrowClosed(closed.getSendException());
                        throw new KotlinNothingValueException();
                    }
                    int iTryWriteAtLeast = ringBufferCapacity.tryWriteAtLeast(min);
                    if (iTryWriteAtLeast <= 0) {
                        i10 = 0;
                    } else {
                        byteBufferChannelResolveDelegation.prepareBuffer(byteBuffer, byteBufferChannelResolveDelegation.writePosition, iTryWriteAtLeast);
                        int iPosition2 = byteBuffer.position();
                        int iLimit = byteBuffer.limit();
                        block.invoke(byteBuffer);
                        if (iLimit == byteBuffer.limit()) {
                            iPosition = byteBuffer.position() - iPosition2;
                            if (iPosition < 0) {
                                throw new IllegalStateException("Position has been moved backward: pushback is not supported");
                            }
                            if (iPosition >= 0) {
                                byteBufferChannelResolveDelegation.bytesWritten(byteBuffer, ringBufferCapacity, iPosition);
                                if (iPosition < iTryWriteAtLeast) {
                                    ringBufferCapacity.completeRead(iTryWriteAtLeast - iPosition);
                                }
                                i10 = 1;
                            } else {
                                throw new IllegalStateException();
                            }
                        } else {
                            throw new IllegalStateException("Buffer limit modified");
                        }
                    }
                    if (ringBufferCapacity.isFull() || byteBufferChannelResolveDelegation.getAutoFlush()) {
                        byteBufferChannelResolveDelegation.flush();
                    }
                    if (byteBufferChannelResolveDelegation != this) {
                        setTotalBytesWritten$ktor_io((byteBufferChannelResolveDelegation.get_totalBytesWritten() - totalBytesWritten) + get_totalBytesWritten());
                    }
                    byteBufferChannelResolveDelegation.restoreStateAfterWrite$ktor_io();
                    byteBufferChannelResolveDelegation.tryTerminate$ktor_io();
                    int i12 = iPosition;
                    iPosition = i10;
                    i11 = i12;
                } catch (Throwable th) {
                    if (ringBufferCapacity.isFull() || byteBufferChannelResolveDelegation.getAutoFlush()) {
                        byteBufferChannelResolveDelegation.flush();
                    }
                    if (byteBufferChannelResolveDelegation != this) {
                        setTotalBytesWritten$ktor_io((byteBufferChannelResolveDelegation.get_totalBytesWritten() - totalBytesWritten) + get_totalBytesWritten());
                    }
                    byteBufferChannelResolveDelegation.restoreStateAfterWrite$ktor_io();
                    byteBufferChannelResolveDelegation.tryTerminate$ktor_io();
                    throw th;
                }
            }
            if (iPosition == 0) {
                return -1;
            }
            return i11;
        }
        throw new IllegalArgumentException(androidx.compose.foundation.c.o(min, "Min(", ") shouldn't be greater than 4088").toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readAvailableSuspend(java.nio.ByteBuffer r6, d7.d<? super java.lang.Integer> r7) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteBufferChannel.AnonymousClass2
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$2 r0 = (io.ktor.utils.io.ByteBufferChannel.AnonymousClass2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$2 r0 = new io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$2
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3e
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r7)
            return r7
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            java.lang.Object r6 = r0.L$1
            java.nio.ByteBuffer r6 = (java.nio.ByteBuffer) r6
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r1 = (io.ktor.utils.io.ByteBufferChannel) r1
            k2.c.G(r7)
            goto L4f
        L3e:
            k2.c.G(r7)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r5.readSuspend(r3, r0)
            if (r7 != r4) goto L4e
            goto L6b
        L4e:
            r1 = r5
        L4f:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 != 0) goto L5e
            java.lang.Integer r6 = new java.lang.Integer
            r7 = -1
            r6.<init>(r7)
            return r6
        L5e:
            r7 = 0
            r0.L$0 = r7
            r0.L$1 = r7
            r0.label = r2
            java.lang.Object r6 = r1.readAvailable(r6, r0)
            if (r6 != r4) goto L6c
        L6b:
            return r4
        L6c:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readAvailableSuspend(java.nio.ByteBuffer, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeAvailableSuspend(io.ktor.utils.io.core.internal.ChunkBuffer r7, d7.d<? super java.lang.Integer> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteBufferChannel.C19823
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$3 r0 = (io.ktor.utils.io.ByteBufferChannel.C19823) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$3 r0 = new io.ktor.utils.io.ByteBufferChannel$writeAvailableSuspend$3
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L45
            if (r2 == r5) goto L39
            if (r2 == r4) goto L35
            if (r2 != r3) goto L2d
            k2.c.G(r8)
            return r8
        L2d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L35:
            k2.c.G(r8)
            return r8
        L39:
            java.lang.Object r7 = r0.L$1
            io.ktor.utils.io.core.internal.ChunkBuffer r7 = (io.ktor.utils.io.core.internal.ChunkBuffer) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r2 = (io.ktor.utils.io.ByteBufferChannel) r2
            k2.c.G(r8)
            goto L56
        L45:
            k2.c.G(r8)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r5
            java.lang.Object r8 = r6.writeSuspend(r5, r0)
            if (r8 != r1) goto L55
            goto L7b
        L55:
            r2 = r6
        L56:
            io.ktor.utils.io.internal.JoiningState r8 = r2.joining
            r5 = 0
            if (r8 == 0) goto L6f
            io.ktor.utils.io.ByteBufferChannel r8 = r2.resolveDelegation(r2, r8)
            if (r8 == 0) goto L6f
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r4
            java.lang.Object r7 = r8.writeAvailableSuspend(r7, r0)
            if (r7 != r1) goto L6e
            goto L7b
        L6e:
            return r7
        L6f:
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r7 = r2.writeAvailable(r7, r0)
            if (r7 != r1) goto L7c
        L7b:
            return r1
        L7c:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeAvailableSuspend(io.ktor.utils.io.core.internal.ChunkBuffer, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object writeFully$suspendImpl(ByteBufferChannel byteBufferChannel, Buffer buffer, d<? super t0> dVar) throws Throwable {
        Object objWriteFullySuspend;
        byteBufferChannel.writeAsMuchAsPossible(buffer);
        return (buffer.getWritePosition() <= buffer.getReadPosition() || (objWriteFullySuspend = byteBufferChannel.writeFullySuspend(buffer, dVar)) != a.f15033i) ? t0.f22605a : objWriteFullySuspend;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public final Object readFully(ByteBuffer byteBuffer, d<? super Integer> dVar) throws Throwable {
        int asMuchAsPossible = readAsMuchAsPossible(byteBuffer);
        if (!byteBuffer.hasRemaining()) {
            return new Integer(asMuchAsPossible);
        }
        return readFullySuspend(byteBuffer, asMuchAsPossible, dVar);
    }

    private final void resumeReadOp(r7.a<? extends Throwable> exception) {
        d dVar = (d) _readOp$FU.getAndSet(this, null);
        if (dVar != null) {
            dVar.resumeWith(new c0((Throwable) exception.invoke()));
        }
    }

    public static Object writeAvailable$suspendImpl(ByteBufferChannel byteBufferChannel, ChunkBuffer chunkBuffer, d<? super Integer> dVar) throws Throwable {
        ByteBufferChannel byteBufferChannelResolveDelegation;
        ByteBufferChannel byteBufferChannelResolveDelegation2;
        JoiningState joiningState = byteBufferChannel.joining;
        if (joiningState != null && (byteBufferChannelResolveDelegation2 = byteBufferChannel.resolveDelegation(byteBufferChannel, joiningState)) != null) {
            return byteBufferChannelResolveDelegation2.writeAvailable(chunkBuffer, dVar);
        }
        int iWriteAsMuchAsPossible = byteBufferChannel.writeAsMuchAsPossible(chunkBuffer);
        if (iWriteAsMuchAsPossible > 0) {
            return new Integer(iWriteAsMuchAsPossible);
        }
        JoiningState joiningState2 = byteBufferChannel.joining;
        return (joiningState2 == null || (byteBufferChannelResolveDelegation = byteBufferChannel.resolveDelegation(byteBufferChannel, joiningState2)) == null) ? byteBufferChannel.writeAvailableSuspend(chunkBuffer, dVar) : byteBufferChannelResolveDelegation.writeAvailableSuspend(chunkBuffer, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0078 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0058 -> B:22:0x005b). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeFullySuspend(io.ktor.utils.io.core.Buffer r8, d7.d<? super x6.t0> r9) throws java.lang.Throwable {
        /*
            r7 = this;
            x6.t0 r0 = x6.t0.f22605a
            boolean r1 = r9 instanceof io.ktor.utils.io.ByteBufferChannel.C19853
            if (r1 == 0) goto L15
            r1 = r9
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$3 r1 = (io.ktor.utils.io.ByteBufferChannel.C19853) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.label = r2
            goto L1a
        L15:
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$3 r1 = new io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$3
            r1.<init>(r9)
        L1a:
            java.lang.Object r9 = r1.result
            e7.a r2 = e7.a.f15033i
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L40
            if (r3 == r5) goto L34
            if (r3 != r4) goto L2c
            k2.c.G(r9)
            goto L73
        L2c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L34:
            java.lang.Object r8 = r1.L$1
            io.ktor.utils.io.core.Buffer r8 = (io.ktor.utils.io.core.Buffer) r8
            java.lang.Object r3 = r1.L$0
            io.ktor.utils.io.ByteBufferChannel r3 = (io.ktor.utils.io.ByteBufferChannel) r3
            k2.c.G(r9)
            goto L5b
        L40:
            k2.c.G(r9)
            r3 = r7
        L44:
            int r9 = r8.getWritePosition()
            int r6 = r8.getReadPosition()
            if (r9 <= r6) goto L78
            r1.L$0 = r3
            r1.L$1 = r8
            r1.label = r5
            java.lang.Object r9 = r3.tryWriteSuspend$ktor_io(r5, r1)
            if (r9 != r2) goto L5b
            goto L72
        L5b:
            io.ktor.utils.io.internal.JoiningState r9 = r3.joining
            if (r9 == 0) goto L74
            io.ktor.utils.io.ByteBufferChannel r9 = r3.resolveDelegation(r3, r9)
            if (r9 == 0) goto L74
            r3 = 0
            r1.L$0 = r3
            r1.L$1 = r3
            r1.label = r4
            java.lang.Object r8 = r9.writeFully(r8, r1)
            if (r8 != r2) goto L73
        L72:
            return r2
        L73:
            return r0
        L74:
            r3.writeAsMuchAsPossible(r8)
            goto L44
        L78:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeFullySuspend(io.ktor.utils.io.core.Buffer, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeSuspend(int r5, d7.d<? super x6.t0> r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteBufferChannel.C19913
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteBufferChannel$writeSuspend$3 r0 = (io.ktor.utils.io.ByteBufferChannel.C19913) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$writeSuspend$3 r0 = new io.ktor.utils.io.ByteBufferChannel$writeSuspend$3
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            int r5 = r0.I$0
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r1 = (io.ktor.utils.io.ByteBufferChannel) r1
            k2.c.G(r6)
            goto L37
        L2b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L33:
            k2.c.G(r6)
            r1 = r4
        L37:
            boolean r6 = r1.writeSuspendPredicate(r5)
            if (r6 == 0) goto L5b
            r0.L$0 = r1
            r0.I$0 = r5
            r0.label = r2
            ka.l r6 = new ka.l
            d7.d r3 = t7.a.A(r0)
            r6.<init>(r2, r3)
            r6.s()
            access$writeSuspendBlock(r1, r5, r6)
            java.lang.Object r6 = r6.r()
            e7.a r3 = e7.a.f15033i
            if (r6 != r3) goto L37
            return r3
        L5b:
            io.ktor.utils.io.internal.ClosedElement r5 = r1.getClosed()
            if (r5 == 0) goto L71
            java.lang.Throwable r5 = r5.getSendException()
            if (r5 != 0) goto L68
            goto L71
        L68:
            io.ktor.utils.io.ByteBufferChannelKt.access$rethrowClosed(r5)
            kotlin.KotlinNothingValueException r5 = new kotlin.KotlinNothingValueException
            r5.<init>()
            throw r5
        L71:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeSuspend(int, d7.d):java.lang.Object");
    }

    public static Object readAvailable$suspendImpl(ByteBufferChannel byteBufferChannel, ByteBuffer byteBuffer, d<? super Integer> dVar) throws Throwable {
        int asMuchAsPossible = byteBufferChannel.readAsMuchAsPossible(byteBuffer);
        if (asMuchAsPossible == 0 && byteBufferChannel.getClosed() != null) {
            asMuchAsPossible = byteBufferChannel.getState().capacity.flush() ? byteBufferChannel.readAsMuchAsPossible(byteBuffer) : -1;
        } else if (asMuchAsPossible <= 0 && byteBuffer.hasRemaining()) {
            return byteBufferChannel.readAvailableSuspend(byteBuffer, dVar);
        }
        return new Integer(asMuchAsPossible);
    }

    public static /* synthetic */ Object writeFully$suspendImpl(ByteBufferChannel byteBufferChannel, byte[] bArr, int i10, int i11, d<? super t0> dVar) throws Throwable {
        Object objWriteFullySuspend;
        ByteBufferChannel byteBufferChannelResolveDelegation;
        a aVar = a.f15033i;
        t0 t0Var = t0.f22605a;
        JoiningState joiningState = byteBufferChannel.joining;
        if (joiningState != null && (byteBufferChannelResolveDelegation = byteBufferChannel.resolveDelegation(byteBufferChannel, joiningState)) != null) {
            Object objWriteFully = byteBufferChannelResolveDelegation.writeFully(bArr, i10, i11, dVar);
            return objWriteFully == aVar ? objWriteFully : t0Var;
        }
        while (i11 > 0) {
            int iWriteAsMuchAsPossible = byteBufferChannel.writeAsMuchAsPossible(bArr, i10, i11);
            if (iWriteAsMuchAsPossible == 0) {
                break;
            }
            i10 += iWriteAsMuchAsPossible;
            i11 -= iWriteAsMuchAsPossible;
        }
        return (i11 != 0 && (objWriteFullySuspend = byteBufferChannel.writeFullySuspend(bArr, i10, i11, dVar)) == aVar) ? objWriteFullySuspend : t0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readAvailableSuspend(io.ktor.utils.io.core.internal.ChunkBuffer r6, d7.d<? super java.lang.Integer> r7) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteBufferChannel.AnonymousClass3
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$3 r0 = (io.ktor.utils.io.ByteBufferChannel.AnonymousClass3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$3 r0 = new io.ktor.utils.io.ByteBufferChannel$readAvailableSuspend$3
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3e
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r7)
            return r7
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            java.lang.Object r6 = r0.L$1
            io.ktor.utils.io.core.internal.ChunkBuffer r6 = (io.ktor.utils.io.core.internal.ChunkBuffer) r6
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r1 = (io.ktor.utils.io.ByteBufferChannel) r1
            k2.c.G(r7)
            goto L4f
        L3e:
            k2.c.G(r7)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r5.readSuspend(r3, r0)
            if (r7 != r4) goto L4e
            goto L6b
        L4e:
            r1 = r5
        L4f:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 != 0) goto L5e
            java.lang.Integer r6 = new java.lang.Integer
            r7 = -1
            r6.<init>(r7)
            return r6
        L5e:
            r7 = 0
            r0.L$0 = r7
            r0.L$1 = r7
            r0.label = r2
            java.lang.Object r6 = r1.readAvailable(r6, r0)
            if (r6 != r4) goto L6c
        L6b:
            return r4
        L6c:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readAvailableSuspend(io.ktor.utils.io.core.internal.ChunkBuffer, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x005e -> B:22:0x0061). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readFullySuspend(io.ktor.utils.io.core.internal.ChunkBuffer r10, int r11, d7.d<? super x6.t0> r12) throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r12 instanceof io.ktor.utils.io.ByteBufferChannel.C19652
            if (r0 == 0) goto L13
            r0 = r12
            io.ktor.utils.io.ByteBufferChannel$readFullySuspend$2 r0 = (io.ktor.utils.io.ByteBufferChannel.C19652) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readFullySuspend$2 r0 = new io.ktor.utils.io.ByteBufferChannel$readFullySuspend$2
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3a
            if (r1 != r2) goto L32
            int r10 = r0.I$1
            int r11 = r0.I$0
            java.lang.Object r1 = r0.L$1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = (io.ktor.utils.io.core.internal.ChunkBuffer) r1
            java.lang.Object r3 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r3 = (io.ktor.utils.io.ByteBufferChannel) r3
            k2.c.G(r12)
            r4 = r1
            goto L61
        L32:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L3a:
            k2.c.G(r12)
            r12 = 0
            r3 = r9
        L3f:
            int r1 = r10.getLimit()
            int r4 = r10.getWritePosition()
            if (r1 <= r4) goto L8f
            if (r12 >= r11) goto L8f
            r0.L$0 = r3
            r0.L$1 = r10
            r0.I$0 = r11
            r0.I$1 = r12
            r0.label = r2
            java.lang.Object r1 = r3.readSuspend(r2, r0)
            e7.a r4 = e7.a.f15033i
            if (r1 != r4) goto L5e
            return r4
        L5e:
            r4 = r10
            r10 = r12
            r12 = r1
        L61:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L75
            int r6 = r11 - r10
            r7 = 2
            r8 = 0
            r5 = 0
            int r12 = readAsMuchAsPossible$default(r3, r4, r5, r6, r7, r8)
            int r12 = r12 + r10
            r10 = r4
            goto L3f
        L75:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r12 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unexpected EOF: expected "
            r0.<init>(r1)
            int r11 = r11 - r10
            r0.append(r11)
            java.lang.String r10 = " more bytes"
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            r12.<init>(r10)
            throw r12
        L8f:
            x6.t0 r10 = x6.t0.f22605a
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readFullySuspend(io.ktor.utils.io.core.internal.ChunkBuffer, int, d7.d):java.lang.Object");
    }

    public static Object writeAvailable$suspendImpl(ByteBufferChannel byteBufferChannel, byte[] bArr, int i10, int i11, d<? super Integer> dVar) throws Throwable {
        ByteBufferChannel byteBufferChannelResolveDelegation;
        JoiningState joiningState = byteBufferChannel.joining;
        if (joiningState != null && (byteBufferChannelResolveDelegation = byteBufferChannel.resolveDelegation(byteBufferChannel, joiningState)) != null) {
            return byteBufferChannelResolveDelegation.writeAvailable(bArr, i10, i11, dVar);
        }
        int iWriteAsMuchAsPossible = byteBufferChannel.writeAsMuchAsPossible(bArr, i10, i11);
        if (iWriteAsMuchAsPossible > 0) {
            return new Integer(iWriteAsMuchAsPossible);
        }
        return byteBufferChannel.writeSuspend(bArr, i10, i11, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0052 -> B:20:0x0055). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeFullySuspend(byte[] r6, int r7, int r8, d7.d<? super x6.t0> r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteBufferChannel.AnonymousClass5
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$5 r0 = (io.ktor.utils.io.ByteBufferChannel.AnonymousClass5) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$5 r0 = new io.ktor.utils.io.ByteBufferChannel$writeFullySuspend$5
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L39
            if (r1 != r2) goto L31
            int r6 = r0.I$1
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$1
            byte[] r8 = (byte[]) r8
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r1 = (io.ktor.utils.io.ByteBufferChannel) r1
            k2.c.G(r9)
            goto L55
        L31:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L39:
            k2.c.G(r9)
            r1 = r5
        L3d:
            if (r8 <= 0) goto L61
            r0.L$0 = r1
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r0.label = r2
            java.lang.Object r9 = r1.writeAvailable(r6, r7, r8, r0)
            e7.a r3 = e7.a.f15033i
            if (r9 != r3) goto L52
            return r3
        L52:
            r4 = r8
            r8 = r6
            r6 = r4
        L55:
            java.lang.Number r9 = (java.lang.Number) r9
            int r9 = r9.intValue()
            int r7 = r7 + r9
            int r6 = r6 - r9
            r4 = r8
            r8 = r6
            r6 = r4
            goto L3d
        L61:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.writeFullySuspend(byte[], int, int, d7.d):java.lang.Object");
    }

    public /* synthetic */ ByteBufferChannel(boolean z, ObjectPool objectPool, int i10, int i11, h hVar) {
        this(z, (i11 & 2) != 0 ? ObjectPoolKt.getBufferObjectPool() : objectPool, (i11 & 4) != 0 ? 8 : i10);
    }

    public static Object readAvailable$suspendImpl(ByteBufferChannel byteBufferChannel, ChunkBuffer chunkBuffer, d<? super Integer> dVar) {
        int asMuchAsPossible$default = readAsMuchAsPossible$default(byteBufferChannel, chunkBuffer, 0, 0, 6, null);
        if (asMuchAsPossible$default == 0 && byteBufferChannel.getClosed() != null) {
            asMuchAsPossible$default = byteBufferChannel.getState().capacity.flush() ? readAsMuchAsPossible$default(byteBufferChannel, chunkBuffer, 0, 0, 6, null) : -1;
        } else if (asMuchAsPossible$default <= 0 && chunkBuffer.getLimit() > chunkBuffer.getWritePosition()) {
            return byteBufferChannel.readAvailableSuspend(chunkBuffer, dVar);
        }
        return new Integer(asMuchAsPossible$default);
    }

    public ByteBufferChannel(ByteBuffer byteBuffer) {
        this(false, ObjectPoolKt.getBufferObjectNoPool(), 0);
        ReadWriteBufferState.Initial initial = new ReadWriteBufferState.Initial(byteBuffer.slice(), 0);
        initial.capacity.resetForRead();
        this._state = initial.startWriting$ktor_io();
        restoreStateAfterWrite$ktor_io();
        ByteWriteChannelKt.close(this);
        tryTerminate$ktor_io();
    }

    private final int readAsMuchAsPossible(Buffer dst, int consumed, int max) throws Throwable {
        int iTryReadAtMost;
        do {
            ByteBuffer byteBuffer = setupStateForRead();
            boolean z = false;
            if (byteBuffer == null) {
                iTryReadAtMost = 0;
            } else {
                RingBufferCapacity ringBufferCapacity = getState().capacity;
                try {
                    if (ringBufferCapacity._availableForRead$internal == 0) {
                        restoreStateAfterRead();
                        tryTerminate$ktor_io();
                        iTryReadAtMost = 0;
                    } else {
                        int limit = dst.getLimit() - dst.getWritePosition();
                        iTryReadAtMost = ringBufferCapacity.tryReadAtMost(Math.min(byteBuffer.remaining(), Math.min(limit, max)));
                        if (iTryReadAtMost > 0) {
                            if (limit < byteBuffer.remaining()) {
                                byteBuffer.limit(byteBuffer.position() + limit);
                            }
                            BufferPrimitivesJvmKt.writeFully(dst, byteBuffer);
                            bytesRead(byteBuffer, ringBufferCapacity, iTryReadAtMost);
                            z = true;
                        }
                    }
                } finally {
                    restoreStateAfterRead();
                    tryTerminate$ktor_io();
                }
            }
            consumed += iTryReadAtMost;
            max -= iTryReadAtMost;
            if (!z || dst.getLimit() <= dst.getWritePosition()) {
                break;
            }
        } while (getState().capacity._availableForRead$internal > 0);
        return consumed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0054 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0055 -> B:19:0x005c). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readFullySuspend(byte[] r7, int r8, int r9, d7.d<? super x6.t0> r10) throws java.lang.Throwable {
        /*
            r6 = this;
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteBufferChannel.C19663
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.utils.io.ByteBufferChannel$readFullySuspend$3 r0 = (io.ktor.utils.io.ByteBufferChannel.C19663) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteBufferChannel$readFullySuspend$3 r0 = new io.ktor.utils.io.ByteBufferChannel$readFullySuspend$3
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3b
            if (r1 != r2) goto L33
            int r7 = r0.I$2
            int r8 = r0.I$1
            int r9 = r0.I$0
            java.lang.Object r1 = r0.L$1
            byte[] r1 = (byte[]) r1
            java.lang.Object r3 = r0.L$0
            io.ktor.utils.io.ByteBufferChannel r3 = (io.ktor.utils.io.ByteBufferChannel) r3
            k2.c.G(r10)
            goto L5c
        L33:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3b:
            k2.c.G(r10)
            r10 = 0
            r3 = r6
        L40:
            r0.L$0 = r3
            r0.L$1 = r7
            r0.I$0 = r8
            r0.I$1 = r9
            r0.I$2 = r10
            r0.label = r2
            java.lang.Object r1 = r3.readSuspend(r2, r0)
            e7.a r4 = e7.a.f15033i
            if (r1 != r4) goto L55
            return r4
        L55:
            r5 = r1
            r1 = r7
            r7 = r10
            r10 = r5
            r5 = r9
            r9 = r8
            r8 = r5
        L5c:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L74
            int r9 = r9 + r7
            int r7 = r8 - r7
            int r10 = r3.readAsMuchAsPossible(r1, r9, r7)
            if (r10 < r7) goto L70
            x6.t0 r7 = x6.t0.f22605a
            return r7
        L70:
            r8 = r9
            r9 = r7
            r7 = r1
            goto L40
        L74:
            kotlinx.coroutines.channels.ClosedReceiveChannelException r7 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
            java.lang.String r9 = "Unexpected EOF: expected "
            java.lang.String r10 = " more bytes"
            java.lang.String r8 = androidx.compose.foundation.c.o(r8, r9, r10)
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteBufferChannel.readFullySuspend(byte[], int, int, d7.d):java.lang.Object");
    }

    private final int writeAsMuchAsPossible(Buffer src) throws Throwable {
        ByteBufferChannel byteBufferChannelResolveDelegation;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (byteBufferChannelResolveDelegation = resolveDelegation(this, joiningState)) == null) {
            byteBufferChannelResolveDelegation = this;
        }
        ByteBuffer byteBuffer = byteBufferChannelResolveDelegation.setupStateForWrite$ktor_io();
        int i10 = 0;
        if (byteBuffer == null) {
            return 0;
        }
        RingBufferCapacity ringBufferCapacity = byteBufferChannelResolveDelegation.getState().capacity;
        long totalBytesWritten = byteBufferChannelResolveDelegation.get_totalBytesWritten();
        try {
            ClosedElement closed = byteBufferChannelResolveDelegation.getClosed();
            if (closed != null) {
                ByteBufferChannelKt.rethrowClosed(closed.getSendException());
                throw new KotlinNothingValueException();
            }
            while (true) {
                int iTryWriteAtMost = ringBufferCapacity.tryWriteAtMost(Math.min(src.getWritePosition() - src.getReadPosition(), byteBuffer.remaining()));
                if (iTryWriteAtMost == 0) {
                    break;
                }
                BufferUtilsJvmKt.readFully(src, byteBuffer, iTryWriteAtMost);
                i10 += iTryWriteAtMost;
                byteBufferChannelResolveDelegation.prepareBuffer(byteBuffer, byteBufferChannelResolveDelegation.carryIndex(byteBuffer, byteBufferChannelResolveDelegation.writePosition + i10), ringBufferCapacity._availableForWrite$internal);
            }
            byteBufferChannelResolveDelegation.bytesWritten(byteBuffer, ringBufferCapacity, i10);
            if (ringBufferCapacity.isFull() || byteBufferChannelResolveDelegation.getAutoFlush()) {
                byteBufferChannelResolveDelegation.flush();
            }
            if (byteBufferChannelResolveDelegation != this) {
                setTotalBytesWritten$ktor_io((byteBufferChannelResolveDelegation.get_totalBytesWritten() - totalBytesWritten) + get_totalBytesWritten());
            }
            byteBufferChannelResolveDelegation.restoreStateAfterWrite$ktor_io();
            byteBufferChannelResolveDelegation.tryTerminate$ktor_io();
            return i10;
        } catch (Throwable th) {
            if (ringBufferCapacity.isFull() || byteBufferChannelResolveDelegation.getAutoFlush()) {
                byteBufferChannelResolveDelegation.flush();
            }
            if (byteBufferChannelResolveDelegation != this) {
                setTotalBytesWritten$ktor_io((byteBufferChannelResolveDelegation.get_totalBytesWritten() - totalBytesWritten) + get_totalBytesWritten());
            }
            byteBufferChannelResolveDelegation.restoreStateAfterWrite$ktor_io();
            byteBufferChannelResolveDelegation.tryTerminate$ktor_io();
            throw th;
        }
    }

    private final int readAsMuchAsPossible(byte[] dst, int offset, int length) throws Throwable {
        ByteBuffer byteBuffer = setupStateForRead();
        int i10 = 0;
        if (byteBuffer == null) {
            return 0;
        }
        RingBufferCapacity ringBufferCapacity = getState().capacity;
        try {
            if (ringBufferCapacity._availableForRead$internal != 0) {
                int iCapacity = byteBuffer.capacity() - this.reservedSize;
                while (true) {
                    int i11 = length - i10;
                    if (i11 == 0) {
                        break;
                    }
                    int i12 = this.readPosition;
                    int iTryReadAtMost = ringBufferCapacity.tryReadAtMost(Math.min(iCapacity - i12, i11));
                    if (iTryReadAtMost == 0) {
                        break;
                    }
                    byteBuffer.limit(i12 + iTryReadAtMost);
                    byteBuffer.position(i12);
                    byteBuffer.get(dst, offset + i10, iTryReadAtMost);
                    bytesRead(byteBuffer, ringBufferCapacity, iTryReadAtMost);
                    i10 += iTryReadAtMost;
                }
            }
            return i10;
        } finally {
            restoreStateAfterRead();
            tryTerminate$ktor_io();
        }
    }

    private final int writeAsMuchAsPossible(byte[] src, int offset, int length) throws Throwable {
        ByteBufferChannel byteBufferChannelResolveDelegation;
        JoiningState joiningState = this.joining;
        if (joiningState == null || (byteBufferChannelResolveDelegation = resolveDelegation(this, joiningState)) == null) {
            byteBufferChannelResolveDelegation = this;
        }
        ByteBuffer byteBuffer = byteBufferChannelResolveDelegation.setupStateForWrite$ktor_io();
        int i10 = 0;
        if (byteBuffer == null) {
            return 0;
        }
        RingBufferCapacity ringBufferCapacity = byteBufferChannelResolveDelegation.getState().capacity;
        long totalBytesWritten = byteBufferChannelResolveDelegation.get_totalBytesWritten();
        try {
            ClosedElement closed = byteBufferChannelResolveDelegation.getClosed();
            if (closed != null) {
                ByteBufferChannelKt.rethrowClosed(closed.getSendException());
                throw new KotlinNothingValueException();
            }
            while (true) {
                int iTryWriteAtMost = ringBufferCapacity.tryWriteAtMost(Math.min(length - i10, byteBuffer.remaining()));
                if (iTryWriteAtMost == 0) {
                    byteBufferChannelResolveDelegation.bytesWritten(byteBuffer, ringBufferCapacity, i10);
                    if (ringBufferCapacity.isFull() || byteBufferChannelResolveDelegation.getAutoFlush()) {
                        byteBufferChannelResolveDelegation.flush();
                    }
                    if (byteBufferChannelResolveDelegation != this) {
                        setTotalBytesWritten$ktor_io((byteBufferChannelResolveDelegation.get_totalBytesWritten() - totalBytesWritten) + get_totalBytesWritten());
                    }
                    byteBufferChannelResolveDelegation.restoreStateAfterWrite$ktor_io();
                    byteBufferChannelResolveDelegation.tryTerminate$ktor_io();
                    return i10;
                }
                if (iTryWriteAtMost > 0) {
                    byteBuffer.put(src, offset + i10, iTryWriteAtMost);
                    i10 += iTryWriteAtMost;
                    byteBufferChannelResolveDelegation.prepareBuffer(byteBuffer, byteBufferChannelResolveDelegation.carryIndex(byteBuffer, byteBufferChannelResolveDelegation.writePosition + i10), ringBufferCapacity._availableForWrite$internal);
                } else {
                    throw new IllegalArgumentException("Failed requirement.");
                }
            }
        } catch (Throwable th) {
            if (ringBufferCapacity.isFull() || byteBufferChannelResolveDelegation.getAutoFlush()) {
                byteBufferChannelResolveDelegation.flush();
            }
            if (byteBufferChannelResolveDelegation != this) {
                setTotalBytesWritten$ktor_io((byteBufferChannelResolveDelegation.get_totalBytesWritten() - totalBytesWritten) + get_totalBytesWritten());
            }
            byteBufferChannelResolveDelegation.restoreStateAfterWrite$ktor_io();
            byteBufferChannelResolveDelegation.tryTerminate$ktor_io();
            throw th;
        }
    }
}
