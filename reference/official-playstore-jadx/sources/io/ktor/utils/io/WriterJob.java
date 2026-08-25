package io.ktor.utils.io;

import d7.d;
import d7.h;
import d7.i;
import d7.j;
import ga.m;
import java.util.concurrent.CancellationException;
import ka.o;
import ka.q;
import ka.v1;
import ka.z0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import sa.b;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, d2 = {"Lio/ktor/utils/io/WriterJob;", "Lka/v1;", "Lio/ktor/utils/io/ByteReadChannel;", "getChannel", "()Lio/ktor/utils/io/ByteReadChannel;", "channel", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
@e
public interface WriterJob extends v1 {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static <R> R fold(WriterJob writerJob, R r4, p<? super R, ? super j.a, ? extends R> pVar) {
            return (R) pVar.invoke(r4, writerJob);
        }

        public static <E extends j.a> E get(WriterJob writerJob, j.b<E> bVar) {
            return (E) i.a(writerJob, bVar);
        }

        public static j minusKey(WriterJob writerJob, j.b<?> bVar) {
            return i.b(writerJob, bVar);
        }

        @e
        public static v1 plus(WriterJob writerJob, v1 v1Var) {
            return v1Var;
        }

        public static j plus(WriterJob writerJob, j jVar) {
            return h.a(writerJob, jVar);
        }
    }

    @Override // ka.v1
    /* synthetic */ o attachChild(q qVar);

    @Override // ka.v1
    @e
    /* synthetic */ void cancel();

    @Override // ka.v1
    /* synthetic */ void cancel(CancellationException cancellationException);

    @Override // ka.v1
    @e
    /* synthetic */ boolean cancel(Throwable th);

    @Override // d7.j
    /* synthetic */ Object fold(Object obj, p pVar);

    @Override // d7.j
    /* synthetic */ j.a get(j.b bVar);

    @Override // ka.v1
    /* synthetic */ CancellationException getCancellationException();

    ByteReadChannel getChannel();

    @Override // ka.v1
    /* synthetic */ m getChildren();

    @Override // d7.j.a
    /* synthetic */ j.b getKey();

    @Override // ka.v1
    /* synthetic */ b getOnJoin();

    @Override // ka.v1
    /* synthetic */ v1 getParent();

    @Override // ka.v1
    /* synthetic */ z0 invokeOnCompletion(l lVar);

    @Override // ka.v1
    /* synthetic */ z0 invokeOnCompletion(boolean z, boolean z5, l lVar);

    @Override // ka.v1
    /* synthetic */ boolean isActive();

    @Override // ka.v1
    /* synthetic */ boolean isCancelled();

    @Override // ka.v1
    /* synthetic */ boolean isCompleted();

    @Override // ka.v1
    /* synthetic */ Object join(d dVar);

    @Override // d7.j
    /* synthetic */ j minusKey(j.b bVar);

    @Override // d7.j
    /* synthetic */ j plus(j jVar);

    @Override // ka.v1
    @e
    /* synthetic */ v1 plus(v1 v1Var);

    @Override // ka.v1
    /* synthetic */ boolean start();
}
