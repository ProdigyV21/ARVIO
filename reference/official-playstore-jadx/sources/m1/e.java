package m1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.view.b2;
import androidx.leanback.widget.VerticalGridView;
import androidx.media3.session.MediaUtils;
import com.arvio.tv.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class e extends FrameLayout {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ViewGroup f20152i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList f20153l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ArrayList f20154m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final float f20155n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final float f20156o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final float f20157p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f20158q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final DecelerateInterpolator f20159r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f20160s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f20161t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f20162u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final ArrayList f20163v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f20164w;
    public int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final a f20165y;

    public e(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.datePickerStyle);
        this.f20153l = new ArrayList();
        this.f20160s = 3.0f;
        this.f20161t = 1.0f;
        this.f20162u = 0;
        this.f20163v = new ArrayList();
        this.f20165y = new a(this);
        int[] iArr = k1.a.f19456f;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, R.attr.datePickerStyle, 0);
        b2.g(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, R.attr.datePickerStyle);
        this.f20164w = typedArrayObtainStyledAttributes.getResourceId(0, R.layout.lb_picker_item);
        this.x = typedArrayObtainStyledAttributes.getResourceId(1, 0);
        typedArrayObtainStyledAttributes.recycle();
        setEnabled(true);
        setDescendantFocusability(MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES);
        this.f20156o = 1.0f;
        this.f20155n = 1.0f;
        this.f20157p = 0.5f;
        this.f20158q = 200;
        this.f20159r = new DecelerateInterpolator(2.5f);
        this.f20152i = (ViewGroup) ((ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.lb_picker, (ViewGroup) this, true)).findViewById(R.id.picker);
    }

    public final void a(int i10, f fVar) {
        this.f20154m.set(i10, fVar);
        VerticalGridView verticalGridView = (VerticalGridView) this.f20153l.get(i10);
        b bVar = (b) verticalGridView.getAdapter();
        if (bVar != null) {
            bVar.notifyDataSetChanged();
        }
        verticalGridView.setSelectedPosition(fVar.f20166a - fVar.f20167b);
    }

    public final void b(View view, boolean z, float f10, DecelerateInterpolator decelerateInterpolator) {
        view.animate().cancel();
        if (z) {
            view.animate().alpha(f10).setDuration(this.f20158q).setInterpolator(decelerateInterpolator).start();
        } else {
            view.setAlpha(f10);
        }
    }

    public final void c(View view, boolean z, int i10, boolean z5) {
        boolean z10 = i10 == this.f20162u || !hasFocus();
        DecelerateInterpolator decelerateInterpolator = this.f20159r;
        if (z) {
            if (z10) {
                b(view, z5, this.f20156o, decelerateInterpolator);
                return;
            } else {
                b(view, z5, this.f20155n, decelerateInterpolator);
                return;
            }
        }
        if (z10) {
            b(view, z5, this.f20157p, decelerateInterpolator);
        } else {
            b(view, z5, 0.0f, decelerateInterpolator);
        }
    }

    public final void d(int i10) {
        VerticalGridView verticalGridView = (VerticalGridView) this.f20153l.get(i10);
        int selectedPosition = verticalGridView.getSelectedPosition();
        int i11 = 0;
        while (i11 < verticalGridView.getAdapter().getItemCount()) {
            View viewR = verticalGridView.getLayoutManager().r(i11);
            if (viewR != null) {
                c(viewR, selectedPosition == i11, i10, true);
            }
            i11++;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!isActivated()) {
            return super.dispatchKeyEvent(keyEvent);
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode != 23 && keyCode != 66) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (keyEvent.getAction() == 1) {
            performClick();
        }
        return true;
    }

    public final void e() {
        for (int i10 = 0; i10 < getColumnsCount(); i10++) {
            f((VerticalGridView) this.f20153l.get(i10));
        }
    }

    public final void f(VerticalGridView verticalGridView) {
        ViewGroup.LayoutParams layoutParams = verticalGridView.getLayoutParams();
        float activatedVisibleItemCount = isActivated() ? getActivatedVisibleItemCount() : getVisibleItemCount();
        layoutParams.height = (int) (((activatedVisibleItemCount - 1.0f) * verticalGridView.getVerticalSpacing()) + (getPickerItemHeightPixels() * activatedVisibleItemCount));
        verticalGridView.setLayoutParams(layoutParams);
    }

    public float getActivatedVisibleItemCount() {
        return this.f20160s;
    }

    public int getColumnsCount() {
        ArrayList arrayList = this.f20154m;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public int getPickerItemHeightPixels() {
        return getContext().getResources().getDimensionPixelSize(R.dimen.picker_item_height);
    }

    public final int getPickerItemLayoutId() {
        return this.f20164w;
    }

    public final int getPickerItemTextViewId() {
        return this.x;
    }

    public int getSelectedColumn() {
        return this.f20162u;
    }

    @Deprecated
    public final CharSequence getSeparator() {
        return (CharSequence) this.f20163v.get(0);
    }

    public final List<CharSequence> getSeparators() {
        return this.f20163v;
    }

    public float getVisibleItemCount() {
        return 1.0f;
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i10, Rect rect) {
        int selectedColumn = getSelectedColumn();
        if (selectedColumn < 0) {
            return false;
        }
        ArrayList arrayList = this.f20153l;
        if (selectedColumn < arrayList.size()) {
            return ((VerticalGridView) arrayList.get(selectedColumn)).requestFocus(i10, rect);
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        int i10 = 0;
        while (true) {
            ArrayList arrayList = this.f20153l;
            if (i10 >= arrayList.size()) {
                return;
            }
            if (((VerticalGridView) arrayList.get(i10)).hasFocus()) {
                setSelectedColumn(i10);
            }
            i10++;
        }
    }

    @Override // android.view.View
    public void setActivated(boolean z) {
        ArrayList arrayList;
        if (z == isActivated()) {
            super.setActivated(z);
            return;
        }
        super.setActivated(z);
        boolean zHasFocus = hasFocus();
        int selectedColumn = getSelectedColumn();
        setDescendantFocusability(131072);
        if (!z && zHasFocus && isFocusable()) {
            requestFocus();
        }
        int i10 = 0;
        while (true) {
            int columnsCount = getColumnsCount();
            arrayList = this.f20153l;
            if (i10 >= columnsCount) {
                break;
            }
            ((VerticalGridView) arrayList.get(i10)).setFocusable(z);
            i10++;
        }
        e();
        boolean zIsActivated = isActivated();
        for (int i11 = 0; i11 < getColumnsCount(); i11++) {
            VerticalGridView verticalGridView = (VerticalGridView) arrayList.get(i11);
            for (int i12 = 0; i12 < verticalGridView.getChildCount(); i12++) {
                verticalGridView.getChildAt(i12).setFocusable(zIsActivated);
            }
        }
        if (z && zHasFocus && selectedColumn >= 0) {
            ((VerticalGridView) arrayList.get(selectedColumn)).requestFocus();
        }
        setDescendantFocusability(MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES);
    }

    public void setActivatedVisibleItemCount(float f10) {
        if (f10 <= 0.0f) {
            throw new IllegalArgumentException();
        }
        if (this.f20160s != f10) {
            this.f20160s = f10;
            if (isActivated()) {
                e();
            }
        }
    }

    public void setColumns(List<f> list) {
        ArrayList arrayList = this.f20163v;
        if (arrayList.size() == 0) {
            throw new IllegalStateException("Separators size is: " + arrayList.size() + ". At least one separator must be provided");
        }
        if (arrayList.size() == 1) {
            CharSequence charSequence = (CharSequence) arrayList.get(0);
            arrayList.clear();
            arrayList.add("");
            for (int i10 = 0; i10 < list.size() - 1; i10++) {
                arrayList.add(charSequence);
            }
            arrayList.add("");
        } else if (arrayList.size() != list.size() + 1) {
            throw new IllegalStateException("Separators size: " + arrayList.size() + " mustequal the size of columns: " + list.size() + " + 1");
        }
        ArrayList arrayList2 = this.f20153l;
        arrayList2.clear();
        ViewGroup viewGroup = this.f20152i;
        viewGroup.removeAllViews();
        ArrayList arrayList3 = new ArrayList(list);
        this.f20154m = arrayList3;
        if (this.f20162u > arrayList3.size() - 1) {
            this.f20162u = this.f20154m.size() - 1;
        }
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        int columnsCount = getColumnsCount();
        if (!TextUtils.isEmpty((CharSequence) arrayList.get(0))) {
            TextView textView = (TextView) layoutInflaterFrom.inflate(R.layout.lb_picker_separator, viewGroup, false);
            textView.setText((CharSequence) arrayList.get(0));
            viewGroup.addView(textView);
        }
        int i11 = 0;
        while (i11 < columnsCount) {
            VerticalGridView verticalGridView = (VerticalGridView) layoutInflaterFrom.inflate(R.layout.lb_picker_column, viewGroup, false);
            f(verticalGridView);
            verticalGridView.setWindowAlignment(0);
            verticalGridView.setHasFixedSize(false);
            verticalGridView.setFocusable(isActivated());
            verticalGridView.setItemViewCacheSize(0);
            arrayList2.add(verticalGridView);
            viewGroup.addView(verticalGridView);
            int i12 = i11 + 1;
            if (!TextUtils.isEmpty((CharSequence) arrayList.get(i12))) {
                TextView textView2 = (TextView) layoutInflaterFrom.inflate(R.layout.lb_picker_separator, viewGroup, false);
                textView2.setText((CharSequence) arrayList.get(i12));
                viewGroup.addView(textView2);
            }
            verticalGridView.setAdapter(new b(this, getPickerItemLayoutId(), getPickerItemTextViewId(), i11));
            verticalGridView.setOnChildViewHolderSelectedListener(this.f20165y);
            i11 = i12;
        }
    }

    public final void setPickerItemLayoutId(int i10) {
        this.f20164w = i10;
    }

    public final void setPickerItemTextViewId(int i10) {
        this.x = i10;
    }

    public void setSelectedColumn(int i10) {
        int i11 = this.f20162u;
        ArrayList arrayList = this.f20153l;
        if (i11 != i10) {
            this.f20162u = i10;
            for (int i12 = 0; i12 < arrayList.size(); i12++) {
                d(i12);
            }
        }
        VerticalGridView verticalGridView = (VerticalGridView) arrayList.get(i10);
        if (!hasFocus() || verticalGridView.hasFocus()) {
            return;
        }
        verticalGridView.requestFocus();
    }

    public final void setSeparator(CharSequence charSequence) {
        setSeparators(Arrays.asList(charSequence));
    }

    public final void setSeparators(List<CharSequence> list) {
        ArrayList arrayList = this.f20163v;
        arrayList.clear();
        arrayList.addAll(list);
    }

    public void setVisibleItemCount(float f10) {
        if (f10 <= 0.0f) {
            throw new IllegalArgumentException();
        }
        if (this.f20161t != f10) {
            this.f20161t = f10;
            if (isActivated()) {
                return;
            }
            e();
        }
    }
}
