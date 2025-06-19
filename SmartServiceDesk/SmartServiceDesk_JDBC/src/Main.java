import dao.TicketDAO;
import model.Ticket;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketDAO dao = new TicketDAO();

        try {
            System.out.println("=== Create New Ticket ===");
            System.out.print("Enter Subject: ");
            String subject = sc.nextLine();

            System.out.print("Enter Description: ");
            String desc = sc.nextLine();

            System.out.print("Enter Status (Open/In Progress/Closed): ");
            String status = sc.nextLine();

            System.out.print("Enter User ID: ");
            int uid = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Agent ID: ");
            int aid = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Category ID: ");
            int cid = Integer.parseInt(sc.nextLine());

            Ticket ticket = new Ticket(subject, desc, status, uid, aid, cid);
            System.out.println("\nTicket with full object:\n" + ticket);

            dao.createTicket(ticket);
            System.out.println("\nTicket inserted successfully.");

            System.out.println("\nAll Tickets:");
            List<Ticket> tickets = dao.getAllTickets();
            for (Ticket t : tickets) {
                System.out.println(t);
            }

            System.out.println("\nSimulating Ticket Queue:");
            Queue<Ticket> queue = new LinkedList<>(tickets);
            while (!queue.isEmpty()) {
                Ticket t = queue.poll();
                System.out.println("Processing Ticket ID: " + t.getTicketId());
            }

            // Update ticket status example
            dao.updateStatus(1, "Resolved");
            System.out.println("\nTicket ID 1 status updated to Resolved.");

        } catch (IllegalArgumentException e) {
            System.err.println("Invalid Input: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}