package com.discord.org.webrtc;

import com.discord.org.webrtc.EglBase;
import com.discord.org.webrtc.EglBase10;
import com.discord.org.webrtc.EglBase10Impl;
import com.discord.org.webrtc.EglBase14;
import com.discord.org.webrtc.EglBase14Impl;
import javax.microedition.khronos.egl.EGLContext;

/* JADX INFO: loaded from: classes4.dex */
public abstract /* synthetic */ class f {
    static {
        Object obj = EglBase.lock;
    }

    public static EglBase.ConfigBuilder a() {
        return new EglBase.ConfigBuilder();
    }

    public static EglBase b() {
        return d(null, EglBase.CONFIG_PLAIN);
    }

    public static EglBase c(EglBase.Context context) {
        return d(context, EglBase.CONFIG_PLAIN);
    }

    public static EglBase d(EglBase.Context context, int[] iArr) {
        if (context == null) {
            return k(iArr);
        }
        if (context instanceof EglBase14.Context) {
            return j((EglBase14.Context) context, iArr);
        }
        if (context instanceof EglBase10.Context) {
            return f((EglBase10.Context) context, iArr);
        }
        throw new IllegalArgumentException("Unrecognized Context");
    }

    public static EglBase e(EglBase.EglConnection eglConnection) {
        if (eglConnection == null) {
            return b();
        }
        if (eglConnection instanceof EglBase14Impl.EglConnection) {
            return new EglBase14Impl((EglBase14Impl.EglConnection) eglConnection);
        }
        if (eglConnection instanceof EglBase10Impl.EglConnection) {
            return new EglBase10Impl((EglBase10Impl.EglConnection) eglConnection);
        }
        throw new IllegalArgumentException("Unrecognized EglConnection");
    }

    public static EglBase10 f(EglBase10.Context context, int[] iArr) {
        return new EglBase10Impl(context == null ? null : context.getRawContext(), iArr);
    }

    public static EglBase10 g(EGLContext eGLContext, int[] iArr) {
        return new EglBase10Impl(eGLContext, iArr);
    }

    public static EglBase10 h(int[] iArr) {
        return new EglBase10Impl(null, iArr);
    }

    public static EglBase14 i(android.opengl.EGLContext eGLContext, int[] iArr) {
        return new EglBase14Impl(eGLContext, iArr);
    }

    public static EglBase14 j(EglBase14.Context context, int[] iArr) {
        return new EglBase14Impl(context == null ? null : context.getRawContext(), iArr);
    }

    public static EglBase14 k(int[] iArr) {
        return new EglBase14Impl(null, iArr);
    }

    public static int l(int[] iArr) {
        for (int i10 = 0; i10 < iArr.length - 1; i10++) {
            if (iArr[i10] == 12352) {
                int i11 = iArr[i10 + 1];
                if (i11 != 4) {
                    return i11 != 64 ? 1 : 3;
                }
                return 2;
            }
        }
        return 1;
    }
}
