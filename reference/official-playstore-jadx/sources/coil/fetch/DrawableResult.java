package coil.fetch;

import android.graphics.DataSource;
import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ$\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007J\u0013\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\r¨\u0006\u0014"}, d2 = {"Lcoil/fetch/DrawableResult;", "Lcoil/fetch/FetchResult;", "drawable", "Landroid/graphics/drawable/Drawable;", "isSampled", "", "dataSource", "Lcoil/decode/DataSource;", "(Landroid/graphics/drawable/Drawable;ZLcoil/decode/DataSource;)V", "getDataSource", "()Lcoil/decode/DataSource;", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "()Z", "copy", "equals", "other", "", "hashCode", "", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DrawableResult extends FetchResult {
    private final DataSource dataSource;
    private final Drawable drawable;
    private final boolean isSampled;

    public DrawableResult(Drawable drawable, boolean z, DataSource dataSource) {
        super(null);
        this.drawable = drawable;
        this.isSampled = z;
        this.dataSource = dataSource;
    }

    public static /* synthetic */ DrawableResult copy$default(DrawableResult drawableResult, Drawable drawable, boolean z, DataSource dataSource, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            drawable = drawableResult.drawable;
        }
        if ((i10 & 2) != 0) {
            z = drawableResult.isSampled;
        }
        if ((i10 & 4) != 0) {
            dataSource = drawableResult.dataSource;
        }
        return drawableResult.copy(drawable, z, dataSource);
    }

    public final DrawableResult copy(Drawable drawable, boolean isSampled, DataSource dataSource) {
        return new DrawableResult(drawable, isSampled, dataSource);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DrawableResult)) {
            return false;
        }
        DrawableResult drawableResult = (DrawableResult) other;
        return p.a(this.drawable, drawableResult.drawable) && this.isSampled == drawableResult.isSampled && this.dataSource == drawableResult.dataSource;
    }

    public final DataSource getDataSource() {
        return this.dataSource;
    }

    public final Drawable getDrawable() {
        return this.drawable;
    }

    public int hashCode() {
        return this.dataSource.hashCode() + (((this.drawable.hashCode() * 31) + (this.isSampled ? 1231 : 1237)) * 31);
    }

    /* JADX INFO: renamed from: isSampled, reason: from getter */
    public final boolean getIsSampled() {
        return this.isSampled;
    }
}
