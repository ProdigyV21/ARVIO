package io.ktor.http.content;

import d7.d;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0001\u0005J\u0015\u0010\u0003\u001a\u0004\u0018\u00010\u0002H¦@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006"}, d2 = {"Lio/ktor/http/content/MultiPartData;", "", "Lio/ktor/http/content/PartData;", "readPart", "(Ld7/d;)Ljava/lang/Object;", "Empty", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface MultiPartData {

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"Lio/ktor/http/content/MultiPartData$Empty;", "Lio/ktor/http/content/MultiPartData;", "<init>", "()V", "Lio/ktor/http/content/PartData;", "readPart", "(Ld7/d;)Ljava/lang/Object;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Empty implements MultiPartData {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }

        @Override // io.ktor.http.content.MultiPartData
        public Object readPart(d<? super PartData> dVar) {
            return null;
        }
    }

    Object readPart(d<? super PartData> dVar);
}
