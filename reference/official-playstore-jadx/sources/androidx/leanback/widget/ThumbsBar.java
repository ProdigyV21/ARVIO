package androidx.leanback.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes3.dex */
public class ThumbsBar extends LinearLayout {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3045i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f3046l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f3047m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f3048n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f3049o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f3050p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f3051q;

    public ThumbsBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f3045i = -1;
        new SparseArray();
        this.f3051q = false;
        this.f3046l = context.getResources().getDimensionPixelSize(R.dimen.lb_playback_transport_thumbs_width);
        this.f3047m = context.getResources().getDimensionPixelSize(R.dimen.lb_playback_transport_thumbs_height);
        this.f3049o = context.getResources().getDimensionPixelSize(R.dimen.lb_playback_transport_hero_thumbs_width);
        this.f3048n = context.getResources().getDimensionPixelSize(R.dimen.lb_playback_transport_hero_thumbs_height);
        this.f3050p = context.getResources().getDimensionPixelSize(R.dimen.lb_playback_transport_thumbs_margin);
    }

    public final void a() {
        int i10;
        int i11;
        while (getChildCount() > this.f3045i) {
            removeView(getChildAt(getChildCount() - 1));
        }
        while (true) {
            int childCount = getChildCount();
            int i12 = this.f3045i;
            i10 = this.f3047m;
            i11 = this.f3046l;
            if (childCount >= i12) {
                break;
            } else {
                addView(new ImageView(getContext()), new LinearLayout.LayoutParams(i11, i10));
            }
        }
        int heroIndex = getHeroIndex();
        for (int i13 = 0; i13 < getChildCount(); i13++) {
            View childAt = getChildAt(i13);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            if (heroIndex == i13) {
                layoutParams.width = this.f3048n;
                layoutParams.height = this.f3049o;
            } else {
                layoutParams.width = i11;
                layoutParams.height = i10;
            }
            childAt.setLayoutParams(layoutParams);
        }
    }

    public int getHeroIndex() {
        return getChildCount() / 2;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i10, int i11, int i12, int i13) {
        super.onLayout(z, i10, i11, i12, i13);
        int heroIndex = getHeroIndex();
        View childAt = getChildAt(heroIndex);
        int width = (getWidth() / 2) - (childAt.getMeasuredWidth() / 2);
        int measuredWidth = (childAt.getMeasuredWidth() / 2) + (getWidth() / 2);
        childAt.layout(width, getPaddingTop(), measuredWidth, childAt.getMeasuredHeight() + getPaddingTop());
        int measuredHeight = (childAt.getMeasuredHeight() / 2) + getPaddingTop();
        for (int i14 = heroIndex - 1; i14 >= 0; i14--) {
            int i15 = width - this.f3050p;
            View childAt2 = getChildAt(i14);
            childAt2.layout(i15 - childAt2.getMeasuredWidth(), measuredHeight - (childAt2.getMeasuredHeight() / 2), i15, (childAt2.getMeasuredHeight() / 2) + measuredHeight);
            width = i15 - childAt2.getMeasuredWidth();
        }
        while (true) {
            heroIndex++;
            if (heroIndex >= this.f3045i) {
                return;
            }
            int i16 = measuredWidth + this.f3050p;
            View childAt3 = getChildAt(heroIndex);
            childAt3.layout(i16, measuredHeight - (childAt3.getMeasuredHeight() / 2), childAt3.getMeasuredWidth() + i16, (childAt3.getMeasuredHeight() / 2) + measuredHeight);
            measuredWidth = i16 + childAt3.getMeasuredWidth();
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i10, int i11) {
        super.onMeasure(i10, i11);
        int measuredWidth = getMeasuredWidth();
        if (this.f3051q) {
            return;
        }
        int i12 = measuredWidth - this.f3048n;
        int i13 = ((i12 + r3) - 1) / (this.f3046l + this.f3050p);
        if (i13 < 2) {
            i13 = 2;
        } else if ((i13 & 1) != 0) {
            i13++;
        }
        int i14 = i13 + 1;
        if (this.f3045i != i14) {
            this.f3045i = i14;
            a();
        }
    }

    public void setNumberOfThumbs(int i10) {
        this.f3051q = true;
        this.f3045i = i10;
        a();
    }

    public void setThumbSpace(int i10) {
        this.f3050p = i10;
        requestLayout();
    }
}
