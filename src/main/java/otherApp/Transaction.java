package otherApp;

import java.util.Date;

public class Transaction {

    Double x, y, value_usd, fee_usd;
    String block_id, sender, recipient;
    String date;

    public Transaction(Double x, Double y, Double value_usd, Double fee_usd, String block_id, String sender, String recipient, String date) {
        this.x = x;
        this.y = y;
        this.value_usd = value_usd;
        this.fee_usd = fee_usd;
        this.block_id = block_id;
        this.sender = sender;
        this.recipient = recipient;
        this.date = date;
    }

    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %s, %s, %s", block_id, x, y, sender, recipient, value_usd, fee_usd, date);
    }
}
