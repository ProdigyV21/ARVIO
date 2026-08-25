package androidx.compose.foundation;

import d7.d;
import f7.j;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import ka.k0;
import ka.l0;
import ka.v1;
import kotlin.Metadata;
import r7.l;
import r7.p;
import ua.e;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0001%B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ>\u0010\u000f\u001a\u00028\u0000\"\u0004\b\u0000\u0010\t2\b\b\u0002\u0010\u000b\u001a\u00020\n2\u001c\u0010\u000e\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fH\u0086@¢\u0006\u0004\b\u000f\u0010\u0010JW\u0010\u0015\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\t2\u0006\u0010\u0012\u001a\u00028\u00002\b\b\u0002\u0010\u000b\u001a\u00020\n2'\u0010\u000e\u001a#\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0013¢\u0006\u0002\b\u0014H\u0086@¢\u0006\u0004\b\u0015\u0010\u0016J\u001e\u0010\u0019\u001a\u00020\u00182\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0017H\u0086\b¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0018H\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u0006H\u0001¢\u0006\u0004\b\u001d\u0010\u0003R(\u0010 \u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u001ej\n\u0012\u0006\u0012\u0004\u0018\u00010\u0004`\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006&"}, d2 = {"Landroidx/compose/foundation/MutatorMutex;", "", "<init>", "()V", "Landroidx/compose/foundation/MutatorMutex$Mutator;", "mutator", "Lx6/t0;", "tryMutateOrCancel", "(Landroidx/compose/foundation/MutatorMutex$Mutator;)V", "R", "Landroidx/compose/foundation/MutatePriority;", "priority", "Lkotlin/Function1;", "Ld7/d;", "block", "mutate", "(Landroidx/compose/foundation/MutatePriority;Lr7/l;Ld7/d;)Ljava/lang/Object;", "T", "receiver", "Lkotlin/Function2;", "Lx6/n;", "mutateWith", "(Ljava/lang/Object;Landroidx/compose/foundation/MutatePriority;Lr7/p;Ld7/d;)Ljava/lang/Object;", "Lkotlin/Function0;", "", "tryMutate", "(Lr7/a;)Z", "tryLock", "()Z", "unlock", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/foundation/AtomicReference;", "currentMutator", "Ljava/util/concurrent/atomic/AtomicReference;", "Lua/a;", "mutex", "Lua/a;", "Mutator", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MutatorMutex {
    public static final int $stable = 0;
    private final AtomicReference<Mutator> currentMutator = new AtomicReference<>(null);
    private final ua.a mutex = e.a();

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0000¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\r\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/compose/foundation/MutatorMutex$Mutator;", "", "Landroidx/compose/foundation/MutatePriority;", "priority", "Lka/v1;", "job", "<init>", "(Landroidx/compose/foundation/MutatePriority;Lka/v1;)V", "other", "", "canInterrupt", "(Landroidx/compose/foundation/MutatorMutex$Mutator;)Z", "Lx6/t0;", "cancel", "()V", "Landroidx/compose/foundation/MutatePriority;", "getPriority", "()Landroidx/compose/foundation/MutatePriority;", "Lka/v1;", "getJob", "()Lka/v1;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Mutator {
        private final v1 job;
        private final MutatePriority priority;

        public Mutator(MutatePriority mutatePriority, v1 v1Var) {
            this.priority = mutatePriority;
            this.job = v1Var;
        }

        public final boolean canInterrupt(Mutator other) {
            return this.priority.compareTo(other.priority) >= 0;
        }

        public final void cancel() {
            this.job.cancel((CancellationException) new MutationInterruptedException());
        }

        public final v1 getJob() {
            return this.job;
        }

        public final MutatePriority getPriority() {
            return this.priority;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.compose.foundation.MutatorMutex$mutate$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@"}, d2 = {"R", "Lka/k0;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.MutatorMutex$mutate$2", f = "MutatorMutex.kt", l = {220, 132}, m = "invokeSuspend")
    public static final class AnonymousClass2<R> extends j implements p<k0, d<? super R>, Object> {
        final /* synthetic */ l<d<? super R>, Object> $block;
        final /* synthetic */ MutatePriority $priority;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        final /* synthetic */ MutatorMutex this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(MutatePriority mutatePriority, MutatorMutex mutatorMutex, l<? super d<? super R>, ? extends Object> lVar, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$priority = mutatePriority;
            this.this$0 = mutatorMutex;
            this.$block = lVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$priority, this.this$0, this.$block, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        /*  JADX ERROR: JadxRuntimeException in pass: ConstInlineVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Unexpected instance arg in invoke
            	at jadx.core.dex.visitors.ConstInlineVisitor.addExplicitCast(ConstInlineVisitor.java:285)
            	at jadx.core.dex.visitors.ConstInlineVisitor.replaceArg(ConstInlineVisitor.java:267)
            	at jadx.core.dex.visitors.ConstInlineVisitor.replaceConst(ConstInlineVisitor.java:177)
            	at jadx.core.dex.visitors.ConstInlineVisitor.checkInsn(ConstInlineVisitor.java:110)
            	at jadx.core.dex.visitors.ConstInlineVisitor.process(ConstInlineVisitor.java:55)
            	at jadx.core.dex.visitors.ConstInlineVisitor.visit(ConstInlineVisitor.java:47)
            */
        @Override // f7.a
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                int r0 = r7.label
                r1 = 2
                r2 = 1
                r3 = 0
                e7.a r4 = e7.a.f15033i
                if (r0 == 0) goto L3e
                if (r0 == r2) goto L29
                if (r0 != r1) goto L21
                java.lang.Object r0 = r7.L$2
                androidx.compose.foundation.MutatorMutex r0 = (androidx.compose.foundation.MutatorMutex) r0
                java.lang.Object r1 = r7.L$1
                ua.a r1 = (ua.a) r1
                java.lang.Object r2 = r7.L$0
                androidx.compose.foundation.MutatorMutex$Mutator r2 = (androidx.compose.foundation.MutatorMutex.Mutator) r2
                k2.c.G(r8)     // Catch: java.lang.Throwable -> L1e
                goto L91
            L1e:
                r8 = move-exception
                goto Lad
            L21:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L29:
                java.lang.Object r0 = r7.L$3
                androidx.compose.foundation.MutatorMutex r0 = (androidx.compose.foundation.MutatorMutex) r0
                java.lang.Object r2 = r7.L$2
                r7.l r2 = (r7.l) r2
                java.lang.Object r5 = r7.L$1
                ua.a r5 = (ua.a) r5
                java.lang.Object r6 = r7.L$0
                androidx.compose.foundation.MutatorMutex$Mutator r6 = (androidx.compose.foundation.MutatorMutex.Mutator) r6
                k2.c.G(r8)
                r8 = r5
                goto L7c
            L3e:
                k2.c.G(r8)
                java.lang.Object r8 = r7.L$0
                ka.k0 r8 = (ka.k0) r8
                androidx.compose.foundation.MutatorMutex$Mutator r0 = new androidx.compose.foundation.MutatorMutex$Mutator
                androidx.compose.foundation.MutatePriority r5 = r7.$priority
                d7.j r8 = r8.getCoroutineContext()
                ka.u1 r6 = ka.u1.f19642i
                d7.j$a r8 = r8.get(r6)
                ka.v1 r8 = (ka.v1) r8
                r0.<init>(r5, r8)
                androidx.compose.foundation.MutatorMutex r8 = r7.this$0
                androidx.compose.foundation.MutatorMutex.access$tryMutateOrCancel(r8, r0)
                androidx.compose.foundation.MutatorMutex r8 = r7.this$0
                ua.a r8 = androidx.compose.foundation.MutatorMutex.access$getMutex$p(r8)
                r7.l<d7.d<? super R>, java.lang.Object> r5 = r7.$block
                androidx.compose.foundation.MutatorMutex r6 = r7.this$0
                r7.L$0 = r0
                r7.L$1 = r8
                r7.L$2 = r5
                r7.L$3 = r6
                r7.label = r2
                java.lang.Object r2 = r8.c(r7)
                if (r2 != r4) goto L78
                goto L8c
            L78:
                r2 = r6
                r6 = r0
                r0 = r2
                r2 = r5
            L7c:
                r7.L$0 = r6     // Catch: java.lang.Throwable -> La8
                r7.L$1 = r8     // Catch: java.lang.Throwable -> La8
                r7.L$2 = r0     // Catch: java.lang.Throwable -> La8
                r7.L$3 = r3     // Catch: java.lang.Throwable -> La8
                r7.label = r1     // Catch: java.lang.Throwable -> La8
                java.lang.Object r1 = r2.invoke(r7)     // Catch: java.lang.Throwable -> La8
                if (r1 != r4) goto L8d
            L8c:
                return r4
            L8d:
                r2 = r1
                r1 = r8
                r8 = r2
                r2 = r6
            L91:
                java.util.concurrent.atomic.AtomicReference r0 = androidx.compose.foundation.MutatorMutex.access$getCurrentMutator$p(r0)     // Catch: java.lang.Throwable -> La6
            L95:
                boolean r4 = r0.compareAndSet(r2, r3)     // Catch: java.lang.Throwable -> La6
                if (r4 == 0) goto L9c
                goto La2
            L9c:
                java.lang.Object r4 = r0.get()     // Catch: java.lang.Throwable -> La6
                if (r4 == r2) goto L95
            La2:
                r1.b(r3)
                return r8
            La6:
                r8 = move-exception
                goto Lbf
            La8:
                r1 = move-exception
                r2 = r1
                r1 = r8
                r8 = r2
                r2 = r6
            Lad:
                java.util.concurrent.atomic.AtomicReference r0 = androidx.compose.foundation.MutatorMutex.access$getCurrentMutator$p(r0)     // Catch: java.lang.Throwable -> La6
            Lb1:
                boolean r4 = r0.compareAndSet(r2, r3)     // Catch: java.lang.Throwable -> La6
                if (r4 != 0) goto Lbe
                java.lang.Object r4 = r0.get()     // Catch: java.lang.Throwable -> La6
                if (r4 != r2) goto Lbe
                goto Lb1
            Lbe:
                throw r8     // Catch: java.lang.Throwable -> La6
            Lbf:
                r1.b(r3)
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.MutatorMutex.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super R> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.compose.foundation.MutatorMutex$mutateWith$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0003\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u00020\u0002H\u008a@"}, d2 = {"T", "R", "Lka/k0;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.MutatorMutex$mutateWith$2", f = "MutatorMutex.kt", l = {220, 173}, m = "invokeSuspend")
    public static final class C02942<R> extends j implements p<k0, d<? super R>, Object> {
        final /* synthetic */ p<T, d<? super R>, Object> $block;
        final /* synthetic */ MutatePriority $priority;
        final /* synthetic */ T $receiver;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        final /* synthetic */ MutatorMutex this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02942(MutatePriority mutatePriority, MutatorMutex mutatorMutex, p<? super T, ? super d<? super R>, ? extends Object> pVar, T t2, d<? super C02942> dVar) {
            super(2, dVar);
            this.$priority = mutatePriority;
            this.this$0 = mutatorMutex;
            this.$block = pVar;
            this.$receiver = t2;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C02942 c02942 = new C02942(this.$priority, this.this$0, this.$block, this.$receiver, dVar);
            c02942.L$0 = obj;
            return c02942;
        }

        /*  JADX ERROR: JadxRuntimeException in pass: ConstInlineVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Unexpected instance arg in invoke
            	at jadx.core.dex.visitors.ConstInlineVisitor.addExplicitCast(ConstInlineVisitor.java:285)
            	at jadx.core.dex.visitors.ConstInlineVisitor.replaceArg(ConstInlineVisitor.java:267)
            	at jadx.core.dex.visitors.ConstInlineVisitor.replaceConst(ConstInlineVisitor.java:177)
            	at jadx.core.dex.visitors.ConstInlineVisitor.checkInsn(ConstInlineVisitor.java:110)
            	at jadx.core.dex.visitors.ConstInlineVisitor.process(ConstInlineVisitor.java:55)
            	at jadx.core.dex.visitors.ConstInlineVisitor.visit(ConstInlineVisitor.java:47)
            */
        @Override // f7.a
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                Method dump skipped, instruction units count: 203
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.MutatorMutex.C02942.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super R> dVar) {
            return ((C02942) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public static final /* synthetic */ AtomicReference access$getCurrentMutator$p(MutatorMutex mutatorMutex) {
        return mutatorMutex.currentMutator;
    }

    public static final /* synthetic */ ua.a access$getMutex$p(MutatorMutex mutatorMutex) {
        return mutatorMutex.mutex;
    }

    public static final /* synthetic */ void access$tryMutateOrCancel(MutatorMutex mutatorMutex, Mutator mutator) {
        mutatorMutex.tryMutateOrCancel(mutator);
    }

    public static /* synthetic */ Object mutate$default(MutatorMutex mutatorMutex, MutatePriority mutatePriority, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return mutatorMutex.mutate(mutatePriority, lVar, dVar);
    }

    public static /* synthetic */ Object mutateWith$default(MutatorMutex mutatorMutex, Object obj, MutatePriority mutatePriority, p pVar, d dVar, int i10, Object obj2) {
        if ((i10 & 2) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return mutatorMutex.mutateWith(obj, mutatePriority, pVar, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void tryMutateOrCancel(Mutator mutator) {
        while (true) {
            Mutator mutator2 = this.currentMutator.get();
            if (mutator2 != null && !mutator.canInterrupt(mutator2)) {
                throw new CancellationException("Current mutation had a higher priority");
            }
            AtomicReference<Mutator> atomicReference = this.currentMutator;
            while (!atomicReference.compareAndSet(mutator2, mutator)) {
                if (atomicReference.get() != mutator2) {
                    break;
                }
            }
            if (mutator2 != null) {
                mutator2.cancel();
                return;
            }
            return;
        }
    }

    public final <R> Object mutate(MutatePriority mutatePriority, l<? super d<? super R>, ? extends Object> lVar, d<? super R> dVar) {
        return l0.c(new AnonymousClass2(mutatePriority, this, lVar, null), dVar);
    }

    public final <T, R> Object mutateWith(T t2, MutatePriority mutatePriority, p<? super T, ? super d<? super R>, ? extends Object> pVar, d<? super R> dVar) {
        return l0.c(new C02942(mutatePriority, this, pVar, t2, null), dVar);
    }

    public final boolean tryLock() {
        return this.mutex.tryLock();
    }

    public final boolean tryMutate(r7.a<t0> block) {
        boolean zTryLock = tryLock();
        if (!zTryLock) {
            return zTryLock;
        }
        try {
            block.invoke();
            return zTryLock;
        } finally {
            unlock();
        }
    }

    public final void unlock() {
        this.mutex.b(null);
    }
}
