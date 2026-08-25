package io.ktor.util.pipeline;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"DISABLE_SFG", "", "getDISABLE_SFG", "()Z", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PipelineContext_jvmKt {
    private static final boolean DISABLE_SFG = p.a(System.getProperty("io.ktor.internal.disable.sfg"), "true");

    public static final boolean getDISABLE_SFG() {
        return DISABLE_SFG;
    }
}
