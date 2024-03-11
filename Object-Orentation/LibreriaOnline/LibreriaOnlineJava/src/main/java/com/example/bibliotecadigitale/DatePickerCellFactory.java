package com.example.bibliotecadigitale;
import com.example.bibliotecadigitale.Model.Libro;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.Date;

public class DatePickerCellFactory implements Callback<TableColumn<Libro, LocalDate>, TableCell<Libro, LocalDate>> {
    @Override
    public TableCell<Libro, LocalDate> call(TableColumn<Libro, LocalDate> param) {
        return new DatePickerCell();
    }

    class DatePickerCell extends TableCell<Libro, LocalDate> {
        private DatePicker datePicker;

        public DatePickerCell() {
            datePicker = new DatePicker();
        }

        @Override
        protected void updateItem(LocalDate item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setGraphic(null);
            } else {
                datePicker.setValue(item);
                setGraphic(datePicker);
            }
        }
    }
}