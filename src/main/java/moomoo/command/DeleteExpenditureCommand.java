package moomoo.command;

import moomoo.task.Category;
import moomoo.task.ScheduleList;
import moomoo.task.Budget;
import moomoo.task.MooMooException;
import moomoo.task.CategoryList;
import moomoo.task.Storage;
import moomoo.task.Ui;
import moomoo.task.Expenditure;

public class DeleteExpenditureCommand extends Command {

    public DeleteExpenditureCommand() {
        super(false, "");
    }

    @Override
    public void execute(ScheduleList calendar, Budget budget, CategoryList categoryList, Category category,
                        Ui ui, Storage storage)
            throws MooMooException {
        super.execute(calendar, budget, categoryList, category, ui, storage);

        categoryList.list(ui);
        ui.showDeleteExpenditureMessage();
        String input = ui.readCommand();
        String[] parts = input.split("-");
        int categoryNumber = Integer.parseInt(parts[0]);
        double amount = Double.parseDouble(parts[1]);

        for (int i = 0; i < categoryList.get(categoryNumber).size(); i++) {
            if (categoryList.get(categoryNumber).get(i).amount() == amount) {
                categoryList.get(categoryNumber).deleteExpenditure(i);
                ui.showRemovedExpenditureMessage(categoryList.get(categoryNumber));
            } else {
                ui.showErrorMessage("Please add the expenditure first.");
            }
        }
    }
}
