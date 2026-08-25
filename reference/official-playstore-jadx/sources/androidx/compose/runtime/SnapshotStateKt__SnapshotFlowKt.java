package androidx.compose.runtime;

import androidx.media3.extractor.ts.TsExtractor;
import kotlin.Metadata;
import na.h1;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a/\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\u001aA\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\"\b\b\u0000\u0010\u0000*\u00028\u0001\"\u0004\b\u0001\u0010\u0007*\b\u0012\u0004\u0012\u00028\u00000\b2\u0006\u0010\t\u001a\u00028\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\n\u001a'\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u00002\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b¢\u0006\u0004\b\r\u0010\u000e\u001a'\u0010\u0016\u001a\u00020\u0013*\b\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u0011H\u0002¢\u0006\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"T", "Lna/h1;", "Ld7/j;", "context", "Landroidx/compose/runtime/State;", "collectAsState", "(Lna/h1;Ld7/j;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "R", "Lna/j;", "initial", "(Lna/j;Ljava/lang/Object;Ld7/j;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "Lkotlin/Function0;", "block", "snapshotFlow", "(Lr7/a;)Lna/j;", "Lt/d0;", "", "", "set", "", "intersects$SnapshotStateKt__SnapshotFlowKt", "(Lt/d0;Ljava/util/Set;)Z", "intersects", "runtime_release"}, k = 5, mv = {1, 8, 0}, xi = 48, xs = "androidx/compose/runtime/SnapshotStateKt")
final /* synthetic */ class SnapshotStateKt__SnapshotFlowKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lna/k;", "Lx6/t0;", "<anonymous>", "(Lna/k;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1", f = "SnapshotFlow.kt", l = {146, 150, TsExtractor.TS_STREAM_TYPE_AC4}, m = "invokeSuspend")
    public static final class AnonymousClass1<T> extends f7.j implements p<na.k<? super T>, d7.d<? super t0>, Object> {
        final /* synthetic */ r7.a<T> $block;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(r7.a<? extends T> aVar, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$block = aVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$block, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        /* JADX WARN: Path cross not found for [B:33:0x00c8, B:37:0x00d1], limit reached: 74 */
        /* JADX WARN: Path cross not found for [B:41:0x00e0, B:57:0x011f], limit reached: 74 */
        /* JADX WARN: Path cross not found for [B:57:0x011f, B:41:0x00e0], limit reached: 74 */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00bd  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00e0 A[Catch: all -> 0x0053, TRY_LEAVE, TryCatch #0 {all -> 0x0053, blocks: (B:15:0x004e, B:31:0x00c4, B:33:0x00c8, B:38:0x00d2, B:41:0x00e0, B:45:0x00f6, B:47:0x00ff, B:55:0x011b, B:56:0x011e, B:42:0x00eb, B:44:0x00f3, B:53:0x0117, B:54:0x011a), top: B:68:0x004e, inners: #1 }] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 309
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(na.k<? super T> kVar, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(kVar, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public static final <T> State<T> collectAsState(h1<? extends T> h1Var, d7.j jVar, Composer composer, int i10, int i11) {
        if ((i11 & 1) != 0) {
            jVar = d7.k.f14688i;
        }
        d7.j jVar2 = jVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1439883919, i10, -1, "androidx.compose.runtime.collectAsState (SnapshotFlow.kt:47)");
        }
        State<T> stateCollectAsState = SnapshotStateKt.collectAsState(h1Var, h1Var.getValue(), jVar2, composer, (i10 & 14) | ((i10 << 3) & 896), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return stateCollectAsState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean intersects$SnapshotStateKt__SnapshotFlowKt(t.d0 r13, java.util.Set<? extends java.lang.Object> r14) {
        /*
            java.lang.Object[] r0 = r13.f21836b
            long[] r13 = r13.f21835a
            int r1 = r13.length
            int r1 = r1 + (-2)
            r2 = 0
            if (r1 < 0) goto L48
            r3 = r2
        Lb:
            r4 = r13[r3]
            long r6 = ~r4
            r8 = 7
            long r6 = r6 << r8
            long r6 = r6 & r4
            r8 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r6 = r6 & r8
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L43
            int r6 = r3 - r1
            int r6 = ~r6
            int r6 = r6 >>> 31
            r7 = 8
            int r6 = 8 - r6
            r8 = r2
        L25:
            if (r8 >= r6) goto L41
            r9 = 255(0xff, double:1.26E-321)
            long r9 = r9 & r4
            r11 = 128(0x80, double:6.3E-322)
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 >= 0) goto L3d
            int r9 = r3 << 3
            int r9 = r9 + r8
            r9 = r0[r9]
            boolean r9 = r14.contains(r9)
            if (r9 == 0) goto L3d
            r13 = 1
            return r13
        L3d:
            long r4 = r4 >> r7
            int r8 = r8 + 1
            goto L25
        L41:
            if (r6 != r7) goto L48
        L43:
            if (r3 == r1) goto L48
            int r3 = r3 + 1
            goto Lb
        L48:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt.intersects$SnapshotStateKt__SnapshotFlowKt(t.d0, java.util.Set):boolean");
    }

    public static final <T> na.j<T> snapshotFlow(r7.a<? extends T> aVar) {
        return new na.t0(new AnonymousClass1(aVar, null));
    }

    public static final <T extends R, R> State<R> collectAsState(na.j<? extends T> jVar, R r4, d7.j jVar2, Composer composer, int i10, int i11) {
        if ((i11 & 2) != 0) {
            jVar2 = d7.k.f14688i;
        }
        d7.j jVar3 = jVar2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-606625098, i10, -1, "androidx.compose.runtime.collectAsState (SnapshotFlow.kt:63)");
        }
        boolean zChangedInstance = composer.changedInstance(jVar3) | composer.changedInstance(jVar);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new SnapshotStateKt__SnapshotFlowKt$collectAsState$1$1(jVar3, jVar, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        State<R> stateProduceState = SnapshotStateKt.produceState(r4, jVar, jVar3, (p) objRememberedValue, composer, ((i10 >> 3) & 14) | ((i10 << 3) & 112) | (i10 & 896));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return stateProduceState;
    }
}
