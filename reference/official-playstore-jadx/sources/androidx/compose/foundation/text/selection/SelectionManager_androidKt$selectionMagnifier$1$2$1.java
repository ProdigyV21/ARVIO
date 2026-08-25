package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.Magnifier_androidKt;
import androidx.compose.foundation.PlatformMagnifierFactory;
import androidx.compose.foundation.text.selection.SelectionManager_androidKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lkotlin/Function0;", "Landroidx/compose/ui/geometry/Offset;", TtmlNode.CENTER, "Landroidx/compose/ui/Modifier;", "invoke", "(Lr7/a;)Landroidx/compose/ui/Modifier;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SelectionManager_androidKt$selectionMagnifier$1$2$1 extends r implements l<r7.a<? extends Offset>, Modifier> {
    final /* synthetic */ Density $density;
    final /* synthetic */ MutableState<IntSize> $magnifierSize$delegate;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager_androidKt$selectionMagnifier$1$2$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/ui/unit/Density;", "invoke-tuRUvjQ", "(Landroidx/compose/ui/unit/Density;)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<Density, Offset> {
        final /* synthetic */ r7.a<Offset> $center;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(r7.a<Offset> aVar) {
            super(1);
            this.$center = aVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return Offset.m3225boximpl(m1018invoketuRUvjQ((Density) obj));
        }

        /* JADX INFO: renamed from: invoke-tuRUvjQ, reason: not valid java name */
        public final long m1018invoketuRUvjQ(Density density) {
            return ((Offset) this.$center.invoke()).getPackedValue();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager_androidKt$selectionMagnifier$1$2$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/unit/DpSize;", ContentDisposition.Parameters.Size, "Lx6/t0;", "invoke-EaSLcWc", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<DpSize, t0> {
        final /* synthetic */ Density $density;
        final /* synthetic */ MutableState<IntSize> $magnifierSize$delegate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Density density, MutableState<IntSize> mutableState) {
            super(1);
            this.$density = density;
            this.$magnifierSize$delegate = mutableState;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m1019invokeEaSLcWc(((DpSize) obj).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-EaSLcWc, reason: not valid java name */
        public final void m1019invokeEaSLcWc(long j10) {
            MutableState<IntSize> mutableState = this.$magnifierSize$delegate;
            Density density = this.$density;
            SelectionManager_androidKt.AnonymousClass1.invoke$lambda$2(mutableState, IntSizeKt.IntSize(density.mo279roundToPx0680j_4(DpSize.m5776getWidthD9Ej5fM(j10)), density.mo279roundToPx0680j_4(DpSize.m5774getHeightD9Ej5fM(j10))));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectionManager_androidKt$selectionMagnifier$1$2$1(Density density, MutableState<IntSize> mutableState) {
        super(1);
        this.$density = density;
        this.$magnifierSize$delegate = mutableState;
    }

    @Override // r7.l
    public final Modifier invoke(r7.a<Offset> aVar) {
        return Magnifier_androidKt.m253magnifierjPUL71Q$default(Modifier.INSTANCE, new AnonymousClass1(aVar), null, new AnonymousClass2(this.$density, this.$magnifierSize$delegate), 0.0f, true, 0L, 0.0f, 0.0f, false, PlatformMagnifierFactory.INSTANCE.getForCurrentPlatform(), 490, null);
    }
}
