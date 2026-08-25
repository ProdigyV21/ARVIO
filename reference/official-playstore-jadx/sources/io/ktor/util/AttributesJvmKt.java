package io.ktor.util;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"Attributes", "Lio/ktor/util/Attributes;", "concurrent", "", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AttributesJvmKt {
    public static final Attributes Attributes(boolean z) {
        return z ? new ConcurrentSafeAttributes() : new HashMapAttributes();
    }

    public static /* synthetic */ Attributes Attributes$default(boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        return Attributes(z);
    }
}
