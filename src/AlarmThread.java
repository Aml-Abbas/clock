import clock.io.ClockOutput;


public class AlarmThread extends Thread {
    private ClockOutput clockOutput;
    private AlarmData alarmData;

    public AlarmThread(ClockOutput clockOutput, AlarmData alarmData) {
        this.clockOutput = clockOutput;
        this.alarmData= alarmData;
    }

    public void run() {

        for (int i = 1; i <= 20; i++) {
            if (!alarmData.getAlarm()) {
                return;
            }
            clockOutput.alarm();

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
