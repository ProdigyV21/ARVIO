package io.ktor.util.pipeline;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.util.debug.ContextUtilsKt;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a5\u0010\u0005\u001a\u00020\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0004\u001a\u00028\u0000H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a|\u0010\u000f\u001a\u00020\u0003\"\n\b\u0000\u0010\u0007\u0018\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0001*\u00020\u0000*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u0010\t\u001a\u00020\b2;\b\b\u0010\u000e\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000b\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\f\u0012\u0006\u0012\u0004\u0018\u00010\u00000\n¢\u0006\u0002\b\rH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010*|\u0010\u0011\u001a\u0004\b\u0000\u0010\u0007\u001a\u0004\b\u0001\u0010\u0001\"5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000b\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\f\u0012\u0006\u0012\u0004\u0018\u00010\u00000\n¢\u0006\u0002\b\r25\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000b\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\f\u0012\u0006\u0012\u0004\u0018\u00010\u00000\n¢\u0006\u0002\b\r*p\b\u0000\u0010\u0012\u001a\u0004\b\u0000\u0010\u0007\u001a\u0004\b\u0001\u0010\u0001\".\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000b\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\f\u0012\u0006\u0012\u0004\u0018\u00010\u00000\n2.\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000b\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\f\u0012\u0006\u0012\u0004\u0018\u00010\u00000\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"", "TContext", "Lio/ktor/util/pipeline/Pipeline;", "Lx6/t0;", "context", "execute", "(Lio/ktor/util/pipeline/Pipeline;Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "TSubject", "Lio/ktor/util/pipeline/PipelinePhase;", "phase", "Lkotlin/Function3;", "Lio/ktor/util/pipeline/PipelineContext;", "Ld7/d;", "Lx6/n;", "block", "intercept", "(Lio/ktor/util/pipeline/Pipeline;Lio/ktor/util/pipeline/PipelinePhase;Lr7/q;)V", "PipelineInterceptor", "PipelineInterceptorFunction", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PipelineKt {

    /* JADX INFO: renamed from: io.ktor.util.pipeline.PipelineKt$execute$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002\"\b\b\u0000\u0010\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "TContext", "Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.util.pipeline.PipelineKt$execute$2", f = "Pipeline.kt", l = {478}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements l<d<? super t0>, Object> {
        final /* synthetic */ TContext $context;
        final /* synthetic */ Pipeline<t0, TContext> $this_execute;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Pipeline<t0, TContext> pipeline, TContext tcontext, d<? super AnonymousClass2> dVar) {
            super(1, dVar);
            this.$this_execute = pipeline;
            this.$context = tcontext;
        }

        @Override // f7.a
        public final d<t0> create(d<?> dVar) {
            return new AnonymousClass2(this.$this_execute, this.$context, dVar);
        }

        @Override // r7.l
        public final Object invoke(d<? super t0> dVar) {
            return ((AnonymousClass2) create(dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            t0 t0Var = t0.f22605a;
            if (i10 == 0) {
                c.G(obj);
                Pipeline<t0, TContext> pipeline = this.$this_execute;
                TContext tcontext = this.$context;
                this.label = 1;
                Object objExecute = pipeline.execute(tcontext, t0Var, this);
                a aVar = a.f15033i;
                if (objExecute == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0Var;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [TContext] */
    /* JADX INFO: renamed from: io.ktor.util.pipeline.PipelineKt$intercept$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0006\u001a\u00020\u0005\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000*\u0010\u0012\u0006\b\u0001\u0012\u00020\u0000\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "TSubject", "TContext", "Lio/ktor/util/pipeline/PipelineContext;", "subject", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.util.pipeline.PipelineKt$intercept$1", f = "Pipeline.kt", l = {494}, m = "invokeSuspend")
    public static final class AnonymousClass1<TContext> extends j implements q<PipelineContext<? extends Object, TContext>, Object, d<? super t0>, Object> {
        final /* synthetic */ q<PipelineContext<TSubject, TContext>, TSubject, d<? super t0>, Object> $block;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(q<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super d<? super t0>, ? extends Object> qVar, d<? super AnonymousClass1> dVar) {
            super(3, dVar);
            this.$block = qVar;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<? extends Object, TContext> pipelineContext, Object obj, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$block, dVar);
            anonymousClass1.L$0 = pipelineContext;
            anonymousClass1.L$1 = obj;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
                return t0.f22605a;
            }
            c.G(obj);
            p.h();
            throw null;
        }
    }

    public static final <TContext> Object execute(Pipeline<t0, TContext> pipeline, TContext tcontext, d<? super t0> dVar) {
        Object objInitContextInDebugMode = ContextUtilsKt.initContextInDebugMode(new AnonymousClass2(pipeline, tcontext, null), dVar);
        return objInitContextInDebugMode == a.f15033i ? objInitContextInDebugMode : t0.f22605a;
    }

    private static final <TContext> Object execute$$forInline(Pipeline<t0, TContext> pipeline, TContext tcontext, d<? super t0> dVar) {
        ContextUtilsKt.initContextInDebugMode(new AnonymousClass2(pipeline, tcontext, null), dVar);
        return t0.f22605a;
    }

    public static final <TSubject, TContext> void intercept(Pipeline<?, TContext> pipeline, PipelinePhase pipelinePhase, q<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super d<? super t0>, ? extends Object> qVar) {
        p.h();
        throw null;
    }
}
