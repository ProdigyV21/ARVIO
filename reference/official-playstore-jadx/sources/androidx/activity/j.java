package androidx.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException;

/* JADX INFO: loaded from: classes.dex */
public abstract class j {
    public static v4.c a(Bundle bundle) throws GoogleIdTokenParsingException {
        try {
            return new v4.c(bundle.getString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_ID"), bundle.getString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_ID_TOKEN"), bundle.getString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_DISPLAY_NAME"), bundle.getString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_FAMILY_NAME"), bundle.getString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_GIVEN_NAME"), Build.VERSION.SDK_INT >= 33 ? (Uri) bundle.getParcelable("com.google.android.libraries.identity.googleid.BUNDLE_KEY_PROFILE_PICTURE_URI", Uri.class) : (Uri) bundle.getParcelable("com.google.android.libraries.identity.googleid.BUNDLE_KEY_PROFILE_PICTURE_URI"), bundle.getString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_PHONE_NUMBER"));
        } catch (Exception e5) {
            throw new GoogleIdTokenParsingException(e5);
        }
    }

    public static OnBackInvokedDispatcher b(Activity activity) {
        return activity.getOnBackInvokedDispatcher();
    }

    public static void c(Object obj, Object obj2) {
        ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(0, (OnBackInvokedCallback) obj2);
    }

    public static void d(Object obj, Object obj2) {
        ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
    }
}
