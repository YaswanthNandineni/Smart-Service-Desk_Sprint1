// Ticket.java - With Validation and Exception Handling
package model;

import java.time.LocalDateTime;

public class Ticket {
    private int ticketId;
    private String subject;
    private String description;
    private String status;
    private LocalDateTime createdAt;

    private int userId;
    private int agentId;
    private int categoryId;

    public Ticket(String subject, String description, String status,
                  int userId, int agentId, int categoryId) {
        if (subject == null || description == null || status == null) {
            throw new IllegalArgumentException("Subject, description, and status cannot be null.");
        }
        if (!isValidStatus(status)) {
            throw new IllegalArgumentException("Invalid status. Allowed: Open, In Progress, Closed.");
        }
        this.subject = subject;
        this.description = description;
        this.status = status;
        this.userId = userId;
        this.agentId = agentId;
        this.categoryId = categoryId;
        this.createdAt = LocalDateTime.now();
    }

    private boolean isValidStatus(String status) {
        return status.equals("Open") || status.equals("In Progress") || status.equals("Closed");
    }

    // Getters and setters
    public int getTicketId() { return ticketId; }
    public void setTicketId(int ticketId) { this.ticketId = ticketId; }

    public String getSubject() { return subject; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public int getUserId() { return userId; }
    public int getAgentId() { return agentId; }
    public int getCategoryId() { return categoryId; }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", userId=" + userId +
                ", agentId=" + agentId +
                ", categoryId=" + categoryId +
                '}';
    }
}
