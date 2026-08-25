package io.ktor.client.request.forms;

import io.ktor.http.ContentDisposition;
import io.ktor.http.ContentType;
import io.ktor.http.HeaderValueWithParametersKt;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.http.content.PartData;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Input;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a/\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u001a\u0010\u0002\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00010\u0000\"\u0006\u0012\u0002\b\u00030\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a,\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n¢\u0006\u0004\b\u0005\u0010\f\u001a]\u0010\u0015\u001a\u00020\t*\u00020\b2\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0019\b\u0004\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\nH\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0004 \u0001¢\u0006\u0004\b\u0015\u0010\u0016\u001a_\u0010\u0015\u001a\u00020\t*\u00020\b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\r2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0005 \u0001¢\u0006\u0004\b\u0015\u0010\u001a\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001b"}, d2 = {"", "Lio/ktor/client/request/forms/FormPart;", "values", "", "Lio/ktor/http/content/PartData;", "formData", "([Lio/ktor/client/request/forms/FormPart;)Ljava/util/List;", "Lkotlin/Function1;", "Lio/ktor/client/request/forms/FormBuilder;", "Lx6/t0;", "Lx6/n;", "block", "(Lr7/l;)Ljava/util/List;", "", "key", "Lio/ktor/http/Headers;", "headers", "", ContentDisposition.Parameters.Size, "Lio/ktor/utils/io/core/BytePacketBuilder;", "bodyBuilder", "append", "(Lio/ktor/client/request/forms/FormBuilder;Ljava/lang/String;Lio/ktor/http/Headers;Ljava/lang/Long;Lr7/l;)V", ContentDisposition.Parameters.FileName, "Lio/ktor/http/ContentType;", "contentType", "(Lio/ktor/client/request/forms/FormBuilder;Ljava/lang/String;Ljava/lang/String;Lio/ktor/http/ContentType;Ljava/lang/Long;Lr7/l;)V", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FormDslKt {

    /* JADX INFO: renamed from: io.ktor.client.request.forms.FormDslKt$append$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/utils/io/core/Input;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass2 extends r implements a<Input> {
        final /* synthetic */ l<BytePacketBuilder, t0> $bodyBuilder;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(l<? super BytePacketBuilder, t0> lVar) {
            super(0);
            this.$bodyBuilder = lVar;
        }

        @Override // r7.a
        public final Input invoke() {
            l<BytePacketBuilder, t0> lVar = this.$bodyBuilder;
            BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, null);
            try {
                lVar.invoke(bytePacketBuilder);
                return bytePacketBuilder.build();
            } catch (Throwable th) {
                bytePacketBuilder.release();
                throw th;
            }
        }
    }

    public static final void append(FormBuilder formBuilder, String str, Headers headers, Long l10, l<? super BytePacketBuilder, t0> lVar) {
        formBuilder.append(new FormPart(str, new InputProvider(l10, new AnonymousClass2(lVar)), headers));
    }

    public static /* synthetic */ void append$default(FormBuilder formBuilder, String str, Headers headers, Long l10, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            headers = Headers.INSTANCE.getEmpty();
        }
        if ((i10 & 4) != 0) {
            l10 = null;
        }
        formBuilder.append(new FormPart(str, new InputProvider(l10, new AnonymousClass2(lVar)), headers));
    }

    public static final List<PartData> formData(FormPart<?>... formPartArr) {
        PartData binaryChannelItem;
        ArrayList arrayList = new ArrayList();
        for (FormPart<?> formPart : formPartArr) {
            String key = formPart.getKey();
            Object objComponent2 = formPart.component2();
            Headers headers = formPart.getHeaders();
            HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, null);
            HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
            headersBuilder.append(httpHeaders.getContentDisposition(), "form-data; name=" + HeaderValueWithParametersKt.escapeIfNeeded(key));
            headersBuilder.appendAll(headers);
            if (objComponent2 instanceof String) {
                binaryChannelItem = new PartData.FormItem((String) objComponent2, FormDslKt$formData$1$part$1.INSTANCE, headersBuilder.build());
            } else if (objComponent2 instanceof Number) {
                binaryChannelItem = new PartData.FormItem(objComponent2.toString(), FormDslKt$formData$1$part$2.INSTANCE, headersBuilder.build());
            } else if (objComponent2 instanceof Boolean) {
                binaryChannelItem = new PartData.FormItem(objComponent2.toString(), FormDslKt$formData$1$part$3.INSTANCE, headersBuilder.build());
            } else if (objComponent2 instanceof byte[]) {
                headersBuilder.append(httpHeaders.getContentLength(), String.valueOf(((byte[]) objComponent2).length));
                binaryChannelItem = new PartData.BinaryItem(new FormDslKt$formData$1$part$4(objComponent2), FormDslKt$formData$1$part$5.INSTANCE, headersBuilder.build());
            } else if (objComponent2 instanceof ByteReadPacket) {
                headersBuilder.append(httpHeaders.getContentLength(), String.valueOf(((ByteReadPacket) objComponent2).getRemaining()));
                binaryChannelItem = new PartData.BinaryItem(new FormDslKt$formData$1$part$6(objComponent2), new FormDslKt$formData$1$part$7(objComponent2), headersBuilder.build());
            } else if (objComponent2 instanceof InputProvider) {
                InputProvider inputProvider = (InputProvider) objComponent2;
                Long size = inputProvider.getSize();
                if (size != null) {
                    headersBuilder.append(httpHeaders.getContentLength(), size.toString());
                }
                binaryChannelItem = new PartData.BinaryItem(inputProvider.getBlock(), FormDslKt$formData$1$part$8.INSTANCE, headersBuilder.build());
            } else {
                if (!(objComponent2 instanceof ChannelProvider)) {
                    if (!(objComponent2 instanceof Input)) {
                        throw new IllegalStateException(("Unknown form content type: " + objComponent2).toString());
                    }
                    throw new IllegalStateException(("Can't use [Input] as part of form: " + objComponent2 + ". Consider using [InputProvider] instead.").toString());
                }
                ChannelProvider channelProvider = (ChannelProvider) objComponent2;
                Long size2 = channelProvider.getSize();
                if (size2 != null) {
                    headersBuilder.append(httpHeaders.getContentLength(), size2.toString());
                }
                binaryChannelItem = new PartData.BinaryChannelItem(channelProvider.getBlock(), headersBuilder.build());
            }
            arrayList.add(binaryChannelItem);
        }
        return arrayList;
    }

    public static final void append(FormBuilder formBuilder, String str, String str2, ContentType contentType, Long l10, l<? super BytePacketBuilder, t0> lVar) {
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, null);
        HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
        headersBuilder.set(httpHeaders.getContentDisposition(), "filename=" + HeaderValueWithParametersKt.escapeIfNeeded(str2));
        if (contentType != null) {
            headersBuilder.set(httpHeaders.getContentType(), contentType.toString());
        }
        formBuilder.append(new FormPart(str, new InputProvider(l10, new AnonymousClass2(lVar)), headersBuilder.build()));
    }

    public static /* synthetic */ void append$default(FormBuilder formBuilder, String str, String str2, ContentType contentType, Long l10, l lVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            contentType = null;
        }
        if ((i10 & 8) != 0) {
            l10 = null;
        }
        append(formBuilder, str, str2, contentType, l10, lVar);
    }

    public static final List<PartData> formData(l<? super FormBuilder, t0> lVar) {
        FormBuilder formBuilder = new FormBuilder();
        lVar.invoke(formBuilder);
        FormPart[] formPartArr = (FormPart[]) formBuilder.build$ktor_client_core().toArray(new FormPart[0]);
        return formData((FormPart<?>[]) Arrays.copyOf(formPartArr, formPartArr.length));
    }
}
