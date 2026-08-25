package i0;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import androidx.activity.s;

/* JADX INFO: loaded from: classes3.dex */
public abstract class o {
    public static Handler getHandler(Handler handler) {
        return handler == null ? new Handler(Looper.getMainLooper()) : handler;
    }

    public final void callbackFailAsync(int i10, Handler handler) {
        getHandler(handler).post(new androidx.media3.exoplayer.audio.e(this, i10, 1));
    }

    public final void callbackSuccessAsync(Typeface typeface, Handler handler) {
        getHandler(handler).post(new s(this, typeface, 22));
    }

    public abstract void onFontRetrievalFailed(int i10);

    public abstract void onFontRetrieved(Typeface typeface);
}
