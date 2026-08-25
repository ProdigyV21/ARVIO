package io.sentry.protocol;

import io.ktor.http.LinkHeader;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class DebugImage implements k2, i2 {
    public static final String JVM = "jvm";
    public static final String PROGUARD = "proguard";
    private String arch;
    private String codeFile;
    private String codeId;
    private String debugFile;
    private String debugId;
    private String imageAddr;
    private Long imageSize;
    private String type;
    private Map<String, Object> unknown;
    private String uuid;

    public String getArch() {
        return this.arch;
    }

    public String getCodeFile() {
        return this.codeFile;
    }

    public String getCodeId() {
        return this.codeId;
    }

    public String getDebugFile() {
        return this.debugFile;
    }

    public String getDebugId() {
        return this.debugId;
    }

    public String getImageAddr() {
        return this.imageAddr;
    }

    public Long getImageSize() {
        return this.imageSize;
    }

    public String getType() {
        return this.type;
    }

    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    public String getUuid() {
        return this.uuid;
    }

    @Override // io.sentry.i2
    public void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        l3Var.j();
        if (this.uuid != null) {
            fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
            fVar.S("uuid");
            fVar.value(this.uuid);
        }
        if (this.type != null) {
            fi.iki.elonen.f fVar2 = (fi.iki.elonen.f) l3Var;
            fVar2.S(LinkHeader.Parameters.Type);
            fVar2.value(this.type);
        }
        if (this.debugId != null) {
            fi.iki.elonen.f fVar3 = (fi.iki.elonen.f) l3Var;
            fVar3.S("debug_id");
            fVar3.value(this.debugId);
        }
        if (this.debugFile != null) {
            fi.iki.elonen.f fVar4 = (fi.iki.elonen.f) l3Var;
            fVar4.S("debug_file");
            fVar4.value(this.debugFile);
        }
        if (this.codeId != null) {
            fi.iki.elonen.f fVar5 = (fi.iki.elonen.f) l3Var;
            fVar5.S("code_id");
            fVar5.value(this.codeId);
        }
        if (this.codeFile != null) {
            fi.iki.elonen.f fVar6 = (fi.iki.elonen.f) l3Var;
            fVar6.S("code_file");
            fVar6.value(this.codeFile);
        }
        if (this.imageAddr != null) {
            fi.iki.elonen.f fVar7 = (fi.iki.elonen.f) l3Var;
            fVar7.S("image_addr");
            fVar7.value(this.imageAddr);
        }
        if (this.imageSize != null) {
            fi.iki.elonen.f fVar8 = (fi.iki.elonen.f) l3Var;
            fVar8.S("image_size");
            fVar8.d0(this.imageSize);
        }
        if (this.arch != null) {
            fi.iki.elonen.f fVar9 = (fi.iki.elonen.f) l3Var;
            fVar9.S("arch");
            fVar9.value(this.arch);
        }
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                Object obj = this.unknown.get(str);
                fi.iki.elonen.f fVar10 = (fi.iki.elonen.f) l3Var;
                fVar10.S(str);
                fVar10.b0(iLogger, obj);
            }
        }
        ((fi.iki.elonen.f) l3Var).K();
    }

    public void setArch(String str) {
        this.arch = str;
    }

    public void setCodeFile(String str) {
        this.codeFile = str;
    }

    public void setCodeId(String str) {
        this.codeId = str;
    }

    public void setDebugFile(String str) {
        this.debugFile = str;
    }

    public void setDebugId(String str) {
        this.debugId = str;
    }

    public void setImageAddr(String str) {
        this.imageAddr = str;
    }

    public void setImageSize(Long l10) {
        this.imageSize = l10;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public void setImageSize(long j10) {
        this.imageSize = Long.valueOf(j10);
    }
}
