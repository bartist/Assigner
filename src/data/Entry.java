package data;

/**
 * Created by Bart on 8-11-2017.
 */
public class Entry {

    private String name;
    private String mail;
    private String address;
    private String country;

    private boolean outofCountry;
    private boolean wantsReturn;

    private String[] optionalQuestions;
    private String[] optionalData;

    public Entry(String[] questions){
        optionalQuestions = questions;
    }

    public String getContactInformation() {
        return name + " - " + mail + "\n\n";
    }

    public String getAllInformation() {
        String res = "Name: " + name + "\n";
        res += "Address: " + address + "\n";
        res += "Country: " + country + "\n\n";
        for(int i = 0; i < optionalQuestions.length; i++){
            if(!optionalData[i].equals(""))
                res += optionalQuestions[i] + optionalData[i] + "\n";
        }
        if(wantsReturn)
            res += name + "has requested a return address to return the favor!";
        return res + "\n";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setOutofCountry(boolean outofCountry) {
        this.outofCountry = outofCountry;
    }

    public void setReturn(boolean wantsReturn) {
        this.wantsReturn = wantsReturn;
    }

    public void setOptional(String[] optional) {
        this.optionalData = optional;
    }

    public boolean canSendTo(Entry receiver) {
        return outofCountry || country.equals(receiver.getCountry());
    }

    public String getCountry() {
        return country;
    }

    public boolean canReceiveFrom(Entry sender) {
        return !wantsReturn || this.canSendTo(sender);
    }
}
