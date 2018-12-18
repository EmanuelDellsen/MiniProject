package Main;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

public class JsonExceptionMethods {

    public void checkDate(String dateRetrieved) {
        if (!dateRetrieved.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new JSONException("Oj oj oj... Looks like you haven't used the data format yyyy-mm-dd somewhere in the JSON File");
        }
    }

    //The following methods validate if IDs are integers
    public void validateProjectId(CharSequence idString) {
        if (!StringUtils.isNumeric(idString)) {
            throw new JSONException("Ooops... Looks like your Project ID contains letters. Please enter a number instead");
        }
    }

    public void validateTeamMemberId(CharSequence idString) {
        if (!StringUtils.isNumeric(idString)) {
            throw new JSONException("Ooops... Looks like one teamMemberID ID contains letters. Please enter a number instead");
        }
    }

    public void validateRiskId(CharSequence riskIdString) {
        if (!StringUtils.isNumeric(riskIdString)) {
            throw new JSONException("Ooops... Looks like one riskID contains letters. Please enter a number instead");
        }
    }

    public void validateTaskId(CharSequence taskId) {
        if (!StringUtils.isNumeric(taskId)) {
            throw new JSONException("Ooops... Looks like one taskID contains letters. Please enter a number instead");
        }
    }

    //The following methods validate if end dates are after starting date
    public void checkProjectCompletedDateAfterStart(LocalDate endDate, LocalDate startDate) {
        if (endDate.compareTo(startDate) < 0) {
            throw new JSONException("Hmmm... Looks like your project start date is after the end date. Please check the JSON-file and try again.");
        }
    }

    public void checkTaskEndDatesAfterStart(LocalDate actualStartDate, LocalDate projectedCompletedDate, LocalDate actualCompletedDate) {
        if (projectedCompletedDate.compareTo(actualStartDate) < 0) {
            throw new JSONException("Hmmm... Looks like you set your projectedCompletedDate of a task is before your actualStartDate. Please check the JSON-file and try again.");
        }
        if (actualCompletedDate.compareTo(actualStartDate) < 0) {
            throw new JSONException("Hmmm... Looks like you set your actualCompletedDate of a task before your start date. Please check the JSON-file and try again.");
        }
    }

    //The following methods validate if numbers are not negative and/or equal to zero
    public void validateBudgetAtCompletion(double budgetAtCompletion) {
        if (budgetAtCompletion <= 0) {
            throw new JSONException("Ooops.... Looks like your budgetAtCompletion is negative or at zero. Please check your JSON-file and try again");
        }
    }

    public void validateSalaryPerHour(double salaryPerHour) {
        if (salaryPerHour < 0) {
            throw new JSONException("Ooops.... Looks like your salaryPerHour is negative. Please check your JSON-file and try again");
        }
    }

    public void validateRiskProbabilityAndImpact(double probability, int impact) {
        if (probability < 0 || impact < 0) {
            throw new JSONException("Hey there! Don't put a negative impact or probability into your RiskMatrix");
        }
    }

}
