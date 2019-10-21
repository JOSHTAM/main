package moomoo.command;

import moomoo.task.ScheduleList;
import moomoo.task.Budget;
import moomoo.task.CategoryList;
import moomoo.task.Category;
import moomoo.task.Ui;
import moomoo.task.Storage;
import moomoo.task.MooMooException;
import moomoo.task.Expenditure;

public class AddExpenditureCommand extends Command {

    public AddExpenditureCommand() {
        super(false, "");
    }

    @Override
    public void execute(ScheduleList calendar, Budget budget, CategoryList categoryList, Category category,
                        Ui ui, Storage storage)
            throws MooMooException {
        super.execute(calendar, budget, categoryList, category, ui, storage);

        ui.showAddExpenditureMessage();
        String input = ui.readCommand();
        String[] parts = input.split("-");
        String expenditureName = parts[0];
        Double amount = Double.parseDouble(parts[1]);

        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).toString().equals(expenditureName)) {
                Expenditure newExpenditure = new Expenditure(amount);
                categoryList.get(i).add(newExpenditure);
                ui.showNewExpenditureMessage(expenditureName);
            } else {
                ui.showErrorMessage("Please add the category first.");
            }
        }
    }
}

