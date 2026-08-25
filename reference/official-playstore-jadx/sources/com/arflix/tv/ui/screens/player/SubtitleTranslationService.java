package com.arflix.tv.ui.screens.player;

import android.util.Log;
import androidx.fragment.app.a2;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import com.google.android.gms.cast.MediaError;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import kotlin.Metadata;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.helper.HttpConnection;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\r\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\r\u0010\fJ\u001f\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J%\u0010\u0014\u001a\u00020\n2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u00122\u0006\u0010\u000f\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J.\u0010\u0019\u001a\u00020\u00182\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u00122\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0016H\u0082@¢\u0006\u0004\b\u0019\u0010\u001aJ.\u0010\u001c\u001a\u00020\u00182\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u00122\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0003H\u0082@¢\u0006\u0004\b\u001c\u0010\u001dJ\u0018\u0010 \u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u0016H\u0082@¢\u0006\u0004\b \u0010!JB\u0010#\u001a\u00020\u00182\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u00122\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\"\u001a\u00020\u00162\b\b\u0002\u0010\u001e\u001a\u00020\u0016H\u0082@¢\u0006\u0004\b#\u0010$J5\u0010&\u001a\u00020\u00182\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u00122\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0002¢\u0006\u0004\b&\u0010'J&\u0010(\u001a\u00020\u00182\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u00122\u0006\u0010\u000e\u001a\u00020\u0003H\u0086@¢\u0006\u0004\b(\u0010)R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010*R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010*R\u0014\u0010,\u001a\u00020+8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u0014\u0010/\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100¨\u00061"}, d2 = {"Lcom/arflix/tv/ui/screens/player/SubtitleTranslationService;", "", "Lkotlin/Function0;", "", "apiKeyProvider", "Lcom/arflix/tv/ui/screens/player/SubtitleAiModel;", "modelProvider", "<init>", "(Lr7/a;Lr7/a;)V", "text", "Lorg/json/JSONArray;", "extractJsonArray", "(Ljava/lang/String;)Lorg/json/JSONArray;", "repairTruncatedArray", "targetLanguage", "NL", "buildSystemPrompt", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "", "lines", "encodeIndexed", "(Ljava/util/List;Ljava/lang/String;)Lorg/json/JSONArray;", "", "depth", "Lcom/arflix/tv/ui/screens/player/TranslationResult;", "translateBatchInternal", "(Ljava/util/List;Ljava/lang/String;ILd7/d;)Ljava/lang/Object;", "apiKey", "translateGroq", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "transientAttempt", "Lx6/t0;", "geminiBackoff", "(ILd7/d;)Ljava/lang/Object;", "attempt", "translateGemini", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;IILd7/d;)Ljava/lang/Object;", "rawText", "parseTranslationResult", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/ui/screens/player/TranslationResult;", "translateBatch", "(Ljava/util/List;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lr7/a;", "Lgb/h0;", "client", "Lgb/h0;", "Lkotlin/text/m;", "indexPrefixRegex", "Lkotlin/text/m;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SubtitleTranslationService {
    public static final int $stable = 8;
    private final r7.a<String> apiKeyProvider;
    private final gb.h0 client;
    private final kotlin.text.m indexPrefixRegex;
    private final r7.a<SubtitleAiModel> modelProvider;

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SubtitleAiModel.values().length];
            try {
                iArr[SubtitleAiModel.GROQ_LLAMA_70B.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SubtitleAiModel.GEMINI_FLASH_25.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.SubtitleTranslationService$translateBatchInternal$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.player.SubtitleTranslationService", f = "SubtitleTranslationService.kt", l = {154, 155, 168, 169}, m = "translateBatchInternal", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubtitleTranslationService.this.translateBatchInternal(null, null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.SubtitleTranslationService$translateGemini$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/ui/screens/player/TranslationResult;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/ui/screens/player/TranslationResult;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.player.SubtitleTranslationService$translateGemini$2", f = "SubtitleTranslationService.kt", l = {MediaError.DetailedErrorCode.HLS_NETWORK_INVALID_SEGMENT, MediaError.DetailedErrorCode.HLS_SEGMENT_PARSING, 366, 377, 378}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super TranslationResult>, Object> {
        final /* synthetic */ String $NL;
        final /* synthetic */ String $apiKey;
        final /* synthetic */ int $attempt;
        final /* synthetic */ List<String> $lines;
        final /* synthetic */ gb.k0 $request;
        final /* synthetic */ String $targetLanguage;
        final /* synthetic */ int $transientAttempt;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(gb.k0 k0Var, int i10, List<String> list, String str, String str2, int i11, String str3, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$request = k0Var;
            this.$transientAttempt = i10;
            this.$lines = list;
            this.$targetLanguage = str;
            this.$apiKey = str2;
            this.$attempt = i11;
            this.$NL = str3;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = SubtitleTranslationService.this.new AnonymousClass2(this.$request, this.$transientAttempt, this.$lines, this.$targetLanguage, this.$apiKey, this.$attempt, this.$NL, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        /* JADX WARN: Removed duplicated region for block: B:119:0x0330  */
        /* JADX WARN: Removed duplicated region for block: B:129:0x039c A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:132:0x03c0  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r19) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 961
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.SubtitleTranslationService.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super TranslationResult> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.SubtitleTranslationService$translateGroq$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/ui/screens/player/TranslationResult;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/ui/screens/player/TranslationResult;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.player.SubtitleTranslationService$translateGroq$2", f = "SubtitleTranslationService.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C14412 extends f7.j implements r7.p<ka.k0, d7.d<? super TranslationResult>, Object> {
        final /* synthetic */ String $NL;
        final /* synthetic */ List<String> $lines;
        final /* synthetic */ gb.k0 $request;
        final /* synthetic */ String $targetLanguage;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14412(gb.k0 k0Var, List<String> list, String str, String str2, d7.d<? super C14412> dVar) {
            super(2, dVar);
            this.$request = k0Var;
            this.$lines = list;
            this.$targetLanguage = str;
            this.$NL = str2;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C14412 c14412 = SubtitleTranslationService.this.new C14412(this.$request, this.$lines, this.$targetLanguage, this.$NL, dVar);
            c14412.L$0 = obj;
            return c14412;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Exception {
            String str;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            try {
                gb.p0 p0VarD = SubtitleTranslationService.this.client.a(this.$request).d();
                gb.s0 s0Var = p0VarD.f15786q;
                int i10 = p0VarD.f15783n;
                if (s0Var == null) {
                    List<String> list = this.$lines;
                    Log.e("SubtitleTranslation", "Empty response body (HTTP " + i10 + ")");
                    return new TranslationResult(list, false, "Empty response (" + i10 + ")");
                }
                String strX = s0Var.x();
                if (p0VarD.k()) {
                    return SubtitleTranslationService.this.parseTranslationResult(this.$lines, this.$targetLanguage, kotlin.text.o.L0(new JSONObject(strX).getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content")).toString(), this.$NL);
                }
                if (i10 == 429) {
                    str = "RATE_LIMITED";
                } else {
                    str = "HTTP " + i10 + ": " + strX;
                }
                return new TranslationResult(this.$lines, false, str);
            } catch (Exception e5) {
                if (e5 instanceof CancellationException) {
                    throw e5;
                }
                Log.e("SubtitleTranslation", "translateGroq exception: " + e5.getMessage(), e5);
                return new TranslationResult(this.$lines, false, e5.getMessage());
            }
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super TranslationResult> dVar) {
            return ((C14412) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SubtitleTranslationService(r7.a<String> aVar, r7.a<? extends SubtitleAiModel> aVar2) {
        this.apiKeyProvider = aVar;
        this.modelProvider = aVar2;
        gb.g0 g0Var = new gb.g0();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        g0Var.b(10L, timeUnit);
        g0Var.e(30L, timeUnit);
        this.client = new gb.h0(g0Var);
        this.indexPrefixRegex = new kotlin.text.m("^\\s*(\\d+)\\s*[:.\\-]\\s*");
    }

    private final String buildSystemPrompt(String targetLanguage, String NL) {
        return a0.c.p(a2.r("You are a professional subtitle translator. Translate the following JSON array into natural ", targetLanguage, ".\nRules:\n1. Return ONLY a valid JSON array of strings.\n2. Every input element starts with a numeric prefix like '7: ' — keep the EXACT same numeric prefix on the corresponding translated element. Never merge or split elements.\n3. Keep the exact same order and element count.\n4. Preserve the '", NL, "' symbol exactly where it appears as a line break.\n5. Use informal, spoken "), targetLanguage, " suitable for cinema.");
    }

    private final JSONArray encodeIndexed(List<String> lines, String NL) {
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(lines, 10));
        int i10 = 0;
        for (Object obj : lines) {
            int i11 = i10 + 1;
            if (i10 < 0) {
                t7.a.Q();
                throw null;
            }
            arrayList.add(i10 + ": " + kotlin.text.u.O((String) obj, "\n", NL, false));
            i10 = i11;
        }
        return new JSONArray((Collection) arrayList);
    }

    private final JSONArray extractJsonArray(String text) {
        for (String str : kotlin.collections.x.I0(kotlin.collections.x.P0(ga.r.U(new ga.t(kotlin.text.m.c(text, new kotlin.text.m("```(?:json)?\\s*([\\s\\S]*?)```")), new w0(10)))), t7.a.E(kotlin.text.o.L0(Pattern.compile("```[^`]*```").matcher(text).replaceAll("")).toString(), text))) {
            try {
                return new JSONArray(str);
            } catch (Exception unused) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    List listU = ga.r.U(ga.r.Q(ga.r.E(jSONObject.keys()), new b(jSONObject, 2)));
                    if (listU.size() == 1) {
                        return (JSONArray) listU.get(0);
                    }
                } catch (Exception unused2) {
                }
                int iE0 = kotlin.text.o.e0(str, '[', 0, false, 6);
                int iK0 = kotlin.text.o.k0(str, ']', 0, 6);
                if (iE0 < 0 || iK0 <= iE0) {
                    JSONArray jSONArrayRepairTruncatedArray = repairTruncatedArray(str);
                    if (jSONArrayRepairTruncatedArray != null) {
                        return jSONArrayRepairTruncatedArray;
                    }
                } else {
                    try {
                        return new JSONArray(str.substring(iE0, iK0 + 1));
                    } catch (Exception unused3) {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String extractJsonArray$lambda$0(kotlin.text.i iVar) {
        return kotlin.text.o.L0((String) iVar.b().get(1)).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object geminiBackoff(int i10, d7.d<? super x6.t0> dVar) {
        Object objA = ka.s0.a((long) ((1 << i10) * 400 * ((Math.random() * 0.6d) + 0.7d)), dVar);
        return objA == e7.a.f15033i ? objA : x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00eb A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.arflix.tv.ui.screens.player.TranslationResult parseTranslationResult(java.util.List<java.lang.String> r17, java.lang.String r18, java.lang.String r19, java.lang.String r20) {
        /*
            Method dump skipped, instruction units count: 408
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.SubtitleTranslationService.parseTranslationResult(java.util.List, java.lang.String, java.lang.String, java.lang.String):com.arflix.tv.ui.screens.player.TranslationResult");
    }

    private final JSONArray repairTruncatedArray(String text) {
        int iK0;
        String string = kotlin.text.o.L0(text).toString();
        if (!kotlin.text.u.P(string, "[", false) || kotlin.text.u.K(string, "]", false) || (iK0 = kotlin.text.o.k0(string, '\"', 0, 6)) <= 0) {
            return null;
        }
        Iterator it = t7.a.E(string, string.substring(0, iK0 + 1)).iterator();
        while (it.hasNext()) {
            String strO0 = kotlin.text.o.O0(kotlin.text.o.N0((String) it.next()).toString(), ',');
            try {
                try {
                    return new JSONArray(strO0 + "]");
                } catch (Exception unused) {
                    return new JSONArray(strO0 + "\"]");
                }
            } catch (Exception unused2) {
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0104, code lost:
    
        if (r3 == r12) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0122, code lost:
    
        if (r3 == r12) goto L56;
     */
    /* JADX WARN: Removed duplicated region for block: B:57:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object translateBatchInternal(java.util.List<java.lang.String> r19, java.lang.String r20, int r21, d7.d<? super com.arflix.tv.ui.screens.player.TranslationResult> r22) throws org.json.JSONException {
        /*
            Method dump skipped, instruction units count: 439
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.SubtitleTranslationService.translateBatchInternal(java.util.List, java.lang.String, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object translateGemini(List<String> list, String str, String str2, int i10, int i11, d7.d<? super TranslationResult> dVar) throws JSONException {
        JSONArray jSONArrayEncodeIndexed = encodeIndexed(list, "⏎");
        String strBuildSystemPrompt = buildSystemPrompt(str, "⏎");
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("text", strBuildSystemPrompt);
        jSONArray.put(jSONObject3);
        jSONObject2.put("parts", jSONArray);
        jSONObject.put("system_instruction", jSONObject2);
        JSONArray jSONArray2 = new JSONArray();
        JSONObject jSONObject4 = new JSONObject();
        JSONArray jSONArray3 = new JSONArray();
        JSONObject jSONObject5 = new JSONObject();
        jSONObject5.put("text", "Translate to " + str + ":\n" + jSONArrayEncodeIndexed);
        jSONArray3.put(jSONObject5);
        jSONObject4.put("parts", jSONArray3);
        jSONArray2.put(jSONObject4);
        jSONObject.put("contents", jSONArray2);
        JSONObject jSONObject6 = new JSONObject();
        jSONObject6.put("temperature", 0.1d);
        jSONObject6.put("responseMimeType", "application/json");
        JSONObject jSONObject7 = new JSONObject();
        jSONObject7.put("thinkingLevel", "minimal");
        jSONObject6.put("thinkingConfig", jSONObject7);
        jSONObject.put("generationConfig", jSONObject6);
        JSONArray jSONArray4 = new JSONArray();
        for (String str3 : t7.a.E("HARM_CATEGORY_HARASSMENT", "HARM_CATEGORY_HATE_SPEECH", "HARM_CATEGORY_SEXUALLY_EXPLICIT", "HARM_CATEGORY_DANGEROUS_CONTENT")) {
            JSONObject jSONObject8 = new JSONObject();
            jSONObject8.put("category", str3);
            jSONObject8.put("threshold", "BLOCK_NONE");
            jSONArray4.put(jSONObject8);
        }
        jSONObject.put("safetySettings", jSONArray4);
        gb.j0 j0Var = new gb.j0();
        j0Var.i("https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash-lite:generateContent?key=" + str2);
        j0Var.f15724c.h(HttpConnection.CONTENT_TYPE, "application/json");
        String string = jSONObject.toString();
        Pattern pattern = gb.d0.f15633d;
        gb.d0 d0VarI0 = xc.d.i0("application/json");
        Charset charset = kotlin.text.a.f19924a;
        Charset charsetA = d0VarI0.a(null);
        if (charsetA == null) {
            try {
                d0VarI0 = xc.d.i0(d0VarI0 + "; charset=utf-8");
            } catch (IllegalArgumentException unused) {
                d0VarI0 = null;
            }
        } else {
            charset = charsetA;
        }
        byte[] bytes = string.getBytes(charset);
        int length = bytes.length;
        ib.c.c(bytes.length, 0, length);
        j0Var.f(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, new gb.m0(d0VarI0, length, bytes));
        return ka.m0.y(ka.x0.f19655d, new AnonymousClass2(j0Var.b(), i11, list, str, str2, i10, "⏎", null), dVar);
    }

    public static /* synthetic */ Object translateGemini$default(SubtitleTranslationService subtitleTranslationService, List list, String str, String str2, int i10, int i11, d7.d dVar, int i12, Object obj) {
        if ((i12 & 8) != 0) {
            i10 = 0;
        }
        if ((i12 & 16) != 0) {
            i11 = 0;
        }
        return subtitleTranslationService.translateGemini(list, str, str2, i10, i11, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object translateGroq(List<String> list, String str, String str2, d7.d<? super TranslationResult> dVar) throws JSONException {
        JSONArray jSONArrayEncodeIndexed = encodeIndexed(list, "⏎");
        String strBuildSystemPrompt = buildSystemPrompt(str, "⏎");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("model", "openai/gpt-oss-120b");
        jSONObject.put("temperature", 0.1d);
        jSONObject.put("reasoning_effort", "low");
        jSONObject.put("reasoning_format", "hidden");
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("role", "system");
        jSONObject2.put("content", strBuildSystemPrompt);
        jSONArray.put(jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("role", "user");
        jSONObject3.put("content", "Translate to " + str + ":\n" + jSONArrayEncodeIndexed);
        jSONArray.put(jSONObject3);
        jSONObject.put("messages", jSONArray);
        gb.j0 j0Var = new gb.j0();
        j0Var.i("https://api.groq.com/openai/v1/chat/completions");
        j0Var.f15724c.h("Authorization", "Bearer " + str2);
        j0Var.f15724c.h(HttpConnection.CONTENT_TYPE, "application/json");
        String string = jSONObject.toString();
        Pattern pattern = gb.d0.f15633d;
        gb.d0 d0VarI0 = xc.d.i0("application/json");
        Charset charset = kotlin.text.a.f19924a;
        Charset charsetA = d0VarI0.a(null);
        if (charsetA == null) {
            try {
                d0VarI0 = xc.d.i0(d0VarI0 + "; charset=utf-8");
            } catch (IllegalArgumentException unused) {
                d0VarI0 = null;
            }
        } else {
            charset = charsetA;
        }
        byte[] bytes = string.getBytes(charset);
        int length = bytes.length;
        ib.c.c(bytes.length, 0, length);
        j0Var.f(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, new gb.m0(d0VarI0, length, bytes));
        return ka.m0.y(ka.x0.f19655d, new C14412(j0Var.b(), list, str, "⏎", null), dVar);
    }

    public final Object translateBatch(List<String> list, String str, d7.d<? super TranslationResult> dVar) {
        return translateBatchInternal(list, str, 0, dVar);
    }

    public /* synthetic */ SubtitleTranslationService(r7.a aVar, r7.a aVar2, int i10, kotlin.jvm.internal.h hVar) {
        this(aVar, (i10 & 2) != 0 ? new d0(7) : aVar2);
    }
}
