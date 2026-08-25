package androidx.tv.material3;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class u3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f6446a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f6447b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f6448c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f6449d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f6450e;

    public u3(Shape shape, long j10, LayoutDirection layoutDirection, ContentDrawScope contentDrawScope) {
        this.f6447b = shape;
        this.f6446a = j10;
        this.f6448c = layoutDirection;
        this.f6449d = contentDrawScope;
    }

    public void a(androidx.work.impl.l lVar) {
        Runnable runnable;
        synchronized (this.f6449d) {
            runnable = (Runnable) ((LinkedHashMap) this.f6450e).remove(lVar);
        }
        if (runnable != null) {
            ((m2.d0) this.f6447b).a(runnable);
        }
    }

    public void b(androidx.work.impl.l lVar) {
        io.sentry.android.ndk.b bVar = new io.sentry.android.ndk.b(this, lVar, 14);
        synchronized (this.f6449d) {
        }
        ((m2.d0) this.f6447b).b(this.f6446a, bVar);
    }

    public Outline c(Shape shape, long j10, LayoutDirection layoutDirection, ContentDrawScope contentDrawScope) {
        if (((Outline) this.f6450e) == null || !kotlin.jvm.internal.p.a(shape, (Shape) this.f6447b) || !Size.m3301equalsimpl0(j10, this.f6446a) || layoutDirection != ((LayoutDirection) this.f6448c) || !kotlin.jvm.internal.p.a(contentDrawScope, (Density) this.f6449d)) {
            this.f6447b = shape;
            this.f6446a = j10;
            this.f6448c = layoutDirection;
            this.f6449d = contentDrawScope;
            this.f6450e = shape.mo230createOutlinePq9zytI(j10, layoutDirection, contentDrawScope);
        }
        return (Outline) this.f6450e;
    }
}
