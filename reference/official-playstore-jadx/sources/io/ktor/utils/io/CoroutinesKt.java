package io.ktor.utils.io;

import d7.d;
import d7.f;
import d7.j;
import d7.k;
import ka.c0;
import ka.f0;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.o2;
import ka.v1;
import ka.x0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aQ\u0010\r\u001a\u00020\f*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\u0002\b\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001aQ\u0010\r\u001a\u00020\f*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u000f2'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\u0002\b\nø\u0001\u0000¢\u0006\u0004\b\r\u0010\u0011\u001aW\u0010\r\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\u0002\b\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u0014\u001aY\u0010\r\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\u0002\b\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u0015\u001aQ\u0010\u0018\u001a\u00020\u0017*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\u0002\b\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001aQ\u0010\u0018\u001a\u00020\u0017*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u000f2'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\u0002\b\nø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u001a\u001aW\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\u0002\b\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u001b\u001aY\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\u0002\b\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u001c\u001aa\u0010!\u001a\u00020 \"\b\b\u0000\u0010\u001d*\u00020\u0000*\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u000f2'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\u0002\b\nH\u0002ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"Lka/k0;", "Ld7/j;", "coroutineContext", "Lio/ktor/utils/io/ByteChannel;", "channel", "Lkotlin/Function2;", "Lio/ktor/utils/io/ReaderScope;", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "block", "Lio/ktor/utils/io/ReaderJob;", "reader", "(Lka/k0;Ld7/j;Lio/ktor/utils/io/ByteChannel;Lr7/p;)Lio/ktor/utils/io/ReaderJob;", "", "autoFlush", "(Lka/k0;Ld7/j;ZLr7/p;)Lio/ktor/utils/io/ReaderJob;", "Lka/v1;", "parent", "(Ld7/j;Lio/ktor/utils/io/ByteChannel;Lka/v1;Lr7/p;)Lio/ktor/utils/io/ReaderJob;", "(Ld7/j;ZLka/v1;Lr7/p;)Lio/ktor/utils/io/ReaderJob;", "Lio/ktor/utils/io/WriterScope;", "Lio/ktor/utils/io/WriterJob;", "writer", "(Lka/k0;Ld7/j;Lio/ktor/utils/io/ByteChannel;Lr7/p;)Lio/ktor/utils/io/WriterJob;", "(Lka/k0;Ld7/j;ZLr7/p;)Lio/ktor/utils/io/WriterJob;", "(Ld7/j;Lio/ktor/utils/io/ByteChannel;Lka/v1;Lr7/p;)Lio/ktor/utils/io/WriterJob;", "(Ld7/j;ZLka/v1;Lr7/p;)Lio/ktor/utils/io/WriterJob;", "S", "context", "attachJob", "Lio/ktor/utils/io/ChannelJob;", "launchChannel", "(Lka/k0;Ld7/j;Lio/ktor/utils/io/ByteChannel;ZLr7/p;)Lio/ktor/utils/io/ChannelJob;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CoroutinesKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.CoroutinesKt$launchChannel$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\b\b\u0000\u0010\u0001*\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lka/k0;", "S", "", "cause", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Throwable, t0> {
        final /* synthetic */ ByteChannel $channel;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ByteChannel byteChannel) {
            super(1);
            this.$channel = byteChannel;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public final void invoke(Throwable th) {
            this.$channel.close(th);
        }
    }

    private static final <S extends k0> ChannelJob launchChannel(k0 k0Var, j jVar, ByteChannel byteChannel, boolean z, p<? super S, ? super d<? super t0>, ? extends Object> pVar) {
        o2 o2VarP = m0.p(k0Var, jVar, 0, new CoroutinesKt$launchChannel$job$1(z, byteChannel, pVar, (f0) k0Var.getCoroutineContext().get(f0.Key), null), 2);
        o2VarP.invokeOnCompletion(new AnonymousClass1(byteChannel));
        return new ChannelJob(o2VarP, byteChannel);
    }

    @e
    public static final ReaderJob reader(k0 k0Var, j jVar, ByteChannel byteChannel, p<? super ReaderScope, ? super d<? super t0>, ? extends Object> pVar) {
        return launchChannel(k0Var, jVar, byteChannel, false, pVar);
    }

    public static /* synthetic */ ReaderJob reader$default(k0 k0Var, j jVar, ByteChannel byteChannel, p pVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            jVar = k.f14688i;
        }
        return reader(k0Var, jVar, byteChannel, (p<? super ReaderScope, ? super d<? super t0>, ? extends Object>) pVar);
    }

    @e
    public static final WriterJob writer(k0 k0Var, j jVar, ByteChannel byteChannel, p<? super WriterScope, ? super d<? super t0>, ? extends Object> pVar) {
        return launchChannel(k0Var, jVar, byteChannel, false, pVar);
    }

    public static /* synthetic */ WriterJob writer$default(k0 k0Var, j jVar, ByteChannel byteChannel, p pVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            jVar = k.f14688i;
        }
        return writer(k0Var, jVar, byteChannel, (p<? super WriterScope, ? super d<? super t0>, ? extends Object>) pVar);
    }

    public static final ReaderJob reader(k0 k0Var, j jVar, boolean z, p<? super ReaderScope, ? super d<? super t0>, ? extends Object> pVar) {
        return launchChannel(k0Var, jVar, ByteChannelKt.ByteChannel(z), true, pVar);
    }

    public static final WriterJob writer(k0 k0Var, j jVar, boolean z, p<? super WriterScope, ? super d<? super t0>, ? extends Object> pVar) {
        return launchChannel(k0Var, jVar, ByteChannelKt.ByteChannel(z), true, pVar);
    }

    @e
    public static final ReaderJob reader(j jVar, ByteChannel byteChannel, v1 v1Var, p<? super ReaderScope, ? super d<? super t0>, ? extends Object> pVar) {
        j jVarA;
        f fVar = f.f14687i;
        k kVar = k.f14688i;
        if (v1Var != null) {
            jVarA = c0.a(kVar, jVar.plus(v1Var), true);
            ra.d dVar = x0.f19653b;
            if (jVarA != dVar && jVarA.get(fVar) == null) {
                jVarA = jVarA.plus(dVar);
            }
        } else {
            jVarA = c0.a(kVar, jVar, true);
            ra.d dVar2 = x0.f19653b;
            if (jVarA != dVar2 && jVarA.get(fVar) == null) {
                jVarA = jVarA.plus(dVar2);
            }
        }
        return reader(l0.a(jVarA), kVar, byteChannel, pVar);
    }

    public static /* synthetic */ ReaderJob reader$default(k0 k0Var, j jVar, boolean z, p pVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            jVar = k.f14688i;
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        return reader(k0Var, jVar, z, (p<? super ReaderScope, ? super d<? super t0>, ? extends Object>) pVar);
    }

    @e
    public static final WriterJob writer(j jVar, ByteChannel byteChannel, v1 v1Var, p<? super WriterScope, ? super d<? super t0>, ? extends Object> pVar) {
        j jVarA;
        f fVar = f.f14687i;
        k kVar = k.f14688i;
        if (v1Var != null) {
            jVarA = c0.a(kVar, jVar.plus(v1Var), true);
            ra.d dVar = x0.f19653b;
            if (jVarA != dVar && jVarA.get(fVar) == null) {
                jVarA = jVarA.plus(dVar);
            }
        } else {
            jVarA = c0.a(kVar, jVar, true);
            ra.d dVar2 = x0.f19653b;
            if (jVarA != dVar2 && jVarA.get(fVar) == null) {
                jVarA = jVarA.plus(dVar2);
            }
        }
        return writer(l0.a(jVarA), kVar, byteChannel, pVar);
    }

    public static /* synthetic */ WriterJob writer$default(k0 k0Var, j jVar, boolean z, p pVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            jVar = k.f14688i;
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        return writer(k0Var, jVar, z, (p<? super WriterScope, ? super d<? super t0>, ? extends Object>) pVar);
    }

    public static /* synthetic */ ReaderJob reader$default(j jVar, ByteChannel byteChannel, v1 v1Var, p pVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            v1Var = null;
        }
        return reader(jVar, byteChannel, v1Var, (p<? super ReaderScope, ? super d<? super t0>, ? extends Object>) pVar);
    }

    public static /* synthetic */ WriterJob writer$default(j jVar, ByteChannel byteChannel, v1 v1Var, p pVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            v1Var = null;
        }
        return writer(jVar, byteChannel, v1Var, (p<? super WriterScope, ? super d<? super t0>, ? extends Object>) pVar);
    }

    public static /* synthetic */ ReaderJob reader$default(j jVar, boolean z, v1 v1Var, p pVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        if ((i10 & 4) != 0) {
            v1Var = null;
        }
        return reader(jVar, z, v1Var, (p<? super ReaderScope, ? super d<? super t0>, ? extends Object>) pVar);
    }

    public static /* synthetic */ WriterJob writer$default(j jVar, boolean z, v1 v1Var, p pVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        if ((i10 & 4) != 0) {
            v1Var = null;
        }
        return writer(jVar, z, v1Var, (p<? super WriterScope, ? super d<? super t0>, ? extends Object>) pVar);
    }

    @e
    public static final ReaderJob reader(j jVar, boolean z, v1 v1Var, p<? super ReaderScope, ? super d<? super t0>, ? extends Object> pVar) {
        ByteChannel ByteChannel = ByteChannelKt.ByteChannel(z);
        ReaderJob erVar = reader(jVar, ByteChannel, v1Var, pVar);
        ByteChannel.attachJob(erVar);
        return erVar;
    }

    @e
    public static final WriterJob writer(j jVar, boolean z, v1 v1Var, p<? super WriterScope, ? super d<? super t0>, ? extends Object> pVar) {
        ByteChannel ByteChannel = ByteChannelKt.ByteChannel(z);
        WriterJob writerJobWriter = writer(jVar, ByteChannel, v1Var, pVar);
        ByteChannel.attachJob(writerJobWriter);
        return writerJobWriter;
    }
}
