package com.discord;

import android.view.Surface;
import com.discord.org.webrtc.AndroidVideoDecoder;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes4.dex */
public class DiscordDecoderStreamManager {
    private static DiscordDecoderStreamManagerDelegate delegate;

    public static void addOnConsumerReadyListener(String str, Consumer<Surface> consumer) {
        checkDelegate();
        delegate.addOnConsumerReadyListener(str, consumer);
    }

    private static void checkDelegate() {
        if (delegate == null) {
            throw new IllegalStateException("DiscordDecoderStreamManagerDelegate cannot be null.");
        }
    }

    public static Surface getStreamConsumer(String str) {
        checkDelegate();
        return delegate.getStreamConsumer(str);
    }

    public static void registerStreamProducer(String str, AndroidVideoDecoder androidVideoDecoder) {
        checkDelegate();
        delegate.registerStreamProducer(str, androidVideoDecoder);
    }

    public static void removeOnConsumerReadyListener(String str) {
        checkDelegate();
        delegate.removeOnConsumerReadyListener(str);
    }

    public static DiscordDecoderStreamManagerDelegate setDelegate(DiscordDecoderStreamManagerDelegate discordDecoderStreamManagerDelegate) {
        delegate = discordDecoderStreamManagerDelegate;
        return discordDecoderStreamManagerDelegate;
    }
}
