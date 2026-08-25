package com.arflix.tv.ui.screens.player;

import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.exoplayer.text.TextOutput;
import com.arflix.tv.ui.screens.player.TranslatingTextOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000f\u001a\u00020\u000e2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J-\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0017\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0018\u0010\u0019J+\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0017\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u001d\u0010\u001e\u001a\u00020\u001d2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0017¢\u0006\u0004\b\u001e\u0010 R\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010!R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\"R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010#R\u0014\u0010%\u001a\u00020$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u0018\u0010'\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010(R*\u0010*\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010)8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u0016\u00101\u001a\u0002008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00102R\u0016\u00104\u001a\u0002038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b4\u00105¨\u00066"}, d2 = {"Lcom/arflix/tv/ui/screens/player/TranslatingTextOutput;", "Landroidx/media3/exoplayer/text/TextOutput;", "delegate", "Lcom/arflix/tv/ui/screens/player/SubtitleTranslationManager;", "manager", "Landroid/os/Looper;", "outputLooper", "Lka/k0;", "scope", "<init>", "(Landroidx/media3/exoplayer/text/TextOutput;Lcom/arflix/tv/ui/screens/player/SubtitleTranslationManager;Landroid/os/Looper;Lka/k0;)V", "", "Landroidx/media3/common/text/Cue;", "cues", "", "extractRawText", "(Ljava/util/List;)Ljava/lang/String;", "text", "stripHearingImpaired", "(Ljava/lang/String;)Ljava/lang/String;", "Landroidx/media3/common/text/CueGroup;", "group", "originalCues", "translatedText", "buildTranslated", "(Landroidx/media3/common/text/CueGroup;Ljava/util/List;Ljava/lang/String;)Landroidx/media3/common/text/CueGroup;", "applyTranslatedLinesToCues", "(Ljava/util/List;Ljava/lang/String;)Ljava/util/List;", "cueGroup", "Lx6/t0;", "onCues", "(Landroidx/media3/common/text/CueGroup;)V", "(Ljava/util/List;)V", "Landroidx/media3/exoplayer/text/TextOutput;", "Lcom/arflix/tv/ui/screens/player/SubtitleTranslationManager;", "Lka/k0;", "Landroid/os/Handler;", "handler", "Landroid/os/Handler;", "lastCueGroup", "Landroidx/media3/common/text/CueGroup;", "Lkotlin/Function0;", "onFirstCueOnPlaybackThread", "Lr7/a;", "getOnFirstCueOnPlaybackThread", "()Lr7/a;", "setOnFirstCueOnPlaybackThread", "(Lr7/a;)V", "", "hasFiredFirstCue", "Z", "", "cueSerial", "I", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class TranslatingTextOutput implements TextOutput {
    private int cueSerial;
    private final TextOutput delegate;
    private final Handler handler;
    private boolean hasFiredFirstCue;
    private volatile CueGroup lastCueGroup;
    private final SubtitleTranslationManager manager;
    private r7.a<x6.t0> onFirstCueOnPlaybackThread;
    private final ka.k0 scope;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.TranslatingTextOutput$onCues$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.player.TranslatingTextOutput$onCues$1", f = "AiSubtitleRenderersFactory.kt", l = {231}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ CueGroup $captured;
        final /* synthetic */ String $text;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, CueGroup cueGroup, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$text = str;
            this.$captured = cueGroup;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(TranslatingTextOutput translatingTextOutput, CueGroup cueGroup, String str) {
            if (translatingTextOutput.lastCueGroup == cueGroup) {
                translatingTextOutput.delegate.onCues(translatingTextOutput.buildTranslated(cueGroup, cueGroup.cues, str));
            }
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return TranslatingTextOutput.this.new AnonymousClass1(this.$text, this.$captured, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SubtitleTranslationManager subtitleTranslationManager = TranslatingTextOutput.this.manager;
                String str = this.$text;
                this.label = 1;
                obj = subtitleTranslationManager.translate(str, this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            final String str2 = (String) obj;
            Handler handler = TranslatingTextOutput.this.handler;
            final TranslatingTextOutput translatingTextOutput = TranslatingTextOutput.this;
            final CueGroup cueGroup = this.$captured;
            handler.post(new Runnable() { // from class: com.arflix.tv.ui.screens.player.y1
                @Override // java.lang.Runnable
                public final void run() {
                    TranslatingTextOutput.AnonymousClass1.invokeSuspend$lambda$0(translatingTextOutput, cueGroup, str2);
                }
            });
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    public TranslatingTextOutput(TextOutput textOutput, SubtitleTranslationManager subtitleTranslationManager, Looper looper, ka.k0 k0Var) {
        this.delegate = textOutput;
        this.manager = subtitleTranslationManager;
        this.scope = k0Var;
        this.handler = new Handler(looper);
    }

    private final List<Cue> applyTranslatedLinesToCues(List<Cue> originalCues, String translatedText) {
        String string;
        String string2;
        List listY0 = kotlin.text.o.y0(translatedText, new String[]{"\n"}, 0, 6);
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(originalCues, 10));
        int i10 = 0;
        for (Cue cue : originalCues) {
            CharSequence charSequence = cue.text;
            String strL = "";
            if (charSequence == null || (string = charSequence.toString()) == null) {
                string = "";
            }
            int size = kotlin.text.o.y0(string, new String[]{"\n"}, 0, 6).size() + i10;
            int size2 = listY0.size();
            if (size <= size2) {
                size2 = size;
            }
            if (i10 < listY0.size()) {
                strL = kotlin.collections.x.u0(listY0.subList(i10, size2), "\n", null, null, null, 62);
            } else {
                CharSequence charSequence2 = cue.text;
                if (charSequence2 != null && (string2 = charSequence2.toString()) != null) {
                    strL = string2;
                }
            }
            for (int i11 = 0; i11 < strL.length(); i11++) {
                byte directionality = Character.getDirectionality(strL.charAt(i11));
                if (directionality == 1 || directionality == 2) {
                    strL = a0.c.l("\u200f", strL, "\u200f");
                    break;
                }
            }
            arrayList.add(cue.buildUpon().setText(new SpannableString(strL)).build());
            i10 = size;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CueGroup buildTranslated(CueGroup group, List<Cue> originalCues, String translatedText) {
        return new CueGroup(applyTranslatedLinesToCues(originalCues, translatedText), group.presentationTimeUs);
    }

    private final String extractRawText(List<Cue> cues) {
        String string;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = cues.iterator();
        while (it.hasNext()) {
            CharSequence charSequence = ((Cue) it.next()).text;
            String string2 = (charSequence == null || (string = charSequence.toString()) == null) ? null : kotlin.text.o.L0(string).toString();
            if (string2 != null) {
                arrayList.add(string2);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (!kotlin.text.o.h0((String) obj)) {
                arrayList2.add(obj);
            }
        }
        return kotlin.collections.x.u0(arrayList2, "\n", null, null, null, 62);
    }

    private final String stripHearingImpaired(String text) {
        AiSubtitleRegexes aiSubtitleRegexes = AiSubtitleRegexes.INSTANCE;
        return com.arflix.tv.data.repository.g.o(aiSubtitleRegexes.getMUSIC_REGEX(), aiSubtitleRegexes.getBRACKET_REGEX().f("", text), "");
    }

    public final r7.a<x6.t0> getOnFirstCueOnPlaybackThread() {
        return this.onFirstCueOnPlaybackThread;
    }

    @Override // androidx.media3.exoplayer.text.TextOutput
    public void onCues(CueGroup cueGroup) {
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        com.google.common.collect.h1 h1Var = cueGroup.cues;
        if (!this.hasFiredFirstCue && !h1Var.isEmpty() && this.manager.getIsEnabled()) {
            this.hasFiredFirstCue = true;
            r7.a<x6.t0> aVar = this.onFirstCueOnPlaybackThread;
            if (aVar != null) {
                aVar.invoke();
            }
            this.onFirstCueOnPlaybackThread = null;
        }
        if (!this.manager.getIsEnabled()) {
            this.lastCueGroup = cueGroup;
            this.delegate.onCues(cueGroup);
            return;
        }
        if (h1Var.isEmpty()) {
            this.lastCueGroup = cueGroup;
            this.delegate.onCues(cueGroup);
            return;
        }
        String strExtractRawText = extractRawText(h1Var);
        if (kotlin.text.o.h0(strExtractRawText)) {
            this.lastCueGroup = cueGroup;
            this.delegate.onCues(cueGroup);
            return;
        }
        if (this.manager.getRemoveHearingImpaired()) {
            strExtractRawText = stripHearingImpaired(strExtractRawText);
        }
        if (kotlin.text.o.h0(strExtractRawText)) {
            this.delegate.onCues(new CueGroup(zVar, cueGroup.presentationTimeUs));
            return;
        }
        this.lastCueGroup = cueGroup;
        this.cueSerial++;
        String cached = this.manager.getCached(strExtractRawText);
        if (cached != null) {
            this.delegate.onCues(buildTranslated(cueGroup, h1Var, cached));
        } else {
            this.delegate.onCues(new CueGroup(zVar, cueGroup.presentationTimeUs));
            ka.m0.p(this.scope, null, 0, new AnonymousClass1(strExtractRawText, cueGroup, null), 3);
        }
    }

    public final void setOnFirstCueOnPlaybackThread(r7.a<x6.t0> aVar) {
        this.onFirstCueOnPlaybackThread = aVar;
    }

    @Override // androidx.media3.exoplayer.text.TextOutput
    @x6.e
    public void onCues(List<Cue> cues) {
        if (this.manager.getIsEnabled() && !cues.isEmpty()) {
            String strExtractRawText = extractRawText(cues);
            if (kotlin.text.o.h0(strExtractRawText)) {
                this.delegate.onCues(cues);
                return;
            }
            if (this.manager.getRemoveHearingImpaired()) {
                strExtractRawText = stripHearingImpaired(strExtractRawText);
            }
            boolean zH0 = kotlin.text.o.h0(strExtractRawText);
            kotlin.collections.z zVar = kotlin.collections.z.f19728i;
            if (zH0) {
                this.delegate.onCues(zVar);
                return;
            }
            String cached = this.manager.getCached(strExtractRawText);
            if (cached != null) {
                this.delegate.onCues(applyTranslatedLinesToCues(cues, cached));
                return;
            } else if (this.manager.isInFlight(strExtractRawText)) {
                this.delegate.onCues(zVar);
                return;
            } else {
                this.delegate.onCues(cues);
                return;
            }
        }
        this.delegate.onCues(cues);
    }
}
