package Models;

public class Bank_Models {
    private String pin;
    private String date;
    private String type;
    private String amount;

    public Bank_Models(String pin, String date, String type, String amount) {
        this.pin = pin;
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Bank_Models{" +
                "pin='" + pin + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
