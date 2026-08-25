package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public TypedValue f1442i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public TypedValue f1443l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public TypedValue f1444m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public TypedValue f1445n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public TypedValue f1446o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public TypedValue f1447p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Rect f1448q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public a f1449r;

    public interface a {
        void onDetachedFromWindow();
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f1448q = new Rect();
    }

    public TypedValue getFixedHeightMajor() {
        if (this.f1446o == null) {
            this.f1446o = new TypedValue();
        }
        return this.f1446o;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.f1447p == null) {
            this.f1447p = new TypedValue();
        }
        return this.f1447p;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.f1444m == null) {
            this.f1444m = new TypedValue();
        }
        return this.f1444m;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.f1445n == null) {
            this.f1445n = new TypedValue();
        }
        return this.f1445n;
    }

    public TypedValue getMinWidthMajor() {
        if (this.f1442i == null) {
            this.f1442i = new TypedValue();
        }
        return this.f1442i;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f1443l == null) {
            this.f1443l = new TypedValue();
        }
        return this.f1443l;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a aVar = this.f1449r;
        if (aVar != null) {
            aVar.onDetachedFromWindow();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00de  */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMeasure(int r17, int r18) {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ContentFrameLayout.onMeasure(int, int):void");
    }

    public void setAttachListener(a aVar) {
        this.f1449r = aVar;
    }
}
