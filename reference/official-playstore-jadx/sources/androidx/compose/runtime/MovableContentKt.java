package androidx.compose.runtime;

import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.media3.exoplayer.RendererCapabilities;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import r7.s;
import r7.t;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a+\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a=\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u0002\"\u0004\b\u0000\u0010\u00062\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u0002¢\u0006\u0004\b\u0004\u0010\b\u001aO\u0010\u0004\u001a\u0019\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0002\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n2\u001d\u0010\u0003\u001a\u0019\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0002¢\u0006\u0004\b\u0004\u0010\f\u001aa\u0010\u0004\u001a\u001f\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00020\u00010\u000e¢\u0006\u0002\b\u0002\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n\"\u0004\b\u0002\u0010\r2#\u0010\u0003\u001a\u001f\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00020\u00010\u000e¢\u0006\u0002\b\u0002¢\u0006\u0004\b\u0004\u0010\u000f\u001as\u0010\u0004\u001a%\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0002\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n\"\u0004\b\u0002\u0010\r\"\u0004\b\u0003\u0010\u00102)\u0010\u0003\u001a%\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0002¢\u0006\u0004\b\u0004\u0010\u0012\u001aG\u0010\u0015\u001a\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u0002¢\u0006\u0002\b\u0014\"\u0004\b\u0000\u0010\u00132\u001c\u0010\u0003\u001a\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u0002¢\u0006\u0002\b\u0014¢\u0006\u0004\b\u0015\u0010\b\u001aY\u0010\u0015\u001a\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0002¢\u0006\u0002\b\u0014\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\u00062\"\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0002¢\u0006\u0002\b\u0014¢\u0006\u0004\b\u0015\u0010\f\u001ak\u0010\u0015\u001a$\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00020\u00010\u000e¢\u0006\u0002\b\u0002¢\u0006\u0002\b\u0014\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\t\"\u0004\b\u0002\u0010\n2(\u0010\u0003\u001a$\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00020\u00010\u000e¢\u0006\u0002\b\u0002¢\u0006\u0002\b\u0014¢\u0006\u0004\b\u0015\u0010\u000f\u001a}\u0010\u0015\u001a*\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0002¢\u0006\u0002\b\u0014\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\t\"\u0004\b\u0002\u0010\n\"\u0004\b\u0003\u0010\r2.\u0010\u0003\u001a*\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0002¢\u0006\u0002\b\u0014¢\u0006\u0004\b\u0015\u0010\u0012\"\u0014\u0010\u0017\u001a\u00020\u00168\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "content", "movableContentOf", "(Lr7/p;)Lr7/p;", "P", "Lkotlin/Function1;", "(Lr7/q;)Lr7/q;", "P1", "P2", "Lkotlin/Function2;", "(Lr7/r;)Lr7/r;", "P3", "Lkotlin/Function3;", "(Lr7/s;)Lr7/s;", "P4", "Lkotlin/Function4;", "(Lr7/t;)Lr7/t;", "R", "Lx6/n;", "movableContentWithReceiverOf", "", "movableContentKey", "I", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MovableContentKt {
    public static final int movableContentKey = 126665345;

    /* JADX INFO: renamed from: androidx.compose.runtime.MovableContentKt$movableContentOf$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ MovableContent<t0> $movableContent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(MovableContent<t0> movableContent) {
            super(2);
            this.$movableContent = movableContent;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-642339857, i10, -1, "androidx.compose.runtime.movableContentOf.<anonymous> (MovableContent.kt:39)");
            }
            composer.insertMovableContent(this.$movableContent, t0.f22605a);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [P] */
    /* JADX INFO: renamed from: androidx.compose.runtime.MovableContentKt$movableContentOf$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u0000H\u000b¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"P", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2<P> extends r implements q<P, Composer, Integer, t0> {
        final /* synthetic */ MovableContent<P> $movableContent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(MovableContent<P> movableContent) {
            super(3);
            this.$movableContent = movableContent;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke(obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(P p10, Composer composer, int i10) {
            if ((i10 & 6) == 0) {
                i10 |= (i10 & 8) == 0 ? composer.changed(p10) : composer.changedInstance(p10) ? 4 : 2;
            }
            if ((i10 & 19) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-434707029, i10, -1, "androidx.compose.runtime.movableContentOf.<anonymous> (MovableContent.kt:64)");
            }
            composer.insertMovableContent(this.$movableContent, p10);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [P1, P2] */
    /* JADX INFO: renamed from: androidx.compose.runtime.MovableContentKt$movableContentOf$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u00002\u0006\u0010\u0003\u001a\u00028\u0001H\u000b¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"P1", "P2", "p1", "p2", "Lx6/t0;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3<P1, P2> extends r implements r7.r<P1, P2, Composer, Integer, t0> {
        final /* synthetic */ MovableContent<x> $movableContent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(MovableContent<x> movableContent) {
            super(4);
            this.$movableContent = movableContent;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // r7.r
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            invoke(obj, obj2, (Composer) obj3, ((Number) obj4).intValue());
            return t0.f22605a;
        }

        public final void invoke(P1 p12, P2 p22, Composer composer, int i10) {
            int i11;
            if ((i10 & 6) == 0) {
                i11 = ((i10 & 8) == 0 ? composer.changed(p12) : composer.changedInstance(p12) ? 4 : 2) | i10;
            } else {
                i11 = i10;
            }
            if ((i10 & 48) == 0) {
                i11 |= (i10 & 64) == 0 ? composer.changed(p22) : composer.changedInstance(p22) ? 32 : 16;
            }
            if ((i11 & 147) == 146 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1200019734, i11, -1, "androidx.compose.runtime.movableContentOf.<anonymous> (MovableContent.kt:89)");
            }
            composer.insertMovableContent(this.$movableContent, new x(p12, p22));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [P1, P2, P3] */
    /* JADX INFO: renamed from: androidx.compose.runtime.MovableContentKt$movableContentOf$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\t\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u00022\u0006\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00028\u00012\u0006\u0010\u0005\u001a\u00028\u0002H\u000b¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"P1", "P2", "P3", "p1", "p2", "p3", "Lx6/t0;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4<P1, P2, P3> extends r implements s<P1, P2, P3, Composer, Integer, t0> {
        final /* synthetic */ MovableContent<x> $movableContent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(MovableContent<x> movableContent) {
            super(5);
            this.$movableContent = movableContent;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // r7.s
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            invoke(obj, obj2, obj3, (Composer) obj4, ((Number) obj5).intValue());
            return t0.f22605a;
        }

        public final void invoke(P1 p12, P2 p22, P3 p32, Composer composer, int i10) {
            int i11;
            if ((i10 & 6) == 0) {
                i11 = ((i10 & 8) == 0 ? composer.changed(p12) : composer.changedInstance(p12) ? 4 : 2) | i10;
            } else {
                i11 = i10;
            }
            if ((i10 & 48) == 0) {
                i11 |= (i10 & 64) == 0 ? composer.changed(p22) : composer.changedInstance(p22) ? 32 : 16;
            }
            if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                i11 |= (i10 & 512) == 0 ? composer.changed(p32) : composer.changedInstance(p32) ? 256 : 128;
            }
            if ((i11 & 1171) == 1170 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1083870185, i11, -1, "androidx.compose.runtime.movableContentOf.<anonymous> (MovableContent.kt:118)");
            }
            composer.insertMovableContent(this.$movableContent, new x(new x(p12, p22), p32));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [P1, P2, P3, P4] */
    /* JADX INFO: renamed from: androidx.compose.runtime.MovableContentKt$movableContentOf$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u000b\u001a\u00020\b\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0002\"\u0004\b\u0003\u0010\u00032\u0006\u0010\u0004\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00028\u00022\u0006\u0010\u0007\u001a\u00028\u0003H\u000b¢\u0006\u0004\b\t\u0010\n"}, d2 = {"P1", "P2", "P3", "P4", "p1", "p2", "p3", "p4", "Lx6/t0;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass5<P1, P2, P3, P4> extends r implements t<P1, P2, P3, P4, Composer, Integer, t0> {
        final /* synthetic */ MovableContent<x> $movableContent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(MovableContent<x> movableContent) {
            super(6);
            this.$movableContent = movableContent;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // r7.t
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
            invoke(obj, obj2, obj3, obj4, (Composer) obj5, ((Number) obj6).intValue());
            return t0.f22605a;
        }

        public final void invoke(P1 p12, P2 p22, P3 p32, P4 p42, Composer composer, int i10) {
            int i11;
            if ((i10 & 6) == 0) {
                i11 = ((i10 & 8) == 0 ? composer.changed(p12) : composer.changedInstance(p12) ? 4 : 2) | i10;
            } else {
                i11 = i10;
            }
            if ((i10 & 48) == 0) {
                i11 |= (i10 & 64) == 0 ? composer.changed(p22) : composer.changedInstance(p22) ? 32 : 16;
            }
            if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                i11 |= (i10 & 512) == 0 ? composer.changed(p32) : composer.changedInstance(p32) ? 256 : 128;
            }
            if ((i10 & 3072) == 0) {
                i11 |= (i10 & 4096) == 0 ? composer.changed(p42) : composer.changedInstance(p42) ? 2048 : 1024;
            }
            if ((i11 & 9363) == 9362 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1741877681, i11, -1, "androidx.compose.runtime.movableContentOf.<anonymous> (MovableContent.kt:147)");
            }
            composer.insertMovableContent(this.$movableContent, new x(new x(p12, p22), new x(p32, p42)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.compose.runtime.MovableContentKt$movableContentWithReceiverOf$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0000*\u00028\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"R", "Lx6/t0;", "invoke", "(Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05901<R> extends r implements q<R, Composer, Integer, t0> {
        final /* synthetic */ MovableContent<R> $movableContent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05901(MovableContent<R> movableContent) {
            super(3);
            this.$movableContent = movableContent;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke(obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(R r4, Composer composer, int i10) {
            if ((i10 & 6) == 0) {
                i10 |= (i10 & 8) == 0 ? composer.changed(r4) : composer.changedInstance(r4) ? 4 : 2;
            }
            if ((i10 & 19) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(506997506, i10, -1, "androidx.compose.runtime.movableContentWithReceiverOf.<anonymous> (MovableContent.kt:172)");
            }
            composer.insertMovableContent(this.$movableContent, r4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [P, R] */
    /* JADX INFO: renamed from: androidx.compose.runtime.MovableContentKt$movableContentWithReceiverOf$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u00028\u00002\u0006\u0010\u0002\u001a\u00028\u0001H\u000b¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"R", "P", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05912<P, R> extends r implements r7.r<R, P, Composer, Integer, t0> {
        final /* synthetic */ MovableContent<x> $movableContent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05912(MovableContent<x> movableContent) {
            super(4);
            this.$movableContent = movableContent;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // r7.r
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            invoke(obj, obj2, (Composer) obj3, ((Number) obj4).intValue());
            return t0.f22605a;
        }

        public final void invoke(R r4, P p10, Composer composer, int i10) {
            int i11;
            if ((i10 & 6) == 0) {
                i11 = ((i10 & 8) == 0 ? composer.changed(r4) : composer.changedInstance(r4) ? 4 : 2) | i10;
            } else {
                i11 = i10;
            }
            if ((i10 & 48) == 0) {
                i11 |= (i10 & 64) == 0 ? composer.changed(p10) : composer.changedInstance(p10) ? 32 : 16;
            }
            if ((i11 & 147) == 146 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(627354118, i11, -1, "androidx.compose.runtime.movableContentWithReceiverOf.<anonymous> (MovableContent.kt:199)");
            }
            composer.insertMovableContent(this.$movableContent, new x(r4, p10));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [P1, R, P2] */
    /* JADX INFO: renamed from: androidx.compose.runtime.MovableContentKt$movableContentWithReceiverOf$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0002*\u00028\u00002\u0006\u0010\u0003\u001a\u00028\u00012\u0006\u0010\u0004\u001a\u00028\u0002H\u000b¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"R", "P1", "P2", "p1", "p2", "Lx6/t0;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05923<P1, P2, R> extends r implements s<R, P1, P2, Composer, Integer, t0> {
        final /* synthetic */ MovableContent<x> $movableContent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05923(MovableContent<x> movableContent) {
            super(5);
            this.$movableContent = movableContent;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // r7.s
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            invoke(obj, obj2, obj3, (Composer) obj4, ((Number) obj5).intValue());
            return t0.f22605a;
        }

        public final void invoke(R r4, P1 p12, P2 p22, Composer composer, int i10) {
            int i11;
            if ((i10 & 6) == 0) {
                i11 = ((i10 & 8) == 0 ? composer.changed(r4) : composer.changedInstance(r4) ? 4 : 2) | i10;
            } else {
                i11 = i10;
            }
            if ((i10 & 48) == 0) {
                i11 |= (i10 & 64) == 0 ? composer.changed(p12) : composer.changedInstance(p12) ? 32 : 16;
            }
            if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                i11 |= (i10 & 512) == 0 ? composer.changed(p22) : composer.changedInstance(p22) ? 256 : 128;
            }
            if ((i11 & 1171) == 1170 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(583402949, i11, -1, "androidx.compose.runtime.movableContentWithReceiverOf.<anonymous> (MovableContent.kt:228)");
            }
            composer.insertMovableContent(this.$movableContent, new x(new x(r4, p12), p22));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [P1, R, P2, P3] */
    /* JADX INFO: renamed from: androidx.compose.runtime.MovableContentKt$movableContentWithReceiverOf$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\n\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0002\"\u0004\b\u0003\u0010\u0003*\u00028\u00002\u0006\u0010\u0004\u001a\u00028\u00012\u0006\u0010\u0005\u001a\u00028\u00022\u0006\u0010\u0006\u001a\u00028\u0003H\u000b¢\u0006\u0004\b\b\u0010\t"}, d2 = {"R", "P1", "P2", "P3", "p1", "p2", "p3", "Lx6/t0;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05934<P1, P2, P3, R> extends r implements t<R, P1, P2, P3, Composer, Integer, t0> {
        final /* synthetic */ MovableContent<x> $movableContent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05934(MovableContent<x> movableContent) {
            super(6);
            this.$movableContent = movableContent;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // r7.t
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
            invoke(obj, obj2, obj3, obj4, (Composer) obj5, ((Number) obj6).intValue());
            return t0.f22605a;
        }

        public final void invoke(R r4, P1 p12, P2 p22, P3 p32, Composer composer, int i10) {
            int i11;
            if ((i10 & 6) == 0) {
                i11 = ((i10 & 8) == 0 ? composer.changed(r4) : composer.changedInstance(r4) ? 4 : 2) | i10;
            } else {
                i11 = i10;
            }
            if ((i10 & 48) == 0) {
                i11 |= (i10 & 64) == 0 ? composer.changed(p12) : composer.changedInstance(p12) ? 32 : 16;
            }
            if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                i11 |= (i10 & 512) == 0 ? composer.changed(p22) : composer.changedInstance(p22) ? 256 : 128;
            }
            if ((i10 & 3072) == 0) {
                i11 |= (i10 & 4096) == 0 ? composer.changed(p32) : composer.changedInstance(p32) ? 2048 : 1024;
            }
            if ((i11 & 9363) == 9362 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1468683306, i11, -1, "androidx.compose.runtime.movableContentWithReceiverOf.<anonymous> (MovableContent.kt:257)");
            }
            composer.insertMovableContent(this.$movableContent, new x(new x(r4, p12), new x(p22, p32)));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static final p<Composer, Integer, t0> movableContentOf(p<? super Composer, ? super Integer, t0> pVar) {
        return ComposableLambdaKt.composableLambdaInstance(-642339857, true, new AnonymousClass1(new MovableContent(ComposableLambdaKt.composableLambdaInstance(-1079330685, true, new MovableContentKt$movableContentOf$movableContent$1(pVar)))));
    }

    public static final <R> q<R, Composer, Integer, t0> movableContentWithReceiverOf(q<? super R, ? super Composer, ? super Integer, t0> qVar) {
        return ComposableLambdaKt.composableLambdaInstance(506997506, true, new C05901(new MovableContent(ComposableLambdaKt.composableLambdaInstance(250838178, true, new MovableContentKt$movableContentWithReceiverOf$movableContent$1(qVar)))));
    }

    public static final <P> q<P, Composer, Integer, t0> movableContentOf(q<? super P, ? super Composer, ? super Integer, t0> qVar) {
        return ComposableLambdaKt.composableLambdaInstance(-434707029, true, new AnonymousClass2(new MovableContent(qVar)));
    }

    public static final <R, P> r7.r<R, P, Composer, Integer, t0> movableContentWithReceiverOf(r7.r<? super R, ? super P, ? super Composer, ? super Integer, t0> rVar) {
        return ComposableLambdaKt.composableLambdaInstance(627354118, true, new C05912(new MovableContent(ComposableLambdaKt.composableLambdaInstance(812082854, true, new MovableContentKt$movableContentWithReceiverOf$movableContent$2(rVar)))));
    }

    public static final <P1, P2> r7.r<P1, P2, Composer, Integer, t0> movableContentOf(r7.r<? super P1, ? super P2, ? super Composer, ? super Integer, t0> rVar) {
        return ComposableLambdaKt.composableLambdaInstance(-1200019734, true, new AnonymousClass3(new MovableContent(ComposableLambdaKt.composableLambdaInstance(1849814513, true, new MovableContentKt$movableContentOf$movableContent$2(rVar)))));
    }

    public static final <R, P1, P2> s<R, P1, P2, Composer, Integer, t0> movableContentWithReceiverOf(s<? super R, ? super P1, ? super P2, ? super Composer, ? super Integer, t0> sVar) {
        return ComposableLambdaKt.composableLambdaInstance(583402949, true, new C05923(new MovableContent(ComposableLambdaKt.composableLambdaInstance(-1322148760, true, new MovableContentKt$movableContentWithReceiverOf$movableContent$3(sVar)))));
    }

    public static final <P1, P2, P3> s<P1, P2, P3, Composer, Integer, t0> movableContentOf(s<? super P1, ? super P2, ? super P3, ? super Composer, ? super Integer, t0> sVar) {
        return ComposableLambdaKt.composableLambdaInstance(-1083870185, true, new AnonymousClass4(new MovableContent(ComposableLambdaKt.composableLambdaInstance(-284417101, true, new MovableContentKt$movableContentOf$movableContent$3(sVar)))));
    }

    public static final <R, P1, P2, P3> t<R, P1, P2, P3, Composer, Integer, t0> movableContentWithReceiverOf(t<? super R, ? super P1, ? super P2, ? super P3, ? super Composer, ? super Integer, t0> tVar) {
        return ComposableLambdaKt.composableLambdaInstance(1468683306, true, new C05934(new MovableContent(ComposableLambdaKt.composableLambdaInstance(838586922, true, new MovableContentKt$movableContentWithReceiverOf$movableContent$4(tVar)))));
    }

    public static final <P1, P2, P3, P4> t<P1, P2, P3, P4, Composer, Integer, t0> movableContentOf(t<? super P1, ? super P2, ? super P3, ? super P4, ? super Composer, ? super Integer, t0> tVar) {
        return ComposableLambdaKt.composableLambdaInstance(-1741877681, true, new AnonymousClass5(new MovableContent(ComposableLambdaKt.composableLambdaInstance(1876318581, true, new MovableContentKt$movableContentOf$movableContent$4(tVar)))));
    }
}
