package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.appcompat.widget.i2;

/* JADX INFO: loaded from: classes.dex */
public class FitWindowsFrameLayout extends FrameLayout implements i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public i2.a f1450i;

    public FitWindowsFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final boolean fitSystemWindows(Rect rect) {
        i2.a aVar = this.f1450i;
        if (aVar == null) {
            return super.fitSystemWindows(rect);
        }
        aVar.a();
        throw null;
    }

    public void setOnFitSystemWindowsListener(i2.a aVar) {
        this.f1450i = aVar;
    }
}
