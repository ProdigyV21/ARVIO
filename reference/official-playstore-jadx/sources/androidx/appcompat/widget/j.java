package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.ActionMenuView;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes.dex */
public final class j extends g0 implements ActionMenuView.a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ m f1602n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(m mVar, Context context) {
        super(context, null, R.attr.actionOverflowButtonStyle);
        this.f1602n = mVar;
        setClickable(true);
        setFocusable(true);
        setVisibility(0);
        setEnabled(true);
        kotlin.reflect.b0.E(this, getContentDescription());
        setOnTouchListener(new androidx.appcompat.view.menu.b(this, this));
    }

    @Override // androidx.appcompat.widget.ActionMenuView.a
    public final boolean a() {
        return false;
    }

    @Override // androidx.appcompat.widget.ActionMenuView.a
    public final boolean b() {
        return false;
    }

    @Override // android.view.View
    public final boolean performClick() {
        if (super.performClick()) {
            return true;
        }
        playSoundEffect(0);
        this.f1602n.l();
        return true;
    }

    @Override // android.widget.ImageView
    public final boolean setFrame(int i10, int i11, int i12, int i13) {
        boolean frame = super.setFrame(i10, i11, i12, i13);
        Drawable drawable = getDrawable();
        Drawable background = getBackground();
        if (drawable != null && background != null) {
            int width = getWidth();
            int height = getHeight();
            int iMax = Math.max(width, height) / 2;
            int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
            int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
            j0.a.f(background, paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
        }
        return frame;
    }
}
