package vendingmachine.cli;

import vendingmachine.cli.cli.CLI;
import vendingmachine.cli.cli.CLIFactory;
import org.beryx.textio.InputReader;
import vendingmachine.core.VendingMachine;
import vendingmachine.core.item.Item;

import java.text.DecimalFormat;
import java.util.Collection;

public class InteractiveVendingMachine {


    private VendingMachine vendingMachine;
    private CLI cli;

    {
        cli = CLIFactory.newCLI();
    }

    public InteractiveVendingMachine(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public Collection<String> listAvailableItems() {
        return cli
                .<Item>listItemSelection(vendingMachine.getInstockItems(), InteractiveVendingMachine::formatItem)
                .show();
    }

    public static String formatItem(Item item) {
        DecimalFormat df = new DecimalFormat("#.##");
        return String.format("%s @ R%s", item.getName(), df.format(item.getPrice()));
    }

    static void showAvailableItems(VendingMachine vendingMachine, InputReader reader) {

        reader
                .withNumberedPossibleValues(vendingMachine.getInstockItems())
                .read("Select an item from the machine:");

    }

}
