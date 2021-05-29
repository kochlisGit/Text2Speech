package audio.builder;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class VoiceBuilder {
    private String voiceName;
    private int voiceRate;
    private int voicePitch;
    private int voiceVolume;

    public static VoiceBuilder newVoiceBuilder() {
        return new VoiceBuilder();
    }

    public VoiceBuilder setVoiceName(final String voiceName) {
        this.voiceName = voiceName;
        return this;
    }

    public VoiceBuilder setVoiceRate(final int voiceRate) {
        this.voiceRate = voiceRate;
        return this;
    }

    public VoiceBuilder setVoicePitch(final int voicePitch) {
        this.voicePitch = voicePitch;
        return this;
    }

    public VoiceBuilder setVoiceVolume(final int voiceVolume) {
        this.voiceVolume = voiceVolume;
        return this;
    }

    public Voice build() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        final Voice voice = VoiceManager.getInstance().getVoice(voiceName);

        if (voice != null) {
            voice.allocate();
            voice.setRate(voiceRate);
            voice.setPitch(voicePitch);
            voice.setVolume(voiceVolume);
            return voice;
        }
        return null;
    }
}
