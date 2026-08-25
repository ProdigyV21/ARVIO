package com.google.android.gms.internal.cast;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.cast.framework.media.MediaUtils;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.internal.zzb;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: loaded from: classes4.dex */
public final class r1 extends UIController {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ImageView f13575a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ImageHints f13576b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Bitmap f13577c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final View f13578d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ImagePicker f13579e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final p1 f13580f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final zzb f13581g;

    public r1(ImageView imageView, Context context, ImageHints imageHints, int i10, View view, p1 p1Var) {
        CastMediaOptions castMediaOptions;
        this.f13575a = imageView;
        this.f13576b = imageHints;
        this.f13580f = p1Var;
        ImagePicker imagePicker = null;
        this.f13577c = i10 != 0 ? BitmapFactory.decodeResource(context.getResources(), i10) : null;
        this.f13578d = view;
        CastContext castContextZza = CastContext.zza(context);
        if (castContextZza != null && (castMediaOptions = castContextZza.getCastOptions().getCastMediaOptions()) != null) {
            imagePicker = castMediaOptions.getImagePicker();
        }
        this.f13579e = imagePicker;
        this.f13581g = new zzb(context.getApplicationContext());
    }

    public final void a() {
        Uri imageUri;
        r3.b bVarOnPickImage;
        Uri uri;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            b();
            return;
        }
        MediaInfo mediaInfo = remoteMediaClient.getMediaInfo();
        if (mediaInfo == null) {
            imageUri = null;
        } else {
            MediaMetadata metadata = mediaInfo.getMetadata();
            ImagePicker imagePicker = this.f13579e;
            imageUri = (imagePicker == null || metadata == null || (bVarOnPickImage = imagePicker.onPickImage(metadata, this.f13576b)) == null || (uri = bVarOnPickImage.f21388l) == null) ? MediaUtils.getImageUri(mediaInfo, 0) : uri;
        }
        if (imageUri == null) {
            b();
        } else {
            this.f13581g.zzb(imageUri);
        }
    }

    public final void b() {
        ImageView imageView = this.f13575a;
        View view = this.f13578d;
        if (view != null) {
            view.setVisibility(0);
            imageView.setVisibility(4);
        }
        Bitmap bitmap = this.f13577c;
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        a();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        this.f13581g.zza(new ja(this, 5));
        b();
        a();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.f13581g.zzc();
        b();
        super.onSessionEnded();
    }
}
