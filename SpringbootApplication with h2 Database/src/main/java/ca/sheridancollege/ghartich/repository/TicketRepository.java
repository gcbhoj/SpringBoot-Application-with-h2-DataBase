package ca.sheridancollege.ghartich.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.ghartich.beans.Ticket;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class TicketRepository {

	private NamedParameterJdbcTemplate jdbc;

	public void addTicket(Ticket ticket) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO ticket (name, event_name, artist_name, location, event_date, event_time,price, section, row_number, seat, parking) VALUES"
				+ "(:n,:en,:an,:loc,:edate,:etime,:price,:sec,:rownum,:seat,:parking)";
		parameters.addValue("n", ticket.getName());
		parameters.addValue("en", ticket.getEventName());
		parameters.addValue("an", ticket.getArtistName());
		parameters.addValue("loc", ticket.getLocation());
		parameters.addValue("edate", ticket.getEventDate());
		parameters.addValue("etime", ticket.getEventTime());
		parameters.addValue("price", ticket.getPrice());
		parameters.addValue("sec", ticket.getSection());
		parameters.addValue("rownum", ticket.getRowNumber());
		parameters.addValue("seat", ticket.getSeat());
		parameters.addValue("parking", ticket.isParking());

		jdbc.update(query, parameters);

	}

	public ArrayList<Ticket> getTicket() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		ArrayList<Ticket> ticket = new ArrayList<Ticket>();
		String query = "SELECT * FROM ticket";
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
			Ticket tick = new Ticket();
			tick.setId((Integer) row.get("id"));
			tick.setName((String) row.get("name"));
			tick.setEventName((String) row.get("event_name"));
			tick.setArtistName((String) row.get("artist_name"));
			tick.setLocation((String) row.get("location"));
			tick.setEventDate((String) row.get("event_Date"));
			tick.setEventTime((String) row.get("event_time"));
			tick.setPrice((Double) row.get("price"));
			tick.setSection((String) row.get("section"));
			tick.setRowNumber((Integer) row.get("row_number"));
			tick.setSeat((Integer) row.get("seat"));
			tick.setParking((boolean) row.get("parking"));

			ticket.add(tick);
		}

		return ticket;
	}

	public Ticket findByID(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		ArrayList<Ticket> ticket = new ArrayList<Ticket>();
		String query = "SELECT * FROM ticket WHERE id = :id";
		parameters.addValue("id", id);
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
			Ticket tick = new Ticket();
			tick.setId((Integer) row.get("id"));
			tick.setName((String) row.get("name"));
			tick.setEventName((String) row.get("event_name"));
			tick.setArtistName((String) row.get("artist_name"));
			tick.setLocation((String) row.get("location"));
			tick.setEventDate((String) row.get("event_Date"));
			tick.setEventTime((String) row.get("event_time"));
			tick.setPrice((Double) row.get("price"));
			tick.setSection((String) row.get("section"));
			tick.setRowNumber((Integer) row.get("row_number"));
			tick.setSeat((Integer) row.get("seat"));
			tick.setParking((boolean) row.get("parking"));

			ticket.add(tick);

		}
		if (ticket.size() > 0)
			return ticket.get(0);
		else
			return null;

	}

	public void editTicket(Ticket ticket) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		String query = "UPDATE ticket SET name = :n, event_name = :en, artist_name = :an, location = :loc, event_date= :edate, event_time = :etime, price = :price, section = :sec, row_number =:rownum, seat = :seat, parking = :parking WHERE id =:id";
		parameters.addValue("id", ticket.getId());
		parameters.addValue("n", ticket.getName());
		parameters.addValue("en", ticket.getEventName());
		parameters.addValue("an", ticket.getArtistName());
		parameters.addValue("loc", ticket.getLocation());
		parameters.addValue("edate", ticket.getEventDate());
		parameters.addValue("etime", ticket.getEventTime());
		parameters.addValue("price", ticket.getPrice());
		parameters.addValue("sec", ticket.getSection());
		parameters.addValue("rownum", ticket.getRowNumber());
		parameters.addValue("seat", ticket.getSeat());
		parameters.addValue("parking", ticket.isParking());

		jdbc.update(query, parameters);
	}

	public void deleteTicket(int id) {

		MapSqlParameterSource parameters = new MapSqlParameterSource();

		String query = "DELETE FROM ticket WHERE id = :id";
		parameters.addValue("id", id);

		jdbc.update(query, parameters);

	}

	public ArrayList<Ticket> getNumberOfAttendees() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		ArrayList<Ticket> ticket = new ArrayList<Ticket>();
		String query = "SELECT event_name, artist_name, count(*)as number_of_attendees FROM ticket GROUP BY event_name, artist_name ORDER BY number_of_attendees DESC;";
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
			Ticket tick = new Ticket();
			tick.setEventName((String) row.get("event_name"));
			tick.setArtistName((String) row.get("artist_name"));
			tick.setNumberOfAttendees((long) row.get("number_of_attendees"));

			ticket.add(tick);

		}
		return ticket;
	}

	public ArrayList<Ticket> getRevenueByEvent() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		ArrayList<Ticket> ticket = new ArrayList<Ticket>();
		String query = "SELECT event_name, SUM(price) AS total_revenue FROM ticket GROUP BY event_name ORDER BY total_revenue DESC;";
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
			Ticket tick = new Ticket();
			tick.setEventName((String) row.get("event_name"));
			tick.setTotalRevenue(((BigDecimal) row.get("total_revenue")).doubleValue());

			ticket.add(tick);
		}
		return ticket;
	}
	
	public ArrayList<Ticket> getListOfAttendees(){
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		ArrayList<Ticket> ticket = new ArrayList<Ticket>();
		String query = "SELECT name,email,event_name, event_date FROM ticket GROUP BY Event_name, name, event_date;";
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for(Map<String, Object> row: rows) {
			Ticket tick = new Ticket();
			tick.setName((String) row.get("name"));
			tick.setEmail((String) row.get("email"));
			tick.setEventName((String) row.get("event_name"));
			tick.setEventDate((String) row.get("event_date"));
			
			ticket.add(tick);
		}
		
		return ticket;
	}

	public ArrayList<Ticket> getMaxAttendanceBySection() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		ArrayList<Ticket> ticket = new ArrayList<Ticket>();
	    String query = "SELECT EVENT_NAME, ARTIST_NAME, SECTION, MAX(attendance_count) AS MAX_ATTENDANCE "
                + "FROM ( "
                + "    SELECT EVENT_NAME, ARTIST_NAME, SECTION, COUNT(*) AS attendance_count "
                + "    FROM ticket "
                + "    GROUP BY EVENT_NAME, ARTIST_NAME, SECTION "
                + ") subquery "
                + "GROUP BY EVENT_NAME, ARTIST_NAME, SECTION "
                + "ORDER BY MAX_ATTENDANCE DESC";
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for(Map<String, Object> row : rows) {
			Ticket tick = new Ticket();
			tick.setEventName((String) row.get("event_name"));
			tick.setArtistName((String) row.get("artist_name"));
			tick.setSection((String) row.get("section"));
			tick.setMaxAttendance((long) row.get("max_attendance"));
			
			ticket.add(tick);
		}
		return ticket;
	}
	
	public ArrayList<Ticket> getNumberOfParkingNeeded(){
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		ArrayList<Ticket> ticket =  new ArrayList<Ticket>();
		String query = "SELECT event_name, event_date, COUNT(*) as parking_requests FROM ticket WHERE parking = true GROUP BY event_name, event_date;";
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for(Map<String, Object> row : rows) {
			Ticket tick =  new Ticket();
			tick.setEventName((String) row.get("event_name"));
			tick.setEventDate((String) row.get("event_date"));
			tick.setNumOfParking((long)row.get("parking_requests"));
			
			ticket.add(tick);
		}
		return ticket;
	}

}
