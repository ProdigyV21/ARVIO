package androidx.leanback.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
class ControlBar extends LinearLayout {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2941i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f2942l;

    public interface a {
    }

    public ControlBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2941i = -1;
        this.f2942l = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList arrayList, int i10, int i11) {
        if (i10 != 33 && i10 != 130) {
            super.addFocusables(arrayList, i10, i11);
            return;
        }
        int i12 = this.f2941i;
        if (i12 >= 0 && i12 < getChildCount()) {
            arrayList.add(getChildAt(this.f2941i));
        } else if (getChildCount() > 0) {
            arrayList.add(getChildAt(this.f2942l ? getChildCount() / 2 : 0));
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i10, int i11) {
        super.onMeasure(i10, i11);
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i10, Rect rect) {
        if (getChildCount() > 0) {
            int i11 = this.f2941i;
            if (getChildAt((i11 < 0 || i11 >= getChildCount()) ? this.f2942l ? getChildCount() / 2 : 0 : this.f2941i).requestFocus(i10, rect)) {
                return true;
            }
        }
        return super.onRequestFocusInDescendants(i10, rect);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        this.f2941i = indexOfChild(view);
    }
}
