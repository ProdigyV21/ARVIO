package androidx.leanback.widget;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.text.SpannableStringBuilder;
import android.text.SpannedString;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.leanback.widget.SearchBar;
import com.arvio.tv.R;
import java.util.ArrayList;
import java.util.regex.Matcher;

/* JADX INFO: loaded from: classes3.dex */
public final class c1 implements RecognitionListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ SearchBar f3077a;

    public c1(SearchBar searchBar) {
        this.f3077a = searchBar;
    }

    @Override // android.speech.RecognitionListener
    public final void onBeginningOfSpeech() {
    }

    @Override // android.speech.RecognitionListener
    public final void onBufferReceived(byte[] bArr) {
    }

    @Override // android.speech.RecognitionListener
    public final void onEndOfSpeech() {
    }

    @Override // android.speech.RecognitionListener
    public final void onError(int i10) {
        switch (i10) {
            case 1:
                int i11 = SearchBar.K;
                Log.w("SearchBar", "recognizer network timeout");
                break;
            case 2:
                int i12 = SearchBar.K;
                Log.w("SearchBar", "recognizer network error");
                break;
            case 3:
                int i13 = SearchBar.K;
                Log.w("SearchBar", "recognizer audio error");
                break;
            case 4:
                int i14 = SearchBar.K;
                Log.w("SearchBar", "recognizer server error");
                break;
            case 5:
                int i15 = SearchBar.K;
                Log.w("SearchBar", "recognizer client error");
                break;
            case 6:
                int i16 = SearchBar.K;
                Log.w("SearchBar", "recognizer speech timeout");
                break;
            case 7:
                int i17 = SearchBar.K;
                Log.w("SearchBar", "recognizer no match");
                break;
            case 8:
                int i18 = SearchBar.K;
                Log.w("SearchBar", "recognizer busy");
                break;
            case 9:
                int i19 = SearchBar.K;
                Log.w("SearchBar", "recognizer insufficient permissions");
                break;
            default:
                int i20 = SearchBar.K;
                Log.d("SearchBar", "recognizer other error");
                break;
        }
        SearchBar searchBar = this.f3077a;
        searchBar.c();
        searchBar.f3011s.post(new androidx.core.provider.a(searchBar, R.raw.lb_voice_failure, 1));
    }

    @Override // android.speech.RecognitionListener
    public final void onEvent(int i10, Bundle bundle) {
    }

    @Override // android.speech.RecognitionListener
    public final void onPartialResults(Bundle bundle) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("results_recognition");
        if (stringArrayList == null || stringArrayList.size() == 0) {
            return;
        }
        String str = stringArrayList.get(0);
        String str2 = stringArrayList.size() > 1 ? stringArrayList.get(1) : null;
        SearchEditText searchEditText = this.f3077a.f3004l;
        searchEditText.getClass();
        if (str == null) {
            str = "";
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        if (str2 != null) {
            int length = spannableStringBuilder.length();
            spannableStringBuilder.append((CharSequence) str2);
            Matcher matcher = m1.f3102p.matcher(str2);
            while (matcher.find()) {
                int iStart = matcher.start() + length;
                spannableStringBuilder.setSpan(new l1(searchEditText, str2.charAt(matcher.start()), iStart), iStart, matcher.end() + length, 33);
            }
        }
        searchEditText.f3107n = Math.max(str.length(), searchEditText.f3107n);
        searchEditText.setText(new SpannedString(spannableStringBuilder));
        searchEditText.bringPointIntoView(searchEditText.length());
        ObjectAnimator objectAnimator = searchEditText.f3108o;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        int streamPosition = searchEditText.getStreamPosition();
        int length2 = searchEditText.length();
        int i10 = length2 - streamPosition;
        if (i10 > 0) {
            if (searchEditText.f3108o == null) {
                ObjectAnimator objectAnimator2 = new ObjectAnimator();
                searchEditText.f3108o = objectAnimator2;
                objectAnimator2.setTarget(searchEditText);
                searchEditText.f3108o.setProperty(m1.f3103q);
            }
            searchEditText.f3108o.setIntValues(streamPosition, length2);
            searchEditText.f3108o.setDuration(((long) i10) * 50);
            searchEditText.f3108o.start();
        }
    }

    @Override // android.speech.RecognitionListener
    public final void onReadyForSpeech(Bundle bundle) {
        SearchBar searchBar = this.f3077a;
        SpeechOrbView speechOrbView = searchBar.f3005m;
        speechOrbView.setOrbColors(speechOrbView.D);
        speechOrbView.setOrbIcon(speechOrbView.getResources().getDrawable(2131231280));
        speechOrbView.a(true);
        speechOrbView.f3030w = false;
        speechOrbView.b();
        View view = speechOrbView.f3020m;
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        speechOrbView.F = 0;
        speechOrbView.G = true;
        searchBar.f3011s.post(new androidx.core.provider.a(searchBar, R.raw.lb_voice_open, 1));
    }

    @Override // android.speech.RecognitionListener
    public final void onResults(Bundle bundle) {
        SearchBar.a aVar;
        ArrayList<String> stringArrayList = bundle.getStringArrayList("results_recognition");
        SearchBar searchBar = this.f3077a;
        if (stringArrayList != null) {
            String str = stringArrayList.get(0);
            searchBar.f3007o = str;
            searchBar.f3004l.setText(str);
            if (!TextUtils.isEmpty(searchBar.f3007o) && (aVar = searchBar.f3003i) != null) {
                aVar.b();
            }
        }
        searchBar.c();
        searchBar.f3011s.post(new androidx.core.provider.a(searchBar, R.raw.lb_voice_success, 1));
    }

    @Override // android.speech.RecognitionListener
    public final void onRmsChanged(float f10) {
        this.f3077a.f3005m.setSoundLevel(f10 < 0.0f ? 0 : (int) (f10 * 10.0f));
    }
}
