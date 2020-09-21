import clock.AlarmClockEmulator;
import clock.io.ClockInput;
import clock.io.ClockOutput;

public class ClockMain {

    public static void main(String[] args) throws InterruptedException {
        AlarmClockEmulator emulator = new AlarmClockEmulator();

        ClockInput  in  = emulator.getInput();
        ClockOutput out = emulator.getOutput();
        AlarmData alarmData= new AlarmData();

        ClockData clockData= new ClockData(15,2,37, alarmData);
        out.displayTime(clockData.getHours(), clockData.getMinutes(), clockData.getSeconds());   // arbitrary time: just an example

        TickUpThread tickUpThread= new TickUpThread(clockData, out,alarmData);
        UserInputThread userInputThread= new UserInputThread(clockData, in, out, alarmData);

        tickUpThread.start();
        userInputThread.start();

    }
}
