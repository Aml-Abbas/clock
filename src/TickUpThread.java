import clock.io.ClockOutput;

public class TickUpThread extends Thread{
    private ClockData clockData;
    private ClockOutput clockOutput;
    private AlarmData alarmData;

    public TickUpThread(ClockData clockData, ClockOutput clockOutput, AlarmData alarmData){
        this.clockData= clockData;
        this.clockOutput= clockOutput;
        this.alarmData= alarmData;
    }
    public void run(){


            try {
                long t, diff;
                t = System. currentTimeMillis ();

                while (true){
                    int [] vectorTime= clockData.getVectorTime();
                    //clockOutput.displayTime(vectorTime[2] , vectorTime[1], vectorTime[0]);
                    clockData.increaseTime();
                    clockOutput.displayTime(vectorTime[2] , vectorTime[1], vectorTime[0]);
                    if (alarmData.getAlarm() && alarmData.getTimeInSeconds()==clockData.getTimeInSeconds()){
                       AlarmThread alarmThread= new AlarmThread(clockOutput, alarmData);
                       alarmThread.start();
                    }
                    t += 1000;
                    diff = t - System. currentTimeMillis ();
                    if (diff > 0) sleep(diff );
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }
}
