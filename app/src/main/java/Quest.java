public class Quest {
    private String name;
    private User giver;
    private User[] takers;
    private int reward;
    private String description;

    public Quest(String name, User giver, int reward) {
        this.name = name;
        this.giver = giver;
        this.reward = reward;
        this.takers = new String[5];
        this.description = "This is a quest";
    }

    public User getName() {
        return name;
    }
    public void setName(User name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public User getGiver() {
        return giver;
    }
    public void setGiver(User giver) {
        this.giver = giver;
    }
    public int getReward() {
        return reward;
    }
    public void setReward(int reward) {
        this.reward = reward;
    }

    public User[] getTakers() {
        return takers;
    }
    public void setTakers(User[] takers) {
        this.takers = takers;
    }
    public String toString() {
        return name + ", a quest by" + giver.toString();
    }
}
