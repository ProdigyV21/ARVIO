package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.i1;

/* JADX INFO: loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements p, e0, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final int[] f1231l = {R.attr.background, R.attr.divider};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public q f1232i;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        i1 i1VarZ = i1.Z(context, attributeSet, f1231l, R.attr.listViewStyle);
        TypedArray typedArray = (TypedArray) i1VarZ.f1060m;
        if (typedArray.hasValue(0)) {
            setBackgroundDrawable(i1VarZ.T(0));
        }
        if (typedArray.hasValue(1)) {
            setDivider(i1VarZ.T(1));
        }
        i1VarZ.b0();
    }

    @Override // androidx.appcompat.view.menu.e0
    public final void a(q qVar) {
        this.f1232i = qVar;
    }

    @Override // androidx.appcompat.view.menu.p
    public final boolean b(t tVar) {
        return this.f1232i.q(tVar, null, 0);
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i10, long j10) {
        b((t) getAdapter().getItem(i10));
    }
}
