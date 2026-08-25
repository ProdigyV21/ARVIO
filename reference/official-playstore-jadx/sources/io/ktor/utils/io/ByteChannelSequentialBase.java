package io.ktor.utils.io;

import androidx.compose.runtime.ComposerKt;
import androidx.fragment.app.a2;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.android.gms.cast.MediaError;
import d7.d;
import e7.a;
import f7.c;
import f7.e;
import f7.j;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.BuffersKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.InputArraysKt;
import io.ktor.utils.io.core.InputPrimitivesKt;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.OutputKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UTF8Kt;
import io.ktor.utils.io.core.internal.UnsafeKt;
import io.ktor.utils.io.internal.AwaitingSlot;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Ü\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\b&\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006B'\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0013J\u000f\u0010\u0017\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0011H\u0004¢\u0006\u0004\b\u0019\u0010\u0018J\u001b\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001aH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010 \u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u001eH\u0096@ø\u0001\u0000¢\u0006\u0004\b \u0010!J\u001b\u0010#\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0004\b#\u0010\u0013J\u001b\u0010&\u001a\u00020\u00112\u0006\u0010%\u001a\u00020$H\u0096@ø\u0001\u0000¢\u0006\u0004\b&\u0010'J\u001b\u0010*\u001a\u00020\u00112\u0006\u0010)\u001a\u00020(H\u0096@ø\u0001\u0000¢\u0006\u0004\b*\u0010+J\u001b\u0010.\u001a\u00020\u00112\u0006\u0010-\u001a\u00020,H\u0096@ø\u0001\u0000¢\u0006\u0004\b.\u0010/J\u001b\u00102\u001a\u00020\u00112\u0006\u00101\u001a\u000200H\u0096@ø\u0001\u0000¢\u0006\u0004\b2\u00103J\u001b\u00106\u001a\u00020\u00112\u0006\u00105\u001a\u000204H\u0096@ø\u0001\u0000¢\u0006\u0004\b6\u00107J+\u00106\u001a\u00020\u00112\u0006\u00105\u001a\u0002082\u0006\u00109\u001a\u00020\u000f2\u0006\u0010:\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0004\b6\u0010;J1\u00106\u001a\u00020\u00112\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020\u000f2\u0006\u0010?\u001a\u00020\u000fH\u0096@ø\u0001\u0001ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b@\u0010AJ\u001b\u0010B\u001a\u00020\u000f2\u0006\u00105\u001a\u00020\u0007H\u0096@ø\u0001\u0000¢\u0006\u0004\bB\u0010CJ+\u0010B\u001a\u00020\u000f2\u0006\u00105\u001a\u0002082\u0006\u00109\u001a\u00020\u000f2\u0006\u0010:\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0004\bB\u0010;J<\u0010J\u001a\u00020\u00112'\u0010I\u001a#\b\u0001\u0012\u0004\u0012\u00020E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110F\u0012\u0006\u0012\u0004\u0018\u00010G0D¢\u0006\u0002\bHH\u0097@ø\u0001\u0000¢\u0006\u0004\bJ\u0010KJ\u000f\u0010L\u001a\u00020EH\u0016¢\u0006\u0004\bL\u0010MJ\u0017\u0010O\u001a\u00020\u00112\u0006\u0010N\u001a\u00020\u000fH\u0016¢\u0006\u0004\bO\u0010PJ\u0013\u0010Q\u001a\u00020\u001aH\u0096@ø\u0001\u0000¢\u0006\u0004\bQ\u0010RJ\u0013\u0010S\u001a\u00020\u001eH\u0096@ø\u0001\u0000¢\u0006\u0004\bS\u0010RJ\u0017\u0010T\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0004¢\u0006\u0004\bT\u0010PJ\u0013\u0010U\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0004\bU\u0010RJ\u0013\u0010V\u001a\u00020$H\u0096@ø\u0001\u0000¢\u0006\u0004\bV\u0010RJ\u0013\u0010W\u001a\u00020(H\u0096@ø\u0001\u0000¢\u0006\u0004\bW\u0010RJ\u0013\u0010X\u001a\u00020,H\u0096@ø\u0001\u0000¢\u0006\u0004\bX\u0010RJ\u001b\u0010Z\u001a\u0002002\u0006\u0010Y\u001a\u00020$H\u0096@ø\u0001\u0000¢\u0006\u0004\bZ\u0010'J\u001b\u0010\\\u001a\u0002002\u0006\u0010[\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0004\b\\\u0010\u0013J\u000f\u0010]\u001a\u00020\u000fH\u0004¢\u0006\u0004\b]\u0010^J\u001b\u0010`\u001a\u00020\u000f2\u0006\u0010_\u001a\u00020\u0007H\u0096@ø\u0001\u0000¢\u0006\u0004\b`\u0010CJ\u001b\u0010`\u001a\u00020\u000f2\u0006\u0010_\u001a\u000204H\u0080@ø\u0001\u0000¢\u0006\u0004\ba\u00107J#\u0010c\u001a\u00020\u00112\u0006\u0010_\u001a\u00020\u00072\u0006\u0010b\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0004\bc\u0010dJ+\u0010`\u001a\u00020\u000f2\u0006\u0010_\u001a\u0002082\u0006\u00109\u001a\u00020\u000f2\u0006\u0010:\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0004\b`\u0010;J+\u0010c\u001a\u00020\u00112\u0006\u0010_\u001a\u0002082\u0006\u00109\u001a\u00020\u000f2\u0006\u0010:\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0004\bc\u0010;J\u0013\u0010e\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0004\be\u0010RJ\u001b\u0010g\u001a\u00020\t2\u0006\u0010f\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0004\bg\u0010\u0013J\u0013\u0010i\u001a\u00020\tH\u0080@ø\u0001\u0000¢\u0006\u0004\bh\u0010RJ\u001b\u0010j\u001a\u00020\t2\u0006\u0010f\u001a\u00020\u000fH\u0084@ø\u0001\u0000¢\u0006\u0004\bj\u0010\u0013J\u0017\u0010k\u001a\u00020\u000f2\u0006\u0010b\u001a\u00020\u000fH\u0016¢\u0006\u0004\bk\u0010lJ\u0019\u0010m\u001a\u0004\u0018\u00010\u00072\u0006\u0010f\u001a\u00020\u000fH\u0016¢\u0006\u0004\bm\u0010nJ\u001b\u0010k\u001a\u00020$2\u0006\u0010o\u001a\u00020$H\u0096@ø\u0001\u0000¢\u0006\u0004\bk\u0010'J(\u0010s\u001a\u00020\u00112\u0017\u0010r\u001a\u0013\u0012\u0004\u0012\u00020q\u0012\u0004\u0012\u00020\u00110p¢\u0006\u0002\bHH\u0017¢\u0006\u0004\bs\u0010tJ\u000f\u0010u\u001a\u00020\u0004H\u0016¢\u0006\u0004\bu\u0010vJ\u000f\u0010w\u001a\u00020\u0011H\u0016¢\u0006\u0004\bw\u0010\u0018J<\u0010x\u001a\u00020\u00112'\u0010r\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110F\u0012\u0006\u0012\u0004\u0018\u00010G0D¢\u0006\u0002\bHH\u0097@ø\u0001\u0000¢\u0006\u0004\bx\u0010KJ1\u0010}\u001a\u00020\t\"\f\b\u0000\u0010{*\u00060yj\u0002`z2\u0006\u0010|\u001a\u00028\u00002\u0006\u0010Y\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0004\b}\u0010~J\u001f\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u007f2\u0006\u0010Y\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0005\b\u0080\u0001\u0010\u0013J\u001e\u0010\u0083\u0001\u001a\u00020\t2\n\u0010\u0082\u0001\u001a\u0005\u0018\u00010\u0081\u0001H\u0016¢\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001J\u001e\u0010\u0085\u0001\u001a\u00020\t2\n\u0010\u0082\u0001\u001a\u0005\u0018\u00010\u0081\u0001H\u0016¢\u0006\u0006\b\u0085\u0001\u0010\u0084\u0001J\"\u0010\u0088\u0001\u001a\u00020$2\u0006\u0010_\u001a\u00020\u00002\u0006\u0010Y\u001a\u00020$H\u0000¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J\u0019\u0010\u0089\u0001\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0004¢\u0006\u0005\b\u0089\u0001\u0010PJ\u0015\u0010\u008a\u0001\u001a\u00020\u0011H\u0096@ø\u0001\u0000¢\u0006\u0005\b\u008a\u0001\u0010RJ\u0015\u0010\u008b\u0001\u001a\u00020\u0011H\u0096@ø\u0001\u0000¢\u0006\u0005\b\u008b\u0001\u0010RJG\u0010\u0091\u0001\u001a\u00020$2\u0007\u0010\u008c\u0001\u001a\u00020<2\u0007\u0010\u008d\u0001\u001a\u00020$2\u0006\u00109\u001a\u00020$2\u0007\u0010\u008e\u0001\u001a\u00020$2\u0006\u0010o\u001a\u00020$H\u0086@ø\u0001\u0001ø\u0001\u0000ø\u0001\u0000¢\u0006\u0006\b\u008f\u0001\u0010\u0090\u0001J\u0012\u0010\u0092\u0001\u001a\u00020\tH\u0002¢\u0006\u0006\b\u0092\u0001\u0010\u0093\u0001J\u0011\u0010\u0094\u0001\u001a\u00020\u0011H\u0002¢\u0006\u0005\b\u0094\u0001\u0010\u0018J\u0011\u0010\u0095\u0001\u001a\u00020\u0011H\u0002¢\u0006\u0005\b\u0095\u0001\u0010\u0018J\u0011\u0010\u0096\u0001\u001a\u00020\u0011H\u0002¢\u0006\u0005\b\u0096\u0001\u0010\u0018J\u001c\u0010\u0096\u0001\u001a\u00020\u00112\b\u0010\u0098\u0001\u001a\u00030\u0097\u0001H\u0002¢\u0006\u0006\b\u0096\u0001\u0010\u0099\u0001J)\u0010\u009b\u0001\u001a\u00020\u00112\u0007\u0010\u009a\u0001\u001a\u00020\u000f2\f\b\u0002\u0010\u0098\u0001\u001a\u0005\u0018\u00010\u0097\u0001H\u0002¢\u0006\u0006\b\u009b\u0001\u0010\u009c\u0001J\u0015\u0010\u009d\u0001\u001a\u00020\u001aH\u0082@ø\u0001\u0000¢\u0006\u0005\b\u009d\u0001\u0010RJ\u0015\u0010\u009e\u0001\u001a\u00020\u001eH\u0082@ø\u0001\u0000¢\u0006\u0005\b\u009e\u0001\u0010RJ\u0015\u0010\u009f\u0001\u001a\u00020\u000fH\u0082@ø\u0001\u0000¢\u0006\u0005\b\u009f\u0001\u0010RJ\u0015\u0010 \u0001\u001a\u00020$H\u0082@ø\u0001\u0000¢\u0006\u0005\b \u0001\u0010RJ\u0015\u0010¡\u0001\u001a\u00020(H\u0082@ø\u0001\u0000¢\u0006\u0005\b¡\u0001\u0010RJ\u0015\u0010¢\u0001\u001a\u00020,H\u0082@ø\u0001\u0000¢\u0006\u0005\b¢\u0001\u0010RJ(\u0010¤\u0001\u001a\u0002002\b\u0010£\u0001\u001a\u00030\u0097\u00012\u0006\u0010Y\u001a\u00020$H\u0082@ø\u0001\u0000¢\u0006\u0006\b¤\u0001\u0010¥\u0001J(\u0010¦\u0001\u001a\u0002002\b\u0010£\u0001\u001a\u00030\u0097\u00012\u0006\u0010[\u001a\u00020\u000fH\u0082@ø\u0001\u0000¢\u0006\u0006\b¦\u0001\u0010§\u0001J$\u0010c\u001a\u00020\u00112\u0006\u0010_\u001a\u0002042\u0006\u0010b\u001a\u00020\u000fH\u0082@ø\u0001\u0000¢\u0006\u0005\bc\u0010¨\u0001J&\u0010©\u0001\u001a\u00020\u00112\u0006\u0010_\u001a\u0002042\u0006\u0010b\u001a\u00020\u000fH\u0082@ø\u0001\u0000¢\u0006\u0006\b©\u0001\u0010¨\u0001J-\u0010©\u0001\u001a\u00020\u00112\u0006\u0010_\u001a\u0002082\u0006\u00109\u001a\u00020\u000f2\u0006\u0010:\u001a\u00020\u000fH\u0082@ø\u0001\u0000¢\u0006\u0005\b©\u0001\u0010;J\u0015\u0010ª\u0001\u001a\u00020\tH\u0082@ø\u0001\u0000¢\u0006\u0005\bª\u0001\u0010RJ\u0011\u0010«\u0001\u001a\u00020\u0011H\u0002¢\u0006\u0005\b«\u0001\u0010\u0018J\u001b\u0010¬\u0001\u001a\u0004\u0018\u00010\u00072\u0006\u0010f\u001a\u00020\u000fH\u0002¢\u0006\u0005\b¬\u0001\u0010nJ'\u0010®\u0001\u001a\u00020$2\u0006\u0010o\u001a\u00020$2\u0007\u0010\u00ad\u0001\u001a\u00020$H\u0082@ø\u0001\u0000¢\u0006\u0006\b®\u0001\u0010¯\u0001J\u001d\u0010°\u0001\u001a\u00020\u000f2\u0006\u00105\u001a\u00020\u0007H\u0082@ø\u0001\u0000¢\u0006\u0005\b°\u0001\u0010CJ-\u0010°\u0001\u001a\u00020\u000f2\u0006\u00105\u001a\u0002082\u0006\u00109\u001a\u00020\u000f2\u0006\u0010:\u001a\u00020\u000fH\u0082@ø\u0001\u0000¢\u0006\u0005\b°\u0001\u0010;J\u0019\u0010±\u0001\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0005\b±\u0001\u0010PJ\u0019\u0010²\u0001\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0005\b²\u0001\u0010PR\u001d\u0010\n\u001a\u00020\t8\u0016X\u0096\u0004¢\u0006\u000f\n\u0005\b\n\u0010³\u0001\u001a\u0006\b´\u0001\u0010\u0093\u0001R \u0010µ\u0001\u001a\u00030\u0097\u00018\u0004X\u0084\u0004¢\u0006\u0010\n\u0006\bµ\u0001\u0010¶\u0001\u001a\u0006\b·\u0001\u0010¸\u0001R\u001f\u0010¹\u0001\u001a\u0002008\u0004X\u0084\u0004¢\u0006\u0010\n\u0006\b¹\u0001\u0010º\u0001\u001a\u0006\b»\u0001\u0010¼\u0001R\u0018\u0010¾\u0001\u001a\u00030½\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¾\u0001\u0010¿\u0001R\u001c\u0010Á\u0001\u001a\u00070Gj\u0003`À\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÁ\u0001\u0010Â\u0001R\u0018\u0010Ã\u0001\u001a\u00030\u0097\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÃ\u0001\u0010¶\u0001R*\u0010È\u0001\u001a\u00020\t2\u0007\u0010Ä\u0001\u001a\u00020\t8D@DX\u0084\u000e¢\u0006\u0010\u001a\u0006\bÅ\u0001\u0010\u0093\u0001\"\u0006\bÆ\u0001\u0010Ç\u0001R\u0016\u0010Ê\u0001\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÉ\u0001\u0010^R\u0016\u0010Ì\u0001\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bË\u0001\u0010^R\u0017\u0010Í\u0001\u001a\u00020\t8VX\u0096\u0004¢\u0006\b\u001a\u0006\bÍ\u0001\u0010\u0093\u0001R\u0017\u0010Î\u0001\u001a\u00020\t8VX\u0096\u0004¢\u0006\b\u001a\u0006\bÎ\u0001\u0010\u0093\u0001R\u0017\u0010Ñ\u0001\u001a\u00020$8VX\u0096\u0004¢\u0006\b\u001a\u0006\bÏ\u0001\u0010Ð\u0001R\u0017\u0010Ó\u0001\u001a\u00020$8VX\u0096\u0004¢\u0006\b\u001a\u0006\bÒ\u0001\u0010Ð\u0001R0\u0010Ø\u0001\u001a\u0005\u0018\u00010\u0081\u00012\n\u0010Ä\u0001\u001a\u0005\u0018\u00010\u0081\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\bÔ\u0001\u0010Õ\u0001\"\u0006\bÖ\u0001\u0010×\u0001R\u0017\u0010Ù\u0001\u001a\u00020\t8BX\u0082\u0004¢\u0006\b\u001a\u0006\bÙ\u0001\u0010\u0093\u0001\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006Ú\u0001"}, d2 = {"Lio/ktor/utils/io/ByteChannelSequentialBase;", "Lio/ktor/utils/io/ByteChannel;", "Lio/ktor/utils/io/ByteReadChannel;", "Lio/ktor/utils/io/ByteWriteChannel;", "Lio/ktor/utils/io/SuspendableReadSession;", "Lio/ktor/utils/io/HasReadSession;", "Lio/ktor/utils/io/HasWriteSession;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "initial", "", "autoFlush", "Lio/ktor/utils/io/pool/ObjectPool;", "pool", "<init>", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;ZLio/ktor/utils/io/pool/ObjectPool;)V", "", "count", "Lx6/t0;", "awaitAtLeastNBytesAvailableForWrite$ktor_io", "(ILd7/d;)Ljava/lang/Object;", "awaitAtLeastNBytesAvailableForWrite", "awaitAtLeastNBytesAvailableForRead$ktor_io", "awaitAtLeastNBytesAvailableForRead", "flush", "()V", "prepareFlushedBytes", "", "b", "writeByte", "(BLd7/d;)Ljava/lang/Object;", "", CmcdData.STREAMING_FORMAT_SS, "writeShort", "(SLd7/d;)Ljava/lang/Object;", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "writeInt", "", CmcdData.STREAM_TYPE_LIVE, "writeLong", "(JLd7/d;)Ljava/lang/Object;", "", "f", "writeFloat", "(FLd7/d;)Ljava/lang/Object;", "", "d", "writeDouble", "(DLd7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/core/ByteReadPacket;", "packet", "writePacket", "(Lio/ktor/utils/io/core/ByteReadPacket;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/core/Buffer;", "src", "writeFully", "(Lio/ktor/utils/io/core/Buffer;Ld7/d;)Ljava/lang/Object;", "", "offset", "length", "([BIILd7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/bits/Memory;", "memory", "startIndex", "endIndex", "writeFully-JT6ljtQ", "(Ljava/nio/ByteBuffer;IILd7/d;)Ljava/lang/Object;", "writeAvailable", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;Ld7/d;)Ljava/lang/Object;", "Lkotlin/Function2;", "Lio/ktor/utils/io/WriterSuspendSession;", "Ld7/d;", "", "Lx6/n;", "visitor", "writeSuspendSession", "(Lr7/p;Ld7/d;)Ljava/lang/Object;", "beginWriteSession", "()Lio/ktor/utils/io/WriterSuspendSession;", "written", "endWriteSession", "(I)V", "readByte", "(Ld7/d;)Ljava/lang/Object;", "readShort", "afterRead", "readInt", "readLong", "readFloat", "readDouble", "limit", "readRemaining", ContentDisposition.Parameters.Size, "readPacket", "readAvailableClosed", "()I", "dst", "readAvailable", "readAvailable$ktor_io", "n", "readFully", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;ILd7/d;)Ljava/lang/Object;", "readBoolean", "atLeast", "await", "awaitInternalAtLeast1$ktor_io", "awaitInternalAtLeast1", "awaitSuspend", "discard", "(I)I", "request", "(I)Lio/ktor/utils/io/core/internal/ChunkBuffer;", "max", "Lkotlin/Function1;", "Lio/ktor/utils/io/ReadSession;", "consumer", "readSession", "(Lr7/l;)V", "startReadSession", "()Lio/ktor/utils/io/SuspendableReadSession;", "endReadSession", "readSuspendableSession", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "A", "out", "readUTF8LineTo", "(Ljava/lang/Appendable;ILd7/d;)Ljava/lang/Object;", "", "readUTF8Line", "", "cause", "cancel", "(Ljava/lang/Throwable;)Z", "close", "transferTo$ktor_io", "(Lio/ktor/utils/io/ByteChannelSequentialBase;J)J", "transferTo", "afterWrite", "awaitFreeSpace", "awaitContent", "destination", "destinationOffset", "min", "peekTo-lBXzO7A", "(Ljava/nio/ByteBuffer;JJJJLd7/d;)Ljava/lang/Object;", "peekTo", "flushImpl", "()Z", "flushWrittenBytes", "ensureNotClosed", "ensureNotFailed", "Lio/ktor/utils/io/core/BytePacketBuilder;", "closeable", "(Lio/ktor/utils/io/core/BytePacketBuilder;)V", "remaining", "checkClosed", "(ILio/ktor/utils/io/core/BytePacketBuilder;)V", "readByteSlow", "readShortSlow", "readIntSlow", "readLongSlow", "readFloatSlow", "readDoubleSlow", "builder", "readRemainingSuspend", "(Lio/ktor/utils/io/core/BytePacketBuilder;JLd7/d;)Ljava/lang/Object;", "readPacketSuspend", "(Lio/ktor/utils/io/core/BytePacketBuilder;ILd7/d;)Ljava/lang/Object;", "(Lio/ktor/utils/io/core/Buffer;ILd7/d;)Ljava/lang/Object;", "readFullySuspend", "readBooleanSlow", "completeReading", "requestNextView", "discarded0", "discardSuspend", "(JJLd7/d;)Ljava/lang/Object;", "writeAvailableSuspend", "addBytesRead", "addBytesWritten", "Z", "getAutoFlush", "writable", "Lio/ktor/utils/io/core/BytePacketBuilder;", "getWritable", "()Lio/ktor/utils/io/core/BytePacketBuilder;", "readable", "Lio/ktor/utils/io/core/ByteReadPacket;", "getReadable", "()Lio/ktor/utils/io/core/ByteReadPacket;", "Lio/ktor/utils/io/internal/AwaitingSlot;", "slot", "Lio/ktor/utils/io/internal/AwaitingSlot;", "Lkotlinx/atomicfu/locks/SynchronizedObject;", "flushMutex", "Ljava/lang/Object;", "flushBuffer", "<anonymous parameter 0>", "getClosed", "setClosed", "(Z)V", "closed", "getAvailableForRead", "availableForRead", "getAvailableForWrite", "availableForWrite", "isClosedForRead", "isClosedForWrite", "getTotalBytesRead", "()J", "totalBytesRead", "getTotalBytesWritten", "totalBytesWritten", "getClosedCause", "()Ljava/lang/Throwable;", "setClosedCause", "(Ljava/lang/Throwable;)V", "closedCause", "isCancelled", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class ByteChannelSequentialBase implements ByteChannel, ByteReadChannel, ByteWriteChannel, SuspendableReadSession, HasReadSession, HasWriteSession {
    private volatile /* synthetic */ int _availableForRead;
    private volatile /* synthetic */ Object _closed;
    private volatile /* synthetic */ Object _lastReadView;
    private volatile /* synthetic */ long _totalBytesRead;
    private volatile /* synthetic */ long _totalBytesWritten;
    private final boolean autoFlush;
    private volatile /* synthetic */ int channelSize;
    private final BytePacketBuilder flushBuffer;
    private final Object flushMutex;
    private volatile /* synthetic */ int lastReadAvailable$delegate;
    private volatile /* synthetic */ Object lastReadView$delegate;
    private final ByteReadPacket readable;
    private final AwaitingSlot slot;
    private final BytePacketBuilder writable;
    private static final /* synthetic */ AtomicLongFieldUpdater _totalBytesRead$FU = AtomicLongFieldUpdater.newUpdater(ByteChannelSequentialBase.class, "_totalBytesRead");
    private static final /* synthetic */ AtomicLongFieldUpdater _totalBytesWritten$FU = AtomicLongFieldUpdater.newUpdater(ByteChannelSequentialBase.class, "_totalBytesWritten");
    private static final /* synthetic */ AtomicIntegerFieldUpdater _availableForRead$FU = AtomicIntegerFieldUpdater.newUpdater(ByteChannelSequentialBase.class, "_availableForRead");
    private static final /* synthetic */ AtomicIntegerFieldUpdater channelSize$FU = AtomicIntegerFieldUpdater.newUpdater(ByteChannelSequentialBase.class, "channelSize");
    private static final /* synthetic */ AtomicReferenceFieldUpdater _closed$FU = AtomicReferenceFieldUpdater.newUpdater(ByteChannelSequentialBase.class, Object.class, "_closed");

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$awaitFreeSpace$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {799}, m = "awaitFreeSpace$suspendImpl")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.awaitFreeSpace$suspendImpl(ByteChannelSequentialBase.this, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$awaitSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {611}, m = "awaitSuspend")
    public static final class C19941 extends c {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19941(d<? super C19941> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.this.awaitSuspend(0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$discardSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {673}, m = "discardSuspend")
    public static final class C19961 extends c {
        long J$0;
        long J$1;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19961(d<? super C19961> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.this.discardSuspend(0L, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$readAvailable$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {486}, m = "readAvailable$ktor_io")
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
            return ByteChannelSequentialBase.this.readAvailable$ktor_io(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$readAvailable$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {530}, m = "readAvailable$suspendImpl")
    public static final class AnonymousClass4 extends c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass4(d<? super AnonymousClass4> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.readAvailable$suspendImpl(ByteChannelSequentialBase.this, null, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$readBooleanSlow$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {570, 572}, m = "readBooleanSlow")
    public static final class C19971 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19971(d<? super C19971> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.this.readBooleanSlow(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$readByteSlow$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {MediaError.DetailedErrorCode.HLS_NETWORK_NO_KEY_RESPONSE}, m = "readByteSlow")
    public static final class C19981 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19981(d<? super C19981> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.this.readByteSlow(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$readDoubleSlow$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {390}, m = "readDoubleSlow")
    public static final class C19991 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C19991(d<? super C19991> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.this.readDoubleSlow(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$readFloatSlow$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {377}, m = "readFloatSlow")
    public static final class C20001 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C20001(d<? super C20001> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.this.readFloatSlow(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$readFully$6, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {544, 548}, m = "readFully$suspendImpl")
    public static final class AnonymousClass6 extends c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass6(d<? super AnonymousClass6> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.readFully$suspendImpl(ByteChannelSequentialBase.this, null, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$readFullySuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {519, 520}, m = "readFullySuspend")
    public static final class C20011 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20011(d<? super C20011> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.this.readFullySuspend(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$readFullySuspend$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {555}, m = "readFullySuspend")
    public static final class C20022 extends c {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20022(d<? super C20022> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.this.readFullySuspend(null, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$readIntSlow$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {349}, m = "readIntSlow")
    public static final class C20031 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C20031(d<? super C20031> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.this.readIntSlow(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$readLongSlow$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {364}, m = "readLongSlow")
    public static final class C20041 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C20041(d<? super C20041> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.this.readLongSlow(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$readPacketSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {459}, m = "readPacketSuspend")
    public static final class C20051 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20051(d<? super C20051> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.this.readPacketSuspend(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$readRemainingSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {425}, m = "readRemainingSuspend")
    public static final class C20061 extends c {
        long J$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20061(d<? super C20061> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.this.readRemainingSuspend(null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$readShortSlow$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {329}, m = "readShortSlow")
    public static final class C20071 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C20071(d<? super C20071> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.this.readShortSlow(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$readSuspendableSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {704}, m = "readSuspendableSession$suspendImpl")
    public static final class C20081 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C20081(d<? super C20081> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.readSuspendableSession$suspendImpl(ByteChannelSequentialBase.this, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$readUTF8Line$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {731}, m = "readUTF8Line$suspendImpl")
    public static final class C20091 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C20091(d<? super C20091> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.readUTF8Line$suspendImpl(ByteChannelSequentialBase.this, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$readUTF8LineTo$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\f\b\u0000\u0010\u0002*\u00060\u0003j\u0002`\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "Lio/ktor/utils/io/core/Input;", "A", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", ContentDisposition.Parameters.Size, ""}, k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase$readUTF8LineTo$2", f = "ByteChannelSequential.kt", l = {721}, m = "invokeSuspend")
    public static final class C20102 extends j implements p<Integer, d<? super Input>, Object> {
        /* synthetic */ int I$0;
        int label;

        public C20102(d<? super C20102> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C20102 c20102 = ByteChannelSequentialBase.this.new C20102(dVar);
            c20102.I$0 = ((Number) obj).intValue();
            return c20102;
        }

        public final Object invoke(int i10, d<? super Input> dVar) {
            return ((C20102) create(Integer.valueOf(i10), dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                int i11 = this.I$0;
                ByteChannelSequentialBase byteChannelSequentialBase = ByteChannelSequentialBase.this;
                this.label = 1;
                obj = byteChannelSequentialBase.await(i11, this);
                a aVar = a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            if (((Boolean) obj).booleanValue()) {
                return ByteChannelSequentialBase.this.getReadable();
            }
            return null;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke(((Number) obj).intValue(), (d<? super Input>) obj2);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$readUTF8LineTo$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u0005\"\f\b\u0000\u0010\u0002*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "A", "", "it", "Lx6/t0;", "invoke", "(I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements l<Integer, t0> {
        public AnonymousClass3() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).intValue());
            return t0.f22605a;
        }

        public final void invoke(int i10) {
            ByteChannelSequentialBase.this.afterRead(i10);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$writeAvailableSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {776, 777}, m = "writeAvailableSuspend")
    public static final class C20111 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20111(d<? super C20111> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.this.writeAvailableSuspend(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$writeAvailableSuspend$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {781, 782}, m = "writeAvailableSuspend")
    public static final class C20122 extends c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20122(d<? super C20122> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.this.writeAvailableSuspend(null, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$writeByte$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {150}, m = "writeByte$suspendImpl")
    public static final class C20131 extends c {
        byte B$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C20131(d<? super C20131> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.writeByte$suspendImpl(ByteChannelSequentialBase.this, (byte) 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$writeDouble$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {180}, m = "writeDouble$suspendImpl")
    public static final class C20141 extends c {
        double D$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C20141(d<? super C20141> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.writeDouble$suspendImpl(ByteChannelSequentialBase.this, 0.0d, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$writeFloat$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {174}, m = "writeFloat$suspendImpl")
    public static final class C20151 extends c {
        float F$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C20151(d<? super C20151> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.writeFloat$suspendImpl(ByteChannelSequentialBase.this, 0.0f, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$writeFully$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {193}, m = "writeFully$suspendImpl")
    public static final class C20161 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20161(d<? super C20161> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.writeFully$suspendImpl(ByteChannelSequentialBase.this, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$writeFully$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {ComposerKt.providerMapsKey}, m = "writeFully$suspendImpl")
    public static final class C20172 extends c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20172(d<? super C20172> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.writeFully$suspendImpl(ByteChannelSequentialBase.this, null, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$writeFully$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {218}, m = "writeFully-JT6ljtQ$suspendImpl")
    public static final class C20183 extends c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20183(d<? super C20183> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.m6737writeFullyJT6ljtQ$suspendImpl(ByteChannelSequentialBase.this, null, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$writeInt$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {162}, m = "writeInt$suspendImpl")
    public static final class C20191 extends c {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C20191(d<? super C20191> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.writeInt$suspendImpl(ByteChannelSequentialBase.this, 0, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$writeLong$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {168}, m = "writeLong$suspendImpl")
    public static final class C20201 extends c {
        long J$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C20201(d<? super C20201> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.writeLong$suspendImpl(ByteChannelSequentialBase.this, 0L, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$writePacket$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {186}, m = "writePacket$suspendImpl")
    public static final class C20211 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C20211(d<? super C20211> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.writePacket$suspendImpl(ByteChannelSequentialBase.this, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelSequentialBase$writeShort$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {156}, m = "writeShort$suspendImpl")
    public static final class C20221 extends c {
        Object L$0;
        short S$0;
        int label;
        /* synthetic */ Object result;

        public C20221(d<? super C20221> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelSequentialBase.writeShort$suspendImpl(ByteChannelSequentialBase.this, (short) 0, this);
        }
    }

    public ByteChannelSequentialBase(ChunkBuffer chunkBuffer, boolean z, ObjectPool<ChunkBuffer> objectPool) throws Throwable {
        this.autoFlush = z;
        ChunkBuffer.Companion companion = ChunkBuffer.INSTANCE;
        this._lastReadView = companion.getEmpty();
        this._totalBytesRead = 0L;
        this._totalBytesWritten = 0L;
        this._availableForRead = 0;
        this.channelSize = 0;
        this._closed = null;
        this.writable = new BytePacketBuilder(objectPool);
        this.readable = new ByteReadPacket(chunkBuffer, objectPool);
        this.lastReadAvailable$delegate = 0;
        this.lastReadView$delegate = companion.getEmpty();
        this.slot = new AwaitingSlot();
        this.flushMutex = new Object();
        this.flushBuffer = new BytePacketBuilder(null, 1, null);
        int iRemainingAll = (int) BuffersKt.remainingAll(chunkBuffer);
        afterWrite(iRemainingAll);
        _availableForRead$FU.addAndGet(this, iRemainingAll);
    }

    private final void addBytesRead(int count) {
        if (count < 0) {
            throw new IllegalArgumentException(a0.c.i(count, "Can't read negative amount of bytes: ").toString());
        }
        int i10 = -count;
        channelSize$FU.getAndAdd(this, i10);
        _totalBytesRead$FU.addAndGet(this, count);
        _availableForRead$FU.getAndAdd(this, i10);
        if (this.channelSize < 0) {
            throw new IllegalStateException(("Readable bytes count is negative: " + get_availableForRead() + ", " + count + " in " + this).toString());
        }
        if (get_availableForRead() >= 0) {
            return;
        }
        throw new IllegalStateException(("Readable bytes count is negative: " + get_availableForRead() + ", " + count + " in " + this).toString());
    }

    private final void addBytesWritten(int count) {
        if (count < 0) {
            throw new IllegalArgumentException(a0.c.i(count, "Can't write negative amount of bytes: ").toString());
        }
        channelSize$FU.getAndAdd(this, count);
        _totalBytesWritten$FU.addAndGet(this, count);
        if (this.channelSize >= 0) {
            return;
        }
        StringBuilder sb2 = new StringBuilder("Readable bytes count is negative: ");
        a2.y(sb2, this.channelSize, ", ", count, " in ");
        sb2.append(this);
        throw new IllegalStateException(sb2.toString().toString());
    }

    public static Object await$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, int i10, d<? super Boolean> dVar) {
        if (i10 < 0) {
            throw new IllegalArgumentException(a0.c.i(i10, "atLeast parameter shouldn't be negative: ").toString());
        }
        long j10 = i10;
        if (j10 > 4088) {
            throw new IllegalArgumentException(a0.c.i(i10, "atLeast parameter shouldn't be larger than max buffer size of 4088: ").toString());
        }
        byteChannelSequentialBase.completeReading();
        return i10 == 0 ? Boolean.valueOf(!byteChannelSequentialBase.isClosedForRead()) : byteChannelSequentialBase.readable.getRemaining() >= j10 ? Boolean.TRUE : byteChannelSequentialBase.awaitSuspend(i10, dVar);
    }

    public static /* synthetic */ Object awaitContent$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, d<? super t0> dVar) {
        Object objAwait = byteChannelSequentialBase.await(1, dVar);
        return objAwait == a.f15033i ? objAwait : t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object awaitFreeSpace$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r4, d7.d<? super x6.t0> r5) throws java.lang.Throwable {
        /*
            boolean r0 = r5 instanceof io.ktor.utils.io.ByteChannelSequentialBase.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r5
            io.ktor.utils.io.ByteChannelSequentialBase$awaitFreeSpace$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$awaitFreeSpace$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$awaitFreeSpace$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r4 = (io.ktor.utils.io.ByteChannelSequentialBase) r4
            k2.c.G(r5)
            goto L44
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            k2.c.G(r5)
            r4.flush()
            r0.L$0 = r4
            r0.label = r2
            java.lang.Object r5 = r4.awaitAtLeastNBytesAvailableForWrite$ktor_io(r2, r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L44
            return r0
        L44:
            r4.ensureNotClosed()
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.awaitFreeSpace$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, d7.d):java.lang.Object");
    }

    private final void checkClosed(int remaining, BytePacketBuilder closeable) throws Throwable {
        Throwable closedCause = getClosedCause();
        if (closedCause != null) {
            if (closeable == null) {
                throw closedCause;
            }
            closeable.close();
            throw closedCause;
        }
        if (!getClosed() || get_availableForRead() >= remaining) {
            return;
        }
        if (closeable != null) {
            closeable.close();
        }
        throw new EOFException(a2.i(remaining, " bytes required but EOF reached"));
    }

    public static /* synthetic */ void checkClosed$default(ByteChannelSequentialBase byteChannelSequentialBase, int i10, BytePacketBuilder bytePacketBuilder, int i11, Object obj) throws Throwable {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: checkClosed");
        }
        if ((i11 & 2) != 0) {
            bytePacketBuilder = null;
        }
        byteChannelSequentialBase.checkClosed(i10, bytePacketBuilder);
    }

    private final void completeReading() {
        ChunkBuffer lastReadView = getLastReadView();
        int lastReadAvailable$delegate = getLastReadAvailable$delegate() - (lastReadView.getWritePosition() - lastReadView.getReadPosition());
        if (getLastReadView() != Buffer.INSTANCE.getEmpty()) {
            UnsafeKt.completeReadHead(this.readable, getLastReadView());
        }
        if (lastReadAvailable$delegate > 0) {
            afterRead(lastReadAvailable$delegate);
        }
        setLastReadAvailable(0);
        setLastReadView(ChunkBuffer.INSTANCE.getEmpty());
    }

    public static Object discard$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, long j10, d<? super Long> dVar) throws Throwable {
        long jDiscard = byteChannelSequentialBase.readable.discard(j10);
        byteChannelSequentialBase.afterRead((int) jDiscard);
        if (jDiscard != j10 && !byteChannelSequentialBase.isClosedForRead()) {
            return byteChannelSequentialBase.discardSuspend(j10, jDiscard, dVar);
        }
        byteChannelSequentialBase.ensureNotFailed();
        return new Long(jDiscard);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006a, code lost:
    
        if (r1.isClosedForRead() == false) goto L15;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x004a -> B:18:0x004d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object discardSuspend(long r8, long r10, d7.d<? super java.lang.Long> r12) throws java.lang.Throwable {
        /*
            r7 = this;
            boolean r0 = r12 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C19961
            if (r0 == 0) goto L13
            r0 = r12
            io.ktor.utils.io.ByteChannelSequentialBase$discardSuspend$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C19961) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$discardSuspend$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$discardSuspend$1
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L38
            if (r1 != r2) goto L30
            long r8 = r0.J$1
            long r10 = r0.J$0
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r1 = (io.ktor.utils.io.ByteChannelSequentialBase) r1
            k2.c.G(r12)
            r5 = r10
            r10 = r8
            r8 = r5
            goto L4d
        L30:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L38:
            k2.c.G(r12)
            r1 = r7
        L3c:
            r0.L$0 = r1
            r0.J$0 = r8
            r0.J$1 = r10
            r0.label = r2
            java.lang.Object r12 = r1.await(r2, r0)
            e7.a r3 = e7.a.f15033i
            if (r12 != r3) goto L4d
            return r3
        L4d:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L6c
            io.ktor.utils.io.core.ByteReadPacket r12 = r1.readable
            long r3 = r8 - r10
            long r3 = r12.discard(r3)
            int r12 = (int) r3
            r1.afterRead(r12)
            long r10 = r10 + r3
            int r12 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r12 >= 0) goto L6c
            boolean r12 = r1.isClosedForRead()
            if (r12 == 0) goto L3c
        L6c:
            r1.ensureNotFailed()
            java.lang.Long r8 = new java.lang.Long
            r8.<init>(r10)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.discardSuspend(long, long, d7.d):java.lang.Object");
    }

    private final void ensureNotClosed() throws Throwable {
        if (getClosed()) {
            Throwable closedCause = getClosedCause();
            if (closedCause != null) {
                throw closedCause;
            }
            throw new ClosedWriteChannelException("Channel " + this + " is already closed");
        }
    }

    private final void ensureNotFailed() throws Throwable {
        Throwable closedCause = getClosedCause();
        if (closedCause != null) {
            throw closedCause;
        }
    }

    private final boolean flushImpl() {
        if (this.writable.isEmpty()) {
            this.slot.resume();
            return false;
        }
        flushWrittenBytes();
        this.slot.resume();
        return true;
    }

    private final void flushWrittenBytes() {
        synchronized (this.flushMutex) {
            int size = this.writable.getSize();
            this.flushBuffer.writeChunkBuffer$ktor_io(this.writable.stealAll$ktor_io());
            _availableForRead$FU.addAndGet(this, size);
        }
    }

    /* JADX INFO: renamed from: getLastReadAvailable, reason: from getter */
    private final int getLastReadAvailable$delegate() {
        return this.lastReadAvailable$delegate;
    }

    private final ChunkBuffer getLastReadView() {
        return (ChunkBuffer) this.lastReadView$delegate;
    }

    private final boolean isCancelled() {
        CloseElement closeElement = (CloseElement) this._closed;
        return (closeElement != null ? closeElement.getCause() : null) != null;
    }

    public static Object readBoolean$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, d<? super Boolean> dVar) {
        if (!byteChannelSequentialBase.readable.canRead()) {
            return byteChannelSequentialBase.readBooleanSlow(dVar);
        }
        boolean z = byteChannelSequentialBase.readable.readByte() == 1;
        byteChannelSequentialBase.afterRead(1);
        return Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readBooleanSlow(d7.d<? super java.lang.Boolean> r6) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C19971
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteChannelSequentialBase$readBooleanSlow$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C19971) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$readBooleanSlow$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readBooleanSlow$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3a
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r6)
            return r6
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L32:
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r1 = (io.ktor.utils.io.ByteChannelSequentialBase) r1
            k2.c.G(r6)
            goto L49
        L3a:
            k2.c.G(r6)
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r5.awaitSuspend(r3, r0)
            if (r6 != r4) goto L48
            goto L57
        L48:
            r1 = r5
        L49:
            r6 = 0
            checkClosed$default(r1, r3, r6, r2, r6)
            r0.L$0 = r6
            r0.label = r2
            java.lang.Object r6 = r1.readBoolean(r0)
            if (r6 != r4) goto L58
        L57:
            return r4
        L58:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readBooleanSlow(d7.d):java.lang.Object");
    }

    public static Object readByte$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, d<? super Byte> dVar) {
        if (byteChannelSequentialBase.readable.getEndOfInput()) {
            return byteChannelSequentialBase.readByteSlow(dVar);
        }
        byte b10 = byteChannelSequentialBase.readable.readByte();
        byteChannelSequentialBase.afterRead(1);
        return Byte.valueOf(b10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0041 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x003f -> B:18:0x0042). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readByteSlow(d7.d<? super java.lang.Byte> r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C19981
            if (r0 == 0) goto L13
            r0 = r5
            io.ktor.utils.io.ByteChannelSequentialBase$readByteSlow$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C19981) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$readByteSlow$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readByteSlow$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r1 = (io.ktor.utils.io.ByteChannelSequentialBase) r1
            k2.c.G(r5)
            goto L42
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L31:
            k2.c.G(r5)
            r1 = r4
        L35:
            r0.L$0 = r1
            r0.label = r2
            java.lang.Object r5 = r1.awaitSuspend(r2, r0)
            e7.a r3 = e7.a.f15033i
            if (r5 != r3) goto L42
            return r3
        L42:
            io.ktor.utils.io.core.ByteReadPacket r5 = r1.readable
            boolean r5 = r5.getEndOfInput()
            if (r5 != 0) goto L58
            io.ktor.utils.io.core.ByteReadPacket r5 = r1.readable
            byte r5 = r5.readByte()
            java.lang.Byte r5 = java.lang.Byte.valueOf(r5)
            r1.afterRead(r2)
            return r5
        L58:
            r5 = 2
            r3 = 0
            checkClosed$default(r1, r2, r3, r5, r3)
            goto L35
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readByteSlow(d7.d):java.lang.Object");
    }

    public static Object readDouble$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, d<? super Double> dVar) {
        if (!byteChannelSequentialBase.readable.hasBytes(8)) {
            return byteChannelSequentialBase.readDoubleSlow(dVar);
        }
        double d4 = InputPrimitivesKt.readDouble(byteChannelSequentialBase.readable);
        byteChannelSequentialBase.afterRead(8);
        return new Double(d4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readDoubleSlow(d7.d<? super java.lang.Double> r6) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C19991
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteChannelSequentialBase$readDoubleSlow$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C19991) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$readDoubleSlow$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readDoubleSlow$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 8
            r3 = 1
            if (r1 == 0) goto L33
            if (r1 != r3) goto L2b
            java.lang.Object r0 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r0 = (io.ktor.utils.io.ByteChannelSequentialBase) r0
            k2.c.G(r6)
            goto L44
        L2b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L33:
            k2.c.G(r6)
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r5.awaitSuspend(r2, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L43
            return r0
        L43:
            r0 = r5
        L44:
            io.ktor.utils.io.core.ByteReadPacket r6 = r0.readable
            double r3 = io.ktor.utils.io.core.InputPrimitivesKt.readDouble(r6)
            r0.afterRead(r2)
            java.lang.Double r6 = new java.lang.Double
            r6.<init>(r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readDoubleSlow(d7.d):java.lang.Object");
    }

    public static Object readFloat$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, d<? super Float> dVar) {
        if (!byteChannelSequentialBase.readable.hasBytes(4)) {
            return byteChannelSequentialBase.readFloatSlow(dVar);
        }
        float f10 = InputPrimitivesKt.readFloat(byteChannelSequentialBase.readable);
        byteChannelSequentialBase.afterRead(4);
        return new Float(f10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readFloatSlow(d7.d<? super java.lang.Float> r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20001
            if (r0 == 0) goto L13
            r0 = r5
            io.ktor.utils.io.ByteChannelSequentialBase$readFloatSlow$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20001) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$readFloatSlow$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readFloatSlow$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 4
            r3 = 1
            if (r1 == 0) goto L32
            if (r1 != r3) goto L2a
            java.lang.Object r0 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r0 = (io.ktor.utils.io.ByteChannelSequentialBase) r0
            k2.c.G(r5)
            goto L43
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L32:
            k2.c.G(r5)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r4.awaitSuspend(r2, r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L42
            return r0
        L42:
            r0 = r4
        L43:
            io.ktor.utils.io.core.ByteReadPacket r5 = r0.readable
            float r5 = io.ktor.utils.io.core.InputPrimitivesKt.readFloat(r5)
            r0.afterRead(r2)
            java.lang.Float r0 = new java.lang.Float
            r0.<init>(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readFloatSlow(d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object readFully$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, ChunkBuffer chunkBuffer, int i10, d<? super t0> dVar) throws Throwable {
        Object fully = byteChannelSequentialBase.readFully((Buffer) chunkBuffer, i10, dVar);
        return fully == a.f15033i ? fully : t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005e, code lost:
    
        if (r1.readFully(r6, r7, r0) == r4) goto L22;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readFullySuspend(io.ktor.utils.io.core.Buffer r6, int r7, d7.d<? super x6.t0> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20011
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.utils.io.ByteChannelSequentialBase$readFullySuspend$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20011) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$readFullySuspend$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readFullySuspend$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L40
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r8)
            goto L61
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            int r7 = r0.I$0
            java.lang.Object r6 = r0.L$1
            io.ktor.utils.io.core.Buffer r6 = (io.ktor.utils.io.core.Buffer) r6
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r1 = (io.ktor.utils.io.ByteChannelSequentialBase) r1
            k2.c.G(r8)
            goto L53
        L40:
            k2.c.G(r8)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.I$0 = r7
            r0.label = r3
            java.lang.Object r8 = r5.awaitSuspend(r7, r0)
            if (r8 != r4) goto L52
            goto L60
        L52:
            r1 = r5
        L53:
            r8 = 0
            r0.L$0 = r8
            r0.L$1 = r8
            r0.label = r2
            java.lang.Object r6 = r1.readFully(r6, r7, r0)
            if (r6 != r4) goto L61
        L60:
            return r4
        L61:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readFullySuspend(io.ktor.utils.io.core.Buffer, int, d7.d):java.lang.Object");
    }

    public static Object readInt$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, d<? super Integer> dVar) {
        if (!byteChannelSequentialBase.readable.hasBytes(4)) {
            return byteChannelSequentialBase.readIntSlow(dVar);
        }
        int i10 = InputPrimitivesKt.readInt(byteChannelSequentialBase.readable);
        byteChannelSequentialBase.afterRead(4);
        return new Integer(i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readIntSlow(d7.d<? super java.lang.Integer> r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20031
            if (r0 == 0) goto L13
            r0 = r5
            io.ktor.utils.io.ByteChannelSequentialBase$readIntSlow$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20031) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$readIntSlow$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readIntSlow$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 4
            r3 = 1
            if (r1 == 0) goto L32
            if (r1 != r3) goto L2a
            java.lang.Object r0 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r0 = (io.ktor.utils.io.ByteChannelSequentialBase) r0
            k2.c.G(r5)
            goto L43
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L32:
            k2.c.G(r5)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r4.awaitSuspend(r2, r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L42
            return r0
        L42:
            r0 = r4
        L43:
            io.ktor.utils.io.core.ByteReadPacket r5 = r0.readable
            int r5 = io.ktor.utils.io.core.InputPrimitivesKt.readInt(r5)
            r0.afterRead(r2)
            java.lang.Integer r0 = new java.lang.Integer
            r0.<init>(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readIntSlow(d7.d):java.lang.Object");
    }

    public static Object readLong$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, d<? super Long> dVar) {
        if (!byteChannelSequentialBase.readable.hasBytes(8)) {
            return byteChannelSequentialBase.readLongSlow(dVar);
        }
        long j10 = InputPrimitivesKt.readLong(byteChannelSequentialBase.readable);
        byteChannelSequentialBase.afterRead(8);
        return new Long(j10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readLongSlow(d7.d<? super java.lang.Long> r6) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20041
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteChannelSequentialBase$readLongSlow$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20041) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$readLongSlow$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readLongSlow$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 8
            r3 = 1
            if (r1 == 0) goto L33
            if (r1 != r3) goto L2b
            java.lang.Object r0 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r0 = (io.ktor.utils.io.ByteChannelSequentialBase) r0
            k2.c.G(r6)
            goto L44
        L2b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L33:
            k2.c.G(r6)
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r5.awaitSuspend(r2, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L43
            return r0
        L43:
            r0 = r5
        L44:
            io.ktor.utils.io.core.ByteReadPacket r6 = r0.readable
            long r3 = io.ktor.utils.io.core.InputPrimitivesKt.readLong(r6)
            r0.afterRead(r2)
            java.lang.Long r6 = new java.lang.Long
            r6.<init>(r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readLongSlow(d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object readPacket$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, int i10, d<? super ByteReadPacket> dVar) throws Throwable {
        checkClosed$default(byteChannelSequentialBase, i10, null, 2, null);
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, null);
        int iMin = (int) Math.min(i10, byteChannelSequentialBase.readable.getRemaining());
        int i11 = i10 - iMin;
        bytePacketBuilder.writePacket(byteChannelSequentialBase.readable, iMin);
        byteChannelSequentialBase.afterRead(iMin);
        byteChannelSequentialBase.checkClosed(i11, bytePacketBuilder);
        return i11 > 0 ? byteChannelSequentialBase.readPacketSuspend(bytePacketBuilder, i11, dVar) : bytePacketBuilder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readPacketSuspend(io.ktor.utils.io.core.BytePacketBuilder r9, int r10, d7.d<? super io.ktor.utils.io.core.ByteReadPacket> r11) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r11 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20051
            if (r0 == 0) goto L13
            r0 = r11
            io.ktor.utils.io.ByteChannelSequentialBase$readPacketSuspend$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20051) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$readPacketSuspend$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readPacketSuspend$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3a
            if (r1 != r2) goto L32
            int r9 = r0.I$0
            java.lang.Object r10 = r0.L$1
            io.ktor.utils.io.core.BytePacketBuilder r10 = (io.ktor.utils.io.core.BytePacketBuilder) r10
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r1 = (io.ktor.utils.io.ByteChannelSequentialBase) r1
            k2.c.G(r11)
            r7 = r10
            r10 = r9
            r9 = r7
            goto L3e
        L32:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L3a:
            k2.c.G(r11)
            r1 = r8
        L3e:
            if (r10 <= 0) goto L6b
            long r3 = (long) r10
            io.ktor.utils.io.core.ByteReadPacket r11 = r1.readable
            long r5 = r11.getRemaining()
            long r3 = java.lang.Math.min(r3, r5)
            int r11 = (int) r3
            int r10 = r10 - r11
            io.ktor.utils.io.core.ByteReadPacket r3 = r1.readable
            r9.writePacket(r3, r11)
            r1.afterRead(r11)
            r1.checkClosed(r10, r9)
            if (r10 <= 0) goto L3e
            r0.L$0 = r1
            r0.L$1 = r9
            r0.I$0 = r10
            r0.label = r2
            java.lang.Object r11 = r1.awaitSuspend(r2, r0)
            e7.a r3 = e7.a.f15033i
            if (r11 != r3) goto L3e
            return r3
        L6b:
            r1.checkClosed(r10, r9)
            io.ktor.utils.io.core.ByteReadPacket r9 = r9.build()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readPacketSuspend(io.ktor.utils.io.core.BytePacketBuilder, int, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object readRemaining$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, long j10, d<? super ByteReadPacket> dVar) throws Throwable {
        byteChannelSequentialBase.ensureNotFailed();
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, null);
        long jMin = Math.min(j10, byteChannelSequentialBase.readable.getRemaining());
        bytePacketBuilder.writePacket(byteChannelSequentialBase.readable, jMin);
        byteChannelSequentialBase.afterRead((int) jMin);
        if (j10 - ((long) bytePacketBuilder.getSize()) != 0 && !byteChannelSequentialBase.isClosedForRead()) {
            return byteChannelSequentialBase.readRemainingSuspend(bytePacketBuilder, j10, dVar);
        }
        byteChannelSequentialBase.ensureNotFailed(bytePacketBuilder);
        return bytePacketBuilder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readRemainingSuspend(io.ktor.utils.io.core.BytePacketBuilder r10, long r11, d7.d<? super io.ktor.utils.io.core.ByteReadPacket> r13) throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r13 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20061
            if (r0 == 0) goto L13
            r0 = r13
            io.ktor.utils.io.ByteChannelSequentialBase$readRemainingSuspend$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20061) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$readRemainingSuspend$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readRemainingSuspend$1
            r0.<init>(r13)
        L18:
            java.lang.Object r13 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3a
            if (r1 != r2) goto L32
            long r10 = r0.J$0
            java.lang.Object r12 = r0.L$1
            io.ktor.utils.io.core.BytePacketBuilder r12 = (io.ktor.utils.io.core.BytePacketBuilder) r12
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r1 = (io.ktor.utils.io.ByteChannelSequentialBase) r1
            k2.c.G(r13)
            r7 = r10
            r10 = r12
            r11 = r7
            goto L3e
        L32:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L3a:
            k2.c.G(r13)
            r1 = r9
        L3e:
            int r13 = r10.getSize()
            long r3 = (long) r13
            int r13 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r13 >= 0) goto L83
            int r13 = r10.getSize()
            long r3 = (long) r13
            long r3 = r11 - r3
            io.ktor.utils.io.core.ByteReadPacket r13 = r1.readable
            long r5 = r13.getRemaining()
            long r3 = java.lang.Math.min(r3, r5)
            io.ktor.utils.io.core.ByteReadPacket r13 = r1.readable
            r10.writePacket(r13, r3)
            int r13 = (int) r3
            r1.afterRead(r13)
            r1.ensureNotFailed(r10)
            boolean r13 = r1.isClosedForRead()
            if (r13 != 0) goto L83
            int r13 = r10.getSize()
            int r3 = (int) r11
            if (r13 != r3) goto L72
            goto L83
        L72:
            r0.L$0 = r1
            r0.L$1 = r10
            r0.J$0 = r11
            r0.label = r2
            java.lang.Object r13 = r1.awaitSuspend(r2, r0)
            e7.a r3 = e7.a.f15033i
            if (r13 != r3) goto L3e
            return r3
        L83:
            r1.ensureNotFailed(r10)
            io.ktor.utils.io.core.ByteReadPacket r10 = r10.build()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readRemainingSuspend(io.ktor.utils.io.core.BytePacketBuilder, long, d7.d):java.lang.Object");
    }

    public static Object readShort$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, d<? super Short> dVar) {
        if (!byteChannelSequentialBase.readable.hasBytes(2)) {
            return byteChannelSequentialBase.readShortSlow(dVar);
        }
        short s10 = InputPrimitivesKt.readShort(byteChannelSequentialBase.readable);
        byteChannelSequentialBase.afterRead(2);
        return new Short(s10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readShortSlow(d7.d<? super java.lang.Short> r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20071
            if (r0 == 0) goto L13
            r0 = r5
            io.ktor.utils.io.ByteChannelSequentialBase$readShortSlow$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20071) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$readShortSlow$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readShortSlow$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L32
            if (r1 != r3) goto L2a
            java.lang.Object r0 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r0 = (io.ktor.utils.io.ByteChannelSequentialBase) r0
            k2.c.G(r5)
            goto L43
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L32:
            k2.c.G(r5)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r4.awaitSuspend(r2, r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L42
            return r0
        L42:
            r0 = r4
        L43:
            io.ktor.utils.io.core.ByteReadPacket r5 = r0.readable
            short r5 = io.ktor.utils.io.core.InputPrimitivesKt.readShort(r5)
            r0.afterRead(r2)
            short r5 = (short) r5
            java.lang.Short r0 = new java.lang.Short
            r0.<init>(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readShortSlow(d7.d):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r4v0, types: [io.ktor.utils.io.ByteChannelSequentialBase, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v1, types: [io.ktor.utils.io.ByteChannelSequentialBase] */
    /* JADX WARN: Type inference failed for: r4v2, types: [io.ktor.utils.io.ByteChannelSequentialBase] */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.Object, x6.t0] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readSuspendableSession$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r4, r7.p<? super io.ktor.utils.io.SuspendableReadSession, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r5, d7.d<? super x6.t0> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20081
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteChannelSequentialBase$readSuspendableSession$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20081) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$readSuspendableSession$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readSuspendableSession$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r4 = (io.ktor.utils.io.ByteChannelSequentialBase) r4
            k2.c.G(r6)     // Catch: java.lang.Throwable -> L29
            goto L43
        L29:
            r5 = move-exception
            goto L49
        L2b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L33:
            k2.c.G(r6)
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L29
            r0.label = r2     // Catch: java.lang.Throwable -> L29
            java.lang.Object r5 = r5.invoke(r4, r0)     // Catch: java.lang.Throwable -> L29
            e7.a r6 = e7.a.f15033i
            if (r5 != r6) goto L43
            return r6
        L43:
            r4.completeReading()
            x6.t0 r4 = x6.t0.f22605a
            return r4
        L49:
            r4.completeReading()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readSuspendableSession$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, r7.p, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readUTF8Line$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r5, int r6, d7.d<? super java.lang.String> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20091
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.ByteChannelSequentialBase$readUTF8Line$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20091) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$readUTF8Line$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readUTF8Line$1
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
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readUTF8Line$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, int, d7.d):java.lang.Object");
    }

    public static <A extends Appendable> Object readUTF8LineTo$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, A a10, int i10, d<? super Boolean> dVar) throws Throwable {
        if (!byteChannelSequentialBase.isClosedForRead()) {
            return UTF8Kt.decodeUTF8LineLoopSuspend(a10, i10, byteChannelSequentialBase.new C20102(null), byteChannelSequentialBase.new AnonymousClass3(), dVar);
        }
        Throwable closedCause = byteChannelSequentialBase.getClosedCause();
        if (closedCause == null) {
            return Boolean.FALSE;
        }
        throw closedCause;
    }

    private final ChunkBuffer requestNextView(int atLeast) {
        if (this.readable.getEndOfInput()) {
            prepareFlushedBytes();
        }
        ChunkBuffer chunkBufferPrepareReadHead$ktor_io = this.readable.prepareReadHead$ktor_io(atLeast);
        if (chunkBufferPrepareReadHead$ktor_io == null) {
            setLastReadView(ChunkBuffer.INSTANCE.getEmpty());
            setLastReadAvailable(0);
            return chunkBufferPrepareReadHead$ktor_io;
        }
        setLastReadView(chunkBufferPrepareReadHead$ktor_io);
        setLastReadAvailable(chunkBufferPrepareReadHead$ktor_io.getWritePosition() - chunkBufferPrepareReadHead$ktor_io.getReadPosition());
        return chunkBufferPrepareReadHead$ktor_io;
    }

    private final void setLastReadAvailable(int i10) {
        this.lastReadAvailable$delegate = i10;
    }

    private final void setLastReadView(ChunkBuffer chunkBuffer) {
        this.lastReadView$delegate = chunkBuffer;
    }

    public static Object writeAvailable$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, byte[] bArr, int i10, int i11, d<? super Integer> dVar) throws Throwable {
        if (i11 == 0) {
            return new Integer(0);
        }
        int iMin = Math.min(i11, byteChannelSequentialBase.getAvailableForWrite());
        if (iMin == 0) {
            return byteChannelSequentialBase.writeAvailableSuspend(bArr, i10, i11, dVar);
        }
        OutputKt.writeFully((Output) byteChannelSequentialBase.writable, bArr, i10, iMin);
        byteChannelSequentialBase.afterWrite(iMin);
        return new Integer(iMin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeAvailableSuspend(io.ktor.utils.io.core.internal.ChunkBuffer r6, d7.d<? super java.lang.Integer> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20111
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.ByteChannelSequentialBase$writeAvailableSuspend$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20111) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$writeAvailableSuspend$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeAvailableSuspend$1
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
            io.ktor.utils.io.ByteChannelSequentialBase r1 = (io.ktor.utils.io.ByteChannelSequentialBase) r1
            k2.c.G(r7)
            goto L4f
        L3e:
            k2.c.G(r7)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r5.awaitAtLeastNBytesAvailableForWrite$ktor_io(r3, r0)
            if (r7 != r4) goto L4e
            goto L5c
        L4e:
            r1 = r5
        L4f:
            r7 = 0
            r0.L$0 = r7
            r0.L$1 = r7
            r0.label = r2
            java.lang.Object r6 = r1.writeAvailable(r6, r0)
            if (r6 != r4) goto L5d
        L5c:
            return r4
        L5d:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeAvailableSuspend(io.ktor.utils.io.core.internal.ChunkBuffer, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeByte$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r4, byte r5, d7.d<? super x6.t0> r6) throws java.lang.Throwable {
        /*
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20131
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteChannelSequentialBase$writeByte$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20131) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$writeByte$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeByte$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            byte r5 = r0.B$0
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r4 = (io.ktor.utils.io.ByteChannelSequentialBase) r4
            k2.c.G(r6)
            goto L45
        L2b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L33:
            k2.c.G(r6)
            r0.L$0 = r4
            r0.B$0 = r5
            r0.label = r2
            java.lang.Object r6 = r4.awaitAtLeastNBytesAvailableForWrite$ktor_io(r2, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L45
            return r0
        L45:
            io.ktor.utils.io.core.BytePacketBuilder r6 = r4.writable
            byte r5 = (byte) r5
            r6.writeByte(r5)
            r4.afterWrite(r2)
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeByte$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, byte, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeDouble$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r4, double r5, d7.d<? super x6.t0> r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20141
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.ByteChannelSequentialBase$writeDouble$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20141) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$writeDouble$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeDouble$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 8
            r3 = 1
            if (r1 == 0) goto L35
            if (r1 != r3) goto L2d
            double r5 = r0.D$0
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r4 = (io.ktor.utils.io.ByteChannelSequentialBase) r4
            k2.c.G(r7)
            goto L47
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            k2.c.G(r7)
            r0.L$0 = r4
            r0.D$0 = r5
            r0.label = r3
            java.lang.Object r7 = r4.awaitAtLeastNBytesAvailableForWrite$ktor_io(r2, r0)
            e7.a r0 = e7.a.f15033i
            if (r7 != r0) goto L47
            return r0
        L47:
            io.ktor.utils.io.core.BytePacketBuilder r7 = r4.writable
            io.ktor.utils.io.core.OutputPrimitivesKt.writeDouble(r7, r5)
            r4.afterWrite(r2)
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeDouble$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, double, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeFloat$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r4, float r5, d7.d<? super x6.t0> r6) throws java.lang.Throwable {
        /*
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20151
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteChannelSequentialBase$writeFloat$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20151) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$writeFloat$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeFloat$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 4
            r3 = 1
            if (r1 == 0) goto L34
            if (r1 != r3) goto L2c
            float r5 = r0.F$0
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r4 = (io.ktor.utils.io.ByteChannelSequentialBase) r4
            k2.c.G(r6)
            goto L46
        L2c:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L34:
            k2.c.G(r6)
            r0.L$0 = r4
            r0.F$0 = r5
            r0.label = r3
            java.lang.Object r6 = r4.awaitAtLeastNBytesAvailableForWrite$ktor_io(r2, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L46
            return r0
        L46:
            io.ktor.utils.io.core.BytePacketBuilder r6 = r4.writable
            io.ktor.utils.io.core.OutputPrimitivesKt.writeFloat(r6, r5)
            r4.afterWrite(r2)
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeFloat$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, float, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeFully$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r4, io.ktor.utils.io.core.Buffer r5, d7.d<? super x6.t0> r6) throws java.lang.Throwable {
        /*
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20161
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteChannelSequentialBase$writeFully$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20161) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$writeFully$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeFully$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r4 = r0.L$1
            r5 = r4
            io.ktor.utils.io.core.Buffer r5 = (io.ktor.utils.io.core.Buffer) r5
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r4 = (io.ktor.utils.io.ByteChannelSequentialBase) r4
            k2.c.G(r6)
            goto L48
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            k2.c.G(r6)
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r2
            java.lang.Object r6 = r4.awaitAtLeastNBytesAvailableForWrite$ktor_io(r2, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L48
            return r0
        L48:
            int r6 = r5.getWritePosition()
            int r0 = r5.getReadPosition()
            int r6 = r6 - r0
            io.ktor.utils.io.core.BytePacketBuilder r0 = r4.writable
            r1 = 2
            r2 = 0
            r3 = 0
            io.ktor.utils.io.core.OutputKt.writeFully$default(r0, r5, r3, r1, r2)
            r4.afterWrite(r6)
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeFully$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, io.ktor.utils.io.core.Buffer, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0053 -> B:19:0x0056). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: writeFully-JT6ljtQ$suspendImpl, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object m6737writeFullyJT6ljtQ$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r5, java.nio.ByteBuffer r6, int r7, int r8, d7.d<? super x6.t0> r9) throws java.lang.Throwable {
        /*
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20183
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.utils.io.ByteChannelSequentialBase$writeFully$3 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20183) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$writeFully$3 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeFully$3
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3e
            if (r1 != r2) goto L36
            int r5 = r0.I$1
            int r6 = r0.I$0
            java.lang.Object r7 = r0.L$1
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r8 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r8 = (io.ktor.utils.io.ByteChannelSequentialBase) r8
            k2.c.G(r9)
            r4 = r7
            r7 = r5
            r5 = r8
            r8 = r6
            r6 = r4
            goto L56
        L36:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3e:
            k2.c.G(r9)
        L41:
            if (r7 >= r8) goto L6a
            r0.L$0 = r5
            r0.L$1 = r6
            r0.I$0 = r8
            r0.I$1 = r7
            r0.label = r2
            java.lang.Object r9 = r5.awaitAtLeastNBytesAvailableForWrite$ktor_io(r2, r0)
            e7.a r1 = e7.a.f15033i
            if (r9 != r1) goto L56
            return r1
        L56:
            int r9 = r5.getAvailableForWrite()
            int r1 = r8 - r7
            int r9 = java.lang.Math.min(r9, r1)
            io.ktor.utils.io.core.BytePacketBuilder r1 = r5.writable
            io.ktor.utils.io.core.OutputKt.m6961writeFullyUAd2zVI(r1, r6, r7, r9)
            int r7 = r7 + r9
            r5.afterWrite(r9)
            goto L41
        L6a:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.m6737writeFullyJT6ljtQ$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, java.nio.ByteBuffer, int, int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeInt$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r4, int r5, d7.d<? super x6.t0> r6) throws java.lang.Throwable {
        /*
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20191
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteChannelSequentialBase$writeInt$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20191) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$writeInt$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeInt$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 4
            r3 = 1
            if (r1 == 0) goto L34
            if (r1 != r3) goto L2c
            int r5 = r0.I$0
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r4 = (io.ktor.utils.io.ByteChannelSequentialBase) r4
            k2.c.G(r6)
            goto L46
        L2c:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L34:
            k2.c.G(r6)
            r0.L$0 = r4
            r0.I$0 = r5
            r0.label = r3
            java.lang.Object r6 = r4.awaitAtLeastNBytesAvailableForWrite$ktor_io(r2, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L46
            return r0
        L46:
            io.ktor.utils.io.core.BytePacketBuilder r6 = r4.writable
            io.ktor.utils.io.core.OutputPrimitivesKt.writeInt(r6, r5)
            r4.afterWrite(r2)
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeInt$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeLong$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r4, long r5, d7.d<? super x6.t0> r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20201
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.ByteChannelSequentialBase$writeLong$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20201) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$writeLong$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeLong$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 8
            r3 = 1
            if (r1 == 0) goto L35
            if (r1 != r3) goto L2d
            long r5 = r0.J$0
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r4 = (io.ktor.utils.io.ByteChannelSequentialBase) r4
            k2.c.G(r7)
            goto L47
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            k2.c.G(r7)
            r0.L$0 = r4
            r0.J$0 = r5
            r0.label = r3
            java.lang.Object r7 = r4.awaitAtLeastNBytesAvailableForWrite$ktor_io(r2, r0)
            e7.a r0 = e7.a.f15033i
            if (r7 != r0) goto L47
            return r0
        L47:
            io.ktor.utils.io.core.BytePacketBuilder r7 = r4.writable
            io.ktor.utils.io.core.OutputPrimitivesKt.writeLong(r7, r5)
            r4.afterWrite(r2)
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeLong$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, long, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writePacket$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r4, io.ktor.utils.io.core.ByteReadPacket r5, d7.d<? super x6.t0> r6) throws java.lang.Throwable {
        /*
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20211
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteChannelSequentialBase$writePacket$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20211) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$writePacket$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writePacket$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r4 = r0.L$1
            r5 = r4
            io.ktor.utils.io.core.ByteReadPacket r5 = (io.ktor.utils.io.core.ByteReadPacket) r5
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r4 = (io.ktor.utils.io.ByteChannelSequentialBase) r4
            k2.c.G(r6)
            goto L48
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            k2.c.G(r6)
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r2
            java.lang.Object r6 = r4.awaitAtLeastNBytesAvailableForWrite$ktor_io(r2, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L48
            return r0
        L48:
            long r0 = r5.getRemaining()
            int r6 = (int) r0
            io.ktor.utils.io.core.BytePacketBuilder r0 = r4.writable
            r0.writePacket(r5)
            r4.afterWrite(r6)
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writePacket$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, io.ktor.utils.io.core.ByteReadPacket, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeShort$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r4, short r5, d7.d<? super x6.t0> r6) throws java.lang.Throwable {
        /*
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20221
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteChannelSequentialBase$writeShort$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20221) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$writeShort$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeShort$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L34
            if (r1 != r3) goto L2c
            short r5 = r0.S$0
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r4 = (io.ktor.utils.io.ByteChannelSequentialBase) r4
            k2.c.G(r6)
            goto L46
        L2c:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L34:
            k2.c.G(r6)
            r0.L$0 = r4
            r0.S$0 = r5
            r0.label = r3
            java.lang.Object r6 = r4.awaitAtLeastNBytesAvailableForWrite$ktor_io(r2, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L46
            return r0
        L46:
            io.ktor.utils.io.core.BytePacketBuilder r6 = r4.writable
            short r5 = (short) r5
            io.ktor.utils.io.core.OutputPrimitivesKt.writeShort(r6, r5)
            r4.afterWrite(r2)
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeShort$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, short, d7.d):java.lang.Object");
    }

    @x6.e
    public static /* synthetic */ Object writeSuspendSession$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, p<? super WriterSuspendSession, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) {
        Object objInvoke = pVar.invoke(byteChannelSequentialBase.beginWriteSession(), dVar);
        return objInvoke == a.f15033i ? objInvoke : t0.f22605a;
    }

    public final void afterRead(int count) {
        addBytesRead(count);
        this.slot.resume();
    }

    public final void afterWrite(int count) throws Throwable {
        addBytesWritten(count);
        if (getClosed()) {
            this.writable.release();
            ensureNotClosed();
        }
        if (getAutoFlush() || getAvailableForWrite() == 0) {
            flush();
        }
    }

    @Override // io.ktor.utils.io.SuspendableReadSession
    public Object await(int i10, d<? super Boolean> dVar) {
        return await$suspendImpl(this, i10, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object awaitAtLeastNBytesAvailableForRead$ktor_io(int r5, d7.d<? super x6.t0> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForRead$1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForRead$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForRead$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForRead$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForRead$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            int r5 = r0.I$0
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r1 = (io.ktor.utils.io.ByteChannelSequentialBase) r1
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
            int r6 = r1.get_availableForRead()
            if (r6 >= r5) goto L59
            boolean r6 = r1.isClosedForRead()
            if (r6 != 0) goto L59
            io.ktor.utils.io.internal.AwaitingSlot r6 = r1.slot
            io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForRead$2 r3 = new io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForRead$2
            r3.<init>(r1, r5)
            r0.L$0 = r1
            r0.I$0 = r5
            r0.label = r2
            java.lang.Object r6 = r6.sleep(r3, r0)
            e7.a r3 = e7.a.f15033i
            if (r6 != r3) goto L37
            return r3
        L59:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.awaitAtLeastNBytesAvailableForRead$ktor_io(int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object awaitAtLeastNBytesAvailableForWrite$ktor_io(int r5, d7.d<? super x6.t0> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForWrite$1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForWrite$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForWrite$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForWrite$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForWrite$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            int r5 = r0.I$0
            java.lang.Object r1 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r1 = (io.ktor.utils.io.ByteChannelSequentialBase) r1
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
            int r6 = r1.getAvailableForWrite()
            if (r6 >= r5) goto L5f
            boolean r6 = r1.getClosed()
            if (r6 != 0) goto L5f
            boolean r6 = r1.flushImpl()
            if (r6 != 0) goto L37
            io.ktor.utils.io.internal.AwaitingSlot r6 = r1.slot
            io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForWrite$2 r3 = new io.ktor.utils.io.ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForWrite$2
            r3.<init>(r1, r5)
            r0.L$0 = r1
            r0.I$0 = r5
            r0.label = r2
            java.lang.Object r6 = r6.sleep(r3, r0)
            e7.a r3 = e7.a.f15033i
            if (r6 != r3) goto L37
            return r3
        L5f:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.awaitAtLeastNBytesAvailableForWrite$ktor_io(int, d7.d):java.lang.Object");
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object awaitContent(d<? super t0> dVar) {
        return awaitContent$suspendImpl(this, dVar);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object awaitFreeSpace(d<? super t0> dVar) {
        return awaitFreeSpace$suspendImpl(this, dVar);
    }

    public final Object awaitInternalAtLeast1$ktor_io(d<? super Boolean> dVar) {
        return !this.readable.getEndOfInput() ? Boolean.TRUE : awaitSuspend(1, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object awaitSuspend(int r5, d7.d<? super java.lang.Boolean> r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C19941
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.utils.io.ByteChannelSequentialBase$awaitSuspend$1 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C19941) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$awaitSuspend$1 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$awaitSuspend$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            int r5 = r0.I$0
            java.lang.Object r0 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r0 = (io.ktor.utils.io.ByteChannelSequentialBase) r0
            k2.c.G(r6)
            goto L48
        L2b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L33:
            k2.c.G(r6)
            if (r5 < 0) goto L65
            r0.L$0 = r4
            r0.I$0 = r5
            r0.label = r2
            java.lang.Object r6 = r4.awaitAtLeastNBytesAvailableForRead$ktor_io(r5, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L47
            return r0
        L47:
            r0 = r4
        L48:
            r0.prepareFlushedBytes()
            java.lang.Throwable r6 = r0.getClosedCause()
            if (r6 != 0) goto L64
            boolean r6 = r0.isClosedForRead()
            if (r6 != 0) goto L5e
            int r6 = r0.get_availableForRead()
            if (r6 < r5) goto L5e
            goto L5f
        L5e:
            r2 = 0
        L5f:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)
            return r5
        L64:
            throw r6
        L65:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "Failed requirement."
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.awaitSuspend(int, d7.d):java.lang.Object");
    }

    @Override // io.ktor.utils.io.HasWriteSession
    public WriterSuspendSession beginWriteSession() {
        return new WriterSuspendSession() { // from class: io.ktor.utils.io.ByteChannelSequentialBase.beginWriteSession.1
            @Override // io.ktor.utils.io.WriterSession
            public void flush() {
                ByteChannelSequentialBase.this.flush();
            }

            @Override // io.ktor.utils.io.WriterSession
            public ChunkBuffer request(int min) {
                if (ByteChannelSequentialBase.this.getAvailableForWrite() == 0) {
                    return null;
                }
                return ByteChannelSequentialBase.this.getWritable().prepareWriteHead(min);
            }

            @Override // io.ktor.utils.io.WriterSuspendSession
            public Object tryAwait(int i10, d<? super t0> dVar) {
                Object objAwaitAtLeastNBytesAvailableForWrite$ktor_io;
                return (ByteChannelSequentialBase.this.getAvailableForWrite() >= i10 || (objAwaitAtLeastNBytesAvailableForWrite$ktor_io = ByteChannelSequentialBase.this.awaitAtLeastNBytesAvailableForWrite$ktor_io(i10, dVar)) != a.f15033i) ? t0.f22605a : objAwaitAtLeastNBytesAvailableForWrite$ktor_io;
            }

            @Override // io.ktor.utils.io.WriterSession
            public void written(int n6) throws Throwable {
                ByteChannelSequentialBase.this.getWritable().afterHeadWrite();
                ByteChannelSequentialBase.this.afterWrite(n6);
            }
        };
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public boolean cancel(Throwable cause) {
        if (getClosedCause() != null || getClosed()) {
            return false;
        }
        if (cause == null) {
            cause = new CancellationException("Channel cancelled");
        }
        return close(cause);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public boolean close(Throwable cause) {
        CloseElement closed_success = cause == null ? CloseElementKt.getCLOSED_SUCCESS() : new CloseElement(cause);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _closed$FU;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, null, closed_success)) {
            if (atomicReferenceFieldUpdater.get(this) != null) {
                return false;
            }
        }
        if (cause != null) {
            this.readable.release();
            this.writable.release();
            this.flushBuffer.release();
        } else {
            flush();
            this.writable.release();
        }
        this.slot.cancel(cause);
        return true;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object discard(long j10, d<? super Long> dVar) {
        return discard$suspendImpl(this, j10, dVar);
    }

    @Override // io.ktor.utils.io.HasReadSession
    public void endReadSession() {
        completeReading();
    }

    @Override // io.ktor.utils.io.HasWriteSession
    public void endWriteSession(int written) throws Throwable {
        this.writable.afterHeadWrite();
        afterWrite(written);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public void flush() {
        flushImpl();
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public boolean getAutoFlush() {
        return this.autoFlush;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    /* JADX INFO: renamed from: getAvailableForRead, reason: from getter */
    public int get_availableForRead() {
        return this._availableForRead;
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public int getAvailableForWrite() {
        return Math.max(0, 4088 - this.channelSize);
    }

    public final boolean getClosed() {
        return this._closed != null;
    }

    @Override // io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel
    public final Throwable getClosedCause() {
        CloseElement closeElement = (CloseElement) this._closed;
        if (closeElement != null) {
            return closeElement.getCause();
        }
        return null;
    }

    public final ByteReadPacket getReadable() {
        return this.readable;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    /* JADX INFO: renamed from: getTotalBytesRead, reason: from getter */
    public long get_totalBytesRead() {
        return this._totalBytesRead;
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    /* JADX INFO: renamed from: getTotalBytesWritten, reason: from getter */
    public long get_totalBytesWritten() {
        return this._totalBytesWritten;
    }

    public final BytePacketBuilder getWritable() {
        return this.writable;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public boolean isClosedForRead() {
        if (isCancelled()) {
            return true;
        }
        return getClosed() && this.channelSize == 0;
    }

    @Override // io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel
    public boolean isClosedForWrite() {
        return getClosed();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    @Override // io.ktor.utils.io.ByteReadChannel
    /* JADX INFO: renamed from: peekTo-lBXzO7A */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object mo6735peekTolBXzO7A(java.nio.ByteBuffer r18, long r19, long r21, long r23, long r25, d7.d<? super java.lang.Long> r27) {
        /*
            r17 = this;
            r0 = r17
            r1 = r27
            boolean r2 = r1 instanceof io.ktor.utils.io.ByteChannelSequentialBase$peekTo$1
            if (r2 == 0) goto L17
            r2 = r1
            io.ktor.utils.io.ByteChannelSequentialBase$peekTo$1 r2 = (io.ktor.utils.io.ByteChannelSequentialBase$peekTo$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L17
            int r3 = r3 - r4
            r2.label = r3
            goto L1c
        L17:
            io.ktor.utils.io.ByteChannelSequentialBase$peekTo$1 r2 = new io.ktor.utils.io.ByteChannelSequentialBase$peekTo$1
            r2.<init>(r0, r1)
        L1c:
            java.lang.Object r1 = r2.result
            int r3 = r2.label
            r4 = 1
            if (r3 == 0) goto L35
            if (r3 != r4) goto L2d
            java.lang.Object r2 = r2.L$0
            kotlin.jvm.internal.j0 r2 = (kotlin.jvm.internal.j0) r2
            k2.c.G(r1)
            goto L5c
        L2d:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L35:
            k2.c.G(r1)
            kotlin.jvm.internal.j0 r10 = new kotlin.jvm.internal.j0
            r10.<init>()
            io.ktor.utils.io.ByteChannelSequentialBase$peekTo$2 r5 = new io.ktor.utils.io.ByteChannelSequentialBase$peekTo$2
            r16 = 0
            r13 = r18
            r14 = r19
            r8 = r21
            r6 = r23
            r11 = r25
            r5.<init>(r6, r8, r10, r11, r13, r14, r16)
            r2.L$0 = r10
            r2.label = r4
            java.lang.Object r1 = r0.readSuspendableSession(r5, r2)
            e7.a r2 = e7.a.f15033i
            if (r1 != r2) goto L5b
            return r2
        L5b:
            r2 = r10
        L5c:
            long r1 = r2.f19745i
            java.lang.Long r3 = new java.lang.Long
            r3.<init>(r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.mo6735peekTolBXzO7A(java.nio.ByteBuffer, long, long, long, long, d7.d):java.lang.Object");
    }

    public final void prepareFlushedBytes() {
        synchronized (this.flushMutex) {
            UnsafeKt.unsafeAppend(this.readable, this.flushBuffer);
        }
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readAvailable(ChunkBuffer chunkBuffer, d<? super Integer> dVar) {
        return readAvailable$ktor_io(chunkBuffer, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readAvailable$ktor_io(io.ktor.utils.io.core.Buffer r6, d7.d<? super java.lang.Integer> r7) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteChannelSequentialBase.AnonymousClass2
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.utils.io.ByteChannelSequentialBase$readAvailable$2 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.AnonymousClass2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$readAvailable$2 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readAvailable$2
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            java.lang.Object r6 = r0.L$1
            io.ktor.utils.io.core.Buffer r6 = (io.ktor.utils.io.core.Buffer) r6
            java.lang.Object r0 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r0 = (io.ktor.utils.io.ByteChannelSequentialBase) r0
            k2.c.G(r7)
            goto L79
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L35:
            k2.c.G(r7)
            java.lang.Throwable r7 = r5.getClosedCause()
            if (r7 != 0) goto La7
            boolean r7 = r5.getClosed()
            if (r7 == 0) goto L51
            int r7 = r5.get_availableForRead()
            if (r7 != 0) goto L51
            java.lang.Integer r6 = new java.lang.Integer
            r7 = -1
            r6.<init>(r7)
            return r6
        L51:
            int r7 = r6.getLimit()
            int r1 = r6.getWritePosition()
            int r7 = r7 - r1
            if (r7 != 0) goto L63
            java.lang.Integer r6 = new java.lang.Integer
            r7 = 0
            r6.<init>(r7)
            return r6
        L63:
            int r7 = r5.get_availableForRead()
            if (r7 != 0) goto L78
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r2
            java.lang.Object r7 = r5.awaitSuspend(r2, r0)
            e7.a r0 = e7.a.f15033i
            if (r7 != r0) goto L78
            return r0
        L78:
            r0 = r5
        L79:
            io.ktor.utils.io.core.ByteReadPacket r7 = r0.readable
            boolean r7 = r7.canRead()
            if (r7 != 0) goto L84
            r0.prepareFlushedBytes()
        L84:
            int r7 = r6.getLimit()
            int r1 = r6.getWritePosition()
            int r7 = r7 - r1
            long r1 = (long) r7
            io.ktor.utils.io.core.ByteReadPacket r7 = r0.readable
            long r3 = r7.getRemaining()
            long r1 = java.lang.Math.min(r1, r3)
            int r7 = (int) r1
            io.ktor.utils.io.core.ByteReadPacket r1 = r0.readable
            io.ktor.utils.io.core.InputArraysKt.readFully(r1, r6, r7)
            r0.afterRead(r7)
            java.lang.Integer r6 = new java.lang.Integer
            r6.<init>(r7)
            return r6
        La7:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readAvailable$ktor_io(io.ktor.utils.io.core.Buffer, d7.d):java.lang.Object");
    }

    public final int readAvailableClosed() throws Throwable {
        Throwable closedCause = getClosedCause();
        if (closedCause != null) {
            throw closedCause;
        }
        if (get_availableForRead() <= 0) {
            return -1;
        }
        prepareFlushedBytes();
        return -1;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readBoolean(d<? super Boolean> dVar) {
        return readBoolean$suspendImpl(this, dVar);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readByte(d<? super Byte> dVar) {
        return readByte$suspendImpl(this, dVar);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readDouble(d<? super Double> dVar) {
        return readDouble$suspendImpl(this, dVar);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readFloat(d<? super Float> dVar) {
        return readFloat$suspendImpl(this, dVar);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readFully(ChunkBuffer chunkBuffer, int i10, d<? super t0> dVar) {
        return readFully$suspendImpl(this, chunkBuffer, i10, dVar);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readInt(d<? super Integer> dVar) {
        return readInt$suspendImpl(this, dVar);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readLong(d<? super Long> dVar) {
        return readLong$suspendImpl(this, dVar);
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
        try {
            consumer.invoke(this);
        } finally {
            completeReading();
        }
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readShort(d<? super Short> dVar) {
        return readShort$suspendImpl(this, dVar);
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
        return readUTF8LineTo$suspendImpl(this, a10, i10, dVar);
    }

    @Override // io.ktor.utils.io.ReadSession
    public ChunkBuffer request(int atLeast) throws Throwable {
        Throwable closedCause = getClosedCause();
        if (closedCause != null) {
            throw closedCause;
        }
        completeReading();
        return requestNextView(atLeast);
    }

    public final void setClosed(boolean z) {
        throw new IllegalStateException("Setting is not allowed for closed");
    }

    public final void setClosedCause(Throwable th) {
        throw new IllegalStateException("Closed cause shouldn't be changed directly");
    }

    @Override // io.ktor.utils.io.HasReadSession
    public SuspendableReadSession startReadSession() {
        return this;
    }

    public final long transferTo$ktor_io(ByteChannelSequentialBase dst, long limit) throws Throwable {
        long remaining = this.readable.getRemaining();
        if (remaining > limit) {
            return 0L;
        }
        dst.writable.writePacket(this.readable);
        int i10 = (int) remaining;
        dst.afterWrite(i10);
        afterRead(i10);
        return remaining;
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
    /* JADX INFO: renamed from: writeFully-JT6ljtQ */
    public Object mo6736writeFullyJT6ljtQ(ByteBuffer byteBuffer, int i10, int i11, d<? super t0> dVar) {
        return m6737writeFullyJT6ljtQ$suspendImpl(this, byteBuffer, i10, i11, dVar);
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

    private final void ensureNotFailed(BytePacketBuilder closeable) throws Throwable {
        Throwable closedCause = getClosedCause();
        if (closedCause == null) {
            return;
        }
        closeable.release();
        throw closedCause;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object readAvailable$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r4, byte[] r5, int r6, int r7, d7.d<? super java.lang.Integer> r8) throws java.lang.Throwable {
        /*
            boolean r0 = r8 instanceof io.ktor.utils.io.ByteChannelSequentialBase.AnonymousClass4
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.utils.io.ByteChannelSequentialBase$readAvailable$4 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.AnonymousClass4) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$readAvailable$4 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readAvailable$4
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3a
            if (r1 != r2) goto L32
            int r7 = r0.I$1
            int r6 = r0.I$0
            java.lang.Object r4 = r0.L$1
            r5 = r4
            byte[] r5 = (byte[]) r5
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r4 = (io.ktor.utils.io.ByteChannelSequentialBase) r4
            k2.c.G(r8)
            goto L78
        L32:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3a:
            k2.c.G(r8)
            java.lang.Throwable r8 = r4.getClosedCause()
            if (r8 != 0) goto L9d
            boolean r8 = r4.getClosed()
            if (r8 == 0) goto L56
            int r8 = r4.get_availableForRead()
            if (r8 != 0) goto L56
            java.lang.Integer r4 = new java.lang.Integer
            r5 = -1
            r4.<init>(r5)
            return r4
        L56:
            if (r7 != 0) goto L5f
            java.lang.Integer r4 = new java.lang.Integer
            r5 = 0
            r4.<init>(r5)
            return r4
        L5f:
            int r8 = r4.get_availableForRead()
            if (r8 != 0) goto L78
            r0.L$0 = r4
            r0.L$1 = r5
            r0.I$0 = r6
            r0.I$1 = r7
            r0.label = r2
            java.lang.Object r8 = r4.awaitSuspend(r2, r0)
            e7.a r0 = e7.a.f15033i
            if (r8 != r0) goto L78
            return r0
        L78:
            io.ktor.utils.io.core.ByteReadPacket r8 = r4.readable
            boolean r8 = r8.canRead()
            if (r8 != 0) goto L83
            r4.prepareFlushedBytes()
        L83:
            long r7 = (long) r7
            io.ktor.utils.io.core.ByteReadPacket r0 = r4.readable
            long r0 = r0.getRemaining()
            long r7 = java.lang.Math.min(r7, r0)
            int r7 = (int) r7
            io.ktor.utils.io.core.ByteReadPacket r8 = r4.readable
            io.ktor.utils.io.core.InputArraysKt.readFully(r8, r5, r6, r7)
            r4.afterRead(r7)
            java.lang.Integer r4 = new java.lang.Integer
            r4.<init>(r7)
            return r4
        L9d:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readAvailable$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, byte[], int, int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object readFully$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r6, byte[] r7, int r8, int r9, d7.d<? super x6.t0> r10) throws java.io.EOFException {
        /*
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteChannelSequentialBase.AnonymousClass6
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.utils.io.ByteChannelSequentialBase$readFully$6 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.AnonymousClass6) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$readFully$6 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readFully$6
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            x6.t0 r2 = x6.t0.f22605a
            r3 = 2
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L45
            if (r1 == r4) goto L34
            if (r1 != r3) goto L2c
            k2.c.G(r10)
            goto L75
        L2c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L34:
            int r9 = r0.I$1
            int r8 = r0.I$0
            java.lang.Object r6 = r0.L$1
            r7 = r6
            byte[] r7 = (byte[]) r7
            java.lang.Object r6 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r6 = (io.ktor.utils.io.ByteChannelSequentialBase) r6
            k2.c.G(r10)
            goto L59
        L45:
            k2.c.G(r10)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.I$0 = r8
            r0.I$1 = r9
            r0.label = r4
            java.lang.Object r10 = r6.readAvailable(r7, r8, r9, r0)
            if (r10 != r5) goto L59
            goto L74
        L59:
            java.lang.Number r10 = (java.lang.Number) r10
            int r10 = r10.intValue()
            if (r10 != r9) goto L62
            return r2
        L62:
            r1 = -1
            if (r10 == r1) goto L76
            int r8 = r8 + r10
            int r9 = r9 - r10
            r10 = 0
            r0.L$0 = r10
            r0.L$1 = r10
            r0.label = r3
            java.lang.Object r6 = r6.readFullySuspend(r7, r8, r9, r0)
            if (r6 != r5) goto L75
        L74:
            return r5
        L75:
            return r2
        L76:
            java.io.EOFException r6 = new java.io.EOFException
            java.lang.String r7 = "Unexpected end of stream"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readFully$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, byte[], int, int, d7.d):java.lang.Object");
    }

    @Override // io.ktor.utils.io.ReadSession
    public int discard(int n6) throws Throwable {
        Throwable closedCause = getClosedCause();
        if (closedCause != null) {
            throw closedCause;
        }
        if (n6 == 0) {
            return 0;
        }
        int iDiscard = this.readable.discard(n6);
        afterRead(n6);
        requestNextView(1);
        return iDiscard;
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readAvailable(byte[] bArr, int i10, int i11, d<? super Integer> dVar) {
        return readAvailable$suspendImpl(this, bArr, i10, i11, dVar);
    }

    @Override // io.ktor.utils.io.ByteReadChannel
    public Object readFully(byte[] bArr, int i10, int i11, d<? super t0> dVar) {
        return readFully$suspendImpl(this, bArr, i10, i11, dVar);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeAvailable(byte[] bArr, int i10, int i11, d<? super Integer> dVar) {
        return writeAvailable$suspendImpl(this, bArr, i10, i11, dVar);
    }

    @Override // io.ktor.utils.io.ByteWriteChannel
    public Object writeFully(byte[] bArr, int i10, int i11, d<? super t0> dVar) {
        return writeFully$suspendImpl(this, bArr, i10, i11, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object readFully(Buffer buffer, int i10, d<? super t0> dVar) throws Throwable {
        if (i10 > buffer.getLimit() - buffer.getWritePosition()) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Not enough space in the destination buffer to write ", " bytes").toString());
        }
        if (i10 >= 0) {
            if (getClosedCause() == null) {
                long remaining = this.readable.getRemaining();
                long j10 = i10;
                t0 t0Var = t0.f22605a;
                if (remaining >= j10) {
                    InputArraysKt.readFully(this.readable, buffer, i10);
                    afterRead(i10);
                    return t0Var;
                }
                if (!getClosed()) {
                    Object fullySuspend = readFullySuspend(buffer, i10, dVar);
                    return fullySuspend == a.f15033i ? fullySuspend : t0Var;
                }
                StringBuilder sbS = a0.c.s(i10, "Channel is closed and not enough bytes available: required ", " but ");
                sbS.append(get_availableForRead());
                sbS.append(" available");
                throw new EOFException(sbS.toString());
            }
            throw getClosedCause();
        }
        throw new IllegalArgumentException("n shouldn't be negative");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0064 -> B:12:0x0035). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readFullySuspend(byte[] r7, int r8, int r9, d7.d<? super x6.t0> r10) throws java.io.EOFException {
        /*
            r6 = this;
            boolean r0 = r10 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20022
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.utils.io.ByteChannelSequentialBase$readFullySuspend$2 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20022) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$readFullySuspend$2 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$readFullySuspend$2
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3f
            if (r1 != r2) goto L37
            int r7 = r0.I$2
            int r8 = r0.I$1
            int r9 = r0.I$0
            java.lang.Object r1 = r0.L$1
            byte[] r1 = (byte[]) r1
            java.lang.Object r3 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r3 = (io.ktor.utils.io.ByteChannelSequentialBase) r3
            k2.c.G(r10)
            r5 = r0
            r0 = r8
            r8 = r1
        L35:
            r1 = r5
            goto L68
        L37:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3f:
            k2.c.G(r10)
            r10 = 0
            r3 = r8
            r8 = r7
            r7 = r10
            r10 = r9
            r9 = r3
            r3 = r6
        L49:
            if (r7 >= r10) goto L7d
            int r1 = r9 + r7
            int r4 = r10 - r7
            r0.L$0 = r3
            r0.L$1 = r8
            r0.I$0 = r9
            r0.I$1 = r10
            r0.I$2 = r7
            r0.label = r2
            java.lang.Object r1 = r3.readAvailable(r8, r1, r4, r0)
            e7.a r4 = e7.a.f15033i
            if (r1 != r4) goto L64
            return r4
        L64:
            r5 = r0
            r0 = r10
            r10 = r1
            goto L35
        L68:
            java.lang.Number r10 = (java.lang.Number) r10
            int r10 = r10.intValue()
            r4 = -1
            if (r10 == r4) goto L75
            int r7 = r7 + r10
            r10 = r0
            r0 = r1
            goto L49
        L75:
            java.io.EOFException r7 = new java.io.EOFException
            java.lang.String r8 = "Unexpected end of stream"
            r7.<init>(r8)
            throw r7
        L7d:
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.readFullySuspend(byte[], int, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object writeAvailableSuspend(byte[] r6, int r7, int r8, d7.d<? super java.lang.Integer> r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20122
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.utils.io.ByteChannelSequentialBase$writeAvailableSuspend$2 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20122) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$writeAvailableSuspend$2 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeAvailableSuspend$2
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
            io.ktor.utils.io.ByteChannelSequentialBase r1 = (io.ktor.utils.io.ByteChannelSequentialBase) r1
            k2.c.G(r9)
            goto L57
        L42:
            k2.c.G(r9)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.I$0 = r7
            r0.I$1 = r8
            r0.label = r3
            java.lang.Object r9 = r5.awaitAtLeastNBytesAvailableForWrite$ktor_io(r3, r0)
            if (r9 != r4) goto L56
            goto L64
        L56:
            r1 = r5
        L57:
            r9 = 0
            r0.L$0 = r9
            r0.L$1 = r9
            r0.label = r2
            java.lang.Object r6 = r1.writeAvailable(r6, r7, r8, r0)
            if (r6 != r4) goto L65
        L64:
            return r4
        L65:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeAvailableSuspend(byte[], int, int, d7.d):java.lang.Object");
    }

    public static Object writeAvailable$suspendImpl(ByteChannelSequentialBase byteChannelSequentialBase, ChunkBuffer chunkBuffer, d<? super Integer> dVar) throws Throwable {
        int writePosition = chunkBuffer.getWritePosition() - chunkBuffer.getReadPosition();
        if (writePosition == 0) {
            return new Integer(0);
        }
        int iMin = Math.min(writePosition, byteChannelSequentialBase.getAvailableForWrite());
        if (iMin == 0) {
            return byteChannelSequentialBase.writeAvailableSuspend(chunkBuffer, dVar);
        }
        OutputKt.writeFully(byteChannelSequentialBase.writable, chunkBuffer, iMin);
        byteChannelSequentialBase.afterWrite(iMin);
        return new Integer(iMin);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0057 -> B:19:0x005a). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object writeFully$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase r5, byte[] r6, int r7, int r8, d7.d<? super x6.t0> r9) throws java.lang.Throwable {
        /*
            boolean r0 = r9 instanceof io.ktor.utils.io.ByteChannelSequentialBase.C20172
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.utils.io.ByteChannelSequentialBase$writeFully$2 r0 = (io.ktor.utils.io.ByteChannelSequentialBase.C20172) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.utils.io.ByteChannelSequentialBase$writeFully$2 r0 = new io.ktor.utils.io.ByteChannelSequentialBase$writeFully$2
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3c
            if (r1 != r2) goto L34
            int r5 = r0.I$1
            int r6 = r0.I$0
            java.lang.Object r7 = r0.L$1
            byte[] r7 = (byte[]) r7
            java.lang.Object r8 = r0.L$0
            io.ktor.utils.io.ByteChannelSequentialBase r8 = (io.ktor.utils.io.ByteChannelSequentialBase) r8
            k2.c.G(r9)
            r4 = r8
            r8 = r6
            r6 = r4
            goto L5a
        L34:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3c:
            k2.c.G(r9)
            int r8 = r8 + r7
            r4 = r6
            r6 = r5
            r5 = r8
            r8 = r7
            r7 = r4
        L45:
            if (r8 >= r5) goto L6e
            r0.L$0 = r6
            r0.L$1 = r7
            r0.I$0 = r8
            r0.I$1 = r5
            r0.label = r2
            java.lang.Object r9 = r6.awaitAtLeastNBytesAvailableForWrite$ktor_io(r2, r0)
            e7.a r1 = e7.a.f15033i
            if (r9 != r1) goto L5a
            return r1
        L5a:
            int r9 = r6.getAvailableForWrite()
            int r1 = r5 - r8
            int r9 = java.lang.Math.min(r9, r1)
            io.ktor.utils.io.core.BytePacketBuilder r1 = r6.writable
            io.ktor.utils.io.core.OutputKt.writeFully(r1, r7, r8, r9)
            int r8 = r8 + r9
            r6.afterWrite(r9)
            goto L45
        L6e:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelSequentialBase.writeFully$suspendImpl(io.ktor.utils.io.ByteChannelSequentialBase, byte[], int, int, d7.d):java.lang.Object");
    }

    public /* synthetic */ ByteChannelSequentialBase(ChunkBuffer chunkBuffer, boolean z, ObjectPool objectPool, int i10, h hVar) {
        this(chunkBuffer, z, (i10 & 4) != 0 ? ChunkBuffer.INSTANCE.getPool() : objectPool);
    }
}
