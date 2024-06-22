package Models;

public class Signup3_Models {
    private String formNo;
    private String accountType;
    private String cardNumber;
    private String pin;
    private String facility;

    public Signup3_Models(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Signup3_Models(String formNo, String accountType, String cardNumber, String pin, String facility) {
        this.formNo = formNo;
        this.accountType = accountType;
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.facility = facility;
    }

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    @Override
    public String toString() {
        return "Signup3_Models{" +
                "formNo='" + formNo + '\'' +
                ", accountType='" + accountType + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", pin='" + pin + '\'' +
                ", facility='" + facility + '\'' +
                '}';
    }
}
