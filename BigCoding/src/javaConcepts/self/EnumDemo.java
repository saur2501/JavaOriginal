package javaConcepts.self;

public class EnumDemo{
	enum DaysOfWeek {
		SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY;
	}
	public enum Season {
		WINTER(1), SPRING, SUMMER(15), FALL(20);
		private int value;  
		private Season(int value){  
		this.value=value;  
		}  
		private Season() {
			
		}
	}  
	  
	public static void main(String[] args) {
		for (Season s : Season.values())  
			System.out.println(s + " " + s.value); 
		DaysOfWeek day=DaysOfWeek.MONDAY;  
		switch(day){  
		case SUNDAY:   
		 System.out.println("sunday");  
		 break;  
		case MONDAY:   
		 System.out.println("monday");  
		 break;  
		default:  
		System.out.println("other day");  
		}
	}
}

/* Compiler internally generates follows:-
final class Season extends Enum  
{  
    public static Season[] values()  
    {  
        return (Season[])$VALUES.clone();  
    }  
    public static Season valueOf(String s)  
    {  
        return (Season)Enum.valueOf(Season, s);  
    }  
    private Season(String s, int i, int j)  
    {  
        super(s, i);  
        value = j;  
    }  
    public static final Season WINTER;  
    public static final Season SUMMER;  
    private int value;  
    private static final Season $VALUES[];  
    static   
    {  
        WINTER = new Season("WINTER", 0, 10);  
        SUMMER = new Season("SUMMER", 1, 20);  
        $VALUES = (new Season[] {  
            WINTER, SUMMER  
        });  
    }  
}  
 */