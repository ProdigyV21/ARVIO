package l6;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f20038a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f20039b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f20040c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f20041d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f20042e;

    public l(int i10, String str, boolean z, c cVar, ClassLoader classLoader) {
        this.f20038a = i10;
        this.f20040c = str;
        this.f20039b = z;
        this.f20041d = cVar;
        this.f20042e = classLoader;
    }

    public fi.iki.elonen.f a() {
        Intent intent = (Intent) this.f20040c;
        if (!intent.hasExtra("android.support.customtabs.extra.SESSION")) {
            Bundle bundle = new Bundle();
            bundle.putBinder("android.support.customtabs.extra.SESSION", null);
            intent.putExtras(bundle);
        }
        intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", this.f20039b);
        ((io.sentry.util.l) this.f20041d).getClass();
        intent.putExtras(new Bundle());
        intent.putExtra("androidx.browser.customtabs.extra.SHARE_STATE", this.f20038a);
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 24) {
            String strA = r.d.a();
            if (!TextUtils.isEmpty(strA)) {
                Bundle bundleExtra = intent.hasExtra("com.android.browser.headers") ? intent.getBundleExtra("com.android.browser.headers") : new Bundle();
                if (!bundleExtra.containsKey("Accept-Language")) {
                    bundleExtra.putString("Accept-Language", strA);
                    intent.putExtra("com.android.browser.headers", bundleExtra);
                }
            }
        }
        if (i10 >= 34) {
            if (((ActivityOptions) this.f20042e) == null) {
                this.f20042e = r.c.a();
            }
            r.e.a((ActivityOptions) this.f20042e, false);
        }
        ActivityOptions activityOptions = (ActivityOptions) this.f20042e;
        return new fi.iki.elonen.f(intent, activityOptions != null ? activityOptions.toBundle() : null);
    }

    public l b(boolean z) {
        return this.f20039b == z ? this : new l(this.f20038a, (String) this.f20040c, z, (c) this.f20041d, (ClassLoader) this.f20042e);
    }

    public l c(c cVar) {
        if (((c) this.f20041d) == cVar) {
            return this;
        }
        return new l(this.f20038a, (String) this.f20040c, this.f20039b, cVar, (ClassLoader) this.f20042e);
    }

    public l d(String str) {
        String str2 = (String) this.f20040c;
        if (str2 == str || (str2 != null && str != null && str2.equals(str))) {
            return this;
        }
        return new l(this.f20038a, str, this.f20039b, (c) this.f20041d, (ClassLoader) this.f20042e);
    }

    public l e(int i10) {
        if (this.f20038a == i10) {
            return this;
        }
        return new l(i10, (String) this.f20040c, this.f20039b, (c) this.f20041d, (ClassLoader) this.f20042e);
    }

    public l() {
        this.f20040c = new Intent("android.intent.action.VIEW");
        this.f20041d = new io.sentry.util.l(18);
        this.f20038a = 0;
        this.f20039b = true;
    }
}
