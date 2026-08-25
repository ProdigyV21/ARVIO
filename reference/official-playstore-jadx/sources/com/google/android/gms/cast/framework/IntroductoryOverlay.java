package com.google.android.gms.cast.framework;

import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import androidx.mediarouter.app.b;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.internal.cast.l6;
import com.google.android.gms.internal.cast.m5;
import com.google.android.gms.internal.cast.o0;

/* JADX INFO: loaded from: classes4.dex */
public interface IntroductoryOverlay {

    public static class Builder {
        private final Activity zza;
        private final View zzb;
        private int zzc;
        private String zzd;
        private OnOverlayDismissedListener zze;
        private boolean zzf;

        public Builder(Activity activity, MenuItem menuItem) {
            t.i(activity);
            this.zza = activity;
            t.i(menuItem);
            this.zzb = menuItem.getActionView();
        }

        public IntroductoryOverlay build() {
            l6.a(m5.INSTRUCTIONS_VIEW);
            return new o0(this);
        }

        public Builder setButtonText(String str) {
            return this;
        }

        public Builder setFocusRadius(float f10) {
            return this;
        }

        public Builder setFocusRadiusId(int i10) {
            this.zza.getResources().getDimension(i10);
            return this;
        }

        public Builder setOnOverlayDismissedListener(OnOverlayDismissedListener onOverlayDismissedListener) {
            this.zze = onOverlayDismissedListener;
            return this;
        }

        public Builder setOverlayColor(int i10) {
            this.zzc = this.zza.getResources().getColor(i10);
            return this;
        }

        public Builder setSingleTime() {
            this.zzf = true;
            return this;
        }

        public Builder setTitleText(String str) {
            this.zzd = str;
            return this;
        }

        public final Activity zza() {
            return this.zza;
        }

        public final View zzb() {
            return this.zzb;
        }

        public final OnOverlayDismissedListener zzc() {
            return this.zze;
        }

        public final int zzd() {
            return this.zzc;
        }

        public final boolean zze() {
            return this.zzf;
        }

        public final String zzf() {
            return this.zzd;
        }

        public Builder setButtonText(int i10) {
            this.zza.getResources().getString(i10);
            return this;
        }

        public Builder setTitleText(int i10) {
            this.zzd = this.zza.getResources().getString(i10);
            return this;
        }

        public Builder(Activity activity, b bVar) {
            t.i(activity);
            this.zza = activity;
            t.i(bVar);
            throw null;
        }
    }

    public interface OnOverlayDismissedListener {
        void onOverlayDismissed();
    }

    void remove();

    void show();
}
