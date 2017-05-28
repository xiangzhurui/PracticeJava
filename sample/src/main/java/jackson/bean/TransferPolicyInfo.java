package jackson.bean;

import java.util.List;

public class TransferPolicyInfo {
    private String              prtId;
    private String              contId;
    private String              amount;
    private Customer            customer;
    private List<InsuredPerson> insuredPersons;

    public String getPrtId() {
        return prtId;
    }

    public void setPrtId(String prtId) {
        this.prtId = prtId;
    }

    public String getContId() {
        return contId;
    }

    public void setContId(String contId) {
        this.contId = contId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<InsuredPerson> getInsuredPersons() {
        return insuredPersons;
    }

    public void setInsuredPersons(List<InsuredPerson> insuredPersons) {
        this.insuredPersons = insuredPersons;
    }

    public TransferPolicyInfo(String prtId, String contId, String amount, Customer customer, List<InsuredPerson> insuredPersons) {
        super();
        this.prtId = prtId;
        this.contId = contId;
        this.amount = amount;
        this.customer = customer;
        this.insuredPersons = insuredPersons;
    }

    public TransferPolicyInfo() {
        super();
    }
}
