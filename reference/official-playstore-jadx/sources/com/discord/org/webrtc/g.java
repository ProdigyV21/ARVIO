package com.discord.org.webrtc;

import com.discord.org.webrtc.EglBase;
import com.discord.org.webrtc.EglBase10;
import com.discord.org.webrtc.EglBase10Impl;
import com.discord.org.webrtc.EglBase14;
import com.discord.org.webrtc.EglBase14Impl;

/* JADX INFO: loaded from: classes4.dex */
public abstract /* synthetic */ class g {
    public static EglBase.EglConnection a(EglBase.Context context, int[] iArr) {
        if (context == null) {
            return c(iArr);
        }
        if (context instanceof EglBase14.Context) {
            return new EglBase14Impl.EglConnection(((EglBase14.Context) context).getRawContext(), iArr);
        }
        if (context instanceof EglBase10.Context) {
            return new EglBase10Impl.EglConnection(((EglBase10.Context) context).getRawContext(), iArr);
        }
        throw new IllegalArgumentException("Unrecognized Context");
    }

    public static EglBase.EglConnection b(int[] iArr) {
        return new EglBase10Impl.EglConnection(null, iArr);
    }

    public static EglBase.EglConnection c(int[] iArr) {
        return new EglBase14Impl.EglConnection(null, iArr);
    }
}
