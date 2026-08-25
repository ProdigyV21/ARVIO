package com.google.android.gms.internal.cast;

import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.zza;
import com.google.android.gms.cast.framework.media.widget.CastSeekBar;

/* JADX INFO: loaded from: classes4.dex */
public final class i2 extends e2 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final RelativeLayout f13305b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final TextView f13306c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final CastSeekBar f13307d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final zza f13308e;

    public i2(RelativeLayout relativeLayout, CastSeekBar castSeekBar, zza zzaVar) {
        this.f13305b = relativeLayout;
        TextView textView = (TextView) relativeLayout.findViewById(R.id.tooltip);
        this.f13306c = textView;
        this.f13307d = castSeekBar;
        this.f13308e = zzaVar;
        TypedArray typedArrayObtainStyledAttributes = textView.getContext().obtainStyledAttributes(null, R.styleable.CastExpandedController, R.attr.castExpandedControllerStyle, R.style.CastExpandedController);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastExpandedController_castSeekBarTooltipBackgroundColor, 0);
        typedArrayObtainStyledAttributes.recycle();
        textView.getBackground().setColorFilter(textView.getContext().getResources().getColor(resourceId), PorterDuff.Mode.SRC_IN);
    }

    @Override // com.google.android.gms.internal.cast.e2
    public final void a(long j10) {
        c();
    }

    @Override // com.google.android.gms.internal.cast.e2
    public final void b(boolean z) {
        this.f13237a = z;
        c();
    }

    public final void c() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        RelativeLayout relativeLayout = this.f13305b;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || this.f13237a) {
            relativeLayout.setVisibility(8);
            return;
        }
        relativeLayout.setVisibility(0);
        CastSeekBar castSeekBar = this.f13307d;
        long progress = castSeekBar.getProgress();
        zza zzaVar = this.f13308e;
        String strZzm = zzaVar.zzm(zzaVar.zzh() + progress);
        TextView textView = this.f13306c;
        textView.setText(strZzm);
        int measuredWidth = (castSeekBar.getMeasuredWidth() - castSeekBar.getPaddingLeft()) - castSeekBar.getPaddingRight();
        textView.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
        int measuredWidth2 = textView.getMeasuredWidth();
        int iMin = Math.min(Math.max(0, ((int) ((((double) castSeekBar.getProgress()) / ((double) castSeekBar.getMaxProgress())) * ((double) measuredWidth))) - (measuredWidth2 / 2)), measuredWidth - measuredWidth2);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.leftMargin = iMin;
        textView.setLayoutParams(layoutParams);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        c();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        c();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        super.onSessionEnded();
        c();
    }
}
