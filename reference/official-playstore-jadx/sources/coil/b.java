package coil;

import coil.EventListener;
import coil.request.ImageRequest;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class b {
    static {
        EventListener.Factory.Companion companion = EventListener.Factory.INSTANCE;
    }

    public static EventListener a(ImageRequest imageRequest) {
        return EventListener.NONE;
    }
}
