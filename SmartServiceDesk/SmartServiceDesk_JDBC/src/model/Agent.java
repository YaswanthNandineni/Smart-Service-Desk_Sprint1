package model;

public class Agent {
    private int agentId;
    private String name;
    private String department;

    public Agent(int agentId, String name, String department) {
        if (name == null || department == null) {
            throw new IllegalArgumentException("Agent fields cannot be null.");
        }
        this.agentId = agentId;
        this.name = name;
        this.department = department;
    }

    public int getAgentId() { return agentId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }

    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String toString() {
        return "Agent{" +
               "agentId=" + agentId +
               ", name='" + name + '\'' +
               ", department='" + department + '\'' +
               '}';
    }
}