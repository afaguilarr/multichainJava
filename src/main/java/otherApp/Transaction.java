package otherApp;

import java.util.Date;

public class Transaction {

    Double x, y, value_usd, fee_usd;
    String block_id, sender, recipient;

    public Transaction(Double x, Double y, Double value_usd, Double fee_usd, String block_id, String sender, String recipient) {
        this.x = x;
        this.y = y;
        this.value_usd = value_usd;
        this.fee_usd = fee_usd;
        this.block_id = block_id;
        this.sender = sender;
        this.recipient = recipient;
    }
}
