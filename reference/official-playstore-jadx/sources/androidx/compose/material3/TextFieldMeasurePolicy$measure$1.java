package androidx.compose.material3;

import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TextFieldMeasurePolicy$measure$1 extends r implements l<Placeable.PlacementScope, t0> {
    final /* synthetic */ Placeable $containerPlaceable;
    final /* synthetic */ Placeable $labelPlaceable;
    final /* synthetic */ Placeable $leadingPlaceable;
    final /* synthetic */ Placeable $placeholderPlaceable;
    final /* synthetic */ Placeable $prefixPlaceable;
    final /* synthetic */ Placeable $suffixPlaceable;
    final /* synthetic */ Placeable $supportingPlaceable;
    final /* synthetic */ Placeable $textFieldPlaceable;
    final /* synthetic */ MeasureScope $this_measure;
    final /* synthetic */ int $topPaddingValue;
    final /* synthetic */ int $totalHeight;
    final /* synthetic */ Placeable $trailingPlaceable;
    final /* synthetic */ int $width;
    final /* synthetic */ TextFieldMeasurePolicy this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldMeasurePolicy$measure$1(Placeable placeable, int i10, int i11, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, Placeable placeable6, Placeable placeable7, Placeable placeable8, Placeable placeable9, TextFieldMeasurePolicy textFieldMeasurePolicy, int i12, MeasureScope measureScope) {
        super(1);
        this.$labelPlaceable = placeable;
        this.$width = i10;
        this.$totalHeight = i11;
        this.$textFieldPlaceable = placeable2;
        this.$placeholderPlaceable = placeable3;
        this.$leadingPlaceable = placeable4;
        this.$trailingPlaceable = placeable5;
        this.$prefixPlaceable = placeable6;
        this.$suffixPlaceable = placeable7;
        this.$containerPlaceable = placeable8;
        this.$supportingPlaceable = placeable9;
        this.this$0 = textFieldMeasurePolicy;
        this.$topPaddingValue = i12;
        this.$this_measure = measureScope;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Placeable.PlacementScope) obj);
        return t0.f22605a;
    }

    public final void invoke(Placeable.PlacementScope placementScope) {
        Placeable placeable = this.$labelPlaceable;
        if (placeable == null) {
            TextFieldKt.placeWithoutLabel(placementScope, this.$width, this.$totalHeight, this.$textFieldPlaceable, this.$placeholderPlaceable, this.$leadingPlaceable, this.$trailingPlaceable, this.$prefixPlaceable, this.$suffixPlaceable, this.$containerPlaceable, this.$supportingPlaceable, this.this$0.singleLine, this.$this_measure.getDensity(), this.this$0.paddingValues);
            return;
        }
        int i10 = this.$width;
        int i11 = this.$totalHeight;
        Placeable placeable2 = this.$textFieldPlaceable;
        Placeable placeable3 = this.$placeholderPlaceable;
        Placeable placeable4 = this.$leadingPlaceable;
        Placeable placeable5 = this.$trailingPlaceable;
        Placeable placeable6 = this.$prefixPlaceable;
        Placeable placeable7 = this.$suffixPlaceable;
        Placeable placeable8 = this.$containerPlaceable;
        Placeable placeable9 = this.$supportingPlaceable;
        boolean z = this.this$0.singleLine;
        int i12 = this.$topPaddingValue;
        TextFieldKt.placeWithLabel(placementScope, i10, i11, placeable2, placeable, placeable3, placeable4, placeable5, placeable6, placeable7, placeable8, placeable9, z, i12, this.$labelPlaceable.getHeight() + i12, this.this$0.animationProgress, this.$this_measure.getDensity());
    }
}
