package audio.builder;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VoiceBuilderTest {
    private static final String VOICE = "kevin";
    private static final int RATE = 190;
    private static final int PITCH = 150;
    private static final int VOLUME = 100;

    @Test
    public void testBuilder() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        final Voice expectedVoice = VoiceManager.getInstance().getVoice(VOICE);

        if (expectedVoice != null) {
            expectedVoice.allocate();
            expectedVoice.setRate(RATE);
            expectedVoice.setPitch(PITCH);
            expectedVoice.setVolume(VOLUME);
        }

        final Voice builderVoice = VoiceBuilder.newVoiceBuilder()
                .setVoiceName(VOICE)
                .setVoiceRate(RATE)
                .setVoicePitch(PITCH)
                .setVoiceVolume(VOLUME)
                .build();

        assertEquals(expectedVoice.getName(), builderVoice.getName());
        assertEquals(expectedVoice.getRate(), builderVoice.getRate());
        assertEquals(expectedVoice.getPitch(), builderVoice.getPitch());
        assertEquals(expectedVoice.getVolume(), builderVoice.getVolume());
    }
}