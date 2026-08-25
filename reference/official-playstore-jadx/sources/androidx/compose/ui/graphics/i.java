package androidx.compose.ui.graphics;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class i {
    public static /* synthetic */ boolean a(PathMeasure pathMeasure, float f10, float f11, Path path, boolean z, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getSegment");
        }
        if ((i10 & 8) != 0) {
            z = true;
        }
        return pathMeasure.getSegment(f10, f11, path, z);
    }
}
