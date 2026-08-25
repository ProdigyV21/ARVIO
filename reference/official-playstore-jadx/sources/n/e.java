package n;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.LayoutInflater;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes.dex */
public final class e extends ContextWrapper {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Configuration f20374f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f20375a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Resources.Theme f20376b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LayoutInflater f20377c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Configuration f20378d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Resources f20379e;

    public e(Context context, int i10) {
        super(context);
        this.f20375a = i10;
    }

    public final void a(Configuration configuration) {
        if (this.f20379e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f20378d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f20378d = new Configuration(configuration);
    }

    @Override // android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public final void b() {
        if (this.f20376b == null) {
            this.f20376b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f20376b.setTo(theme);
            }
        }
        this.f20376b.applyStyle(this.f20375a, true);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final AssetManager getAssets() {
        return getResources().getAssets();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    @Override // android.content.ContextWrapper, android.content.Context
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.content.res.Resources getResources() {
        /*
            r3 = this;
            android.content.res.Resources r0 = r3.f20379e
            if (r0 != 0) goto L38
            android.content.res.Configuration r0 = r3.f20378d
            if (r0 == 0) goto L32
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 26
            if (r1 < r2) goto L25
            android.content.res.Configuration r1 = n.e.f20374f
            if (r1 != 0) goto L1c
            android.content.res.Configuration r1 = new android.content.res.Configuration
            r1.<init>()
            r2 = 0
            r1.fontScale = r2
            n.e.f20374f = r1
        L1c:
            android.content.res.Configuration r1 = n.e.f20374f
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L25
            goto L32
        L25:
            android.content.res.Configuration r0 = r3.f20378d
            android.content.Context r0 = n.d.a(r3, r0)
            android.content.res.Resources r0 = r0.getResources()
            r3.f20379e = r0
            goto L38
        L32:
            android.content.res.Resources r0 = super.getResources()
            r3.f20379e = r0
        L38:
            android.content.res.Resources r0 = r3.f20379e
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: n.e.getResources():android.content.res.Resources");
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.f20377c == null) {
            this.f20377c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f20377c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Resources.Theme getTheme() {
        Resources.Theme theme = this.f20376b;
        if (theme != null) {
            return theme;
        }
        if (this.f20375a == 0) {
            this.f20375a = R.style.Theme_AppCompat_Light;
        }
        b();
        return this.f20376b;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final void setTheme(int i10) {
        if (this.f20375a != i10) {
            this.f20375a = i10;
            b();
        }
    }
}
