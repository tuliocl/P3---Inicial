public class TimeCard 
{
    int day;
    int in;
    int out;
    int hours_day;

    TimeCard(int day,int in,int out)
    {
        this.day = day;
        this.in = in;
        this.out = out;
        this.hours_day = out - in;
    }

    TimeCard()
    {
        
    }
}