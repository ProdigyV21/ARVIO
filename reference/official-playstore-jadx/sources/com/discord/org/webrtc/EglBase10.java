package com.discord.org.webrtc;

import com.discord.org.webrtc.EglBase;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

/* JADX INFO: loaded from: classes4.dex */
public interface EglBase10 extends EglBase {

    public interface Context extends EglBase.Context {
        EGLContext getRawContext();
    }

    public interface EglConnection extends EglBase.EglConnection {
        EGLConfig getConfig();

        EGLContext getContext();

        EGLDisplay getDisplay();

        EGL10 getEgl();
    }
}
