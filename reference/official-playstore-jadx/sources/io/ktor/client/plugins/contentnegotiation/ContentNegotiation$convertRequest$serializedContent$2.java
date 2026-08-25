package io.ktor.client.plugins.contentnegotiation;

import io.ktor.client.plugins.contentnegotiation.ContentNegotiation;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/client/plugins/contentnegotiation/ContentNegotiation$Config$ConverterRegistration;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ContentNegotiation$convertRequest$serializedContent$2 extends r implements l<ContentNegotiation.Config.ConverterRegistration, CharSequence> {
    public static final ContentNegotiation$convertRequest$serializedContent$2 INSTANCE = new ContentNegotiation$convertRequest$serializedContent$2();

    public ContentNegotiation$convertRequest$serializedContent$2() {
        super(1);
    }

    @Override // r7.l
    public final CharSequence invoke(ContentNegotiation.Config.ConverterRegistration converterRegistration) {
        return converterRegistration.getConverter().toString();
    }
}
