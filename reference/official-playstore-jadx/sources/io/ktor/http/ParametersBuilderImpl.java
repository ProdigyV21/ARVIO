package io.ktor.http;

import io.ktor.http.ContentDisposition;
import io.ktor.util.StringValuesBuilderImpl;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lio/ktor/http/ParametersBuilderImpl;", "Lio/ktor/util/StringValuesBuilderImpl;", "Lio/ktor/http/ParametersBuilder;", ContentDisposition.Parameters.Size, "", "(I)V", "build", "Lio/ktor/http/Parameters;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ParametersBuilderImpl extends StringValuesBuilderImpl implements ParametersBuilder {
    public ParametersBuilderImpl() {
        this(0, 1, null);
    }

    public /* synthetic */ ParametersBuilderImpl(int i10, int i11, h hVar) {
        this((i11 & 1) != 0 ? 8 : i10);
    }

    @Override // io.ktor.util.StringValuesBuilderImpl, io.ktor.util.StringValuesBuilder
    public Parameters build() {
        return new ParametersImpl(getValues());
    }

    public ParametersBuilderImpl(int i10) {
        super(true, i10);
    }
}
