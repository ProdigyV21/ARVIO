package androidx.emoji2.text;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.DefaultLifecycleObserver;

/* JADX INFO: loaded from: classes3.dex */
public final class p implements DefaultLifecycleObserver {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ androidx.lifecycle.r f2636i;

    public p(EmojiCompatInitializer emojiCompatInitializer, androidx.lifecycle.r rVar) {
        this.f2636i = rVar;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onCreate(androidx.lifecycle.y yVar) {
        androidx.lifecycle.e.a(this, yVar);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onDestroy(androidx.lifecycle.y yVar) {
        androidx.lifecycle.e.b(this, yVar);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onPause(androidx.lifecycle.y yVar) {
        androidx.lifecycle.e.c(this, yVar);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onResume(androidx.lifecycle.y yVar) {
        (Build.VERSION.SDK_INT >= 28 ? b.a(Looper.getMainLooper()) : new Handler(Looper.getMainLooper())).postDelayed(new s(0), 500L);
        this.f2636i.removeObserver(this);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onStart(androidx.lifecycle.y yVar) {
        androidx.lifecycle.e.e(this, yVar);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onStop(androidx.lifecycle.y yVar) {
        androidx.lifecycle.e.f(this, yVar);
    }
}
