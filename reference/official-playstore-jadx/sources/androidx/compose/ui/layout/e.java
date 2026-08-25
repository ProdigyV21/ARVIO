package androidx.compose.ui.layout;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class e {
    public static int a(LayoutModifier layoutModifier, IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return MeasuringIntrinsics.INSTANCE.maxHeight(layoutModifier, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    public static int b(LayoutModifier layoutModifier, IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return MeasuringIntrinsics.INSTANCE.maxWidth(layoutModifier, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    public static int c(LayoutModifier layoutModifier, IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return MeasuringIntrinsics.INSTANCE.minHeight(layoutModifier, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    public static int d(LayoutModifier layoutModifier, IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return MeasuringIntrinsics.INSTANCE.minWidth(layoutModifier, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }
}
