package android.view;

import android.view.View;
import android.view.ViewSizeResolver;
import d7.d;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\r\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcoil/size/RealViewSizeResolver;", "T", "Landroid/view/View;", "Lcoil/size/ViewSizeResolver;", "view", "subtractPadding", "", "(Landroid/view/View;Z)V", "getSubtractPadding", "()Z", "getView", "()Landroid/view/View;", "Landroid/view/View;", "equals", "other", "", "hashCode", "", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RealViewSizeResolver<T extends View> implements ViewSizeResolver<T> {
    private final boolean subtractPadding;
    private final T view;

    public RealViewSizeResolver(T t2, boolean z) {
        this.view = t2;
        this.subtractPadding = z;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RealViewSizeResolver)) {
            return false;
        }
        RealViewSizeResolver realViewSizeResolver = (RealViewSizeResolver) other;
        return p.a(getView(), realViewSizeResolver.getView()) && getSubtractPadding() == realViewSizeResolver.getSubtractPadding();
    }

    @Override // android.view.ViewSizeResolver
    public boolean getSubtractPadding() {
        return this.subtractPadding;
    }

    @Override // android.view.ViewSizeResolver
    public T getView() {
        return this.view;
    }

    public int hashCode() {
        return (getView().hashCode() * 31) + (getSubtractPadding() ? 1231 : 1237);
    }

    @Override // android.view.ViewSizeResolver, android.view.SizeResolver
    public final /* synthetic */ Object size(d dVar) {
        return ViewSizeResolver.CC.b(this, dVar);
    }
}
