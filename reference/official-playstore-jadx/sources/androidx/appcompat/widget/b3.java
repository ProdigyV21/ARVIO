package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;

/* JADX INFO: loaded from: classes.dex */
public final class b3 extends g2 {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f1512w;
    public final int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public x2 f1513y;
    public androidx.appcompat.view.menu.t z;

    public b3(Context context, boolean z) {
        super(context, z);
        if (1 == a3.a(context.getResources().getConfiguration())) {
            this.f1512w = 21;
            this.x = 22;
        } else {
            this.f1512w = 22;
            this.x = 21;
        }
    }

    @Override // androidx.appcompat.widget.g2, android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        androidx.appcompat.view.menu.n nVar;
        int headersCount;
        int iPointToPosition;
        int i10;
        if (this.f1513y != null) {
            ListAdapter adapter = getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                headersCount = headerViewListAdapter.getHeadersCount();
                nVar = (androidx.appcompat.view.menu.n) headerViewListAdapter.getWrappedAdapter();
            } else {
                nVar = (androidx.appcompat.view.menu.n) adapter;
                headersCount = 0;
            }
            androidx.appcompat.view.menu.t tVarC = (motionEvent.getAction() == 10 || (iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) == -1 || (i10 = iPointToPosition - headersCount) < 0 || i10 >= nVar.getCount()) ? null : nVar.getItem(i10);
            androidx.appcompat.view.menu.t tVar = this.z;
            if (tVar != tVarC) {
                androidx.appcompat.view.menu.q qVar = nVar.f1335i;
                if (tVar != null) {
                    this.f1513y.z(qVar, tVar);
                }
                this.z = tVarC;
                if (tVarC != null) {
                    this.f1513y.c(qVar, tVarC);
                }
            }
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i10, KeyEvent keyEvent) {
        ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
        if (listMenuItemView != null && i10 == this.f1512w) {
            if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
            }
            return true;
        }
        if (listMenuItemView == null || i10 != this.x) {
            return super.onKeyDown(i10, keyEvent);
        }
        setSelection(-1);
        ListAdapter adapter = getAdapter();
        (adapter instanceof HeaderViewListAdapter ? (androidx.appcompat.view.menu.n) ((HeaderViewListAdapter) adapter).getWrappedAdapter() : (androidx.appcompat.view.menu.n) adapter).f1335i.c(false);
        return true;
    }

    public void setHoverListener(x2 x2Var) {
        this.f1513y = x2Var;
    }

    @Override // androidx.appcompat.widget.g2, android.widget.AbsListView
    public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
        super.setSelector(drawable);
    }
}
