package sumdu.edu.ua.gui;

import java.util.Comparator;
import java.util.UUID;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import sumdu.edu.ua.EmployeeDto;
import sumdu.edu.ua.app.AppBootstrap;
import sumdu.edu.ua.app.AppContext;
import sumdu.edu.ua.model.Company;
import sumdu.edu.ua.model.Employee;
import sumdu.edu.ua.model.EmployeeType;
import sumdu.edu.ua.model.Position;
import sumdu.edu.ua.service.CompanyUtils;
import sumdu.edu.ua.service.EmployeeComparators;
public class MainController {

    private ObservableList<Company> companies;
    AppBootstrap bootstrap = new AppBootstrap();
    AppContext app = bootstrap.initInMemory();

    @FXML private TextField nameSurnameField;
    @FXML private TextField ageField;
    @FXML private TextField salaryField;

    @FXML private ComboBox<Position> positionComboBox;
    @FXML private ComboBox<EmployeeType> typeComboBox;

    @FXML private Label extraLabel1;
    @FXML private TextField extraField1;

    @FXML private Label extraLabel2;
    @FXML private TextField extraField2;

    @FXML
    private TextField uuidSearchField;

    @FXML
    private ListView<Company> companiesListView;

    @FXML
    private TextArea resultTextArea;

    @FXML
    private ComboBox<String> sortComboBox;

    private Comparator<Employee> getSelectedComparator() {
        String selected = sortComboBox.getValue();

        if (selected == null) {
            return EmployeeComparators.BY_SALARY;
        }

        switch (selected) {
            case "Name":
                return EmployeeComparators.BY_NAMESURNAME;
            case "Age":
                return EmployeeComparators.BY_AGE;
            default:
                return EmployeeComparators.BY_SALARY;
        }
    }

    @FXML
    private void handleFindByUuid() {
        String uuidText = uuidSearchField.getText();

        UUID uuid;

        try {
            uuid = UUID.fromString(uuidText);
        } catch (IllegalArgumentException e) {
            resultTextArea.setText("Некоректний формат UUID.");
            return;
        }

        Company foundCompany = CompanyUtils.findByUuid(companies, uuid);

        if (foundCompany == null) {
            resultTextArea.setText("Не знайдено.");
            return;
        }

        Comparator<Employee> comparator = getSelectedComparator();

        resultTextArea.setText(
                foundCompany.toFullStringEmpSorted(comparator)
        );
    }

    @FXML
    private void hadleCreateEmployee() {
        String uuidText = uuidSearchField.getText();

        UUID uuid;

        try {
            uuid = UUID.fromString(uuidText);
        } catch (IllegalArgumentException e) {
            resultTextArea.setText("Некоректний формат UUID.");
            return;
        }

        Company selectedCompany = CompanyUtils.findByUuid(companies, uuid);
        if (selectedCompany == null) {
            resultTextArea.setText("Не знайдено  компанію за uuid.");
            return;
        }
        try {
            EmployeeDto dto = GuiEmployeeMapper.map(
                    typeComboBox.getValue(),
                    nameSurnameField.getText(),
                    ageField.getText(),
                    salaryField.getText(),
                    positionComboBox.getValue(),
                    extraField1.getText(),
                    extraField2.getText()
            );
            app.employeeService.createAndSaveEmployee(dto, selectedCompany);
            resultTextArea.setText(
                selectedCompany.toFullStringEmpSorted(getSelectedComparator())
            );

            showSuccess("Співробітника створено");

            clearFields();
        } catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }
    }

    private void clearFields() {
        nameSurnameField.clear();
        ageField.clear();
        salaryField.clear();

        positionComboBox.setValue(null);
        typeComboBox.setValue(null);

        extraField1.clear();
        extraField2.clear();

        extraLabel1.setText("");
        extraLabel2.setText("");

        extraField2.setVisible(false);
        extraLabel2.setVisible(false);
    }

    @FXML
    private Label messageLabel;

    private void showError(String message) {
        messageLabel.setText(message);
    }

    private void showSuccess(String message) {
        messageLabel.setText(message);
    }

    @FXML
    private void clearUuidField() {
        uuidSearchField.clear();
    }

    @FXML
    public void initialize() {
        sortComboBox.setItems(
            FXCollections.observableArrayList(
            "Salary",
            "Name",
            "Age"
            )
        );

        positionComboBox.setItems(
            FXCollections.observableArrayList(Position.values())
        );

        typeComboBox.setItems(
            FXCollections.observableArrayList(EmployeeType.values())
        );

        companies = FXCollections.observableArrayList();

        app.companyJsonService.loadCompanies("companies.json", companies);

        companiesListView.setItems(companies);


        companiesListView.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldCompany, newCompany) -> {
                    if (newCompany != null) {

                        ClipboardContent content = new ClipboardContent();
                        content.putString(newCompany.getUuid().toString());
                        Clipboard.getSystemClipboard().setContent(content); 
                    }
                });

        typeComboBox.setOnAction(e -> {
                EmployeeType type = typeComboBox.getValue();

                switch (type) {
                    case FULL_TIME:
                        extraLabel1.setText("Роки в компанії");
                        extraLabel2.setVisible(false);
                        extraField2.setVisible(false);
                        break;

                    case CONTRACT:
                        extraLabel1.setText("Тривалість контракту");
                        extraLabel2.setVisible(false);
                        extraField2.setVisible(false);
                        break;

                    case PART_TIME:
                        extraLabel1.setText("Годин на день");
                        extraLabel2.setVisible(false);
                        extraField2.setVisible(false);
                        break;

                    case INTERN:
                        extraLabel1.setText("Університет");
                        extraLabel2.setText("Місяців стажування");

                        extraLabel2.setVisible(true);
                        extraField2.setVisible(true);
                        break;
                }
            });


    }
}
