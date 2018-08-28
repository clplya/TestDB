package Objects;

public class Customer {

    private int customerId;
    private String customerName;
    private int active;

    public Customer(int customerId, String customerName, int active) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.active = active;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
