package androidx.compose.foundation.layout;

import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/layout/DoNothingNestedScrollConnection;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "()V", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class DoNothingNestedScrollConnection implements NestedScrollConnection {
    public static final DoNothingNestedScrollConnection INSTANCE = new DoNothingNestedScrollConnection();

    private DoNothingNestedScrollConnection() {
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    public final /* synthetic */ Object mo368onPostFlingRZ2iAVY(long j10, long j11, d7.d dVar) {
        return androidx.compose.ui.input.nestedscroll.a.a(this, j10, j11, dVar);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public final /* synthetic */ long mo369onPostScrollDzOQY0M(long j10, long j11, int i10) {
        return androidx.compose.ui.input.nestedscroll.a.b(this, j10, j11, i10);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreFling-QWom1Mo */
    public final /* synthetic */ Object mo370onPreFlingQWom1Mo(long j10, d7.d dVar) {
        return androidx.compose.ui.input.nestedscroll.a.c(this, j10, dVar);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
    public final /* synthetic */ long mo371onPreScrollOzD1aCk(long j10, int i10) {
        return androidx.compose.ui.input.nestedscroll.a.d(this, j10, i10);
    }
}
