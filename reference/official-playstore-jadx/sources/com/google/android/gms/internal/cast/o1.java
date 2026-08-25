package com.google.android.gms.internal.cast;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
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
public final class o1 extends UIController {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ImageView f13504a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ImageHints f13505b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Bitmap f13506c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ImagePicker f13507d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final zzb f13508e;

    public o1(ImageView imageView, Context context, ImageHints imageHints, int i10) {
        CastMediaOptions castMediaOptions;
        zzb zzbVar = new zzb(context.getApplicationContext());
        this.f13504a = imageView;
        this.f13505b = imageHints;
        this.f13506c = BitmapFactory.decodeResource(context.getResources(), i10);
        CastContext castContextZza = CastContext.zza(context);
        ImagePicker imagePicker = null;
        if (castContextZza != null && (castMediaOptions = castContextZza.getCastOptions().getCastMediaOptions()) != null) {
            imagePicker = castMediaOptions.getImagePicker();
        }
        this.f13507d = imagePicker;
        this.f13508e = zzbVar;
    }

    public final void a() {
        MediaInfo media;
        r3.b bVarOnPickImage;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        Bitmap bitmap = this.f13506c;
        ImageView imageView = this.f13504a;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        MediaQueueItem preloadedItem = remoteMediaClient.getPreloadedItem();
        Uri imageUri = null;
        if (preloadedItem != null && (media = preloadedItem.getMedia()) != null) {
            MediaMetadata metadata = media.getMetadata();
            ImagePicker imagePicker = this.f13507d;
            if (imagePicker == null || metadata == null || (bVarOnPickImage = imagePicker.onPickImage(metadata, this.f13505b)) == null || (imageUri = bVarOnPickImage.f21388l) == null) {
                imageUri = MediaUtils.getImageUri(media, 0);
            }
        }
        if (imageUri == null) {
            imageView.setImageBitmap(bitmap);
        } else {
            this.f13508e.zzb(imageUri);
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        a();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        this.f13508e.zza(new ja(this, 4));
        this.f13504a.setImageBitmap(this.f13506c);
        a();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.f13508e.zzc();
        this.f13504a.setImageBitmap(this.f13506c);
        super.onSessionEnded();
    }
}
