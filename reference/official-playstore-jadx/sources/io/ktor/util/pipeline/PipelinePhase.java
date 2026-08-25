package io.ktor.util.pipeline;

import a0.c;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lio/ktor/util/pipeline/PipelinePhase;", "", ContentDisposition.Parameters.Name, "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "toString", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PipelinePhase {
    private final String name;

    public PipelinePhase(String str) {
        this.name = str;
    }

    public final String getName() {
        return this.name;
    }

    public String toString() {
        return c.p(new StringBuilder("Phase('"), this.name, "')");
    }
}
