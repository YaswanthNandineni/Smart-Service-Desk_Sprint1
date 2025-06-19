package dao;

import model.Ticket;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    public void createTicket(Ticket t) {
        String sql = "INSERT INTO Tickets (ticket_id, title, description, status, created_at, user_id, agent_id, category_id) " +
                     "VALUES (ticket_seq.NEXTVAL, ?, ?, ?, SYSTIMESTAMP, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getSubject());
            ps.setString(2, t.getDescription());
            ps.setString(3, t.getStatus());
            ps.setInt(4, t.getUserId());
            ps.setInt(5, t.getAgentId());
            ps.setInt(6, t.getCategoryId());

            ps.executeUpdate();
            System.out.println("\u2705 Ticket inserted successfully.");

        } catch (SQLIntegrityConstraintViolationException ce) {
            System.err.println("\u274C Foreign key constraint failed: " + ce.getMessage());
        } catch (SQLException e) {
            System.err.println("\u274C SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("\u274C Unexpected error: " + e.getMessage());
        }
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM Tickets";

        try (Connection con = DBUtil.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                try {
                    Ticket t = new Ticket(
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getInt("user_id"),
                        rs.getInt("agent_id"),
                        rs.getInt("category_id")
                    );
                    t.setTicketId(rs.getInt("ticket_id"));
                    t.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    tickets.add(t);
                } catch (IllegalArgumentException e) {
                    System.err.println(" Skipping ticket due to error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println(" Error fetching tickets: " + e.getMessage());
            e.printStackTrace();
        }

        return tickets;
    }

    public boolean updateStatus(int ticketId, String newStatus) {
        String sql = "UPDATE Tickets SET status = ? WHERE ticket_id = ?";
        try (Connection con = DBUtil.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newStatus);
            ps.setInt(2, ticketId);
            int rows = ps.executeUpdate();

            return rows > 0;
        } catch (Exception e) {
            System.out.println(" Failed to update status: " + e.getMessage());
            return false;
        }
    }
}
