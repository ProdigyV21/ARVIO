package com.discord.org.webrtc;

import android.content.Context;
import com.discord.org.webrtc.NetworkChangeDetector;

/* JADX INFO: loaded from: classes4.dex */
public interface NetworkChangeDetectorFactory {
    NetworkChangeDetector create(NetworkChangeDetector.Observer observer, Context context);
}
