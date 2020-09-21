import java.util.concurrent.Semaphore;

public class ClockData {

    private int hours;
    private int minutes;
    private int seconds;
    private int [] time;
    private AlarmData alarmData;
    private Semaphore semaphore;

    public ClockData (int hours, int minutes, int seconds, AlarmData alarmData){
        this.time= new int [3];
        this.hours= hours;
        this.minutes= minutes;
        this.seconds= seconds;
        this.alarmData= alarmData;
        time[0]= seconds;
        time[1]= minutes;
        time[2]= hours;

        semaphore= new Semaphore(1);
    }

    public void setVectorTime(){
        try {
            semaphore.acquire();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        time[0]= seconds;
        time[1]= minutes;
        time[2]= hours;
        semaphore.release();

    }
    public int [] getVectorTime(){
        try {
            semaphore.acquire();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int [] retVectorTime= time;
        semaphore.release();

        return retVectorTime;
    }
    public int getHours(){
        try {
            semaphore.acquire();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int retHours= hours;
        semaphore.release();
        return retHours;
    }
    public int getMinutes(){
        try {
            semaphore.acquire();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int retMinutes= minutes;
        semaphore.release();
        return retMinutes;

    }
    public int getSeconds(){
        try {
            semaphore.acquire();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int retSeconds= seconds;
        semaphore.release();
        return retSeconds;
    }
    public void setTime(int hours, int minutes, int seconds){
        alarmData.setAlarm(false);
        try {
            semaphore.acquire();
            this.hours= hours;
            this.minutes= minutes;
            this.seconds= seconds;
            semaphore.release();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void increaseTime(){

        try {
            semaphore.acquire();

            seconds++;
            if (seconds>59){
                seconds=0;
                minutes++;
            }
            if (minutes>59){
                minutes=0;
                hours++;
            }
            if (hours>24){
                hours=0;
            }
            semaphore.release();
            setVectorTime();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public long getTimeInSeconds(){
        try {
            semaphore.acquire();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        int value=hours*3600+minutes*60+seconds;
        semaphore.release();
        return value;
    }

}
