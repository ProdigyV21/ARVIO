package io.ktor.client.statement;

import androidx.compose.material3.d;
import io.ktor.util.reflect.TypeInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0002\u0010\u0005J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0001HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0001HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Lio/ktor/client/statement/HttpResponseContainer;", "", "expectedType", "Lio/ktor/util/reflect/TypeInfo;", "response", "(Lio/ktor/util/reflect/TypeInfo;Ljava/lang/Object;)V", "getExpectedType", "()Lio/ktor/util/reflect/TypeInfo;", "getResponse", "()Ljava/lang/Object;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class HttpResponseContainer {
    private final TypeInfo expectedType;
    private final Object response;

    public HttpResponseContainer(TypeInfo typeInfo, Object obj) {
        this.expectedType = typeInfo;
        this.response = obj;
    }

    public static /* synthetic */ HttpResponseContainer copy$default(HttpResponseContainer httpResponseContainer, TypeInfo typeInfo, Object obj, int i10, Object obj2) {
        if ((i10 & 1) != 0) {
            typeInfo = httpResponseContainer.expectedType;
        }
        if ((i10 & 2) != 0) {
            obj = httpResponseContainer.response;
        }
        return httpResponseContainer.copy(typeInfo, obj);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final TypeInfo getExpectedType() {
        return this.expectedType;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Object getResponse() {
        return this.response;
    }

    public final HttpResponseContainer copy(TypeInfo expectedType, Object response) {
        return new HttpResponseContainer(expectedType, response);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HttpResponseContainer)) {
            return false;
        }
        HttpResponseContainer httpResponseContainer = (HttpResponseContainer) other;
        return p.a(this.expectedType, httpResponseContainer.expectedType) && p.a(this.response, httpResponseContainer.response);
    }

    public final TypeInfo getExpectedType() {
        return this.expectedType;
    }

    public final Object getResponse() {
        return this.response;
    }

    public int hashCode() {
        return this.response.hashCode() + (this.expectedType.hashCode() * 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("HttpResponseContainer(expectedType=");
        sb2.append(this.expectedType);
        sb2.append(", response=");
        return d.p(sb2, this.response, ')');
    }
}
