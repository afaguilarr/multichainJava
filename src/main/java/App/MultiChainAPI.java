package App;

import multichain.command.CommandElt;
import multichain.command.CommandManager;
import multichain.command.MultichainException;
import multichain.object.BalanceAssetGeneral;

import java.util.ArrayList;

public class MultiChainAPI {

    static String assetName = "bdcointest";

    static CommandManager commandManager = new CommandManager("localhost", "4252",
            "multichainrpc", "2KnfTWEjiV4G4kKJFPb5YZhS3FtKMpvAPVXNvywZ6DpK", null);

    public static String getNewAddress() throws MultichainException {
        Object result = commandManager.invoke(CommandElt.GETNEWADDRESS);
        commandManager.invoke(CommandElt.GRANT, result.toString(), "connect");
        commandManager.invoke(CommandElt.GRANT, result.toString(), "send");
        commandManager.invoke(CommandElt.GRANT, result.toString(), "receive");
        return result.toString();
    }

    public static String getAddressBalances(String direccion) throws MultichainException {
        ArrayList result = (ArrayList) commandManager.invoke(CommandElt.GETADDRESSBALANCES, direccion);
        if (result.isEmpty()) {
            return "0";
        }
        BalanceAssetGeneral balanceAssetGeneral = (BalanceAssetGeneral) result.get(0);
        return Double.toString(balanceAssetGeneral.getQty());
    }

    public static void sendAssetFrom(String direccionFrom, String direccionTo, String cantidad) throws MultichainException {
        commandManager.invoke(CommandElt.SENDASSETFROM, direccionFrom, direccionTo, assetName, Double.parseDouble(cantidad));
    }

}
