package com.discord.org.webrtc;

import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import com.discord.org.webrtc.EglBase;

/* JADX INFO: loaded from: classes4.dex */
public interface EglBase14 extends EglBase {

    public interface Context extends EglBase.Context {
        EGLContext getRawContext();
    }

    public interface EglConnection extends EglBase.EglConnection {
        EGLConfig getConfig();

        EGLContext getContext();

        EGLDisplay getDisplay();
    }
}
