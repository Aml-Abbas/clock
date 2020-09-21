import java.util.concurrent.Semaphore;

public class AlarmData {
    private int hours;
    private int minutes;
    private int seconds;
    private boolean alarm;

    private Semaphore semaphore= new Semaphore(1);

    public AlarmData(){
        hours=0;
        minutes=0;
        seconds=0;
        alarm= false;

    }
    public void setAlarm(int hours, int minutes, int seconds){
        try {
            semaphore.acquire();
            this.alarm=false;
            this.hours= hours;
            this.minutes= minutes;
            this.seconds= seconds;
            semaphore.release();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public boolean getAlarm(){
        try {
            semaphore.acquire();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean retAlarm= alarm;
        semaphore.release();

        return retAlarm;
    }

    public void setAlarm(boolean alarm){
        try {
            semaphore.acquire();
            this.alarm= alarm;
            semaphore.release();
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
