package androidx.media3.exoplayer.dash.manifest;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.media3.extractor.metadata.emsg.EventMessage;

/* JADX INFO: loaded from: classes3.dex */
public final class EventStream {
    public final EventMessage[] events;
    public final long[] presentationTimesUs;
    public final String schemeIdUri;
    public final long timescale;
    public final String value;

    public EventStream(String str, String str2, long j10, long[] jArr, EventMessage[] eventMessageArr) {
        this.schemeIdUri = str;
        this.value = str2;
        this.timescale = j10;
        this.presentationTimesUs = jArr;
        this.events = eventMessageArr;
    }

    public String id() {
        return this.schemeIdUri + DomExceptionUtils.SEPARATOR + this.value;
    }
}
