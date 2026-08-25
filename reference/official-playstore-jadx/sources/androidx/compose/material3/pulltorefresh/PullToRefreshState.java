package androidx.compose.material3.pulltorefresh;

import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0004R\u0014\u0010\t\u001a\u00020\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\u00068gX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0014\u0010\r\u001a\u00020\f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u00068gX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\bR\u001c\u0010\u0016\u001a\u00020\u00118&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0017À\u0006\u0001"}, d2 = {"Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "", "Lx6/t0;", "startRefresh", "()V", "endRefresh", "", "getPositionalThreshold", "()F", "positionalThreshold", "getProgress", "progress", "", "isRefreshing", "()Z", "getVerticalOffset", "verticalOffset", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "getNestedScrollConnection", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "setNestedScrollConnection", "(Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;)V", "nestedScrollConnection", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface PullToRefreshState {
    void endRefresh();

    NestedScrollConnection getNestedScrollConnection();

    float getPositionalThreshold();

    float getProgress();

    float getVerticalOffset();

    boolean isRefreshing();

    void setNestedScrollConnection(NestedScrollConnection nestedScrollConnection);

    void startRefresh();
}
