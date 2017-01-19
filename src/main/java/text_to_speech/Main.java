package text_to_speech;

import java.text.SimpleDateFormat;
import java.util.Calendar;




public class Main {

	private static final String VOICENAME = "kevin16"; 
	private static Calendar cal = Calendar.getInstance();
    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	public static void main(String[] args) {
//		Voice voice;
//		VoiceManager voiceManager = VoiceManager.getInstance();
//		voice = voiceManager.getVoice(VOICENAME);
//		voice.allocate();
//		while(true){
//			try {
//				voice.speak("Hello Gaurav!");
//				voice.speak("What can i do for u!");
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}

		//System.out.println(System.getProperty("java.class.path"));
		SpeechInterface.init("C:\\Users\\gah6kor\\Desktop\\IOT_Platform\\speech_to_text\\voce-0.9.1\\lib", true, true, 
				"./grammar", "digits");

		System.out.println("This is a speech recognition test. " 
				+ "Speak digits from 0-9 into the microphone. " 
				+ "Speak 'quit' to quit.");
		boolean sleep = false;
		while (!sleep)
		{
			// Normally, applications would do application-specific things 
			// here.  For this sample, we'll just sleep for a little bit.
			try
			{
				//SpeechInterface.setRecognizerEnabled(true);
				Thread.sleep(200);
			}
			catch (InterruptedException e)
			{
			}
			//System.out.println(" SpeechInterface.getRecognizerQueueSize() = "+SpeechInterface.isRecognizerEnabled());
			while (SpeechInterface.getRecognizerQueueSize() > 0)
			{
				String s = SpeechInterface.popRecognizedString();

				// Check if the string contains 'quit'.
				if (-1 != s.indexOf("sleep"))
				{
					sleep = true;
				}
				
				if(s.equals("hi pi")){
					SpeechInterface.synthesize("what can i do for you");
				} else if(s.equals("tell me the time")){
					SpeechInterface.synthesize(sdf.format(cal.getTime()));
				} else if(s.equals("tell me the schedule for today")){
					SpeechInterface.synthesize("you have meeting at five thirty");
				}
				else if(s.equals("sleep")){
					System.out.println("You said: " + s);
					//voice.speak(s);
					SpeechInterface.synthesize("Bye Bye");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(s);

				
			}
		}
		SpeechInterface.destroy();
		System.exit(0);	
	}

}
