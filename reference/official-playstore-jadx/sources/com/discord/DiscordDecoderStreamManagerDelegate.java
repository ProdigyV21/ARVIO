package com.discord;

import android.view.Surface;
import com.discord.org.webrtc.AndroidVideoDecoder;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes4.dex */
public interface DiscordDecoderStreamManagerDelegate {
    void addOnConsumerReadyListener(String str, Consumer<Surface> consumer);

    Surface getStreamConsumer(String str);

    void registerStreamProducer(String str, AndroidVideoDecoder androidVideoDecoder);

    void removeOnConsumerReadyListener(String str);
}
