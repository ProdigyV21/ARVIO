package i0;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;

/* JADX INFO: loaded from: classes3.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ColorStateList f15999a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Configuration f16000b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f16001c;

    public m(ColorStateList colorStateList, Configuration configuration, Resources.Theme theme) {
        this.f15999a = colorStateList;
        this.f16000b = configuration;
        this.f16001c = theme == null ? 0 : theme.hashCode();
    }
}
