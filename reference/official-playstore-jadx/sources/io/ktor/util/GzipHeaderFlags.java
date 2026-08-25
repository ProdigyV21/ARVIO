package io.ktor.util;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lio/ktor/util/GzipHeaderFlags;", "", "()V", "EXTRA", "", "FCOMMENT", "FHCRC", "FNAME", "FTEXT", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class GzipHeaderFlags {
    public static final int EXTRA = 4;
    public static final int FCOMMENT = 16;
    public static final int FHCRC = 2;
    public static final int FNAME = 8;
    public static final int FTEXT = 1;
    public static final GzipHeaderFlags INSTANCE = new GzipHeaderFlags();

    private GzipHeaderFlags() {
    }
}
