import clock.io.ClockInput;
import clock.io.ClockOutput;

import java.util.concurrent.Semaphore;

public class UserInputThread extends Thread{
    private ClockInput clockInput;
    private ClockData clockData;
    private ClockOutput clockOutput;
    private AlarmData alarmData;

    public UserInputThread(ClockData clockData, ClockInput clockInput, ClockOutput clockOutput, AlarmData alarmData){
        this.clockData= clockData;
        this.clockInput= clockInput;
        this.clockOutput= clockOutput;
        this.alarmData= alarmData;
    }
    public void run(){
        try {
            while (true){

                clockInput.getSemaphore().acquire();

                ClockInput.UserInput userInput = clockInput.getUserInput();

                int choice = userInput.getChoice();
                if (choice== ClockInput.CHOICE_TOGGLE_ALARM){
                    boolean alarm = !(alarmData.getAlarm());
                    alarmData.setAlarm(alarm);
                    clockOutput.setAlarmIndicator(alarm);
                }else {
                    int h = userInput.getHours();
                    int m = userInput.getMinutes();
                    int s = userInput.getSeconds();
                    System.out.println("choice=" + choice + " h=" + h + " m=" + m + " s=" + s);
                    clockOutput.setAlarmIndicator(false);
                    if (choice==ClockInput.CHOICE_SET_ALARM){
                        alarmData.setAlarm(h,m,s);
                    }else {
                        clockData.setTime(h,m,s);

                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
