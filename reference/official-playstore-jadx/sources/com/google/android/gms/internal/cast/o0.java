package com.google.android.gms.internal.cast;

import android.app.Activity;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.RelativeLayout;
import com.google.android.gms.cast.framework.IntroductoryOverlay;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.internal.featurehighlight.HelpTextView;
import com.google.android.gms.cast.framework.internal.featurehighlight.zzh;

/* JADX INFO: loaded from: classes4.dex */
public final class o0 extends RelativeLayout implements IntroductoryOverlay {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f13497i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Activity f13498l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public IntroductoryOverlay.OnOverlayDismissedListener f13499m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public View f13500n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f13501o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f13502p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f13503q;

    public o0(IntroductoryOverlay.Builder builder) {
        super(builder.zza());
        this.f13498l = builder.zza();
        this.f13497i = builder.zze();
        this.f13499m = builder.zzc();
        this.f13500n = builder.zzb();
        this.f13501o = builder.zzf();
        this.f13503q = builder.zzd();
    }

    public final void a() {
        removeAllViews();
        this.f13498l = null;
        this.f13499m = null;
        this.f13500n = null;
        this.f13501o = null;
        this.f13503q = 0;
        this.f13502p = false;
    }

    @Override // com.google.android.gms.cast.framework.IntroductoryOverlay
    public final void remove() {
        Activity activity;
        if (!this.f13502p || (activity = this.f13498l) == null) {
            return;
        }
        ((ViewGroup) activity.getWindow().getDecorView()).removeView(this);
        a();
    }

    @Override // com.google.android.gms.cast.framework.IntroductoryOverlay
    public final void show() {
        View view;
        Activity activity = this.f13498l;
        if (activity == null || (view = this.f13500n) == null || this.f13502p) {
            return;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) activity.getSystemService("accessibility");
        if (accessibilityManager != null && accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return;
        }
        if (this.f13497i && PreferenceManager.getDefaultSharedPreferences(activity).getBoolean("googlecast-introOverlayShown", false)) {
            a();
            return;
        }
        zzh zzhVar = new zzh(activity);
        int i10 = this.f13503q;
        if (i10 != 0) {
            zzhVar.zzf(i10);
        }
        addView(zzhVar);
        HelpTextView helpTextView = (HelpTextView) activity.getLayoutInflater().inflate(R.layout.cast_help_text, (ViewGroup) zzhVar, false);
        helpTextView.setText(this.f13501o, null);
        zzhVar.zzn(helpTextView);
        zzhVar.zza(view, null, true, new n0(this, activity, zzhVar));
        this.f13502p = true;
        ((ViewGroup) activity.getWindow().getDecorView()).addView(this);
        zzhVar.zzb(null);
    }
}
