package androidx.compose.foundation.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "invoke-0kLqBqw", "(Landroidx/compose/ui/layout/SubcomposeMeasureScope;J)Landroidx/compose/ui/layout/MeasureResult;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class BoxWithConstraintsKt$BoxWithConstraints$1$1 extends r implements p<SubcomposeMeasureScope, Constraints, MeasureResult> {
    final /* synthetic */ q<BoxWithConstraintsScope, Composer, Integer, t0> $content;
    final /* synthetic */ MeasurePolicy $measurePolicy;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BoxWithConstraintsKt$BoxWithConstraints$1$1(MeasurePolicy measurePolicy, q<? super BoxWithConstraintsScope, ? super Composer, ? super Integer, t0> qVar) {
        super(2);
        this.$measurePolicy = measurePolicy;
        this.$content = qVar;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m464invoke0kLqBqw((SubcomposeMeasureScope) obj, ((Constraints) obj2).getValue());
    }

    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
    public final MeasureResult m464invoke0kLqBqw(SubcomposeMeasureScope subcomposeMeasureScope, long j10) {
        return this.$measurePolicy.mo5measure3p2s80s(subcomposeMeasureScope, subcomposeMeasureScope.subcompose(t0.f22605a, ComposableLambdaKt.composableLambdaInstance(-1945019079, true, new BoxWithConstraintsKt$BoxWithConstraints$1$1$measurables$1(this.$content, new BoxWithConstraintsScopeImpl(subcomposeMeasureScope, j10, null)))), j10);
    }
}
