package App;

import multichain.command.CommandElt;
import multichain.command.CommandManager;
import multichain.command.MultichainException;

public class MultiChainAPI {

    static CommandManager commandManager = new CommandManager("localhost", "6487",
    "multichainrpc", "redes22020", null);

    public static String getNewAddress() throws MultichainException {
        Object result = commandManager.invoke(CommandElt.GETNEWADDRESS);
        commandManager.invoke(CommandElt.GRANT, "connect");
        commandManager.invoke(CommandElt.GRANT, "send");
        commandManager.invoke(CommandElt.GRANT, "receive");
        return result.toString();
    }

    public static String getAddressBalances(String direccion) throws MultichainException {
        Object result = commandManager.invoke(CommandElt.GETADDRESSBALANCES, direccion);
        return parseAddressBalances(result);
    }

    public static void sendAssetFrom(String direccionFrom, String direccionTo, String cantidad) throws MultichainException {
        commandManager.invoke(CommandElt.SENDASSETFROM, direccionFrom, direccionTo, cantidad);
    }

    public static String parseAddressBalances(Object rawAddressBalances){
        return rawAddressBalances.toString();
    }

}
