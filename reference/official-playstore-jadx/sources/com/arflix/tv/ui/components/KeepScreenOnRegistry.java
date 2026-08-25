package com.arflix.tv.ui.components;

import android.view.Window;
import java.util.WeakHashMap;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\bR \u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/arflix/tv/ui/components/KeepScreenOnRegistry;", "", "<init>", "()V", "Landroid/view/Window;", "window", "Lx6/t0;", "acquire", "(Landroid/view/Window;)V", "release", "Ljava/util/WeakHashMap;", "", "activeWindows", "Ljava/util/WeakHashMap;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class KeepScreenOnRegistry {
    public static final KeepScreenOnRegistry INSTANCE = new KeepScreenOnRegistry();
    private static final WeakHashMap<Window, Integer> activeWindows = new WeakHashMap<>();

    private KeepScreenOnRegistry() {
    }

    public final synchronized void acquire(Window window) {
        try {
            WeakHashMap<Window, Integer> weakHashMap = activeWindows;
            Integer num = weakHashMap.get(window);
            int iIntValue = num != null ? num.intValue() : 0;
            if (iIntValue == 0) {
                window.addFlags(128);
            }
            weakHashMap.put(window, Integer.valueOf(iIntValue + 1));
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void release(Window window) {
        try {
            WeakHashMap<Window, Integer> weakHashMap = activeWindows;
            Integer num = weakHashMap.get(window);
            if (num != null) {
                int iIntValue = num.intValue();
                if (iIntValue <= 1) {
                    weakHashMap.remove(window);
                    window.clearFlags(128);
                } else {
                    weakHashMap.put(window, Integer.valueOf(iIntValue - 1));
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
