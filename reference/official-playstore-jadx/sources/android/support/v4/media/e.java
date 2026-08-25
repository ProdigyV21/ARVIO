package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {
    public static MediaDescription a(MediaDescription.Builder builder) {
        return builder.build();
    }

    public static MediaDescription.Builder b() {
        return new MediaDescription.Builder();
    }

    public static CharSequence c(MediaDescription mediaDescription) {
        return mediaDescription.getDescription();
    }

    public static Bundle d(MediaDescription mediaDescription) {
        return mediaDescription.getExtras();
    }

    public static Bitmap e(MediaDescription mediaDescription) {
        return mediaDescription.getIconBitmap();
    }

    public static Uri f(MediaDescription mediaDescription) {
        return mediaDescription.getIconUri();
    }

    public static String g(MediaDescription mediaDescription) {
        return mediaDescription.getMediaId();
    }

    public static CharSequence h(MediaDescription mediaDescription) {
        return mediaDescription.getSubtitle();
    }

    public static CharSequence i(MediaDescription mediaDescription) {
        return mediaDescription.getTitle();
    }

    public static void j(MediaDescription.Builder builder, CharSequence charSequence) {
        builder.setDescription(charSequence);
    }

    public static void k(MediaDescription.Builder builder, Bundle bundle) {
        builder.setExtras(bundle);
    }

    public static void l(MediaDescription.Builder builder, Bitmap bitmap) {
        builder.setIconBitmap(bitmap);
    }

    public static void m(MediaDescription.Builder builder, Uri uri) {
        builder.setIconUri(uri);
    }

    public static void n(MediaDescription.Builder builder, String str) {
        builder.setMediaId(str);
    }

    public static void o(MediaDescription.Builder builder, CharSequence charSequence) {
        builder.setSubtitle(charSequence);
    }

    public static void p(MediaDescription.Builder builder, CharSequence charSequence) {
        builder.setTitle(charSequence);
    }
}
