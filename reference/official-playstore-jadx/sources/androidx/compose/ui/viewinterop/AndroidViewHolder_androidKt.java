package androidx.compose.ui.viewinterop;

import android.view.View;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.LayoutNode;
import d7.d;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000-\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0004*\u0001\u0012\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0013\u0010\b\u001a\u00020\u0007*\u00020\u0006H\u0002¢\u0006\u0004\b\b\u0010\t\u001a\u0013\u0010\n\u001a\u00020\u0007*\u00020\u0007H\u0002¢\u0006\u0004\b\n\u0010\u000b\u001a\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000e\u0010\u000f\"\u0014\u0010\u0010\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011\"\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroid/view/View;", "Landroidx/compose/ui/node/LayoutNode;", "layoutNode", "Lx6/t0;", "layoutAccordingTo", "(Landroid/view/View;Landroidx/compose/ui/node/LayoutNode;)V", "", "", "toComposeOffset", "(I)F", "toComposeVelocity", "(F)F", LinkHeader.Parameters.Type, "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "toNestedScrollSource", "(I)I", "Unmeasured", "I", "androidx/compose/ui/viewinterop/AndroidViewHolder_androidKt$NoOpScrollConnection$1", "NoOpScrollConnection", "Landroidx/compose/ui/viewinterop/AndroidViewHolder_androidKt$NoOpScrollConnection$1;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidViewHolder_androidKt {
    private static final AndroidViewHolder_androidKt$NoOpScrollConnection$1 NoOpScrollConnection = new NestedScrollConnection() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder_androidKt$NoOpScrollConnection$1
        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
        public final /* synthetic */ Object mo368onPostFlingRZ2iAVY(long j10, long j11, d dVar) {
            return androidx.compose.ui.input.nestedscroll.a.a(this, j10, j11, dVar);
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
        public final /* synthetic */ long mo369onPostScrollDzOQY0M(long j10, long j11, int i10) {
            return androidx.compose.ui.input.nestedscroll.a.b(this, j10, j11, i10);
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPreFling-QWom1Mo */
        public final /* synthetic */ Object mo370onPreFlingQWom1Mo(long j10, d dVar) {
            return androidx.compose.ui.input.nestedscroll.a.c(this, j10, dVar);
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
        public final /* synthetic */ long mo371onPreScrollOzD1aCk(long j10, int i10) {
            return androidx.compose.ui.input.nestedscroll.a.d(this, j10, i10);
        }
    };
    private static final int Unmeasured = Integer.MIN_VALUE;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void layoutAccordingTo(View view, LayoutNode layoutNode) {
        long jPositionInRoot = LayoutCoordinatesKt.positionInRoot(layoutNode.getCoordinates());
        int iM = t7.a.M(Offset.m3236getXimpl(jPositionInRoot));
        int iM2 = t7.a.M(Offset.m3237getYimpl(jPositionInRoot));
        view.layout(iM, iM2, view.getMeasuredWidth() + iM, view.getMeasuredHeight() + iM2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float toComposeOffset(int i10) {
        return i10 * (-1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float toComposeVelocity(float f10) {
        return f10 * (-1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int toNestedScrollSource(int i10) {
        return i10 == 0 ? NestedScrollSource.INSTANCE.m4425getDragWNlRxjI() : NestedScrollSource.INSTANCE.m4426getFlingWNlRxjI();
    }
}
