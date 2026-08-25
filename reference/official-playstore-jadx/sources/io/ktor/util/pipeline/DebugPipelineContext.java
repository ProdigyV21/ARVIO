package io.ktor.util.pipeline;

import com.arflix.tv.ui.theme.AnimationConstants;
import d7.d;
import d7.j;
import f7.c;
import f7.e;
import io.ktor.util.KtorDsl;
import java.util.List;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@KtorDsl
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0003\b\u0001\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004Bi\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012H\u0010\u000b\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\n0\u0006\u0012\u0006\u0010\f\u001a\u00028\u0000\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0013\u0010\u0011\u001a\u00028\u0000H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0015\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0012J\u001b\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00028\u0000H\u0090@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u0016RV\u0010\u000b\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\n0\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\u001bR\u001a\u0010\u000e\u001a\u00020\r8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\"\u0010\f\u001a\u00028\u00008\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\f\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0016\u0010%\u001a\u00020$8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"}, d2 = {"Lio/ktor/util/pipeline/DebugPipelineContext;", "", "TSubject", "TContext", "Lio/ktor/util/pipeline/PipelineContext;", "context", "", "Lkotlin/Function3;", "Ld7/d;", "Lx6/t0;", "Lio/ktor/util/pipeline/PipelineInterceptorFunction;", "interceptors", "subject", "Ld7/j;", "coroutineContext", "<init>", "(Ljava/lang/Object;Ljava/util/List;Ljava/lang/Object;Ld7/j;)V", "proceedLoop", "(Ld7/d;)Ljava/lang/Object;", "finish", "()V", "proceedWith", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "proceed", "initial", "execute$ktor_utils", "execute", "Ljava/util/List;", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "Ljava/lang/Object;", "getSubject", "()Ljava/lang/Object;", "setSubject", "(Ljava/lang/Object;)V", "", "index", "I", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DebugPipelineContext<TSubject, TContext> extends PipelineContext<TSubject, TContext> {
    private final j coroutineContext;
    private int index;
    private final List<q<PipelineContext<TSubject, TContext>, TSubject, d<? super t0>, Object>> interceptors;
    private TSubject subject;

    /* JADX INFO: renamed from: io.ktor.util.pipeline.DebugPipelineContext$proceedLoop$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.util.pipeline.DebugPipelineContext", f = "DebugPipelineContext.kt", l = {AnimationConstants.STAGGER_SECTION}, m = "proceedLoop")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ DebugPipelineContext<TSubject, TContext> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(DebugPipelineContext<TSubject, TContext> debugPipelineContext, d<? super AnonymousClass1> dVar) {
            super(dVar);
            this.this$0 = debugPipelineContext;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.proceedLoop(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DebugPipelineContext(TContext tcontext, List<? extends q<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super d<? super t0>, ? extends Object>> list, TSubject tsubject, j jVar) {
        super(tcontext);
        this.interceptors = list;
        this.coroutineContext = jVar;
        this.subject = tsubject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object proceedLoop(d7.d<? super TSubject> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof io.ktor.util.pipeline.DebugPipelineContext.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.util.pipeline.DebugPipelineContext$proceedLoop$1 r0 = (io.ktor.util.pipeline.DebugPipelineContext.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.util.pipeline.DebugPipelineContext$proceedLoop$1 r0 = new io.ktor.util.pipeline.DebugPipelineContext$proceedLoop$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r1 = r0.L$0
            io.ktor.util.pipeline.DebugPipelineContext r1 = (io.ktor.util.pipeline.DebugPipelineContext) r1
            k2.c.G(r6)
            goto L35
        L29:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L31:
            k2.c.G(r6)
            r1 = r5
        L35:
            int r6 = r1.index
            r3 = -1
            if (r6 != r3) goto L3b
            goto L46
        L3b:
            java.util.List<r7.q<io.ktor.util.pipeline.PipelineContext<TSubject, TContext>, TSubject, d7.d<? super x6.t0>, java.lang.Object>> r3 = r1.interceptors
            int r4 = r3.size()
            if (r6 < r4) goto L4b
            r1.finish()
        L46:
            java.lang.Object r6 = r1.getSubject()
            return r6
        L4b:
            java.lang.Object r3 = r3.get(r6)
            r7.q r3 = (r7.q) r3
            int r6 = r6 + 1
            r1.index = r6
            java.lang.Object r6 = r1.getSubject()
            r0.L$0 = r1
            r0.label = r2
            java.lang.Object r6 = r3.invoke(r1, r6, r0)
            e7.a r3 = e7.a.f15033i
            if (r6 != r3) goto L35
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.pipeline.DebugPipelineContext.proceedLoop(d7.d):java.lang.Object");
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public Object execute$ktor_utils(TSubject tsubject, d<? super TSubject> dVar) {
        this.index = 0;
        setSubject(tsubject);
        return proceed(dVar);
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public void finish() {
        this.index = -1;
    }

    @Override // io.ktor.util.pipeline.PipelineContext, ka.k0
    public j getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public TSubject getSubject() {
        return this.subject;
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public Object proceed(d<? super TSubject> dVar) {
        int i10 = this.index;
        if (i10 < 0) {
            return getSubject();
        }
        if (i10 < this.interceptors.size()) {
            return proceedLoop(dVar);
        }
        finish();
        return getSubject();
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public Object proceedWith(TSubject tsubject, d<? super TSubject> dVar) {
        setSubject(tsubject);
        return proceed(dVar);
    }

    @Override // io.ktor.util.pipeline.PipelineContext
    public void setSubject(TSubject tsubject) {
        this.subject = tsubject;
    }
}
