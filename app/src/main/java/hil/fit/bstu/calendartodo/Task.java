package hil.fit.bstu.calendartodo;


import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Task implements Serializable {

    private Integer id;
    private String description;
    private String category;
    private LocalDate date;
    private boolean done;


    public boolean isDisplayed(LocalDate selectedDate) {
        if (selectedDate.equals(date)) return true;
        return selectedDate.equals(LocalDate.now());
    }
}


    /*public void getDescription() {
    }

    public void setDescription(String toString) {
    }

    public void getId() {
    }

    public void getDate() {
    }

    public void getCategory() {
    }

    public boolean isDone() {
        return false;
    }

    public void setId(int id) {
    }

    public void setCategory(String category) {
    }

    public void setDate(LocalDate date) {
    }

    public void setDone(Boolean done) {
    }*/

