package ca.sheridancollege.ghartich.beans;



import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Ticket {

	private int id;
	private String name;
	private String email;
	private String eventName;
	private String artistName;
	private String location;
	private String eventDate;
	private String eventTime;
	private double price;
	private String section;
	private int rowNumber;
	private int seat;
	private boolean parking;
	
	private long numberOfAttendees;
	private double totalRevenue;
	private long maxAttendance;
	private long numOfParking;

	public String[] etimes = { "18:30:00" };
	public String[] dates = { "2024-06-30", "2024-07-31", "2024-08-31", "2024-09-31" };
	public String[] mySection = { "East", "West", "North", "South" };
	public int[] myRow = new int[50];

	public int[] mySeat = new int[50];

	{
		for (int i = 0; i < myRow.length; i++) {
			myRow[i] = i + 1;
		}

		for (int j = 0; j < mySeat.length; j++) {
			mySeat[j] = j + 1;

		}
	}

}
