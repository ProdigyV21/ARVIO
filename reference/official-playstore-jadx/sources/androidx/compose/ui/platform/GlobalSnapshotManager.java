package androidx.compose.ui.platform;

import androidx.compose.runtime.snapshots.Snapshot;
import androidx.media3.container.MdtaMetadataEntry;
import java.util.concurrent.atomic.AtomicBoolean;
import ka.k0;
import ka.l0;
import ka.m0;
import kotlin.Metadata;
import m2.f0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0003R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Landroidx/compose/ui/platform/GlobalSnapshotManager;", "", "<init>", "()V", "Lx6/t0;", "ensureStarted", "Ljava/util/concurrent/atomic/AtomicBoolean;", "started", "Ljava/util/concurrent/atomic/AtomicBoolean;", "sent", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class GlobalSnapshotManager {
    public static final GlobalSnapshotManager INSTANCE = new GlobalSnapshotManager();
    private static final AtomicBoolean started = new AtomicBoolean(false);
    private static final AtomicBoolean sent = new AtomicBoolean(false);
    public static final int $stable = 8;

    /* JADX INFO: renamed from: androidx.compose.ui.platform.GlobalSnapshotManager$ensureStarted$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.ui.platform.GlobalSnapshotManager$ensureStarted$1", f = "GlobalSnapshotManager.android.kt", l = {MdtaMetadataEntry.TYPE_INDICATOR_INT32}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ ma.j<t0> $channel;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ma.j<t0> jVar, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$channel = jVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass1(this.$channel, dVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0035 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x003e A[Catch: all -> 0x0013, TRY_LEAVE, TryCatch #0 {all -> 0x0013, blocks: (B:6:0x000f, B:18:0x0036, B:20:0x003e, B:14:0x0027, B:13:0x0022), top: B:28:0x0003 }] */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0052  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0033 -> B:18:0x0036). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r5) {
            /*
                r4 = this;
                int r0 = r4.label
                r1 = 1
                if (r0 == 0) goto L1d
                if (r0 != r1) goto L15
                java.lang.Object r0 = r4.L$1
                ma.l r0 = (ma.l) r0
                java.lang.Object r2 = r4.L$0
                ma.w r2 = (ma.w) r2
                k2.c.G(r5)     // Catch: java.lang.Throwable -> L13
                goto L36
            L13:
                r5 = move-exception
                goto L59
            L15:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r0)
                throw r5
            L1d:
                k2.c.G(r5)
                ma.j<x6.t0> r2 = r4.$channel
                ma.l r5 = r2.iterator()     // Catch: java.lang.Throwable -> L13
                r0 = r5
            L27:
                r4.L$0 = r2     // Catch: java.lang.Throwable -> L13
                r4.L$1 = r0     // Catch: java.lang.Throwable -> L13
                r4.label = r1     // Catch: java.lang.Throwable -> L13
                java.lang.Object r5 = r0.a(r4)     // Catch: java.lang.Throwable -> L13
                e7.a r3 = e7.a.f15033i
                if (r5 != r3) goto L36
                return r3
            L36:
                java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch: java.lang.Throwable -> L13
                boolean r5 = r5.booleanValue()     // Catch: java.lang.Throwable -> L13
                if (r5 == 0) goto L52
                java.lang.Object r5 = r0.next()     // Catch: java.lang.Throwable -> L13
                x6.t0 r5 = (x6.t0) r5     // Catch: java.lang.Throwable -> L13
                java.util.concurrent.atomic.AtomicBoolean r5 = androidx.compose.ui.platform.GlobalSnapshotManager.access$getSent$p()     // Catch: java.lang.Throwable -> L13
                r3 = 0
                r5.set(r3)     // Catch: java.lang.Throwable -> L13
                androidx.compose.runtime.snapshots.Snapshot$Companion r5 = androidx.compose.runtime.snapshots.Snapshot.INSTANCE     // Catch: java.lang.Throwable -> L13
                r5.sendApplyNotifications()     // Catch: java.lang.Throwable -> L13
                goto L27
            L52:
                r5 = 0
                r2.cancel(r5)
                x6.t0 r5 = x6.t0.f22605a
                return r5
            L59:
                throw r5     // Catch: java.lang.Throwable -> L5a
            L5a:
                r0 = move-exception
                qb.d.e(r2, r5)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.GlobalSnapshotManager.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.platform.GlobalSnapshotManager$ensureStarted$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Object;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends kotlin.jvm.internal.r implements r7.l<Object, t0> {
        final /* synthetic */ ma.j<t0> $channel;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ma.j<t0> jVar) {
            super(1);
            this.$channel = jVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m4968invoke(obj);
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m4968invoke(Object obj) {
            if (GlobalSnapshotManager.sent.compareAndSet(false, true)) {
                this.$channel.mo6685trySendJP2dKIU(t0.f22605a);
            }
        }
    }

    private GlobalSnapshotManager() {
    }

    public final void ensureStarted() {
        if (started.compareAndSet(false, true)) {
            ma.f fVarA = f0.a(1, 0, 6);
            m0.p(l0.a(AndroidUiDispatcher.INSTANCE.getMain()), null, 0, new AnonymousClass1(fVarA, null), 3);
            Snapshot.INSTANCE.registerGlobalWriteObserver(new AnonymousClass2(fVarA));
        }
    }
}
